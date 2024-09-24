package org.jkl4o4.expense.tracker.main.presentation

import androidx.compose.ui.graphics.vector.ImageVector
import org.jkl4o4.expense.tracker.navigation.presentation.Screen

data class MenuItem(
    val name: String,
    val icon: ImageVector,
    val screen: Screen
)