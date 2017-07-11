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
import com.zsl.familyfinancing.dao.FlagDAO;
import com.zsl.familyfinancing.model.Tb_flag;

import java.util.List;

/**
 * Created by zsl on 2017/6/20.
 */
public class flagfragment extends Fragment {
    private ListView listView;
    private ArrayAdapter<String> arrayAdapter;
    public static final String FLAG="id";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.flagfragment,container,false);

        listView = (ListView) view.findViewById(R.id.lvflagaccountinfo);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String StrOutfo = String.valueOf(((TextView)view).getText());
                String Strid = StrOutfo.substring(0,StrOutfo.indexOf(":"));
                Intent intent = new Intent(getActivity(),FlagManage.class);
                intent.putExtra(FLAG,Strid);
                startActivity(intent);
            }
        });
        showoutfo();
        return view;
    }
    private void showoutfo(){
        String[]datas=null;
        FlagDAO flagDAO = new FlagDAO(getActivity());
        List<Tb_flag> list =  flagDAO.getScrollData(0, (int) flagDAO.getCount());
        datas = new String[list.size()];
        int i =0;
        for(Tb_flag tb_flag : list){
            datas[i]=tb_flag.getid()+":"+tb_flag.getFlag();
            if(datas[i].length()>15){
                    datas[i]=datas[i].substring(0,15)+".....";
            }
            i++;
        }
        arrayAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,datas);
        listView.setAdapter(arrayAdapter);
    }
}
