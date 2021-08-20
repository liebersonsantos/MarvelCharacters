package br.com.liebersonsantos.marvelcharacters.data.repository

import br.com.liebersonsantos.marvelcharacters.domain.model.CharactersResponse

/**
 * Created by lieberson on 8/16/21.
 * @author lieberson.xsantos@gmail.com
 */
interface MarvelRepository {
    suspend fun getCharacters(ts: Long, apiKey: String, hash: String): CharactersResponse
}