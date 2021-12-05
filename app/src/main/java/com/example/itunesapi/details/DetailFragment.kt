package com.example.itunesapi.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.itunesapi.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentDetailBinding.inflate( inflater )

        binding.lifecycleOwner = this

        binding.songInfo = DetailFragmentArgs.fromBundle( requireArguments() ).songInfo

        return binding.root
    }

}