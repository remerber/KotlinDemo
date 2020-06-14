package com.example.kotlindemo

import com.google.gson.annotations.SerializedName
data class Repo(
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("description")
    val description: String?
)
