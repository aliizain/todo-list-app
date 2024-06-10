package com.example.todolistapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolistapp.data.Todo
import com.example.todolistapp.data.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(private val todoRepository: TodoRepository) : ViewModel() {

    private val _todoList = MutableStateFlow<List<Todo>>(emptyList())
    val todoList = _todoList.asStateFlow()

    init {
        viewModelScope.launch {
            todoRepository.getAllTodos().collectLatest { todos ->
                _todoList.value = todos
            }
        }
    }

    fun insert(todo: Todo) {
        viewModelScope.launch(Dispatchers.IO) {
            todoRepository.insertTodo(todo)
        }
    }

    fun update(todo: Todo) {
        viewModelScope.launch(Dispatchers.IO) {
            todoRepository.updateTodo(todo)
        }
    }

    fun delete(todo: Todo) {
        viewModelScope.launch(Dispatchers.IO) {
            todoRepository.deleteTodo(todo)
        }
    }
}