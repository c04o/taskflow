package com.example.taskflow.model

import java.util.UUID

enum class Priority {
    LOW, MEDIUM, HIGH
}

enum class TaskStatus {
    PENDING, COMPLETED
}

data class Task(
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val description: String,
    val priority: Priority = Priority.MEDIUM,
    val status: TaskStatus = TaskStatus.PENDING
)
