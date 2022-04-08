package com.example.todolist.TodoData

data class Todo(
    var title: String,
    var description: String ="",
    var priority:String,
    var date: String = "", //храниться будет именно в строковом значении
    var isChecked: Boolean = false
)