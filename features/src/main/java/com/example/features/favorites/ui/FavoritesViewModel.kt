package com.example.features.favorites.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.CurrencyRates
import com.example.domain.entity.Favorite
import com.example.domain.util.ViewState
import com.example.features.currencies.data.CurrenciesRepository
import com.example.features.currencies.data.FavoritesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val favoritesRepository: FavoritesRepository,
) : ViewModel() {
    private val _favorites: MutableStateFlow<List<Favorite>> = MutableStateFlow(emptyList())
    val favorites: StateFlow<List<Favorite>>
        get() = _favorites

    private val _favoriteItemRate: MutableStateFlow<CurrencyRates> =
        MutableStateFlow(
            CurrencyRates("USD", emptyMap(), 0)
        )
    val favoriteItemRate: StateFlow<CurrencyRates>
        get() = _favoriteItemRate

    fun getFavoriteItemRate(base: String, symbols: String) {
        viewModelScope.launch {
            favoritesRepository.favoriteItemRate(base, symbols).collectLatest {
                when(it) {
                    is ViewState.Idle -> {}
                    is ViewState.Loading -> {}
                    is ViewState.Success -> _favoriteItemRate.emit(it.data)
                    is ViewState.Failure -> {}
                }
            }
        }
    }

    fun getFavorites() {
        viewModelScope.launch {
            favoritesRepository.favorites().collectLatest {
                _favorites.tryEmit(it)
            }
        }
    }

    fun removeFromFavorites(favorite: Favorite) {
        viewModelScope.launch {
            favoritesRepository.removeFromFavorites(favorite)
        }
    }
}