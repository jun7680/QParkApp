package com.example.qpark_app.Login;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class LoginTask extends AsyncTask<String, Void, JSONObject> {

    Context context;
    public LoginTask(Context context){
        this.context = context;

    }

    @Override
    protected JSONObject doInBackground(String... params) {
        JSONObject result = null;

        try {
            String url = params[0];

            String Body = "ID=" + params[1]+
                    "&PW=" + params[2];

            Log.d("doInBackground", Body);

            URL URLObj = new URL(url);

            HttpURLConnection Conn = (HttpURLConnection) URLObj.openConnection();

            Conn.setReadTimeout(100000);
            Conn.setConnectTimeout(15000);

            Conn.setRequestMethod("POST");
            Conn.setRequestProperty("Accept-Charset", "UTF-8");
            Conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            Conn.setDoInput(true);
            Conn.setDoOutput(true);


            /**
             * InputStream = Reader
             * OutputStream = Writer
             * BufferedReader / BufferedWriter는 문자 입력 스트림으로부터 문자를 읽어 들이거나 문자 출력 스트림으로 문자를 내보낼 때 버퍼링을 함으로써
             * 문자, 문자 배열, 문자열 라인 등을 보다 효율적으로 처리할 수 있도록 해준다
             * InputStreamReader / OutputStreamWriter를 사용하는 경우는 한 문자씩 읽지만 버퍼링을 하게 되면 입출력 스트림으로부터 미리 버퍼에 데이터를 갖다 놓기 때문에 보다 효율적으로 입출력할 수 있다.
             **/

            OutputStream OutStream = Conn.getOutputStream();
            OutStream.write(Body.getBytes("utf-8"));

            InputStreamReader InputStream = new InputStreamReader(Conn.getInputStream(), "UTF-8");

            BufferedReader Reader = new BufferedReader(InputStream);
            StringBuilder Builder = new StringBuilder();
            String ResultStr;

            while ((ResultStr = Reader.readLine()) != null) {
                Builder.append(ResultStr + "\n");
            }

            result = new JSONObject(Builder.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
            result = null;
        } catch (Exception e) {
            e.printStackTrace();
            result = null;
        } finally {
            //Todo finally..
        }

        return result;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        super.onPostExecute(jsonObject);
    }
}