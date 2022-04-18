package com.example.todolist.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.todolist.model.Todo

@Dao
interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTodo(todo : Todo)

    @Update
    suspend fun updateTodo(todo:Todo)

    @Query("SELECT * FROM todo_table ORDER BY id ASC ")
    fun readAllData():LiveData<List<Todo>>
}