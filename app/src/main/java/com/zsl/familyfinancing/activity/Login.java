package com.zsl.familyfinancing.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zsl.familyfinancing.ExitApplication;
import com.zsl.familyfinancing.R;
import com.zsl.familyfinancing.dao.PwdDAO;

public class Login extends Activity {
	private EditText txtlogin;
	private  Button btnlogin, btnclose;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		ExitApplication.getInstance().addActivity(this);

		txtlogin = (EditText)findViewById(R.id.txtLogin);
		btnlogin = (Button) findViewById(R.id.btnLogin);
		btnclose = (Button) findViewById(R.id.btnClose);

		btnlogin.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Login.this,MainActivity.class);
				PwdDAO pwdDAO = new PwdDAO(Login.this);
				// 第一次登陆，因为没数据不需要密码登录
				if ((pwdDAO.getCount() == 0 || pwdDAO.find().getPassword().isEmpty()) && txtlogin.getText().toString().isEmpty()) {
					startActivity(intent);// 启动主Activity
				} else {
					// 判断输入的密码是否与数据库中的密码一致
					if (pwdDAO.find().getPassword().equals(txtlogin.getText().toString())) {
						startActivity(intent);// 启动主Activity
					} else {
						// 弹出信息提示
						Toast.makeText(Login.this, "请输入正确的密码！", Toast.LENGTH_SHORT).show();
					}
				}
				txtlogin.setText("");// 清空密码文本框
			}
		});

		btnclose.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}
}
