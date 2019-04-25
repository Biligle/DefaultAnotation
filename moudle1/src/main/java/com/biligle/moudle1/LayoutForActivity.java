package com.biligle.moudle1;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * @Author wangguoli
 */
public class LayoutForActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_layout);
        if (BuildConfig.isMoudle) {
            //组件模式
            Toast.makeText(this,"组件模式",Toast.LENGTH_SHORT).show();
        } else {
            //集成模式
        }
    }
}
