package com.metinozcura.rickandmorty.ui.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.metinozcura.rickandmorty.base.BaseViewModel
import com.metinozcura.rickandmorty.data.model.Character
import com.metinozcura.rickandmorty.data.repository.character.CharacterRepository
import com.metinozcura.rickandmorty.util.NetworkUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val characterRepository: CharacterRepository,
    private val networkUtils: NetworkUtils
) : BaseViewModel() {
    private lateinit var _charactersFlow: Flow<PagingData<Character>>
    val charactersFlow: Flow<PagingData<Character>>
        get() = _charactersFlow


    private val _isConnected = MutableLiveData<Boolean>()
    val isConnected: LiveData<Boolean> get() = _isConnected


    init {
        checkNetworkConnection()
        getAllCharacters()
    }
    fun checkNetworkConnection() {
        _isConnected.value = networkUtils.isConnected()
    }
    private fun getAllCharacters() = launchPagingAsync({
        characterRepository.getAllCharacters().cachedIn(viewModelScope)
    }, {
        _charactersFlow = it
    })
}