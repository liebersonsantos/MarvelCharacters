package br.com.liebersonsantos.marvelcharacters.ui.fragments.favorites.viewmodel

import androidx.lifecycle.ViewModel
import br.com.liebersonsantos.marvelcharacters.domain.usecase.usecasedb.CrudDbUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
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

    fun getCharacters() = crudDbUseCase.invoke()

}