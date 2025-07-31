package com.example.myapplication.ui.auth

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.myapplication.ui.utils.PreferenceHelper
import com.example.roses_and_recipes.R
import com.example.roses_and_recipes.data.local.database.AppDatabase
import com.example.roses_and_recipes.databinding.FragmentLoginBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private var isPasswordVisible = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 👁 Toggle password visibility
        binding.eyeToggle.setOnClickListener {
            isPasswordVisible = !isPasswordVisible
            binding.passwordInput.inputType = if (isPasswordVisible) {
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            } else {
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            }
            binding.eyeToggle.setImageResource(if (isPasswordVisible) R.drawable.eye else R.drawable.hide)
            binding.passwordInput.setSelection(binding.passwordInput.text.length)
        }

        // 🔐 Login logic
        binding.loginBtn.setOnClickListener {
            val username = binding.emailInput.text.toString().trim()
            val password = binding.passwordInput.text.toString().trim()

            if (username.isEmpty() || password.isEmpty()) {
                showSnackbar("Please fill in all fields")
                return@setOnClickListener
            }

            lifecycleScope.launch {
                try {
                    val userDao = AppDatabase.getInstance(requireContext()).userDao()
                    val user = userDao.getUser(username)

                    if (user != null && user.password == password) {
                        PreferenceHelper.setLoggedIn(requireContext())

                        findNavController().navigate(
                            R.id.action_loginFragment_to_homeFragment,
                            null,
                            NavOptions.Builder()
                                .setPopUpTo(R.id.loginFragment, true)
                                .build()
                        )
                    } else {
                        showSnackbar("Invalid username or password")
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    showSnackbar("Login failed: ${e.localizedMessage}")
                }
            }
        }

        // 👉 Go to SignUp
        binding.SignUPBtn.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
        }
    }

    private fun showSnackbar(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG)
            .setBackgroundTint(resources.getColor(R.color.dishdash_red, null))
            .setTextColor(resources.getColor(android.R.color.white, null))
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
