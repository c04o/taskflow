package com.example.taskflow.repository

import com.example.taskflow.model.Task
import com.example.taskflow.model.TaskStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class TaskRepository {
    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks: StateFlow<List<Task>> = _tasks.asStateFlow()

    fun addTask(task: Task) {
        _tasks.update { it + task }
    }

    fun updateTask(updatedTask: Task) {
        _tasks.update { list ->
            list.map { if (it.id == updatedTask.id) updatedTask else it }
        }
    }

    fun deleteTask(taskId: String) {
        _tasks.update { list ->
            list.filterNot { it.id == taskId }
        }
    }

    fun toggleTaskStatus(taskId: String) {
        _tasks.update { list ->
            list.map {
                if (it.id == taskId) {
                    it.copy(status = if (it.status == TaskStatus.PENDING) TaskStatus.COMPLETED else TaskStatus.PENDING)
                } else it
            }
        }
    }

    fun getTaskById(taskId: String): Task? {
        return _tasks.value.find { it.id == taskId }
    }
}
