package br.com.liebersonsantos.marvelcharacters.domain.usecase.getcharacters

import br.com.liebersonsantos.marvelcharacters.domain.model.CharactersResponse

/**
 * Created by lieberson on 8/17/21.
 * @author lieberson.xsantos@gmail.com
 */
interface GetCharactersUseCase {
    suspend operator fun invoke(ts: Long, apiKey: String, hash: String): CharactersResponse
}