package infinitives.whatsappgroups;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rey.material.widget.ProgressView;
import com.yarolegovich.lovelydialog.LovelyProgressDialog;

import java.util.ArrayList;
import java.util.List;


public class JsonFetch extends AppCompatActivity {

    // private List<Sheet1> s = new ArrayList<>();
    private RecyclerView recyclerView;
    ProgressView progressView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_fetch);
        recyclerView = findViewById(R.id.recycleview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        progressView = findViewById(R.id.progv);
        progressView.start();
        // recyclerView.addItemDecoration(new divider(this,Color.BLACK,10));

        Intent intent = getIntent();
        String link = intent.getStringExtra("0");
        JsonFetch(link);


    }


    public void JsonFetch(String link) {
        StringRequest stringRequest = new StringRequest(link, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                //GsonBuilder gsonBuilder =  new GsonBuilder();
                progressView.stop();
                recyclerView.setVisibility(View.VISIBLE);


                final Gson gson = new Gson();
                Jsonfetching jsonfetching = gson.fromJson(response, Jsonfetching.class);

                List<Sheet1> sheet1List = jsonfetching.getSheet1();
                if (sheet1List != null) {
                    final Customlist customlist = new Customlist(sheet1List, JsonFetch.this);
                    customlist.notifyDataSetChanged();
                    recyclerView.setAdapter(customlist);

                    final Handler handler = new Handler();
                    final Runnable runnable = new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(JsonFetch.this, "Working", Toast.LENGTH_SHORT).show();
                            customlist.notifyDataSetChanged();
                            handler.postDelayed(
                                    this,120000
                            );

                        }
                    };
                    handler.postDelayed(runnable,0);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressView.stop();

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
