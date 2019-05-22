package com.example.krasm.marvelapp

import android.app.Application
import com.example.krasm.marvelapp.model.RepoComic
import com.example.krasm.marvelapp.model.RepoHeroes
import com.example.krasm.marvelapp.network.MarvelAPI

class MarvelApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        val api = MarvelAPI.create()

        heroesRepo = RepoHeroes(api)
        comicsRepo = RepoComic(api)
    }

    companion object {
        lateinit var heroesRepo: RepoHeroes
        lateinit var comicsRepo: RepoComic
    }
}