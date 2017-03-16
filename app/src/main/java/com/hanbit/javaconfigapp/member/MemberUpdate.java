package com.hanbit.javaconfigapp.member;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.hanbit.javaconfigapp.action.IUpdate;
import com.hanbit.javaconfigapp.composite.ButtonFactory;
import com.hanbit.javaconfigapp.composite.CompositeCompo;
import com.hanbit.javaconfigapp.composite.EditTextFactory;
import com.hanbit.javaconfigapp.composite.LayoutParamsFactory;
import com.hanbit.javaconfigapp.composite.LinearLayoutFactory;
import com.hanbit.javaconfigapp.composite.TextViewFactory;
import com.hanbit.javaconfigapp.factory.WriteQuery;

import java.util.HashMap;

public class MemberUpdate extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = this.getIntent();
        LinearLayout.LayoutParams mw = LayoutParamsFactory.createLayoutParams("mw");
        LinearLayout.LayoutParams ww = LayoutParamsFactory.createLayoutParams("ww");
        LinearLayout.LayoutParams mww = LayoutParamsFactory.createLayoutParams("mw", 1);
        final Context context= MemberUpdate.this;
        final String data = intent.getExtras().getString("data").toString();
        final String id = data.split(",")[0];
        final String name = data.split(",")[1];
        final String phone = data.split(",")[2];
        final String age = data.split(",")[3];
        final String address = data.split(",")[4];
        final String salary = data.split(",")[5];

        LinearLayout frame = LinearLayoutFactory.createLinearLayout(context, mww, "v");
        LinearLayout uiName = LinearLayoutFactory.createLinearLayout(context, mw);
        uiName.addView(TextViewFactory.createTextView(context, ww, "NAME: ", 20));
        uiName.addView(TextViewFactory.createTextView(context, ww, name, 20));
        frame.addView(uiName);

        LinearLayout llPhone = LinearLayoutFactory.createLinearLayout(context, mw);
        final EditText etPhoneContent = EditTextFactory.createEditText(context, ww, phone, 20);
        llPhone.addView(TextViewFactory.createTextView(context, ww, "PHONE: ", 20));
        llPhone.addView(etPhoneContent);
        frame.addView(llPhone);

        LinearLayout llAge = LinearLayoutFactory.createLinearLayout(context, mw);
        llAge.addView(TextViewFactory.createTextView(context, ww, "AGE: ", 20));
        llAge.addView(TextViewFactory.createTextView(context, ww, age, 20));
        frame.addView(llAge);

        LinearLayout llAddress = LinearLayoutFactory.createLinearLayout(context, mw);
        final EditText etAddressContent = EditTextFactory.createEditText(context, ww, address, 20);
        llAddress.addView(TextViewFactory.createTextView(context, ww, "ADDRESS: ", 20));
        llAddress.addView(etAddressContent);
        frame.addView(llAddress);

        LinearLayout llSalary = LinearLayoutFactory.createLinearLayout(context, mw);
        final EditText etSalaryContent = EditTextFactory.createEditText(context, ww, salary, 20);
        llSalary.addView(TextViewFactory.createTextView(context, ww, "SALARY: ", 20));
        llSalary.addView(etSalaryContent);
        frame.addView(llSalary);

        LinearLayout llButton = LinearLayoutFactory.createLinearLayout(context, mw);
        Button btCancel = ButtonFactory.createButton(context, mww, "CANCEL", 20);
        Button btConfirm = ButtonFactory.createButton(context, mww, "CONFIRM", 20);
        llButton.addView(btCancel);
        llButton.addView(btConfirm);
        frame.addView(llButton);

        setContentView(frame);
        final HashMap<String,String> map = new HashMap<>();
        final IUpdate service=new IUpdate() {
            @Override
            public void update() {
                new UpdateDAO(context).execQuery(String.format("UPDATE Member SET phone='%s',address='%s',salary='%s' WHERE _id='%s'",
                        map.get("phone").equals("") ? phone : (String) map.get("phone"),
                        map.get("address").equals("") ? address : (String) map.get("address"),
                        map.get("salary").equals("") ? salary : (String) map.get("salary"),
                        id));
            }
        };

        btConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                map.put("phone",etPhoneContent.getText().toString());
                map.put("address",etAddressContent.getText().toString());
                map.put("salary",etSalaryContent.getText().toString());
                service.update();
                Intent intent=new Intent(context, MemberDetail.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });

        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(context, MemberDetail.class);
                it.putExtra("id",id);
                startActivity(it);
            }
        });

    }

    class UpdateDAO extends WriteQuery {
        public UpdateDAO(Context context) {
            super(context);
        }

        @Override
        public void execQuery(String sql) {
            super.getDatabase().execSQL(sql);
        }

    }

    public HashMap<?,?> init(Context context) {
        CompositeCompo compo = new CompositeCompo(context, "MemberUpdate");
        compo.execute();
        setContentView(compo.getFrame());
        return compo.getComponents();
    }
}
