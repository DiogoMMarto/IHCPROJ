package com.example.mhiru.ui.logout

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mhiru.MainActivity
import com.example.mhiru.databinding.FragmentAboutUsBinding
import com.example.mhiru.databinding.FragmentLogoutBinding

class LogoutFragment : Fragment() {

    private var _binding: FragmentLogoutBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val aboutUsViewModel =
            ViewModelProvider(this).get(LogoutViewModel::class.java)

        _binding = FragmentLogoutBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val intent = Intent(this.context, MainActivity::class.java)
        intent.putExtra("key","guest")
        startActivity(intent)
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}