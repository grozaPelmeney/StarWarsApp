package com.example.starwarsapp.fragments.film

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.starwarsapp.R
import com.example.starwarsapp.databinding.FilmsFragmentBinding
import com.example.starwarsapp.db.film.Film
import com.example.starwarsapp.utils.Status

class FilmsFragment : Fragment(R.layout.films_fragment) {
    private val binding: FilmsFragmentBinding by viewBinding(FilmsFragmentBinding::bind)
    private val viewModel by lazy { FilmsViewModel() }
    private val adapter by lazy { FilmsRvAdapter() }
    private val layoutManager by lazy { LinearLayoutManager(requireContext()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getFilms().observe(viewLifecycleOwner) { resource ->
            resource?.let {
                when (resource.status) {
                    Status.LOADING -> {
                        //TODO add progressbar later
                    }
                    Status.ERROR -> {
                        Toast.makeText(requireContext(), resource.message, Toast.LENGTH_SHORT).show()
                    }
                    Status.SUCCESS -> {
                        setupRv(films = resource.data!!.films)
                    }
                }
            }
        }
    }

    private fun setupRv(films: List<Film>) {
        with(binding) {
            filmsRv.adapter = adapter
            filmsRv.layoutManager = layoutManager
        }
        updateRv(films)
        adapter.setOnClickListener(object : FilmsRvAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                findNavController().navigate(R.id.action_filmsFragment_to_characterFragment)
            }
        })
    }

    private fun updateRv(films: List<Film>) {
        with(binding) {
            (filmsRv.adapter as FilmsRvAdapter).updateList(films)
            filmsRv.adapter?.notifyDataSetChanged()
        }
    }

}