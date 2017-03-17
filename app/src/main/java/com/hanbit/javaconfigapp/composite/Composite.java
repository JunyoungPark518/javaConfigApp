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

import static com.hanbit.javaconfigapp.composite.Complex.LayoutParamsFactory.create;

/**
 * Created by hb2005 on 2017-03-16.
 */

public class Composite {
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

    public Composite(Context context, String order) {
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
                frame.addView((Button) map.get("btnIndexHTML"));
                break;
            case "MemberList":
                frame = (LinearLayout) map.get("llMemberListFrame");
                LinearLayout head = (LinearLayout) map.get("llMemberListHead");
                head.addView((Button) map.get("btnMemberListBack"));
                head.addView((TextView) map.get("tvMemberListFriend"));
                head.addView((Button) map.get("btnMemberListAdd"));
                frame.addView(head);
                LinearLayout body = (LinearLayout) map.get("llMemberListBody");
                body.addView((EditText) map.get("etMemberListFind"));
                body.addView((ListView) map.get("lvMemberList"));
                frame.addView(body);
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
                LinearLayout temp1 = (LinearLayout) map.get("llDetailBtns1");
                temp1.addView((Button) map.get("btnDetailMyLocation"));
                temp1.addView((Button) map.get("btnDetailGoogleMap"));
                frame.addView(temp1);
                LinearLayout temp2 = (LinearLayout) map.get("llDetailBtns2");
                temp2.addView((Button) map.get("btnDetailAlbum"));
                temp2.addView((Button) map.get("btnDetailMusic"));
                frame.addView(temp2);
                LinearLayout temp3 = (LinearLayout) map.get("llDetailBtns3");
                temp3.addView((Button) map.get("btnDetailSMS"));
                temp3.addView((Button) map.get("btnDetailMail"));
                frame.addView(temp3);
                LinearLayout temp4 = (LinearLayout) map.get("llDetailBtns4");
                temp4.addView((Button) map.get("btnDetailDial"));
                temp4.addView((Button) map.get("btnDetailCall"));
                frame.addView(temp4);
                LinearLayout temp5 = (LinearLayout) map.get("llDetailBtns5");
                temp5.addView((Button) map.get("btnDetailUpdate"));
                temp5.addView((Button) map.get("btnDetailList"));
                frame.addView(temp5);
                break;
            case "MemberUpdate":
                frame = (LinearLayout) map.get("llUpdateFrame");
                LinearLayout button = (LinearLayout) map.get("llUpdateButton");
                button.addView((Button) map.get("btnUpdateCancel"));
                button.addView((Button) map.get("btnUpdateConfirm"));
                frame.addView(button);
                LinearLayout name = (LinearLayout) map.get("llUpdateName");
                name.addView((TextView) map.get("tvUpdateName"));
                name.addView((EditText) map.get("etUpdateNameContent"));
                frame.addView(name);
                LinearLayout phone = (LinearLayout) map.get("llUpdatePhone");
                phone.addView((TextView) map.get("tvUpdatePhone"));
                phone.addView((EditText) map.get("etUpdatePhoneContent"));
                frame.addView(phone);
                LinearLayout age = (LinearLayout) map.get("llUpdateAge");
                age.addView((TextView) map.get("tvUpdateAge"));
                age.addView((EditText) map.get("etUpdateAgeContent"));
                frame.addView(age);
                LinearLayout address = (LinearLayout) map.get("llUpdateAddress");
                address.addView((TextView) map.get("tvUpdateAddress"));
                address.addView((EditText) map.get("etUpdateAddressContent"));
                frame.addView(address);
                LinearLayout salary = (LinearLayout) map.get("llUpdateSalary");
                salary.addView((TextView) map.get("tvUpdateSalary"));
                salary.addView((EditText) map.get("etUpdateSalaryContent"));
                frame.addView(salary);
                break;
            case "MemberAdd":
                frame = (LinearLayout) map.get("llAddFrame");
                LinearLayout buttonAdd = (LinearLayout) map.get("llAddButton");
                buttonAdd.addView((Button) map.get("btnAddCancel"));
                buttonAdd.addView((Button) map.get("btnAddConfirm"));
                frame.addView(buttonAdd);
                LinearLayout listAdd = (LinearLayout) map.get("llAddList");
                listAdd.addView((EditText) map.get("etAddName"));
                listAdd.addView((EditText) map.get("etAddPhone"));
                listAdd.addView((EditText) map.get("etAddAge"));
                listAdd.addView((EditText) map.get("etAddAddress"));
                listAdd.addView((EditText) map.get("etAddSalary"));
                frame.addView(listAdd);
                break;
            case "MessageWrite":
                frame = (LinearLayout) map.get("llMsgWriteFrame");
                LinearLayout receiver = (LinearLayout) map.get("llMsgWriteReceiver");
                receiver.addView((EditText) map.get("etMsgWriteReceiver"));
                frame.addView(receiver);
                frame.addView((ListView) map.get("lvMsgWriteList"));
                LinearLayout msg = (LinearLayout) map.get("llMsgWriteMessage");
                msg.addView((EditText) map.get("etMsgWriteMessage"));
                msg.addView((Button) map.get("btnMsgWriteSend"));
                frame.addView(msg);
                break;
        }

    }

    class ButtonCompo {
        IComposite service = new IComposite() {
            @Override
            public void execute() {
                LinearLayout.LayoutParams mw = create("mw");
                LinearLayout.LayoutParams mww = create("mw", 1);
                LinearLayout.LayoutParams ww = create("ww");
                switch(order) {
                    case "Index" :
                        int[] btnMarginArr = {0, 300, 0, 0};
                        Button btn = Complex.ButtonFactory.create(context, mw, "Start Talk", "#FAE100", btnMarginArr);
                        map.put("btnIndex",btn);
                        map.put("btnIndexHTML", Complex.ButtonFactory.create(context, mw, "GO HTML", "#AAE100"));
                        break;
                    case "MemberList":
                        Button btnMemberListManage = Complex.ButtonFactory.create(context, mww, "BACK", "#625253");
                        btnMemberListManage.setTextColor(Color.parseColor("#ffffff"));
                        map.put("btnMemberListBack", btnMemberListManage);
                        map.put("btnMemberListAdd", Complex.ButtonFactory.create(context, mww, "+", "#ffffff"));
                        break;
                    case "MemberDetail":
                        map.put("btnDetailMyLocation", Complex.ButtonFactory.create(context, mww, "MY LOCATION"));
                        map.put("btnDetailGoogleMap", Complex.ButtonFactory.create(context, mww, "GOOGLE MAP"));
                        map.put("btnDetailAlbum", Complex.ButtonFactory.create(context, mww, "ALBUM"));
                        map.put("btnDetailMusic", Complex.ButtonFactory.create(context, mww, "MUSIC"));
                        map.put("btnDetailSMS", Complex.ButtonFactory.create(context, mww, "SMS"));
                        map.put("btnDetailMail", Complex.ButtonFactory.create(context, mww, "MAIL"));
                        map.put("btnDetailDial", Complex.ButtonFactory.create(context, mww, "DIAL"));
                        map.put("btnDetailCall", Complex.ButtonFactory.create(context, mww, "CALL"));
                        map.put("btnDetailUpdate", Complex.ButtonFactory.create(context, mww, "UPDATE"));
                        map.put("btnDetailList", Complex.ButtonFactory.create(context, mww, "LIST"));
                        break;
                    case "MemberUpdate":
                        Button btnUpdateCancel = Complex.ButtonFactory.create(context, mww, "CANCEL", "#725253");
                        btnUpdateCancel.setTextColor(Color.parseColor("#ffffff"));
                        map.put("btnUpdateCancel", btnUpdateCancel);
                        Button btnUpdateConfirm = Complex.ButtonFactory.create(context, mww, "CONFIRM", "#725253");
                        btnUpdateConfirm.setTextColor(Color.parseColor("#ffffff"));
                        map.put("btnUpdateConfirm", btnUpdateConfirm);
                        break;
                    case "MemberAdd":
                        Button btnAddCancel = Complex.ButtonFactory.create(context, mww, "CANCEL", "#725253");
                        btnAddCancel.setTextColor(Color.parseColor("#ffffff"));
                        map.put("btnAddCancel", btnAddCancel);
                        Button btnAddConfirm = Complex.ButtonFactory.create(context, mww, "ADD", "#725253");
                        btnAddConfirm.setTextColor(Color.parseColor("#ffffff"));
                        map.put("btnAddConfirm", btnAddConfirm);
                        break;
                    case "MessageWrite":
                        map.put("btnMsgWriteSend", Complex.ButtonFactory.create(context, mww, "SEND", 20));
                        break;
                }
            }
        };
    }

    class TextViewCompo {
        IComposite service = new IComposite() {
            @Override
            public void execute() {
                LinearLayout.LayoutParams mw = create("mw");
                LinearLayout.LayoutParams ww = create("ww");
                LinearLayout.LayoutParams mww1 = create("mw",1);
                TextView tv = new TextView(context);
                switch (order) {
                    case "Index":
                        tv = Complex.TextViewFactory.create(context, mw, "토크 시작하기", 30);
                        tv.setTextColor(Color.parseColor("#ffffff"));
                        ViewGroup.MarginLayoutParams marginIndex = new ViewGroup.MarginLayoutParams(create("mw"));
                        marginIndex.setMargins(0, 100, 0, 100);
                        tv.setLayoutParams(new LinearLayout.LayoutParams(marginIndex));
                        tv.setGravity(1);
                        map.put("tvIndex",tv);
                        break;
                    case "MemberList":
                        TextView tvMemberListFriend = Complex.TextViewFactory.create(context, mww1, "Friends", 20, "center");
                        tvMemberListFriend.setTextColor(Color.parseColor("#FAE100"));
                        map.put("tvMemberListFriend", tvMemberListFriend);
                        break;
                    case "MemberDetail":
                        map.put("tvDetail", Complex.TextViewFactory.create(context, mw, "상세", 30, "center", "#725253", "#ffffff"));
                        map.put("tvDetailName", Complex.TextViewFactory.create(context, mw, "Name:", 25, "left"));
                        map.put("tvDetailPhone", Complex.TextViewFactory.create(context, mw, "Phone:", 25, "left"));
                        map.put("tvDetailAge", Complex.TextViewFactory.create(context, mw, "Age:", 25, "left"));
                        map.put("tvDetailAddress", Complex.TextViewFactory.create(context, mw, "Address:", 25, "left"));
                        map.put("tvDetailSalary", Complex.TextViewFactory.create(context, mw, "Salary:", 25, "left"));
                        break;
                    case "MemberUpdate":
                        map.put("tvUpdateName",Complex.TextViewFactory.create(context, ww, "NAME: ", 25));
                        map.put("tvUpdatePhone",Complex.TextViewFactory.create(context, ww, "PHONE: ", 25));
                        map.put("tvUpdateAge",Complex.TextViewFactory.create(context, ww, "AGE: ", 25));
                        map.put("tvUpdateAddress",Complex.TextViewFactory.create(context, ww, "ADDRESS: ", 25));
                        map.put("tvUpdateSalary",Complex.TextViewFactory.create(context, ww, "SALARY: ", 25));
                        break;
                    case "Temp":
                        tv.setText("");
                        tv.setGravity(Gravity.CENTER);
                        tv.setBackgroundColor(Color.parseColor("#FFFFFF"));
                        tv.setTextSize(30);
                        ViewGroup.MarginLayoutParams marginTmp = new ViewGroup.MarginLayoutParams(mw);
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
                LinearLayout.LayoutParams mw = create("mw");
                LinearLayout.LayoutParams ww = create("ww");
                LinearLayout.LayoutParams mmw = create("mm", 1);
                switch (order) {
                    case "Index":
                        EditText et = new EditText(context);
                        et.setText("");
                        et.setLayoutParams(create("mw"));
                        map.put("etIndex",et);
                        break;
                    case "MemberList":
                        map.put("etMemberListFind",Complex.EditTextFactory.create(context, mw, "Search name", 15));
                        break;
                    case "MemberUpdate":
                        map.put("etUpdateNameContent",Complex.EditTextFactory.create(context, ww, "", 20));
                        map.put("etUpdatePhoneContent",Complex.EditTextFactory.create(context, ww, "", 20));
                        map.put("etUpdateAgeContent",Complex.EditTextFactory.create(context, ww, "", 20));
                        map.put("etUpdateAddressContent",Complex.EditTextFactory.create(context, ww, "", 20));
                        map.put("etUpdateSalaryContent",Complex.EditTextFactory.create(context, ww, "", 20));
                        break;
                    case "MemberAdd":
                        map.put("etAddName",Complex.EditTextFactory.create(context, ww, "Name", 20));
                        map.put("etAddPhone",Complex.EditTextFactory.create(context, ww, "Phone", 20));
                        map.put("etAddAge",Complex.EditTextFactory.create(context, ww, "Age", 20));
                        map.put("etAddAddress",Complex.EditTextFactory.create(context, ww, "Address", 20));
                        map.put("etAddSalary",Complex.EditTextFactory.create(context, ww, "Salary", 20));
                        break;
                    case "MessageWrite":
                        map.put("etMsgWriteReceiver",Complex.EditTextFactory.create(context, mw, "", 20));
                        map.put("etMsgWriteMessage",Complex.EditTextFactory.create(context, mmw, "", 20));
                        break;
                }

            }
        };
    }

    class LinearLayoutCompo {
        IComposite service = new IComposite() {
            @Override
            public void execute() {
                LinearLayout.LayoutParams mm = create("mm");
                LinearLayout.LayoutParams mw = create("mw");
                LinearLayout.LayoutParams mww = create("mw", 1);
                switch (order) {
                    case "Index":
                        map.put("llIndex",Complex.LinearLayoutFactory.create(context, mm, "v", "#725253"));
                        break;
                    case "MemberList":
                        map.put("llMemberListFrame", Complex.LinearLayoutFactory.create(context, mw, "v", "#725253"));
                        map.put("llMemberListHead", Complex.LinearLayoutFactory.create(context, mw, "h", "#725253"));
                        map.put("llMemberListBody", Complex.LinearLayoutFactory.create(context, mw, "v", "#FAE100"));
                        break;
                    case "MemberDetail":
                        map.put("llDetailFrame", Complex.LinearLayoutFactory.create(context, mm, "v", "#FAE100"));
                        map.put("llDetailSub", Complex.LinearLayoutFactory.create(context, mw, "v"));
                        map.put("llDetailBtns1", Complex.LinearLayoutFactory.create(context, mw, "h"));
                        map.put("llDetailBtns2", Complex.LinearLayoutFactory.create(context, mw, "h"));
                        map.put("llDetailBtns3", Complex.LinearLayoutFactory.create(context, mw, "h"));
                        map.put("llDetailBtns4", Complex.LinearLayoutFactory.create(context, mw, "h"));
                        map.put("llDetailBtns5", Complex.LinearLayoutFactory.create(context, mw, "h"));
                        break;
                    case "MemberUpdate":
                        map.put("llUpdateFrame", Complex.LinearLayoutFactory.create(context, mm, "v", "#FAE100"));
                        map.put("llUpdateName", Complex.LinearLayoutFactory.create(context, mw));
                        map.put("llUpdatePhone", Complex.LinearLayoutFactory.create(context, mw));
                        map.put("llUpdateAge", Complex.LinearLayoutFactory.create(context, mw));
                        map.put("llUpdateAddress", Complex.LinearLayoutFactory.create(context, mw));
                        map.put("llUpdateSalary", Complex.LinearLayoutFactory.create(context, mw));
                        map.put("llUpdateButton", Complex.LinearLayoutFactory.create(context, mw));
                        break;
                    case "MemberAdd":
                        map.put("llAddFrame", Complex.LinearLayoutFactory.create(context, mm, "v", "#FAE100"));
                        map.put("llAddButton", Complex.LinearLayoutFactory.create(context, mw));
                        map.put("llAddList", Complex.LinearLayoutFactory.create(context, mw, "v"));
                        break;
                    case "MessageWrite":
                        map.put("llMsgWriteFrame", Complex.LinearLayoutFactory.create(context, mm, "v", "#FAE100"));
                        map.put("llMsgWriteReceiver", Complex.LinearLayoutFactory.create(context, mw));
                        map.put("llMsgWriteMessage", Complex.LinearLayoutFactory.create(context, mw, "h"));
                        break;
                }
            }
        };
    }

    class ListViewCompo {
        IComposite service = new IComposite() {
            @Override
            public void execute() {
                LinearLayout.LayoutParams mm = create("mm");
                switch(order) {
                    case "MemberList":
                        map.put("lvMemberList", Complex.ListViewFactory.create(context, mm));
                        break;
                    case "MessageWrite":
                        map.put("lvMsgWriteList", Complex.ListViewFactory.create(context, mm));
                        break;
                }
            }
        };
    }
}
