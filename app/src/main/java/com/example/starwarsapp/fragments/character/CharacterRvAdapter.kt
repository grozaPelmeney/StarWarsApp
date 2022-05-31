package com.example.starwarsapp.fragments.character

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarsapp.R
import com.example.starwarsapp.db.character.Character

class CharacterRvAdapter : RecyclerView.Adapter<CharacterRvAdapter.ViewHolder>() {
    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    private var clickListener: OnItemClickListener? = null
    private var characters = listOf<Character>()

    fun setOnClickListener(listener: OnItemClickListener) {
        clickListener = listener
    }

    fun updateList(characters: List<Character>) {
        this.characters = characters
    }

    fun getPlanetUrlByCharacterPosition(position: Int) =
        characters[position].planetUrl

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.character_item, parent, false)
        return ViewHolder(view, clickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(characters[position])
    }

    override fun getItemCount() =
        characters.size

    class ViewHolder(itemView: View, listener: OnItemClickListener?) : RecyclerView.ViewHolder(itemView) {
        init {
            if (listener != null)
                itemView.setOnClickListener { listener.onItemClick(absoluteAdapterPosition) }
        }

        fun bind(value: Character) {
            val tvName = itemView.findViewById<TextView>(R.id.characterNameTv)
            val tvBith = itemView.findViewById<TextView>(R.id.bithDateTv)
            val tvSex = itemView.findViewById<TextView>(R.id.sexTv)
            tvName.text = value.name
            tvBith.text = value.birthYear
            tvSex.text = value.sex
        }
    }
}