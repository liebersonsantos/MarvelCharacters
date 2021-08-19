package br.com.liebersonsantos.marvelcharacters.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import br.com.liebersonsantos.marvelcharacters.core.Status
import br.com.liebersonsantos.marvelcharacters.databinding.ActivityHomeBinding
import br.com.liebersonsantos.marvelcharacters.ui.viewmodel.CharactersViewModel
import br.com.liebersonsantos.marvelcharacters.util.apiKey
import br.com.liebersonsantos.marvelcharacters.util.hash
import br.com.liebersonsantos.marvelcharacters.util.ts
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val viewModel: CharactersViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

}