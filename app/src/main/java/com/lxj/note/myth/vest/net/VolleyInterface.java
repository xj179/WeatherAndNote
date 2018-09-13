package com.lxj.note.myth.vest.net;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;

/**
 * 封装volley成功回调和失败回调的方法
 */
public abstract class VolleyInterface {
	public Context mContext;
	public static Response.Listener<String> mListener;
	public static Response.ErrorListener mErrorListener;

	public VolleyInterface(Context mContext,
			Response.Listener<String> mListener,
			Response.ErrorListener mErrorListener) {
		this.mContext = mContext;
		this.mListener = mListener;
		this.mErrorListener = mErrorListener;
	}

	public VolleyInterface(Context mContext) {
		this.mContext = mContext;
	}

	public abstract void onSuccess(String result);

	public abstract void onError(VolleyError error);

	/**
	 * 成功回调
	 * 
	 * @return Response.Listener<String>
	 */
	public Response.Listener<String> loadingListener() {
		mListener = new Response.Listener<String>() {
			@Override
			public void onResponse(String response) {
				onSuccess(response);
			}
		};
		return mListener;
	}

	/**
	 * 失败回调
	 * 
	 * @return
	 */
	public Response.ErrorListener errorListener() {
		mErrorListener = new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				onError(error);
			}
		};
		return mErrorListener;
	}
}
