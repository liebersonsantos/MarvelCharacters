package br.com.liebersonsantos.marvelcharacters.domain.usecase.usecasedb

import br.com.liebersonsantos.marvelcharacters.data.db.repository.DbRepository
import br.com.liebersonsantos.marvelcharacters.domain.model.Results
import javax.inject.Inject

/**
 * Created by lieberson on 8/22/21.
 * @author lieberson.xsantos@gmail.com
 */
class InsertCrudDbUseCaseImpl @Inject constructor(private val dbRepository: DbRepository): InsertCrudDbUseCase {
    override suspend fun invoke(results: Results) = dbRepository.insertCharacter(results)
}