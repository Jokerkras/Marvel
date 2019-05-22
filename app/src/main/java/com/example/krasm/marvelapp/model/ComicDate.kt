package com.example.krasm.marvelapp.model

import com.google.gson.annotations.SerializedName

data class ComicDate (
        @SerializedName("type")val type: String,
        @SerializedName("date")val date: String
)