package com.example.todolist

import android.content.Intent
import android.os.Bundle
import android.system.Os.remove
import android.text.TextUtils
import android.text.TextUtils.replace
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.fragment.app.Fragment
import com.example.todolist.databinding.AddingNewTodoBinding
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.adding_new_todo.*

class addingNewTodo : Fragment(R.layout.adding_new_todo) {

    lateinit var binding: AddingNewTodoBinding
    lateinit var priorityStr:String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AddingNewTodoBinding.inflate(layoutInflater)

        binding.prioritySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long) {
                    priorityStr = adapterView?.getItemAtPosition(position).toString()
                }
                override fun onNothingSelected(p0: AdapterView<*>?) {

                }
        }

        binding.btnAddTodo.setOnClickListener {
            val title = binding.todoTitle.text.toString()
            val description = binding.description.text.toString()
            if(inputCheck(title, description)) {
                (activity as MainActivity).reply(title, description, priorityStr)
                (activity as MainActivity).deleteFragment()
                Toast.makeText(requireContext(), "Добавлено", Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText(requireContext(), "Заполните поля названия и описания", Toast.LENGTH_LONG).show()
            }
        }

        return binding.root
    }
    private fun inputCheck(title:String, description:String):Boolean{
        return !(TextUtils.isEmpty(title) && TextUtils.isEmpty(description))
    }
}