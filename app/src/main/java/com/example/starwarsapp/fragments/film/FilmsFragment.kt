package com.example.starwarsapp.fragments.film

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.starwarsapp.R
import com.example.starwarsapp.databinding.FilmsFragmentBinding

class FilmsFragment : Fragment(R.layout.films_fragment) {
    private val binding: FilmsFragmentBinding by viewBinding(FilmsFragmentBinding::bind)
    private val viewModel by lazy { FilmsViewModel() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}