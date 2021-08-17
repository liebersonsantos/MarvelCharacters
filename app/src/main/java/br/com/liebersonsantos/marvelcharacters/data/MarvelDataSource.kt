package br.com.liebersonsantos.marvelcharacters.data

/**
 * Created by lieberson on 8/16/21.
 * @author lieberson.xsantos@gmail.com
 */
interface MarvelDataSource {
    suspend fun getCharacters()
}