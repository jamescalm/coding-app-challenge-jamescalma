package com.appetiser.coding_app_challenge_jamescalma.ui.mainactivity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.appetiser.coding_app_challenge_jamescalma.R
import com.appetiser.coding_app_challenge_jamescalma.ViewModelFactory
import com.appetiser.coding_app_challenge_jamescalma.api.TrackHttp
import com.appetiser.coding_app_challenge_jamescalma.databinding.ActivityMainBinding
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var api: TrackHttp

    lateinit var factory: ViewModelFactory

    lateinit var mainViewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        factory = ViewModelFactory(api)
        mainViewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)

        mainViewModel.getTrackList()
    }
}