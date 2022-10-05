package com.example.notes.ui.adapter

import android.content.Context
import com.example.notes.databinding.ItemNotesBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.model.Notes
import com.example.notes.ui.fragments.HomeFragmentDirections

class NoteAdapter( ) : RecyclerView.Adapter<NoteAdapter.notesViewHolder>() {
    private var notesList : ArrayList<Notes> ?=null
    fun filtering(newFilteredList: ArrayList<Notes>) {
        notesList = newFilteredList
        notifyDataSetChanged()
    }
    var onclickItem: ((Int) -> Unit)? = null
    class notesViewHolder(val binding : ItemNotesBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): notesViewHolder {
    return notesViewHolder(ItemNotesBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: notesViewHolder, position: Int) {
        val data = notesList?.get(position)
        holder.binding.notesTitle.text = data?.title
        holder.binding.notes.text = data?.notes
        holder.binding.notesDate.text = data?.date
        holder.binding.notesHour.text = data?.hour.toString() + ":00"

//        holder.binding.root.setOnClickListener {
//            val action = HomeFragmentDirections.actionHomeFragmentToEditNoteFragment(data)
//            Navigation.findNavController(it).navigate(action)
//        }

        holder.binding.root.setOnClickListener{
            data?.id?.let { it -> onclickItem?.invoke(it) }
        }

    }

    override fun getItemCount(): Int = notesList?.size?:0

    fun getNotesId(position: Int): Int? {
        return notesList?.get(position)?.id
    }

    fun addList(mNotesList: ArrayList<Notes>){
        notesList=mNotesList
        notifyDataSetChanged()
    }



}