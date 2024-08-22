package com.example.moviesbaseexample.ui.theme.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<Event, State, Effect,W> : ViewModel() {

    private val defaultState: State by lazy { setInitialState() }

    private val _state: MutableStateFlow<State> = MutableStateFlow(defaultState)
    val state: StateFlow<State> = _state.asStateFlow()

    private val _event: MutableSharedFlow<Event> = MutableSharedFlow()
    val event: SharedFlow<Event> = _event.asSharedFlow()

    private val _effect: MutableSharedFlow<Effect> = MutableSharedFlow()
    val effect: SharedFlow<Effect> = _effect.asSharedFlow()

    protected val _inputErrorChannel = Channel<W>()
    val inputErrorChannel : Flow<W>
        get() = _inputErrorChannel.receiveAsFlow()

    init {
        subscribeToEvents()
    }

    abstract fun setInitialState(): State

    fun setEvent(event: Event) {
        viewModelScope.launch { _event.emit(event) }
    }

    fun setState(reducer: State.() -> State) {
        val newState = state.value.reducer()
        _state.value = newState
    }

    fun setEffect(builder: () -> Effect) {
        val effectValue = builder()
        viewModelScope.launch { _effect.emit(effectValue) }
    }

    private fun subscribeToEvents() {
        viewModelScope.launch {
            _event.collect {
                handleEvents(it)
            }
        }
    }

    abstract fun handleEvents(event: Event)
}