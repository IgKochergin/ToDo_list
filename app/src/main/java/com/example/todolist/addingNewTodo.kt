package com.example.todolist

import android.content.Intent
import android.os.Bundle
import android.system.Os.remove
import android.text.TextUtils.replace
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.fragment.app.Fragment
import com.example.todolist.databinding.AddingNewTodoBinding
import kotlinx.android.synthetic.*
//import com.example.todolist.databinding.FragmentMyFragmentBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.adding_new_todo.*

class addingNewTodo : Fragment(R.layout.adding_new_todo) {

    lateinit var binding: AddingNewTodoBinding
    var priorityInt: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AddingNewTodoBinding.inflate(layoutInflater)
        priorityInt = choosePriority()

        binding.btnAddTodo.setOnClickListener {
            val title = binding.todoTitle.text.toString()
            val description = binding.description.text.toString()
            (activity as MainActivity).reply(title, description, priorityInt)
            (activity as MainActivity).deleteFragment()
        }

        return binding.root
    }

    private fun choosePriority():Int{
        val choosing:Int
        if (priority.selectedItem.toString()=="Низкий")
            choosing=1
        else if (priority.selectedItem.toString()=="Средний")
            choosing=2
        else
            choosing=3
        return choosing
    }

}