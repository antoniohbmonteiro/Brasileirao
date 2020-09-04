package com.example.data.local.dao

import androidx.room.*
import com.example.data.models.local.GameLocal
import com.example.data.models.local.GameWithTeams

@Dao
interface GameDao {

//    @Query("SELECT * FROM gameLocal")
//    suspend fun getAll(): List<GameLocal>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(teams: List<GameLocal>)

    @Transaction
    @Query("SELECT * FROM GameLocal")
    suspend fun getGamesWithTeams(): List<GameWithTeams>

    @Query("SELECT * FROM gameLocal where id = :id")
    suspend fun getGame(id: Int): GameWithTeams
}