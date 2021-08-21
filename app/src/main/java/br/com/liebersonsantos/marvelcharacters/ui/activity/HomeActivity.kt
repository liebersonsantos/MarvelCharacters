package br.com.liebersonsantos.marvelcharacters.ui.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import br.com.liebersonsantos.marvelcharacters.databinding.ActivityHomeBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    fun showMessage(view: View, message: String) =
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show()
}