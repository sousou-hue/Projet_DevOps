package com.example.tp5

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.card.MaterialCardView

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Gestion du bouton Emploi du Temps
        view.findViewById<MaterialCardView>(R.id.btnEmploiDuTemps).setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_emploiDuTempsFragment)
        }

        // Gestion du bouton Calendrier
        view.findViewById<MaterialCardView>(R.id.btnCalendrier).setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_calendrierFragment)
        }
    }
}