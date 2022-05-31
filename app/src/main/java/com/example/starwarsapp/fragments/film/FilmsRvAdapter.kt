package com.example.starwarsapp.fragments.film

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarsapp.R
import com.example.starwarsapp.db.film.Film
import java.util.*

class FilmsRvAdapter : RecyclerView.Adapter<FilmsRvAdapter.ViewHolder>(), Filterable {
    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    private var clickListener: OnItemClickListener? = null
    private var films = listOf<Film>()
    private var filteredFilms = films

    fun setOnClickListener(listener: OnItemClickListener) {
        clickListener = listener
    }

    fun updateList(films: List<Film>) {
        filteredFilms = films.sortedBy { it.episodeId }
        this.films = filteredFilms
        notifyDataSetChanged()
    }

    fun getFilmByPosition(position: Int) =
        filteredFilms[position]

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val request = constraint.toString()
                if (request.isEmpty()) {
                    filteredFilms = films
                } else {
                    filteredFilms = films.filter { it.title.lowercase(Locale.ROOT).contains(request.lowercase(Locale.ROOT)) }
                }
                val filterResults = FilterResults()
                filterResults.values = filteredFilms
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                results ?: return
                filteredFilms = results.values as List<Film>
                notifyDataSetChanged()
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.film_item, parent, false)
        return ViewHolder(view, clickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(filteredFilms[position])
    }

    override fun getItemCount() =
        filteredFilms.size

    class ViewHolder(itemView: View, listener: OnItemClickListener?) : RecyclerView.ViewHolder(itemView) {
        init {
            if (listener != null)
                itemView.setOnClickListener { listener.onItemClick(absoluteAdapterPosition) }
        }

        fun bind(value : Film) {
            val tvTitle = itemView.findViewById<TextView>(R.id.filmTitleTv)
            val tvDirector = itemView.findViewById<TextView>(R.id.bithDateTv)
            val tvProducer = itemView.findViewById<TextView>(R.id.producerTv)
            val tvReleaseDate = itemView.findViewById<TextView>(R.id.sexTv)
            tvTitle.text = value.title
            tvDirector.text = value.director
            tvProducer.text = value.producer
            tvReleaseDate.text = value.releaseDate
        }
    }
}