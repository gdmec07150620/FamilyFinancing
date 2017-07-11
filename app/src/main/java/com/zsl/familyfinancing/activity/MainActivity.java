package com.zsl.familyfinancing.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.zsl.familyfinancing.ExitApplication;
import com.zsl.familyfinancing.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zsl on 2017/6/14.
 */
public class MainActivity extends Activity implements AdapterView.OnItemClickListener {
    private GridView gridView;
    private SimpleAdapter simpleAdapter;
    private List<Map<String,Object>>data;
    private String[]text={"新增支出","新增收入","我的支出","我的收入","数据管理","系统设置","收支便签","退出"};
    private int[] images = {R.mipmap.addoutaccount,
            R.mipmap.addinaccount, R.mipmap.outaccountinfo,
            R.mipmap.inaccountinfo, R.mipmap.showinfo, R.mipmap.sysset,
            R.mipmap.accountflag, R.mipmap.exit };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ExitApplication.getInstance().addActivity(this);

        gridView = (GridView) findViewById(R.id.gvInfo);

        data = new ArrayList<Map<String,Object>>();

        getdata();
        simpleAdapter = new SimpleAdapter(this,data,R.layout.gvitem,new String[]{"image","text"},new int[]{R.id.ItemImage,R.id.ItemTitle});

        gridView.setAdapter(simpleAdapter);

        gridView.setOnItemClickListener(this);

    }
    // 用来计算返回键的点击间隔时间
    private long exitTime = 0;
    //按两次返回键退出程序
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 3000) {
                //弹出提示，可以有多种方式
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                ExitApplication.getInstance().exit(MainActivity.this);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public List<Map<String,Object>>getdata(){
            for(int i=0;i<images.length;i++){
                Map<String,Object>map =new HashMap<String,Object>();
                map.put("image",images[i]);
                map.put("text",text[i]);
                data.add(map);
            }
        return data;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = null;
        switch (images[position]){
            case R.mipmap.addoutaccount:
                intent = new Intent(MainActivity.this, AddOutaccount.class);// 使用AddOutaccount窗口初始化Intent
                startActivity(intent);// 打开AddOutaccount
                break;
            case R.mipmap.addinaccount:
                intent = new Intent(MainActivity.this, AddInaccount.class);// 使用AddInaccount窗口初始化Intent
                startActivity(intent);// 打开AddInaccount
                break;
            case R.mipmap.outaccountinfo:
                intent = new Intent(MainActivity.this, Outaccountinfo.class);// 使用Outaccountinfo窗口初始化Intent
                startActivity(intent);// 打开Outaccountinfo
                break;
            case R.mipmap.inaccountinfo:
                intent = new Intent(MainActivity.this, Inaccountinfo.class);// 使用Inaccountinfo窗口初始化Intent
                startActivity(intent);// 打开Inaccountinfo
                break;
            case R.mipmap.showinfo:
                intent = new Intent(MainActivity.this, Showinfo.class);// 使用Showinfo窗口初始化Intent
                startActivity(intent);// 打开Showinfo
                break;
            case R.mipmap.sysset:
                intent = new Intent(MainActivity.this, Sysset.class);// 使用Sysset窗口初始化Intent
                startActivity(intent);// 打开Sysset
                break;
            case R.mipmap.accountflag:
                intent = new Intent(MainActivity.this, Accountflag.class);// 使用Accountflag窗口初始化Intent
                startActivity(intent);// 打开Accountflag
                break;
            case R.mipmap.exit:
                AlertDialog.Builder normalDialog = new AlertDialog.Builder(MainActivity.this);
                normalDialog.setTitle("退出");
                normalDialog.setMessage("确定要退出吗");
                normalDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //调用ExitApplication的方法结束存储在里面的全部activity
                        ExitApplication.getInstance().exit(MainActivity.this);
                    }
                });
                normalDialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                normalDialog.show();
        }
    }

}
