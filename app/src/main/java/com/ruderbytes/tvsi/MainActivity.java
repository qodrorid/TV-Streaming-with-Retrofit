package com.ruderbytes.tvsi;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by muhammad on 10/07/17.
 */

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Channel> data;
    private DataAdapter adapter;
    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews(){
        progress = new ProgressDialog(this);

        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        loadJSON();
    }
    private void loadJSON(){
        progress.setMessage("Mohon Tunggu...");
        progress.show();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.bbenkpartnersolo.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RequstInterface request = retrofit.create(RequstInterface.class);
        Call<JSONResponse> call = request.getJSON();
        call.enqueue(new Callback<JSONResponse>() {
            @Override
            public void onResponse(Call<JSONResponse> call, Response<JSONResponse> response) {
                progress.dismiss();
                JSONResponse jsonResponse = response.body();
                Toast.makeText(MainActivity.this, ""+jsonResponse.getStatus(), Toast.LENGTH_SHORT).show();
                data = new ArrayList<>(Arrays.asList(jsonResponse.getItems()));
                adapter = new DataAdapter(data, getApplicationContext());
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<JSONResponse> call, Throwable t) {
                Log.d("Error",t.getMessage());
                Toast.makeText(MainActivity.this, "Gagal "+t.getMessage(), Toast.LENGTH_SHORT).show();
                progress.dismiss();
            }
        });
    }
}
