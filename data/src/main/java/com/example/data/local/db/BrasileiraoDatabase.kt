package com.example.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.BrasileiraoConverter
import com.example.data.local.dao.GameDao
import com.example.data.local.dao.TeamDao
import com.example.data.models.local.GameLocal
import com.example.data.models.local.TeamLocal

@Database(entities = [TeamLocal::class, GameLocal::class], version = 1)
@TypeConverters(BrasileiraoConverter::class)
abstract class BrasileiraoDatabase : RoomDatabase() {

    abstract fun teamDao(): TeamDao
    abstract fun gameDao(): GameDao

}