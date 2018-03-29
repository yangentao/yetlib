package yet.ui.ext

import android.content.Context
import android.graphics.drawable.LayerDrawable
import android.view.View
import android.widget.CheckBox
import yet.theme.Colors
import yet.ui.res.ImageStated
import yet.ui.util.OvalDrawable
import yet.ui.util.makeRoundEdgeRectDrawable

/**
 * Created by entaoyang@163.com on 16/6/4.
 */

//width:60, height:30
open class SwitchButton(context: Context) : CheckBox(context) {
	private val onLayoutChange = View.OnLayoutChangeListener {
		_, _, _, _, _, _, _, _, _ ->
		this.post {
			resetImage()
		}
	}

	init {
		this.compoundDrawablePadding = 0
		this.addOnLayoutChangeListener(this.onLayoutChange)
	}

	override fun setChecked(checked: Boolean) {
		val old = this.isChecked
		super.setChecked(checked)
		if (old != checked) {
			resetImage()
		}
	}

	fun makeDrawDp(w: Int, h: Int): LayerDrawable {
		val dd1 = makeRoundEdgeRectDrawable(w, h, Colors.WHITE, 1, Colors.LightGray)
		val dd2 = makeRoundEdgeRectDrawable(w, h, Colors.Safe)
		val dd3 = makeRoundEdgeRectDrawable(w, h, Colors.LightGray, 1, Colors.WHITE)
		val draw = ImageStated(dd1).checked(dd2).enabled(dd3, false).value

		val h2: Int = if (h <= 2) {
			1
		} else {
			h - 2
		}
		val round = if (this.isChecked) {
			OvalDrawable(h2, h2, Colors.WHITE)
		} else {
			OvalDrawable(h2, h2, Colors.WHITE, 1, Colors.LightGray)
		}

		val ld = LayerDrawable(arrayOf(draw, round))
		val offset = dp(w - h)
		if (this.isChecked) {
			ld.setLayerInset(1, offset, 1, 1, 1)
		} else {
			ld.setLayerInset(1, 1, 1, offset, 1)
		}


		return ld
	}

	fun resetImage() {
		this.buttonDrawable = makeDrawDp(px2dp(this.width), px2dp(this.height))
	}

	companion object {
		val WIDTH = 60
		val HEIGHT = 30
	}
}
