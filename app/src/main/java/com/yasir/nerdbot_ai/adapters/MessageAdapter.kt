package com.yasir.nerdbot_ai.adapters




import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yasir.nerdbot_ai.R
import com.yasir.nerdbot_ai.models.request.MessageModel


class MessageAdapter(private val list:ArrayList<MessageModel>):RecyclerView.Adapter<MessageAdapter.MessageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageAdapter.MessageViewHolder {
        var view:View? = null
        var from  = LayoutInflater.from(parent.context)
        if(viewType==0){
           view = from.inflate(R.layout.chatrightitem,parent,false)
        }else{
            view = from.inflate(R.layout.chatleftitem,parent,false)
        }
        return MessageViewHolder(view)
    }

    override fun getItemViewType(position: Int): Int {
        val message = list[position]
        return if(message.isUser) 0 else 1
    }

    override fun onBindViewHolder(holder: MessageAdapter.MessageViewHolder, position: Int) {
           val message = list[position]
           if(!message.isUser){
               holder.imageContainer.visibility = View.GONE

           }
           if(message.isImage){
               holder.imageContainer.visibility = View.VISIBLE
               Glide.with(holder.itemView.context).load(message.message).into(holder.image)
           }else{
               holder.msgTxt.text = message.message

           }
    }

    override fun getItemCount(): Int {
        return list.size
    }
    inner class MessageViewHolder(view: View):RecyclerView.ViewHolder(view) {
        val msgTxt = view.findViewById<TextView>(R.id.show_message)
        val imageContainer = view.findViewById<LinearLayout>(R.id.imageCard)
        val image = view.findViewById<ImageView>(R.id.image)


    }
}
