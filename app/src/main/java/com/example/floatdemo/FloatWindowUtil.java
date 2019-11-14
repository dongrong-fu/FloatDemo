package com.example.floatdemo;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.floatdemo.floatwindow.FloatWindow;
import com.example.floatdemo.floatwindow.IFloatWindow;
import com.example.floatdemo.floatwindow.MoveType;
import com.example.floatdemo.floatwindow.PermissionListener;
import com.example.floatdemo.floatwindow.Screen;
import com.example.floatdemo.floatwindow.ViewStateListener;


/**
 * Created by dongrong.fu on 2019/9/17
 */
public class FloatWindowUtil {

    private static final String TAG = "FloatWindowUtil";
    private static int x;
    private static int y;

    public static void initFloatWindow() {
        ImageView imageView = new ImageView(App.getInstance());
        imageView.setImageResource(R.drawable.pinzi_wait_l);
        try {
            FloatWindow.initLifecycle(App.getInstance());
            FloatWindow
                    .with(App.getInstance())
                    .setView(imageView)
                    .setWidth(ScreenUtils.dp2px(App.getInstance(), 72))
                    .setHeight(ScreenUtils.dp2px(App.getInstance(), 72))
                    .setX(-ScreenUtils.dp2px(App.getInstance(), 5))
                    .setY(Screen.height, 0.5f)
                    .setMoveType(MoveType.slide, -ScreenUtils.dp2px(App.getInstance(), 5), -ScreenUtils.dp2px(App.getInstance(), 5))
                    .setMoveStyle(500, new BounceInterpolator())
                    .setViewStateListener(mViewStateListener)
                    .setPermissionListener(mPermissionListener)
                    .setDesktopShow(false)
                    .build();
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }

    }

    private static PermissionListener mPermissionListener = new PermissionListener() {
        @Override
        public void onSuccess() {
            FloatWindow.get().hide();
            Toast.makeText(App.getInstance(), "有权限了", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onFail() {
        }
    };

    public static void updateFloatVisio() {
        ImageView imageview = (ImageView) FloatWindow.get().getView();
        if (x <= 0) {
            imageview.setImageResource(R.drawable.pinzi_wait_l);
        } else if (x >= ScreenUtils.getScreenWidth(App.getInstance()) - ScreenUtils.dp2px(App.getInstance(), 67)) {
            imageview.setImageResource(R.drawable.pinzi_wait_r);
        } else {
            imageview.setImageResource(R.drawable.pinzi_wait_m);
        }
        imageview.setOnClickListener(v1 -> {
            Toast.makeText(App.getInstance(), "点击了悬浮窗", Toast.LENGTH_SHORT).show();
        });
    }


    private static ViewStateListener mViewStateListener = new ViewStateListener() {
        @Override
        public void onPositionUpdate(int x, int y) {
            FloatWindowUtil.x = x;
            FloatWindowUtil.y = y;
            updateFloatVisio();
        }

        @Override
        public void onShow() {
            Log.d(TAG, "onShow");
        }

        @Override
        public void onHide() {
            Log.d(TAG, "onHide");
        }

        @Override
        public void onDismiss() {
            Log.d(TAG, "onDismiss");
        }

        @Override
        public void onMoveAnimStart() {
            Log.d(TAG, "onMoveAnimStart");
        }

        @Override
        public void onMoveAnimEnd() {
            Log.d(TAG, "onMoveAnimEnd");
        }

        @Override
        public void onBackToDesktop() {
            Log.d(TAG, "onBackToDesktop");
        }
    };
}
