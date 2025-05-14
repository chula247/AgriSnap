package com.chula.agrisnap.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.chula.agrisnap.model.Grain
import com.chula.agrisnap.model.User
import com.chula.agrisnap.model.Vegetable

@Database(entities = [Vegetable::class, Grain::class, User::class], version = 4, exportSchema = false)
abstract class GrainDatabase : RoomDatabase() {
    abstract fun vegetableDao(): VegetableDao
    abstract fun grainDao(): GrainDao // Changed from dairyDao to grainDao
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: GrainDatabase? = null

        fun getDatabase(context: Context): GrainDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GrainDatabase::class.java,
                    "main_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
