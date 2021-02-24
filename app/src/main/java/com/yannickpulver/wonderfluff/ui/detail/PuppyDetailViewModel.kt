package com.yannickpulver.wonderfluff.ui.detail

import com.yannickpulver.wonderfluff.domain.Puppy
import com.yannickpulver.wonderfluff.domain.PuppyRepository
import com.yannickpulver.wonderfluff.ui.core.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PuppyDetailViewModel @Inject constructor(val repo: PuppyRepository) : BaseViewModel() {

    private val _state = MutableStateFlow<Puppy?>(null)
    val state: StateFlow<Puppy?> = _state

    private val _adopted = MutableStateFlow(false)
    val adopted: StateFlow<Boolean> = _adopted

    private val _reset = MutableStateFlow(false)
    val reset: StateFlow<Boolean> = _reset

    fun getPuppy(id: Int) {
        ioScope.launch { _state.emit(repo.getPuppy(id)) }
    }

    fun adopt(id: Int) {
        ioScope.launch { _adopted.emit(true) }
        // TODO: Adoption in parallel universe
    }

    fun reset() {
        ioScope.launch { _adopted.emit(false) }
        ioScope.launch { _reset.emit(true) }
    }

}