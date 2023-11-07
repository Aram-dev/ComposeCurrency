package com.example.features.currencies.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.CurrencyRates
import com.example.domain.entity.Favorite
import com.example.domain.util.ViewState
import com.example.features.currencies.data.CurrenciesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

@HiltViewModel
class CurrenciesViewModel @Inject constructor(
    private val currenciesRepository: CurrenciesRepository,
) : ViewModel() {
    private val _currencies: MutableStateFlow<CurrencyRates> =
        MutableStateFlow(
            CurrencyRates("USD", emptyMap(), 0)
        )
    val currencies: StateFlow<CurrencyRates>
        get() = _currencies

    fun getCurrencies(base: String) {
        viewModelScope.launch {
            currenciesRepository.currencies(base).collectLatest {
                when(it) {
                    is ViewState.Idle -> {}
                    is ViewState.Loading -> {}
                    is ViewState.Success -> _currencies.emit(it.data)
                    is ViewState.Failure -> {}
                }
            }
        }
    }

    fun addToFavorites(favorite: Favorite) {
        viewModelScope.launch {
            currenciesRepository.addToFavorites(favorite)
        }
    }

    fun removeFromFavorites(favorite: Favorite) {
        viewModelScope.launch {
            currenciesRepository.removeFromFavorites(favorite)
        }
    }

    fun isFavorite(from: String, to: String): Boolean = currenciesRepository.isFavorite(from, to)
}