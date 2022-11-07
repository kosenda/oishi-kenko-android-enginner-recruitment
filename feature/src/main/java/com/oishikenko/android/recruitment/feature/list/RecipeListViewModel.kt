package com.oishikenko.android.recruitment.feature.list

import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.oishikenko.android.recruitment.data.model.CookingRecord
import com.oishikenko.android.recruitment.data.repository.CookingRecordsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class RecipeListViewModel @Inject constructor(
    private var cookingRecordsRepository: CookingRecordsRepository
) : ViewModel() {

    val recordPager = Pager(PagingConfig(pageSize = 100)) {
        RecordPagingSource(cookingRecordsRepository)
    }.flow.cachedIn(viewModelScope)

}