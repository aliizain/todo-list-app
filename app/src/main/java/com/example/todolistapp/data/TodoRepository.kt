package com.example.todolistapp.data

import kotlinx.coroutines.flow.Flow

class TodoRepository(
    private val todoDao: TodoDao
) {
    suspend fun insertTodo(todo: Todo) = todoDao.insertTodo(todo = todo)

    suspend fun updateTodo(todo: Todo) = todoDao.updateTodo(todo = todo)

    suspend fun deleteTodo(todo: Todo) = todoDao.deleteTodo(todo = todo)

    fun getAllTodos(): Flow<List<Todo>> = todoDao.getAllTodos()
}