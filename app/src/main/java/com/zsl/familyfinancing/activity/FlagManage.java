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
import com.zsl.familyfinancing.dao.FlagDAO;
import com.zsl.familyfinancing.model.Tb_flag;

/**
 * Created by zsl on 2017/6/15.
 */
public class FlagManage extends Activity {
    private EditText txtFlag;
    private Button btnEdit, btnDel;
    private String strid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flagmanage);
        ExitApplication.getInstance().addActivity(this);

        txtFlag = (EditText) findViewById(R.id.txtFlagManage);
        btnEdit = (Button) findViewById(R.id.btnFlagManageEdit);
        btnDel = (Button) findViewById(R.id.btnFlagManageDelete);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        strid = bundle.getString(flagfragment.FLAG);

        final FlagDAO flagDAO = new FlagDAO(FlagManage.this);

        txtFlag.setText(flagDAO.find(Integer.parseInt(strid)).getFlag());

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tb_flag tb_flag = new Tb_flag();
                tb_flag.setid(Integer.parseInt(strid));
                tb_flag.setFlag(txtFlag.getText().toString());
                flagDAO.update(tb_flag);
                Toast.makeText(FlagManage.this, "〖便签数据〗修改成功！",Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(FlagManage.this,Showinfo.class);
                startActivity(intent1);
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flagDAO.detele(Integer.parseInt(strid));
                Toast.makeText(FlagManage.this, "〖便签数据〗删除成功！", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(FlagManage.this,Showinfo.class);
                startActivity(intent1);
            }
        });
    }
}
