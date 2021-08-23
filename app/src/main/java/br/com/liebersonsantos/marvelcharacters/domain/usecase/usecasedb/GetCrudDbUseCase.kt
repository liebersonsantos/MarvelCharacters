package br.com.liebersonsantos.marvelcharacters.domain.usecase.usecasedb

import androidx.lifecycle.LiveData
import br.com.liebersonsantos.marvelcharacters.domain.model.Results

/**
 * Created by lieberson on 8/23/21.
 * @author lieberson.xsantos@gmail.com
 */
interface GetCrudDbUseCase {
    operator fun invoke(): LiveData<List<Results>>
}