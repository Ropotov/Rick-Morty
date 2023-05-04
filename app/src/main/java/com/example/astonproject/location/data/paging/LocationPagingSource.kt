package com.example.astonproject.location.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.astonproject.location.domain.model.LocationResult
import com.example.astonproject.location.domain.repository.LocationRepository
import retrofit2.HttpException
import javax.inject.Inject

class LocationPagingSource @Inject constructor(
    private val repository: LocationRepository,
    private val name: String,
    private val type: String,
    private val dimension: String,
) : PagingSource<Int, LocationResult>() {
    override fun getRefreshKey(state: PagingState<Int, LocationResult>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, LocationResult> {
        return try {
            val page = params.key ?: 1
            val resultDate = arrayListOf<LocationResult>()
            resultDate.addAll((repository.getLocation(page, name, type, dimension)).results)

            LoadResult.Page(
                data = resultDate,
                prevKey = if (page == 1) null else -1,
                nextKey = page.plus(1)
            )
        } catch (e: java.lang.Exception) {
            LoadResult.Error(e)
        } catch (e: HttpException){
           LoadResult.Error(e)
        }
    }
}