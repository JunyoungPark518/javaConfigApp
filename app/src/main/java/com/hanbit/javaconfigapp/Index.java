package com.hanbit.javaconfigapp;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Index extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Context context = Index.this;
        LinearLayout ui = new LinearLayout(context);
        LinearLayout.LayoutParams matchAndMatch = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
        );
        LinearLayout.LayoutParams matchAndWrap = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        LinearLayout.LayoutParams wrapAndMatch = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT
        );
        LinearLayout.LayoutParams wrapAndWrap = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        ui.setOrientation(LinearLayout.VERTICAL);
        ui.setLayoutParams(matchAndMatch);
        TextView tv = new TextView(context);
        tv.setText("HELLO");
        tv.setLayoutParams(matchAndWrap);
        ui.addView(tv);
        Button btn = new Button(context);
        btn.setText("BUTTON");
        btn.setLayoutParams(matchAndWrap);
        btn.setBackgroundColor(Color.parseColor("#00ff00"));
        ui.addView(btn);
        setContentView(ui);
        ViewGroup.MarginLayoutParams tvMargin = new ViewGroup.MarginLayoutParams(tv.getLayoutParams());
        tvMargin.setMargins(0, 200, 0, 0);
        btn.setLayoutParams(new LinearLayout.LayoutParams(tvMargin));
        ViewGroup.MarginLayoutParams btnMargin = new ViewGroup.MarginLayoutParams(btn.getLayoutParams());
        btnMargin.setMargins(0, 300, 0, 0);
        btn.setLayoutParams(new LinearLayout.LayoutParams(btnMargin));
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Hi!", Toast.LENGTH_LONG).show();
            }
        });
    }
}