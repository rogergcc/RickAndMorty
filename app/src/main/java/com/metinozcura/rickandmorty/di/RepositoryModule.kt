package com.metinozcura.rickandmorty.di

import com.metinozcura.rickandmorty.data.paging.datasource.CharactersPagingDataSource
import com.metinozcura.rickandmorty.data.repository.character.CharacterRepository
import com.metinozcura.rickandmorty.data.repository.character.CharacterRepositoryImpl
import com.metinozcura.rickandmorty.data.repository.episode.EpisodeRepository
import com.metinozcura.rickandmorty.data.repository.episode.EpisodeRepositoryImpl
import com.metinozcura.rickandmorty.data.repository.location.LocationRepository
import com.metinozcura.rickandmorty.data.repository.location.LocationRepositoryImpl
import com.metinozcura.rickandmorty.data.service.CharacterApi
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

//    @Provides
//    @Singleton
//    fun provideCharactersPagingDataSource(
//        service: CharacterApi): CharactersPagingDataSource {
//        return CharactersPagingDataSource(service)
//    }

    @Binds
    abstract fun bindCharacterRepository(
        characterRepositoryImpl: CharacterRepositoryImpl
    ): CharacterRepository

    @Binds
    abstract fun bindEpisodeRepository(
        episodeRepositoryImpl: EpisodeRepositoryImpl
    ): EpisodeRepository


    @Binds
    abstract fun bindLocationRepository(
        locationRepositoryImpl: LocationRepositoryImpl
    ): LocationRepository
}