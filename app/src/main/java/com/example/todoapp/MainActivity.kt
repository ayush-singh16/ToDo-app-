package com.example.todoapp

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var todoEditText: EditText
    private lateinit var addButton: Button
    private lateinit var todoListView: ListView
    private lateinit var todoList: MutableList<String>
    private lateinit var listAdapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        todoEditText = findViewById(R.id.todo_edittext)
        addButton = findViewById(R.id.add_button)
        todoListView = findViewById(R.id.todo_listview)

        todoList = mutableListOf()
        listAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, todoList)
        todoListView.adapter = listAdapter

        addButton.setOnClickListener { addTodo() }
        todoListView.setOnItemLongClickListener { _, _, position, _ -> removeTodoAt(position); true }
    }

    private fun addTodo() {
        val newTodo = todoEditText.text.toString()
        if (newTodo.isNotEmpty()) {
            todoList.add(newTodo)
            listAdapter.notifyDataSetChanged()
            todoEditText.text.clear()
        }
    }

    private fun removeTodoAt(position: Int) {
        todoList.removeAt(position)
        listAdapter.notifyDataSetChanged()
    }
}
