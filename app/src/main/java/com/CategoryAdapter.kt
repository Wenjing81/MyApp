package com

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.r.myapp.R
import kotlinx.android.synthetic.main.guess_item.view.*

class CategoryAdapter(
    private val exampleList: List<CategoryItem>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<CategoryAdapter.GuessViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuessViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.guess_item, parent, false)

        return GuessViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: GuessViewHolder, position: Int) {
        val currentItem = exampleList[position]

        holder.imageView.setImageResource(currentItem.imageResource)
        holder.textView1.text = currentItem.type
        holder.textView2.text = currentItem.status
    }

    override fun getItemCount() = exampleList.size
    inner class GuessViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        val imageView: ImageView = itemView.image_view
        val textView1: TextView = itemView.text_view_1
        val textView2: TextView = itemView.text_view_2

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }

        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}