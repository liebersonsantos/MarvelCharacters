package br.com.liebersonsantos.marvelcharacters.data.db.repository

import androidx.lifecycle.LiveData
import br.com.liebersonsantos.marvelcharacters.domain.model.Results

/**
 * Created by lieberson on 8/19/21.
 * @author lieberson.xsantos@gmail.com
 */
interface DbRepository {
    suspend fun insertCharacter(results: Results)
    fun getAllCharacters(): LiveData<List<Results>>
    suspend fun deleteCharacter(results: Results)
}