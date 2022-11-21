package com.example.redditnews.ui.adapters
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.redditnews.data.realmdb.ArticleDatabaseExplorer
import com.example.redditnews.databinding.ArticleRowBinding
class ArticlesExplorerAdapter ( private val articleListExplorer: List<ArticleDatabaseExplorer>) :RecyclerView.Adapter<ArticlesExplorerAdapter.ViewHolder>() {

    lateinit var onArticleExploreClick : ((ArticleDatabaseExplorer) -> Unit)
    lateinit var onArticleFavoriteExploreClick :((ArticleDatabaseExplorer) -> Unit)
    inner class ViewHolder (  val binding : ArticleRowBinding) :RecyclerView.ViewHolder(binding.root)

   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
      return ViewHolder(ArticleRowBinding.inflate(LayoutInflater.from(parent.context) , parent , false))
   }
   override fun onBindViewHolder(holder: ViewHolder, position: Int) {

      val article = articleListExplorer[position]

      holder.binding.tvTitle.text = article.title

      Glide.with(holder.itemView).load(article.thumbnail).into(holder.binding.articleImage)

       holder.itemView.setOnClickListener {
          onArticleExploreClick.invoke(article)
       }


       holder.binding.addFav.setOnClickListener {
           onArticleFavoriteExploreClick.invoke(article)
       }

   }
   override fun getItemCount()  = articleListExplorer.size
}