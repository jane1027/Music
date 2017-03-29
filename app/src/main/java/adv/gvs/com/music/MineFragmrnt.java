package adv.gvs.com.music;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * Created by gvs on 2017/3/23.
 */

public class MineFragmrnt extends android.support.v4.app.Fragment{


    private ListView lv;
    private ArrayList<String> datas;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
       datas =   new ArrayList<>();
        for (int i = 0;i<20;i++){
            datas.add("音乐  告白气球"+i);
        }
        lv = ((ListView) view.findViewById
                (R.id.listview));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        lv.setAdapter(new ArrayAdapter<String>(getActivity(),android.R.layout.simple_expandable_list_item_1,
                android.R.id.text1,datas));


    }
}
