package com.example.pr3

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.pr3.databinding.WordItemBinding
import model.Word

class WordAdapter(): RecyclerView.Adapter<WordAdapter.WordViewHolder>(){
    var wordList = mutableListOf<Word>()
        set(newList){
            val utils = WordListDiffCallBack(wordList, newList)
            val diffRes = DiffUtil.calculateDiff(utils)
            field = newList
            diffRes.dispatchUpdatesTo(this)
        }

    private var onItemClickListener: ((Int) -> Unit)? = null
    fun attachOnItemClickListener(listener:(Int) -> Unit){
        onItemClickListener = listener
    }

    inner class WordViewHolder(val binding: WordItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bindView(word: Word){
            binding.apply{
                tvWordItem.text = word.word_eng
                root.setOnClickListener {
                    onItemClickListener?.let{ it(bindingAdapterPosition) }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = WordItemBinding.inflate(inflater, parent, false)
        return WordViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        holder.bindView(wordList[position])
    }

    override fun getItemCount(): Int = wordList.size
}