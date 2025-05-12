package com.chula.agrisnap.data


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.chula.agrisnap.model.Dairy
import com.chula.agrisnap.model.User
import com.chula.agrisnap.model.Vegetable

@Database(entities = [Vegetable::class, Dairy::class, User::class], version = 4, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun vegetableDao(): VegetableDao
    abstract fun dairyDao(): DairyDao // DairyDao added
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "main_database"
                )
                    .fallbackToDestructiveMigration() // 💥 Clears DB on version change
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}