package com.example.eyepetizer.util;

import com.example.eyepetizer.interfaces.CallBack;
import com.example.eyepetizer.bean.HttpBean;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class HttpUtil {

    public static void GetHttpString(HttpBean httpBean, CallBack callBcak) {

        Map<String,String> map = httpBean.getMap();

        String methon = httpBean.getMethod();

        String url = httpBean.getUrl();

        HttpURLConnection httpURLConnection = null;

        String response;

        BufferedReader bufferedReader = null;

        try

        {

            URL url1 = new URL(url);

            httpURLConnection = (HttpURLConnection) url1.openConnection();

            httpURLConnection.setRequestMethod(methon);

            httpURLConnection.setConnectTimeout(8000);

            httpURLConnection.setReadTimeout(8000);

            httpURLConnection.connect();



            if(methon.equals("POST")) {

                StringBuffer sbRequest = new StringBuffer();

                if (map != null && map.size() > 0) {

                    for (String key : map.keySet()) {

                        sbRequest.append(key + "=" + map.get(key) + "&");

                    }

                    String params = sbRequest.substring(0, sbRequest.length() - 1);

                    OutputStream os = httpURLConnection.getOutputStream();

                    os.write(params.getBytes());

                    os.flush();

                }

            }

            else {



                if (httpURLConnection.getResponseCode() == 200) {

                    InputStream inputStream = httpURLConnection.getInputStream();

                    bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                    StringBuffer stringBuffer = new StringBuffer();

                    String response1;

                    if ((response1 = bufferedReader.readLine()) != null) {

                        stringBuffer.append(response1);

                    }

                    response = stringBuffer.toString();

                    callBcak.finish(response);

                }

            }

        }catch (Exception e)

        {

            e.printStackTrace();

        }finally {

            if(bufferedReader != null)

                try {

                    bufferedReader.close();

                }catch (Exception e)

                {

                    e.printStackTrace();

                }



            if(httpURLConnection != null)

            {

                httpURLConnection.disconnect();

            }

        }

    }

}