package com.ragnarok.staticlayoutview.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by ragnarok on 15/7/16.
 */
public class StaticLayoutView extends View {
    
    private Layout layout = null;

    public StaticLayoutView(Context context) {
        super(context);
    }

    public StaticLayoutView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public StaticLayoutView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    
    public void setLayout(Layout layout) {
        this.layout = layout;
        requestLayout();
    } 

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        
        canvas.save();
        if (layout != null) {
            layout.draw(canvas, null, null, 0);
        }
        canvas.restore();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (layout != null) {
            setMeasuredDimension(layout.getWidth(), layout.getHeight());
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);   
        }
        
    }
}
