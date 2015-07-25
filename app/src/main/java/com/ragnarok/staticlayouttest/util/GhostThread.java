package com.ragnarok.staticlayouttest.util;

import android.util.Log;

/**
 * Created by ragnarok on 15/7/22.
 */
public class GhostThread  {
    private static final String TAG = "GhostThread";
    
    private static boolean isStart = false;
    
    private static Runnable runnable = new Runnable() {
        @Override
        public void run() {
            for (; ;) {
                double c = Math.PI * Math.PI * Math.PI; 
//                Log.d(TAG, "c = " + c);
//                try {
//                    Thread.sleep(5);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
        }
    };
    
    public static void start() {
//        Thread thread = new Thread(runnable);
//        thread.setPriority(Thread.MAX_PRIORITY);
//        thread.start();
        if (isStart) {
            return;
        }
        isStart = true;
        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(runnable);
            thread.setPriority(Thread.NORM_PRIORITY);
            thread.start();
        }
    }
}
