package br.com.liebersonsantos.marvelcharacters.ui.fragments.favorites.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.liebersonsantos.marvelcharacters.core.State
import br.com.liebersonsantos.marvelcharacters.domain.model.Results
import br.com.liebersonsantos.marvelcharacters.domain.usecase.usecasedb.CrudDbUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by lieberson on 8/21/21.
 * @author lieberson.xsantos@gmail.com
 */
@HiltViewModel
class FavoritesViewModel @Inject constructor(
    @Named("io") private val ioDispatcher: CoroutineDispatcher,
    private val crudDbUseCase: CrudDbUseCase
) : ViewModel() {

    private val _delete = MutableLiveData<State<Boolean>>()
    val delete: LiveData<State<Boolean>>
        get() = _delete

    fun getCharacters() = crudDbUseCase.invoke()

    fun deleteCharacters(results: Results) = viewModelScope.launch {
        try {
            _delete.value = State.loading(true)
            withContext(ioDispatcher) {
                crudDbUseCase.deleteCharactersDb(results)
            }

            _delete.value = State.success(true)
        } catch (ex: Exception) {
            _delete.value = State.error(ex)
        }

    }

}