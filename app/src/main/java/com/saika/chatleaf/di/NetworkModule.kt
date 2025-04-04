package com.saika.chatleaf.di

import com.saika.chatleaf.BuildConfig
import com.saika.chatleaf.data.remote.api.ChatApiService
import com.saika.chatleaf.data.repository.ChatRepositoryImpl
import com.saika.chatleaf.domain.repository.ChatRepository
import com.saika.chatleaf.domain.usecase.ChatUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = BuildConfig.BASE_URL

    @Provides
    fun provideAuthInterceptor(): Interceptor = Interceptor { chain ->
        val request = chain.request().newBuilder()
            .addHeader("Content-Type", "application/json")
            .build()


        chain.proceed(request)
    }

    @Provides
    fun provideOkHttpClient(authInterceptor: Interceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .build()

    @Provides
    @Singleton
    fun provideCharacterApiService(): ChatApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ChatApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideChatRepository(apiService: ChatApiService): ChatRepository {
        return ChatRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideCharacterUseCase(repository: ChatRepository): ChatUseCase {
        return ChatUseCase(repository)
    }

//        @Provides
//        @Singleton
//        fun provideCharacterDetailUseCase(repository: CharacterRepository): CharacterDetailUseCase {
//            return CharacterDetailUseCase(repository)
//        }
//
//        @Provides
//        fun provideDispatcher(): CoroutineDispatcher = Dispatchers.IO

}