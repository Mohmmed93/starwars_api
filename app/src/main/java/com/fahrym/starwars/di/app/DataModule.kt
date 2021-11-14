package com.fahrym.starwars.di.app

import android.content.Context
import com.fahrym.starwars.data.repository.RepositoryImp
import com.fahrym.starwars.data.source.remote.APIService
import com.fahrym.starwars.di.scope.ForApplication
import com.fahrym.starwars.domain.repository.ConnectivityManager
import com.fahrym.starwars.domain.repository.Repository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
object DataModule {

    @Singleton
    @JvmStatic
    @Provides
    fun provideRepository(api: APIService): Repository {
        return RepositoryImp(api)
    }

    @Singleton
    @JvmStatic
    @Provides
    fun provideForStarWarApi(retrofit: Retrofit): APIService =
        retrofit.create(APIService::class.java)

    @Singleton
    @JvmStatic
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit =
        Retrofit.Builder()
            .baseUrl(APIService.BASE_URL)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()

    @Singleton
    @JvmStatic
    @Provides
    fun provideGson(): Gson = GsonBuilder().setLenient().create()


    @Singleton
    @JvmStatic
    @Provides
    fun provideOkHttpClient(
        connectivityManager: ConnectivityManager,
        @ForApplication context: Context
    ): OkHttpClient {
        val cacheSize = (5 * 1024 * 1024).toLong()

        val myCache = Cache(context.cacheDir, cacheSize)


        val builder = OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .cache(myCache)
            .addInterceptor { chain ->
                var request = chain.request()
                val url = request.url.newBuilder()
                    .build()
                request = request.newBuilder()
                    .url(url)
                    .build()
                request = if (connectivityManager.hasNetwork()!!)
                    request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build()
                else
                /*
                *  If there is no Internet, get the cache that was stored 7 days ago.
                *  If the cache is older than 7 days, then discard it,
                *  and indicate an error in fetching the response.
                *  The 'max-stale' attribute is responsible for this behavior.
                *  The 'only-if-cached' attribute indicates to not retrieve new data; fetch the cache only instead.
                */
                    request.newBuilder().header(
                        "Cache-Control",
                        "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7
                    ).build()
                chain.proceed(request)
            }
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        builder.addNetworkInterceptor(logging)

        return builder.build()
    }
}