package co.devine_solutions.runtimer.android

import co.devine_solutions.runtimer.android.ui.screens.MainActivityVM
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModules = module {
    viewModel { MainActivityVM() }
}