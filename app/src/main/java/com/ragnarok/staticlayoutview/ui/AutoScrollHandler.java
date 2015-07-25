package com.ragnarok.staticlayoutview.ui;

import android.os.Handler;
import android.os.Looper;
import android.widget.ListView;
import android.widget.Toast;
import com.ragnarok.staticlayoutview.util.FpsCalculator;
import com.ragnarok.staticlayoutview.util.Util;

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
        FpsCalculator.instance().startCalculate();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (listView.getLastVisiblePosition() >= itemCount - 1) {
                    final int avgFps = FpsCalculator.instance().stopGetAvgFPS();
                    uiHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(listView.getContext(), "Average FPS: " + avgFps, Toast.LENGTH_LONG).show();
                        }
                    });
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
        FpsCalculator.instance().startCalculate();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (listView.getFirstVisiblePosition() <= 1) {
                    final int avgFps = FpsCalculator.instance().stopGetAvgFPS();
                    uiHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(listView.getContext(), "Average FPS: " + avgFps, Toast.LENGTH_LONG).show();
                        }
                    });
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
