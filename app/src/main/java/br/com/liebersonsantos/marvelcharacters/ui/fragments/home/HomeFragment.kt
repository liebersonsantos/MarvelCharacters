package br.com.liebersonsantos.marvelcharacters.ui.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import br.com.liebersonsantos.marvelcharacters.R
import br.com.liebersonsantos.marvelcharacters.core.Status
import br.com.liebersonsantos.marvelcharacters.databinding.FragmentHomeBinding
import br.com.liebersonsantos.marvelcharacters.ui.adapter.CharactersAdapter
import br.com.liebersonsantos.marvelcharacters.ui.fragments.home.viewmodel.HomeViewModel
import br.com.liebersonsantos.marvelcharacters.util.apiKey
import br.com.liebersonsantos.marvelcharacters.util.hash
import br.com.liebersonsantos.marvelcharacters.util.ts
import dagger.hilt.android.AndroidEntryPoint

const val DETAIL = "DETAIL"

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var charactersAdapter : CharactersAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAdapter()
        setRecyclerView()
        observeVMEvents()
        viewModel.getCharacters(ts().toLong(), apiKey(), hash())
    }
//binding.progressBar.visibility = if (it.loading == true) View.VISIBLE else View.GONE
    private fun observeVMEvents(){
        viewModel.response.observe(viewLifecycleOwner){
            if (viewLifecycleOwner.lifecycle.currentState != Lifecycle.State.RESUMED) return@observe
            binding.progressBar.visibility = if (it.loading == true) View.VISIBLE else View.GONE
            when(it.status){
                Status.SUCCESS -> {
                    it.data?.let { response ->
                        charactersAdapter.submitList(response.data.results)
                    }
                }
                Status.ERROR -> {

                }
                Status.LOADING -> {

                }
            }
        }
    }

    private fun setAdapter(){
        charactersAdapter = CharactersAdapter { result ->
            findNavController().navigate(R.id.action_homeFragment_to_detailFragment,
            Bundle().apply { putSerializable(DETAIL, result) })
        }
    }

    private fun setRecyclerView(){
        binding.rvHome.run {
            setHasFixedSize(true)
            adapter = charactersAdapter
        }
    }
}