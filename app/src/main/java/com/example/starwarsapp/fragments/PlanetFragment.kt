package com.example.starwarsapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.starwarsapp.R
import com.example.starwarsapp.databinding.PlanetFragmentBinding
import com.example.starwarsapp.db.planet.Planet
import com.example.starwarsapp.utils.Status

class PlanetFragment : Fragment(R.layout.planet_fragment) {
    private val binding: PlanetFragmentBinding by viewBinding(PlanetFragmentBinding::bind)
    private val viewModel by lazy { PlanetViewModel() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args: PlanetFragmentArgs by navArgs()
        val planetUrl = args.characterPlanetUrl
        viewModel.getPlanet(url = planetUrl).observe(viewLifecycleOwner) { result ->
            result?.let {
                when (result.status) {
                    Status.LOADING -> {
                        //TODO add progressbar later
                        Log.e("AA", "Loading")
                    }
                    Status.ERROR -> {
                        Log.e("AA", "error1")
                        Toast.makeText(requireContext(), result.message, Toast.LENGTH_SHORT).show()
                        Log.e("AA", "error2")
                    }
                    Status.SUCCESS -> {
                        showPlanetData(result.data)
                        Log.e("AA", "success")
                    }
                }
            }
        }
    }

    private fun showPlanetData(planet: Planet?) {
        planet ?: return
        with(binding) {
            planetNameTv.text = planet.name
            gravityTv.text = "gravity: ${planet.gravity}"
            climateTv.text = "climate: ${planet.climate}"
            populationTv.text = "population: ${planet.population}"
            terrainTv.text = "terrain: ${planet.terrain}"
            diameterTv.text = "diameter: ${planet.diameter}"
        }
    }
}