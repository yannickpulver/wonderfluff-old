package com.yannickpulver.wonderfluff.domain

import kotlinx.coroutines.flow.Flow

interface PuppyRepository {
    fun getPuppies(): List<Puppy>
    fun getPuppy(id: Int): Puppy?
}