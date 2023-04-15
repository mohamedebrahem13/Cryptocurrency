package com.example.cryptocurrency.presentation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrency.common.Resource
import com.example.cryptocurrency.domain.model.Coin
import com.example.cryptocurrency.domain.use_case.get_coins.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class CoinListViewModel  @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase
):ViewModel() {
    private val _state = MutableStateFlow (CoinListState())
    val state: StateFlow<CoinListState> = _state




    private val _navigateToSelectedCoin = MutableLiveData<Coin?>()
    val navigateToSelectedCoin: MutableLiveData<Coin?>
        get() = _navigateToSelectedCoin

    fun onCoinslected(coin: Coin){
        //it  is the  clicked coin  and i set it with live data to observe and navigate to detail fragment
        _navigateToSelectedCoin.value = coin
    }

    fun navigated() {
        //clean the live data after navigate
        _navigateToSelectedCoin.value = null
    }

     fun getCoins() {
        getCoinsUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = CoinListState(coins = result.data ?: emptyList())


                }
                is Resource.Error -> {
                    _state.value = CoinListState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = CoinListState(isLoading = true)

                }
            }
        }.launchIn(viewModelScope)
    }
}