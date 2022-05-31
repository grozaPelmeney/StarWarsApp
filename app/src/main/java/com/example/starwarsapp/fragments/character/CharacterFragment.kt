package com.example.starwarsapp.fragments.character

import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.starwarsapp.R
import com.example.starwarsapp.databinding.CharacterFragmentBinding
import com.example.starwarsapp.db.character.Character
import com.example.starwarsapp.db.character.CharacterDatabase
import com.example.starwarsapp.db.character.CharacterDbRepository
import com.example.starwarsapp.utils.Status


class CharacterFragment : Fragment(R.layout.character_fragment) {
    val binding: CharacterFragmentBinding by viewBinding(CharacterFragmentBinding::bind)
    private val adapter by lazy { CharacterRvAdapter() }
    private val dbRepository by lazy { CharacterDbRepository(CharacterDatabase.getDatabase(requireContext()).characterDao()) }
    private val viewModel by lazy { CharacterViewModel(dbRepository) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)

        val args: CharacterFragmentArgs by navArgs()
        val characterUrls = args.charactersInFilm.toList()
        val filmUrl = args.filmUrl
        val filmTitle = args.filmTitle

        (activity as AppCompatActivity).supportActionBar?.title = filmTitle

        setupRv()
        getFilms(filmUrl, characterUrls)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        val item = menu.findItem(R.id.search_btn)
        item.isVisible = false
    }

    private fun getFilms(filmUrl: String, characterUrls: List<String>) {
        viewModel.getCharactersFromDb(filmUrl, characterUrls).observe(viewLifecycleOwner) { result ->
            result?.let {
                when (result.status) {
                    Status.LOADING -> {
                        showOrHideProgressBar(show = true)
                    }
                    Status.ERROR -> {
                        showOrHideProgressBar(show = false)
                        getCharactersFromApi(characterUrls)
                        Toast.makeText(requireContext(), result.message, Toast.LENGTH_SHORT).show()
                    }
                    Status.SUCCESS -> {
                        showOrHideProgressBar(show = false)
                        updateRv(result.data)
                        Toast.makeText(requireContext(), "Данные загружены", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun getCharactersFromApi(characterUrls: List<String>) {
        viewModel.getCharactersFromApi(characterUrls).observe(viewLifecycleOwner) { result ->
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
                        updateRv(result.data)
                        Toast.makeText(requireContext(), "Данные загружены", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun showOrHideProgressBar(show: Boolean) {
        binding.charactersProgressBar.isVisible = show
    }

    private fun setupRv() {
        with(binding) {
            charactersRv.adapter = adapter
            charactersRv.layoutManager = LinearLayoutManager(requireContext())
        }
        adapter.setOnClickListener(object : CharacterRvAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                val planetUrl = adapter.getPlanetUrlByCharacterPosition(position)
                val action = CharacterFragmentDirections.actionCharacterFragmentToPlanetFragment(planetUrl)
                findNavController().navigate(action)
            }
        })
    }

    private fun updateRv(characters: List<Character>?) {
        characters ?: return
        with(binding) {
            (charactersRv.adapter as CharacterRvAdapter).updateList(characters)
        }
    }
}