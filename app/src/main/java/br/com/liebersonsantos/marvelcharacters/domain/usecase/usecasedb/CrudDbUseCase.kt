package br.com.liebersonsantos.marvelcharacters.domain.usecase.usecasedb

import androidx.lifecycle.LiveData
import br.com.liebersonsantos.marvelcharacters.domain.model.Results

/**
 * Created by lieberson on 8/19/21.
 * @author lieberson.xsantos@gmail.com
 */
interface CrudDbUseCase {
    suspend operator fun invoke(results: Results)
    operator fun invoke(): LiveData<List<Results>>
    suspend fun deleteCharactersDb(results: Results)
}