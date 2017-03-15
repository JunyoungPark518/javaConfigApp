package com.hanbit.javaconfigapp.itemfactory;

import android.content.Context;
import android.util.TypedValue;
import android.widget.EditText;
import android.widget.LinearLayout;

/**
 * Created by hb2005 on 2017-03-15.
 */

public class EditTextFactory {
    public static EditText createEditText(Context context, LinearLayout.LayoutParams layoutParams, String hint, int textSize) {
        EditText et = new EditText(context);
        et.setLayoutParams(layoutParams);
        et.setHint(hint);
        et.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        return et;
    }
}
