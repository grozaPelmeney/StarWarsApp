package com.example.starwarsapp.fragments.film

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarsapp.R
import com.example.starwarsapp.db.film.Film

class FilmsRvAdapter : RecyclerView.Adapter<FilmsRvAdapter.ViewHolder>() {
    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    private var clickListener: OnItemClickListener? = null
    private var films = listOf<Film>()

    fun setOnClickListener(listener: OnItemClickListener) {
        clickListener = listener
    }

    fun updateList(places: List<Film>) {
        films = places
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.film_item, parent, false)
        return ViewHolder(view, clickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(films[position])
    }

    override fun getItemCount() =
        films.size

    class ViewHolder(itemView: View, listener: OnItemClickListener?) : RecyclerView.ViewHolder(itemView) {
        init {
            if (listener != null)
                itemView.setOnClickListener { listener.onItemClick(absoluteAdapterPosition) }
        }

        fun bind(value : Film) {
            val tvTitle = itemView.findViewById<TextView>(R.id.filmTitleTv)
            val tvDirector = itemView.findViewById<TextView>(R.id.directorTv)
            val tvProducer = itemView.findViewById<TextView>(R.id.producerTv)
            val tvReleaseDate = itemView.findViewById<TextView>(R.id.releaseDateTv)
            tvTitle.text = value.title
            tvDirector.text = value.director
            tvProducer.text = value.producer
            tvReleaseDate.text = value.releaseDate
        }
    }
}