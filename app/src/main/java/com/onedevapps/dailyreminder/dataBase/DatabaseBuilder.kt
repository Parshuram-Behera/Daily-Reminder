package com.onedevapps.dailyreminder.dataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.onedevapps.dailyreminder.dataBase.DAO.DatabaseInterface
import com.onedevapps.dailyreminder.dataBase.entity.ScheduleEntity

@Database(entities = [ScheduleEntity::class], version = 1)
abstract class DatabaseBuilder(): RoomDatabase() {

    abstract fun getDao(): DatabaseInterface

    companion object {
        const val DATABASE_NAME = "schedule_database"

        fun getInstance(context: Context): DatabaseBuilder {

            return Room.databaseBuilder(
                context.applicationContext,
                DatabaseBuilder::class.java,
                DATABASE_NAME
            ).build()
        }

    }
}