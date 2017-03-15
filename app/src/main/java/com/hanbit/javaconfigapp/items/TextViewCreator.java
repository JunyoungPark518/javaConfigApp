package com.hanbit.javaconfigapp.items;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by hb2005 on 2017-03-15.
 */

public class TextViewCreator {
    public static TextView getTextView(Context context, LinearLayout.LayoutParams layoutParams, String text, int textSize) {
        TextView tv = new TextView(context);
        tv.setLayoutParams(layoutParams);
        tv.setText(text);
        tv.setTextSize(textSize);
        return tv;
    }

    public static TextView getTextView(Context context, LinearLayout.LayoutParams layoutParams, String text, int textSize, String gravity) {
        TextView tv = new TextView(context);
        tv.setLayoutParams(layoutParams);
        tv.setText(text);
        tv.setTextSize(textSize);
        switch (gravity) {
            case "left":
                tv.setGravity(0);
                break;
            case "center":
                tv.setGravity(1);
                break;
            case "right":
                tv.setGravity(2);
                break;
        }
        return tv;
    }
}
