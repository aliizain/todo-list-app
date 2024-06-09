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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todolistapp.ui.components.CreateTaskDialog
import com.example.todolistapp.ui.components.TaskItem
import com.example.todolistapp.ui.theme.backgroundColor
import com.example.todolistapp.ui.theme.buttonColor
import com.example.todolistapp.ui.theme.textColor

@Composable
fun TaskListScreen(modifier: Modifier = Modifier) {

    val showDialog =  remember { mutableStateOf(false) }

    if(showDialog.value) {
        CreateTaskDialog(
            value = "",
            setShowDialog = {
                showDialog.value = it
            }
        )
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
                    items(40) {
                        TaskItem()
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TaskListScreenPreview() {
    TaskListScreen()
}