package com.wellmember.app.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.wellmember.app.data.network.AuthApi
import com.wellmember.app.data.network.Resource
import com.wellmember.app.data.repository.AuthRepository
import com.wellmember.app.ui.Base.BaseFragment
import com.wellmember.app.databinding.FragmentLoginBinding
import kotlinx.coroutines.launch


class LoginFragment : BaseFragment<AuthViewModel, FragmentLoginBinding, AuthRepository>() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.loginResponse.observe(viewLifecycleOwner, Observer {
            when(it){
                is Resource.Success -> {
                    lifecycleScope.launch{
                        userPreferences.saveAuthToken(it.value.access_token)
                    }
                }
                is Resource.Failure -> {
                    Toast.makeText(requireContext(), "Login Failure", Toast.LENGTH_LONG).show()
                }
            }
        })

        binding.loginBtn.setOnClickListener{

            val customerid = binding.account.text.toString().trim().uppercase()
            val password = binding.password.text.toString().trim()

            // TODO add input validations
            viewModel.login(customerid, password)

        }

    }

    override fun getViewModel() = AuthViewModel::class.java

    override fun getFragmentBinding(
        inflate: LayoutInflater,
        container: ViewGroup?
    ) = FragmentLoginBinding.inflate(inflate, container, false)

    override fun getFragmentRepository() = AuthRepository(remoteDataSource.buildApi(AuthApi::class.java))

}