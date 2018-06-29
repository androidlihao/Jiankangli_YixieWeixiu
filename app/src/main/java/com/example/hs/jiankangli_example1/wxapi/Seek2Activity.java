package com.example.hs.jiankangli_example1.wxapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.hs.jiankangli_example1.R;

import MyView.Search_View;
import utils.Statubars;

public class Seek2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Statubars().setStatubars(this,getWindow(),"zhaose",getResources().getColor(R.color.statue));
        setContentView(R.layout.seek2_activity_layout);
        Search_View ss_id= (Search_View) findViewById(R.id.ss_id);
        View sets_back_id=ss_id.findViewById(R.id.sets_back_id);
        sets_back_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
