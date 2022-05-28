package com.example.starwarsapp.fragments.character

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.starwarsapp.R
import com.example.starwarsapp.databinding.CharacterFragmentBinding
import com.example.starwarsapp.fragments.film.FilmsViewModel

class CharacterFragment : Fragment(R.layout.character_fragment) {
    val binding: CharacterFragmentBinding by viewBinding(CharacterFragmentBinding::bind)
    private val viewModel by lazy { CharacterViewModel() }

}