package com.example.moviesbaseexample.ui.theme.domain.repository

import androidx.paging.PagingData
import com.example.moviesbaseexample.ui.theme.data.model.CreateSession
import com.example.moviesbaseexample.ui.theme.domain.model.Genre
import com.example.moviesbaseexample.ui.theme.domain.model.Movie
import com.example.moviesbaseexample.ui.theme.domain.model.SessionId
import com.example.moviesbaseexample.ui.theme.domain.model.TokenResponse
import com.example.moviesbaseexample.ui.theme.util.dataresource.RepoResult
import kotlinx.coroutines.flow.Flow

sealed interface Repository{
    interface AuthRepository{
        suspend fun getAuth(): Flow<RepoResult<TokenResponse>>
        suspend fun getId(model: CreateSession?):Flow<RepoResult<TokenResponse>>
        suspend fun getSessionId(token: String?):Flow<RepoResult<SessionId>>
    }
    interface HomeRepository{
        suspend fun getMovies():Flow<PagingData<Movie>>
        suspend fun getGenres():Flow<RepoResult<List<Genre>>>
    }
}