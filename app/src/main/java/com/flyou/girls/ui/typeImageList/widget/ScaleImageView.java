package com.flyou.girls.ui.typeImageList.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.flyou.girls.R;


/**
 * ==================================================
 * 项目名称：mobike
 * 创建人：wangxiaolong
 * 创建时间：16/5/12 下午12:25
 * 修改时间：16/5/12 下午12:25
 * 修改备注：
 * Version：
 * ==================================================
 */
public class ScaleImageView extends ImageView {
    private float defaultValue = 0f;

    public ScaleImageView(Context context) {
        this(context, null);
    }

    public ScaleImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.ScaleImageView_Style);
        defaultValue = a.getFloat(R.styleable.ScaleImageView_Style_scaleValue, 0f);
        a.recycle();
    }

    public ScaleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (0f == defaultValue) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        } else if (1f == defaultValue) {
            super.onMeasure(widthMeasureSpec, widthMeasureSpec);
        } else {
            int widMode = MeasureSpec.getMode(widthMeasureSpec);
            int widSize = MeasureSpec.getSize(widthMeasureSpec);

            int heightMode = MeasureSpec.getMode(heightMeasureSpec);
            int heightSize = MeasureSpec.getSize(heightMeasureSpec);

            if (widMode == MeasureSpec.EXACTLY && heightMode != MeasureSpec.EXACTLY) {
                heightMeasureSpec = MeasureSpec.makeMeasureSpec(Math.round(widSize * defaultValue), MeasureSpec.EXACTLY);
                setMeasuredDimension(widSize, MeasureSpec.getSize(heightMeasureSpec));
                return;
            } else if (widMode != MeasureSpec.EXACTLY && heightMode == MeasureSpec.EXACTLY) {
                widthMeasureSpec = MeasureSpec.makeMeasureSpec(Math.round(heightSize / defaultValue), MeasureSpec.EXACTLY);
                setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), heightSize);
                return;
            } else {
                super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            }
        }
    }

    public void setDefaultValue(float defaultValue) {
        this.defaultValue = defaultValue;
    }
}
