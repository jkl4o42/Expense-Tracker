package org.jkl4o4.expense.tracker.main.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationDefaults
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jkl4o4.expense.tracker.navigation.presentation.NavigationHost
import org.jkl4o4.expense.tracker.navigation.presentation.Screen
import org.jkl4o4.expense.tracker.new.di.transactionModule
import org.koin.compose.KoinApplication

@Composable
@Preview
fun App() {
    KoinApplication(application = {
        modules(transactionModule)
    }) {
        MaterialTheme {

            var selectedItem by remember { mutableStateOf(0) }
            val navController = rememberNavController()
            val items = listOf(
                MenuItem("Home", Icons.Filled.Home, Screen.Home),
                MenuItem("New", Icons.Filled.Add, Screen.New),
                MenuItem("History", Icons.AutoMirrored.Filled.List, Screen.History)
            )

            Scaffold(
                modifier = Modifier.fillMaxSize(),
                bottomBar = {
                    BottomNavigation(
                        backgroundColor = Color.Black,
                        windowInsets = BottomNavigationDefaults.windowInsets,
                    ) {
                        items.forEachIndexed { index, item ->

                            val color = if (selectedItem == index) Color.White else Color.Gray

                            BottomNavigationItem(
                                icon = { Icon(item.icon, tint = color, contentDescription = null) },
                                label = { Text(item.name, color = color) },
                                selected = selectedItem == index,
                                onClick = {
                                    selectedItem = index
                                    navController.navigate(item.screen)
                                }
                            )
                        }
                    }
                }
            ) { innerPadding ->
                NavigationHost(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    navController = navController
                )
            }
        }
    }
}