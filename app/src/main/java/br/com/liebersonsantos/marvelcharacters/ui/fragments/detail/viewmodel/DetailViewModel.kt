package br.com.liebersonsantos.marvelcharacters.ui.fragments.detail.viewmodel

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
 * Created by lieberson on 8/19/21.
 * @author lieberson.xsantos@gmail.com
 */
@HiltViewModel
class DetailViewModel @Inject constructor(
    @Named("io") private val ioDispatcher: CoroutineDispatcher,
    private val crudDbUseCase: CrudDbUseCase
) : ViewModel() {

    private val _insert = MutableLiveData<State<Boolean>>()
    val insert: LiveData<State<Boolean>>
        get() = _insert

    fun insert(results: Results) {
        viewModelScope.launch {
            try {
                _insert.value = State.loading(true)
                withContext(ioDispatcher) {
                    crudDbUseCase(results)
                }

                _insert.value = State.success(true)
            } catch (ex: Exception) {
                _insert.value = State.error(ex)
            }
        }
    }


}