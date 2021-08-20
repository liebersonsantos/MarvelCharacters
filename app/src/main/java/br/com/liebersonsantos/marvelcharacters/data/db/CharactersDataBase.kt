package br.com.liebersonsantos.marvelcharacters.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.liebersonsantos.marvelcharacters.domain.model.Results

/**
 * Created by lieberson on 8/19/21.
 * @author lieberson.xsantos@gmail.com
 */
@Database(entities = [Results::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class CharactersDataBase: RoomDatabase() {
    abstract fun charactersDao(): CharactersDao
}