package br.com.hellodev.movieapp.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.hellodev.movieapp.data.local.dao.MovieDao
import br.com.hellodev.movieapp.data.local.entity.MovieEntity

@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

}