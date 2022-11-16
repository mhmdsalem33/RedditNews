package com.example.redditnews.domain.models

data class Data(
    val after: String,
    val before: Any,
    val children: List<Children>,
    val dist: Int,
    val geo_filter: Any,
    val modhash: String
)