package br.com.liebersonsantos.marvelcharacters.domain.usecase.usecasedb

import br.com.liebersonsantos.marvelcharacters.data.db.repository.DbRepository
import br.com.liebersonsantos.marvelcharacters.domain.model.Results
import javax.inject.Inject

/**
 * Created by lieberson on 8/23/21.
 * @author lieberson.xsantos@gmail.com
 */
class DeleteCrudDbUseCaseImpl @Inject constructor(private val dbRepository: DbRepository): DeleteCrudDbUseCase {
    override suspend fun deleteCharactersDb(results: Results) = dbRepository.deleteCharacter(results)
}