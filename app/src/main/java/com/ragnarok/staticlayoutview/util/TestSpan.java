package com.ragnarok.staticlayoutview.util;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import com.ragnarok.staticlayouttest.app.R;

import java.util.Random;

/**
 * Created by ragnarok on 15/7/21.
 */
public class TestSpan {

    private static SpannableString testString = 
            new SpannableString("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzABCDEFGH" +
                    "IJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
    
    private static SpannableString testLongString = 
            new SpannableString("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\n" +
                    "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\n" +
                    "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\n" +
                    "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\n" +
                    "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\n" +
                    "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\n" +
                    "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\n" +
                    "AAAAAAAAAAAAAAAAAAAAAAAAAAAA");
    
    private static SpannableString[] allTestStrings = new SpannableString[Util.TEST_LIST_ITEM_COUNT];
    
    public static void init(Context context) {
        for (int i = 0; i <= testString.length(); i++) {
            if (i + 1 <= testString.length()) {
                ImageSpan imgSpan = new ImageSpan(context, BitmapFactory.decodeResource(context.getResources(), R.drawable.test));
                testString.setSpan(imgSpan, i, i + 1, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
            }
        }
        
        Random random = new Random();
        
        for (int i = 0; i < allTestStrings.length; i++) {
            int start = random.nextInt(testString.length() / 2);
            int len = random.nextInt(testString.length());
            len = len > 1 ? len : 1;
            int end = start + len > testString.length() ? testString.length() : start + len;
            allTestStrings[i] = (SpannableString) testString.subSequence(start, end);
        }

        for (int i = 0; i < testLongString.length(); i++) {
            if (i + 1 < testLongString.length()) {
                ImageSpan imgSpan = new ImageSpan(context, BitmapFactory.decodeResource(context.getResources(), R.drawable.test));
                testLongString.setSpan(imgSpan, i, i + 1, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
            }
        }
    }
    
    public static SpannableString getSpanString(int index) {
        return allTestStrings[index];
    }
    
    public static SpannableString getSpanString() {
        return testString;
    }
    
    public static SpannableString getLongSpanString() {
        return testLongString;
    }
}
