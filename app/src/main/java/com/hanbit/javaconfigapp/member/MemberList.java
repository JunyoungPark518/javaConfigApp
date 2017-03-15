package com.hanbit.javaconfigapp.member;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.hanbit.javaconfigapp.R;
import com.hanbit.javaconfigapp.action.IList;
import com.hanbit.javaconfigapp.itemfactory.LayoutParamsFactory;
import com.hanbit.javaconfigapp.factory.ReadQuery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberList extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Context context=MemberList.this;
        LinearLayout ui=new LinearLayout(context);
        ui.setLayoutParams(LayoutParamsFactory.createLayoutParams("mm"));
        ListView listView=new ListView(context);
        listView.setLayoutParams(LayoutParamsFactory.createLayoutParams("mm"));
        ui.addView(listView);
        setContentView(ui);

        final MemList memberList=new MemList(context);
        IList service=new IList() {
            @Override
            public List<?> list() {
                return memberList.list("select _id AS id, name, phone, age, address, salary from member;");
            }
        };
        final ArrayList<Map<String,String>>memberMap= (ArrayList<Map<String, String>>) service.list();
        listView.setAdapter(new MemberAdapter(memberMap,context));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int i, long l) {
                Intent intent=new Intent(context,MemberDetail.class);
                intent.putExtra("id",memberMap.get(i).get("id"));
                startActivity(intent);
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View v, int i, long l) {
                return false;
            }
        });
    }
    class MemList extends ReadQuery {
        public MemList(Context context) {
            super(context);
        }

        @Override
        public List<?> list(String sql) {
            Map<String,String> map;
            ArrayList<Map<String,String>> members=new ArrayList<>();
            SQLiteDatabase db=super.getDatabase();
            Cursor cursor=db.rawQuery(sql,null);
            if(cursor!=null) {
                if (cursor.moveToFirst()){
                    do{
                        map=new HashMap<>();
                        map.put("id",cursor.getString(cursor.getColumnIndex("id")));
                        map.put("name",cursor.getString(cursor.getColumnIndex("name")));
                        map.put("age",cursor.getString(cursor.getColumnIndex("age")));
                        map.put("phone",cursor.getString(cursor.getColumnIndex("phone")));
                        map.put("address",cursor.getString(cursor.getColumnIndex("address")));
                        map.put("salary",cursor.getString(cursor.getColumnIndex("salary")));
                        members.add(map);
                    }while(cursor.moveToNext());
                }
            }
            return members;
        }
    }
    class MemberAdapter extends BaseAdapter {
        ArrayList<Map<String,String>> list;
        LayoutInflater inflater;
        private int[] photos={R.drawable.cupcake,R.drawable.donut,R.drawable.eclair,R.drawable.froyo,R.drawable.gingerbread,R.drawable.honeycomb,R.drawable.icecream,R.drawable.jellybean,R.drawable.kitkat,R.drawable.lollipop,R.drawable.cupcake,R.drawable.donut};
        public MemberAdapter(ArrayList<?> list, Context context) {
            this.list = (ArrayList<Map<String, String>>) list;
            this.inflater= LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) { return list.get(i);}

        @Override
        public long getItemId(int i) { //index
            return i;
        }

        @Override
        public View getView(int i, View v, ViewGroup g) {
            ViewHoler holder;
            LinearLayout uiItem;
            if(v==null) {
                uiItem=new LinearLayout(MemberList.this);
                uiItem.setLayoutParams(LayoutParamsFactory.createLayoutParams("mm"));
                uiItem.setPadding(8, 8, 8, 8);
                ImageView profileImg = new ImageView(MemberList.this);
                profileImg.setLayoutParams(new ViewGroup.LayoutParams((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 80, getResources().getDisplayMetrics()),(int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 80, getResources().getDisplayMetrics())));
                TextView tvName = new TextView(MemberList.this);
                tvName.setLayoutParams(LayoutParamsFactory.createLayoutParams("ww"));
                tvName.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
                tvName.setText("Name");
                tvName.setTypeface(null, Typeface.BOLD);
                TextView tvPhone = new TextView(MemberList.this);
                tvPhone.setLayoutParams(LayoutParamsFactory.createLayoutParams("ww"));
                tvPhone.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 17);
                tvPhone.setText("Phone");
                uiItem.addView(profileImg);
                uiItem.addView(tvName);
                uiItem.addView(tvPhone);
                holder=new ViewHoler();
                holder.profileImg= profileImg;
                holder.tvName= tvName;
                holder.tvPhone= tvPhone;
                v=uiItem;
                v.setTag(holder);
            }else{
                holder= (ViewHoler) v.getTag();
            }
            holder.profileImg.setImageResource(photos[i]);
            holder.tvName.setText(list.get(i).get("name"));
            holder.tvPhone.setText(list.get(i).get("phone"));
            return v;
        }
    }
    static class ViewHoler{
        ImageView profileImg;
        TextView tvName,tvPhone;
    }
}
