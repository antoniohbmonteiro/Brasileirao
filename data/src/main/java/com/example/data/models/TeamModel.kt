package com.example.data.models

import com.google.gson.annotations.SerializedName

data class TeamModel(

    @SerializedName("id")
    val id: Int,

    @SerializedName("nome")
    val name: String,

    @SerializedName("acronimo")
    val acronym: String,

    @SerializedName("estadio")
    val stadium: String,

    @SerializedName("escudo")
    val logo: String

)