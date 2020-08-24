package com.xuanthuan.lucoso.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xuanthuan.lucoso.Object.Food;
import com.xuanthuan.lucoso.R;

import java.util.List;

public class AdapterFood extends BaseAdapter {

    List<Food> list;
    Context context;
    int layout;

    public AdapterFood(List<Food> list, Context context, int layout) {
        this.list = list;
        this.context = context;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    class ViewHolderFood{
        TextView txtnamefood, txtpricefood;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolderFood holder = new ViewHolderFood();
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_show_food, null);

            holder.txtnamefood = view.findViewById(R.id.namefood);
            holder.txtpricefood = view.findViewById(R.id.pricefood);

            view.setTag(holder);
        } else {
            holder = (ViewHolderFood) view.getTag();
        }

        Food food = list.get(i);

        holder.txtnamefood.setText(food.getName());
        holder.txtpricefood.setText(food.getPrice() + " VND");

        return view;
    }
}
