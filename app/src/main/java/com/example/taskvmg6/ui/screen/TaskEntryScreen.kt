package com.example.taskvmg6.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.taskvmg6.ui.model.Priority
import com.example.taskvmg6.ui.navigation.TaskEntry
import com.example.taskvmg6.ui.viewmodel.TaskViewModel
import androidx.navigation.toRoute

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskEntryScreen(
    viewModel: TaskViewModel,
    onNavigateBack: () -> Unit
) {
    // In a real app with proper DI, we'd get the args from the savedStateHandle in ViewModel
    // For simplicity here, we'll extract it from the route in the UI if needed
    // But better yet, let's just use the current route info
    
    // This is a bit tricky with the new type-safe navigation if not using a shared ViewModel or StateHandle
    // Let's assume for this simple example we just handle "New Task" if taskId is null
    
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var priority by remember { mutableStateOf(Priority.MEDIUM) }
    
    // If we had a taskId, we would load the task here. 
    // For this prototype, let's focus on adding.

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Add Task") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Title") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Description") },
                modifier = Modifier.fillMaxWidth()
            )
            
            Text("Priority")
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Priority.values().forEach { p ->
                    FilterChip(
                        selected = priority == p,
                        onClick = { priority = p },
                        label = { Text(p.name) }
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    if (title.isNotBlank()) {
                        viewModel.addTask(title, description, priority)
                        onNavigateBack()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Save Task")
            }
        }
    }
}
