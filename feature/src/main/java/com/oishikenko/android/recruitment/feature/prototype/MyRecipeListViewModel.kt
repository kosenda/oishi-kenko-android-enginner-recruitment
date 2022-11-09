package com.oishikenko.android.recruitment.feature.prototype

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.oishikenko.android.recruitment.data.repository.CookingRecordsRepository
import com.oishikenko.android.recruitment.feature.list.RecordPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MyRecipeListViewModel @Inject constructor(
    private var cookingRecordsRepository: CookingRecordsRepository
) : ViewModel() {

    val recordPager = Pager(PagingConfig(pageSize = 100)) {
        RecordPagingSource(cookingRecordsRepository)
    }.flow.cachedIn(viewModelScope)

}