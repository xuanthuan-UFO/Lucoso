package com.xuanthuan.lucoso.Acitvity;

import android.app.Dialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.common.api.internal.LifecycleFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textfield.TextInputEditText;
import com.xuanthuan.lucoso.Adapter.AdapterViewPagerFood;
import com.xuanthuan.lucoso.R;

import java.util.ArrayList;

public class FragmentMenuFood extends Fragment {
    TabLayout tabLayout;
    ViewPager viewPager;
    Button btnadd;

    String nameTable;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_menufood, container, false);

        tabLayout = view.findViewById(R.id.tablayout_menufood);
        viewPager = view.findViewById(R.id.viewpager_menufood);
        tabLayout.setupWithViewPager(viewPager);
        btnadd = view.findViewById(R.id.addfood);
        setTablayout();

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDialogAddFood();
            }
        });


        return view;
    }

    //dialog add food
    private void setDialogAddFood() {
        //khởi tạo
        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_addfood);


        //ánh xạ
        final TextInputEditText edtName = dialog.findViewById(R.id.inputNameFood);
        final TextInputEditText edtPrice = dialog.findViewById(R.id.inputPriceFood);
        final Spinner spinner = dialog.findViewById(R.id.menu_style);
        Button btnOkeAdd = dialog.findViewById(R.id.okeAddFood);

        //add data Spinner
        final ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Main Food");
        arrayList.add("Food");
        arrayList.add("Drink");
        arrayList.add("More");

        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                nameTable = arrayList.get(i);
                //Toast.makeText(getActivity(), vitri + " " + nameTable.replaceAll("\\s+",""), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //bắt sự kiện nút click

        btnOkeAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String namefood = edtName.getText().toString().trim();
                String pricefood = edtPrice.getText().toString().trim();
                if ((!namefood.isEmpty()) && (!pricefood.isEmpty())) {
                    ActivityHome.dataBase.queryData("CREATE TABLE IF NOT EXISTS '" + nameTable.replaceAll("\\s+", "") + "'(Id INTEGER PRIMARY KEY AUTOINCREMENT, Name VARCHAR, Price INTEGER)");
                    ActivityHome.dataBase.queryData("INSERT INTO '" + nameTable.replaceAll("\\s+", "") + "' VALUES (null, '" + namefood + "', '" + pricefood + "')");
                    Toast.makeText(getContext(), "Đã thêm", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Nhập tên và giá món ăn!", Toast.LENGTH_SHORT).show();
                }
            }
        });


        dialog.show();
    }


    // tab layout
    private void setTablayout() {

        FragmentMainFood fragmentMainFood = new FragmentMainFood();
        FragmentDrink fragmentDrink = new FragmentDrink();
        FragmentFood fragmentFood = new FragmentFood();
        Fragmentmore fragmentmore = new Fragmentmore();

        AdapterViewPagerFood adapterViewPagerFood = new AdapterViewPagerFood(getChildFragmentManager(), 0);
        adapterViewPagerFood.addFragment(fragmentMainFood, "Món chính");
        adapterViewPagerFood.addFragment(fragmentDrink, "Đồ uống");
        adapterViewPagerFood.addFragment(fragmentFood, "Món Nóng");
        adapterViewPagerFood.addFragment(fragmentmore, "Thêm");

        viewPager.setAdapter(adapterViewPagerFood);

        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TextView texttab = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.customtablayout, null);
            texttab.setTypeface(Typeface.DEFAULT);
            tabLayout.getTabAt(i).setCustomView(texttab);
        }
        viewPager.setOffscreenPageLimit(4);
    }
}

