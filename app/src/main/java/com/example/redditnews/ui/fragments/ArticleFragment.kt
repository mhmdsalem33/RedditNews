package com.example.redditnews.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.example.redditnews.R
import com.example.redditnews.ui.activites.MainActivity
import com.example.redditnews.databinding.FragmentArticleBinding

class ArticleFragment : Fragment() {

    private lateinit var binding   : FragmentArticleBinding
    private var title     : String ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentArticleBinding.inflate(inflater , container , false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fetchArticleInformation()
        onHandleOnBackPressed()
        setArticleInformationInViews()

    }

    private fun setArticleInformationInViews() {
        binding.articleTitle.text = title
    }

    private fun fetchArticleInformation() {
        val data  = arguments
        if (data != null)
        {
             title = data.getString("title").toString()
            (activity as MainActivity).supportActionBar?.title = title
        }
    }

    private fun onHandleOnBackPressed() {
        val callback = object : OnBackPressedCallback(true)
        {
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.action_articleFragment_to_homeFragment)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner , callback)
    }

}