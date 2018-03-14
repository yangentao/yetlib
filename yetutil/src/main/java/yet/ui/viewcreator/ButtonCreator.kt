package yet.ui.viewcreator

import android.app.Fragment
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import yet.ui.ext.*

/**
 * Created by entaoyang@163.com on 2018-03-14.
 */

//Button
fun <P : ViewGroup.LayoutParams> ViewGroup.button(param: P, block: Button.() -> Unit): Button {
	val v = this.createButton()
	this.addView(v, param)
	v.block()
	return v
}

fun <P : ViewGroup.LayoutParams> ViewGroup.button(index: Int, param: P, block: Button.() -> Unit): Button {
	val v = this.createButton()
	this.addView(v, index, param)
	v.block()
	return v
}

fun <P : ViewGroup.LayoutParams> ViewGroup.buttonBefore(ankor: View, param: P, block: Button.() -> Unit): Button {
	return this.button(this.indexOfChild(ankor), param, block)
}

fun View.createButton(text: String = ""): Button {
	return this.context.createButton(text)
}

fun Fragment.createButton(text: String = ""): Button {
	return this.activity.createButton(text)
}

fun Context.createButton(text: String = ""): Button {
	return Button(this).genId().text(text).textSizeB().padding(3)
}
