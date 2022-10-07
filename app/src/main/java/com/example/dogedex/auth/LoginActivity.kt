package com.example.dogedex.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.navigation.findNavController
import com.example.dogedex.MainActivity
import com.example.dogedex.R
import com.example.dogedex.api.ApiResponceStatus
import com.example.dogedex.databinding.ActivityLoginBinding
import com.example.dogedex.model.User

class LoginActivity : AppCompatActivity(), LoginFragment.LoginFragmentActions,
    SingUpFragment.SingUpFragmentActions {

    private val viewModel : AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val loadingWheel = binding.loadingWheel

        viewModel.status.observe(this) { status ->
            when (status) {
                is ApiResponceStatus.Error -> {
                    loadingWheel.visibility = View.GONE
                    Toast.makeText(this, getText(status.msn), Toast.LENGTH_LONG).show()
                }
                is ApiResponceStatus.Loading -> loadingWheel.visibility = View.VISIBLE
                is ApiResponceStatus.Success -> loadingWheel.visibility = View.GONE
            }
        }
        viewModel.user.observe(this) { user ->
            if (user != null) {
                User.setLoggedInUser(this, user)
                startMainActivity()
            }
        }
    }

    private fun startMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun onRegistrerButtonClick() {
        findNavController(R.id.nav_host_fragment).navigate(LoginFragmentDirections.actionLoginFragmentToSingUpFragment())
    }

    override fun onLoginFieldsValidated(email: String, password: String) {
        viewModel.login(email, password)
    }

    override fun onSingUpFieldsValidated(
        email: String,
        password: String,
        passwordConfirmation: String
    ) {
        viewModel.singUp(email, password, passwordConfirmation)
    }

    private  fun showErrorDialog (msn : Int){
        AlertDialog.Builder(this)
            .setTitle(R.string.there_was_an_error)
            .setMessage(msn)
            .setPositiveButton(android.R.string.ok) { _, _ -> }
            .create()
            .show()
    }
}