package com.example.githubusers.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.githubusers.R
import com.example.githubusers.data.model.Users
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_view.view.*

class Adapter(private var callback: (User: Users) -> Unit) :
    RecyclerView.Adapter<Adapter.MyViewHolder>() {
    var dataGit = listOf<Users>()
    fun setData(data: List<Users>) {
        this.dataGit = data
        notifyDataSetChanged()
    }
    fun addData(listItems: List<Users>) {
        val sizePast = this.dataGit.size
        this.dataGit = this.dataGit + listItems
        val sizeNew = this.dataGit.size
        notifyItemRangeChanged(sizePast, sizeNew)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_view,
                parent,
                false
            ), callback
        )
    }

    override fun getItemCount(): Int = dataGit.count()

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(dataGit[position])
    }

    class MyViewHolder(itemView: View, private var callback: (User: Users) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        var TextViewLogin: TextView = itemView.textViewLogin
        var TextViewURL: TextView = itemView.textViewURl
        var ImageView: ImageView = itemView.imageView
        fun bind(Users: Users) {
            Picasso.get()
                .load(Users.avatar_url)
                .into(ImageView)

            TextViewLogin.text = Users.login
            TextViewURL.text = Users.url
            itemView.setOnClickListener {
                callback.invoke(Users)
            }
        }

    }
}