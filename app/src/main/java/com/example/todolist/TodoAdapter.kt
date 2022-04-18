package com.example.todolist

import android.graphics.Color
import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.ListFragment
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.model.Todo
import kotlinx.android.synthetic.main.item_todo.view.*

class TodoAdapter(val mainActivity: MainActivity):
        RecyclerView.Adapter<TodoAdapter.TodoViewHolder>()
{

    /*private val todos: MutableList<Todo>*/
    private var todoList = emptyList<Todo>()
    class TodoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_todo,
                parent,
                false
            )
        )
    }

    private fun toggleStrikeThrough(tvTodoTitle: TextView,tvTodoDescription: TextView , isChecked: Boolean){
        if(isChecked){
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
            tvTodoDescription.paintFlags = tvTodoDescription.paintFlags or STRIKE_THRU_TEXT_FLAG
        }
        else{
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
            tvTodoDescription.paintFlags = tvTodoDescription.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val curTodo = todoList[position]
        holder.itemView.apply {
            tvTodoTitle.text = curTodo.title
            tvTodoDescription.text = curTodo.description
            cbDone.isChecked=curTodo.isChecked

            when(todoList[position].priority){
                "Низкий"->holder.itemView.setBackgroundColor(Color.parseColor("#A1FF81"))
                "Средний"->holder.itemView.setBackgroundColor(Color.parseColor("#FCFF57"))
                else-> holder.itemView.setBackgroundColor(Color.parseColor("#FF8787"))
            }

            toggleStrikeThrough(tvTodoTitle, tvTodoDescription, curTodo.isChecked)
            cbDone.setOnCheckedChangeListener{ _, isChecked ->
                toggleStrikeThrough(tvTodoTitle,tvTodoDescription, isChecked)
                curTodo.isChecked = !curTodo.isChecked
            }

            holder.itemView.rowLayout.setOnClickListener {
                mainActivity.createUpdateFragment(todoList[position])
            }
        }

    }

    override fun getItemCount(): Int {return todoList.size}

    fun setData(todo:List<Todo>){
        this.todoList=todo
        notifyDataSetChanged()
    }
}