package br.com.liebersonsantos.marvelcharacters.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.liebersonsantos.marvelcharacters.core.State
import br.com.liebersonsantos.marvelcharacters.domain.model.CharactersResponse
import br.com.liebersonsantos.marvelcharacters.domain.usecase.getcharacters.GetCharactersUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by lieberson on 8/17/21.
 * @author lieberson.xsantos@gmail.com
 */
class CharactersViewModel @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher,
    private val getCharactersUseCase: GetCharactersUseCase
) : ViewModel() {

    private val _response = MutableLiveData<State<CharactersResponse>>()
    val response: LiveData<State<CharactersResponse>>
        get() = _response

    fun getCharacters(ts: Long, apiKey: String, hash: String) {
        viewModelScope.launch {
            try {
                _response.value = State.loading(true)
                val response = withContext(ioDispatcher){
                    getCharactersUseCase(ts, apiKey, hash)
                }

                _response.value = State.success(response)

            }catch (throwable: Throwable){
                _response.value = State.loading(false)
                _response.value = State.error(throwable)
            }
        }
    }

}