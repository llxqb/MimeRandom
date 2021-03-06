package com.freemud.app.mimerandom;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edt = null;
    private String TAG = "MainActivity";
    private TextView text1, text2, text3, text4, text5, text6, text7;
    private List<Integer> mList;
    private List<Integer> mMunTenList;//显示前十项数据
    private int big = 0, small = 0;
    private int single = 0, doubles = 0;
    private RadioButton mBig, mSmall, mSingle, mDouble;
    private int radioBtnFlag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        edt = findViewById(R.id.edt);
        Button btn1 = findViewById(R.id.btn1);
        mBig = findViewById(R.id.big);
        mSmall = findViewById(R.id.small);
        mSingle = findViewById(R.id.single);
        mDouble = findViewById(R.id.mdouble);

        text1 = findViewById(R.id.text1);
        text2 = findViewById(R.id.text2);
        text3 = findViewById(R.id.text3);
        text4 = findViewById(R.id.text4);
        text5 = findViewById(R.id.text5);
        text6 = findViewById(R.id.text6);
        text7 = findViewById(R.id.text7);
        btn1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1://产生随机数
                initData();
                if (!TextUtils.isEmpty(edt.getText().toString())) {
                    int num = Integer.parseInt(edt.getText().toString());
                    getMyRandom(num);
                } else {
                    Toast.makeText(this, "先输入随机期数", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    /**
     * 初始化数据
     */
    private void initData() {
        mList = new ArrayList<>();
        mMunTenList = new ArrayList<>();
        big = 0;
        small = 0;
        single = 0;
        doubles = 0;
        if (mBig.isChecked()) {
            radioBtnFlag = 0;
        }
        if (mSmall.isChecked()) {
            radioBtnFlag = 1;
        }
        if (mSingle.isChecked()) {
            radioBtnFlag = 2;
        }
        if (mDouble.isChecked()) {
            radioBtnFlag = 3;
        }
    }

    /**
     * 获得随机数
     *
     * @param num 组数
     */
    private void getMyRandom(int num) {
        for (int i = 0; i < num; i++) {
            Random random = new Random();
            int count = random.nextInt(16) + 3;
            if (count <= 10) {
                small++;
            } else {
                big++;
            }
            if (count % 2 == 0) {
                doubles++;
            } else {
                single++;
            }
            mList.add(count);
        }

        //打印前10项数据
        if (mList.size() > 0) {
            if (mList.size() <= 20) {
                for (int j = 0; j < mList.size(); j++) {
                    mMunTenList.add(mList.get(j));
                    Log.d(TAG, "num=" + mList.get(j));
                }
            } else {
                for (int j = 0; j < 20; j++) {
                    mMunTenList.add(mList.get(j));
                    Log.d(TAG, "num=" + mList.get(j));
                }
            }
            text7.setText(mMunTenList.toString());
        }
        firstText0();
        firstText();
        firstText2();
        radioBtnFlag = 0;
        firstText3();
        radioBtnFlag = 0;
        firstText4();
        radioBtnFlag = 0;
        firstText5();
        radioBtnFlag = 0;
        secondText();
        thirdText();
        thirdText2();
        thirdText3();
        radioBtnFlag = 0;
        forthText1();
        forthText2();
        String text6Content = "大:" + big + "   小:" + small + "   单:" + single + "   双:" + doubles;
//        String text7Content = "亏损:" + payMoney + "元  回报:" + getMoney + "元  赚取:" + (getMoney - payMoney) + "元";
        text6.setText(text6Content);
//        text7.setText(text7Content);
    }

    /**
     * 传统倍投
     * 1, 2 ,4, 8或 1，3，8，15 三期或四期一个周期，不中就回头
     */
    String text7Content0,text7Content;

    private void firstText0() {
        int payMoney = 0, getMoney = 0;
        int mflag = 0;
        for (int i = 0; i < mList.size(); i++) {
            int key = mList.get(i);
            if (radioBtnFlag == 0) {//大
                if (key >= 11) {//中奖
                    getMoney = getMoney + (int) Math.pow(2, mflag);
                    mflag = 0;
                } else {//没中奖
                    if (mflag > 3) mflag = 0;
                    payMoney = payMoney + (int) Math.pow(2, mflag);
                    mflag++;
                }
            } else if (radioBtnFlag == 1) {//小
                if (key < 11) {//中奖
                    getMoney = getMoney + (int) Math.pow(2, mflag);
                    mflag = 0;
                } else {//没中奖
                    if (mflag > 3) mflag = 0;
                    payMoney = payMoney + (int) Math.pow(2, mflag);
                    mflag++;
                }
            }

        }
        Log.d(TAG, "getMoney1=" + getMoney + "  payMoney1=" + payMoney);
        text7Content = "亏损:" + payMoney + "元   回报:" + getMoney + "元   赚取:" + (getMoney - payMoney) + "元";
//        text1.setText(text7Content);
    }

    private void firstText() {
        int payMoney = 0, getMoney = 0;
        int mflag = 0;
        for (int i = 0; i < mList.size(); i++) {
            int key = mList.get(i);
            if (radioBtnFlag == 0) {//大
                if (key >= 11) {//中奖
                    getMoney = getMoney + (int) Math.pow(2, mflag);
                    mflag = 0;
                } else {//没中奖
                    if (mflag > 2) mflag = 0;
                    payMoney = payMoney + (int) Math.pow(2, mflag);
                    mflag++;
                }
            } else if (radioBtnFlag == 1) {//小
                if (key < 11) {//中奖
                    getMoney = getMoney + (int) Math.pow(2, mflag);
                    mflag = 0;
                } else {//没中奖
                    if (mflag > 2) mflag = 0;
                    payMoney = payMoney + (int) Math.pow(2, mflag);
                    mflag++;
                }
            }

        }
        Log.d(TAG, "getMoney1=" + getMoney + "  payMoney1=" + payMoney);
        text7Content0 = "亏损:" + payMoney + "元   回报:" + getMoney + "元   赚取:" + (getMoney - payMoney) + "元";
//        text1.setText(text7Content);
    }

    /**
     * 传统倍投
     * 1, 2 ,4, 8或 1，3，8，15 三期或四期一个周期，不中就回头
     * 大小交换
     */
    String firstContent;

    private void firstText2() {
        int payMoney = 0, getMoney = 0;
        int mflag = 0;
        for (int i = 0; i < mList.size(); i++) {
            int key = mList.get(i);
            if (radioBtnFlag == 0) {//大
                if (key >= 11) {//中奖
                    getMoney = getMoney + (int) Math.pow(2, mflag);
                    radioBtnFlag = 1;
                    mflag = 0;
                } else {//没中奖
                    if (mflag > 3) mflag = 0;
                    payMoney = payMoney + (int) Math.pow(2, mflag);
                    mflag++;
                }
            } else if (radioBtnFlag == 1) {//小
                if (key < 11) {//中奖
                    getMoney = getMoney + (int) Math.pow(2, mflag);
                    radioBtnFlag = 0;
                    mflag = 0;
                } else {//没中奖
                    if (mflag > 3) mflag = 0;
                    payMoney = payMoney + (int) Math.pow(2, mflag);
                    mflag++;
                }
            }

        }
        Log.d(TAG, "getMoney1=" + getMoney + "  payMoney1=" + payMoney);
        firstContent = "亏损:" + payMoney + "元   回报:" + getMoney + "元   赚取:" + (getMoney - payMoney) + "元";
//        text1.setText(text7Content+"\n"+firstContent);
    }

    /**
     * 传统倍投
     * 1, 2 ,4, 8或 1，3，8，15 三期或四期一个周期，不中就回头
     * 出大买大，出小买小
     */
    String firstContent2;

    private void firstText3() {
        int payMoney = 0, getMoney = 0;
        int mflag = 0;
        for (int i = 0; i < mList.size(); i++) {
            int key = mList.get(i);
            if (radioBtnFlag == 0) {//大
                if (key >= 11) {//中奖
                    if(mflag==0){
                        getMoney = getMoney + (int) Math.pow(2, mflag);
                    }else {
                        getMoney = getMoney + (int) Math.pow(2, mflag)- (int) Math.pow(2, mflag-1);
                    }
                    radioBtnFlag = 0;
                    mflag = 0;
                } else {//没中奖
                    if (mflag > 3) mflag = 0;
                    payMoney = payMoney + (int) Math.pow(2, mflag);
                    radioBtnFlag = 1;
                    mflag++;
                }
            } else if (radioBtnFlag == 1) {//小
                if (key < 11) {//中奖
                    if(mflag==0){
                        getMoney = getMoney + (int) Math.pow(2, mflag);
                    }else {
                        getMoney = getMoney + (int) Math.pow(2, mflag)- (int) Math.pow(2, mflag-1);
                    }
                    radioBtnFlag = 1;
                    mflag = 0;
                } else {//没中奖
                    if (mflag > 3) mflag = 0;
                    payMoney = payMoney + (int) Math.pow(2, mflag);
                    radioBtnFlag = 0;
                    mflag++;
                }
            }

        }
        Log.d(TAG, "getMoney1=" + getMoney + "  payMoney1=" + payMoney);
        firstContent2 = "亏损:" + payMoney + "元   回报:" + getMoney + "元   赚取:" + (getMoney - payMoney) + "元";
//        text1.setText(text7Content+"\n"+firstContent+"\n"+text8Content);
    }

    /**
     * 传统倍投
     * 1, 2 ,4或 1，3，8三期或四期一个周期，不中就回头
     * 出大买大，出小买小  3期一周期
     */
    String firstContent3;
    private void firstText4() {
        int payMoney = 0, getMoney = 0;
        int mflag = 0;
        for (int i = 0; i < mList.size(); i++) {
            int key = mList.get(i);
            if (radioBtnFlag == 0) {//大
                if (key >= 11) {//中奖
                    if(mflag==0){
                        getMoney = getMoney + (int) Math.pow(3, mflag);
                    }else {
                        getMoney = getMoney + (int) Math.pow(3, mflag)- (int) Math.pow(3, mflag-1);
                    }
                    radioBtnFlag = 0;
                    mflag = 0;
                } else {//没中奖
                    if (mflag > 2) mflag = 0;
                    payMoney = payMoney + (int) Math.pow(3, mflag);
                    radioBtnFlag = 1;
                    mflag++;
                }
            } else if (radioBtnFlag == 1) {//小
                if (key < 11) {//中奖
                    if(mflag==0){
                        getMoney = getMoney + (int) Math.pow(3, mflag);
                    }else {
                        getMoney = getMoney + (int) Math.pow(3, mflag)- (int) Math.pow(3, mflag-1);
                    }
                    radioBtnFlag = 1;
                    mflag = 0;
                } else {//没中奖
                    if (mflag > 2) mflag = 0;
                    payMoney = payMoney + (int) Math.pow(3, mflag);
                    radioBtnFlag = 0;
                    mflag++;
                }
            }

        }
        Log.d(TAG, "getMoney1=" + getMoney + "  payMoney1=" + payMoney);
        firstContent3 = "亏损:" + payMoney + "元   回报:" + getMoney + "元   赚取:" + (getMoney - payMoney) + "元";
//        text1.setText( firstContent + "\n" + firstContent2 + "\n" + text8Content);
    }

    /**
     * 传统倍投
     * 1, 2 ,4, 8或 1，3，8，15 三期或四期一个周期，不中就回头
     * 出大买大，出小买小，连出2期相同的就换
     */
    private void firstText5() {
        int payMoney = 0, getMoney = 0;
        int mflag = 0;
        int countflag = 0;
        for (int i = 0; i < mList.size(); i++) {
            int key = mList.get(i);
            if (radioBtnFlag == 0) {//大
                if (key >= 11) {//中奖
                    if(mflag==0){
                        getMoney = getMoney + (int) Math.pow(2, mflag);
                    }else {
                        getMoney = getMoney + (int) Math.pow(2, mflag)- (int) Math.pow(2, mflag-1);
                    }
                    if(countflag >=2){
                        radioBtnFlag = 1;
                    }else {
                        radioBtnFlag = 0;
                    }
                    countflag++;
                    mflag = 0;
                } else {//没中奖
                    if (mflag > 3) mflag = 0;
                    payMoney = payMoney + (int) Math.pow(2, mflag);
                    radioBtnFlag = 1;
                    countflag = 0;
                    mflag++;
                }
            } else if (radioBtnFlag == 1) {//小
                if (key < 11) {//中奖
                    if(mflag==0){
                        getMoney = getMoney + (int) Math.pow(2, mflag);
                    }else {
                        getMoney = getMoney + (int) Math.pow(2, mflag)- (int) Math.pow(2, mflag-1);
                    }
                    if(countflag >=2){
                        radioBtnFlag = 0;
                    }else {
                        radioBtnFlag = 1;
                    }
//                    radioBtnFlag = 1;
                    mflag = 0;
                    countflag++;
                } else {//没中奖
                    if (mflag > 3) mflag = 0;
                    payMoney = payMoney + (int) Math.pow(2, mflag);
                    radioBtnFlag = 0;
                    mflag++;
                    countflag=0;
                }
            }

        }
        Log.d(TAG, "getMoney1=" + getMoney + "  payMoney1=" + payMoney);
       String text8Content = "亏损:" + payMoney + "元   回报:" + getMoney + "元   赚取:" + (getMoney - payMoney) + "元";
        text1.setText( firstContent2 + "\n" +firstContent3+"\n"+ text8Content);
    }

    /**
     * 敢死队模式
     * 1，2，4，8，16，32....
     */
    private void secondText() {
        int payMoney = 0, getMoney = 0;
        int mflag = 0;
        for (int i = 0; i < mList.size(); i++) {
            int key = mList.get(i);
            if (radioBtnFlag == 0) {//大
                if (key >= 11) {//中奖
                    getMoney = getMoney + (int) Math.pow(2, mflag);
                    mflag = 0;
                } else {//没中奖
                    payMoney = payMoney + (int) Math.pow(2, mflag);
                    mflag++;
                }
            } else if (radioBtnFlag == 1) {//小
                if (key < 11) {//中奖
                    getMoney = getMoney + (int) Math.pow(2, mflag);
                    mflag = 0;
                } else {//没中奖
                    payMoney = payMoney + (int) Math.pow(2, mflag);
                    mflag++;
                }
            }

        }
        Log.d(TAG, "getMoney2=" + getMoney + "  payMoney2=" + payMoney);
        String text7Content = "亏损:" + payMoney + "元   回报:" + getMoney + "元   赚取:" + (getMoney - payMoney) + "元";
        text2.setText(text7Content);
    }

    /**
     * 进一退二法
     * 1，2，3，5，8，13，21，34....前两期之和，不中前进一步，中了后退2步
     */
    String textThirdContent;

    private void thirdText() {
        int payMoney = 0, getMoney = 0;
        int mflag = 0;
        for (int i = 0; i < mList.size(); i++) {
            int key = mList.get(i);
            if (radioBtnFlag == 0) {//大
                if (key >= 11) {//中奖
                    getMoney = getMoney + getThridNum(mflag);
                    if (mflag >= 2) {
                        mflag = mflag - 2;
                    } else {
                        mflag = 0;
                    }
                } else {//没中奖
                    payMoney = payMoney + getThridNum(mflag);
                    mflag++;
                }
            } else if (radioBtnFlag == 1) {//小
                if (key < 11) {//中奖
                    getMoney = getMoney + getThridNum(mflag);
                    if (mflag >= 2) {
                        mflag = mflag - 2;
                    } else {
                        mflag = 0;
                    }
                } else {//没中奖
                    payMoney = payMoney + getThridNum(mflag);
                    mflag++;
                }
            }
        }
        Log.d(TAG, "getMoney3=" + getMoney + "  payMoney3=" + payMoney);
        textThirdContent = "亏损:" + payMoney + "元   回报:" + getMoney + "元   赚取:" + (getMoney - payMoney) + "元";
//        text3.setText(text7Content);
    }

    /**
     * 进一退二法
     * 1，2，3，5，8，前两期之和，不中前进一步，中了后退2步
     * 不中1，2，3，5，8
     * 出大买大，出小买小
     */
    String textThirdContent2;

    private void thirdText2() {
        int payMoney = 0, getMoney = 0;
        int mflag = 0;
        for (int i = 0; i < mList.size(); i++) {
            int key = mList.get(i);
            if (radioBtnFlag == 0) {//大
                if (key >= 11) {//中奖
                    getMoney = getMoney + getThridNum(mflag);
                    radioBtnFlag = 0;
//                    if (mflag >= 2) {
//                        mflag = mflag - 2;
//                    } else {
//                        mflag = 0;
//                    }
                    mflag = 0;
                } else {//没中奖
                    payMoney = payMoney + getThridNum(mflag);
                    radioBtnFlag = 1;
                    mflag++;
                    if (mflag > 4) mflag = 0;
                }
            } else if (radioBtnFlag == 1) {//小
                if (key < 11) {//中奖
                    getMoney = getMoney + getThridNum(mflag);
                    radioBtnFlag = 1;
//                    if (mflag >= 2) {
//                        mflag = mflag - 2;
//                    } else {
//                        mflag = 0;
//                    }
                    mflag = 0;
                } else {//没中奖
                    payMoney = payMoney + getThridNum(mflag);
                    radioBtnFlag = 0;
                    mflag++;
                    if (mflag > 4) mflag = 0;
                }
            }
        }
        Log.d(TAG, "getMoney3=" + getMoney + "  payMoney3=" + payMoney);
        textThirdContent2 = "亏损:" + payMoney + "元   回报:" + getMoney + "元   赚取:" + (getMoney - payMoney) + "元";
//        text3.setText(textThirdContent+"\n"+text7Content);
    }

    /**
     * 进一退二法
     * 1，2，3，5，8，前两期之和，不中前进一步，中了后退2步
     * 不中1，2，3，5，8
     * 中了 大小交换 ， 没中继续追
     */
    private void thirdText3() {
        radioBtnFlag = 0;
        int payMoney = 0, getMoney = 0;
        int mflag = 0;
        for (int i = 0; i < mList.size(); i++) {
            int key = mList.get(i);
            if (radioBtnFlag == 0) {//大
                if (key >= 11) {//中奖
                    getMoney = getMoney + getThridNum(mflag);
                    radioBtnFlag = 1;
//                    if (mflag >= 2) {
//                        mflag = mflag - 2;
//                    } else {
//                        mflag = 0;
//                    }
                    mflag = 0;
                } else {//没中奖
                    payMoney = payMoney + getThridNum(mflag);
                    mflag++;
                    if (mflag > 4) mflag = 0;
                }
            } else if (radioBtnFlag == 1) {//小
                if (key < 11) {//中奖
                    getMoney = getMoney + getThridNum(mflag);
                    radioBtnFlag = 0;
//                    if (mflag >= 2) {
//                        mflag = mflag - 2;
//                    } else {
//                        mflag = 0;
//                    }
                    mflag = 0;
                } else {//没中奖
                    payMoney = payMoney + getThridNum(mflag);
                    mflag++;
                    if (mflag > 4) mflag = 0;
                }
            }
        }
        Log.d(TAG, "getMoney3=" + getMoney + "  payMoney3=" + payMoney);
        String text7Content = "亏损:" + payMoney + "元   回报:" + getMoney + "元   赚取:" + (getMoney - payMoney) + "元";
        text3.setText(textThirdContent + "\n" + textThirdContent2 + "\n" + text7Content);
    }

    /**
     * 胜进模式
     * 1.倍投反过来，1，2，4, 3次为一周期
     */
    private void forthText1() {
        int payMoney = 0, getMoney = 0;
        int mflag = 0;
        radioBtnFlag = 0;
        for (int i = 0; i < mList.size(); i++) {
            int key = mList.get(i);
            if (radioBtnFlag == 0) {//大
                if (key >= 11) {//中奖
                    if (mflag > 2) {
                        mflag = 0;
                    }
                    getMoney = getMoney + (int) Math.pow(2, mflag);
                    mflag++;
                } else {//没中奖
                    payMoney = payMoney + (int) Math.pow(2, mflag);
                    mflag = 0;
                }
            } else if (radioBtnFlag == 1) {//小
                if (key < 11) {//中奖
                    if (mflag > 2) {
                        mflag = 0;
                    }
                    getMoney = getMoney + (int) Math.pow(2, mflag);
                    mflag++;
                } else {//没中奖
                    payMoney = payMoney + (int) Math.pow(2, mflag);
                    mflag = 0;
                }
            }

        }
        Log.d(TAG, "getMoney2=" + getMoney + "  payMoney2=" + payMoney);
        String text7Content = "亏损:" + payMoney + "元   回报:" + getMoney + "元   赚取:" + (getMoney - payMoney) + "元";
        text4.setText(text7Content);
    }

    /**
     * 胜进模式
     * 2.倍投反过来，1，2，4，8， 4次为一周期
     */
    private void forthText2() {
        int payMoney = 0, getMoney = 0;
        int mflag = 0;
        radioBtnFlag = 0;
        for (int i = 0; i < mList.size(); i++) {
            int key = mList.get(i);
            if (radioBtnFlag == 0) {//大
                if (key >= 11) {//中奖
                    if (mflag > 3) {
                        mflag = 0;
                    }
                    getMoney = getMoney + (int) Math.pow(2, mflag);
                    mflag++;
                } else {//没中奖
                    payMoney = payMoney + (int) Math.pow(2, mflag);
                    mflag = 0;
                }
            } else if (radioBtnFlag == 1) {//小
                if (key < 11) {//中奖
                    if (mflag > 3) {
                        mflag = 0;
                    }
                    getMoney = getMoney + (int) Math.pow(2, mflag);
                    mflag++;
                } else {//没中奖
                    payMoney = payMoney + (int) Math.pow(2, mflag);
                    mflag = 0;
                }
            }
        }
        Log.d(TAG, "getMoney5=" + getMoney + "  payMoney5=" + payMoney);
        String text7Content = "亏损:" + payMoney + "元   回报:" + getMoney + "元   赚取:" + (getMoney - payMoney) + "元";
        text5.setText(text7Content);
    }


    /**
     * 斐波拉契数列求和
     * 第二种 例:
     * String[] test2 = {"数组0","数组1","数组2","...."};
     * 第三种 例:
     * String[] test3 = new String[]{"数组0","数组1","数组2","...."};
     */
    private int getThridNum(int num) {
//        int[] array = new int[num + 1];
//        array[0] = 1;
//        array[1] = 2;
        int first = 1;
        int second = 2;
        int third = 0;
        if (num == 0) {
            return 1;
        }
        if (num == 1) {
            return 2;
        }
        for (int i = 2; i <= num; i++) {
            third = first + second;
            first = second;
            second = third;
//            array[i] = array[i - 1] + array[i - 2];
//            s = array[i];
        }
        return third;
    }

    public static void start(Context context){
        Intent intent = new Intent(context,MainActivity.class);
        context.startActivity(intent);
    }

}
