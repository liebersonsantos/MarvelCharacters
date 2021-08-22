package br.com.liebersonsantos.marvelcharacters.ui.fragments.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import br.com.liebersonsantos.marvelcharacters.R
import br.com.liebersonsantos.marvelcharacters.core.Status
import br.com.liebersonsantos.marvelcharacters.databinding.FragmentFavoritesBinding
import br.com.liebersonsantos.marvelcharacters.ui.activity.HomeActivity
import br.com.liebersonsantos.marvelcharacters.ui.adapter.FavoritesAdapter
import br.com.liebersonsantos.marvelcharacters.ui.fragments.favorites.viewmodel.FavoritesViewModel
import br.com.liebersonsantos.marvelcharacters.util.ConfirmDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment() {
    private lateinit var binding: FragmentFavoritesBinding
    private val viewModel: FavoritesViewModel by viewModels()
    private lateinit var favoritesAdapter: FavoritesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        setRecyclerView()
        observeVMEvents()

    }

    private fun observeVMEvents() {
        viewModel.getCharacters().observe(viewLifecycleOwner){ results ->
            when{
                results.isNotEmpty() -> favoritesAdapter.submitList(results)
                else -> {
                    favoritesAdapter.submitList(results)
                    message(binding.rvFavorites, "Não há personagens favoritados.")
                }
            }
        }

        viewModel.delete.observe(viewLifecycleOwner){
            if (viewLifecycleOwner.lifecycle.currentState != Lifecycle.State.RESUMED) return@observe
            when(it.status) {
                Status.SUCCESS -> {
                    message(binding.rvFavorites, getString(R.string.exclude_success))
                }
                Status.ERROR -> {

                }
                Status.LOADING -> {}
            }
        }
    }

    private fun setRecyclerView() {
        binding.rvFavorites.run {
            setHasFixedSize(true)
            adapter = favoritesAdapter
        }
    }

    private fun setAdapter() {
        favoritesAdapter = FavoritesAdapter { result ->
            ConfirmDialog().apply {
                setYesListener {
                    viewModel.deleteCharacters(result)
                }
            }.show(parentFragmentManager, TAG)
        }
    }

    private fun message(view: View, message: String) {
        (activity as HomeActivity).showMessage(view, message)
    }

    companion object{
        var TAG: String = FavoritesFragment::class.java.simpleName
    }

}