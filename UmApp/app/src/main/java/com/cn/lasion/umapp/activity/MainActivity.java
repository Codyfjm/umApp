package com.cn.lasion.umapp.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.cn.lasion.umapp.R;
import com.umeng.analytics.MobclickAgent;


public class MainActivity extends AppCompatActivity {

    private Context mContext;
    private final String mPageName = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;

        //集成调试
        MobclickAgent.setDebugMode(true);
        // SDK在统计Fragment时，需要关闭Activity自带的页面统计，
        // 然后在每个页面中重新集成页面统计的代码(包括调用了 onResume 和 onPause 的Activity)。
        MobclickAgent.openActivityDurationTrack(false);
        MobclickAgent.setScenarioType(mContext, MobclickAgent.EScenarioType.E_UM_NORMAL);

    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(mContext); //统计时长
        MobclickAgent.onPageStart(mPageName); //统计页面(仅有Activity的应用中SDK自动调用，不需要单独写。"mPageName"为页面名称，可自定义)
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(mContext); //统计时长
        MobclickAgent.onPageEnd(mPageName);
    }


    public void OneClick(View view) {
        startActivity(new Intent(MainActivity.this, OneAcitivity.class));
    }

    public void TwoClick(View view) {
        startActivity(new Intent(MainActivity.this, TwoActivity.class));
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            MobclickAgent.onKillProcess(mContext);

            int pid = android.os.Process.myPid();
            android.os.Process.killProcess(pid);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
