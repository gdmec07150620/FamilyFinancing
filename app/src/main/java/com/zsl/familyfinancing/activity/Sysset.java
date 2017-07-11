package com.zsl.familyfinancing.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zsl.familyfinancing.ExitApplication;
import com.zsl.familyfinancing.R;
import com.zsl.familyfinancing.dao.PwdDAO;
import com.zsl.familyfinancing.model.Tb_pwd;

/**
 * Created by zsl on 2017/6/14.
 */
public class Sysset extends Activity {
    private EditText newpwd;
    private Button savebtn,cancelbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sysset);
        ExitApplication.getInstance().addActivity(this);

        newpwd = (EditText) findViewById(R.id.txtPwd);
        savebtn = (Button) findViewById(R.id.btnSet);
        cancelbtn = (Button) findViewById(R.id.btnsetCancel);

        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PwdDAO pwdDAO = new PwdDAO(Sysset.this);
                Tb_pwd tb_pwd = new Tb_pwd(newpwd.getText().toString());
                if(pwdDAO.getCount()==0){
                        pwdDAO.add(tb_pwd);//如果没有密码就设置密码
                }else{
                        pwdDAO.update(tb_pwd);//如果有密码就修改当前密码
                }
                Toast.makeText(Sysset.this, "〖密码〗设置成功！", Toast.LENGTH_SHORT).show();
            }
        });

        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newpwd.setText("");
                newpwd.setHint("请输入密码");
            }
        });
    }
}
