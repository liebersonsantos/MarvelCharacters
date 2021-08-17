package br.com.liebersonsantos.marvelcharacters.data

import br.com.liebersonsantos.marvelcharacters.domain.model.CharactersResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by lieberson on 8/17/21.
 * @author lieberson.xsantos@gmail.com
 */
interface Api {

    @GET("characters")
    suspend fun getCharacters(
        @Query("ts")ts: Long,
        @Query("apikey")apiKey: String,
        @Query("hash")hash: String
    ): CharactersResponse

}