package com.yannickpulver.wonderfluff.ui.list

import com.yannickpulver.wonderfluff.domain.Puppy
import com.yannickpulver.wonderfluff.domain.PuppyRepository
import com.yannickpulver.wonderfluff.ui.core.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PuppyListViewModel @Inject constructor(val repo: PuppyRepository) : BaseViewModel() {

    private val _state = MutableStateFlow<List<Puppy>>(listOf())
    val state: StateFlow<List<Puppy>> = _state

    init {
        ioScope.launch { _state.emit(repo.getPuppies().shuffled()) }
    }

}