package com.example.starwarsapp.db.film

import android.content.Context
import androidx.room.*
import com.example.starwarsapp.db.Converter
import com.example.starwarsapp.db.character.Character

@Database(entities = [Film::class], version = 1)
@TypeConverters(Converter::class)
abstract class FilmDatabase : RoomDatabase() {
    abstract fun filmDao() : FilmDao

    companion object {
        private var instance: FilmDatabase? = null

        fun getDatabase(context: Context) : FilmDatabase {
            if (instance == null) {
                synchronized(this) {
                    val inst = Room.databaseBuilder(
                        context.applicationContext,
                        FilmDatabase::class.java,
                        "films"
                    ).build()
                    instance = inst
                }
            }
            return instance!! //нулл не будет
        }
    }
}