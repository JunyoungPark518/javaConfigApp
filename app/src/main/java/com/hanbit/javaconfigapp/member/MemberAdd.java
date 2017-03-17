package com.hanbit.javaconfigapp.member;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.hanbit.javaconfigapp.action.ICreate;
import com.hanbit.javaconfigapp.composite.Composite;
import com.hanbit.javaconfigapp.factory.WriteQuery;

import java.util.HashMap;

public class MemberAdd extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Context context = MemberAdd.this;
        HashMap<String, Object> view = (HashMap<String, Object>) init(context);
        final EditText etName = (EditText) view.get("etAddName");
        final EditText etPhone = (EditText) view.get("etAddPhone");
        final EditText etAge = (EditText) view.get("etAddAge");
        final EditText etAddress = (EditText) view.get("etAddAddress");
        final EditText etSalary = (EditText) view.get("etAddSalary");
        final ICreate service = new ICreate() {
            @Override
            public void create() {
                new InsertDAO(context).execQuery(String.format("INSERT INTO Member(name, phone, age, address, salary) VALUES ('%s','%s','%s','%s','%s');",
                        etName.getText().toString(),
                        etPhone.getText().toString(),
                        etAge.getText().toString(),
                        etAddress.getText().toString(),
                        etSalary.getText().toString())
                );
            }
        };

        Button btnAdd = (Button) view.get("btnAddConfirm");
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                service.create();
                startActivity(new Intent(context, MemberList.class));
            }
        });

        Button btnCancel = (Button) view.get("btnAddCancel");
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, MemberList.class));
            }
        });
        setContentView((LinearLayout) view.get("llAddFrame"));
    }

    class InsertDAO extends WriteQuery {
        public InsertDAO(Context context) {
            super(context);
        }

        @Override
        public void execQuery(String sql) {
            super.getDatabase().execSQL(sql);
        }
    }

    public HashMap<?,?> init(Context context) {
        Composite compo = new Composite(context, "MemberAdd");
        compo.execute();
        return compo.getComponents();
    }
}
