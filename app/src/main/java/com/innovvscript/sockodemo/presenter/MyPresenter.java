package com.innovvscript.sockodemo.presenter;

import com.innovvscript.sockodemo.HttpsUtil;
import com.innovvscript.sockodemo.model.Request;
import org.json.JSONObject;
import java.io.IOException;
import javax.net.ssl.HttpsURLConnection;

public class MyPresenter {

    private Request request;
    private MyView view;
    private HttpsUtil httpsUtil;

    public MyPresenter(MyView view) {
        this.request = new Request();
        this.view = view;
        this.httpsUtil = new HttpsUtil(this);
    }

    public void executionCompleted() throws NullPointerException {
            view.onGetData();
        }

    public void gotResponseJson(JSONObject json){
        Request.setResponseJson(json);
    }

    public void getData() {
        try {
            HttpsURLConnection connection = httpsUtil.getHttpsConnection();
            httpsUtil.connectAsync();
            request.setHttpsURLConnection(connection);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public interface MyView {
        void onGetData();
    }
}
