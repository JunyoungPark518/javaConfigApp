package com.hanbit.javaconfigapp.composite;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.HashMap;

/**
 * Created by hb2005 on 2017-03-16.
 */

public class CompositeCompo {
    interface IComposite { void execute(); };
    final HashMap<String, Object> map = new HashMap<>();
    String order;
    Context context;
    LinearLayout frame;

    public LinearLayout getFrame() {
        return frame;
    }

    public HashMap<String, Object> getComponents() {
        return map;
    }

    public CompositeCompo(Context context, String order) {
        this.context = context;
        this.order = order;
    }

    public void execute() {
        new ButtonCompo().service.execute();
        new TextViewCompo().service.execute();
        new EditTextCompo().service.execute();
        new LinearLayoutCompo().service.execute();
        new ListViewCompo().service.execute();
        switch (order) {
            case "Index":
                frame = (LinearLayout) map.get("llIndex");
                frame.addView((TextView) map.get("tvIndex"));
                frame.addView((Button) map.get("btnIndex"));
                break;
            case "MemberList":
                frame = (LinearLayout) map.get("llIndex");
                frame.addView((ListView) map.get("lvMemberList"));
                break;
            case "MemberDetail":
                frame = (LinearLayout) map.get("llDetailFrame");

                frame.addView((TextView) map.get("tvDetailName"));
//                frame.addView((TextView) map.get("tvDetail"));
//                LinearLayout temp = (LinearLayout) map.get("llDetailSub");
//                temp.addView((TextView) map.get("tvDetailName"));
//                temp.addView((TextView) map.get("tvDetailPhone"));
//                temp.addView((TextView) map.get("tvDetailAge"));
//                temp.addView((TextView) map.get("tvDetailAddress"));
//                temp.addView((TextView) map.get("tvDetailSalary"));
//                frame.addView(temp);
//                LinearLayout temp1 = (LinearLayout) map.get("llDetailBtns");
//                temp1.addView((Button) map.get("btnDetailMyLocation"));
//                temp1.addView((Button) map.get("btnDetailGoogleMap"));
//                frame.addView(temp1);
//                LinearLayout temp2 = (LinearLayout) map.get("llDetailBtns");
//                temp2.addView((Button) map.get("btnDetailAlbum"));
//                temp2.addView((Button) map.get("btnDetailMusic"));
//                frame.addView(temp2);
//                LinearLayout temp3 = (LinearLayout) map.get("llDetailBtns");
//                temp3.addView((Button) map.get("btnDetailSMS"));
//                temp3.addView((Button) map.get("btnDetailMail"));
//                frame.addView(temp3);
//                LinearLayout temp4 = (LinearLayout) map.get("llDetailBtns");
//                temp4.addView((Button) map.get("btnDetailDial"));
//                temp4.addView((Button) map.get("btnDetailCall"));
//                frame.addView(temp4);
//                LinearLayout temp5 = (LinearLayout) map.get("llDetailBtns");
//                temp5.addView((Button) map.get("btnDetailUpdate"));
//                temp5.addView((Button) map.get("btnDetailList"));
//                frame.addView(temp5);
                break;
        }

    }

    class ButtonCompo {
        IComposite service = new IComposite() {
            @Override
            public void execute() {
                LinearLayout.LayoutParams mww = CompositeFactory.createLayoutParams("mw", 1);
                Button btn = new Button(context);
                switch(order) {
                    case "Index" :
                        int[] btnMarginArr = {0, 300, 0, 0};
                        btn = CompositeFactory.createButton(context, CompositeFactory.createLayoutParams("mw"), "Button", "#00ff00", btnMarginArr);
                        map.put("btnIndex",btn);
                        break;
                    case "MemberDetail":
                        map.put("btnDetailMyLocation",CompositeFactory.createButton(context, mww, "MY LOCATION"));
                        map.put("btnDetailGoogleMap",CompositeFactory.createButton(context, mww, "GOOGLE MAP"));
                        map.put("btnDetailAlbum",CompositeFactory.createButton(context, mww, "ALBUM"));
                        map.put("btnDetailMusic",CompositeFactory.createButton(context, mww, "MUSIC"));
                        map.put("btnDetailSMS",CompositeFactory.createButton(context, mww, "SMS"));
                        map.put("btnDetailMail",CompositeFactory.createButton(context, mww, "MAIL"));
                        map.put("btnDetailDial",CompositeFactory.createButton(context, mww, "DIAL", "#51b6e1"));
                        map.put("btnDetailCall",CompositeFactory.createButton(context, mww, "CALL"));
                        map.put("btnDetailUpdate",CompositeFactory.createButton(context, mww, "UPDATE", "#51b6e1"));
                        map.put("btnDetailList",CompositeFactory.createButton(context, mww, "LIST", "#51b6e1"));
                        break;
                }
            }
        };
    }

    class TextViewCompo {
        IComposite service = new IComposite() {
            @Override
            public void execute() {
                LinearLayout.LayoutParams mw = CompositeFactory.createLayoutParams("mw");
                TextView tv = new TextView(context);
                switch (order) {
                    case "Index":
                        tv = CompositeFactory.createTextView(context, CompositeFactory.createLayoutParams("mw"), "HELLO", 30);
                        ViewGroup.MarginLayoutParams marginIndex = new ViewGroup.MarginLayoutParams(CompositeFactory.createLayoutParams("mw"));
                        marginIndex.setMargins(0, 200, 0, 0);
                        tv.setLayoutParams(new LinearLayout.LayoutParams(marginIndex));
                        map.put("tvIndex",tv);
                        break;
                    case "MemberDetail":
                        map.put("tvDetail",CompositeFactory.createTextView(context, mw, "상세", 30, "center"));
                        map.put("tvDetailName",CompositeFactory.createTextView(context, mw, "", 25, "left"));
                        map.put("tvDetailPhone",CompositeFactory.createTextView(context, mw, "", 25, "left"));
                        map.put("tvDetailAge",CompositeFactory.createTextView(context, mw, "", 25, "left"));
                        map.put("tvDetailAddress",CompositeFactory.createTextView(context, mw, "", 25, "left"));
                        map.put("tvDetailSalary",CompositeFactory.createTextView(context, mw, "", 25, "left"));
                        break;
                    case "Temp":
                        tv.setText("");
                        tv.setGravity(Gravity.CENTER);
                        tv.setBackgroundColor(Color.parseColor("#FFFFFF"));
                        tv.setTextSize(30);
                        ViewGroup.MarginLayoutParams marginTmp = new ViewGroup.MarginLayoutParams(CompositeFactory.createLayoutParams("mw"));
                        marginTmp.setMargins(0, 200, 0, 0);
                        tv.setLayoutParams(new LinearLayout.LayoutParams(marginTmp));
                        map.put("tvTemp",tv);
                        break;
                }
            }
        };
    }

    class EditTextCompo {
        IComposite service = new IComposite() {
            @Override
            public void execute() {
                EditText et = new EditText(context);
                switch (order) {
                    case "Index":
                        et.setText("");
                        et.setLayoutParams(CompositeFactory.createLayoutParams("mw"));
                        map.put("etIndex",et);
                        break;
                }

            }
        };
    }

    class LinearLayoutCompo {
        IComposite service = new IComposite() {
            @Override
            public void execute() {
                LinearLayout.LayoutParams mm = CompositeFactory.createLayoutParams("mm");
                LinearLayout.LayoutParams mw = CompositeFactory.createLayoutParams("mw");
                LinearLayout ll;
                switch (order) {
                    case "Index":case "MemberList":
                        int[] llMarginArr = {0, 200, 0, 0};
                        ll = CompositeFactory.createLinearLayout(context, mm, "v", llMarginArr);
                        map.put("llIndex",ll);
                        break;
                    case "MemberDetail":
                        map.put("llDetailFrame",CompositeFactory.createLinearLayout(context, mm, "v"));
                        map.put("llDetailSub",CompositeFactory.createLinearLayout(context, mw, "v"));
                        map.put("llDetailBtns",CompositeFactory.createLinearLayout(context, mw, "h"));
                        break;
                }
            }
        };
    }

    class ListViewCompo {
        IComposite service = new IComposite() {
            @Override
            public void execute() {
                ListView lv;
                switch(order) {
                    case "MemberList":
                        lv = new ListView(context);
                        lv.setLayoutParams(CompositeFactory.createLayoutParams("mm"));
                        map.put("lvMemberList", lv);
                        break;
                }
            }
        };
    }
}
