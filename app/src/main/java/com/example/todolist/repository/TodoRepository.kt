package com.example.todolist.repository

import androidx.lifecycle.LiveData
import com.example.todolist.data.TodoDao
import com.example.todolist.model.Todo

class TodoRepository(private val todoDao: TodoDao) {

    val readAllData: LiveData<List<Todo>> = todoDao.readAllData()

    suspend fun addTodo(todo: Todo){
        todoDao.addTodo(todo)
    }
    suspend fun updateTodo(todo:Todo){
        todoDao.updateTodo(todo)
    }

    suspend fun deleteTodo(todo:Todo){
        todoDao.deleteTodo(todo)
    }
}