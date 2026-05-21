package com.example.taskflow.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskflow.model.Priority
import com.example.taskflow.model.Task
import com.example.taskflow.repository.TaskRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class TaskViewModel(private val repository: TaskRepository) : ViewModel() {

    val tasks: StateFlow<List<Task>> = repository.tasks
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun addTask(title: String, description: String, priority: Priority) {
        if (title.isBlank()) return
        val newTask = Task(title = title, description = description, priority = priority)
        repository.addTask(newTask)
    }

    fun updateTask(id: String, title: String, description: String, priority: Priority) {
        val existingTask = repository.getTaskById(id) ?: return
        val updatedTask = existingTask.copy(title = title, description = description, priority = priority)
        repository.updateTask(updatedTask)
    }

    fun deleteTask(taskId: String) {
        repository.deleteTask(taskId)
    }

    fun toggleTaskStatus(taskId: String) {
        repository.toggleTaskStatus(taskId)
    }

    fun getTaskById(taskId: String): Task? {
        return repository.getTaskById(taskId)
    }
}
