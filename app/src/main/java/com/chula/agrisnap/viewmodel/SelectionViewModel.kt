package com.chula.agrisnap.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

enum class SelectionOption {
    BUY, SELL, NONE
}

class SelectionViewModel : ViewModel() {
    private val _selectedOption = MutableLiveData<SelectionOption>(SelectionOption.NONE)
    val selectedOption: LiveData<SelectionOption> get() = _selectedOption

    fun selectOption(option: SelectionOption) {
        _selectedOption.value = option
    }
}