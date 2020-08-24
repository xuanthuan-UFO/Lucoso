package com.xuanthuan.lucoso.Acitvity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.xuanthuan.lucoso.Adapter.Adapter_TableShow;
import com.xuanthuan.lucoso.Object.Table;
import com.xuanthuan.lucoso.R;

import java.util.ArrayList;

public class FragmentHome extends Fragment{
    GridView gridView;
    ArrayList<Table> arrayList;
    Adapter_TableShow adapter_tableShow;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_home, container, false);
        arrayList = new ArrayList<>();
        gridView = view.findViewById(R.id.gridviewTable);
        arrayList.add(new Table(R.drawable.metting, "Bàn 0"));
        arrayList.add(new Table(R.drawable.metting, "Bàn 1"));
        arrayList.add(new Table(R.drawable.metting, "Bàn 2"));
        arrayList.add(new Table(R.drawable.metting, "Bàn 3"));
        arrayList.add(new Table(R.drawable.metting, "Bàn 4"));
        arrayList.add(new Table(R.drawable.metting, "Bàn 5"));
        arrayList.add(new Table(R.drawable.metting, "Bàn 6"));
        adapter_tableShow = new Adapter_TableShow(arrayList, getActivity(), R.layout.item_table);
        gridView.setAdapter(adapter_tableShow);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getContext(), ActivityOrder.class);
                Bundle bundle = new Bundle();
                bundle.putString("nametable",arrayList.get(i).getNameTable() );
                bundle.putInt("vitri", i);
                intent.putExtra("data",bundle);
                startActivity(intent);
            }
        });

        return view;
    }
}
