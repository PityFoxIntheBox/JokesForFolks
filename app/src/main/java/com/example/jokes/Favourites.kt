package com.example.jokes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jokes.databinding.ActivityFavouritesBinding
import com.example.jokes.databinding.ActivityMainPageBinding
import com.example.jokes.databinding.OneJokeBinding
import kotlinx.coroutines.delay
import net.thauvin.erik.jokeapi.JokeApi.jokes
import net.thauvin.erik.jokeapi.joke
import net.thauvin.erik.jokeapi.jokes
import net.thauvin.erik.jokeapi.models.Category
import net.thauvin.erik.jokeapi.models.Flag
import net.thauvin.erik.jokeapi.models.IdRange
import net.thauvin.erik.jokeapi.models.Joke
import net.thauvin.erik.jokeapi.models.Language
import java.io.InputStreamReader
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException



class Favourites : AppCompatActivity() {

    public var binding : ActivityFavouritesBinding? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavouritesBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        getJokes()
    }

    fun getJokes(){
        val fIn = openFileInput("FavJokes.txt")

        val isr = InputStreamReader(fIn)

        val inputBuffer = CharArray(100)

        isr.read(inputBuffer)

        var readString = String(inputBuffer).filter { it.isDigit() || it == ' ' }
        var splitString = readString.split(" ".toRegex()).toTypedArray()
        var Jokes : MutableList<String> = mutableListOf()
        Thread {
            var i = 0
            while (i<splitString.count()-1){
                Jokes.add(joke(idRange = IdRange(splitString[i].toInt())).joke.toString().filterNot { it == '[' || it == ']' })
                i++
            }
            runOnUiThread {setRecyclerView(Jokes) }
        }.start()
    }

    fun goBack(){
        val intent = Intent(this@Favourites, MainPage::class.java)
        startActivity(intent)
    }

    fun setRecyclerView(Jokes : MutableList<String>) {
        binding!!.RecViewJokes.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding!!.RecViewJokes.adapter = RecViewJokesAdapter(Jokes);

    }
}

class RecViewJokesAdapter(var Jokes : MutableList<String>) : RecyclerView.Adapter<RecViewJokesViewHolder>()
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecViewJokesViewHolder {
        var layoutInflater = LayoutInflater.from(parent.context)
        var binding = OneJokeBinding.inflate(layoutInflater)
        return RecViewJokesViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return Jokes.count()
    }

    override fun onBindViewHolder(holder: RecViewJokesViewHolder, Id: Int) {
        holder.binding.JokeText.text = Jokes[Id]
    }

}

class RecViewJokesViewHolder(var binding : OneJokeBinding) : RecyclerView.ViewHolder(binding.root)