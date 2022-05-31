package com.example.starwarsapp.fragments.character

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.starwarsapp.R
import com.example.starwarsapp.databinding.CharacterFragmentBinding
import com.example.starwarsapp.db.character.Character
import com.example.starwarsapp.utils.Status


class CharacterFragment : Fragment(R.layout.character_fragment) {
    val binding: CharacterFragmentBinding by viewBinding(CharacterFragmentBinding::bind)
    private val viewModel by lazy { CharacterViewModel() }
    private val adapter by lazy { CharacterRvAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args: CharacterFragmentArgs by navArgs()
        val characterUrls = args.charactersInFilm.toList()

        viewModel.getCharacters(characterUrls).observe(viewLifecycleOwner) { result ->
            result?.let {
                when (result.status) {
                    Status.LOADING -> {
                        //TODO add progressbar later
                    }
                    Status.ERROR -> {
                        Toast.makeText(requireContext(), result.message, Toast.LENGTH_SHORT).show()
                    }
                    Status.SUCCESS -> {
                        setupRv(characters = result.data!!)
                    }
                }
            }
        }
    }

    private fun setupRv(characters: List<Character>) {
        with(binding) {
            charactersRv.adapter = adapter
            charactersRv.layoutManager = LinearLayoutManager(requireContext())
        }
        updateRv(characters)
        adapter.setOnClickListener(object : CharacterRvAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                val planetUrl = adapter.getPlanetUrlByCharacterPosition(position)
                val action = CharacterFragmentDirections.actionCharacterFragmentToPlanetFragment(planetUrl)
                findNavController().navigate(action)
            }
        })
    }

    private fun updateRv(characters: List<Character>) {
        with(binding) {
            (charactersRv.adapter as CharacterRvAdapter).updateList(characters)
            charactersRv.adapter?.notifyDataSetChanged()
        }
    }
}