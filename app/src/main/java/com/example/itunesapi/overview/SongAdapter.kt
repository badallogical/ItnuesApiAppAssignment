package com.example.itunesapi.overview


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.itunesapi.databinding.SongItemBinding
import com.example.itunesapi.network.SongInfo

class SongAdapter( private val onClick : (SongInfo) -> Unit ) : ListAdapter<SongInfo, SongAdapter.SongItemViewHolder>(SongComparator) {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongItemViewHolder {
        val binding = SongItemBinding.inflate( LayoutInflater.from(parent.context) )
        return SongItemViewHolder( binding )
    }

    override fun onBindViewHolder(holder: SongItemViewHolder, position: Int) {
        holder.bind( getItem(position) )
        holder.binding.root.setOnClickListener{
            onClick( getItem(position) )
        }
    }

    class SongItemViewHolder( val binding : SongItemBinding ) : RecyclerView.ViewHolder( binding.root ) {

        fun bind( songInfo : SongInfo ){
            binding.songInfo = songInfo
            binding.executePendingBindings()
        }

    }

    companion object SongComparator : DiffUtil.ItemCallback<SongInfo>() {
        override fun areItemsTheSame(oldItem: SongInfo, newItem: SongInfo): Boolean {
            return oldItem.trackId === newItem.trackId
        }

        override fun areContentsTheSame(oldItem: SongInfo, newItem: SongInfo): Boolean {
            return oldItem.trackId == newItem.trackId
        }

    }
}


