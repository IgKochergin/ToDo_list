package com.example.todolist.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "todo_table")
data class Todo(
    @PrimaryKey(autoGenerate =true)
    val id: Int,
    var title: String,
    var description: String ="",
    var priority:String,
    var date: String = "", //храниться будет именно в строковом значении
    var isChecked: Boolean = false
)