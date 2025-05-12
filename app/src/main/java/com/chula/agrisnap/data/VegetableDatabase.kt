package com.chula.agrisnap.data


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.chula.agrisnap.model.Vegetable
import com.chula.agrisnap.model.User

@Database(entities = [Vegetable::class, User::class], version = 3, exportSchema = false)
abstract class VegetableDatabase : RoomDatabase() {
    abstract fun vegetableDao(): VegetableDao
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: VegetableDatabase? = null

        fun getDatabase(context: Context): VegetableDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    VegetableDatabase::class.java,
                    "main_database"
                )
                    .fallbackToDestructiveMigration() // 💥 This clears DB on version change
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}