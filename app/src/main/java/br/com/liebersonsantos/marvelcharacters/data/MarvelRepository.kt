package br.com.liebersonsantos.marvelcharacters.data

import br.com.liebersonsantos.marvelcharacters.domain.model.CharactersResponse
import javax.inject.Inject

/**
 * Created by lieberson on 8/16/21.
 * @author lieberson.xsantos@gmail.com
 */
class MarvelRepository @Inject constructor(private val api: Api) {
    suspend fun getCharacters(ts: Long, apiKey: String, hash: String): CharactersResponse =
        api.getCharacters(ts, apiKey, hash)
}