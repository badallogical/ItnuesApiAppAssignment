package com.example.itunesapi.overview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.example.itunesapi.R
import com.example.itunesapi.databinding.FragmentOverviewBinding
import com.example.itunesapi.network.SongInfo
import timber.log.Timber

class OverviewFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        Timber.d("Fragment called")

        val binding = FragmentOverviewBinding.inflate( inflater )

        binding.lifecycleOwner = this

        val viewModel = ViewModelProvider(this).get(OverviewViewModel::class.java)

        binding.viewModel = viewModel

        val navController = NavHostFragment.findNavController(this)
        val onClickImage : ( SongInfo) -> Unit = { songInfo : SongInfo ->
            navController.navigate( OverviewFragmentDirections.actionOverviewFragmentToDetailFragment(songInfo) )
        }

        binding.songList.adapter = SongAdapter( onClickImage )

        viewModel.songApiStatus.observe( viewLifecycleOwner, Observer<SongsApiStatus> {
            when ( it ){
                SongsApiStatus.Loading -> binding.progressBar.visibility = View.VISIBLE
                SongsApiStatus.Done -> binding.progressBar.visibility = View.GONE
                else -> binding.progressBar.visibility = View.GONE
            }
        })

        binding.searchBar.setOnQueryTextListener( object : SearchView.OnQueryTextListener{
            override fun onQueryTextChange(newText: String?): Boolean {
                return true;
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                if( query != null ){
                    viewModel.getSongInfo(query)
                }

                return true;
            }
        })



        // Inflate the layout for this fragment
        return binding.root
    }

}