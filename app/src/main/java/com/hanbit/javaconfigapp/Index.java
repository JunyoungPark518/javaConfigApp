package com.hanbit.javaconfigapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hanbit.javaconfigapp.factory.LayoutParamsFactory;
import com.hanbit.javaconfigapp.items.ButtonCreator;
import com.hanbit.javaconfigapp.items.LinearLayoutItem;
import com.hanbit.javaconfigapp.items.TextViewCreator;
import com.hanbit.javaconfigapp.member.MemberDetail;

public class Index extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Context context = Index.this;

        LinearLayout.LayoutParams mm = LayoutParamsFactory.createLayoutParams("mm");
        LinearLayout.LayoutParams mw = LayoutParamsFactory.createLayoutParams("mw");

        int[] llMarginArr = {0, 200, 0, 0};
        LinearLayout ui = LinearLayoutItem.getLinearLayout(context, mm, "v", llMarginArr);
        TextView tv = TextViewCreator.getTextView(context, mw, "HELLO", 30);
        ui.addView(tv);
        int[] btnMarginArr = {0, 300, 0, 0};
        Button btn = ButtonCreator.getButton(context, mw, "Button", "#00ff00", btnMarginArr);
        ui.addView(btn);
        setContentView(ui);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Hi!", Toast.LENGTH_LONG).show();
                startActivity(new Intent(context, MemberDetail.class));
            }
        });
    }
}