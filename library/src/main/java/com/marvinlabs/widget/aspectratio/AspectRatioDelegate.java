package com.marvinlabs.widget.aspectratio;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.marvinlabs.widget.constrained.R;


/**
 * Class to do most of the work for views that should be constrained to a given aspect ratio
 * <p/>
 * Created by vprat on 23/05/2014.
 */
public class AspectRatioDelegate {

    /**
     * Interface to be implemented by the views that we should constrain
     */
    public interface ConstrainedView {

        /**
         * Implementations should simply make a call to parent.onMeasure(widthMeasureSpec, heightMeasureSpec)
         */
        public void callParentOnMeasure(int widthMeasureSpec, int heightMeasureSpec);
    }

    /**
     * Tells which of width or height will be our reference. The other dimension will adapt to
     * match the aspect ratio.
     */
    public enum FixedDimension {
        WIDTH(1), HEIGHT(2);
        int id;

        FixedDimension(int id) {
            this.id = id;
        }

        static FixedDimension fromId(int id) {
            for (FixedDimension f : values()) {
                if (f.id == id) return f;
            }
            throw new IllegalArgumentException();
        }
    }

    /**
     * Default constructor (square aspect ratio following the width of the parent view)
     *
     * @param parent The view to constrain
     */
    public AspectRatioDelegate(ConstrainedView parent) {
        this(parent, 1, FixedDimension.WIDTH);
    }

    /**
     * Constructor from the view attributes
     *
     * @param parent The view to constrain
     */
    public AspectRatioDelegate(ConstrainedView parent, AttributeSet attrs) {
        this(parent);
        readViewAttributes(((View) parent).getContext(), attrs);
    }

    /**
     * Constructor with all the parameters to give a fixed aspect ratio to a view
     *
     * @param parent         The view to constrain
     * @param aspectRatio    The aspect ratio to follow
     * @param fixedDimension The dimension that will be the reference (the other will be set to match the aspect ratio)
     */
    public AspectRatioDelegate(ConstrainedView parent, float aspectRatio, FixedDimension fixedDimension) {
        this.parent = parent;
        this.aspectRatio = aspectRatio;
        this.fixedDimension = fixedDimension;
    }

    /**
     * Delegates the onMeasure code for the constrained views. Those views should override their parent's onMeasure method
     * and simply call their delegate's onMeasure method.
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int originalWidth = View.MeasureSpec.getSize(widthMeasureSpec);
        int originalHeight = View.MeasureSpec.getSize(heightMeasureSpec);

        int finalWidth, finalHeight;

        switch (getFixedDimension()) {
            case WIDTH:
                finalWidth = originalWidth;
                finalHeight = (int) (originalWidth / aspectRatio);
                break;

            case HEIGHT:
                finalWidth = (int) (originalHeight * aspectRatio);
                finalHeight = originalHeight;
                break;

            default:
                finalWidth = originalWidth;
                finalHeight = originalHeight;
        }

        Log.d("", "View measure is now: " + finalWidth + " x " + finalHeight);

        parent.callParentOnMeasure(
                View.MeasureSpec.makeMeasureSpec(finalWidth, View.MeasureSpec.EXACTLY),
                View.MeasureSpec.makeMeasureSpec(finalHeight, View.MeasureSpec.EXACTLY));
    }

    public void readViewAttributes(Context context, AttributeSet attrs) {
        if (attrs == null) return;

        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.AspectRatioView);

        int aspectRatioWidth = 1;
        int aspectRatioHeight = 1;
        FixedDimension fixedDimension = FixedDimension.WIDTH;

        final int N = a.getIndexCount();
        for (int i = 0; i < N; ++i) {
            int attr = a.getIndex(i);

            if (attr == R.styleable.AspectRatioView_aspectRatioWidth) {
                aspectRatioWidth = a.getInteger(attr, 1);
            } else if (attr == R.styleable.AspectRatioView_aspectRatioHeight) {
                aspectRatioHeight = a.getInteger(attr, 1);
            } else if (attr == R.styleable.AspectRatioView_fixedDimension) {
                fixedDimension = FixedDimension.fromId(a.getInteger(attr, 1));
            }
        }
        a.recycle();

        this.aspectRatio = (float) (aspectRatioWidth) / (float) (aspectRatioHeight);
        this.fixedDimension = fixedDimension;
    }

    public float getAspectRatio() {
        return aspectRatio;
    }

    public void setAspectRatio(float aspectRatio) {
        this.aspectRatio = aspectRatio;
    }

    public FixedDimension getFixedDimension() {
        return fixedDimension;
    }

    public void setFixedDimension(FixedDimension fixedDimension) {
        this.fixedDimension = fixedDimension;
    }

    private ConstrainedView parent;
    private float aspectRatio;
    private FixedDimension fixedDimension;
}
