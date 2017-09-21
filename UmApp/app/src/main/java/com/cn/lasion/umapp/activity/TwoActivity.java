package com.cn.lasion.umapp.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.cn.lasion.umapp.fragment.TwoFragment;

/**
 * Created by 21032 on 2017/9/20.
 */

public class TwoActivity extends MyBaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupFragment(new TwoFragment());
    }
}
