package com.example.android.vinylsappg21.ui.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.android.vinylsappg21.R
import com.example.android.vinylsappg21.databinding.CollectorItemBinding
import com.example.android.vinylsappg21.models.Collector
import com.example.android.vinylsappg21.ui.collectors.CollectorDetailActivity

class CollectorsAdapter : RecyclerView.Adapter<CollectorsAdapter.CollectorsViewHolder>(){

    var collectors :List<Collector> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectorsViewHolder {
        val withDataBinding: CollectorItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            CollectorsViewHolder.LAYOUT,
            parent,
            false)
        return CollectorsViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: CollectorsViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.collector = collectors[position]
        }

        holder.itemView.setOnClickListener { v ->
            val activity = v!!.context as AppCompatActivity;
            activity?.let{
                val intent = Intent (it, CollectorDetailActivity::class.java)
                intent.putExtra("collector_name", collectors[position].name)
                intent.putExtra("collector_tel", collectors[position].telephone)
                intent.putExtra("collector_email", collectors[position].email)
                intent.putStringArrayListExtra("collector_albums", arrayListOf(*collectors[position].collectorAlbums))
                it.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int {
        return collectors.size
    }

    class CollectorsViewHolder(val viewDataBinding: CollectorItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.collector_item
        }
    }


}