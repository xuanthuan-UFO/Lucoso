package com.xuanthuan.lucoso.Acitvity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.xuanthuan.lucoso.Adapter.AdapterViewPagerFood;
import com.xuanthuan.lucoso.R;

public class ActivityOrder extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        init();
        setToolbar();
        getData();
        setTablayout();

    }

    private void setTablayout() {

        FragmentMainFood fragmentMainFood = new FragmentMainFood();
        FragmentDrink fragmentDrink = new FragmentDrink();
        FragmentFood fragmentFood = new FragmentFood();
        Fragmentmore fragmentmore = new Fragmentmore();

        AdapterViewPagerFood adapterViewPagerFood = new AdapterViewPagerFood(getSupportFragmentManager(), 0);
        adapterViewPagerFood.addFragment(fragmentMainFood, "Món chính");
        adapterViewPagerFood.addFragment(fragmentFood, "Món Nóng");
        adapterViewPagerFood.addFragment(fragmentDrink, "Đồ uống");
        adapterViewPagerFood.addFragment(fragmentmore, "Thêm");

        viewPager.setAdapter(adapterViewPagerFood);

        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TextView texttab = (TextView) LayoutInflater.from(getApplicationContext()).inflate(R.layout.customtablayout, null);
            texttab.setTypeface(Typeface.DEFAULT);
            tabLayout.getTabAt(i).setCustomView(texttab);
        }
        viewPager.setOffscreenPageLimit(4);
    }

    private void init() {
        tabLayout = findViewById(R.id.tablayoutfood);
        viewPager = findViewById(R.id.viewPagerfood);

        tabLayout.setupWithViewPager(viewPager);

    }

    private void setToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar_order);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void getData() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("data");
        int vitri = bundle.getInt("vitri");
        String nameTable = bundle.getString("nametable");
        Toast.makeText(this, "" + vitri + " " + nameTable, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}