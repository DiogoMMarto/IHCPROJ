package com.example.mhiru.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.text.set
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.mhiru.MainActivity
import com.example.mhiru.R
import com.example.mhiru.databinding.FragmentAboutUsBinding
import com.example.mhiru.databinding.FragmentLoginBinding
import com.example.mhiru.ui.home.LoginViewModel
import com.google.android.material.navigation.NavigationView

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val linkBtn=binding.registerLink
        val loginlayout=binding.loginForm
        val registerlayout=binding.registerForm
        val loginBtn=binding.loginButton
        val registerBtn=binding.registerButton

        linkBtn.setOnClickListener {
            loginlayout.visibility=View.GONE
            registerlayout.visibility=View.VISIBLE
        }
        val linkBtn2=binding.loginLink
        linkBtn2.setOnClickListener {
            registerlayout.visibility=View.GONE
            loginlayout.visibility=View.VISIBLE
        }
        loginBtn.setOnClickListener {
            val intent = Intent(this.context, MainActivity::class.java)
            if (binding.emailInput.text.toString()=="professional"){
                intent.putExtra("key","professional")
            }
            else {
                intent.putExtra("key","client")
            }

            startActivity(intent)
        }
        registerBtn.setOnClickListener {
            val intent = Intent(this.context, MainActivity::class.java)
            intent.putExtra("key","client")

            startActivity(intent)

        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}