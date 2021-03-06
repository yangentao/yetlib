package yet.ui.ext

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.*
import yet.theme.Colors
import yet.ui.res.D
import yet.ui.viewcreator.*

/**
 * Created by entaoyang@163.com on 2018-04-17.
 */

class FormView(val tableLayout: TableLayout) {
	fun header(label: String, label2: String) {
		tableLayout.tableRow(TParam.WidthFill.HeightWrap.marginBottom(5)) {
			setBackgroundColor(Colors.Theme)
			textView(RowParam.WidthWrap.HeightEditSmall) {
				text = label
				minimumWidth = dp(80)
				textColorWhite()
				gravityCenter()
			}
			textView(RowParam.WidthWrap.HeightEditSmall) {
				text = label2
				textColorWhite()
				gravityCenter()
			}
		}
	}

	fun row(label: String, block: (Context) -> View) {
		tableLayout.tableRow(TParam.WidthFill.HeightWrap.marginBottom(5)) {
			textView(RowParam.WidthWrap.HeightEditSmall) {
				text = label
				textColorMajor()
				gravityLeftCenter()
			}
			val v = block(context)
			addView(v, RowParam.WidthWrap.HeightEditSmall.marginLeft(10))
		}
	}

	fun rowText(label: String, text: String): TextView {
		val v = tableLayout.createTextViewB()
		v.gravityRightCenter()
		row(label) {
			v.text = text
			v
		}
		return v
	}

	fun rowButton(label: String, text: String): TextView {
		val v = tableLayout.createTextViewB()
		v.gravityCenter()
		v.backDrawable(D.buttonWhite(2))
		v.clickable()
		row(label) {
			v.text = text
			v
		}
		return v
	}

	fun rowEdit(label: String, text: String = ""): EditText {
		val v = tableLayout.createEditX()
		v.gravityLeftCenter()
		v.backDrawable(D.InputRect)
		row(label) {
			v.textS = text
			v
		}
		return v
	}
}

fun ViewGroup.form(param: ViewGroup.LayoutParams, block: FormView.() -> Unit): TableLayout {
	val tb = table(param) {
		this.setColumnStretchable(1, true)
	}
	val f = FormView(tb)
	f.block()
	return tb
}

fun TableLayout.editOf(label: String): EditText? {
	for (row in this.childViews) {
		if (row is TableRow && row.childCount == 2) {
			val first = row.getChildAt(0)
			val second = row.getChildAt(1)
			if (first is TextView && second is EditText) {
				if (first.textS == label) {
					return second
				}
			}
		}
	}
	return null
}

fun TableLayout.stretch(vararg cols: Int) {
	for (n in cols) {
		this.setColumnStretchable(n, true)
	}
}

fun TableLayout.header(block: TableRow.() -> Unit): TableRow {
	return tableRow(TParam.WidthFill.HeightWrap.marginBottom(5)) {
		setBackgroundColor(Colors.Theme)
		this.tag = "header"
		this.block()

	}
}

fun TableLayout.row(block: TableRow.() -> Unit): TableRow {
	return tableRow(TParam.WidthFill.HeightWrap.marginBottom(5)) {
		this.block()
	}
}

private val colParam: TableRow.LayoutParams
	get() {
		return RowParam.WidthWrap.HeightEditSmall.margins(3, 0, 3, 0)
	}

fun TableRow.label(label: String): TextView {
	val isHeaderRow = tag == "header"
	return textView(colParam) {
		text = label
		gravityLeftCenter()
		if (isHeaderRow) {
			textColorWhite()
		} else {
			textColorMajor()
		}
	}
}

fun TableRow.edit(text: String): EditText {
	val isHeaderRow = tag == "header"
	return this.editX(colParam) {
		gravityLeftCenter()
		backDrawable(D.InputRect)
		textS = text
	}

}

fun TableRow.button(label: String): TextView {
	val isHeaderRow = tag == "header"
	return textView(colParam) {
		text = label
		padding(5, 3, 5, 3)
		textColorMajor()
		textSizeB()
		gravityCenter()
		backDrawable(D.buttonWhite(2))
		clickable()
	}
}

fun <T : View> T.span(n: Int): T {
	val p = this.layoutParams as TableRow.LayoutParams
	p.span(n)
	return this
}

fun <T : View> T.atColumn(n: Int): T {
	val p = this.layoutParams as TableRow.LayoutParams
	p.atColumn(n)
	return this
}


fun TableLayout.colViews(col: Int): List<View> {
	val ls = ArrayList<View>()
	for (r in this.childViews) {
		if (r is TableRow) {
			ls += r.getChildAt(col)
		}
	}
	return ls
}