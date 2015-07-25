package com.ragnarok.staticlayoutview.util;

import android.annotation.TargetApi;
import android.os.Build;
import android.util.Log;
import android.view.Choreographer;
import android.view.Choreographer.FrameCallback;

import java.lang.reflect.Field;
import java.util.concurrent.atomic.AtomicInteger;

public class FpsCalculator {

	private final static String TAG = "FpsCalculator";
	
    private long mFrameIntervalNanos;
    
    private boolean mRunning = false;
    
    private static FpsCalculator instance;

	private AtomicInteger atom = new AtomicInteger(0);
	private Thread syncCheckThread = null;
    
    static {
    	instance = new FpsCalculator();
    }
    
    public static FpsCalculator instance() {
    	return instance;
    }
	
	private int totalFps;
	private int fpsCalculateCount;
	private boolean isCalculatingFPS;
	
	// calculate the average fps
	
	public void startCalculate() {
		totalFps = 0;
		fpsCalculateCount = 0;
		isCalculatingFPS = true;
	}
	
	public int stopGetAvgFPS() {
		isCalculatingFPS = false;
		int avgFPS = totalFps / fpsCalculateCount;
		totalFps = 0;
		fpsCalculateCount = 0;
		return avgFPS;
	}

	private void syncCheckThread(){
		if(!mRunning){
			return;
		}
		int val = atom.getAndSet(0);
		if (isCalculatingFPS) {
			totalFps += val;
			fpsCalculateCount++;
		}
		android.util.Log.i(TAG, "FPS: " + val);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	    
	private FrameCallback frameCallback = new FrameCallback() {

		@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
		@Override
		public void doFrame(long frameTimeNanos) {
			
			if (!mRunning) {
				return;
			}
            
            Choreographer.getInstance().postFrameCallback(frameCallback);

			atom.incrementAndGet();
		}};
	
	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	public void start() {
		Log.d(TAG, "start vsync detect");
		if (mRunning) {
			return;
		}
		
		mRunning = true;

		syncCheckThread = new Thread(new Runnable() {
			@Override
			public void run() {
				for (;;) {
					if (!mRunning) {
						break;
					}
					syncCheckThread();
				}
			}
		});
		syncCheckThread.start();
		
		Choreographer chor = Choreographer.getInstance();
		Field field;
		try {
			field = chor.getClass().getDeclaredField("mFrameIntervalNanos");
			field.setAccessible(true);
			mFrameIntervalNanos = field.getLong(chor);
			Log.d(TAG, "mFrameIntervalNanos " + mFrameIntervalNanos);
		} catch (Exception e) {
			Log.e(TAG, "error: " + e.getMessage());
		}
		chor.postFrameCallback(frameCallback);

	}
	
	public void stop() {
		mRunning = false;
		if (syncCheckThread != null) {
			try {
				syncCheckThread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
