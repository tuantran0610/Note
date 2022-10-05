package com.example.notes.ui.fragments

import android.os.Bundle
import android.text.format.DateFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.notes.R
import com.example.notes.databinding.FragmentCreateNoteBinding
import com.example.notes.model.Notes
import com.example.notes.viewmodel.NotesViewModel
import java.lang.String.format
import java.util.*


class CreateNoteFragment : Fragment() {
        lateinit var binding : FragmentCreateNoteBinding
        val viewModel : NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCreateNoteBinding.inflate(layoutInflater,container,false)

        binding.btnSaveNotes.setOnClickListener {
            createNotes(it)
        }
        return binding.root
    }

    private fun createNotes(it: View?) {
        var title = binding.edtNotesTitle.text.toString()
        var notes = binding.edtNotes.text.toString()
        var hour = binding.edtNotesHour.text.toString()

        if(hour.toInt() <= 8){
            val d = Date()
            val notesDate : CharSequence = DateFormat.format("d MMMM yyyy",d.time)

            val data = Notes(
                null,
                title = title,
                notes = notes,
                date = notesDate.toString(),
                hour = hour
            )
            viewModel.addNotes(data)
            Navigation.findNavController(it!!).navigate(R.id.action_createNoteFragment_to_homeFragment)
        }else{
            Toast.makeText(requireContext(),"Số giờ làm việc nhỏ hơn hoặc bằng 8",Toast.LENGTH_SHORT).show()
        }



    }

}