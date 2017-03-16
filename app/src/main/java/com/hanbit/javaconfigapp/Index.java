package com.hanbit.javaconfigapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.hanbit.javaconfigapp.composite.Composite;
import com.hanbit.javaconfigapp.member.MemberList;

import java.util.HashMap;

public class Index extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Context context = Index.this;

        setContentView((LinearLayout) init(context).get("llIndex"));
        ((Button)init(context).get("btnIndex")).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Hi!", Toast.LENGTH_LONG).show();
                startActivity(new Intent(context, MemberList.class));
            }
        });
    }

    public HashMap<?,?> init(Context context) {
        Composite compo = new Composite(context, "Index");
        compo.execute();
        setContentView(compo.getFrame());
        return compo.getComponents();
    }
}