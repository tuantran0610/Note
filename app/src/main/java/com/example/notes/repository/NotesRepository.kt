package com.example.notes.repository

import androidx.lifecycle.LiveData
import com.example.notes.dao.NotesDao
import com.example.notes.model.Notes

class NotesRepository(val dao: NotesDao) {

    fun getAllNotes() : LiveData<List<Notes>> = dao.getNotes()

    fun getANotes(id:Int) :List<Notes> {
        return dao.getANotes(id)
    }

    fun insertNotes(notes: Notes){
        return dao.insertNotes(notes)
    }

    fun deleteNotes(id : Int){
        return dao.deleteNotes(id)
    }
    fun updateNotes(notes: Notes){
        return dao.updateNotes(notes)
    }
}