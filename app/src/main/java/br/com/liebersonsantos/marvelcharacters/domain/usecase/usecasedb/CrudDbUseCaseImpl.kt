package br.com.liebersonsantos.marvelcharacters.domain.usecase.usecasedb

import androidx.lifecycle.LiveData
import br.com.liebersonsantos.marvelcharacters.data.db.repository.DbRepository
import br.com.liebersonsantos.marvelcharacters.domain.model.Results
import javax.inject.Inject

/**
 * Created by lieberson on 8/19/21.
 * @author lieberson.xsantos@gmail.com
 */
class CrudDbUseCaseImpl @Inject constructor (private val dbRepository: DbRepository): CrudDbUseCase {
    override suspend fun invoke(results: Results) = dbRepository.insertCharacter(results)
    override fun invoke(): LiveData<List<Results>> = dbRepository.getAllCharacters()
    override suspend fun deleteCharactersDb(results: Results) = dbRepository.deleteCharacter(results)
}