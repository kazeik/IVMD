package com.jingsong.ivmd.view;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.View;

/**
 * @author jingsong.chen, QQ:77132995, email:kazeik@163.com
 * 2019-06-10 20:07
 * 类说明:
 */
public class Drawer {
    private final Drawable mDivider;
    private final int mWidth;
    private final int mHeight;

    public Drawer(Drawable divider, int width, int height) {
        this.mDivider = divider;
        this.mWidth = width;
        this.mHeight = height;
    }

    /**
     * Draw the divider on the left side of the Item.
     */
    public void drawLeft(View view, Canvas c) {
        int left = view.getLeft() - mWidth;
        int top = view.getTop() - mHeight;
        int right = left + mWidth;
        int bottom = view.getBottom() + mHeight;
        mDivider.setBounds(left, top, right, bottom);
        mDivider.draw(c);
    }

    /**
     * Draw the divider on the top side of the Item.
     */
    public void drawTop(View view, Canvas c) {
        int left = view.getLeft() - mWidth;
        int top = view.getTop() - mHeight;
        int right = view.getRight() + mWidth;
        int bottom = top + mHeight;
        mDivider.setBounds(left, top, right, bottom);
        mDivider.draw(c);
    }

    /**
     * Draw the divider on the top side of the Item.
     */
    public void drawRight(View view, Canvas c) {
        int left = view.getRight();
        int top = view.getTop() - mHeight;
        int right = left + mWidth;
        int bottom = view.getBottom() + mHeight;
        mDivider.setBounds(left, top, right, bottom);
        mDivider.draw(c);
    }

    /**
     * Draw the divider on the top side of the Item.
     */
    public void drawBottom(View view, Canvas c) {
        int left = view.getLeft() - mWidth;
        int top = view.getBottom();
        int right = view.getRight() + mWidth;
        int bottom = top + mHeight;
        mDivider.setBounds(left, top, right, bottom);
        mDivider.draw(c);
    }
}
