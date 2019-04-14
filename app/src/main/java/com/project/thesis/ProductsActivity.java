package com.project.thesis;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.project.thesis.adapter.RVAdapter;
import com.project.thesis.http.APIHelper;
import com.project.thesis.http.CloudSightService;
import com.project.thesis.http.HttpClient;
import com.project.thesis.model.ProductData;
import com.project.thesis.util.CommonUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProductsActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        RecyclerView rv = findViewById(R.id.rv);
        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);

        Intent intent = getIntent();
        String productTitle = intent.getStringExtra("productTitle");

        initializeData(productTitle, rv, getApplicationContext());
    }

    private void initializeData(String title, final RecyclerView rv, final Context context){
        Retrofit retrofit = HttpClient.createClient();
        progressDialog = CommonUtil.progressDialog(ProductsActivity.this);
        if (progressDialog != null && !progressDialog.isShowing()) {
            progressDialog.show();
        }

        APIHelper.enqueueWithRetry(retrofit.create(CloudSightService.class).findProducts(title), new Callback<List<ProductData>>() {

            @Override
            public void onResponse(Call<List<ProductData>> call, Response<List<ProductData>> response) {
                RVAdapter adapter = new RVAdapter(context, response.body());
                rv.setAdapter(adapter);
                if (progressDialog != null && progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<List<ProductData>> call, Throwable t) {
                if (progressDialog != null && progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                Toast.makeText(getApplicationContext(),
                        "Products not found",
                        Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    protected void onDestroy() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
        super.onDestroy();
    }
}
