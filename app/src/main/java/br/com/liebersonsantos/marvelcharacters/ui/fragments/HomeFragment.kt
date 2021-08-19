package br.com.liebersonsantos.marvelcharacters.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import br.com.liebersonsantos.marvelcharacters.core.Status
import br.com.liebersonsantos.marvelcharacters.databinding.FragmentHomeBinding
import br.com.liebersonsantos.marvelcharacters.ui.adapter.CharactersAdapter
import br.com.liebersonsantos.marvelcharacters.ui.viewmodel.CharactersViewModel
import br.com.liebersonsantos.marvelcharacters.util.apiKey
import br.com.liebersonsantos.marvelcharacters.util.hash
import br.com.liebersonsantos.marvelcharacters.util.ts
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding
    private val viewModel: CharactersViewModel by viewModels()
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

    private fun observeVMEvents(){
        viewModel.response.observe(viewLifecycleOwner){
            when(it.status){
                Status.SUCCESS -> {
                    it.data?.let { response ->
                        charactersAdapter.submitList(response.data.results)
                    }
                }
                Status.ERROR -> {

                }
                Status.LOADING -> {}
            }
        }
    }

    private fun setAdapter(){
        charactersAdapter = CharactersAdapter {
            Toast.makeText(activity, it.name, Toast.LENGTH_SHORT).show()
        }
    }

    private fun setRecyclerView(){
        binding.rvHome.run {
            setHasFixedSize(true)
            adapter = charactersAdapter
        }
    }
}