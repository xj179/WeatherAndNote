package com.lxj.note.myth.vest.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.ProgressBar;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 文件下载工具类（单例模式）
 * Created on 2017/10/16.
 */

public class DownloadUtil {
    private static DownloadUtil downloadUtil;
    private final OkHttpClient okHttpClient;

    public static DownloadUtil get() {
        if (downloadUtil == null) {
            downloadUtil = new DownloadUtil();
        }
        return downloadUtil;
    }

    private DownloadUtil() {
        okHttpClient = new OkHttpClient();
    }

    /**
     * @param url          下载连接
     * @param destFileDir  下载的文件储存目录
     * @param destFileName 下载文件名称
     * @param listener     下载监听
     */
    public void download(final String url, final String destFileDir, final String destFileName, final OnDownloadListener listener) {
        Request request = new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // 下载失败监听回调
                listener.onDownloadFailed(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream is = null;
                byte[] buf = new byte[2048];
                int len = 0;
                FileOutputStream fos = null;
                // 储存下载文件的目录
                File dir = new File(destFileDir);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                File file = new File(dir, destFileName);
                try {
                    is = response.body().byteStream();
                    long total = response.body().contentLength();
                    fos = new FileOutputStream(file);
                    long sum = 0;
                    while ((len = is.read(buf)) != -1) {
                        fos.write(buf, 0, len);
                        sum += len;
                        int progress = (int) (sum * 1.0f / total * 100);
                        // 下载中更新进度条
                        listener.onDownloading(progress);
                    }
                    fos.flush();
                    // 下载完成
                    listener.onDownloadSuccess(file);
                } catch (Exception e) {
                    listener.onDownloadFailed(e);
                } finally {
                    try {
                        if (is != null)
                            is.close();
                    } catch (IOException e) {
                    }
                    try {
                        if (fos != null)
                            fos.close();
                    } catch (IOException e) {
                    }
                }
            }
        });
    }

    public interface OnDownloadListener {
        /**
         * @param file 下载成功后的文件
         */
        void onDownloadSuccess(File file);

        /**
         * @param progress 下载进度
         */
        void onDownloading(int progress);

        /**
         * @param e 下载异常信息
         */
        void onDownloadFailed(Exception e);
    }

    static String apkPath = "";
    /**
     * 文件下载
     *
     * @param url
     */
    public static void downFile(final Context context, String url, final ProgressBar progressBar) {
        if (progressBar == null) {
            final ProgressDialog progressDialog = new ProgressDialog(context);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.setTitle("正在下载");
            progressDialog.setMessage("请稍后...");
            progressDialog.setProgress(0);
            progressDialog.setMax(100);
            progressDialog.show();
            progressDialog.setCancelable(false);
            DownloadUtil.get().download(url, context.getExternalCacheDir().getAbsolutePath(), "vest.apk", new DownloadUtil.OnDownloadListener() {
                @Override
                public void onDownloadSuccess(File file) {
                    if (progressDialog != null && progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    //下载完成进行相关逻辑操作

                }

                @Override
                public void onDownloading(int progress) {
                    progressDialog.setProgress(progress);
                }

                @Override
                public void onDownloadFailed(Exception e) {
                    //下载异常进行相关提示操作
                    Log.e("xjlei", "下载失败：" + e.getMessage()) ;
                }
            });
        } else {
            progressBar.setProgress(0);
            progressBar.setMax(100);
            DownloadUtil.get().download(url, context.getExternalCacheDir().getAbsolutePath(), "vest.apk", new DownloadUtil.OnDownloadListener() {
                @Override
                public void onDownloadSuccess(File file) {
                    //下载完成进行相关逻辑操作
                    apkPath = file.getAbsolutePath() ;
                    //下载完之后直接进行安装
                    InstallUtil.akInstall(context, file.getAbsolutePath());

//                    EventBus.getDefault().post(new BusEventData(BusEventData.KEY_CLOSE));
                }

                @Override
                public void onDownloading(int progress) {
                    progressBar.setProgress(progress);
                }

                @Override
                public void onDownloadFailed(Exception e) {
                    //下载异常进行相关提示操作
                    Log.e("xjlei", "下载失败：" + e.getMessage()) ;
                }
            });
        }
    }

}
