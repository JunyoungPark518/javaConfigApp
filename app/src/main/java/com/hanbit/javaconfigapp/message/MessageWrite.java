package com.hanbit.javaconfigapp.message;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.hanbit.javaconfigapp.composite.Complex;
import com.hanbit.javaconfigapp.composite.Composite;
import com.hanbit.javaconfigapp.factory.ReadQuery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageWrite extends AppCompatActivity {
    String temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Context context= MessageWrite.this;
        final String data = this.getIntent().getExtras().getString("data").toString();
        final String receiver_id = data.split(",")[0];
        final String name = data.split(",")[1];
        final String phone = data.split(",")[2];
        final String age = data.split(",")[3];
        final String address = data.split(",")[4];
        final String salary = data.split(",")[5];
        LinearLayout.LayoutParams mm = Complex.LayoutParamsFactory.create("mm");
        LinearLayout frame = Complex.LinearLayoutFactory.create(context, mm);
        WebView wv = Complex.WebViewFactory.create(context, mm, "file:///android_asset/www/html/messageWrite.html");

        wv.addJavascriptInterface(new JavascriptInterface(){
            @android.webkit.JavascriptInterface
            @Override
            public void showToast(String message) {
                Toast.makeText(context, temp, Toast.LENGTH_LONG).show();
            }

            @Override
            public void sendMessage(String message) {
                temp = message;
            }
        }, "Hybrid");
        frame.addView(wv);
        setContentView(frame);
//        HashMap<String, Object> view = (HashMap<String, Object>) init(context);
//        final ListView lvMsg = (ListView) view.get("lvMsgWriteList");
//        IList service = new IList() {
//            @Override
//            public List<?> list() {
//                return null;
//            }
//        };
//        final ArrayList<Map<String,String>> msgList = (ArrayList<Map<String, String>>) service.list();
//        lvMsg.setAdapter(new MessageAdapter(msgList, context));
//        setContentView((LinearLayout) view.get("llMsgWriteFrame"));
    }

    public interface JavascriptInterface {
        public void showToast(String message);
        public void sendMessage(String message);
    }

    class ListDAO extends ReadQuery {
        public ListDAO(Context context) {
            super(context);
        }

        @Override
        public List<?> list(String sql) {
            Map<String, String> map;
            ArrayList<Map<String, String>> msg = new ArrayList<>();
            SQLiteDatabase db = super.getDatabase();
            Cursor c = db.rawQuery(sql, null);
            if(c!=null) {
                if(c.moveToFirst()) {
                    do {
                        map = new HashMap<>();
                        map.put("id", c.getString(c.getColumnIndex("id")));
                        map.put("title", c.getString(c.getColumnIndex("title")));
                        map.put("write_date", c.getString(c.getColumnIndex("write_date")));
                        map.put("sender",c.getString(c.getColumnIndex("sender")));
                        map.put("receiver",c.getString(c.getColumnIndex("receiver")));
                        msg.add(map);
                    } while(c.moveToNext());
                }
            }
            return msg;
        }
    }


    public HashMap<?,?> init(Context context) {
        Composite compo = new Composite(context, "MessageWrite");
        compo.execute();
        return compo.getComponents();
    }
}
