package com.zsl.familyfinancing.activity;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.zsl.familyfinancing.R;
import com.zsl.familyfinancing.dao.InaccountDAO;
import com.zsl.familyfinancing.model.Tb_inaccount;

import java.util.List;

/**
 * Created by zsl on 2017/6/19.
 */
public class Infragment extends Fragment {
    private ListView lvinfo;// 创建ListView对象
    public static final String FLAG = "id";// 定义一个常量，用来作为请求码
    private String strType = "";// 创建字符串，记录管理类型
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.infragment,container,false);
            lvinfo = (ListView) view.findViewById(R.id.lvinaccountinfo);// 获取布局文件中的ListView组件
            ShowInfo();// 调用自定义方法显示收入信息
         lvinfo.setOnItemClickListener(new AdapterView.OnItemClickListener()// 为ListView添加项单击事件
        {
            // 覆写onItemClick方法
            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                String strInfo = String.valueOf(((TextView) view).getText());// 记录收入信息
                String strid = strInfo.substring(0, strInfo.indexOf(':'));// 从收入信息中截取收入编号
                Intent intent = new Intent(getActivity(), InfoManage.class);// 创建Intent对象
                intent.putExtra(FLAG, new String[] { strid, strType });// 设置传递数据
                startActivity(intent);// 执行Intent操作
            }
        });
            return view;
    }
    private void ShowInfo() {// 用来根据传入的管理类型，显示相应的信息
        String[] strInfos = null;// 定义字符串数组，用来存储收入信息
        ArrayAdapter<String> arrayAdapter = null;// 创建ArrayAdapter对象
        strType = "btnininfo";// 为strType变量赋值
        InaccountDAO inaccountinfo = new InaccountDAO(getActivity());// 创建InaccountDAO对象
        // 获取所有收入信息，并存储到List泛型集合中
        List<Tb_inaccount> listinfos = inaccountinfo.getScrollData(0, (int) inaccountinfo.getCount());
        strInfos = new String[listinfos.size()];// 设置字符串数组的长度
        int m = 0;// 定义一个开始标识
        for (Tb_inaccount tb_inaccount : listinfos) {// 遍历List泛型集合
            // 将收入相关信息组合成一个字符串，存储到字符串数组的相应位置
            strInfos[m] =tb_inaccount.getid()+":"+tb_inaccount.getType()+"  "+tb_inaccount.getMoney()+"元   "+tb_inaccount.getTime();
            m++;// 标识加1
        }
        // 使用字符串数组初始化ArrayAdapter对象
        arrayAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, strInfos);
        lvinfo.setAdapter(arrayAdapter);// 为ListView列表设置数据源
    }
}
