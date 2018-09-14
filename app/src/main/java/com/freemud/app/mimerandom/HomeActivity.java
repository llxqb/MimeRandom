package com.freemud.app.mimerandom;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mHomeRandomValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initView();
    }

    private void initView() {
        Button mHomeRandom = findViewById(R.id.home_random);
        mHomeRandomValue = findViewById(R.id.home_random_value);
        Button mHomeGoMain = findViewById(R.id.home_go_main);
        mHomeRandom.setOnClickListener(this);
        mHomeGoMain.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home_random:
                getMyRandom();
                break;
            case R.id.home_go_main:
                MainActivity.start(this);
                break;
        }
    }


    private void getMyRandom() {
        Random random = new Random();
        int count = random.nextInt(4);
        Toast.makeText(this,"count:"+count,Toast.LENGTH_SHORT).show();
        if (count == 0) {
            mHomeRandomValue.setText("中奖：大");
        } else if (count == 1) {
            mHomeRandomValue.setText("中奖：小");
        } else if (count == 2) {
            mHomeRandomValue.setText("中奖：单");
        } else if (count == 3) {
            mHomeRandomValue.setText("中奖：双");
        }
    }
}
