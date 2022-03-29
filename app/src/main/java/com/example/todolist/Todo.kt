package com.example.todolist

import java.io.Serializable

data class Todo(
    var title:String,
    var description:String ="",
    //var pair: Pair<String, String>,
    var isChecked: Boolean = false
)