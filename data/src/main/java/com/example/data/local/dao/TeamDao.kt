package com.example.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.models.local.TeamLocal

@Dao
interface TeamDao {

    @Query("SELECT * FROM teamLocal")
    suspend fun getAll(): List<TeamLocal>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(teams: List<TeamLocal>)
}