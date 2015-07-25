package com.ragnarok.staticlayouttest.ui;

import android.os.Handler;
import android.os.Looper;
import android.widget.ListView;
import com.ragnarok.staticlayouttest.util.Util;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by ragnarok on 15/7/22.
 */
public class AutoScrollHandler {
    
    private ListView listView;
    
    private int itemCount;
    
    private Handler uiHandler = new Handler(Looper.getMainLooper());
    
    public AutoScrollHandler(ListView listView, int itemCount) {
        this.listView = listView;
        this.itemCount = itemCount;
    }
    
    public void startAutoScrollDown() {
        final Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (listView.getLastVisiblePosition() >= itemCount - 1) {
                    timer.cancel();
                }
                uiHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        listView.smoothScrollByOffset(Util.AUTO_SCROLL_STEP);
                    }
                });
            }
        }, 0, Util.AUTO_SCROLL_INTERVAL);
        
    }
    
    public void startAutoScrollUp() {
        final Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (listView.getFirstVisiblePosition() <= 1) {
                    timer.cancel();
                }
                uiHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        listView.smoothScrollByOffset(-Util.AUTO_SCROLL_STEP);
                    }
                });
            }
        }, 0, Util.AUTO_SCROLL_INTERVAL);
    }
}
