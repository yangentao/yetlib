package net.yet.util.imageloader

import android.widget.ImageView
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache
import com.nostra13.universalimageloader.core.DisplayImageOptions
import com.nostra13.universalimageloader.core.ImageLoader
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration
import net.yet.file.SdAppFile
import net.yet.util.app.App

/**
 * Created by entaoyang@163.com on 2016-11-02.
 */


fun initImageLoader() {
	val cacheDir = SdAppFile.dir("imgcaches")
	val config = ImageLoaderConfiguration.Builder(App.get())
			.memoryCacheExtraOptions(480, 800) // default = device screen dimensions
			.diskCacheExtraOptions(480, 800, null)
			.denyCacheImageMultipleSizesInMemory()
			.memoryCache(LruMemoryCache(2 * 1024 * 1024))
			.memoryCacheSize(2 * 1024 * 1024)
			.diskCache(UnlimitedDiskCache(cacheDir)) // default
			.diskCacheSize(50 * 1024 * 1024)
			.diskCacheFileCount(100)
			.build()
	ImageLoader.getInstance().init(config)
}

fun ImageView.fromUri(uri: String) {
	ImageLoader.getInstance().displayImage(uri, this)
}

fun ImageView.fromUri(uri: String, block: DisplayImageOptions.Builder.() -> Unit) {
	val b = DisplayImageOptions.Builder()
	b.block()
	ImageLoader.getInstance().displayImage(uri, this, b.build())
}