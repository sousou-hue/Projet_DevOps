package com.example.tp5

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.tp5.AbsenceDatabaseHelper
import com.example.tp5.R

class AbsenceFormFragment : Fragment() {

    private lateinit var dbHelper: AbsenceDatabaseHelper

    private lateinit var cbStudent1: CheckBox
    private lateinit var cbStudent2: CheckBox
    private lateinit var cbStudent3: CheckBox
    private lateinit var btnSubmit: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_absence_form, container, false)

        dbHelper = AbsenceDatabaseHelper(requireContext())

        // Récupération manuelle de l'argument "className"
        val className = arguments?.getString("className") ?: ""

        cbStudent1 = view.findViewById(R.id.cbStudent1)
        cbStudent2 = view.findViewById(R.id.cbStudent2)
        cbStudent3 = view.findViewById(R.id.cbStudent3)
        btnSubmit = view.findViewById(R.id.btnSubmit)

        btnSubmit.setOnClickListener {
            val absences = listOf(
                Pair("Étudiant 1", cbStudent1.isChecked),
                Pair("Étudiant 2", cbStudent2.isChecked),
                Pair("Étudiant 3", cbStudent3.isChecked),
            )
            absences.forEach {
                if (it.second) {
                    dbHelper.insertAbsence(className, it.first)
                }
            }
            Toast.makeText(requireContext(), "Absences enregistrées", Toast.LENGTH_SHORT).show()
        }

        return view
    }
}
