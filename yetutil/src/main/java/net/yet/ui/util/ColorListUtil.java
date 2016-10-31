package net.yet.ui.util;

import android.content.res.ColorStateList;

import net.yet.util.log.xlog;

import java.util.Arrays;

public class ColorListUtil {
	private int[] colors = new int[10];
	private int[][] states = new int[10][];

	private int index = 0;

	//add if color != null
	public void addColor(Integer color, int... states) {
		if (color != null) {
			if (index >= 10) {
				xlog.INSTANCE.e("max color num is 10");
				return;
			}
			colors[index] = color.intValue();
			this.states[index] = states;
			++index;
		}
	}

	public ColorStateList get() {
		if (index <= 0) {
			return null;
		}
		int[][] ss = new int[index][];
		for (int i = 0; i < index; ++i) {
			ss[i] = states[i];
		}
		return new ColorStateList(ss, Arrays.copyOf(colors, index));
	}
}
