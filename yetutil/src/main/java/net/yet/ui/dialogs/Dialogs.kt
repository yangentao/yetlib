package net.yet.ui.dialogs

import android.app.AlertDialog
import android.view.Gravity
import net.yet.R
import net.yet.ui.ext.dp
import net.yet.ui.page.BaseFragment
import net.yet.ui.res.Res

/**
 * Created by entaoyang@163.com on 16/5/10.
 */


fun AlertDialog.gravityTop(margin: Int): AlertDialog {
	val lp = this.window.attributes
	lp.gravity = Gravity.TOP or Gravity.CENTER_HORIZONTAL
	lp.y = dp(margin)
	return this
}

fun AlertDialog.gravityBottom(margin: Int): AlertDialog {
	val lp = this.window.attributes
	lp.gravity = Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL
	lp.y = dp(margin)
	return this
}


fun BaseFragment.showAlert(title: String, msg: String? = null, okText: String = Res.str(R.string.ok)): OKDialog {
	val dlg = OKDialog()
	dlg.ok(okText)
	dlg.show(activity, title, msg)
	return dlg
}

fun BaseFragment.showAlert(title: String, msg: String?, okText: String = Res.str(R.string.ok), block: () -> Unit): OKDialog {
	val dlg = OKDialog()
	dlg.onOK = block
	dlg.ok(okText)
	dlg.show(activity, title, msg)
	return dlg
}

fun BaseFragment.showConfirm(title: String, msg: String? = null, block: () -> Unit) {
	val dlg = ConfirmDialog()
	dlg.onOK = block
	dlg.show(activity, title, msg)
}

fun BaseFragment.confirm(title: String?, msg: String?, okText: String? = null, cancelText: String? = null, block: () -> Unit) {
	val dlg = ConfirmDialog()
	if (okText != null) {
		dlg.ok(okText)
	}
	if (cancelText != null) {
		dlg.cancel(cancelText)
	}
	dlg.onOK = block
	dlg.show(activity, title, msg)
}

fun BaseFragment.showInputDialog(title: String, defaultValue: String = "", block: (String) -> Unit) {
	val dlg = InputDialog()
	dlg.onOK = block
	dlg.inputTypeText()
	dlg.show(activity, title, defaultValue)
}

fun BaseFragment.showInputPassword(title: String, defaultValue: String = "", block: (String) -> Unit) {
	val dlg = InputDialog()
	dlg.onOK = block
	dlg.inputTypePassword()
	dlg.show(activity, title, defaultValue)
}

fun BaseFragment.showInputPasswordNumber(title: String, defaultValue: String = "", block: (String) -> Unit) {
	val dlg = InputDialog()
	dlg.onOK = block
	dlg.inputTypePasswordNumber()
	dlg.show(activity, title, defaultValue)
}

fun BaseFragment.showInputEmail(title: String, defaultValue: String = "", block: (String) -> Unit) {
	val dlg = InputDialog()
	dlg.onOK = block
	dlg.inputTypeEmail()
	dlg.show(activity, title, defaultValue)
}

fun BaseFragment.showInputNumber(title: String, defaultValue: String = "", block: (String) -> Unit) {
	val dlg = InputDialog()
	dlg.onOK = block
	dlg.inputTypeNumber()
	dlg.show(activity, title, defaultValue)
}

fun BaseFragment.showInputPhone(title: String, defaultValue: String = "", block: (String) -> Unit) {
	val dlg = InputDialog()
	dlg.onOK = block
	dlg.inputTypePhone()
	dlg.show(activity, title, defaultValue)
}