package com.project.thesis.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.thesis.R;
import com.project.thesis.model.ProductData;

import java.io.InputStream;
import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.MovieViewHolder> {

    List<ProductData> productDataList;
    Context context;

    public RVAdapter(Context context, List<ProductData> productDataList) {
        this.productDataList = productDataList;
        this.context = context;
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView txtProductTitle;
        TextView txtProductPrice;
        ImageView ivProduct;

        MovieViewHolder(final View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            txtProductTitle = (TextView) itemView.findViewById(R.id.txtProductTitle);
            txtProductPrice = (TextView) itemView.findViewById(R.id.txtPrice);
            ivProduct = (ImageView) itemView.findViewById(R.id.ivProduct);
        }
    }

    @Override
    public int getItemCount() {
        return productDataList.size();
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.product_item, viewGroup, false);
        MovieViewHolder mvh = new MovieViewHolder(v);
        return mvh;
    }

    @Override
    public void onBindViewHolder(MovieViewHolder movieViewHolder, final int i) {
        movieViewHolder.txtProductTitle.setText(productDataList.get(i).getTitle());
        movieViewHolder.txtProductPrice.setText(productDataList.get(i).getPrice());

        movieViewHolder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(productDataList.get(i).getLink()));
                browserIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(browserIntent);
            }
        });

        new DownloadImageTask(movieViewHolder. ivProduct).execute(productDataList.get(i).getImageLink());

    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}