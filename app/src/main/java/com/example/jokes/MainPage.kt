package com.example.jokes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.jokes.databinding.ActivityMainPageBinding
import net.thauvin.erik.jokeapi.joke
import net.thauvin.erik.jokeapi.models.Language

class MainPage : AppCompatActivity() {

    val lg = Language.EN
    public var binding : ActivityMainPageBinding? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainPageBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        getJoke()
    }

    fun getJoke() {
        Thread({
            val jk = joke()
            binding!!.Joke.text = jk.toString()
        }).start()
    }

}

