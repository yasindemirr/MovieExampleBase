package com.example.moviesbaseexample.ui.theme.ui.home

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import androidx.paging.map
import com.example.moviesbaseexample.ui.theme.domain.model.Genre
import com.example.moviesbaseexample.ui.theme.domain.model.Movie
import com.example.moviesbaseexample.ui.theme.domain.usecases.home.HomeUseCases
import com.example.moviesbaseexample.ui.theme.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class HomeViewModel @Inject constructor(

    private val homeUseCases : HomeUseCases

):BaseViewModel<HomeEvent,HomeState,HomeEffect,Nothing>() {


    init {
        getMovies()
    }
    override fun setInitialState() : HomeState {

        return HomeState()
    }

    override fun handleEvents(event : HomeEvent) {

        when (event) {

            is HomeEvent.MovieClicked -> setEffect {

                HomeEffect.OpenBottomSheet(event.model)
            }
        }
    }
    fun getMovies() {

        viewModelScope.launch {

            setState { copy(isLoading = true) }

            homeUseCases.getMovieUseCases()

            .cachedIn(viewModelScope)

            .collectLatest { pagingData ->

                    setState { copy(movies = pagingData, isLoading = false) }
            }
        }
    }
    fun filteredList(genreId:Int?){

        viewModelScope.launch {

    setState { copy(filteredMovies= state.value.movies.filter {

        it.genreIds?.contains(genreId)!!

    }) }
        }
    }
}
sealed interface HomeEvent {
    data class MovieClicked(val model: Movie) : HomeEvent

}

sealed interface HomeEffect {
    data class OpenBottomSheet(val model: Movie) : HomeEffect
}

data class HomeState(

    val movies: PagingData<Movie> = PagingData.empty(),

    val filteredMovies: PagingData<Movie> = PagingData.empty(),

    val isLoading: Boolean=false,
)