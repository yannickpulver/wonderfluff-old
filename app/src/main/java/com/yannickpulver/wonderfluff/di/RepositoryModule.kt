package com.yannickpulver.wonderfluff.di

import com.yannickpulver.wonderfluff.data.PuppyRepositoryImpl
import com.yannickpulver.wonderfluff.domain.PuppyRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindPuppyRepository(puppyRepositoryImpl: PuppyRepositoryImpl): PuppyRepository

}
