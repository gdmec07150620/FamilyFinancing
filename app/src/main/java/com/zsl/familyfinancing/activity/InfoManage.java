package com.zsl.familyfinancing.activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.zsl.familyfinancing.ExitApplication;
import com.zsl.familyfinancing.R;
import com.zsl.familyfinancing.dao.InaccountDAO;
import com.zsl.familyfinancing.dao.OutaccountDAO;
import com.zsl.familyfinancing.model.Tb_inaccount;
import com.zsl.familyfinancing.model.Tb_outaccount;

import java.util.Calendar;

/**
 * Created by zsl on 2017/6/15.
 */
public class InfoManage extends Activity {
    protected static final int DATE_DIALOG_ID = 0;// 创建日期对话框常量
    private TextView tvtitle, textView;// 创建两个TextView对象
    private EditText txtMoney, txtTime, txtHA, txtMark;// 创建4个EditText对象
    private Spinner spType;// 创建Spinner对象
    private Button btnEdit, btnDel;// 创建两个Button对象
    private String[] strInfos;// 定义字符串数组
    private String strid, strType;// 定义两个字符串变量，分别用来记录信息编号和管理类型

    private int mYear;// 年
    private int mMonth;// 月
    private int mDay;// 日

    private OutaccountDAO outaccountDAO = new OutaccountDAO(InfoManage.this);// 创建OutaccountDAO对象
    private InaccountDAO inaccountDAO = new InaccountDAO(InfoManage.this);// 创建InaccountDAO对象

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.infomanage);
        ExitApplication.getInstance().addActivity(this);

        init();

        final Calendar calendar = Calendar.getInstance();
        mYear = calendar.get(Calendar.YEAR);
        mMonth=calendar.get(Calendar.MONTH);
        mDay = calendar.get(Calendar.DAY_OF_MONTH);
        updatedisplaytime();

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        strInfos = bundle.getStringArray(Infragment.FLAG);
        strid = strInfos[0];//id
        strType=strInfos[1];//类型

        if(strType.equals("btnininfo")){
            tvtitle.setText("收入管理");
            textView.setText("付款方");
           Tb_inaccount tb_inaccount = inaccountDAO.find(Integer.parseInt(strid));
            txtMoney.setText(tb_inaccount.getMoney()+"");
            txtTime.setText(tb_inaccount.getTime());
            spType.setPrompt(tb_inaccount.getType());
            txtHA.setText(tb_inaccount.getHandler());
            txtMark.setText(tb_inaccount.getMark());

        }else if(strType.equals("btnoutinfo")){
            tvtitle.setText("支出管理");// 设置标题为“支出管理”
            textView.setText("地  点：");// 设置“地点/付款方”标签文本为“地 点：”
            // 根据编号查找支出信息，并存储到Tb_outaccount对象中
            Tb_outaccount tb_outaccount = outaccountDAO.find(Integer.parseInt(strid));
            txtMoney.setText(tb_outaccount.getMoney()+"");// 显示金额
            txtTime.setText(tb_outaccount.getTime());// 显示时间
            spType.setPrompt(tb_outaccount.getType());// 显示类别
            txtHA.setText(tb_outaccount.getAddress());// 显示地点
            txtMark.setText(tb_outaccount.getMark());// 显示备注
        }

        txtTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(strType.equals("btnininfo")){

                    Tb_inaccount tb_inaccount = new Tb_inaccount();
                    tb_inaccount.setid(Integer.parseInt(strid));
                    tb_inaccount.setHandler(txtHA.getText().toString());
                    tb_inaccount.setMark(txtMark.getText().toString());
                    tb_inaccount.setMoney(Double.parseDouble(txtMoney.getText().toString()));
                    tb_inaccount.setTime(txtTime.getText().toString());
                    tb_inaccount.setType(spType.getSelectedItem().toString());
                    inaccountDAO.update(tb_inaccount);

                    Toast.makeText(InfoManage.this, "〖数据〗修改成功！", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(InfoManage.this,Inaccountinfo.class);
                    startActivity(intent);
                }else if(strType.equals("btnoutinfo")){

                    Tb_outaccount tb_outaccount = new Tb_outaccount();
                    tb_outaccount.setid(Integer.parseInt(strid));
                    tb_outaccount.setMoney(Double.parseDouble(txtMoney.getText().toString()));
                    tb_outaccount.setTime(txtTime.getText().toString());
                    tb_outaccount.setType(spType.getSelectedItem().toString());
                    tb_outaccount.setAddress(txtHA.getText().toString());
                    tb_outaccount.setMark(txtMark.getText().toString());
                    outaccountDAO.update(tb_outaccount);

                    Toast.makeText(InfoManage.this, "〖数据〗修改成功！", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(InfoManage.this,Outaccountinfo.class);
                    startActivity(intent);
                }
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (strType.equals("btnoutinfo"))
                {
                    outaccountDAO.detele(Integer.parseInt(strid));// 根据编号删除支出信息
                    Toast.makeText(InfoManage.this, "〖收入数据〗删除成功！", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(InfoManage.this,Outaccountinfo.class);
                    startActivity(intent);
                } else if (strType.equals("btnininfo"))
                {
                    inaccountDAO.detele(Integer.parseInt(strid));// 根据编号删除收入信息
                    Toast.makeText(InfoManage.this, "〖支出数据〗删除成功！", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(InfoManage.this,Inaccountinfo.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void updatedisplaytime() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(mYear).append("-").append(mMonth+1).append("-").append(mDay);
            txtTime.setText(stringBuilder);
    }

    private void init() {
        tvtitle = (TextView) findViewById(R.id.inouttitle);// 获取标题标签对象
        textView = (TextView) findViewById(R.id.tvInOut);// 获取地点/付款方标签对象
        txtMoney = (EditText) findViewById(R.id.txtInOutMoney);// 获取金额文本框
        txtTime = (EditText) findViewById(R.id.txtInOutTime);// 获取时间文本框
        spType = (Spinner) findViewById(R.id.spInOutType);// 获取类别下拉列表
        txtHA = (EditText) findViewById(R.id.txtInOut);// 获取地点/付款方文本框
        txtMark = (EditText) findViewById(R.id.txtInOutMark);// 获取备注文本框
        btnEdit = (Button) findViewById(R.id.btnInOutEdit);// 获取修改按钮
        btnDel = (Button) findViewById(R.id.btnInOutDelete);// 获取删除按钮
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id){
            case DATE_DIALOG_ID:
                return new DatePickerDialog(InfoManage.this,mDateSetListener,mYear,mMonth,mDay);
        }
            return null;
    }
    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            mYear = year;
            mMonth= month;
            mDay = dayOfMonth;
            updatedisplaytime();
        }
    };

}
