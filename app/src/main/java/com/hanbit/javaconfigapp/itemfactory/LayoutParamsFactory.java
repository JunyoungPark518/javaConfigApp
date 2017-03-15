package com.hanbit.javaconfigapp.itemfactory;

import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by hb2005 on 2017-03-15.
 */

public class LayoutParamsFactory {
    public static LinearLayout.LayoutParams createLayoutParams(String flag) {
        LinearLayout.LayoutParams rs = null;
        switch(flag) {
            case "mm":
                rs = new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
                );
                break;
            case "mw":
                rs = new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
                );
                break;
            case "ww":
                rs = new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT
                );
                break;
            case "wm":
                rs = new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
                );
                break;
        }
        return rs;
    }
    public static LinearLayout.LayoutParams createLayoutParams(String flag, int weight) {
        LinearLayout.LayoutParams rs = null;
        switch(flag) {
            case "mm":
                rs = new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, weight
                );
                break;
            case "mw":
                rs = new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, weight
                );
                break;
            case "ww":
                rs = new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT, weight
                );
                break;
            case "wm":
                rs = new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, weight
                );
        }
        return rs;
    }
}
