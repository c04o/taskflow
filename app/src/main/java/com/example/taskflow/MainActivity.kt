package com.example.taskflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.taskflow.repository.TaskRepository
import com.example.taskflow.ui.SplashScreen
import com.example.taskflow.ui.TaskDetailScreen
import com.example.taskflow.ui.TaskListScreen
import com.example.taskflow.ui.theme.TaskFlowTheme
import com.example.taskflow.viewmodel.TaskViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        
        // In a real app, you would use a Service Locator or DI (Hilt)
        val repository = TaskRepository()
        
        setContent {
            TaskFlowTheme {
                val viewModel: TaskViewModel = viewModel(
                    factory = object : ViewModelProvider.Factory {
                        @Suppress("UNCHECKED_CAST")
                        override fun <T : ViewModel> create(modelClass: Class<T>): T {
                            return TaskViewModel(repository) as T
                        }
                    }
                )
                TaskFlowApp(viewModel)
            }
        }
    }
}

@Composable
fun TaskFlowApp(viewModel: TaskViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") {
            SplashScreen(onTimeout = {
                navController.navigate("taskList") {
                    popUpTo("splash") { inclusive = true }
                }
            })
        }
        composable("taskList") {
            TaskListScreen(
                viewModel = viewModel,
                onAddTask = { navController.navigate("taskDetail") },
                onEditTask = { taskId -> navController.navigate("taskDetail/$taskId") }
            )
        }
        // Route for adding a task
        composable("taskDetail") {
            TaskDetailScreen(
                viewModel = viewModel,
                taskId = null,
                onBack = { navController.popBackStack() }
            )
        }
        // Route for editing a task
        composable(
            route = "taskDetail/{taskId}",
            arguments = listOf(navArgument("taskId") { type = NavType.StringType })
        ) { backStackEntry ->
            val taskId = backStackEntry.arguments?.getString("taskId")
            TaskDetailScreen(
                viewModel = viewModel,
                taskId = taskId,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
