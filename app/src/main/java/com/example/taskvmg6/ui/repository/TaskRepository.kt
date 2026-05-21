package com.example.taskvmg6.ui.repository

import com.example.taskvmg6.ui.model.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class TaskRepository {
    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks: StateFlow<List<Task>> = _tasks.asStateFlow()

    private var nextId = 1

    fun addTask(title: String, description: String, priority: com.example.taskvmg6.ui.model.Priority) {
        val newTask = Task(id = nextId++, title = title, description = description, priority = priority)
        _tasks.update { it + newTask }
    }

    fun updateTask(updatedTask: Task) {
        _tasks.update { list ->
            list.map { if (it.id == updatedTask.id) updatedTask else it }
        }
    }

    fun deleteTask(taskId: Int) {
        _tasks.update { list ->
            list.filter { it.id != taskId }
        }
    }

    fun toggleTaskCompletion(taskId: Int) {
        _tasks.update { list ->
            list.map { if (it.id == taskId) it.copy(isCompleted = !it.isCompleted) else it }
        }
    }
    
    fun getTaskById(id: Int): Task? {
        return _tasks.value.find { it.id == id }
    }
}
