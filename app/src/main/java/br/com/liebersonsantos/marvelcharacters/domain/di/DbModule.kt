package br.com.liebersonsantos.marvelcharacters.domain.di

import android.content.Context
import androidx.room.Room
import br.com.liebersonsantos.marvelcharacters.data.db.CharactersDao
import br.com.liebersonsantos.marvelcharacters.data.db.CharactersDataBase
import br.com.liebersonsantos.marvelcharacters.data.db.repository.DbRepository
import br.com.liebersonsantos.marvelcharacters.data.db.repository.DbRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by lieberson on 8/19/21.
 * @author lieberson.xsantos@gmail.com
 */
@Module
@InstallIn(SingletonComponent::class)
object DbModule {

    @Singleton
    @Provides
    fun providerCharactersDataBase(@ApplicationContext app: Context) = Room.databaseBuilder(
        app,
        CharactersDataBase::class.java,
        "characters_db"
    ).build()

    @Singleton
    @Provides
    fun providerCharactersDao(db: CharactersDataBase) = db.charactersDao()

    @Singleton
    @Provides
    fun providerDbRepository(charactersDao: CharactersDao): DbRepository
    = DbRepositoryImpl(charactersDao)

}