package com.example.todolist

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.TodoData.Todo
import com.example.todolist.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.btnAddTodo

class MainActivity : AppCompatActivity() {

    private lateinit var todoAdapter: TodoAdapter
    lateinit var binding: ActivityMainBinding
    lateinit var addNewTodo: addingNewTodo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        todoAdapter = TodoAdapter(mutableListOf())
        rvTodoItems.adapter=todoAdapter
        rvTodoItems.layoutManager = LinearLayoutManager(this)
        addNewTodo = addingNewTodo()

        binding.btnAddTodo.setOnClickListener{
            rvTodoItems.isVisible=false
            btnAddTodo.isEnabled=false
            btnDeleteDoneTodos.isEnabled=false
            supportFragmentManager.beginTransaction().apply{
                replace(R.id.fragment, addNewTodo)
                commit()
            }
        }

        btnDeleteDoneTodos.setOnClickListener {
            todoAdapter.deleteDoneTodos()
        }
    }

    fun deleteFragment(){
        supportFragmentManager.beginTransaction().remove(addNewTodo).commit()
        rvTodoItems.isVisible=true
        btnAddTodo.isEnabled=true
        btnDeleteDoneTodos.isEnabled=true

    }

    fun reply(titleNew:String, descriptionNew:String, priority:Int) {
        var newTodo = Todo(titleNew, descriptionNew, priority)
        todoAdapter.addTodo(newTodo)
    }
}
