package com.zsl.familyfinancing.activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.zsl.familyfinancing.ExitApplication;
import com.zsl.familyfinancing.R;
import com.zsl.familyfinancing.dao.OutaccountDAO;
import com.zsl.familyfinancing.model.Tb_outaccount;

import java.util.Calendar;

/**
 * Created by zsl on 2017/6/14.
 */
public class AddOutaccount extends Activity {
    protected static final int DATE_DIALOG_ID = 0;// 创建日期对话框常量标识
    private EditText money,time,address,mark;
    private Button savebtn,cancelbtn;
    private Spinner spType;

    private int mYear;
    private int mMonth;
    private int mDay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addoutaccount);
        ExitApplication.getInstance().addActivity(this);

        init();

        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                money.setText("");
                money.setHint("0.00");
                time.setText("");
                time.setHint("2011-01-01");
                spType.setSelection(0);
                address.setText("");
                mark.setText("");
            }
        });

        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strmoney = money.getText().toString();
                if(!strmoney.equals("")){
                    OutaccountDAO outaccountDAO = new OutaccountDAO(AddOutaccount.this);
                    //获取输入框的内容封装到model的类中
                    Tb_outaccount tb_outaccount = new Tb_outaccount(outaccountDAO.getMaxId()+1,
                            Double.parseDouble(strmoney),
                            time.getText().toString(),
                            spType.getSelectedItem().toString(),
                            address.getText().toString()
                            ,mark.getText().toString());
                    outaccountDAO.add(tb_outaccount);//向数据库中插入model中类的数据
                    Toast.makeText(AddOutaccount.this,"〖新增支出〗数据添加成功！",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(AddOutaccount.this,"请输入支出金额！",Toast.LENGTH_SHORT).show();
                }
            }
        });
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //显示时间提示框
                showDialog(DATE_DIALOG_ID);
            }
        });

        final Calendar c = Calendar.getInstance();// 获取当前系统日期
        mYear  = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        upDateDisplayTime();
    }
    private void init(){
        money = (EditText) findViewById(R.id.txtMoney);
        time = (EditText)findViewById(R.id.txtTime);
        address = (EditText) findViewById(R.id.txtAddress);
        mark = (EditText) findViewById(R.id.txtMark);
        savebtn = (Button) findViewById(R.id.btnSave);
        cancelbtn = (Button) findViewById(R.id.btnCancel);
        spType = (Spinner) findViewById(R.id.spType);
    }
    //更新显示时间
    public void upDateDisplayTime(){
        time.setText(new StringBuilder().append(mYear).append("-").append(mMonth+1).append("-").append(mDay));
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID://显示框唯一标识
                return new DatePickerDialog(AddOutaccount.this, mDateSetListener, mYear, mMonth, mDay);//返回一个时间对话框对象
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener(){

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                mYear = year;
                mMonth = month;
                mDay = dayOfMonth;
                upDateDisplayTime();
        }
    };
}
