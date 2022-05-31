package com.example.starwarsapp.fragments.film

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.appcompat.widget.SearchView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.starwarsapp.R
import com.example.starwarsapp.databinding.FilmsFragmentBinding
import com.example.starwarsapp.db.film.Film
import com.example.starwarsapp.db.film.FilmDatabase
import com.example.starwarsapp.db.film.FilmDbRepository
import com.example.starwarsapp.utils.Status

class FilmsFragment : Fragment(R.layout.films_fragment) {
    private val binding: FilmsFragmentBinding by viewBinding(FilmsFragmentBinding::bind)
    private val adapter by lazy { FilmsRvAdapter() }
    private val dbRepository by lazy { FilmDbRepository(FilmDatabase.getDatabase(requireContext()).filmDao()) }
    private val viewModel by lazy { FilmsViewModel(dbRepository) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)

        setupRv()
        getFilms()
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        val searchView = menu.findItem(R.id.search_btn).actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return false
            }
        })
    }

    private fun getFilms() {
        viewModel.getFilmsFromDb().observe(viewLifecycleOwner) { result ->
            result?.let {
                when (result.status) {
                    Status.LOADING -> {
                        showOrHideProgressBar(show = true)
                    }
                    Status.ERROR -> {
                        showOrHideProgressBar(show = false)
                        getFilmsFromApi()
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

    private fun getFilmsFromApi() {
        viewModel.getFilmsFromApi().observe(viewLifecycleOwner) { result ->
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
        binding.filmsProgressBar.isVisible = show
    }

    private fun setupRv() {
        with(binding) {
            filmsRv.adapter = adapter
            filmsRv.layoutManager = LinearLayoutManager(requireContext())
        }
        adapter.setOnClickListener(object : FilmsRvAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                val charactersList = adapter.getFilmByPosition(position).characters
                val filmUrl = adapter.getFilmByPosition(position).url
                val action =
                    FilmsFragmentDirections.actionFilmsFragmentToCharacterFragment(
                        charactersList.toTypedArray(),
                        filmUrl)
                findNavController().navigate(action)
            }
        })
    }

    private fun updateRv(films: List<Film>?) {
        films ?: return
        (binding.filmsRv.adapter as FilmsRvAdapter).updateList(films)
    }

}