package org.example.project.di


import org.example.project.data.remote.SpikerClient
import org.example.project.data.remote.YoutubeClient
import org.example.project.domain.repository.SpikerRepository
import org.example.project.domain.repository.YoutubeRepository
import org.example.project.data.repository.SpikerRepositoryImpl
import org.example.project.data.repository.YoutubeRepositoryImpl
import org.example.project.domain.usecases.FetchTranscriptUseCase
import org.example.project.domain.usecases.SearchYoutubeVideosUseCase
import org.koin.dsl.module
import org.example.project.app.constants.Constants
import org.example.project.data.remote.HttpClientEngine
import org.example.project.domain.usecases.GetSearchSuggestions
import org.example.project.domain.usecases.YoutubeUseCases
import org.example.project.presentation.home.HomeViewModel
import org.example.project.presentation.summarize.SummarizeVideoViewModel

val commonModule = module {
    // Repository
    single<YoutubeRepository> { YoutubeRepositoryImpl(get()) }
    single<SpikerRepository> { SpikerRepositoryImpl(get()) }

    // Usecase
    single {
        YoutubeUseCases(
            SearchYoutubeVideosUseCase(get()),
            GetSearchSuggestions(get())
        )
    }
    single { FetchTranscriptUseCase(get()) }

    // Client
    val httpClient = HttpClientEngine().create()
    single { YoutubeClient(apiKey = Constants.Youtube.YOUTUBE_DATA_KEY, httpClient = httpClient) }
    single { SpikerClient(httpClient = httpClient) }

    // Viewmodel
    single {
        HomeViewModel(get())
    }

    factory {
        SummarizeVideoViewModel(
            fetchTranscriptUseCase = get()
        )
    }
}