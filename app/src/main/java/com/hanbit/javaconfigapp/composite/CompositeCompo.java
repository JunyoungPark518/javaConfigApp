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
                frame.addView((TextView) map.get("tvDetail"));
                LinearLayout temp = (LinearLayout) map.get("llDetailSub");
                temp.addView((TextView) map.get("tvDetailName"));
                temp.addView((TextView) map.get("tvDetailPhone"));
                temp.addView((TextView) map.get("tvDetailAge"));
                temp.addView((TextView) map.get("tvDetailAddress"));
                temp.addView((TextView) map.get("tvDetailSalary"));
                frame.addView(temp);
                LinearLayout temp1 = (LinearLayout) map.get("llDetailBtns");
                temp1.addView((Button) map.get("btnDetailMyLocation"));
                temp1.addView((Button) map.get("btnDetailGoogleMap"));
                frame.addView(temp1);
                LinearLayout temp2 = (LinearLayout) map.get("llDetailBtns");
                temp2.addView((Button) map.get("btnDetailAlbum"));
                temp2.addView((Button) map.get("btnDetailMusic"));
                frame.addView(temp2);
                LinearLayout temp3 = (LinearLayout) map.get("llDetailBtns");
                temp3.addView((Button) map.get("btnDetailSMS"));
                temp3.addView((Button) map.get("btnDetailMail"));
                frame.addView(temp3);
                LinearLayout temp4 = (LinearLayout) map.get("llDetailBtns");
                temp4.addView((Button) map.get("btnDetailDial"));
                temp4.addView((Button) map.get("btnDetailCall"));
                frame.addView(temp4);
                LinearLayout temp5 = (LinearLayout) map.get("llDetailBtns");
                temp5.addView((Button) map.get("btnDetailUpdate"));
                temp5.addView((Button) map.get("btnDetailList"));
                frame.addView(temp5);
                break;
        }

    }

    class ButtonCompo {
        IComposite service = new IComposite() {
            @Override
            public void execute() {
                LinearLayout.LayoutParams mww = LayoutParamsFactory.createLayoutParams("mw", 1);
                Button btn = new Button(context);
                switch(order) {
                    case "Index" :
                        int[] btnMarginArr = {0, 300, 0, 0};
                        btn = ButtonFactory.createButton(context, LayoutParamsFactory.createLayoutParams("mw"), "Button", "#00ff00", btnMarginArr);
                        map.put("btnIndex",btn);
                        break;
                    case "MemberDetail":
                        map.put("btnDetailMyLocation",ButtonFactory.createButton(context, mww, "MY LOCATION"));
                        map.put("btnDetailGoogleMap",ButtonFactory.createButton(context, mww, "GOOGLE MAP"));
                        map.put("btnDetailAlbum",ButtonFactory.createButton(context, mww, "ALBUM"));
                        map.put("btnDetailMusic",ButtonFactory.createButton(context, mww, "MUSIC"));
                        map.put("btnDetailSMS",ButtonFactory.createButton(context, mww, "SMS"));
                        map.put("btnDetailMail",ButtonFactory.createButton(context, mww, "MAIL"));
                        map.put("btnDetailDial",ButtonFactory.createButton(context, mww, "DIAL", "#51b6e1"));
                        map.put("btnDetailCall",ButtonFactory.createButton(context, mww, "CALL"));
                        map.put("btnDetailUpdate",ButtonFactory.createButton(context, mww, "UPDATE", "#51b6e1"));
                        map.put("btnDetailList",ButtonFactory.createButton(context, mww, "LIST", "#51b6e1"));
                        break;
                }
            }
        };
    }

    class TextViewCompo {
        IComposite service = new IComposite() {
            @Override
            public void execute() {
                LinearLayout.LayoutParams mw = LayoutParamsFactory.createLayoutParams("mw");
                TextView tv = new TextView(context);
                switch (order) {
                    case "Index":
                        tv = TextViewFactory.createTextView(context, LayoutParamsFactory.createLayoutParams("mw"), "HELLO", 30);
                        ViewGroup.MarginLayoutParams marginIndex = new ViewGroup.MarginLayoutParams(LayoutParamsFactory.createLayoutParams("mw"));
                        marginIndex.setMargins(0, 200, 0, 0);
                        tv.setLayoutParams(new LinearLayout.LayoutParams(marginIndex));
                        map.put("tvIndex",tv);
                        break;
                    case "MemberDetail":
                        map.put("tvDetail",TextViewFactory.createTextView(context, mw, "상세", 30, "center"));
                        map.put("tvDetailName",TextViewFactory.createTextView(context, mw, "", 25, "left"));
                        map.put("tvDetailPhone",TextViewFactory.createTextView(context, mw, "", 25, "left"));
                        map.put("tvDetailAge",TextViewFactory.createTextView(context, mw, "", 25, "left"));
                        map.put("tvDetailAddress",TextViewFactory.createTextView(context, mw, "", 25, "left"));
                        map.put("tvDetailSalary",TextViewFactory.createTextView(context, mw, "", 25, "left"));
                        break;
                    case "Temp":
                        tv.setText("");
                        tv.setGravity(Gravity.CENTER);
                        tv.setBackgroundColor(Color.parseColor("#FFFFFF"));
                        tv.setTextSize(30);
                        ViewGroup.MarginLayoutParams marginTmp = new ViewGroup.MarginLayoutParams(LayoutParamsFactory.createLayoutParams("mw"));
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
                        et.setLayoutParams(LayoutParamsFactory.createLayoutParams("mw"));
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
                LinearLayout.LayoutParams mm = LayoutParamsFactory.createLayoutParams("mm");
                LinearLayout.LayoutParams mw = LayoutParamsFactory.createLayoutParams("mw");
                LinearLayout ll;
                switch (order) {
                    case "Index":case "MemberList":
                        int[] llMarginArr = {0, 200, 0, 0};
                        ll = LinearLayoutFactory.createLinearLayout(context, mm, "v", llMarginArr);
                        map.put("llIndex",ll);
                        break;
                    case "MemberDetail":
                        map.put("llDetailFrame",LinearLayoutFactory.createLinearLayout(context, mm, "v"));
                        map.put("llDetailSub",LinearLayoutFactory.createLinearLayout(context, mw, "v"));
                        map.put("llDetailBtns",LinearLayoutFactory.createLinearLayout(context, mw, "h"));
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
                        lv.setLayoutParams(LayoutParamsFactory.createLayoutParams("mm"));
                        map.put("lvMemberList", lv);
                        break;
                }
            }
        };
    }
}
