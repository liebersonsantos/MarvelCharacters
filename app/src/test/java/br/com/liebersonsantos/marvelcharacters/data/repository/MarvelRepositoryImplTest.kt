package br.com.liebersonsantos.marvelcharacters.data.repository

import br.com.liebersonsantos.marvelcharacters.data.Api
import br.com.liebersonsantos.marvelcharacters.domain.model.CharactersResponse
import com.google.common.truth.Truth
import com.google.gson.Gson
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.mockito.Mockito.*

/**
 * Created by lieberson on 8/21/21.
 *
 * @author lieberson.xsantos@gmail.com
 */
@ExperimentalCoroutinesApi
class MarvelRepositoryImplTest : TestCase() {
    private val testCoroutinesDispatcher = TestCoroutineDispatcher()
    private val api = mock(Api::class.java)

    @Test
    fun `test response repository`() = testCoroutinesDispatcher.runBlockingTest {
        val repository = MarvelRepositoryImpl(api)
        val response = mockResponse()

        testCoroutinesDispatcher.pauseDispatcher()

        doReturn(response).`when`(api).getCharacters(anyLong(), anyString(), anyString())

        repository.getCharacters(anyLong(), anyString(), anyString()).let {
            Truth.assertThat(it).isEqualTo(response)
        }
    }

    private fun mockResponse(): CharactersResponse =
        Gson().fromJson(
            "{\n" +
                    "  \"code\": 200,\n" +
                    "  \"status\": \"Ok\",\n" +
                    "  \"copyright\": \"© 2021 MARVEL\",\n" +
                    "  \"attributionText\": \"Data provided by Marvel. © 2021 MARVEL\",\n" +
                    "  \"attributionHTML\": \"<a href=\\\"http://marvel.com\\\">Data provided by Marvel. © 2021 MARVEL</a>\",\n" +
                    "  \"etag\": \"0dbb151cdae0a15b6a0dcede768342438d07d759\",\n" +
                    "  \"data\": {\n" +
                    "    \"offset\": 0,\n" +
                    "    \"limit\": 20,\n" +
                    "    \"total\": 1533,\n" +
                    "    \"count\": 20,\n" +
                    "    \"results\": [\n" +
                    "      {\n" +
                    "        \"id\": 1011176,\n" +
                    "        \"name\": \"Ajak\",\n" +
                    "        \"description\": \"\",\n" +
                    "        \"modified\": \"1969-12-31T19:00:00-0500\",\n" +
                    "        \"thumbnail\": {\n" +
                    "          \"path\": \"http://i.annihil.us/u/prod/marvel/i/mg/2/80/4c002f35c5215\",\n" +
                    "          \"extension\": \"jpg\"\n" +
                    "        },\n" +
                    "        \"resourceURI\": \"http://gateway.marvel.com/v1/public/characters/1011176\",\n" +
                    "        \"comics\": {\n" +
                    "          \"available\": 4,\n" +
                    "          \"collectionURI\": \"http://gateway.marvel.com/v1/public/characters/1011176/comics\",\n" +
                    "          \"items\": [\n" +
                    "            {\n" +
                    "              \"resourceURI\": \"http://gateway.marvel.com/v1/public/comics/21175\",\n" +
                    "              \"name\": \"Incredible Hercules (2008) #117\"\n" +
                    "            }\n" +
                    "          ],\n" +
                    "          \"returned\": 4\n" +
                    "        },\n" +
                    "        \"series\": {\n" +
                    "          \"available\": 1,\n" +
                    "          \"collectionURI\": \"http://gateway.marvel.com/v1/public/characters/1011176/series\",\n" +
                    "          \"items\": [\n" +
                    "            {\n" +
                    "              \"resourceURI\": \"http://gateway.marvel.com/v1/public/series/3762\",\n" +
                    "              \"name\": \"Incredible Hercules (2008 - 2010)\"\n" +
                    "            }\n" +
                    "          ],\n" +
                    "          \"returned\": 1\n" +
                    "        },\n" +
                    "        \"stories\": {\n" +
                    "          \"available\": 8,\n" +
                    "          \"collectionURI\": \"http://gateway.marvel.com/v1/public/characters/1011176/stories\",\n" +
                    "          \"items\": [\n" +
                    "            {\n" +
                    "              \"resourceURI\": \"http://gateway.marvel.com/v1/public/stories/47722\",\n" +
                    "              \"name\": \"4 of 4 - Secret Invasion\",\n" +
                    "              \"type\": \"interiorStory\"\n" +
                    "            }\n" +
                    "          ],\n" +
                    "          \"returned\": 8\n" +
                    "        },\n" +
                    "        \"events\": {\n" +
                    "          \"available\": 1,\n" +
                    "          \"collectionURI\": \"http://gateway.marvel.com/v1/public/characters/1011176/events\",\n" +
                    "          \"items\": [\n" +
                    "            {\n" +
                    "              \"resourceURI\": \"http://gateway.marvel.com/v1/public/events/269\",\n" +
                    "              \"name\": \"Secret Invasion\"\n" +
                    "            }\n" +
                    "          ],\n" +
                    "          \"returned\": 1\n" +
                    "        },\n" +
                    "        \"urls\": [\n" +
                    "          {\n" +
                    "            \"type\": \"comiclink\",\n" +
                    "            \"url\": \"http://marvel.com/comics/characters/1011176/ajak?utm_campaign=apiRef&utm_source=de34757044858291b4dd5ddd470ddb83\"\n" +
                    "          }\n" +
                    "        ]\n" +
                    "      }\n" +
                    "    ]\n" +
                    "  }\n" +
                    "}", CharactersResponse::class.java
        )
}