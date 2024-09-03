package com.project.deteksimata.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ArticleViewPagerModel(
    val id: Int,
    val title: String,
    val content: String,
    val img : String,
) : Parcelable
