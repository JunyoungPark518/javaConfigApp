package com.hanbit.javaconfigapp.items;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by hb2005 on 2017-03-15.
 */

public class LinearLayoutItem {
    public static LinearLayout getLinearLayout(Context context, LinearLayout.LayoutParams layoutParams, String orientation) {
        LinearLayout ll = new LinearLayout(context);
        ll.setLayoutParams(layoutParams);
        switch (orientation) {
            case "v":case "vertical":
                ll.setOrientation(LinearLayout.VERTICAL);
                break;
            case "h":case "horizontal":
                ll.setOrientation(LinearLayout.HORIZONTAL);
                break;
        }
        return ll;
    }

    public static LinearLayout getLinearLayout(Context context, LinearLayout.LayoutParams layoutParams, String orientation, int[] margin) {
        LinearLayout ll = new LinearLayout(context);
        ViewGroup.MarginLayoutParams llMargin = new ViewGroup.MarginLayoutParams(layoutParams);
        llMargin.setMargins(margin[0], margin[1], margin[2], margin[3]);
        ll.setLayoutParams(llMargin);
        switch (orientation) {
            case "v":case "vertical":
                ll.setOrientation(LinearLayout.VERTICAL);
                break;
            case "h":case "horizontal":
                ll.setOrientation(LinearLayout.HORIZONTAL);
                break;
        }
        return ll;
    }

}
