package com.workload.inc.expensetracker.ui.language

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.workload.inc.expensetracker.databinding.LanguageItemBinding
import com.workload.inc.expensetracker.model.LanguageModel
import com.workload.inc.expensetracker.utils.setSafeOnClickListener

class LanguageAdapter(
    private val languages: List<LanguageModel>,
    private var selectedItemPosition: Int = -1,
    private val onLanguageSelected: (item: LanguageModel, position: Int) -> Unit
) : RecyclerView.Adapter<LanguageAdapter.LanguageViewHolder>() {

    class LanguageViewHolder(val binding: LanguageItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LanguageViewHolder {
        val binding = LanguageItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return LanguageViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: LanguageViewHolder,
        position: Int
    ) {
        val language = languages[position]
        holder.binding.languageTV.text = language.languageName
        if (position == selectedItemPosition) {
            holder.binding.languageRB.isChecked = true
        } else {
            holder.binding.languageRB.isChecked = false
        }
        holder.binding.root.setSafeOnClickListener {
            holder.binding.languageRB.isChecked = true
            onLanguageSelected(language, position)
        }
    }

    override fun getItemCount(): Int {
        return languages.size
    }

    fun notifyChange(selectedItemIndex : Int) {
        selectedItemPosition = selectedItemIndex
        notifyDataSetChanged()
    }

}