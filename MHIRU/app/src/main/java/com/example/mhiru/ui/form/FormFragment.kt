package com.example.mhiru.ui.form

import android.content.Intent
import android.os.Bundle
import android.provider.Settings.Global
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mhiru.GlobalVariables
import com.example.mhiru.MainActivity
import com.example.mhiru.databinding.FragmentFormBinding

class FormFragment : Fragment() {

    private var _binding: FragmentFormBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val slideshowViewModel =
            ViewModelProvider(this).get(FormViewModel::class.java)

        _binding = FragmentFormBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.submitbtb.setOnClickListener {
            val intent = Intent(this.context, MainActivity::class.java)
            intent.putExtra("key",GlobalVariables.user)
            startActivity(intent)
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}