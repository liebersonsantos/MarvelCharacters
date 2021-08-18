package br.com.liebersonsantos.marvelcharacters.domain.usecase.getcharacters

import br.com.liebersonsantos.marvelcharacters.data.repository.MarvelRepository
import br.com.liebersonsantos.marvelcharacters.domain.model.CharactersResponse
import javax.inject.Inject

/**
 * Created by lieberson on 8/17/21.
 * @author lieberson.xsantos@gmail.com
 */
class GetCharactersUseCaseImpl @Inject constructor(
    private val marvelRepository: MarvelRepository
) : GetCharactersUseCase {
    override suspend fun invoke(ts: Long, apiKey: String, hash: String): CharactersResponse =
        marvelRepository.getCharacters(ts, apiKey, hash)

}