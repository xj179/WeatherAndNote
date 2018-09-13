package com.lxj.note.myth.vest;

import android.app.Application;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.lxj.note.myth.vest.net.MultiPartStack;

import org.xutils.x;

import cn.jpush.android.api.JPushInterface;
import test.greenDAO.dao.DaoMaster;
import test.greenDAO.dao.DaoSession;

public class MyApplication extends Application {

    private static MyApplication mInstance;
    private static DaoMaster daoMaster;
    private static DaoSession daoSession;

    private static Context mContext;
    private static RequestQueue mQueues;

    public static Context getContext() {
        return mContext;
    }

    public static RequestQueue getHttpQueue(){
        return mQueues;
    }

    @Override
    //程序的入口
    public void onCreate() {
        super.onCreate();
    	x.Ext.init(this);
        mContext = this ;
		mQueues= Volley.newRequestQueue(mContext,new MultiPartStack());

		//极光初始化
        JPushInterface.setDebugMode(true);  //调试模式
        JPushInterface.init(this);
    }

    /**
     * 取得DaoMaster
     *
     * @param context 上下文
     * @return DaoMaster
     */
    public static DaoMaster getDaoMaster(Context context) {
        if (daoMaster == null) {
            DaoMaster.OpenHelper helper = new DaoMaster.DevOpenHelper(context, "myDb", null);
            daoMaster = new DaoMaster(helper.getWritableDatabase());
        }
        return daoMaster;
    }

    /**
     * 取得DaoSession
     *
     * @param context 上下文
     * @return DaoSession
     */
    public static DaoSession getDaoSession(Context context) {
        if (daoSession == null) {
            if (daoMaster == null) {
                daoMaster = getDaoMaster(context);
            }
            daoSession = daoMaster.newSession();
        }
        return daoSession;
    }

    public void onLowMemory() {
        super.onLowMemory();
        System.gc();
    }
}
