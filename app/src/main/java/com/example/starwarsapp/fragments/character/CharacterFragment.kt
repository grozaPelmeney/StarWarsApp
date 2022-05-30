package com.example.starwarsapp.fragments.character

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
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
    private val layoutManager by lazy { LinearLayoutManager(requireContext()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args: CharacterFragmentArgs by navArgs()
        val characterUrls = args.charactersInFilm.toList()

        viewModel.getCharacters(characterUrls).observe(viewLifecycleOwner) { resource ->
            resource?.let {
                when (resource.status) {
                    Status.LOADING -> {
                        //TODO add progressbar later
                        Log.e("AA", "Loading")
                    }
                    Status.ERROR -> {
                        Toast.makeText(requireContext(), resource.message, Toast.LENGTH_SHORT).show()
                        Log.e("AA", "error")
                    }
                    Status.SUCCESS -> {
                        setupRv(characters = resource.data!!)
                        Log.e("AA", "success")
                    }
                }
            }
        }


    }

    private fun setupRv(characters: List<Character>) {
        with(binding) {
            charactersRv.adapter = adapter
            charactersRv.layoutManager = layoutManager
        }
        updateRv(characters)
        adapter.setOnClickListener(object : CharacterRvAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                //open planet info
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