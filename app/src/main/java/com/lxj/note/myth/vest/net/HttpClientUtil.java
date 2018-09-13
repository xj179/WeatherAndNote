package com.lxj.note.myth.vest.net;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpClientUtil {

	/**
	 * 描述: 基于okHttp 完成的post请求
	 */
	public static String okHttpPost(String url, Map<String, String> params) {

		// 1.创建okHttpClient 对象
		OkHttpClient okHttpClient = new OkHttpClient();

		FormBody.Builder builder = new FormBody.Builder();

		for (Map.Entry<String, String> entry : params.entrySet()) {
			builder.add(entry.getKey(), entry.getValue());
		}

		// 2.创建请求
		Request request = new Request.Builder().url(url).post(builder.build())
				.build();

		// 3.发起请求
		Response response;
		String result = null;
		try {
			response = okHttpClient.newCall(request).execute();

			if (response.isSuccessful()) {

				result = response.body().string();

				return result;
			} else {
				return null;
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			return result;
		}
	}

	/**
	 * 描述: 基于okHttp 完成的get请求
	 */
	public static String okHttpGet(String url, Map<String, String> params) {

		// 1.创建okHttpClient 对象
		OkHttpClient okHttpClient = new OkHttpClient();

		String fullUrl = url + "?" + HttpUtils.getUrlParamsByMap(params);

		Log.i("拼接的地址:", fullUrl);

		// 2.创建请求
		Request request = new Request.Builder().get().url(fullUrl).build();

		// 3.发起请求
		Response response;
		String result = null;
		try {
			response = okHttpClient.newCall(request).execute();

			if (response.isSuccessful()) {

				result = response.body().string();

				return result;
			} else {
				return null;
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			return result;
		}
	}


	/**
	 * 自己实现HTTP请求 HttpClient POST请求
	 *
	 * @param url
	 * @param params
	 * @return
	 */
	public static String postss(String url, Map<String, String> params) {
		/*StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
				.detectDiskReads().detectDiskWrites().detectNetwork()
				.penaltyLog().build());
		StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
				.detectLeakedSqlLiteObjects().detectLeakedClosableObjects()
				.penaltyLog().penaltyDeath().build());
		try {
//			params.put("key", Constant.API_KEY);
			params.put("time", System.currentTimeMillis() + "");
			params.put("user_token", "");
			List<NameValuePair> postParams = new ArrayList<NameValuePair>();
			StringBuffer auth = new StringBuffer();
			StringBuffer token = new StringBuffer();
			for (Entry<String, String> entry : params.entrySet()) {
				if ("".equals(auth.toString())) {
					auth.append(entry.getKey());
				} else {
					auth.append("," + entry.getKey());
				}
				token.append(entry.getValue());
				postParams.add(new BasicNameValuePair(entry.getKey(), entry
						.getValue()));
			}
			postParams.add(new BasicNameValuePair("auth", Base32.encode((auth
					.toString()).getBytes())));
			postParams.add(new BasicNameValuePair("token", MD5.GetMD5Code(token
					.toString())));

			// 创建POST请求
			HttpPost request = new HttpPost(url);
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(postParams,
					HTTP.UTF_8);
			request.setEntity(entity);
			// 发送请求
			HttpClient client = new DefaultHttpClient();
			client.getParams().setParameter(
					CoreConnectionPNames.CONNECTION_TIMEOUT, 15000);
			client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,
					15000);
			HttpResponse response = client.execute(request);
			if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
				throw new RuntimeException("请求失败");
			}
			HttpEntity resEntity = response.getEntity();
			return (resEntity == null) ? null : EntityUtils.toString(resEntity,
					HTTP.UTF_8);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}*/
		return "" ;
	}

	/** POST请求 */
	public static String post(String url, Map<String, String> params) {
	/*	try {
			params.put("key", Constant.API_KEY);
			params.put("time", System.currentTimeMillis() + "");
			params.put("user_token", "");
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
			params.put("auth", Base32.encode((auth.toString()).getBytes()));
			params.put("token", MD5.GetMD5Code(token.toString()));

			byte[] data = getRequestData(params, HTTP.UTF_8).toString()
					.getBytes();// 获得请求体
			URL urls = new URL(url);
			HttpURLConnection httpURLConnection = (HttpURLConnection) urls
					.openConnection();
			httpURLConnection.setConnectTimeout(3000); // 设置连接超时时间
			httpURLConnection.setDoInput(true); // 打开输入流，以便从服务器获取数据
			httpURLConnection.setDoOutput(true); // 打开输出流，以便向服务器提交数据
			httpURLConnection.setRequestMethod("POST"); // 设置以Post方式提交数据
			httpURLConnection.setUseCaches(false); // 使用Post方式不能使用缓存
			// 设置请求体的类型是文本类型
			httpURLConnection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			// 设置请求体的长度
			httpURLConnection.setRequestProperty("Content-Length",
					String.valueOf(data.length));
			// 获得输出流，向服务器写入数据
			OutputStream outputStream = httpURLConnection.getOutputStream();
			outputStream.write(data);

			int response = httpURLConnection.getResponseCode(); // 获得服务器的响应码
			if (response == HttpURLConnection.HTTP_OK) {
				InputStream inptStream = httpURLConnection.getInputStream();
				return dealResponseResult(inptStream); // 处理服务器的响应结果
			}
		} catch (IOException e) {
			// e.printStackTrace();
			return "";
		};*/
		return "" ;
	}

	/*
	 * Function : 封装请求体信息 Param : params请求体内容，encode编码格式
	 */
	public static StringBuffer getRequestData(Map<String, String> params,
                                              String encode) {
		StringBuffer stringBuffer = new StringBuffer(); // 存储封装好的请求体信息
		try {
			for (Map.Entry<String, String> entry : params.entrySet()) {
				stringBuffer.append(entry.getKey()).append("=")
						.append(URLEncoder.encode(entry.getValue(), encode))
						.append("&");
			}
			stringBuffer.deleteCharAt(stringBuffer.length() - 1); // 删除最后的一个"&"
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stringBuffer;
	}

	/*
	 * Function : 处理服务器的响应结果（将输入流转化成字符串） Param : inputStream服务器的响应输入流
	 */
	public static String dealResponseResult(InputStream inputStream) {
		String resultData = null; // 存储处理结果
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		byte[] data = new byte[1024];
		int len = 0;
		try {
			while ((len = inputStream.read(data)) != -1) {
				byteArrayOutputStream.write(data, 0, len);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		resultData = new String(byteArrayOutputStream.toByteArray());
		return resultData;
	}

	/**
	 * 自己实现的Http请求 HttpClient get请求
	 *
	 * @param url
	 * @return
	 */
	public static String get(String url) {
		HttpGet httpGet = new HttpGet(url);
		// 设置连接超时或响应超时
		HttpConnectionParams.setConnectionTimeout(httpGet.getParams(), 15000);
		HttpConnectionParams.setSoTimeout(httpGet.getParams(), 15000);
		// 创建一个网络访问处理对象
		HttpClient httpClient = new DefaultHttpClient();
		try {
			HttpResponse response = httpClient.execute(httpGet); // 发起GET请求
			if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
				throw new RuntimeException("请求失败");
			}
			HttpEntity resEntity = response.getEntity();
			return (resEntity == null) ? null : EntityUtils.toString(resEntity,
					HTTP.UTF_8);
		} catch (Exception e) {
			return null;
		} finally {
			// 释放网络连接资源
			httpClient.getConnectionManager().shutdown();
		}
	}

	/**
	 * 描述: 使用okHttp 完成的get请求
	 */
	/*
	 * public static String okHttpGet(String url, Map<String, String> params) {
	 * 
	 * //1.创建okHttpClient 对象 OkHttpClient okHttpClient = new OkHttpClient();
	 * 
	 * String fullUrl = url + "?" + HttpUtils.getUrlParamsByMap(params);
	 * 
	 * Log.i("拼接的地址:", fullUrl);
	 * 
	 * //2.创建请求 Request request = new
	 * Request.Builder().get().url(fullUrl).build();
	 * 
	 * //3.发起请求 Response response; String result = null; try { response =
	 * okHttpClient.newCall(request).execute();
	 * 
	 * if (response.isSuccessful()) {
	 * 
	 * result = response.body().string();
	 * 
	 * return result; } else { return null; } } catch (IOException e) {
	 * e.printStackTrace(); return null; } finally { return result; } }
	 */

	/*  *//**
	 * 自定义请求添加共用的参数
	 *
	 * @param context
	 * @param params
	 */
	/*
	 * public static void addOkHttpComm(Context context, Map<String, String>
	 * params) { TelephonyManager tm = (TelephonyManager)
	 * context.getSystemService(Context.TELEPHONY_SERVICE);
	 * params.put("device_id", PreferenceUtil.getId(context));
	 * params.put("device", "Android"); params.put("userkey", "1");
	 * params.put("systemVer", android.os.Build.MODEL +
	 * android.os.Build.VERSION.RELEASE); params.put("imei",
	 * tm.getSimSerialNumber() == null ? "" : tm.getSimSerialNumber());
	 * params.put("key", Constant.API_KEY); params.put("time",
	 * System.currentTimeMillis() + ""); params.put("user_token", "");
	 * 
	 * // params.put("site_code", "common_old"); //new add 2016-03-16
	 * 
	 * StringBuffer auth = new StringBuffer(); StringBuffer token = new
	 * StringBuffer(); for (Entry<String, String> entry : params.entrySet()) {
	 * if ("".equals(auth.toString())) { auth.append(entry.getKey()); } else {
	 * auth.append("," + entry.getKey()); } token.append(entry.getValue()); }
	 * params.put("auth", Base32.encode((auth .toString()).getBytes()));
	 * params.put("token", MD5.GetMD5Code(token .toString())); }
	 */

}
