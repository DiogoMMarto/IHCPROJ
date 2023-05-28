package com.example.mhiru.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mhiru.GlobalVariables
import com.example.mhiru.MainActivity
import com.example.mhiru.R
import com.example.mhiru.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val profileViewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)

        // Use the null-check operator (?.) to safely access `binding`
        val root: View = binding.root
        val tochatbtn=binding.button2

        tochatbtn.setOnClickListener {
            val intent = Intent(context,MainActivity::class.java)
            intent.putExtra("fragmentToLoad","conversation")
            intent.putExtra("key",GlobalVariables.user)

            startActivity(intent)
        }

        return root
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main, menu)

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
