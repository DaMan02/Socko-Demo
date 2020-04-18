package com.innovvscript.sockodemo;

import android.os.AsyncTask;
import android.util.Log;

import com.innovvscript.sockodemo.model.Request;
import com.innovvscript.sockodemo.presenter.MyPresenter;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

public class HttpsUtil {
        private MyPresenter presenter;
        public HttpsUtil(MyPresenter presenter) {
            this.presenter = presenter;
        }

    private static final String BASE_URL = "https://sockoapi.azurewebsites.net/";

    public HttpsURLConnection getHttpsConnection() throws IOException {
        URL url = new URL(BASE_URL + "api/get/getlabs");
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Connection", "keep-alive");
        connection.setDoInput(true);
        return connection;
    }

     public void connectAsync(){
            new RequestAsyncTask().execute();
        }

private class RequestAsyncTask extends AsyncTask<String,Void,String> {
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s)  {
        super.onPostExecute(s);
        Log.w("onPostExecute()","responseCode:" + Request.getResponseCode());
        Log.w("onPostExecute()","response:" + Request.getResponseJson());

        try {
            presenter.executionCompleted();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected String doInBackground(String... strings) {
        try {
            JSONObject obj = new JSONObject();
            obj.put("pincode","110045");
            obj.put("testId", "2290");

            HttpsURLConnection connection = Request.getHttpsURLConnection();
            OutputStreamWriter wr= new OutputStreamWriter(connection.getOutputStream());
            wr.write(obj.toString());
            OutputStream os = connection.getOutputStream();
            os.write(obj.toString().getBytes("UTF-8"));
            os.close();

            connection.connect();
                presenter.gotResponseJson(getBody());
            Request.setResponseCode(Request.getHttpsURLConnection().getResponseCode());
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return "Executed";
    }
    }

    private JSONObject getBody() throws IOException, JSONException {
        BufferedReader br = new BufferedReader(new InputStreamReader(Request.getHttpsURLConnection().getInputStream()));
        String json = br.readLine();
        return new JSONObject(json);
    }
}

