package com.hanbit.javaconfigapp.items;

import android.content.Context;
import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * Created by hb2005 on 2017-03-15.
 */

public class ButtonCreator {

    public static Button getButton(Context context, LinearLayout.LayoutParams layoutParams, String text) {
        Button btn = new Button(context);
        btn.setLayoutParams(layoutParams);
        btn.setText(text);
        return btn;
    }

    public static Button getButton(Context context, LinearLayout.LayoutParams layoutParams, String text, String bgColor) {
        Button btn = new Button(context);
        btn.setLayoutParams(layoutParams);
        btn.setText(text);
        btn.setBackgroundColor(Color.parseColor(bgColor));
        return btn;
    }

    public static Button getButton(Context context, LinearLayout.LayoutParams layoutParams, String text, String bgColor, int[] margin) {
        Button btn = new Button(context);
        ViewGroup.MarginLayoutParams btnMargin = new ViewGroup.MarginLayoutParams(layoutParams);
        btn.setText(text);
        btn.setBackgroundColor(Color.parseColor(bgColor));
        btnMargin.setMargins(margin[0], margin[1], margin[2], margin[3]);
        btn.setLayoutParams(btnMargin);
        return btn;
    }
}
