package com.example.krasm.marvelapp.network

import io.reactivex.Observable
import com.example.krasm.marvelapp.model.CharacterDataWrapper
import com.example.krasm.marvelapp.model.ComicDataWrapper
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelAPI {
    @GET("v1/public/characters")
    fun getHeroes(@Query("ts") ts:String,
                  @Query("apikey") apikey: String,
                  @Query("hash") hash: String,
                  @Query("limit") limit: Int,
                  @Query("offset") offset: Int): Observable<CharacterDataWrapper>

    @GET("v1/public/characters/{characterId}")
    fun getCharacter(@Path("characterId") characterId: Int,
                     @Query("ts") ts:String,
                     @Query("apikey") apikey: String,
                     @Query("hash") hash: String): Observable<CharacterDataWrapper>

    @GET("v1/public/characters/{characterId}/comics")
    fun getComicsByCharacter(@Path("characterId") characterId: Int,
                             @Query("ts") ts:String,
                             @Query("apikey") apikey: String,
                             @Query("hash") hash: String,
                             @Query("limit") limit: Int,
                             @Query("offset") offset: Int): Observable<ComicDataWrapper>

    companion object Factory {
        fun create(): MarvelAPI {
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://gateway.marvel.com:443/")
                    .build()
            return retrofit.create(MarvelAPI::class.java)
        }
    }
}