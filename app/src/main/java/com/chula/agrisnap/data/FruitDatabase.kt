package com.chula.agrisnap.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.chula.agrisnap.model.Fruit
import com.chula.agrisnap.model.User

@Database(entities = [Fruit::class, User::class], version = 3, exportSchema = false)
abstract class FruitDatabase : RoomDatabase() {
    abstract fun fruitDao(): FruitDao
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: FruitDatabase? = null

        fun getDatabase(context: Context): FruitDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FruitDatabase::class.java,
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
