package net.yet.util.imgloader

import android.widget.ImageView

/**
 * Created by entaoyang@163.com on 2016-11-02.
 */

fun ImageView.load(uri: String) {
	ImgLoader.display(this, uri, BmpConfig().mid().rgb565())
}

fun ImageView.load(uri: String, block: BmpConfig.() -> Unit) {
	ImgLoader.display(this, uri, block)
}