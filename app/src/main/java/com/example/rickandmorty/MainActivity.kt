package com.example.rickandmorty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmorty.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    private val rickAndMortyAdapter = RickAndMortyAdapter()
    private lateinit var binding: ActivityMainBinding
    private var rickAndMortyAPI = RickAndMortyApi.createAPI()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Timber.plant(Timber.DebugTree())

        recyclerViewInit()
        if(RickAndMortyObject.rickAndMortyList.isEmpty()) {
            loadAllCharacters()
        } else{
            rickAndMortyAdapter.submitList(RickAndMortyObject.rickAndMortyList)
        }

    }

    private fun recyclerViewInit() {
        binding.rvRickAndMorty.adapter = rickAndMortyAdapter
        binding.rvRickAndMorty.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )
    }

    private fun loadAllCharacters(){
        rickAndMortyAPI.getAllCharacters().
        enqueue(object: Callback<CharacterResponse>{
            override fun onResponse(call: Call<CharacterResponse>, response: Response<CharacterResponse>){
                if(response.isSuccessful) {
                    RickAndMortyObject.rickAndMortyList = response.body()?.characters!!
                    rickAndMortyAdapter.submitList(RickAndMortyObject.rickAndMortyList)
                    Log.d("aaa","${RickAndMortyObject.rickAndMortyList}")
                }
            }
            override fun onFailure(call: Call<CharacterResponse>, t: Throwable) {
                Log.e("aaa", "$t")
            }
        }
        )
    }
}