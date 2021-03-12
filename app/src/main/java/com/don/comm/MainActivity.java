package com.don.comm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.tv_new_add_master);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addTask();
                isAppForgroud(MainActivity.this);
            }
        });

        TextView tvBactivity = findViewById(R.id.tv_start_bb);
        tvBactivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isAppForgroud(MainActivity.this);
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, BActivity.class);
                startActivity(intent);
            }
        });
    }

    private  Bitmap getBitmap(Context context,int vectorDrawableId) {
        Bitmap bitmap=null;
        if (Build.VERSION.SDK_INT>Build.VERSION_CODES.LOLLIPOP){
            Drawable vectorDrawable = context.getDrawable(vectorDrawableId);
            bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(),
                    vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            vectorDrawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            vectorDrawable.draw(canvas);
        }else {
            bitmap = BitmapFactory.decodeResource(context.getResources(), vectorDrawableId);
        }
        return bitmap;
    }

    @Override
    protected void onPause() {
        super.onPause();

    }
    private void addTask(){
        ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        Intent intent = new Intent(this,BActivity.class);
        Bitmap bitmap = getBitmap(getApplication(),R.mipmap.ic_launcher);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            am.addAppTask(this,intent,new ActivityManager.TaskDescription(),
                    bitmap );

        }

    }

    public void isAppForgroud(Context context) {


        ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);


        if (context != null) {
            String packName = context.getPackageName();
            List<ActivityManager.RunningTaskInfo> rTasks = am.getRunningTasks(100);
            Log.i("chyy", " taskSize: " + rTasks.size());
            for (ActivityManager.RunningTaskInfo taskInfo : rTasks) {
                if (taskInfo.topActivity.getPackageName().equals(getPackageName())
                        || taskInfo.baseActivity.getPackageName().equals(getPackageName())) {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        Log.i("chyy", " --- " + taskInfo.taskDescription.toString());
                    } else {
                        Log.i("chyy", "  Q download : " + taskInfo.description);
                    }
                    if (taskInfo.topActivity != null) {
                        Log.i("chyy", "activity topActivity : " + taskInfo.topActivity.getClassName());
                    }

                    if (taskInfo.baseActivity != null) {
                        Log.i("chyy", "activity baseActivity : " + taskInfo.baseActivity.getClassName());
                    }

                }
                ActivityManager.RunningTaskInfo task = rTasks.get(0);


            }

        }
    }
}