package org.jkl4o4.expense.tracker.navigation.presentation

import kotlinx.serialization.Serializable

interface Screen {
    @Serializable
    object Home: Screen
    @Serializable
    object History: Screen
    @Serializable
    object New: Screen
}