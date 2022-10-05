package com.example.notes.ui.fragments

import android.os.Bundle
import android.text.format.DateFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.notes.R
import com.example.notes.databinding.FragmentEditNoteBinding
import com.example.notes.model.Notes
import com.example.notes.viewmodel.NotesViewModel
import java.util.*


class EditNoteFragment : Fragment() {

    val oldNotes by navArgs<EditNoteFragmentArgs>()
    lateinit var binding : FragmentEditNoteBinding
    val viewModel : NotesViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentEditNoteBinding.inflate(layoutInflater,container,false)
        binding.edtOldTitle.setText(oldNotes.data.title)
        binding.edtOldNotes.setText(oldNotes.data.notes)
        binding.edtOldHour.setText(oldNotes.data.hour.toString())

        binding.btnEditNotes.setOnClickListener {
            updateNotes(it)

        }
        binding.btnPieChart.setOnClickListener{
            val action = EditNoteFragmentDirections.actionEditNoteFragmentToChartFragment(oldNotes.data)
            Navigation.findNavController(it).navigate(action)


        }



        return binding.root
    }

    private fun updateNotes(it: View?) {

        val title = binding.edtOldTitle.text.toString()
        val notes = binding.edtOldNotes.text.toString()
        val hour = binding.edtOldHour.text.toString()

        val d = Date()

        val notesDate : CharSequence = DateFormat.format("d MMMM yyyy" , d.time)

        val data = Notes(
            oldNotes.data.id,
            title =title,
            notes = notes,
            hour = hour,
            date = notesDate.toString()
        )

        viewModel.updateNotes(data)

        Navigation.findNavController(it!!).navigate(R.id.action_editNoteFragment_to_homeFragment)



    }


}