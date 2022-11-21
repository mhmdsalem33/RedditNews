package com.example.redditnews.ui.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.redditnews.databinding.ArticleRowBinding
import com.example.redditnews.databinding.FavoriteRowBinding
import com.example.redditnews.domain.models.Article
import com.example.redditnews.domain.models.ArticleFavorite
import com.example.redditnews.ui.activites.MainActivity
import com.example.redditnews.ui.viewmodels.HomeViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class FavoriteAdapter () :RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    lateinit var onItemFavoriteCLick       : ( ( ArticleFavorite ) -> Unit )
    lateinit var onDeleteFavoriteItemClick : ( ( ArticleFavorite ) -> Unit )

   private val diffUtilCallback = object :DiffUtil.ItemCallback<ArticleFavorite>() {
      override fun areItemsTheSame(oldItem: ArticleFavorite, newItem: ArticleFavorite): Boolean {
         return oldItem.id == newItem.id
      }
      override fun areContentsTheSame(oldItem: ArticleFavorite, newItem: ArticleFavorite): Boolean {
        return  oldItem == newItem
      }
   }
   val differ = AsyncListDiffer(this , diffUtilCallback)

   inner class ViewHolder (  val binding : FavoriteRowBinding) :RecyclerView.ViewHolder(binding.root)

   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
      return ViewHolder(FavoriteRowBinding.inflate(LayoutInflater.from(parent.context) , parent , false))
   }
   override fun onBindViewHolder(holder: ViewHolder, position: Int) {

     val data = differ.currentList[position]
         holder.binding.tvTitle.text = data.title

      Glide.with(holder.itemView).load(data.thumbnail).into(holder.binding.articleImage)


       holder.itemView.setOnClickListener {
           onItemFavoriteCLick.invoke(data)
       }

       holder.binding.deleteFavoriteArticles.setOnClickListener {
           onDeleteFavoriteItemClick.invoke(data)
       }


   }
   override fun getItemCount()  = differ.currentList.size
}