package com.yannickpulver.wonderfluff.domain

import androidx.annotation.DrawableRes

data class Puppy(val id: Int, @DrawableRes val imageRes: Int, val name: String, val barkability: Float, val cuddliness: Float, val guardability: Float, val pawfulFact: String)