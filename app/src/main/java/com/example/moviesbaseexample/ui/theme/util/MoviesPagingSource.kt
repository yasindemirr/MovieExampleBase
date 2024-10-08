package com.example.movieapp.common.base

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.moviesbaseexample.ui.theme.util.dataresource.RepoResult
import kotlinx.coroutines.flow.Flow

/**
 * MoviesPagingSource ve ilgili fonksiyonlar, Jetpack Paging kütüphanesi kullanarak
 * API'den sayfalama işlemlerini yönetir.
 *
 * MoviesPagingSource:
 * - loadDataFromApi: API'den veri yüklemek için kullanılan lambda fonksiyonu.
 * - load: Mevcut sayfa ve boyut bilgisiyle API çağrısı yapar, LoadResult döner.
 * - getRefreshKey: Sayfalama yenileme anahtarını belirler.
 * - STARTING_PAGE_INDEX: Başlangıç sayfa indeksini tanımlar.
 * - LIMIT: Sayfa başına öğe sayısını tanımlar.
 *
 * safeApiCallPaging:
 * - API çağrılarını güvenli bir şekilde yaparak PagingData akışı döner.
 *
 * setPager:
 * - Paging yapılandırmasını sağlar ve PagingSource kullanarak Pager döner.
 */
class MoviesPagingSource<Value : Any>(

    val loadDataFromApi: suspend (page: Int, pageSize: Int) -> List<Value>?,

) : PagingSource<Int, Value>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Value> {

        val currentPage = params.key ?: STARTING_PAGE_INDEX

        return try {

            val data = loadDataFromApi(currentPage, params.loadSize)

            LoadResult.Page(

                data = data.orEmpty(),

                prevKey = if (currentPage == STARTING_PAGE_INDEX) null else currentPage - 1,

                nextKey = if (data.isNullOrEmpty()) null else currentPage + 1
            )

        } catch (e: Exception) {

            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Value>): Int? {

        return state.anchorPosition?.let { anchorPosition ->

            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)

                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    companion object {

        const val STARTING_PAGE_INDEX = 1

        const val LIMIT = 30
    }
}

fun <T : Any> safeApiCallPaging(

    loadDataFromApi: suspend (page: Int, pageSize: Int) -> RepoResult<List<T>>

): Flow<PagingData<T>> {

    return setPager(

        pagingSourceFactory = {

            MoviesPagingSource { page, pageSize ->

                when (val result = loadDataFromApi(page, pageSize)) {

                    is RepoResult.Success -> result.data

                    is RepoResult.Error -> throw result.error

                    RepoResult.None -> emptyList()
                }
            }
        }
    ).flow
}

fun <Value : Any> setPager(

    pageSize: Int = MoviesPagingSource.LIMIT,

    initialLoadSize: Int = MoviesPagingSource.LIMIT,

    enablePlaceholders: Boolean = false,

    pagingSourceFactory: () -> MoviesPagingSource<Value>,

): Pager<Int, Value> {

    return Pager(

        config = PagingConfig(
            pageSize = pageSize,
            initialLoadSize = initialLoadSize,
            prefetchDistance = pageSize/2,
            enablePlaceholders = enablePlaceholders,
        ),
        pagingSourceFactory = pagingSourceFactory
    )
}