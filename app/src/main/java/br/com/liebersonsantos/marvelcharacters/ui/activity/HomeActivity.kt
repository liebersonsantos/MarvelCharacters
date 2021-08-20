package br.com.liebersonsantos.marvelcharacters.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.liebersonsantos.marvelcharacters.databinding.ActivityHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}