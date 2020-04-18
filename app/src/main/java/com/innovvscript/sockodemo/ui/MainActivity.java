package com.innovvscript.sockodemo.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.innovvscript.sockodemo.LabsAdapter;
import com.innovvscript.sockodemo.R;
import com.innovvscript.sockodemo.model.Lab;
import com.innovvscript.sockodemo.model.Request;
import com.innovvscript.sockodemo.presenter.MyPresenter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MyPresenter.MyView {

    private MyPresenter presenter;
    private List<Lab> labs;
    private RecyclerView rv;
    private ProgressBar loader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loader = findViewById(R.id.progress);
        loader.setVisibility(View.VISIBLE);
        presenter = new MyPresenter(this);
        presenter.getData();
        rv = findViewById(R.id.recycler);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setHasFixedSize(true);
    }

    private void setUpRecycler() {
        loader.setVisibility(View.GONE);
        TextView count = findViewById(R.id.count);
        count.setText(String.valueOf(labs.size()));
        LabsAdapter adapter = new LabsAdapter(this, labs);
        rv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onGetData() {
        try {
            JSONObject data = (JSONObject) Request.getResponseJson().get("data");
            String response = (String) data.get("response");
            JSONArray jsonArray = new JSONArray(response);
            labs = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = (JSONObject) jsonArray.get(i);
                Lab lab = new Lab();
                lab.setUrl(obj.getString("logo_url"));
                lab.setPrice(obj.getString("price"));
                lab.setPayable(obj.getString("payable"));
                lab.setAddress(obj.getString("address"));
                lab.setRating(obj.getString("review"));
                labs.add(lab);
            }
            setUpRecycler();

        } catch (JSONException e) {
            e.printStackTrace();
            Log.w("Main", e.getMessage());
        }
    }
}
