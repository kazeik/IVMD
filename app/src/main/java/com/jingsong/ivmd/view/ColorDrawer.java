package com.jingsong.ivmd.view;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.ColorInt;

/**
 * @author jingsong.chen, QQ:77132995, email:kazeik@163.com
 * 2019-06-10 20:08
 * 类说明:
 */
public class ColorDrawer extends Drawer {
    public ColorDrawer(int color, int width, int height) {
        super(new ColorDrawable(opaqueColor(color)), width, height);
    }

    /**
     * The target color is packaged in an opaque color.
     *
     * @param color color.
     *
     * @return color.
     */
    @ColorInt
    public static int opaqueColor(@ColorInt int color) {
        int alpha = Color.alpha(color);
        if (alpha == 0) return color;
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        return Color.argb(255, red, green, blue);
    }
}
