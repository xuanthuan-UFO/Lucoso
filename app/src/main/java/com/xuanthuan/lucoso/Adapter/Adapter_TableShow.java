package com.xuanthuan.lucoso.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.xuanthuan.lucoso.Object.Table;
import com.xuanthuan.lucoso.R;

import java.util.List;

public class Adapter_TableShow extends BaseAdapter {
    List<Table> list;
    Context context;
    int layout;

    public Adapter_TableShow(List<Table> list, Context context, int layout) {
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

    class ViewHolderTable{
        ImageView imgTable;
        TextView txtNameTable;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolderTable holder = new ViewHolderTable();
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_table, null);

            holder.imgTable = view.findViewById(R.id.imgTable);
            holder.txtNameTable = view.findViewById(R.id.nameTable);
            view.setTag(holder);
        } else {
            holder = (ViewHolderTable) view.getTag();
        }

        Table table = list.get(i);
        holder.imgTable.setImageResource(table.getImgTable());
        holder.txtNameTable.setText(table.getNameTable());
        return view;
    }
}
