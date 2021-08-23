package br.com.liebersonsantos.marvelcharacters.ui.fragments.home.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.liebersonsantos.marvelcharacters.core.State
import br.com.liebersonsantos.marvelcharacters.domain.model.CharactersResponse
import br.com.liebersonsantos.marvelcharacters.domain.model.Results
import br.com.liebersonsantos.marvelcharacters.domain.usecase.getcharacters.GetCharactersUseCaseImpl
import br.com.liebersonsantos.marvelcharacters.domain.usecase.usecasedb.InsertCrudDbUseCaseImpl
import com.google.common.truth.Truth
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.anyLong
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.mock
import org.mockito.exceptions.base.MockitoException
import java.io.IOException

/**
 * Created by lieberson on 8/22/21.
 *
 * @author lieberson.xsantos@gmail.com
 */
@ExperimentalCoroutinesApi
class HomeViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val testCoroutineDispatcher = TestCoroutineDispatcher()
    private var mockGetCharactersImpl = mock(GetCharactersUseCaseImpl::class.java)
    private var mockInsertCrudDbUseCaseImpl = mock(InsertCrudDbUseCaseImpl::class.java)
    private lateinit var viewModel: HomeViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        viewModel = HomeViewModel(testCoroutineDispatcher, mockGetCharactersImpl,mockInsertCrudDbUseCaseImpl)
    }

    @After
    fun tearDown(){
        testCoroutineDispatcher.cleanupTestCoroutines()
        Dispatchers.resetMain()
    }

    @Test
    fun `testing get Characters loading`() = testCoroutineDispatcher.runBlockingTest{
        testCoroutineDispatcher.pauseDispatcher()

        doReturn(mockResponse()).`when`(mockGetCharactersImpl).invoke(
            anyLong(),
            anyString(),
            anyString()
        )

        viewModel.getCharacters(anyLong(), anyString(), anyString())

        Truth.assertThat(viewModel.response.value).isEqualTo(State.loading<CharactersResponse>(true))
    }

    @Test
    fun `testing get characters loading success`() = testCoroutineDispatcher.runBlockingTest {
        testCoroutineDispatcher.pauseDispatcher()

        doReturn(mockResponse()).`when`(mockGetCharactersImpl).invoke(
            anyLong(),
            anyString(),
            anyString())

        viewModel.getCharacters(anyLong(), anyString(), anyString())

        Truth.assertThat(viewModel.response.value).isEqualTo(State.loading<CharactersResponse>(true))

        testCoroutineDispatcher.resumeDispatcher()
        Truth.assertThat(viewModel.response.value).isEqualTo(State.success(mockResponse()))
    }

    @Test (expected = MockitoException::class)
    fun `testing get characters error state`() = testCoroutineDispatcher.runBlockingTest {
        testCoroutineDispatcher.pauseDispatcher()

        Mockito.doThrow(IOException::class.java).`when`(mockGetCharactersImpl).invoke(
            anyLong(),
            anyString(),
            anyString())

        viewModel.getCharacters(anyLong(), anyString(), anyString())
        Truth.assertThat(viewModel.response.value).isEqualTo(State.loading<CharactersResponse>(true))

        testCoroutineDispatcher.resumeDispatcher()
        Truth.assertThat(viewModel.response.value).isEqualTo(State.error<CharactersResponse>(IOException()))
    }

    @Test
    fun `testing insert loading`() = testCoroutineDispatcher.runBlockingTest{
        testCoroutineDispatcher.pauseDispatcher()

        doReturn(true).`when`(mockInsertCrudDbUseCaseImpl).invoke(mockResult())

        viewModel.insert(mockResult())

        Truth.assertThat(viewModel.insert.value).isEqualTo(State.loading<Boolean>(true))
    }

    @Test
    fun `testing insert loading success`() = testCoroutineDispatcher.runBlockingTest {
        testCoroutineDispatcher.pauseDispatcher()

        doReturn(true).`when`(mockInsertCrudDbUseCaseImpl).invoke(mockResult())

        viewModel.insert(mockResult())

        Truth.assertThat(viewModel.insert.value).isEqualTo(State.loading<Boolean>(true))

        testCoroutineDispatcher.resumeDispatcher()
        Truth.assertThat(viewModel.insert.value).isEqualTo(State.success(true))
    }

    @Test (expected = MockitoException::class)
    fun `testing insert error state`() = testCoroutineDispatcher.runBlockingTest {
        testCoroutineDispatcher.pauseDispatcher()

        Mockito.doThrow(IOException::class.java).`when`(mockInsertCrudDbUseCaseImpl).invoke(mockResult())

        viewModel.insert(mockResult())
        Truth.assertThat(viewModel.insert.value).isEqualTo(State.loading<Boolean>(true))

        testCoroutineDispatcher.resumeDispatcher()
        Truth.assertThat(viewModel.insert.value).isEqualTo(State.error<Boolean>(IOException()))
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
                    "          \"items\": [],\n" +
                    "          \"returned\": 4\n" +
                    "        },\n" +
                    "        \"series\": {\n" +
                    "          \"available\": 1,\n" +
                    "          \"collectionURI\": \"http://gateway.marvel.com/v1/public/characters/1011176/series\",\n" +
                    "          \"items\": [],\n" +
                    "          \"returned\": 1\n" +
                    "        },\n" +
                    "        \"stories\": {\n" +
                    "          \"available\": 8,\n" +
                    "          \"collectionURI\": \"http://gateway.marvel.com/v1/public/characters/1011176/stories\",\n" +
                    "          \"items\": [],\n" +
                    "          \"returned\": 8\n" +
                    "        },\n" +
                    "        \"events\": {\n" +
                    "          \"available\": 1,\n" +
                    "          \"collectionURI\": \"http://gateway.marvel.com/v1/public/characters/1011176/events\",\n" +
                    "          \"items\": [],\n" +
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

    private fun mockResult(): Results =
        Gson().fromJson("{\n" +
                "  \"comics\": {\n" +
                "    \"available\": 4,\n" +
                "    \"collectionURI\": \"http://gateway.marvel.com/v1/public/characters/1017100/comics\",\n" +
                "    \"items\": [\n" +
                "      {\n" +
                "        \"name\": \"FREE COMIC BOOK DAY 2013 1 (2013) #1\",\n" +
                "        \"resourceURI\": \"http://gateway.marvel.com/v1/public/comics/47176\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"name\": \"Hulk (2008) #53\",\n" +
                "        \"resourceURI\": \"http://gateway.marvel.com/v1/public/comics/40632\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"name\": \"Hulk (2008) #54\",\n" +
                "        \"resourceURI\": \"http://gateway.marvel.com/v1/public/comics/40630\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"name\": \"Hulk (2008) #55\",\n" +
                "        \"resourceURI\": \"http://gateway.marvel.com/v1/public/comics/40628\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"returned\": 4\n" +
                "  },\n" +
                "  \"description\": \"Rick Jones has been Hulk's best bud since day one, but now he's more than a friend...he's a teammate! Transformed by a Gamma energy explosion, A-Bomb's thick, armored skin is just as strong and powerful as it is blue. And when he curls into action, he uses it like a giant bowling ball of destruction! \",\n" +
                "  \"events\": {\n" +
                "    \"available\": 0,\n" +
                "    \"collectionURI\": \"http://gateway.marvel.com/v1/public/characters/1017100/events\",\n" +
                "    \"items\": [],\n" +
                "    \"returned\": 0\n" +
                "  },\n" +
                "  \"id\": 1017100,\n" +
                "  \"modified\": \"2013-09-18T15:54:04-0400\",\n" +
                "  \"name\": \"A-Bomb (HAS)\",\n" +
                "  \"resourceURI\": \"http://gateway.marvel.com/v1/public/characters/1017100\",\n" +
                "  \"series\": {\n" +
                "    \"available\": 2,\n" +
                "    \"collectionURI\": \"http://gateway.marvel.com/v1/public/characters/1017100/series\",\n" +
                "    \"items\": [\n" +
                "      {\n" +
                "        \"name\": \"FREE COMIC BOOK DAY 2013 1 (2013)\",\n" +
                "        \"resourceURI\": \"http://gateway.marvel.com/v1/public/series/17765\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"name\": \"Hulk (2008 - 2012)\",\n" +
                "        \"resourceURI\": \"http://gateway.marvel.com/v1/public/series/3374\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"returned\": 2\n" +
                "  },\n" +
                "  \"stories\": {\n" +
                "    \"available\": 7,\n" +
                "    \"collectionURI\": \"http://gateway.marvel.com/v1/public/characters/1017100/stories\",\n" +
                "    \"items\": [\n" +
                "      {\n" +
                "        \"name\": \"Hulk (2008) #55\",\n" +
                "        \"resourceURI\": \"http://gateway.marvel.com/v1/public/stories/92078\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"name\": \"Interior #92079\",\n" +
                "        \"resourceURI\": \"http://gateway.marvel.com/v1/public/stories/92079\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"name\": \"Hulk (2008) #54\",\n" +
                "        \"resourceURI\": \"http://gateway.marvel.com/v1/public/stories/92082\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"name\": \"Interior #92083\",\n" +
                "        \"resourceURI\": \"http://gateway.marvel.com/v1/public/stories/92083\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"name\": \"Hulk (2008) #53\",\n" +
                "        \"resourceURI\": \"http://gateway.marvel.com/v1/public/stories/92086\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"name\": \"Interior #92087\",\n" +
                "        \"resourceURI\": \"http://gateway.marvel.com/v1/public/stories/92087\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"name\": \"cover from Free Comic Book Day 2013 (Avengers/Hulk) (2013) #1\",\n" +
                "        \"resourceURI\": \"http://gateway.marvel.com/v1/public/stories/105929\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"returned\": 7\n" +
                "  },\n" +
                "  \"thumbnail\": {\n" +
                "    \"extension\": \"jpg\",\n" +
                "    \"path\": \"http://i.annihil.us/u/prod/marvel/i/mg/3/20/5232158de5b16\"\n" +
                "  },\n" +
                "  \"urls\": [\n" +
                "    {\n" +
                "      \"type\": \"detail\",\n" +
                "      \"url\": \"http://marvel.com/characters/76/a-bomb?utm_campaign=apiRef&utm_source=de34757044858291b4dd5ddd470ddb83\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"type\": \"comiclink\",\n" +
                "      \"url\": \"http://marvel.com/comics/characters/1017100/a-bomb_has?utm_campaign=apiRef&utm_source=de34757044858291b4dd5ddd470ddb83\"\n" +
                "    }\n" +
                "  ]\n" +
                "}", Results::class.java)
}