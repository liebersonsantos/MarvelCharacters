package br.com.liebersonsantos.marvelcharacters.domain.usecase.usecasedb

import br.com.liebersonsantos.marvelcharacters.domain.model.Results

/**
 * Created by lieberson on 8/23/21.
 * @author lieberson.xsantos@gmail.com
 */
interface DeleteCrudDbUseCase {
    suspend fun deleteCharactersDb(results: Results)
}