package com.lxj.note.myth.vest.net;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.lxj.note.myth.vest.MyApplication;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Volley工具类
 */
public class VolleyUtils {

	public static Context mContext;
	public static StringRequest mStringRequest;

	/**
	 * get请求
	 * 
	 * @param mContext
	 * @param url
	 * @param tag
	 * @param vif
	 */
	public static void requestGet(Context mContext, String url, String tag,
                                  VolleyInterface vif) {
		MyApplication.getHttpQueue().cancelAll(tag);
		mStringRequest = new StringRequest(Request.Method.GET, url,
				vif.loadingListener(), vif.errorListener());
		mStringRequest.setTag(tag);
		MyApplication.getHttpQueue().add(mStringRequest);
//		MyApplication.getHttpQueue().start();
	}

	/**
	 * post请求（带有map传递参数）
	 * 
	 * @param mContext
	 * @param url
	 * @param tag
	 * @param params
	 * @param vif
	 */
	public static void requestPost(Context mContext, String url, String tag,
                                   final Map<String, String> params, VolleyInterface vif) {
		StringBuffer auth = new StringBuffer();
		StringBuffer token = new StringBuffer();
		for (Entry<String, String> entry : params.entrySet()) {
			if ("".equals(auth.toString())) {
				auth.append(entry.getKey());
			} else {
				auth.append("," + entry.getKey());
			}
			token.append(entry.getValue());
		}
		MyApplication.getHttpQueue().cancelAll(tag);
		mStringRequest = new StringRequest(Request.Method.POST, url,
				vif.loadingListener(), vif.errorListener()) {
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				System.out.println("params:" + params);
				return params;
			}
		};
		mStringRequest.setRetryPolicy(new DefaultRetryPolicy(500000,
				DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
				DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

		mStringRequest.setTag(tag);
		MyApplication.getHttpQueue().add(mStringRequest);
		MyApplication.getHttpQueue().start();
	}

	/**
	 * 上传文件（带有map传递参数）
	 * 
	 * @param mContext
	 * @param url
	 * @param tag
	 * @param params
	 * @param vif
	 */
	public static void filePost(Context mContext, String url, String tag,
                                final Map<String, String> params, final Map<String, File> files,
                                VolleyInterface vif) {
		StringBuffer auth = new StringBuffer();
		StringBuffer token = new StringBuffer();
		for (Entry<String, String> entry : params.entrySet()) {
			if ("".equals(auth.toString())) {
				auth.append(entry.getKey());
			} else {
				auth.append("," + entry.getKey());
			}
			token.append(entry.getValue());
		}
		MyApplication.getHttpQueue().cancelAll(tag);
		MultiPartStringRequest multiPartRequest = new MultiPartStringRequest(
				Request.Method.POST, url, vif.loadingListener(),
				vif.errorListener()) {
			@Override
			public Map<String, File> getFileUploads() {
				return files;
			}

			@Override
			public Map<String, String> getStringUploads() {
				return params;
			}
		};
		multiPartRequest.setRetryPolicy(new DefaultRetryPolicy(500000,
				DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
				DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
		multiPartRequest.setTag(tag);
		MyApplication.getHttpQueue().add(multiPartRequest);
		MyApplication.getHttpQueue().start();
	}

	/**
	 * post请求(带有参数map 重写传递参数方法)
	 *
	 * @param mContext
	 * @param url
	 *            地址
	 * @param tag
	 *            标签
	 * @param params
	 *            参数
	 * @param vif
	 *            接口 void
	 *
	 */
	public static void requestSpecPost(Context mContext, String url,
                                       String tag, final Map<String, String> params, VolleyInterface vif) {
		MyApplication.getHttpQueue().cancelAll(tag);
		mStringRequest = new StringRequest(Request.Method.POST, url,
				vif.loadingListener(), vif.errorListener()) {
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				System.out.println("params:" + params);
				return params;
			}

			@Override
			public byte[] getBody() throws AuthFailureError {
				Map<String, String> params = getParams();
				if (params != null && params.size() > 0) {
					try {
						return params.get("data").getBytes("UTF-8");
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
				}
				return null;
			}
		};

		mStringRequest.setTag(tag);
		MyApplication.getHttpQueue().add(mStringRequest);
		MyApplication.getHttpQueue().start();
	}

	/**
	 * volley返回信息
	 *
	 *
	 * @param error
	 * @param context
	 * @return String
	 */
	public static String getMessage(Object error, Context context) {
		if (error instanceof TimeoutError) {
			return "连接服务器超时";
		} else if (isServerProblem(error)) {
			return handleServerError(error, context);
		} else if (isNetworkError(error)) {
			return "当前网络不稳定，请重新再试";
		} else if (isNoConnectionError(error)) {
			return "无网络连接";
		}
		return "当前网络不稳定，请重新再试";
	}

	private static boolean isNetworkError(Object error) {
		return (error instanceof NetworkError);
	}

	private static boolean isNoConnectionError(Object error) {
		return (error instanceof NoConnectionError);
	}

	private static boolean isServerProblem(Object error) {
		return (error instanceof ServerError)
				|| (error instanceof AuthFailureError);
	}

	private static String handleServerError(Object err, Context context) {
		VolleyError error = (VolleyError) err;
		NetworkResponse response = error.networkResponse;
		if (response != null) {
			switch (response.statusCode) {
			case 404:
			case 422:
			case 401:
				return "连接服务器失败";

			default:
				return "连接服务器超时";
			}
		}
		return "当前网络不稳定，请重新再试";
	}
}
