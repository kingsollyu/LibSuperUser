package com.sollyu.android.lib.superuser.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.sollyu.android.libsuperuser.Shell;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Shell";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            Shell.Result result = null;

            result= Shell.Su.run("ls /asdfasdf");
            Log.d(TAG, "命令执行结束：" + result.getExitCode());

            result = Shell.Su.run("ls -al /system/build.prop");
            Log.d(TAG, "命令执行结束：" + result.getExitCode());

            result = Shell.Su.run("ping -c 4 www.baidu.com");
            Log.d(TAG, "命令执行结束：" + result.getExitCode());
        } catch (IOException e) {
            Log.e(TAG, e.getMessage(), e);
        }

        try {
            Shell.Result result = null;
            result= Shell.Sh.run("ls /system/build.prop");
            Log.d(TAG, "命令执行结束：" + result.getExitCode());

            result= Shell.Sh.run("ls -al /sdafsdfasdf");
            Log.d(TAG, "命令执行结束：" + result.getExitCode());

            result= Shell.Sh.run("cat /proc/cpuinfo");
            Log.d(TAG, "命令执行结束：" + result.getExitCode());
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
        }
    }
}
