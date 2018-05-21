package com.aboutball.wp.aboutball.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import com.aboutball.wp.aboutball.R;

/**
 * Created by wp on 2018/5/21.
 */

public class WelcomeActivity extends BaseActivity {

    private final int WHAT = 0;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
            finish();
        }
    };
    private ImageView mWelcomeIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        mWelcomeIv = findViewById(R.id.welcome_img);
        handler.sendEmptyMessageDelayed(0, 2000);
        showProgressDialog("loadidng");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (handler != null) {
            handler.removeMessages(WHAT);
        }
    }
}
