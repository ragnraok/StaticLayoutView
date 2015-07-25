package com.ragnarok.staticlayoutview.util;

import android.content.Context;
import android.graphics.Point;
import android.view.WindowManager;

/**
 * Created by ragnarok on 15/7/21.
 */
public class Util {
    
    public static final int TEST_LIST_ITEM_COUNT = 300;
    
    public static final int TEXT_SIZE_DP = 25;
    
    public static final int AUTO_SCROLL_INTERVAL = 1;
    
    public static final int AUTO_SCROLL_STEP = 10;
    
    public static float fromDPtoPix(Context context, int dp) {
        return context.getResources().getDisplayMetrics().density * dp;
    }
    
    public static int getScreenWidth(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        
        Point size = new Point();
        windowManager.getDefaultDisplay().getSize(size);
        
        return size.x;
    }
}
