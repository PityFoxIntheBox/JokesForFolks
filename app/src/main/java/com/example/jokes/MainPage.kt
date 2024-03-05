package com.example.jokes

import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.example.jokes.databinding.ActivityMainPageBinding
import net.thauvin.erik.jokeapi.joke
import net.thauvin.erik.jokeapi.models.Language
import kotlin.coroutines.*

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
        Thread{
            var jk = joke().joke.toString()
            jk = jk.dropLastWhile { it == ']' }
            runOnUiThread { binding!!.Joke.text = jk.dropWhile { it == '[' } }
        }.start()
    }

}



