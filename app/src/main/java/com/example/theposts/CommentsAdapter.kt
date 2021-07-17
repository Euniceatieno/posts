package com.example.theposts

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class CommentsAdapter(var commentContent: List<Comment>):RecyclerView.Adapter<CommentsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsViewHolder {
       var itemView= LayoutInflater.from(parent.context).inflate(R.layout.comments_list_item, parent,false)
        return CommentsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CommentsViewHolder, position: Int) {
        var currentComment=commentContent[position]
        holder.tvComName.text=currentComment.name
        holder.tvComEmail.text=currentComment.email
        holder.tvComBody.text=currentComment.body

    }

    override fun getItemCount(): Int {
       return commentContent.size
    }
}
class CommentsViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
    var tvComName=itemView.findViewById<TextView>(R.id.tvComName)
    var tvComEmail=itemView.findViewById<TextView>(R.id.tvComEmail)
    var tvComBody=itemView.findViewById<TextView>(R.id.tvComBody)


}