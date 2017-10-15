package com.example.jingle.circleprogressview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private CircleProgressView circleview;
    private android.widget.LinearLayout activitymain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.activitymain = (LinearLayout) findViewById(R.id.activity_main);
        this.circleview = (CircleProgressView) findViewById(R.id.circle_view);
        circleview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circleview.changeAngle();
            }
        });
    }
}
