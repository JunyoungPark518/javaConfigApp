package com.hanbit.javaconfigapp.member;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
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
import com.hanbit.javaconfigapp.action.IDelete;
import com.hanbit.javaconfigapp.action.IList;
import com.hanbit.javaconfigapp.composite.CompositeCompo;
import com.hanbit.javaconfigapp.composite.LayoutParamsFactory;
import com.hanbit.javaconfigapp.composite.LinearLayoutFactory;
import com.hanbit.javaconfigapp.composite.TextViewFactory;
import com.hanbit.javaconfigapp.factory.ReadQuery;
import com.hanbit.javaconfigapp.factory.WriteQuery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
* @Create : 2017-03-16
* @Author : John J Park
* @Story :
* @Nested Class : ListDAO, DeleteDAO, MemberAdapter ViewHolder
* */
public class MemberList extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Context context = MemberList.this;
        setContentView((LinearLayout) init(context).get("llIndex"));
        final ListView listView = (ListView) init(context).get("lvMemberList");
        final HashMap<String, String> map = new HashMap<>();

        IList service = new IList() {
            @Override
            public List<?> list() {
                return new ListDAO(context).list("SELECT _id AS id, name, phone, age, address, salary FROM Member;");
            }
        };

        final ArrayList<Map<String,String>> memberMap = (ArrayList<Map<String, String>>) service.list();
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
                HashMap<String,String> tempMap = (HashMap) listView.getItemAtPosition(i);
                map.clear();
                map.put("id",tempMap.get("id"));
                new AlertDialog.Builder(context).setTitle("삭제").setMessage("삭제할까요?").setPositiveButton(
                        android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                               IDelete service = new IDelete() {
                                    @Override
                                    public void delete() {
                                        new DeleteDAO(context).execQuery(String.format("DELETE FROM Member WHERE _id = '%s'", map.get("id")));
                                        startActivity(new Intent(context, MemberList.class));
                                    }
                                };
                                service.delete();
                            }
                        }
                ).setNegativeButton(android.R.string.no, null).show();
                return false;
            }
        });
    }

    class ListDAO extends ReadQuery {
        public ListDAO(Context context) {
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

    class DeleteDAO extends WriteQuery {

        public DeleteDAO(Context context) {
            super(context);
        }

        @Override
        public void execQuery(String sql) {
            super.getDatabase().execSQL(sql);
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
            ViewHolder holder;
            LinearLayout uiItem;
            Context context = MemberList.this;
            if(v==null) {
                uiItem = LinearLayoutFactory.createLinearLayout(context, LayoutParamsFactory.createLayoutParams("mm"));
                uiItem.setPadding(8, 8, 8, 8);
                ImageView profileImg = new ImageView(context);
                profileImg.setLayoutParams(new ViewGroup.LayoutParams((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 80, getResources().getDisplayMetrics()),(int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 80, getResources().getDisplayMetrics())));
                TextView tvName = TextViewFactory.createTextView(context, LayoutParamsFactory.createLayoutParams("ww"), "Name", "B", 20);
                TextView tvPhone = TextViewFactory.createTextView(context, LayoutParamsFactory.createLayoutParams("ww"), "Phone", 17);
                uiItem.addView(profileImg);
                uiItem.addView(tvName);
                uiItem.addView(tvPhone);
                holder = new ViewHolder();
                holder.profileImg= profileImg;
                holder.tvName= tvName;
                holder.tvPhone= tvPhone;
                v=uiItem;
                v.setTag(holder);
            }else{
                holder= (ViewHolder) v.getTag();
            }
            holder.profileImg.setImageResource(photos[i]);
            holder.tvName.setText(list.get(i).get("name"));
            holder.tvPhone.setText(list.get(i).get("phone"));
            return v;
        }
    }

    static class ViewHolder{
        ImageView profileImg;
        TextView tvName,tvPhone;
    }

    public HashMap<?,?> init(Context context) {
        CompositeCompo compo = new CompositeCompo(context, "MemberList");
        compo.execute();
        setContentView(compo.getFrame());
        return compo.getComponents();
    }
}
