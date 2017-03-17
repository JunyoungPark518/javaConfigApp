package com.hanbit.javaconfigapp.member;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hanbit.javaconfigapp.composite.Composite;

import java.util.HashMap;

public class MemberAdd extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Context context = MemberAdd.this;

    }

    public HashMap<?,?> init(Context context) {
        Composite compo = new Composite(context, "MemberAdd");
        compo.execute();
        return compo.getComponents();
    }
}
