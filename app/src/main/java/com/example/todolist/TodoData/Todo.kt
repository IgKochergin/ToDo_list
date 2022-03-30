package com.example.todolist.TodoData

data class Todo(
    var title: String,
    var description: String ="",
    var priority:Int, //от 1 до 3, от наименее важных до наиболее важного
    var date: String = "", //храниться будет именно в строковом значении
    var isChecked: Boolean = false
)