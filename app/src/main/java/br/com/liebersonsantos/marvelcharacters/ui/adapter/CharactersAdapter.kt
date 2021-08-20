package br.com.liebersonsantos.marvelcharacters.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.liebersonsantos.marvelcharacters.databinding.ItemBinding
import br.com.liebersonsantos.marvelcharacters.domain.model.Results
import com.bumptech.glide.Glide

/**
 * Created by lieberson on 8/19/21.
 * @author lieberson.xsantos@gmail.com
 */
class CharactersAdapter(private val itemClick: ((result: Results) -> Unit)) :
    ListAdapter<Results, CharactersAdapter.AdapterViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder {
        val itemBinding = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AdapterViewHolder(itemBinding, itemClick)
    }

    override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class AdapterViewHolder(
        private val itemBinding: ItemBinding,
        private val itemClick: (result: Results) -> Unit
    ) :
        RecyclerView.ViewHolder(itemBinding.root) {


        fun bind(results: Results) {
            itemBinding.run {
                Glide.with(itemView)
                    .load("${results.thumbnail.path}.${results.thumbnail.extension}")
                    .fitCenter()
                    .into(imgPoster)

                txtTitle.text = results.name
                itemView.setOnClickListener {
                    itemClick.invoke(results)
                }

            }

        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Results>() {
            override fun areItemsTheSame(oldItem: Results, newItem: Results): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Results, newItem: Results): Boolean {
                return oldItem == newItem
            }

        }
    }

}