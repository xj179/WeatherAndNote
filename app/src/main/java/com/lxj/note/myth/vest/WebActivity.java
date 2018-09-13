package com.lxj.note.myth.vest;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;

import com.lxj.note.myth.R;

import java.io.File;

/**
 */
public class WebActivity extends AppCompatActivity {

    private WebView mWebView;
    private RelativeLayout mLoadingTL;

    public static final String WEB_URL = "web_url" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*设置状态栏渐变色*/
      /*  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }*/

        setContentView(R.layout.activity_web);
        mLoadingTL = (RelativeLayout) findViewById(R.id.loading_web);
        mWebView = (WebView) findViewById(R.id.web_view);
//        mWebView.setWebViewClient(new MyWebViewClient());
        mWebView.setWebViewClient(new WebViewClient());
        String url = getIntent().getExtras().getString(WEB_URL);

        WebSettings settings = mWebView.getSettings();
        settings.setLoadWithOverviewMode(true);
        settings.setBuiltInZoomControls(false);
        settings.setSupportZoom(false);
        settings.setDisplayZoomControls(false);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        settings.setDomStorageEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        settings.setAllowFileAccess(true);// 设置允许访问文件数据
        settings.setUseWideViewPort(true);
        settings.setSupportMultipleWindows(true);
        settings.setBlockNetworkImage(false);//同步请求图片

        mWebView.setVerticalScrollBarEnabled(true);
        mWebView.setHorizontalScrollBarEnabled(true);
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.getSettings().setJavaScriptEnabled(true);

        //设置缓存路径
        String appCacheDir = Environment.getExternalStorageDirectory().getPath() + "/2048master/cache";
        File fileSD = new File(appCacheDir);
        if (!fileSD.exists()) {
            fileSD.mkdir();
        }
        mWebView.requestFocus();
        mWebView.clearHistory();//只会webview访问历史记录里的所有记录除了当前访问记录
        mWebView.clearFormData();//这个api仅仅清除自动完成填充的表单数据，并不会清除WebView存储到本地的数据
//        mWebView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        mWebView.loadUrl(url);

  /*      mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                mWebView.loadUrl(url);
                return true;
            }
        });*/

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mWebView != null) {
     /*       mWebView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            mWebView.clearHistory();
            ((ViewGroup) mWebView.getParent()).removeView(mWebView);*/
            mWebView.destroy();
            mWebView = null;
        }
    }

    //定义一个progressDialog来实现中间显示加载进度和温馨提示
    private class MyWebViewClient extends WebViewClient {

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {//网页页面开始加载的时候
            mLoadingTL.setVisibility(View.VISIBLE);
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {//网页加载结束的时候
            mLoadingTL.setVisibility(View.GONE);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) { //网页加载时的连接的网址
            mLoadingTL.setVisibility(View.GONE);
            view.loadUrl(url);
            return false;
        }
    }
}
