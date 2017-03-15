package com.hanbit.javaconfigapp.member;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hanbit.javaconfigapp.factory.LayoutParamsFactory;
import com.hanbit.javaconfigapp.items.ButtonCreator;
import com.hanbit.javaconfigapp.items.LinearLayoutItem;
import com.hanbit.javaconfigapp.items.TextViewCreator;

public class MemberDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Context context = MemberDetail.this;
        LinearLayout.LayoutParams mm = LayoutParamsFactory.createLayoutParams("mm");
        LinearLayout.LayoutParams mw = LayoutParamsFactory.createLayoutParams("mw");
        LinearLayout.LayoutParams mww = LayoutParamsFactory.createLayoutParams("mww");
        LinearLayout ui = LinearLayoutItem.getLinearLayout(context, mm, "v");
        TextView tvDetail = TextViewCreator.getTextView(context, mw, "상세", 30, "center");
        ui.addView(tvDetail);

        LinearLayout uiSub = LinearLayoutItem.getLinearLayout(context, mw, "v");
        uiSub.addView(TextViewCreator.getTextView(context, mw, "ID", 25, "left"));
        uiSub.addView(TextViewCreator.getTextView(context, mw, "NAME", 25, "left"));
        uiSub.addView(TextViewCreator.getTextView(context, mw, "PHONE", 25, "left"));
        uiSub.addView(TextViewCreator.getTextView(context, mw, "AGE", 25, "left"));
        uiSub.addView(TextViewCreator.getTextView(context, mw, "ADDRESS", 25, "left"));
        uiSub.addView(TextViewCreator.getTextView(context, mw, "SALARY", 25, "left"));
        ui.addView(uiSub);

        LinearLayout uiBtns = LinearLayoutItem.getLinearLayout(context, mm, "h");
        uiBtns.addView(ButtonCreator.getButton(context, mww, "MY LOCATION"));
        uiBtns.addView(ButtonCreator.getButton(context, mww, "GOOGLE MAP"));
        ui.addView(uiBtns);

        LinearLayout uiBtns2 = LinearLayoutItem.getLinearLayout(context, mm, "h");
        uiBtns2.addView(ButtonCreator.getButton(context, mww, "ALBUM"));
        uiBtns2.addView(ButtonCreator.getButton(context, mww, "MUSIC"));
        ui.addView(uiBtns2);

        LinearLayout uiBtns3 = LinearLayoutItem.getLinearLayout(context, mm, "h");
        uiBtns3.addView(ButtonCreator.getButton(context, mww, "SMS"));
        uiBtns3.addView(ButtonCreator.getButton(context, mww, "MAIL"));
        ui.addView(uiBtns3);

        LinearLayout uiBtns4 = LinearLayoutItem.getLinearLayout(context, mm, "h");
        uiBtns4.addView(ButtonCreator.getButton(context, mww, "DIAL"));
        uiBtns4.addView(ButtonCreator.getButton(context, mww, "CALL"));
        ui.addView(uiBtns4);

        LinearLayout uiBtns5 = LinearLayoutItem.getLinearLayout(context, mm, "h");
        uiBtns5.addView(ButtonCreator.getButton(context, mww, "UPDATE"));
        uiBtns5.addView(ButtonCreator.getButton(context, mww, "BACK"));
        ui.addView(uiBtns5);
        setContentView(ui);
    }
}
