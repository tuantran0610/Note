package com.example.notes.ui.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.format.DateFormat
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.activity.viewModels
import com.example.notes.R
import com.example.notes.model.Notes
import com.example.notes.viewmodel.NotesViewModel
import java.util.*
import kotlin.collections.ArrayList

class EditNotesActivity : AppCompatActivity() {
    val viewModel : NotesViewModel by viewModels()
    lateinit var edtTitle :EditText
    lateinit var edtNote :EditText
    lateinit var edtHour :EditText
    lateinit var btnEdit : Button
    lateinit var btnBack : ImageButton
    lateinit var arrayList : ArrayList<Notes>
    var id =0


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_notes)

        edtTitle = findViewById(R.id.OldTitle)
        edtNote = findViewById(R.id.OldNotes)
        edtHour = findViewById(R.id.OldHour)
        btnEdit = findViewById(R.id.btnEdit)
        btnBack = findViewById(R.id.btnBack)

        id = intent.getIntExtra("id",0)

        var oldNotes = viewModel.getANotes(id).get(0)



        edtTitle.setText(oldNotes.title)
        edtNote.setText(oldNotes.notes)
        edtHour.setText(oldNotes.hour)

        btnEdit.setOnClickListener {
            updateNotes()

        }

        btnBack.setOnClickListener {
            onBackPressed()

        }

    }

    private fun updateNotes() {
        val title = edtTitle.text.toString()
        val notes = edtNote.text.toString()
        val hour = edtHour.text.toString()

        val d = Date()

        val notesDate : CharSequence = DateFormat.format("d MMMM yyyy" , d.time)

        val data = Notes(
            id,
            title =title,
            notes = notes,
            hour = hour,
            date = notesDate.toString()
        )

        viewModel.updateNotes(data)

        finish()

    }
}