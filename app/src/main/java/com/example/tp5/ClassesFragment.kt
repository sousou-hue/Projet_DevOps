package com.example.tp5

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import android.widget.Button
import com.example.tp5.R

class ClassesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_classes, container, false)

        val btnClass1: Button = view.findViewById(R.id.btnClass1)
        val btnClass2: Button = view.findViewById(R.id.btnClass2)

        btnClass1.setOnClickListener {
            val bundle = Bundle().apply {
                putString("className", "Classe 1")
            }
            findNavController().navigate(R.id.absenceFormFragment, bundle)
        }

        btnClass2.setOnClickListener {
            val bundle = Bundle().apply {
                putString("className", "Classe 2")
            }
            findNavController().navigate(R.id.absenceFormFragment, bundle)
        }

        return view
    }
}
