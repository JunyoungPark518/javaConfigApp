package com.hanbit.javaconfigapp.member;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hanbit.javaconfigapp.action.IDetail;
import com.hanbit.javaconfigapp.composite.Composite;
import com.hanbit.javaconfigapp.factory.ReadQuery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MemberDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Context context = MemberDetail.this;
        final String id = this.getIntent().getExtras().getString("id").toString();
        HashMap<String, Object> map = (HashMap<String, Object>) init(context);
        IDetail service = new IDetail() {
            @Override
            public List<?> detail(String id) {
                return new DetailDAO(context).list("SELECT _id AS id, name, phone, age, address, salary FROM Member WHERE _id='"+id+"';");
            }
        };
        final ArrayList<String> member= (ArrayList<String>) service.detail(id);
        TextView tv1 = (TextView) map.get("tvDetailName");
        tv1.setText("NAME: " + member.get(1));
        TextView tv2 = (TextView) map.get("tvDetailPhone");
        tv2.setText("PHONE: " + member.get(2));
        TextView tv3 = (TextView) map.get("tvDetailAge");
        tv3.setText("AGE: " + member.get(3));
        TextView tv4 = (TextView) map.get("tvDetailAddress");
        tv4.setText("ADDRESS: " + member.get(4));
        TextView tv5 = (TextView) map.get("tvDetailSalary");
        tv5.setText("SALARY: " + member.get(5));
        Button btnDetailDial = (Button) map.get("btnDetailDial");
        btnDetailDial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Intent.ACTION_DIAL, Uri.parse("tel: "+member.get(2)));
                it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(it);
            }
        });
        Button btnDetailUpdate = (Button) map.get("btnDetailUpdate");
        btnDetailUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(context, MemberUpdate.class);
                String sendingMsg = String.format("%s,%s,%s,%s,%s,%s", member.get(0), member.get(1), member.get(2), member.get(3), member.get(4), member.get(5));
                it.putExtra("data",sendingMsg);
                startActivity(it);
            }
        });
        Button btnDetailList = (Button) map.get("btnDetailList");
        btnDetailList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "PLAESE!!!", Toast.LENGTH_LONG).show();
                startActivity(new Intent(context, MemberList.class));
            }
        });
        setContentView((LinearLayout) map.get("llDetailFrame"));
    }
    class DetailDAO extends ReadQuery {
        public DetailDAO(Context context) {
            super(context);
        }
        @Override
        public List<?> list(String sql) {
            ArrayList<String> members = new ArrayList<>();
            SQLiteDatabase db = super.getDatabase();
            Cursor cursor = db.rawQuery(sql,null);
            if(cursor!=null) {
                if (cursor.moveToNext()){
                    members.add(0,cursor.getString(cursor.getColumnIndex("id")));
                    members.add(1,cursor.getString(cursor.getColumnIndex("name")));
                    members.add(2,cursor.getString(cursor.getColumnIndex("phone")));
                    members.add(3,cursor.getString(cursor.getColumnIndex("age")));
                    members.add(4,cursor.getString(cursor.getColumnIndex("address")));
                    members.add(5,cursor.getString(cursor.getColumnIndex("salary")));
                }
            }
            return members;
        }
    }

    public HashMap<?,?> init(Context context) {
        Composite compo = new Composite(context, "MemberDetail");
        compo.execute();
        return compo.getComponents();
    }
}
