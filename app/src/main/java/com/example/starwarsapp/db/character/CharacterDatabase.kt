package com.example.starwarsapp.db.character

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.starwarsapp.db.Converter
import com.example.starwarsapp.db.film.FilmDao

@Database(entities = [Character::class], version = 1)
@TypeConverters(Converter::class)
abstract class CharacterDatabase : RoomDatabase() {
    abstract fun characterDao() : CharacterDao

    companion object {
        private var instance: CharacterDatabase? = null

        fun getDatabase(context: Context) : CharacterDatabase {
            if (instance == null) {
                synchronized(this) {
                    val inst = Room.databaseBuilder(
                        context.applicationContext,
                        CharacterDatabase::class.java,
                        "characters"
                    ).build()
                    instance = inst
                }
            }
            return instance!! //нулл не будет
        }
    }
}