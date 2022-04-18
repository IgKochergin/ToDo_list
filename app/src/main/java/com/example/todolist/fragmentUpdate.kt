package com.example.todolist

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.todolist.databinding.FragmentUpdateBinding
import com.example.todolist.model.Todo
import com.example.todolist.viewmodel.TodoViewModel
import kotlinx.android.synthetic.main.adding_new_todo.*
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*

class fragmentUpdate(var todo: Todo) : Fragment(R.layout.fragment_update) {

    private lateinit var mTodoViewModel: TodoViewModel
    lateinit var binding: FragmentUpdateBinding
    lateinit var priority:String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val listForSpinner= mutableListOf("Низкий", "Средний", "Высокий")
        binding = FragmentUpdateBinding.inflate(layoutInflater)

        mTodoViewModel = ViewModelProvider(this).get(TodoViewModel::class.java)

        binding.todoTitleUp.setText(todo.title)
        binding.todoDescriptionUp.setText(todo.description)
        binding.prioritySpinnerUp.setSelection(listForSpinner.indexOf(todo.priority))

        binding.prioritySpinnerUp.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long) {
                priority = adapterView?.getItemAtPosition(position).toString()
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }

        binding.btnUpdateTodo.setOnClickListener{
            updateItem()
        }
        return binding.root
    }

    private fun updateItem(){
        val title = todoTitleUp.text.toString()
        val description = todoDescriptionUp.text.toString()
        if(inputCheck(title, description)) {
            val updatedTodo=Todo(todo.id, title,description,priority)
            mTodoViewModel.updateTodo(updatedTodo)
            (activity as MainActivity).deleteFragmentUpdate()
            Toast.makeText(requireContext(), "Изменено", Toast.LENGTH_LONG).show()
        }
        else{
            Toast.makeText(requireContext(), "Заполните поля названия и описания", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(title:String, description:String):Boolean{
        return !(TextUtils.isEmpty(title) && TextUtils.isEmpty(description))
    }
}