package com.example.notes.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.MainActivity
import com.example.notes.R
import com.example.notes.databinding.FragmentHomeBinding
import com.example.notes.model.Notes
import com.example.notes.ui.activity.CreateNotesActivity
import com.example.notes.ui.activity.EditNotesActivity
import com.example.notes.ui.adapter.NoteAdapter
import com.example.notes.viewmodel.NotesViewModel


class HomeFragment : Fragment() {

    private var loadDone:(() -> Unit)?=null
    lateinit var binding: FragmentHomeBinding
    val viewModel : NotesViewModel by viewModels()
    var MyNotes = arrayListOf<Notes>()
   // lateinit var adapter: NoteAdapter
    private val notesAdapter = NoteAdapter()
    private var noteList = ArrayList<Notes>()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater,container,false)


        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rcvAllNotes.layoutManager = layoutManager

        deleteItem()


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getData()

        loadDone={
            notesAdapter.addList(noteList)
        }
        binding.rcvAllNotes.adapter = notesAdapter

        //EditNotes
        notesAdapter.onclickItem ={
            val intent =Intent((activity as MainActivity),EditNotesActivity::class.java)
            intent.putExtra("id",it)
            (activity as MainActivity).startActivity(intent)
        }
        // Add Notes
        binding.btnAddNotes.setOnClickListener {
            val intent =Intent((activity as MainActivity),CreateNotesActivity::class.java)
            (activity as MainActivity).startActivity(intent)
        }
    }

    private fun getData(){
        viewModel.getNotes().observe(viewLifecycleOwner){ notesList ->
            noteList = notesList as ArrayList<Notes>
            loadDone?.invoke()
        }
    }

    private fun deleteItem() {
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                TODO("Not yet implemented")
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val id = notesAdapter.getNotesId(viewHolder.adapterPosition)
                if (id != null) {
                    viewModel.deleteNotes(id)
                }

            }

        }).attachToRecyclerView(binding.rcvAllNotes)
    }


}