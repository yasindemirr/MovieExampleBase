package com.example.moviesbaseexample.ui.theme.data.repository.Home

import androidx.paging.PagingData
import com.example.movieapp.common.base.safeApiCallPaging
import com.example.moviesbaseexample.ui.theme.data.model.toGenre
import com.example.moviesbaseexample.ui.theme.data.model.toMovie
import com.example.moviesbaseexample.ui.theme.data.remote.Services
import com.example.moviesbaseexample.ui.theme.data.repository.base.BaseRepository
import com.example.moviesbaseexample.ui.theme.domain.model.Genre
import com.example.moviesbaseexample.ui.theme.domain.model.Movie
import com.example.moviesbaseexample.ui.theme.domain.repository.Repository
import com.example.moviesbaseexample.ui.theme.util.dataresource.RepoResult
import com.example.moviesbaseexample.ui.theme.util.dataresource.RepoResult.None.transformData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(

 private val services : Services.Home
):BaseRepository(),Repository.HomeRepository {
 override suspend fun getMovies() : Flow<PagingData<Movie>>{

  return safeApiCallPaging { page, pageSize ->

    safeCall { services.getMovies(page=page) } .transformData {

     it.results.toMovie() }
   }
   }
 override suspend fun getGenres() : Flow<RepoResult<List<Genre>>>{

  return flow {

   safeCall { services.getGenres() } .transformData {

    it.genres.toGenre()}.also {

     emit(it)

   }
  }
 }
}


