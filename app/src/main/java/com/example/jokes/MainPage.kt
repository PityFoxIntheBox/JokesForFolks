package com.example.jokes

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.jokes.databinding.ActivityMainPageBinding
import net.thauvin.erik.jokeapi.joke
import net.thauvin.erik.jokeapi.models.Category
import net.thauvin.erik.jokeapi.models.Flag
import net.thauvin.erik.jokeapi.models.IdRange
import net.thauvin.erik.jokeapi.models.Language
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStreamReader


class MainPage : AppCompatActivity() {

    public var binding : ActivityMainPageBinding? = null;
    var JokeId : Int = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainPageBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        if (ContextCompat.checkSelfPermission(this@MainPage, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this@MainPage,arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE), 101)
            CreateFile()
        }
        else
        {
            CreateFile()
        }


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

    fun goToFav(view: View?){
        val intent = Intent(this@MainPage, Favourites::class.java)
        startActivity(intent)
    }

    fun CreateFile(){
        val file = File(this.filesDir.toString() + "/FavJokes.txt")
        if(file.exists()){
            return
        }
        else
        {
            file.createNewFile()
        }
    }

    fun getSomeJokes(view: View?)
    {
        binding!!.EmptyHeart.isChecked = false
        if(binding!!.Any.isChecked)
        {
            getJoke(true)
        }
        else if (!binding!!.Pun.isChecked && !binding!!.Misc.isChecked && !binding!!.Spooky.isChecked && !binding!!.Christmas.isChecked && !binding!!.Programming.isChecked &&
            !binding!!.Dark.isChecked)
        {
            Toast.makeText(this, "You have not selected any category", Toast.LENGTH_SHORT).show()
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
                var jk = joke(categories = setOf(Category.ANY),lang = Language.EN, blacklistFlags = setOf(Flag.ALL))
                var jkText = jk.joke.toString()
                JokeId = jk.id
                checkOnFav()
                jkText = jkText.dropLastWhile { it == ']' }
                runOnUiThread { binding!!.Joke.text = jkText.dropWhile { it == '[' } }
            }.start()
        }
        else {
            var cat = hashSetOf<Category>()
            if(binding!!.Dark.isChecked)
            {
                cat += Category.DARK
            }
            if(binding!!.Christmas.isChecked)
            {
                cat += Category.CHRISTMAS
            }
            if(binding!!.Misc.isChecked)
            {
                cat += Category.MISC
            }
            if(binding!!.Pun.isChecked)
            {
                cat += Category.PUN
            }
            if(binding!!.Spooky.isChecked)
            {
                cat += Category.SPOOKY
            }
            if(binding!!.Programming.isChecked)
            {
                cat += Category.PROGRAMMING
            }
            Thread {
                var jk = joke(categories = cat,lang = Language.EN, blacklistFlags = setOf(Flag.ALL))
                var jkText = jk.joke.toString()
                JokeId = jk.id
                checkOnFav()
                jkText = jkText.dropLastWhile { it == ']' }
                runOnUiThread { binding!!.Joke.text = jkText.dropWhile { it == '[' } }
            }.start()
        }
    }

    fun checkOnFav() : Boolean{

        val fIn = openFileInput("FavJokes.txt")
        val isr = InputStreamReader(fIn)

        val inputBuffer = CharArray(100)

        isr.read(inputBuffer)

        var readString = String(inputBuffer)
        var splitString = readString.split(" ".toRegex()).toTypedArray()
        if(splitString.contains(JokeId.toString())){
                binding!!.EmptyHeart.isChecked = true
                return true
            }
            else{
                return false
            }

    }

    fun saveFavJoke(view: View?) {
        var fos: FileOutputStream? = null
        try {
            if(checkOnFav()){
                Toast.makeText(this, "Шутка уже в избранном", Toast.LENGTH_SHORT).show()
                binding!!.EmptyHeart.isChecked = true
                return
            }
            fos = openFileOutput("FavJokes.txt", MODE_APPEND)
            var id = JokeId.toString() + " "
            fos.write(id.toByteArray())
            Toast.makeText(this, "Шутка добавлена в избранное", Toast.LENGTH_SHORT).show()
        } catch (ex: IOException) {
            Toast.makeText(this, ex.message, Toast.LENGTH_SHORT).show()
        } finally {
            try {
                fos?.close()
            } catch (ex: IOException) {
                Toast.makeText(this, ex.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}



