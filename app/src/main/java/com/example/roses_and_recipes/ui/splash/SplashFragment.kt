package com.example.myapplication.ui.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.roses_and_recipes.R
import com.example.myapplication.ui.utils.PreferenceHelper
import com.example.roses_and_recipes.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {

    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Simulate loading delay
        Handler(Looper.getMainLooper()).postDelayed({
            if (PreferenceHelper.isLoggedIn(requireContext())) {
                // User is logged in → go to Home
                findNavController().navigate(
                    R.id.action_splashFragment_to_homeFragment
                )
            } else {
                // Not logged in → go to Login
                findNavController().navigate(
                    R.id.action_splashFragment_to_loginFragment
                )
            }
        }, 2000) // 2-second delay
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
