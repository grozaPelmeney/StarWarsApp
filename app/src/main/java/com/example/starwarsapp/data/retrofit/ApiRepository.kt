package com.example.starwarsapp.data.retrofit

class ApiRepository : ApiServices {
    override suspend fun getFilms() =
        Common.apiServices.getFilms()

    override suspend fun getFilmById(id: Int) =
        Common.apiServices.getFilmById(id)

    override suspend fun getCharacters() =
        Common.apiServices.getCharacters()

    override suspend fun getCharacterById(id: Int) =
        Common.apiServices.getCharacterById(id)

    override suspend fun getCharacterByUrl( url: String) =
        Common.apiServices.getCharacterByUrl(url)

    override suspend fun getPlanetByUrl( url: String) =
        Common.apiServices.getPlanetByUrl(url)

    override suspend fun getPlanetById(id: Int) =
        Common.apiServices.getPlanetById(id)
}