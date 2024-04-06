package com.example.proj2.adapter

import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.proj2.model.Celebrity
import com.example.proj2.databinding.ItemCelebrityBinding

class CelebrityAdapter : ListAdapter<Celebrity, CelebrityAdapter.CelebrityViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CelebrityViewHolder {
        val binding = ItemCelebrityBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CelebrityViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CelebrityViewHolder, position: Int) {
        val celebrity = getItem(position)
        holder.bind(celebrity)
    }

    class CelebrityViewHolder(private val binding: ItemCelebrityBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(celebrity: Celebrity) {
            binding.tvName.text = celebrity.name.uppercase()
            binding.tvProfession.text = Html.fromHtml("<b>Occupation:</b> ${celebrity.occupation?.get(0)}")
            binding.tvDob.text = Html.fromHtml("<b>Date of Birth:</b> ${celebrity.birthday}")
            binding.tvNationality.text = Html.fromHtml("<b>Nationality:</b> ${celebrity.nationality}")
            binding.tvGender.text = Html.fromHtml("<b>Gender:</b> ${celebrity.gender}")
            binding.tvNetWorth.text = Html.fromHtml("<b>NetWorth:</b> ${celebrity.net_worth}")
            binding.tvHeight.text = Html.fromHtml("<b>Height:</b> ${celebrity.height}")
        }

    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Celebrity>() {
            override fun areItemsTheSame(oldItem: Celebrity, newItem: Celebrity): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: Celebrity, newItem: Celebrity): Boolean {
                return oldItem == newItem
            }
        }
    }
}
