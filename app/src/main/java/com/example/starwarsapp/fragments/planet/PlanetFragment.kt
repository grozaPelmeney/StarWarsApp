package com.example.starwarsapp.fragments.planet

import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.starwarsapp.R
import com.example.starwarsapp.databinding.PlanetFragmentBinding
import com.example.starwarsapp.db.planet.Planet
import com.example.starwarsapp.db.planet.PlanetDatabase
import com.example.starwarsapp.db.planet.PlanetDbRepository
import com.example.starwarsapp.utils.Status

class PlanetFragment : Fragment(R.layout.planet_fragment) {
    private val binding: PlanetFragmentBinding by viewBinding(PlanetFragmentBinding::bind)
    private val dbRepository by lazy { PlanetDbRepository(PlanetDatabase.getDatabase(requireContext()).planetDao()) }
    private val viewModel by lazy { PlanetViewModel(dbRepository) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)

        val args: PlanetFragmentArgs by navArgs()
        val planetUrl = args.characterPlanetUrl

        getPlanetData(planetUrl)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        val item = menu.findItem(R.id.search_btn)
        item.isVisible = false
    }

    private fun getPlanetData(planetUrl: String) {
        viewModel.getPlanetFromDb(url = planetUrl).observe(viewLifecycleOwner) { result ->
            result?.let {
                when (result.status) {
                    Status.LOADING -> {
                        showOrHideProgressBar(show = true)
                    }
                    Status.ERROR -> {
                        showOrHideProgressBar(show = false)
                        getPlanetDataFromApi(planetUrl)
                        Toast.makeText(requireContext(), result.message, Toast.LENGTH_SHORT).show()
                    }
                    Status.SUCCESS -> {
                        showOrHideProgressBar(show = false)
                        showPlanetData(result.data)
                    }
                }
            }
        }
    }

    private fun getPlanetDataFromApi(planetUrl: String) {
        viewModel.getPlanetFromApi(url = planetUrl).observe(viewLifecycleOwner) { result ->
            result?.let {
                when (result.status) {
                    Status.LOADING -> {
                        showOrHideProgressBar(show = true)
                    }
                    Status.ERROR -> {
                        showOrHideProgressBar(show = false)
                        Toast.makeText(requireContext(), result.message, Toast.LENGTH_SHORT).show()
                    }
                    Status.SUCCESS -> {
                        showOrHideProgressBar(show = false)
                        showPlanetData(result.data)
                    }
                }
            }
        }
    }

    private fun showOrHideProgressBar(show: Boolean) {
        binding.planetProgressBar.isVisible = show
    }

    private fun showPlanetData(planet: Planet?) {
        planet ?: return
        with(binding) {
            planetNameTv.text = planet.name
            gravityTv.text = String.format(getString(R.string.planet_gravity), planet.gravity)
            climateTv.text = String.format(getString(R.string.planet_climate), planet.climate)
            populationTv.text = String.format(getString(R.string.planet_population), planet.population)
            terrainTv.text = String.format(getString(R.string.planet_terrain), planet.terrain)
            diameterTv.text = String.format(getString(R.string.planet_diameter), planet.diameter)
        }
    }
}