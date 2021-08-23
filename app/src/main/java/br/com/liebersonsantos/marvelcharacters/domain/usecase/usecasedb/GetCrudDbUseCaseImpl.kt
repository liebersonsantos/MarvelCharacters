package br.com.liebersonsantos.marvelcharacters.domain.usecase.usecasedb

import androidx.lifecycle.LiveData
import br.com.liebersonsantos.marvelcharacters.data.db.repository.DbRepository
import br.com.liebersonsantos.marvelcharacters.domain.model.Results
import javax.inject.Inject

/**
 * Created by lieberson on 8/23/21.
 * @author lieberson.xsantos@gmail.com
 */
class GetCrudDbUseCaseImpl @Inject constructor(private val dbRepository: DbRepository): GetCrudDbUseCase {
    override fun invoke(): LiveData<List<Results>> = dbRepository.getAllCharacters()
}