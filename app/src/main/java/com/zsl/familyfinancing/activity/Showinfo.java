package com.zsl.familyfinancing.activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zsl.familyfinancing.ExitApplication;
import com.zsl.familyfinancing.R;

/**
 * Created by zsl on 2017/6/14.
 */
public class Showinfo extends Activity {
    private Button btnoutinfo, btnininfo, btnflaginfo;// 创建3个Button对象
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showinfo);
        ExitApplication.getInstance().addActivity(this);

        btnininfo = (Button) findViewById(R.id.btnininfo);
        btnoutinfo = (Button) findViewById(R.id.btnoutinfo);
        btnflaginfo = (Button) findViewById(R.id.btnflaginfo);
        //默认显示
        showone();
        btnininfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager = getFragmentManager();
                fragmentTransaction =fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame,new Infragment());
                fragmentTransaction.commit();
            }
        });
        btnoutinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager = getFragmentManager();
                fragmentTransaction =fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame,new Outfragment());
                fragmentTransaction.commit();
            }
        });
        btnflaginfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager = getFragmentManager();
                fragmentTransaction =fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame,new flagfragment());
                fragmentTransaction.commit();
            }
        });
    }
    public void showone(){
        fragmentManager = getFragmentManager();
        fragmentTransaction =fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frame,new Outfragment());
        fragmentTransaction.commit();
    }
}
