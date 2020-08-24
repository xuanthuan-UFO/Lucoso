package com.xuanthuan.lucoso.Acitvity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.xuanthuan.lucoso.Adapter.AdapterFood;
import com.xuanthuan.lucoso.Object.Food;
import com.xuanthuan.lucoso.R;

import java.util.ArrayList;

public class FragmentFood extends Fragment {
    ListView listView;
    ArrayList<Food> arraylist;
    SwipeRefreshLayout refreshLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_food, container, false);
        refreshLayout = view.findViewById(R.id.refreshfood);
        arraylist = new ArrayList<>();
        addDataList();
        listView = view.findViewById(R.id.listFood);
        final AdapterFood adapterFood = new AdapterFood(arraylist, getActivity(), R.layout.item_show_food);
        listView.setAdapter(adapterFood);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshLayout.setRefreshing(false);
                addDataList();
                adapterFood.notifyDataSetChanged();
            }
        });
        return view;
    }

    private void addDataList() {
        arraylist.clear();
        Cursor data = ActivityHome.dataBase.getData("SELECT * FROM Food");
        while (data.moveToNext()) {
            int id = data.getInt(0);
            String name = data.getString(1);
            int price = data.getInt(2);
            arraylist.add(new Food(id, name, price));
        }
    }

}
