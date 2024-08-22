package com.example.moviesbaseexample.ui.theme.ui.home

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.moviesbaseexample.ui.theme.domain.model.Genre
import com.example.moviesbaseexample.ui.theme.domain.model.Movie
import com.example.moviesbaseexample.ui.theme.domain.usecases.home.HomeUseCases
import com.example.moviesbaseexample.ui.theme.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class HomeParentViewModel @Inject constructor(

    private val homeUseCases : HomeUseCases

):BaseViewModel<HomeParentEvent,HomeParentState,HomeParentEffect,Nothing>() {


    override fun setInitialState() : HomeParentState {

        return HomeParentState()
    }

    override fun handleEvents(event : HomeParentEvent) {

        when (event) {

            is HomeParentEvent.TabClicked -> setEffect {

                HomeParentEffect.ChangePage(event.id)
            }
        }
    }
    fun getGenres(){

        viewModelScope.launch {

            setState { copy(isLoading=true) }

            homeUseCases.getGenreUseCases().onEach {repoResult ->

            repoResult.onSuccess {

                setState { copy(genres=it) }

            }
                repoResult.onResponed {

                    setState { copy(isLoading=false) }
                }
            }.launchIn(viewModelScope)
        }
    }
}
sealed interface HomeParentEvent {
    data class TabClicked(val id: Int?) : HomeParentEvent

}

sealed interface HomeParentEffect {
    data class ChangePage(val id: Int?) : HomeParentEffect
}

data class HomeParentState(

    val genres: List<Genre> = emptyList(),

    val isLoading: Boolean=false,
)