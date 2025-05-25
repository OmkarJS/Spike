package org.example.project.di


import org.example.project.data.remote.KtorClient
import org.example.project.data.remote.YoutubeClient
import org.example.project.domain.repository.ISpikerRepository
import org.example.project.domain.repository.IYoutubeRepository
import org.example.project.domain.repository.SpikerRepositoryImpl
import org.example.project.domain.repository.YoutubeRepositoryImpl
import org.example.project.domain.usecases.FetchTranscriptUseCase
import org.example.project.domain.usecases.SearchYoutubeVideosUseCase
import org.koin.dsl.module
import org.example.project.app.constants.Constants
import org.example.project.presentation.home.HomeViewModel

val commonModule = module {
    single<IYoutubeRepository> { YoutubeRepositoryImpl(get()) }
    single<ISpikerRepository> { SpikerRepositoryImpl(get()) }

    single { SearchYoutubeVideosUseCase(get()) }
    single { FetchTranscriptUseCase(get()) }

    single { YoutubeClient(apiKey = Constants.Spike.YOUTUBE_DATA_KEY) }
    single { KtorClient() }

    factory {
        HomeViewModel(
            searchYoutubeVideosUseCase = get(),
            fetchTranscriptUseCase = get()
        )
    }
}