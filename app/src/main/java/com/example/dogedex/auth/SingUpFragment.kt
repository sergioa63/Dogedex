package com.example.dogedex.auth

import android.os.Bundle
import android.provider.ContactsContract
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dogedex.R
import com.example.dogedex.databinding.FragmentSingUpBinding

class SingUpFragment : Fragment() {

    private lateinit var binding : FragmentSingUpBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSingUpBinding.inflate(inflater)
        setupSignUpButton()
        return binding.root
    }

    private fun setupSignUpButton() {
        binding.signUpButton.setOnClickListener {
            validateFields()
        }
    }

    private fun validateFields() {
        binding.confirmPasswordInput.error = ""
        binding.passwordInput.error = ""
        binding.emailInput.error = ""
        val email = binding.emailEdit.text.toString()
        if(!isValidEmail(email)) {
            binding.emailInput.error = getString(R.string.email_is_not_valid)
            return
        }
        val password = binding.passwordEdit.text.toString()
        if(password.isEmpty()){
            binding.passwordInput.error = getString(R.string.password_must_not_be_empty)
            return
        }

        val passwordConfirmation = binding.confirmPasswordEdit.text.toString()
        if(passwordConfirmation.isEmpty()){
            binding.confirmPasswordInput.error = getString(R.string.password_must_not_be_empty)
            return
        }
        if(password != passwordConfirmation){
            binding.passwordInput.error = getString(R.string.passwords_do_not_match)
            return
        }
    }

    private fun isValidEmail(email: String?) : Boolean {
        return !email.isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}