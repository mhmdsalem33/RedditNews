package com.example.redditnews.ui.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.redditnews.databinding.ArticleRowBinding
import com.example.redditnews.domain.models.Article



class ArticlesAdapter () :RecyclerView.Adapter<ArticlesAdapter.ViewHolder>() {

   lateinit var onItemArticleClick : (( Article ) -> Unit )

   private val diffUtilCallback = object :DiffUtil.ItemCallback<Article>() {
      override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
         return oldItem.id == newItem.id
      }
      override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        return  oldItem == newItem
      }
   }
   val differ = AsyncListDiffer(this , diffUtilCallback)

   inner class ViewHolder (  val binding : ArticleRowBinding) :RecyclerView.ViewHolder(binding.root)

   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
      return ViewHolder(ArticleRowBinding.inflate(LayoutInflater.from(parent.context) , parent , false))
   }
   override fun onBindViewHolder(holder: ViewHolder, position: Int) {

     val data = differ.currentList[position]
         holder.binding.tvTitle.text = data.title

      Glide.with(holder.itemView).load(data.thumbnail).into(holder.binding.articleImage)

      holder.itemView.setOnClickListener {
          onItemArticleClick.invoke(data)
      }
   }
   override fun getItemCount()  = differ.currentList.size
}