package com.example.todolist

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.LinearLayout
import androidx.core.content.ContextCompat.startActivity
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.btnAddTodo
import kotlinx.android.synthetic.main.adding_new_todo.*

class MainActivity : AppCompatActivity() {

    private lateinit var todoAdapter: TodoAdapter
    lateinit var binding: ActivityMainBinding
    lateinit var addNewTodo: addingNewTodo
    var savePreferenses: SharedPreferences?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        savePreferenses = getSharedPreferences("Todos", Context.MODE_PRIVATE)

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

    fun load(){

    }

    private fun saveData(){
        for (i in todoAdapter.todos){
            val editor = savePreferenses?.edit()
            editor?.putString(i.title, i.description)
            editor?.apply()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        saveData()
    }

    fun deleteFragment(){
        supportFragmentManager.beginTransaction().remove(addNewTodo).commit()
        rvTodoItems.isVisible=true
        btnAddTodo.isEnabled=true
        btnDeleteDoneTodos.isEnabled=true

    }

    fun reply(titleNew:String, descriptionNew:String) {
        var newTodo = Todo(titleNew, descriptionNew)
        todoAdapter.addTodo(newTodo)
    }
}
