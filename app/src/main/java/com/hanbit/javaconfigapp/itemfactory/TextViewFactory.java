package com.hanbit.javaconfigapp.itemfactory;

import android.content.Context;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by hb2005 on 2017-03-15.
 */

public class TextViewFactory {
    public static TextView createTextView(Context context, LinearLayout.LayoutParams layoutParams, String text, int textSize) {
        TextView tv = new TextView(context);
        tv.setLayoutParams(layoutParams);
        tv.setText(text);
        tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        return tv;
    }

    public static TextView createTextView(Context context, LinearLayout.LayoutParams layoutParams, String text, int textSize, String gravity) {
        TextView tv = new TextView(context);
        tv.setLayoutParams(layoutParams);
        tv.setText(text);
        tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
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

    public static TextView createTextView(Context context, LinearLayout.LayoutParams layoutParams, String text, String typeFace, int textSize) {
        TextView tv = new TextView(context);
        tv.setLayoutParams(layoutParams);
        tv.setText(text);
        switch (typeFace) {
            case "B":case "BOLD":
                tv.setTypeface(null, Typeface.BOLD);
                break;
        }
        tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        return tv;
    }
}
