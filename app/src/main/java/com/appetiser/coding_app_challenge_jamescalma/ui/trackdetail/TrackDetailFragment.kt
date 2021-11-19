package com.appetiser.coding_app_challenge_jamescalma.ui.trackdetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.appetiser.coding_app_challenge_jamescalma.R
import com.appetiser.coding_app_challenge_jamescalma.databinding.FragmentTrackDetailBinding
import com.appetiser.coding_app_challenge_jamescalma.extensions.loadItemImage
import com.mcxiaoke.koi.ext.toast

class TrackDetailFragment : Fragment() {
    private lateinit var binding: FragmentTrackDetailBinding
    private val args: TrackDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTrackDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            val track = args.track
            context?.getDrawable(R.drawable.music_placeholder)?.let {
                imgTrack.loadItemImage(args.track.artworkUrl100, it)
            }
            txtTrackName.text = track.getValidTrackName()
            txtArtistName.text = track.artistName
            txtGenre.text = track.primaryGenreName
            txtDescription.text = track.getValidDescription()
            btnPrice.text = track.getTrackPrice()
            imgBack.setOnClickListener { activity?.onBackPressed() }
            btnPrice.setOnClickListener { toast("${track.getValidTrackName()} added to your cart for ${track.getTrackPrice()}") }
        }
    }
}