package br.com.liebersonsantos.marvelcharacters.ui.fragments.detail

import android.graphics.drawable.Drawable
import android.graphics.drawable.LayerDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import br.com.liebersonsantos.marvelcharacters.R
import br.com.liebersonsantos.marvelcharacters.databinding.FragmentDetailBinding
import br.com.liebersonsantos.marvelcharacters.domain.model.Results
import br.com.liebersonsantos.marvelcharacters.ui.fragments.detail.viewmodel.DetailViewModel
import br.com.liebersonsantos.marvelcharacters.ui.fragments.home.DETAIL
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
//    private val viewModel: DetailViewModel by viewModels()
    private lateinit var detail: Results

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detail = arguments?.getSerializable(DETAIL) as Results

        setToolbar()
        back()
        fillDataDetail(detail)




    }

    private fun setToolbar() {
        val activity = (activity as AppCompatActivity)
        activity.run {
            setSupportActionBar(binding.toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_white_24)
            supportActionBar?.title = ""
        }
    }

    private fun back() {
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }


    private fun fillDataDetail(results: Results) {
        binding.run {
            setImage(results, imgPoster)
            setImage(results, imgMiniPoster)
            txtTitle.text = results.name
            txtDescription.text = results.description
        }

    }

    private fun FragmentDetailBinding.setImage(results: Results, image: ImageView)
    = Glide.with(this@DetailFragment)
        .load("${results.thumbnail.path}.${results.thumbnail.extension}")
        .fitCenter()
        .into(image)
}