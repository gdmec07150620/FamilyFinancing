package com.zsl.familyfinancing.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zsl.familyfinancing.ExitApplication;
import com.zsl.familyfinancing.R;
import com.zsl.familyfinancing.dao.FlagDAO;
import com.zsl.familyfinancing.model.Tb_flag;

/**
 * Created by zsl on 2017/6/14.
 */
public class Accountflag extends Activity{
    private EditText editText;
    private Button savebtn,cancelbtn;
    private FlagDAO flagDAO = new FlagDAO(Accountflag.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accountflag);
        ExitApplication.getInstance().addActivity(this);

        editText = (EditText) findViewById(R.id.txtFlag);
        savebtn = (Button) findViewById(R.id.btnflagSave);
        cancelbtn = (Button) findViewById(R.id.btnflagCancel);



        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strFlag = editText.getText().toString();
                if(!strFlag.isEmpty()){
                    Tb_flag tb_flag = new Tb_flag(flagDAO.getMaxId()+1,strFlag);
                    flagDAO.add(tb_flag);
                    Toast.makeText(Accountflag.this, "〖新增便签〗数据添加成功！", Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(Accountflag.this, "请输入便签！", Toast.LENGTH_SHORT).show();
                }
            }
        });
        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("");// 清空便签文本
            }
        });
    }
}
