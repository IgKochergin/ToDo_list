package com.example.todolist

import android.content.Intent
import android.os.Bundle
import android.system.Os.remove
import android.text.TextUtils.replace
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.todolist.databinding.AddingNewTodoBinding
//import com.example.todolist.databinding.FragmentMyFragmentBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.adding_new_todo.*

class addingNewTodo : Fragment(R.layout.adding_new_todo) {
    lateinit var binding: AddingNewTodoBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AddingNewTodoBinding.inflate(layoutInflater)


        binding.btnAddTodo.setOnClickListener {
            val title = binding.todoTitle.text.toString()
            val description = binding.description.text.toString()
            (activity as MainActivity).reply(title, description)
            (activity as MainActivity).deleteFragment()
        }

        return binding.root
    }


}