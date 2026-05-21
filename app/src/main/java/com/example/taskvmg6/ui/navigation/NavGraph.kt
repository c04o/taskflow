package com.example.taskvmg6.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.taskvmg6.ui.screen.SplashScreen
import com.example.taskvmg6.ui.screen.TaskListScreen
import com.example.taskvmg6.ui.screen.TaskEntryScreen
import com.example.taskvmg6.ui.viewmodel.TaskViewModel
import kotlinx.serialization.Serializable

@Serializable
object Splash

@Serializable
object TaskList

@Serializable
data class TaskEntry(val taskId: Int? = null)

@Composable
fun TaskNavHost(
    navController: NavHostController,
    viewModel: TaskViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Splash
    ) {
        composable<Splash> {
            SplashScreen(
                onSplashFinished = {
                    navController.navigate(TaskList) {
                        popUpTo(Splash) { inclusive = true }
                    }
                }
            )
        }
        composable<TaskList> {
            TaskListScreen(
                viewModel = viewModel,
                onAddTask = { navController.navigate(TaskEntry()) },
                onEditTask = { taskId -> navController.navigate(TaskEntry(taskId)) }
            )
        }
        composable<TaskEntry> {
            TaskEntryScreen(
                viewModel = viewModel,
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}
