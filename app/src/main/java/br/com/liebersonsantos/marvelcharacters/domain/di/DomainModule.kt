package br.com.liebersonsantos.marvelcharacters.domain.di

import br.com.liebersonsantos.marvelcharacters.domain.usecase.getcharacters.GetCharactersUseCase
import br.com.liebersonsantos.marvelcharacters.domain.usecase.getcharacters.GetCharactersUseCaseImpl
import br.com.liebersonsantos.marvelcharacters.domain.usecase.usecasedb.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

/**
 * Created by lieberson on 8/17/21.
 * @author lieberson.xsantos@gmail.com
 */
@Module
@InstallIn(ViewModelComponent::class)
interface DomainModule {

    @Binds
    fun bindGetCharactersUseCase(useCase: GetCharactersUseCaseImpl): GetCharactersUseCase

    @Binds
    fun bindInsertCrudDbUseCase(insertCrudDbUseCaseImpl: InsertCrudDbUseCaseImpl): InsertCrudDbUseCase

    @Binds
    fun bindGetCrudDbUseCase(getCrudDbUseCaseImpl: GetCrudDbUseCaseImpl): GetCrudDbUseCase

    @Binds
    fun bindDeleteCrudDbUseCase(deleteCrudDbUseCaseImpl: DeleteCrudDbUseCaseImpl): DeleteCrudDbUseCase

}
