package com.zsl.familyfinancing.activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zsl.familyfinancing.ExitApplication;
import com.zsl.familyfinancing.R;

/**
 * Created by zsl on 2017/6/14.
 */
public class Outaccountinfo extends Activity{
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.outaccountinfo);
        ExitApplication.getInstance().addActivity(this);

        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame2,new Outfragment());
        fragmentTransaction.commit();

        button = (Button) findViewById(R.id.outcomeback);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Outaccountinfo.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }



}
