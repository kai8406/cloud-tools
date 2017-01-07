package com.chinamcloud.devops.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpClientUtils {

	/**
	 * 发送Post请求.
	 * 
	 * @param url
	 *            地址
	 * @param params
	 *            post请求参数
	 */
	public static String post(String url, Map<String, String> params) {

		String content = "";

		// 创建默认的httpClient实例.
		CloseableHttpClient httpclient = HttpClients.createDefault();

		// 创建httppost
		HttpPost httppost = new HttpPost(url);

		httppost.addHeader("accessKey", params.get("accessKey"));

		try {

			String json = new ObjectMapper().writeValueAsString(params);

			StringEntity sentity = new StringEntity(json, "UTF-8");
			sentity.setContentType("application/json");
			sentity.setContentEncoding("utf-8");

			httppost.setEntity(sentity);

			CloseableHttpResponse response = httpclient.execute(httppost);

			try {

				HttpEntity entity = response.getEntity();
				if (entity != null) {
					content = EntityUtils.toString(entity, "UTF-8");
					System.out.println(content);
				}
			} finally {
				response.close();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 关闭连接,释放资源
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return content;
	}

	/**
	 * 发送Get请求
	 * 
	 * @param url
	 */
	public static String get(String url) {

		String content = "";

		CloseableHttpClient httpclient = HttpClients.createDefault();

		try {

			// 创建httpget.
			HttpGet httpget = new HttpGet(url);

			// 执行get请求.
			CloseableHttpResponse response = httpclient.execute(httpget);

			try {
				// 获取响应实体
				HttpEntity entity = response.getEntity();

				if (entity != null) {
					content = EntityUtils.toString(entity);
				}

			} finally {
				response.close();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 关闭连接,释放资源
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return content;
	}
}
