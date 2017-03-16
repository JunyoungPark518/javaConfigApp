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

import com.hanbit.javaconfigapp.action.IDetail;
import com.hanbit.javaconfigapp.factory.ReadQuery;
import com.hanbit.javaconfigapp.itemfactory.ButtonFactory;
import com.hanbit.javaconfigapp.itemfactory.LayoutParamsFactory;
import com.hanbit.javaconfigapp.itemfactory.LinearLayoutFactory;
import com.hanbit.javaconfigapp.itemfactory.TextViewFactory;

import java.util.ArrayList;
import java.util.List;

public class MemberDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Context context = MemberDetail.this;
        final String id = this.getIntent().getExtras().getString("id").toString();
        LinearLayout.LayoutParams mm = LayoutParamsFactory.createLayoutParams("mm");
        LinearLayout.LayoutParams mw = LayoutParamsFactory.createLayoutParams("mw");
        LinearLayout.LayoutParams mww = LayoutParamsFactory.createLayoutParams("mw", 1);
        LinearLayout frame = LinearLayoutFactory.createLinearLayout(context, mm, "v");
        TextView tvDetail = TextViewFactory.createTextView(context, mw, "상세", 30, "center");
        frame.addView(tvDetail);
        IDetail service = new IDetail() {
            @Override
            public List<?> detail(String id) {
                return new DetailDAO(context).list("SELECT _id AS id, name, phone, age, address, salary FROM Member WHERE _id='"+id+"';");
            }
        };

        final ArrayList<String> member= (ArrayList<String>) service.detail(id);
        LinearLayout uiSub = LinearLayoutFactory.createLinearLayout(context, mw, "v");
        uiSub.addView(TextViewFactory.createTextView(context, mw, "NAME: " + member.get(1), 25, "left"));
        uiSub.addView(TextViewFactory.createTextView(context, mw, "PHONE: " + member.get(2), 25, "left"));
        uiSub.addView(TextViewFactory.createTextView(context, mw, "AGE: " + member.get(3), 25, "left"));
        uiSub.addView(TextViewFactory.createTextView(context, mw, "ADDRESS: " + member.get(4), 25, "left"));
        uiSub.addView(TextViewFactory.createTextView(context, mw, "SALARY: " + member.get(5), 25, "left"));
        frame.addView(uiSub);

        LinearLayout uiBtns = LinearLayoutFactory.createLinearLayout(context, mw, "h");
        uiBtns.addView(ButtonFactory.createButton(context, mww, "MY LOCATION"));
        uiBtns.addView(ButtonFactory.createButton(context, mww, "GOOGLE MAP"));
        frame.addView(uiBtns);

        LinearLayout uiBtns2 = LinearLayoutFactory.createLinearLayout(context, mw, "h");
        uiBtns2.addView(ButtonFactory.createButton(context, mww, "ALBUM"));
        uiBtns2.addView(ButtonFactory.createButton(context, mww, "MUSIC"));
        frame.addView(uiBtns2);

        LinearLayout uiBtns3 = LinearLayoutFactory.createLinearLayout(context, mw, "h");
        uiBtns3.addView(ButtonFactory.createButton(context, mww, "SMS"));
        uiBtns3.addView(ButtonFactory.createButton(context, mww, "MAIL"));
        frame.addView(uiBtns3);

        LinearLayout uiBtns4 = LinearLayoutFactory.createLinearLayout(context, mw, "h");
        Button btDial = ButtonFactory.createButton(context, mww, "DIAL", "#51b6e1");
        btDial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Intent.ACTION_DIAL, Uri.parse("tel: "+member.get(2)));

                it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(it);
            }
        });
        uiBtns4.addView(btDial);
        uiBtns4.addView(ButtonFactory.createButton(context, mww, "CALL"));
        frame.addView(uiBtns4);

        LinearLayout uiBtns5 = LinearLayoutFactory.createLinearLayout(context, mm);
        Button btUpdate = ButtonFactory.createButton(context, mww, "UPDATE", "#51b6e1");
        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(context, MemberUpdate.class);
                String sendingMsg = String.format("%s,%s,%s,%s,%s,%s", member.get(0), member.get(1), member.get(2), member.get(3), member.get(4), member.get(5));
                it.putExtra("data",sendingMsg);
                startActivity(it);
            }
        });
        uiBtns5.addView(btUpdate);
        Button btList = ButtonFactory.createButton(context, mww, "LIST", "#51b6e1");
        btList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, MemberList.class));
            }
        });
        uiBtns5.addView(btList);
        frame.addView(uiBtns5);
        setContentView(frame);
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
}
