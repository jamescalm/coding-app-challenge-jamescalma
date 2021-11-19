package com.appetiser.coding_app_challenge_jamescalma.ui.tracklist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.appetiser.coding_app_challenge_jamescalma.TrackListApp
import com.appetiser.coding_app_challenge_jamescalma.ViewModelFactory
import com.appetiser.coding_app_challenge_jamescalma.api.TrackHttp
import com.appetiser.coding_app_challenge_jamescalma.databinding.FragmentTrackListBinding
import com.appetiser.coding_app_challenge_jamescalma.model.Constants
import com.appetiser.coding_app_challenge_jamescalma.model.Track
import com.appetiser.coding_app_challenge_jamescalma.ui.tracklist.adapter.TrackAdapter
import com.appetiser.coding_app_challenge_jamescalma.util.Status
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


class TrackListFragment : Fragment(), TrackAdapter.OnTrackClickListener {
    private lateinit var binding: FragmentTrackListBinding

    @Inject
    lateinit var api: TrackHttp

    lateinit var factory: ViewModelFactory

    lateinit var viewModel: TrackListViewModel

    private var trackAdapter: TrackAdapter? = null
    private var recentListAdapter: TrackAdapter? = null

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
        factory = ViewModelFactory(api)
        viewModel = ViewModelProvider(this, factory).get(TrackListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentTrackListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getTrackList()
        setupObservers()
    }

    override fun onResume() {
        super.onResume()
        binding.trackListRV.adapter = trackAdapter
        binding.recentRV.adapter = recentListAdapter
    }

    /** This function will observe the live data from the viewModel*/
    private fun setupObservers(){
        viewModel.trackList.observe(viewLifecycleOwner, {
            if (it.results != null) {
                initTrackAdapter(it.results!!)
                initRecentList(it.results!!)
            }
        })
        viewModel.unique.observe(viewLifecycleOwner, {
            if (it != null) {
                viewModel.trackList.value?.results?.let { tracks -> initRecentList(tracks) }
            }
        })

        viewModel.trackListLoader.observe(viewLifecycleOwner, {
            if (it.status == Status.LOADING) {
                binding.mainLayout.isGone = true
                binding.loader.isVisible = true
            }else{
                binding.mainLayout.isVisible = true
                binding.loader.isGone = true
            }
        })
    }

    /** This function sets up the Tracker Adapter(full list of tracks)*/
    private fun initTrackAdapter(tracks: List<Track>){
        if(trackAdapter == null) {
            trackAdapter = context?.let { TrackAdapter(it, tracks, this) }
            binding.trackListRV.adapter = trackAdapter
        } else {
            trackAdapter?.trackList = tracks
            trackAdapter?.notifyDataSetChanged()
        }
    }

    /** This function sets up the recentList Adapter(Persistence function)*/
    private fun initRecentList(tracks: List<Track>){
        var recentTrack = TrackListApp.sharedPreferences?.getString(Constants.PREF_RECENT_TRACKS,"")
        var recentCollectionId = TrackListApp.sharedPreferences?.getString(Constants.PREF_RECENT_TRACKS_COLLECTION,"")

        if(recentTrack.isNullOrEmpty() && recentCollectionId.isNullOrEmpty()) binding.txtRecent.isGone = true
        else binding.txtRecent.isVisible = true

        var filteredTracks = tracks.filter {
            recentTrack == it.trackId.toString() && recentCollectionId == it.collectionId.toString()
        }

        if(recentListAdapter == null) {
            recentListAdapter = context?.let { TrackAdapter(it, filteredTracks, this) }
            binding.recentRV.adapter = recentListAdapter
        } else {
            recentListAdapter?.trackList = filteredTracks
            recentListAdapter?.notifyDataSetChanged()
        }
    }

    /** This function is the click listener of the TrackAdapter when the user taps on a single adapter
     * It also calls the functions that save the trackId and collectionId for the Persistence Function*/
    override fun onTrackClicked(track: Track) {
        viewModel.saveRecentTracks(track.trackId.toString())
        viewModel.saveRecentTrackCollection(track.collectionId.toString())
        findNavController().navigate(
            TrackListFragmentDirections.actionTrackListFragmentToTrackDetailFragment(track)
        )
    }

}