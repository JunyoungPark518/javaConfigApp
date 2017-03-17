package com.hanbit.javaconfigapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.hanbit.javaconfigapp.composite.Composite;
import com.hanbit.javaconfigapp.member.MemberList;
import com.hanbit.javaconfigapp.message.MessageWrite;

import java.util.HashMap;

public class Index extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Context context = Index.this;
        HashMap<String, Object> view = (HashMap<String, Object>) init(context);

        Button btnGoList = (Button) view.get("btnIndex");
        btnGoList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, MemberList.class));
            }
        });
        Button btnGoHTML = (Button) view.get("btnIndexHTML");
        btnGoHTML.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(context, MessageWrite.class);
                String sendingMsg = String.format("%s,%s,%s,%s,%s,%s", 1, 2, 3, 4, 5, 6);
                it.putExtra("data",sendingMsg);
                startActivity(it);
            }
        });
        setContentView((LinearLayout) init(context).get("llIndex"));
    }

    public HashMap<?,?> init(Context context) {
        Composite compo = new Composite(context, "Index");
        compo.execute();
        return compo.getComponents();
    }
}