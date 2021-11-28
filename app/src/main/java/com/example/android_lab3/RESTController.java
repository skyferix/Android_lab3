package com.example.android_lab3;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

public class RESTController {

    private static BufferedWriter bufferedWriter;
    private static OutputStream outputStream;

    public static String sendGet(String urlGet) throws IOException {
        URL url = new URL(urlGet);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        int code = httpURLConnection.getResponseCode();
        System.out.println("Response code: " + code);

        if (code == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String line;
            StringBuffer response = new StringBuffer();

            while ((line = in.readLine()) != null) {
                response.append(line);
                break;
            }
            in.close();
            return response.toString();

        } else {
            return "";
        }
    }

    public static String sendPost(String urlPost, String postDataParams) throws IOException {
        URL url = new URL(urlPost);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json; utf-8");
        conn.setRequestProperty("Accept", "application/json");
        conn.setDoOutput(true);
        outputStream = conn.getOutputStream();
        bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
        bufferedWriter.write(postDataParams);
        //? bufferedWriter.flush();
        bufferedWriter.close();
        outputStream.close();

        int code = conn.getResponseCode();

        System.out.println("Response code: " + code);

        if (code == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            StringBuffer response = new StringBuffer();

            while ((line = in.readLine()) != null) {
                response.append(line);
                break;
            }
            in.close();
            return response.toString();

        } else {
            return "";
        }
    }

    public static String sendPut(String url, String postDataParams) {
        return "";
    }

    public static String sendDelete(String urlDelete) throws IOException {
        URL url = new URL(urlDelete);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("DELETE");
        httpURLConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        int code = httpURLConnection.getResponseCode();
        System.out.println("Response code: " + code);

        if (code == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String line;
            StringBuffer response = new StringBuffer();

            while ((line = in.readLine()) != null) {
                response.append(line);
                break;
            }
            in.close();
            return response.toString();

        } else {
            return "";
        }
    }

    private static void setConnectionParameters(HttpURLConnection httpURLConnection, String type) throws ProtocolException {
        httpURLConnection.setRequestMethod(type);
        httpURLConnection.setReadTimeout(20000);
        httpURLConnection.setConnectTimeout(20000);
        httpURLConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        httpURLConnection.setRequestProperty("Accept", "application/json");
        httpURLConnection.setDoInput(true);
        httpURLConnection.setDoOutput(true);
    }


}
