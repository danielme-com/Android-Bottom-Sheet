package com.danielme.android.bottomsheetmenu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
    }

    @OnClick(R.id.buttonList)
    public void showBottomSheetList(View view) {
        BottomSheetMenuFragment frg = BottomSheetMenuFragment.createInstanceList();
        frg.show(getSupportFragmentManager(), BottomSheetMenuFragment.class.getSimpleName());
    }

    @OnClick(R.id.buttonGrid)
    public void showBottomSheetGrid(View view) {
        BottomSheetMenuFragment frg = BottomSheetMenuFragment.createInstanceGrid();
        frg.show(getSupportFragmentManager(), BottomSheetMenuFragment.class.getSimpleName());
    }

}
