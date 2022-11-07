package com.oishikenko.android.recruitment.feature.list

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.oishikenko.android.recruitment.data.model.CookingRecord
import com.oishikenko.android.recruitment.data.repository.CookingRecordsRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RecordPagingSource @Inject constructor(
    private var cookingRecordsRepository: CookingRecordsRepository
) : PagingSource<Int, CookingRecord>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CookingRecord> {
        val pageNum = params.key ?: 0
        val response = cookingRecordsRepository.getCookingRecords(
            offset = pageNum * 10, 10)
        var cookingRecords: List<CookingRecord> = emptyList()
        response.map {
            it.body()?.cookingRecords ?: emptyList<CookingRecord>()
        }.collect{ new -> cookingRecords = new }
        return LoadResult.Page(
            data = cookingRecords,
            prevKey = null,
            nextKey = if(cookingRecords.isNotEmpty()) pageNum + 1 else null
        )
    }
    override fun getRefreshKey(state: PagingState<Int, CookingRecord>): Int? {
        return state.anchorPosition
    }
}