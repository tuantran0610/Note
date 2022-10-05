package com.example.notes.model

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey


@Parcelize
@Entity(tableName="Notes")
class Notes (
    @PrimaryKey(autoGenerate  = true)
    var id : Int?= null,
    var title : String,
    var date : String,
    var notes : String,
    var hour : String
): Parcelable