package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.model.Todo
import com.example.todolist.viewmodel.TodoViewModel
import com.example.todolist.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.btnAddTodo

class MainActivity : AppCompatActivity() {

    private lateinit var mTodoViewModel: TodoViewModel
    private lateinit var todoAdapter: TodoAdapter
    lateinit var binding: ActivityMainBinding
    lateinit var addNewTodo: addingNewTodo
    lateinit var fragmentUpdate: fragmentUpdate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mTodoViewModel = ViewModelProvider(this).get(TodoViewModel::class.java)

        todoAdapter = TodoAdapter(this)
        rvTodoItems.adapter=todoAdapter
        rvTodoItems.layoutManager = LinearLayoutManager(this)
        addNewTodo = addingNewTodo()

        //инизиализация объекта mTodoViewModel
        mTodoViewModel = ViewModelProvider(this).get(TodoViewModel::class.java)
        mTodoViewModel.readAllData.observe(this, Observer { todo ->
            todoAdapter.setData(todo)
        })

        //обработка нажатия кнопки "добавить"
        binding.btnAddTodo.setOnClickListener{
            rvTodoItems.isVisible=false
            btnAddTodo.isEnabled=false
            //btnDeleteDoneTodos.isEnabled=false
            supportFragmentManager.beginTransaction().apply{
                replace(R.id.fragment, addNewTodo)
                commit()
            }
        }

        //обработка нажатия кнопки "удалить"
        btnDeleteDoneTodos.setOnClickListener {
            todoAdapter.deleteDoneTodos(mTodoViewModel)
        }
    }

    //создание нового фрагмента для изменения задания
    fun createUpdateFragment(todo: Todo){
        fragmentUpdate = fragmentUpdate(todo)
        rvTodoItems.isVisible=false
        btnAddTodo.isEnabled=false
        supportFragmentManager.beginTransaction().apply{
            replace(R.id.fragment, fragmentUpdate)
            commit()
        }
    }

    //удаляет фрагмент добавления
    fun deleteFragment(){
        supportFragmentManager.beginTransaction().remove(addNewTodo).commit()
        rvTodoItems.isVisible=true
        btnAddTodo.isEnabled=true
        //btnDeleteDoneTodos.isEnabled=true
    }

    //удаляет фрагмент обновления
    fun deleteFragmentUpdate(){
        supportFragmentManager.beginTransaction().remove(fragmentUpdate).commit()
        rvTodoItems.isVisible=true
        btnAddTodo.isEnabled=true
        //btnDeleteDoneTodos.isEnabled=true
    }

    //добавляет в бд новый элемент типа Todo
    fun reply(titleNew:String, descriptionNew:String, priority:String) {
        var newTodo = Todo(0, titleNew, descriptionNew, priority)
        mTodoViewModel.addTodo(newTodo)
    }
}
