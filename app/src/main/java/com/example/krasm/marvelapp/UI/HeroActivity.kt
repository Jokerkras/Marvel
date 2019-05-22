package com.example.krasm.marvelapp.UI

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.example.krasm.marvelapp.MarvelApplication
import com.example.krasm.marvelapp.R
import com.example.krasm.marvelapp.adapter.CardAdapter
import com.example.krasm.marvelapp.model.Character
import com.example.krasm.marvelapp.model.Comic
import com.example.krasm.marvelapp.model.ComicSummary
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_hero.*

class HeroActivity : AppCompatActivity() {
    lateinit var hero: Character
    var comics = ArrayList<Comic>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hero)

        recycleView_Comics.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recycleView_Comics.adapter = CardAdapter(comics, this)

        val id = intent.getIntExtra("id", 0)

        MarvelApplication.comicsRepo.getComicsByCharacter(id, 0)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe { result ->
                    result.data?.results?.let { comics.addAll(it) }
                    recycleView_Comics.adapter.notifyDataSetChanged()
                }

        MarvelApplication.heroesRepo.getCharacter(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({result ->
                    hero = result.data.results!![0]
                    Log.d("fuck", result.data.results[0].toString())
                    insertHero(hero)
                }, {error -> error.printStackTrace()})
    }

    fun insertHero(hero: Character) {
        textView_name.text = hero.name
        textView_description.text = hero.description
        Picasso.get()
                .load(hero.thumbnail!!.path + "/portrait_uncanny." + hero.thumbnail!!.extension)
                .into(imageView_hero)
    }
}