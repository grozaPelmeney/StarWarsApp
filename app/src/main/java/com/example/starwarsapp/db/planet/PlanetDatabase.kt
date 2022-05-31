package com.example.starwarsapp.db.planet

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.starwarsapp.db.Converter

@Database(entities = [Planet::class], version = 1)
@TypeConverters(Converter::class)
abstract class PlanetDatabase : RoomDatabase() {
    abstract fun planetDao() : PlanetDao

    companion object {
        private var instance: PlanetDatabase? = null

        fun getDatabase(context: Context) : PlanetDatabase {
            if (instance == null) {
                synchronized(this) {
                    val inst = Room.databaseBuilder(
                        context.applicationContext,
                        PlanetDatabase::class.java,
                        "planets"
                    ).build()
                    instance = inst
                }
            }
            return instance!! //нулл не будет
        }
    }
}