package com.example.material3app.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.material3app.Cibo
import com.example.material3app.Ricetta


@Database(entities = [Cibo::class, Ricetta::class], version = 1, exportSchema = false)
@TypeConverters(ImageConverter::class)
abstract class FoodDatabase: RoomDatabase()
{
    abstract fun foodDao(): FoodDao

    companion object{
        @Volatile
        private var INSTANCE: FoodDatabase? = null

        fun getDatabase(context:Context):FoodDatabase{
            val tempIstance = INSTANCE
            if(tempIstance != null){
                return tempIstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext,FoodDatabase::class.java,"food_database").build()
                INSTANCE = instance
                return  instance
            }

        }

    }

}