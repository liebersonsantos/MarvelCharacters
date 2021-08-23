package br.com.liebersonsantos.marvelcharacters.domain.usecase.usecasedb

import br.com.liebersonsantos.marvelcharacters.domain.model.Results

/**
 * Created by lieberson on 8/22/21.
 * @author lieberson.xsantos@gmail.com
 */
interface InsertCrudDbUseCase {
    suspend operator fun invoke(results: Results)
}