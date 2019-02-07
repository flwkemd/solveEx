package com.solve.spring.utils;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class Utils {

	/**
	* (비인증) SSL 인증서를 통과 시켜주는 소스 {@link <a href=
	* "http://beyondj2ee.tumblr.com/post/14507308404/httpcomponents-httpclient-4x%EC%97%90%EC%84%9C-https-%EC%84%9C%EB%B2%84-%EC%97%B0%EB%8F%99%ED%95%98%EA%B8%B0">http://beyondj2ee.tumblr.com/post/14507308404/httpcomponents-httpclient-4x%EC%97%90%EC%84%9C-https-%EC%84%9C%EB%B2%84-%EC%97%B0%EB%8F%99%ED%95%98%EA%B8%B0</a>
	* }
	* 
	* @param httpclient
	* @throws Exception
	*/
	private static void setTestHttpClient(HttpClient httpclient) throws Exception {
		TrustManager easyTrustManager = new X509TrustManager() {
			@Override
			public X509Certificate[] getAcceptedIssuers() {
				return null;
			}

			@Override
			public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			}

			@Override
			public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			}
		};

		SSLContext sslcontext = SSLContext.getInstance("TLS");
		sslcontext.init(null, new TrustManager[] { easyTrustManager }, null);
		//
		SSLSocketFactory socketFactory = new SSLSocketFactory(sslcontext,
				org.apache.http.conn.ssl.SSLSocketFactory.STRICT_HOSTNAME_VERIFIER);

		// 본인 인증 방식일 경우 (Self-Signed Certificate)
		// SSLSocketFactory socketFactory = new SSLSocketFactory(sslcontext,
		// SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

		Scheme sch = new Scheme("https", 443, socketFactory);
		httpclient.getConnectionManager().getSchemeRegistry().register(sch);
	}

	
	/**
	* url로 접근 해서 source를 받아오는 메소드 (POST방식) created by kimjy 타 웹사이트의 API를 Ajax에서
	* 불러오기 위한 방법으로 만들었음.
	* 
	* @throws Exception
	*/
	public static String getHttpPOST2String(String url, Map<String, String> headers, Map<String, String> params,
			boolean isTest) throws Exception {
		//
		HttpClient httpclient = new DefaultHttpClient();
		String responseBody = null;
		try {
			HttpPost post = new HttpPost(url);
			if (params != null && !params.isEmpty()) {

				List<BasicNameValuePair> parameters = new ArrayList<BasicNameValuePair>();
				for (String key : params.keySet()) {
					parameters.add(new BasicNameValuePair(key, params.get(key)));
				}

				UrlEncodedFormEntity reqEntity = new UrlEncodedFormEntity(parameters, "UTF-8");
				post.setEntity(reqEntity);
			}

			if (headers != null && !headers.isEmpty()) {

				for (String key : headers.keySet()) {

					post.addHeader(key, headers.get(key));
				}
			}

			if (isTest) {
				setTestHttpClient(httpclient);
			}
			HttpResponse response = httpclient.execute(post);
			responseBody = EntityUtils.toString(response.getEntity(), "UTF-8");
		} finally {
			httpclient.getConnectionManager().shutdown();
		}

		return responseBody;
	}

}
