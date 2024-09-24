package org.jkl4o4.expense.tracker.navigation.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.jkl4o4.expense.tracker.history.presentation.HistoryScreen
import org.jkl4o4.expense.tracker.home.presentation.HomeScreen
import org.jkl4o4.expense.tracker.new.presentation.TransactionScreen

@Composable
fun NavigationHost(modifier: Modifier, navController: NavHostController) {

    NavHost(
        modifier = modifier,
        navController = navController, startDestination = Screen.Home
    ) {
        composable<Screen.Home> {
            HomeScreen()
        }
        composable<Screen.New> {
            TransactionScreen()
        }
        composable<Screen.History> {
            HistoryScreen()
        }
    }
}