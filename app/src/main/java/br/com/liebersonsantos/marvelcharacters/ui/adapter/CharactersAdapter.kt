package br.com.liebersonsantos.marvelcharacters.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import br.com.liebersonsantos.marvelcharacters.databinding.ItemBinding
import br.com.liebersonsantos.marvelcharacters.domain.model.Results
import com.bumptech.glide.Glide
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by lieberson on 8/19/21.
 * @author lieberson.xsantos@gmail.com
 */
class CharactersAdapter(
    results: MutableList<Results>,
    private val itemClick: ((result: Results) -> Unit),
    private val longClick: ((result: Results) -> Unit)
) : RecyclerView.Adapter<CharactersAdapter.AdapterViewHolder>(), Filterable {

    private val dataSet: MutableList<Results> = results
    private val fullList: ArrayList<Results> = ArrayList(results)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder {
        val itemBinding = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AdapterViewHolder(itemBinding, itemClick, longClick)
    }

    override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {
        holder.bind(dataSet[position])
    }

    override fun getItemCount(): Int = dataSet.count()

    class AdapterViewHolder(
        private val itemBinding: ItemBinding,
        private val itemClick: (result: Results) -> Unit,
        private val longClick: (result: Results) -> Unit) :
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

                itemView.setOnLongClickListener {
                    longClick.invoke(results)
                    layout.setBackgroundColor(Color.parseColor("#E9CF1A1D"))
                    return@setOnLongClickListener true
                }

            }

        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filteredList: ArrayList<Results> = ArrayList()
                if (constraint == null || constraint.count() == 0) {
                    filteredList.addAll(fullList)
                } else {
                    val filterPattern = constraint.toString().toLowerCase(Locale.ROOT).trim()
                    for (item in fullList) {
                        if (item.name.toLowerCase(Locale.ROOT).contains(
                                filterPattern
                            )
                        ) {
                            filteredList.add(item)
                        }
                    }
                }
                val results = FilterResults()
                results.values = filteredList
                return results
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                dataSet.clear()
                dataSet.addAll(results?.values as Collection<Results>)
                notifyDataSetChanged()
            }

        }
    }
}