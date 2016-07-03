package com.marvinlabs.widget.aspectratio;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * A linear layout with a constrained aspect ratio.
 *
 * @author Vincent Mimoun-Prat @ MarvinLabs (www.marvinlabs.com)
 */
public class ConstrainedLinearLayout extends LinearLayout implements AspectRatioDelegate.ConstrainedView {

    @TargetApi(11)
    public ConstrainedLinearLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        aspectRatioDelegate = new AspectRatioDelegate(this, attrs);
    }

    public ConstrainedLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        aspectRatioDelegate = new AspectRatioDelegate(this, attrs);
    }

    public ConstrainedLinearLayout(Context context) {
        super(context);
        aspectRatioDelegate = new AspectRatioDelegate(this);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        aspectRatioDelegate.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @SuppressLint("WrongCall")
    @Override
    public void callParentOnMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private final AspectRatioDelegate aspectRatioDelegate;
}
