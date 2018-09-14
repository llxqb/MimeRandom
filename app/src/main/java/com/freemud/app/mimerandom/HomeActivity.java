package com.freemud.app.mimerandom;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mHomeRandomValue, mHomeRandomValue2,mHomeRandomBossValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
    }

    private void initView() {
        Button mHomeRandom = findViewById(R.id.home_random);
        mHomeRandomValue = findViewById(R.id.home_random_value);
        Button mHomeRandom2 = findViewById(R.id.home_random2);
        mHomeRandomValue2 = findViewById(R.id.home_random_value2);
        Button mHomeRandomBoss = findViewById(R.id.home_random_boss);
        mHomeRandomBossValue = findViewById(R.id.home_random_boss_value);
        Button mHomeGoMain = findViewById(R.id.home_go_main);
        mHomeRandom.setOnClickListener(this);
        mHomeRandom2.setOnClickListener(this);
        mHomeRandomBoss.setOnClickListener(this);
        mHomeGoMain.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home_random:
                getMyRandom(1);
                break;
            case R.id.home_random2:
                getMyRandom(2);
                break;
            case R.id.home_random_boss:
                getMyRandom(3);
                break;
            case R.id.home_go_main:
                MainActivity.start(this);
                break;
        }
    }


    private void getMyRandom(int type) {
        Random random = new Random();
        int count = random.nextInt(4);
        Toast.makeText(this, "count:" + count, Toast.LENGTH_SHORT).show();
        if (count == 0) {
            if (type == 1) {
                mHomeRandomValue.setText("大");
            } else if (type == 2) {
                mHomeRandomValue2.setText("大");
            }else if(type ==3){
                mHomeRandomBossValue.setText("绝杀：大");
            }
        } else if (count == 1) {
            if (type == 1) {
                mHomeRandomValue.setText("小");
            } else if (type == 2) {
                mHomeRandomValue2.setText("小");
            }else if(type ==3){
                mHomeRandomBossValue.setText("绝杀：小");
            }
        } else if (count == 2) {
            if (type == 1) {
                mHomeRandomValue.setText("单");
            } else if (type == 2) {
                mHomeRandomValue2.setText("单");
            }else if(type ==3){
                mHomeRandomBossValue.setText("绝杀：单");
            }
        } else if (count == 3) {
            if (type == 1) {
                mHomeRandomValue.setText("双");
            } else if (type == 2) {
                mHomeRandomValue2.setText("双");
            }else if(type ==3){
                mHomeRandomBossValue.setText("绝杀：双");
            }
        }
    }
}
