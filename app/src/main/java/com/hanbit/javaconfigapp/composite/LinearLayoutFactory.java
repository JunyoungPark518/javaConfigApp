package com.hanbit.javaconfigapp.composite;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.Map;

/**
 * Created by hb2005 on 2017-03-15.
 */

public class LinearLayoutFactory {
    public static LinearLayout createLinearLayout(Context context, Map<String, String> map) {
        LinearLayout ll = new LinearLayout(context);
        if(map.get("type")!=null)
        switch(map.get("type")) {
            case "vertical" :
                ll.setOrientation(LinearLayout.VERTICAL);
                ll.setLayoutParams(LayoutParamsFactory.createLayoutParams(map.get("layoutParams")));
                break;
        }
        if(map.get("margin")!=null) {
            ViewGroup.MarginLayoutParams margin = new ViewGroup.MarginLayoutParams(LayoutParamsFactory.createLayoutParams(map.get("layoutParams")));
            int[] marginStr = new int[4];
            marginStr[0] = Integer.parseInt(map.get("margin").split(",")[0]);
            marginStr[1] = Integer.parseInt(map.get("margin").split(",")[1]);
            marginStr[2] = Integer.parseInt(map.get("margin").split(",")[2]);
            marginStr[3] = Integer.parseInt(map.get("margin").split(",")[3]);
            margin.setMargins(marginStr[0],marginStr[1],marginStr[2],marginStr[3]);
            ll.setLayoutParams(margin);
        }

        return ll;
    }

    public static LinearLayout createLinearLayout(Context context, LinearLayout.LayoutParams layoutParams) {
        LinearLayout ll = new LinearLayout(context);
        ll.setLayoutParams(layoutParams);
        return ll;
    }

    public static LinearLayout createLinearLayout(Context context, LinearLayout.LayoutParams layoutParams, String orientation) {
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

    public static LinearLayout createLinearLayout(Context context, LinearLayout.LayoutParams layoutParams, String orientation, int[] margin) {
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
