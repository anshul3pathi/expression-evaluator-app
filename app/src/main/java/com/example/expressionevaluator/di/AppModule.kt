package com.example.expressionevaluator.di

import com.example.expressionevaluator.data.remote.EvaluationApi
import com.example.expressionevaluator.data.repository.EvaluationRepositoryImpl
import com.example.expressionevaluator.domain.repository.EvaluationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideExpressionEvaluationApi(client: OkHttpClient): EvaluationApi {
        return Retrofit.Builder()
            .baseUrl(EvaluationApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideEvaluationRepository(api: EvaluationApi): EvaluationRepository {
        return EvaluationRepositoryImpl(api)
    }
}