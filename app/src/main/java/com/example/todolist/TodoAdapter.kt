//класс адаптера для отображения списка элементов типа Todo в RecyclerView
package com.example.todolist

import android.content.Context
import android.graphics.Color
import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.ListFragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.model.Todo
import com.example.todolist.viewmodel.TodoViewModel
import kotlinx.android.synthetic.main.item_todo.view.*

class TodoAdapter(val mainActivity: MainActivity):
        RecyclerView.Adapter<TodoAdapter.TodoViewHolder>()
{
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
    //метод для зачеркивания текста, если задача выполнена
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
    //метод для привязки данных к элементам списка
    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val curTodo = todoList[position]
        holder.itemView.apply {
            tvTodoTitle.text = curTodo.title
            tvTodoDescription.text = curTodo.description
            cbDone.isChecked=curTodo.isChecked

           // when(todoList[position].priority){
            holder.itemView.setBackgroundColor(Color.parseColor(
                when(todoList[position].priority){
                    "Низкий"->"#A1FF81"
                    "Средний"->"#FCFF57"
                    else-> "#FF8787"
                }
            ))
                //"Низкий"->holder.itemView.setBackgroundColor(Color.parseColor("#A1FF81"))
               // "Средний"->holder.itemView.setBackgroundColor(Color.parseColor("#FCFF57"))
                //else-> holder.itemView.setBackgroundColor(Color.parseColor("#FF8787"))
            //}

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
    //метод для определения количества отображаемых элементов
    override fun getItemCount(): Int {return todoList.size}
    //метод для удаления всех выполненных заданий.
    //Вызывается при нажатии кнопки "удалить" в MainActivity
    fun deleteDoneTodos(viewModel: TodoViewModel){
        for (i in todoList){
            if (i.isChecked)
                viewModel.deleteTodo(i)
        }
    }
    //устанавливает данные
    fun setData(todo:List<Todo>){
        this.todoList=todo
        notifyDataSetChanged()
    }
}