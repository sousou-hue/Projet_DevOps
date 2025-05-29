package com.example.tp5

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment

class HistoryFragment : Fragment() {

    private lateinit var dbHelper: AbsenceDatabaseHelper
    private lateinit var listView: ListView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_history, container, false)

        dbHelper = AbsenceDatabaseHelper(requireContext())
        listView = view.findViewById(R.id.listViewAbsences)

        val absences = dbHelper.getAllAbsences()
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, absences)
        listView.adapter = adapter

        return view
    }
}
