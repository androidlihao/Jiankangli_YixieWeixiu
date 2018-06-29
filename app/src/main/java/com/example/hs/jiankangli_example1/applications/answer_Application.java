package com.example.hs.jiankangli_example1.applications;

import android.app.Activity;
import android.app.Application;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by 李浩 on 2016/11/10.
 */
public class answer_Application extends Application {
    private List<Activity> mList = new LinkedList();
    private static answer_Application instance;
    private answer_Application() {
    }
    public synchronized static answer_Application getInstance() {
        if (null == instance) {
            instance = new answer_Application();
        }
        return instance;
    }
    //add Activity
    public void addActivity(Activity activity) {
        mList.add(activity);
    }
    public void kill() {
        for (Activity activity : mList) {
            if (activity != null)
                activity.finish();
        }
        mList.clear();//将集合清空
    }
}
