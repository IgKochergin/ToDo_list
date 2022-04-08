package com.example.todolist

import android.content.Intent
import android.os.Bundle
import android.system.Os.remove
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


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AddingNewTodoBinding.inflate(layoutInflater)

        val customList = resources.getStringArray(R.array.priority)

        val view = inflater.inflate(R.layout.adding_new_todo, container,false)
        val spinner:Spinner = view.findViewById(R.id.prioritySpinner)
        val adapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_item, customList)
        var priorityStr:String = "Средний"
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner!!.setAdapter(adapter)


        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long) {
                    priorityStr = adapterView?.getItemAtPosition(position).toString()
                    //Log.i("")
                }
                override fun onNothingSelected(p0: AdapterView<*>?) {
                    priorityStr="Высокий"
                }
        }

        binding.btnAddTodo.setOnClickListener {
            val title = binding.todoTitle.text.toString()
            val description = binding.description.text.toString()
            (activity as MainActivity).reply(title, description, priorityStr)
            (activity as MainActivity).deleteFragment()
        }
        return binding.root
    }
}