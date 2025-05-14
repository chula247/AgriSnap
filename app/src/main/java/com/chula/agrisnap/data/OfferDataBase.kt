package com.chula.agrisnap.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.chula.agrisnap.model.Offer

@Database(entities = [Offer::class], version = 1, exportSchema = false)
abstract class OfferDatabase : RoomDatabase() {
    abstract fun offerDao(): OfferDao

    companion object {
        @Volatile
        private var INSTANCE: OfferDatabase? = null

        fun getDatabase(context: Context): OfferDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    OfferDatabase::class.java,
                    "offer_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
