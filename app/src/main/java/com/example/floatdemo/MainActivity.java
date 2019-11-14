package com.example.floatdemo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.floatdemo.floatwindow.FloatWindow;
import com.example.floatdemo.floatwindow.PermissionUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.tv_open_float).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!PermissionUtil.hasPermission(App.getInstance())){
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this,R.style.Theme_AppCompat_Light_Dialog_Alert)
                            .setTitle("拼字比赛需要在应用设置中开启悬浮窗权限，是否前往开启权限？")
                            .setPositiveButton("是的", (dialog, which) -> FloatWindow.get().initPermission(false))
                            .setNegativeButton("取消", null);
                    builder.show();
                }else {
                    FloatWindow.get().show();
                }

            }
        });
        findViewById(R.id.tv_close_float).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FloatWindow.get().hide();
            }
        });
    }
}
