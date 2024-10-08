package org.jkl4o4.expense.tracker

import androidx.compose.ui.window.ComposeUIViewController
import androidx.lifecycle.ViewModel
import org.jkl4o4.expense.tracker.main.presentation.App
import org.koin.core.definition.Definition
import org.koin.core.definition.KoinDefinition
import org.koin.core.module.Module
import org.koin.core.qualifier.Qualifier

fun MainViewController() = ComposeUIViewController { App() }

actual inline fun <reified T : ViewModel> Module.viewModelDefinition(
    qualifier: Qualifier?,
    noinline definition: Definition<T>,
): KoinDefinition<T> = factory(qualifier = qualifier, definition = definition)