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
import com.zsl.familyfinancing.dao.OutaccountDAO;
import com.zsl.familyfinancing.model.Tb_outaccount;

import java.util.List;

/**
 * Created by zsl on 2017/6/19.
 */
public class Outfragment extends Fragment {
    private ListView listView;
    private ArrayAdapter<String> arrayAdapter;
    private static final String FLAG="id";
    private String strType="";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.outfragment,container,false);

        listView  = (ListView) view.findViewById(R.id.lvoutaccountinfo);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String StrOutfo = String.valueOf(((TextView)view).getText());
                String Strid = StrOutfo.substring(0,StrOutfo.indexOf(":"));
                Intent intent = new Intent(getActivity(),InfoManage.class);
                intent.putExtra(FLAG,new String[]{Strid,strType});
                startActivity(intent);
            }
        });
        showoutfo();
        return view;
    }
    private void showoutfo(){
        String[]datas=null;
        strType = "btnoutinfo";
        OutaccountDAO outaccountDAO = new OutaccountDAO(getActivity());
        List<Tb_outaccount> list =  outaccountDAO.getScrollData(0, (int) outaccountDAO.getCount());
        datas = new String[list.size()];
        int i =0;
        for(Tb_outaccount tb_outaccount : list){
            datas[i]=tb_outaccount.getid()+":"+tb_outaccount.getType()+"  "+String.valueOf(tb_outaccount.getMoney())+"元   "+tb_outaccount.getTime();
            i++;
        }
        arrayAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,datas);
        listView.setAdapter(arrayAdapter);
    }
}
