package com.example.jokes

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.jokes.databinding.ActivityMainPageBinding
import net.thauvin.erik.jokeapi.joke
import net.thauvin.erik.jokeapi.models.Category
import net.thauvin.erik.jokeapi.models.Language


class MainPage : AppCompatActivity() {

    val lg = Language.EN
    public var binding : ActivityMainPageBinding? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainPageBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        binding!!.Any.setOnCheckedChangeListener{ buttonView, isChecked ->
            if (isChecked){
                binding!!.Dark.isChecked = false
                binding!!.Programming.isChecked = false
                binding!!.Pun.isChecked = false
                binding!!.Misc.isChecked = false
                binding!!.Spooky.isChecked = false
                binding!!.Christmas.isChecked = false
            }
        }

        binding!!.Dark.setOnCheckedChangeListener{ buttonView, isChecked ->
            if (isChecked){
                uncheckAny()
            }
        }

        binding!!.Misc.setOnCheckedChangeListener{ buttonView, isChecked ->
            if (isChecked){
                uncheckAny()
            }
        }

        binding!!.Programming.setOnCheckedChangeListener{ buttonView, isChecked ->
            if (isChecked){
                uncheckAny()
            }
        }

        binding!!.Pun.setOnCheckedChangeListener{ buttonView, isChecked ->
            if (isChecked){
                uncheckAny()
            }
        }

        binding!!.Christmas.setOnCheckedChangeListener{ buttonView, isChecked ->
            if (isChecked){
                uncheckAny()
            }
        }

        binding!!.Spooky.setOnCheckedChangeListener{ buttonView, isChecked ->
            if (isChecked){
                uncheckAny()
            }
        }
    }

    fun getSomeJokes()
    {
        if(binding!!.Any.isChecked)
        {
            getJoke(true)
        }
        else
        {
            getJoke(false)
        }
    }

    fun CheckUncheck(view: View?) {

        when(view!!.id)
        {
            binding!!.ButtonAny.id -> if(binding!!.Any.isChecked) {
                binding!!.Any.isChecked = false
            }
            else
            {
                binding!!.Any.isChecked = true
            }

            binding!!.ButtonChristmas.id -> if(binding!!.Christmas.isChecked) {
                binding!!.Christmas.isChecked = false
            }
            else
            {
                binding!!.Christmas.isChecked = true
            }

            binding!!.ButtonDark.id -> if(binding!!.Dark.isChecked) {
                binding!!.Dark.isChecked = false
            }
            else
            {
                binding!!.Dark.isChecked = true
            }

            binding!!.ButtonMisc.id -> if(binding!!.Misc.isChecked) {
                binding!!.Misc.isChecked = false
            }
            else
            {
                binding!!.Misc.isChecked = true
            }

            binding!!.ButtonPun.id -> if(binding!!.Pun.isChecked) {
                binding!!.Pun.isChecked = false
            }
            else
            {
                binding!!.Pun.isChecked = true
            }

            binding!!.ButtonProgramming.id -> if(binding!!.Programming.isChecked) {
                binding!!.Programming.isChecked = false
            }
            else
            {
                binding!!.Programming.isChecked = true
            }

            binding!!.ButtonSpooky.id -> if(binding!!.Spooky.isChecked) {
                binding!!.Spooky.isChecked = false
            }
            else
            {
                binding!!.Spooky.isChecked = true
            }
        }
    }

    fun uncheckAny()
    {
        binding!!.Any.isChecked = false
    }

    fun getJoke(AnyOrNot : Boolean) {
        if(AnyOrNot) {
            Thread {
                var jk = joke(categories = setOf(Category.ANY),lang = lg).joke.toString()
                jk = jk.dropLastWhile { it == ']' }
                runOnUiThread { binding!!.Joke.text = jk.dropWhile { it == '[' } }
            }.start()
        }
        else {

        }
    }

}



