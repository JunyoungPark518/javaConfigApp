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
import com.hanbit.javaconfigapp.composite.CompositeCompo;
import com.hanbit.javaconfigapp.composite.Complex;
import com.hanbit.javaconfigapp.factory.WriteQuery;

import java.util.HashMap;

public class MemberUpdate extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = this.getIntent();
        LinearLayout.LayoutParams mw = Complex.LayoutParamsFactory.create("mw");
        LinearLayout.LayoutParams ww = Complex.LayoutParamsFactory.create("ww");
        LinearLayout.LayoutParams mww = Complex.LayoutParamsFactory.create("mw", 1);
        final Context context= MemberUpdate.this;
        final String data = intent.getExtras().getString("data").toString();
        final String id = data.split(",")[0];
        final String name = data.split(",")[1];
        final String phone = data.split(",")[2];
        final String age = data.split(",")[3];
        final String address = data.split(",")[4];
        final String salary = data.split(",")[5];

        LinearLayout frame = Complex.LinearLayoutFactory.create(context, mww, "v");
        LinearLayout uiName = Complex.LinearLayoutFactory.create(context, mw);
        uiName.addView(Complex.TextViewFactory.create(context, ww, "NAME: ", 20));
        uiName.addView(Complex.TextViewFactory.create(context, ww, name, 20));
        frame.addView(uiName);

        LinearLayout llPhone = Complex.LinearLayoutFactory.create(context, mw);
        final EditText etPhoneContent = Complex.EditTextFactory.create(context, ww, phone, 20);
        llPhone.addView(Complex.TextViewFactory.create(context, ww, "PHONE: ", 20));
        llPhone.addView(etPhoneContent);
        frame.addView(llPhone);

        LinearLayout llAge = Complex.LinearLayoutFactory.create(context, mw);
        llAge.addView(Complex.TextViewFactory.create(context, ww, "AGE: ", 20));
        llAge.addView(Complex.TextViewFactory.create(context, ww, age, 20));
        frame.addView(llAge);

        LinearLayout llAddress = Complex.LinearLayoutFactory.create(context, mw);
        final EditText etAddressContent = Complex.EditTextFactory.create(context, ww, address, 20);
        llAddress.addView(Complex.TextViewFactory.create(context, ww, "ADDRESS: ", 20));
        llAddress.addView(etAddressContent);
        frame.addView(llAddress);

        LinearLayout llSalary = Complex.LinearLayoutFactory.create(context, mw);
        final EditText etSalaryContent = Complex.EditTextFactory.create(context, ww, salary, 20);
        llSalary.addView(Complex.TextViewFactory.create(context, ww, "SALARY: ", 20));
        llSalary.addView(etSalaryContent);
        frame.addView(llSalary);

        LinearLayout llButton = Complex.LinearLayoutFactory.create(context, mw);
        Button btCancel = Complex.ButtonFactory.create(context, mww, "CANCEL", 20);
        Button btConfirm = Complex.ButtonFactory.create(context, mww, "CONFIRM", 20);
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
