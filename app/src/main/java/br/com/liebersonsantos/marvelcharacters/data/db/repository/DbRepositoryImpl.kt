package br.com.liebersonsantos.marvelcharacters.data.db.repository

import androidx.lifecycle.LiveData
import br.com.liebersonsantos.marvelcharacters.data.db.CharactersDao
import br.com.liebersonsantos.marvelcharacters.domain.model.Results
import javax.inject.Inject

/**
 * Created by lieberson on 8/19/21.
 * @author lieberson.xsantos@gmail.com
 */
class DbRepositoryImpl @Inject constructor(private val charactersDao: CharactersDao): DbRepository {

    override suspend fun insertCharacter(results: Results) = charactersDao.insertCharacter(results)
    override fun getAllCharacters(): LiveData<List<Results>> = charactersDao.getAllCharacters()
    override suspend fun deleteCharacter(results: Results) = charactersDao.deleteCharacter(results)

}