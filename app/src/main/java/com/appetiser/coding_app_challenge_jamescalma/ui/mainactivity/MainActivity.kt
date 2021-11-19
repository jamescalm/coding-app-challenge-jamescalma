package com.appetiser.coding_app_challenge_jamescalma.ui.mainactivity


import android.R
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.appetiser.coding_app_challenge_jamescalma.databinding.ActivityMainBinding
import com.appetiser.coding_app_challenge_jamescalma.ui.tracklist.TrackListFragment


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}