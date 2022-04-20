package com.example.todolist.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.todolist.data.TodoDatabase
import com.example.todolist.repository.TodoRepository
import com.example.todolist.model.Todo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoViewModel(application: Application): AndroidViewModel(application) {
    val readAllData: LiveData<List<Todo>>
    private val repository: TodoRepository
    init{
        val todoDao= TodoDatabase.getDatabase(application).todoDao()
        repository= TodoRepository(todoDao)
        readAllData = repository.readAllData
    }

    fun addTodo(todo: Todo){
        viewModelScope.launch(Dispatchers.IO){
            repository.addTodo(todo)
        }
    }
    fun updateTodo(todo:Todo){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateTodo(todo)
        }
    }
    fun deleteTodo(todo:Todo){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteTodo(todo)
        }
    }
}