package com.lxh.manhuagui4a

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import coil3.ImageLoader
import coil3.PlatformContext
import coil3.SingletonImageLoader
import coil3.network.okhttp.OkHttpNetworkFetcherFactory
import dagger.hilt.android.HiltAndroidApp
import okhttp3.OkHttpClient
import rxhttp.RxHttpPlugins
import javax.inject.Inject

@HiltAndroidApp
class Manhuagui4aApplication : Application(), SingletonImageLoader.Factory {
    companion object {
        lateinit var instance: Application
    }

    @Inject
    lateinit var okHttpClient: OkHttpClient

    override fun onCreate() {
        super.onCreate()
        instance = this
        RxHttpPlugins.init(okHttpClient)
    }

    override fun newImageLoader(context: PlatformContext): ImageLoader {
        val imageLoader = ImageLoader.Builder(this).components {
            add(OkHttpNetworkFetcherFactory(callFactory = { okHttpClient }))
        }
//            .memoryCachePolicy(CachePolicy.DISABLED)
//            .diskCachePolicy(CachePolicy.DISABLED)
            .build()
//        imageLoader.memoryCache?.clear()
//        imageLoader.diskCache?.clear()
        return imageLoader
    }
}