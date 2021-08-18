package br.com.liebersonsantos.marvelcharacters.util.dispatcher

import kotlinx.coroutines.CoroutineDispatcher

/**
 * Created by lieberson on 8/17/21.
 * @author lieberson.xsantos@gmail.com
 */
interface DispatcherProvider {
    val main: CoroutineDispatcher
    val io: CoroutineDispatcher
    val default: CoroutineDispatcher
    val unconfined: CoroutineDispatcher
}