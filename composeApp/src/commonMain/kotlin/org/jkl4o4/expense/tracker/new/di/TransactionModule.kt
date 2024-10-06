package org.jkl4o4.expense.tracker.new.di

import org.jkl4o4.expense.tracker.new.data.ExpressionEvaluation
import org.jkl4o4.expense.tracker.new.presentation.KeyboardHandler
import org.jkl4o4.expense.tracker.new.presentation.TransactionVideModel
import org.jkl4o4.expense.tracker.viewModelDefinition
import org.koin.compose.viewmodel.dsl.viewModel
import org.koin.dsl.module

val transactionModule = module {

    single<KeyboardHandler> { KeyboardHandler.Base() }
    single<ExpressionEvaluation> { ExpressionEvaluation.Base() }

    viewModelDefinition { TransactionVideModel(get(), get()) }
}