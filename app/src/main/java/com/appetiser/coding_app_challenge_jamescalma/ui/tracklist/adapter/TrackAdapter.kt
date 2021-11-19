package com.appetiser.coding_app_challenge_jamescalma.ui.tracklist.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.appetiser.coding_app_challenge_jamescalma.R
import com.appetiser.coding_app_challenge_jamescalma.databinding.AdapterTrackBinding
import com.appetiser.coding_app_challenge_jamescalma.extensions.loadItemImageCircleCrop
import com.appetiser.coding_app_challenge_jamescalma.model.Track

class TrackAdapter(
    var context: Context,
    var trackList: List<Track>,
    var onTrackClickedListener: OnTrackClickListener
    ) : androidx.recyclerview.widget.RecyclerView.Adapter<TrackAdapter.ViewHolder>() {

    interface OnTrackClickListener{
        fun onTrackClicked(track: Track)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = AdapterTrackBinding.inflate(layoutInflater, parent, false)

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return trackList.size
    }
    override fun onBindViewHolder(holder: TrackAdapter.ViewHolder, position: Int) {
        val track = trackList[position]
        holder.bindTrack(track)
    }

    inner class ViewHolder(val binding: AdapterTrackBinding): androidx.recyclerview.widget.RecyclerView.ViewHolder(binding.root) {
        fun bindTrack(track: Track) {
            with(binding) {
                context.getDrawable(R.drawable.music_placeholder)?.let {
                    imgTrack.loadItemImageCircleCrop(track.artworkUrl60, it)
                }
                txtTrackName.text =  track.getValidTrackName()
                txtTrackGenre.text =  track.primaryGenreName
                txtPrize.text =  track.getTrackPrice()
                itemView.setOnClickListener {
                    onTrackClickedListener.onTrackClicked(track)
                }
            }
        }
    }
}