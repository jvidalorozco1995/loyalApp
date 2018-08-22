package com.groupsoft.loyalapp.adapters

/**
 * Created by JORGE-PC on 12/08/2018.
 */


import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.groupsoft.loyalapp.R
import com.groupsoft.loyalapp.models.Store

class StoreAdapter(val userList: ArrayList<Store>): RecyclerView.Adapter<StoreAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //holder?.tvName?.text = userList[position].nameStore
        //holder?.tvDescription?.text = userList[position].descriptionStore
        holder?.ivLogo?.setImageDrawable(userList[position].image)
        holder?.tvNameStore?.text = userList[position].nameStore

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.store_item, parent, false)
        return ViewHolder(v)
    }


    override fun getItemCount(): Int {
        return userList.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
       // valtvName = itemView.findViewById<TextView>(R.id.store_name)
     //   val tvDescription = itemView.findViewById<TextView>(R.id.store_description)
        val ivLogo = itemView.findViewById<ImageView>(R.id.ivLogoStore)
        val tvNameStore = itemView.findViewById<TextView>(R.id.tvNameStore)
    }

}