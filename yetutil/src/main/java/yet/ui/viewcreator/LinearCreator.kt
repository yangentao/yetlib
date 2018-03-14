package yet.ui.viewcreator

import android.app.Fragment
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import yet.ui.ext.*

/**
 * Created by entaoyang@163.com on 2018-03-14.
 */

fun Context.linearVer(block: LinearLayout.() -> Unit): LinearLayout {
	val ll = LinearLayout(this).genId().vertical()
	ll.block()
	return ll
}

fun Context.linearHor(block: LinearLayout.() -> Unit): LinearLayout {
	val ll = LinearLayout(this).genId().horizontal()
	ll.block()
	return ll
}

fun Context.linear(block: LinearLayout.() -> Unit): LinearLayout {
	val ll = LinearLayout(this).genId()
	ll.block()
	return ll
}

fun Fragment.linearVer(block: LinearLayout.() -> Unit): LinearLayout {
	val ll = LinearLayout(this.activity).genId().vertical()
	ll.block()
	return ll
}

fun Fragment.linearHor(block: LinearLayout.() -> Unit): LinearLayout {
	val ll = LinearLayout(this.activity).genId().horizontal()
	ll.block()
	return ll
}

fun Fragment.linear(block: LinearLayout.() -> Unit): LinearLayout {
	val ll = LinearLayout(this.activity).genId()
	ll.block()
	return ll
}


//LinearLayout
inline fun <P : ViewGroup.LayoutParams> ViewGroup.linear(param: P, block: LinearLayout.() -> Unit): LinearLayout {
	val v = this.createLinear()
	this.addView(v, param)
	v.block()
	return v
}

fun <P : ViewGroup.LayoutParams> ViewGroup.linear(index: Int, param: P, block: LinearLayout.() -> Unit): LinearLayout {
	val v = this.createLinear()
	this.addView(v, index, param)
	v.block()
	return v
}

fun <P : ViewGroup.LayoutParams> ViewGroup.linearBefore(ankor: View, param: P, block: LinearLayout.() -> Unit): LinearLayout {
	return this.linear(this.indexOfChild(ankor), param, block)
}

fun <P : ViewGroup.LayoutParams> ViewGroup.linearHor(param: P, block: LinearLayout.() -> Unit): LinearLayout {
	val v = this.context.createLinearHorizontal()
	this.addView(v, param)
	v.block()
	return v
}

fun <P : ViewGroup.LayoutParams> ViewGroup.linearHor(index: Int, param: P, block: LinearLayout.() -> Unit): LinearLayout {
	val v = this.context.createLinearHorizontal()
	this.addView(v, index, param)
	v.block()
	return v
}

fun <P : ViewGroup.LayoutParams> ViewGroup.linearHorBefore(ankor: View, param: P, block: LinearLayout.() -> Unit): LinearLayout {
	return this.linearHor(this.indexOfChild(ankor), param, block)
}

fun <P : ViewGroup.LayoutParams> ViewGroup.linearVer(param: P, block: LinearLayout.() -> Unit): LinearLayout {
	val v = this.context.createLinearVertical()
	this.addView(v, param)
	v.block()
	return v
}

fun <P : ViewGroup.LayoutParams> ViewGroup.linearVer(index: Int, param: P, block: LinearLayout.() -> Unit): LinearLayout {
	val v = this.context.createLinearVertical()
	this.addView(v, index, param)
	v.block()
	return v
}

fun <P : ViewGroup.LayoutParams> ViewGroup.linearVerBefore(ankor: View, param: P, block: LinearLayout.() -> Unit): LinearLayout {
	return this.linearVer(this.indexOfChild(ankor), param, block)
}

fun View.createLinear(): LinearLayout {
	return this.context.createLinear()
}

fun Fragment.createLinear(): LinearLayout {
	return this.activity.createLinear()
}

fun Context.createLinear(): LinearLayout {
	return LinearLayout(this).genId()
}


fun View.createLinearVertical(): LinearLayout {
	return this.context.createLinearVertical()
}

fun Fragment.createLinearVertical(): LinearLayout {
	return this.activity.createLinearVertical()
}

fun Context.createLinearVertical(): LinearLayout {
	return LinearLayout(this).genId().vertical()
}

fun View.createLinearHorizontal(): LinearLayout {
	return this.context.createLinearHorizontal()
}

fun Fragment.createLinearHorizontal(): LinearLayout {
	return this.activity.createLinearHorizontal()
}

fun Context.createLinearHorizontal(): LinearLayout {
	return LinearLayout(this).genId().horizontal()
}

