package com.don.comm;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

/**
 * FileName: BActivity
 * Author: SnowDragon
 * Date: 2020/12/25 8:48
 * Description:
 */
public class BActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);

        TextView textView = findViewById(R.id.tv_new_add_master);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isAppForgroud(BActivity.this);
            }
        });

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
                        Log.i("chyy", "  " + taskInfo.taskDescription.toString());
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
