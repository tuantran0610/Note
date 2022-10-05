package com.example.notes.ui.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.format.DateFormat
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.viewModels
import androidx.navigation.Navigation
import com.example.notes.R
import com.example.notes.model.Notes
import com.example.notes.viewmodel.NotesViewModel
import java.util.*

class CreateNotesActivity : AppCompatActivity() {

    val viewModel : NotesViewModel by viewModels()
    lateinit var edtTitle : EditText
    lateinit var edtNote : EditText
    lateinit var edtHour : EditText
    lateinit var btnAdd : Button
    lateinit var btnBack : ImageButton

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_notes)

        edtTitle = findViewById(R.id.edtNoteTitle)
        edtNote = findViewById(R.id.edtNote)
        edtHour = findViewById(R.id.edtNoteHour)
        btnAdd = findViewById(R.id.btnSaveNote)
        btnBack = findViewById(R.id.btnBack)


        btnAdd.setOnClickListener {

            createNotes()

        }
        btnBack.setOnClickListener {
            onBackPressed()


        }


    }

    private fun createNotes() {
        var title = edtTitle.text.toString()
        var notes = edtNote.text.toString()
        var hour = edtHour.text.toString()
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
                finish()
        }else{
            Toast.makeText(this,"Số giờ làm việc nhỏ hơn hoặc bằng 8", Toast.LENGTH_SHORT).show()
        }



    }
}