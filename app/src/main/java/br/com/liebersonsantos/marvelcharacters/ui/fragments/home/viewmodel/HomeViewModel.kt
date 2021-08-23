package br.com.liebersonsantos.marvelcharacters.ui.fragments.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.liebersonsantos.marvelcharacters.core.State
import br.com.liebersonsantos.marvelcharacters.domain.model.CharactersResponse
import br.com.liebersonsantos.marvelcharacters.domain.model.Results
import br.com.liebersonsantos.marvelcharacters.domain.usecase.getcharacters.GetCharactersUseCase
import br.com.liebersonsantos.marvelcharacters.domain.usecase.usecasedb.InsertCrudDbUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by lieberson on 8/17/21.
 * @author lieberson.xsantos@gmail.com
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    @Named("io") private val ioDispatcher: CoroutineDispatcher,
    private val getCharactersUseCase: GetCharactersUseCase,
    private val insertCrudDbUseCase: InsertCrudDbUseCase
) : ViewModel() {

    private val _response = MutableLiveData<State<CharactersResponse>>()
    val response: LiveData<State<CharactersResponse>>
        get() = _response

    private val _insert = MutableLiveData<State<Boolean>>()
    val insert: LiveData<State<Boolean>>
        get() = _insert

    fun getCharacters(ts: Long, apiKey: String, hash: String) {
        viewModelScope.launch {
            try {
                _response.value = State.loading(true)
                val response = withContext(ioDispatcher){
                    getCharactersUseCase(ts, apiKey, hash)
                }

                _response.value = State.success(response)

            }catch (throwable: Throwable){
                _response.value = State.error(throwable)
            }
        }
    }

    fun insert(results: Results) {
        viewModelScope.launch {
            try {
                _insert.value = State.loading(true)
                withContext(ioDispatcher) {
                    insertCrudDbUseCase(results)
                }

                _insert.value = State.success(true)
            } catch (ex: Exception) {
                _insert.value = State.error(ex)
            }
        }
    }

}