package com.example.todolistapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todolistapp.TodoViewModel
import com.example.todolistapp.data.Todo
import com.example.todolistapp.ui.components.CreateTodoDialog
import com.example.todolistapp.ui.components.EditTodoDialog
import com.example.todolistapp.ui.components.TodoItem
import com.example.todolistapp.ui.theme.backgroundColor
import com.example.todolistapp.ui.theme.buttonColor
import com.example.todolistapp.ui.theme.textColor

@Composable
fun TodoListScreen(
    modifier: Modifier = Modifier,
    todoViewModel: TodoViewModel = viewModel()
) {

    val todoList by todoViewModel.todoList.collectAsState()

    val showDialog =  remember { mutableStateOf(false) }
    val showEditDialog =  remember { mutableStateOf(false) }
    val (selectedTodo, setSelectedTodo) = remember { mutableStateOf<Todo?>(null) }

    if(showDialog.value) {
        CreateTodoDialog(
            value = "",
            setShowDialog = {
                showDialog.value = it
            },
            onAddClick = {
                todoViewModel.insert(todo = Todo(task = it, isCompleted = false))
            }
        )
    }

    if(showEditDialog.value) {
        selectedTodo?.let { todo ->
            EditTodoDialog(
                value = todo.task,
                setShowDialog = {
                    showEditDialog.value = it
                },
                onSaveClick = { newTaskName ->
                    todoViewModel.update(todo.copy(task = newTaskName))
                    showEditDialog.value = false
                }
            )
        }
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    showDialog.value = true
                },
                modifier = Modifier
                    .offset(y = (-36).dp),
                shape = CircleShape,
                containerColor = buttonColor
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Task",
                    modifier = Modifier
                        .padding(8.dp)
                        .size(64.dp),
                    tint = Color.White
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { innerPadding ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(brush = backgroundColor)
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .fillMaxSize()
            ) {
                Text(
                    text = "All Tasks",
                    fontSize = 24.sp,
                    color = textColor,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp),
                    textAlign = TextAlign.Center
                )
                LazyColumn(
                    modifier = Modifier.fillMaxHeight(),
                    contentPadding = PaddingValues(vertical = 12.dp, horizontal = 12.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(todoList) { todo ->
                        TodoItem(
                            todo = todo,
                            onClick = {
                                setSelectedTodo(todo)
                                showEditDialog.value = true
                            },
                            onDeleteClick = {
                                todoViewModel.delete(todo = todo)
                            },
                            onCheckedChange = { isChecked ->
                                todoViewModel.update(todo.copy(isCompleted = isChecked))
                            }
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TodoListScreenPreview() {
    TodoListScreen()
}