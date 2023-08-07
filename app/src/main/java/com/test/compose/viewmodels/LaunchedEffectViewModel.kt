package com.test.compose.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class LaunchedEffectViewModel : ViewModel() {

    private val _sharedFlow = MutableSharedFlow<ScreenEvents>()
    val sharedFlow: SharedFlow<ScreenEvents> = _sharedFlow.asSharedFlow()

    init {
        toast()
    }

    fun toast() {
        viewModelScope.launch {
            _sharedFlow.emit(ScreenEvents.ShowSnackBar("Something from view model"))
        }
    }

    sealed class ScreenEvents {

        data class Navigate(val route: String) : ScreenEvents()

        data class ShowSnackBar(val message: String) : ScreenEvents()
    }
}