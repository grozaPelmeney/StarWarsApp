package com.example.starwarsapp.data.retrofit

class ApiRepository : ApiServices {
    override suspend fun getFilms() =
        Common.apiServices.getFilms()

    override suspend fun getFilmById(id: Int) =
        Common.apiServices.getFilmById(id)

    override suspend fun getCharacters() =
        Common.apiServices.getCharacters()

    override suspend fun getCharactersById(id: Int) =
        Common.apiServices.getCharactersById(id)

    override suspend fun getCharactersByUrl(url: String) =
        Common.apiServices.getCharactersByUrl(url)

    override suspend fun getPlanetById(id: Int) =
        Common.apiServices.getPlanetById(id)
}