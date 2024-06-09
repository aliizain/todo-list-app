package com.example.todolistapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.todolistapp.ui.theme.buttonColor
import com.example.todolistapp.ui.theme.textColor

@Composable
fun CreateTaskDialog(
    value: String,
    setShowDialog: (Boolean) -> Unit
) {

    val textFieldValue = remember { mutableStateOf(value) }

    Dialog(onDismissRequest = { setShowDialog(false) }) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = Color.White
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(
                        text = "Add new task",
                        fontSize = 24.sp,
                        color = textColor
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    OutlinedTextField(
                        value = textFieldValue.value,
                        onValueChange = {
                            textFieldValue.value = it
                        },
                        label = {
                            Text(text = "Task name")
                        },
                        placeholder = {
                            Text(text = "Enter task name")
                        }
                    )

                    Spacer(modifier = Modifier.height(25.dp))

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        OutlinedButton(
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(25),
                            onClick = { setShowDialog(false) }
                        ) {
                            Text(
                                text = "CANCEL",
                                fontWeight = FontWeight.Bold,
                                color = textColor
                            )
                        }

                        Button(
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(25),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = buttonColor
                            ),
                            elevation = ButtonDefaults.buttonElevation(
                                defaultElevation = 5.dp
                            ),
                            onClick = { setShowDialog(false) }
                        ) {
                            Text(
                                text = "ADD",
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CreateTaskDialogPreview() {
    CreateTaskDialog(value = "", setShowDialog = {})
}