package com.example.moviesbaseexample.ui.theme.domain.usecases.home

import androidx.paging.PagingData
import com.example.moviesbaseexample.ui.theme.domain.model.Movie
import com.example.moviesbaseexample.ui.theme.domain.repository.Repository
import com.example.moviesbaseexample.ui.theme.util.dataresource.RepoResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieUseCase @Inject constructor(
    private val repository : Repository.HomeRepository
) {
     suspend operator fun  invoke() : Flow<PagingData<Movie>>{

        return repository.getMovies()
    }

}