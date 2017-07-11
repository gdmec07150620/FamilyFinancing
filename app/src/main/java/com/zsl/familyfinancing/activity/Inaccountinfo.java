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

public class Inaccountinfo extends Activity {
    private Button button;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inaccountinfo);
        ExitApplication.getInstance().addActivity(this);

        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame1,new Infragment());
        fragmentTransaction.commit();

        button = (Button) findViewById(R.id.incomeback);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Inaccountinfo.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

}
