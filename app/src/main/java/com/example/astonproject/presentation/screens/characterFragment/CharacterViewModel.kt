package com.example.astonproject.presentation.screens.characterFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.astonproject.data.pagingSource.CharacterPagingSource
import com.example.astonproject.domain.model.character.CharacterResult
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class CharacterViewModel : ViewModel() {

    val characterFlow: StateFlow<PagingData<CharacterResult>> = Pager(PagingConfig(pageSize = 1)) {
        CharacterPagingSource()
    }.flow.cachedIn(viewModelScope).stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())
}