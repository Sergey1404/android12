package com.example.rickandmorty

import com.google.gson.annotations.SerializedName

sealed class RickAndMortySealed {
    data class Character(val character: CharacterResponse.RickAndMortyNW): RickAndMortySealed()
    object Button: RickAndMortySealed()
}
