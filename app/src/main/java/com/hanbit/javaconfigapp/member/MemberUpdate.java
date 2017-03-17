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
import com.hanbit.javaconfigapp.composite.Composite;
import com.hanbit.javaconfigapp.factory.WriteQuery;

import java.util.HashMap;

public class MemberUpdate extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Context context= MemberUpdate.this;
        final String data = this.getIntent().getExtras().getString("data").toString();
        final String id = data.split(",")[0];
        final String name = data.split(",")[1];
        final String phone = data.split(",")[2];
        final String age = data.split(",")[3];
        final String address = data.split(",")[4];
        final String salary = data.split(",")[5];
        final HashMap<String, Object> init = (HashMap<String, Object>) init(context);

        final EditText etNameContent = (EditText) init.get("etUpdateNameContent");
        etNameContent.setText(name);
        final EditText etPhoneContent = (EditText) init.get("etUpdatePhoneContent");
        etPhoneContent.setText(phone);
        final EditText etAgeContent = (EditText) init.get("etUpdateAgeContent");
        etAgeContent.setText(age);
        final EditText etAddressContent = (EditText) init.get("etUpdateAddressContent");
        etAddressContent.setText(address);
        final EditText etSalaryContent = (EditText) init.get("etUpdateSalaryContent");
        etSalaryContent.setText(salary);
        Button btCancel = (Button) init.get("btnUpdateCancel");
        Button btConfirm = (Button) init.get("btnUpdateConfirm");

        final HashMap<String,String> map = new HashMap<>();
        final IUpdate service=new IUpdate() {
            @Override
            public void update() {
                new UpdateDAO(context).execQuery(String.format("UPDATE Member SET name='%s', phone='%s', age='%s', address='%s',salary='%s' WHERE _id='%s'",
                        map.get("name").equals("") ? name : (String) map.get("name"),
                        map.get("phone").equals("") ? phone : (String) map.get("phone"),
                        map.get("age").equals("") ? age : (String) map.get("age"),
                        map.get("address").equals("") ? address : (String) map.get("address"),
                        map.get("salary").equals("") ? salary : (String) map.get("salary"),
                        id));
            }
        };

        btConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                map.put("name", etNameContent.getText().toString());
                map.put("phone", etPhoneContent.getText().toString());
                map.put("age", etAgeContent.getText().toString());
                map.put("address", etAddressContent.getText().toString());
                map.put("salary", etSalaryContent.getText().toString());
                service.update();
                Intent intent = new Intent(context, MemberDetail.class);
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
        setContentView((LinearLayout) init.get("llUpdateFrame"));
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
        Composite compo = new Composite(context, "MemberUpdate");
        compo.execute();
        return compo.getComponents();
    }
}
