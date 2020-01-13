package infinitives.whatsgroups;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;
import android.widget.Toolbar;

import com.android.volley.Cache;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rey.material.widget.ProgressView;
import com.shashank.sony.fancydialoglib.FancyAlertDialog;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


public class JsonFetch extends AppCompatActivity {

    // private List<Sheet1> s = new ArrayList<>();
    private RecyclerView recyclerView;
    private ProgressView progressView;
    private ActionBar toolbar;
    private FancyAlertDialog.Builder fancyAlertDialog;
    private Boolean show = false;
    private InterstitialAd interstitialAd1;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_fetch);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.bgBottomNavigation));
        }

        mAdView = findViewById(R.id.adView);
        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.SMART_BANNER);
        adView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        ShowAds(interstitialAd1);
        recyclerView = findViewById(R.id.recycleview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        progressView = findViewById(R.id.progv);
        progressView.start();
        fancyAlertDialog = new FancyAlertDialog.Builder(this);
        // recyclerView.addItemDecoration(new divider(this,Color.BLACK,10));

        Intent intent = getIntent();
        String link = intent.getStringExtra("0");
        String title = intent.getStringExtra("1");
        toolbar = getSupportActionBar();
        toolbar.setTitle(title);
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
                    final Customlist customlist = new Customlist(sheet1List, JsonFetch.this, fancyAlertDialog);
                    recyclerView.setAdapter(customlist);
                    customlist.notifyDataSetChanged();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressView.stop();

            }
        }) {
            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                try {
                    Cache.Entry cacheEntry = HttpHeaderParser.parseCacheHeaders(response);
                    if (cacheEntry == null) {
                        cacheEntry = new Cache.Entry();
                    }
                    final long cacheHitButRefreshed = 2 * 60 * 1000; // in 2 minutes cache will be hit, but also refreshed on background
                    final long cacheExpired = 24 * 60 * 60 * 1000; // in 24 hours this cache entry expires completely
                    long now = System.currentTimeMillis();
                    final long softExpire = now + cacheHitButRefreshed;
                    final long ttl = now + cacheExpired;
                    cacheEntry.data = response.data;
                    cacheEntry.softTtl = softExpire;
                    cacheEntry.ttl = ttl;
                    String headerValue;
                    headerValue = response.headers.get("Date");
                    if (headerValue != null) {
                        cacheEntry.serverDate = HttpHeaderParser.parseDateAsEpoch(headerValue);
                    }
                    headerValue = response.headers.get("Last-Modified");
                    if (headerValue != null) {
                        cacheEntry.lastModified = HttpHeaderParser.parseDateAsEpoch(headerValue);
                    }
                    cacheEntry.responseHeaders = response.headers;
                    final String jsonString = new String(response.data,
                            HttpHeaderParser.parseCharset(response.headers));
                    return Response.success(new String(jsonString), cacheEntry);
                } catch (UnsupportedEncodingException e) {
                    return Response.error(new ParseError(e));
                }
            }

            @Override
            public void deliverError(VolleyError error) {
                super.deliverError(error);
            }

            @Override
            protected VolleyError parseNetworkError(VolleyError volleyError) {
                return super.parseNetworkError(volleyError);
            }
        };


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    public void ShowAds(InterstitialAd interstitial1) {


        interstitial1 = new InterstitialAd(getApplicationContext());
        interstitial1.setAdUnitId("ca-app-pub-3940256099942544/1033173712");

        interstitial1.loadAd(new AdRequest.Builder().addTestDevice("754DB6521943676637AE86202C5ACE52").build());

        final InterstitialAd finalInterstitial = interstitial1;
        interstitial1.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                if (finalInterstitial.isLoaded()) {
                    finalInterstitial.show();
                }
            }

            @Override
            public void onAdLeftApplication() {

                show = true;
            }

            @Override
            public void onAdClosed() {

                if (!show) {
                    if (finalInterstitial.isLoaded()) {
                        finalInterstitial.show();
                    }
                }
            }

        });


    }
}
