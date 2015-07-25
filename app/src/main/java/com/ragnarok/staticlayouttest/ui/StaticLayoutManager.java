package com.ragnarok.staticlayouttest.ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.text.*;
import com.ragnarok.staticlayouttest.util.TestSpan;
import com.ragnarok.staticlayouttest.util.Util;

/**
 * Created by ragnarok on 15/7/21.
 */
public class StaticLayoutManager {
    
    private StaticLayout[] layout = new StaticLayout[Util.TEST_LIST_ITEM_COUNT];
    
    private StaticLayout longStringLayout;

    private TextPaint textPaint;
    private TextDirectionHeuristic textDir;
    private Layout.Alignment alignment;
    
    private Canvas dummyCanvas;

    private int hardCodeWidth;
    
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    public void initLayout(Context context, CharSequence source, CharSequence longString) {

        textPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        textPaint.density = context.getResources().getDisplayMetrics().density;
        textPaint.setTextSize(Util.fromDPtoPix(context, Util.TEXT_SIZE_DP));

        textDir = TextDirectionHeuristics.LTR;

        alignment = Layout.Alignment.ALIGN_NORMAL;

        hardCodeWidth = Util.getScreenWidth(context);
        
        longStringLayout = new StaticLayout(longString, textPaint, hardCodeWidth, alignment, 1.0f, 0f, true);
        
        dummyCanvas = new Canvas();
        
        longStringLayout.draw(dummyCanvas);
        
        for (int i = 0; i < layout.length; i++) {
            layout[i] = new StaticLayout(TestSpan.getSpanString(i), textPaint, hardCodeWidth, alignment, 1.0f, 0f, true);
            layout[i].draw(dummyCanvas);
        }
    }
    
    public StaticLayout getLayout(int index) {
        return layout[index];
    }
    
    public StaticLayout getLongStringLayout() {
        return longStringLayout;
    }
    
    private static StaticLayoutManager INSTANCE = null;
    
    public static StaticLayoutManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new StaticLayoutManager();
        }
        return INSTANCE;
    }
}
