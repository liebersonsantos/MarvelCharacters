package br.com.liebersonsantos.marvelcharacters.data.repository

import br.com.liebersonsantos.marvelcharacters.data.Api
import br.com.liebersonsantos.marvelcharacters.domain.model.CharactersResponse
import javax.inject.Inject

/**
 * Created by lieberson on 8/18/21.
 * @author lieberson.xsantos@gmail.com
 */
class MarvelRepositoryImpl @Inject constructor (private val api: Api): MarvelRepository {
    override suspend fun getCharacters(ts: Long, apiKey: String, hash: String): CharactersResponse =
        api.getCharacters(ts, apiKey, hash)
}