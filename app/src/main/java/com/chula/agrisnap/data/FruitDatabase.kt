package com.chula.agrisnap.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.chula.agrisnap.model.Dairy
import com.chula.agrisnap.model.User
import com.chula.agrisnap.model.Vegetable
import com.chula.agrisnap.model.Fruit // Import Fruit model

@Database(entities = [Vegetable::class, Dairy::class, User::class, Fruit::class], version = 5, exportSchema = false)
abstract class FruitDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun fruitDao(): FruitDao // FruitDao added

    companion object {
        @Volatile
        private var INSTANCE: FruitDatabase? = null

        fun getDatabase(context: Context): FruitDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FruitDatabase::class.java,
                    "fruit_database"
                )
                    .fallbackToDestructiveMigration() // 💥 Clears DB on version change
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
