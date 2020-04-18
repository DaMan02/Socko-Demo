package com.innovvscript.sockodemo.model;

import org.json.JSONObject;
import javax.net.ssl.HttpsURLConnection;

public class Request {

    private static int this_responseCode;
    private static HttpsURLConnection httpsURLConnection;
    private static JSONObject responseJson;

    public static JSONObject getResponseJson() {
        return responseJson;
    }

    public static void setResponseJson(JSONObject responseJson) {
        Request.responseJson = responseJson;
    }

    public static HttpsURLConnection getHttpsURLConnection() {
        return httpsURLConnection;
    }

    public static void setHttpsURLConnection(HttpsURLConnection httpsURLConnection) {
        Request.httpsURLConnection = httpsURLConnection;
    }

    public static int getResponseCode() {
        return this_responseCode;
    }

    public static void setResponseCode(int responseCode) {
        this_responseCode = responseCode ;
    }

}
