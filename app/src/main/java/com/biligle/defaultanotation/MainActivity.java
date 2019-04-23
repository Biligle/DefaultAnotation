package com.biligle.defaultanotation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (BuildConfig.isDebug) {
            //Debug包，一般用于测试，可以弄一些开发者模式功能
        } else {
            //release
        }
    }
}
