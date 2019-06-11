package com.example.maxwang.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    String titles[];
    String details[];
    int imagesIds[];
    Context ctx;

    RecyclerViewAdapter(Context ctx, String titles[], String details[], int imagesIds[]) {
        this.titles = titles;
        this.details = details;
        this.imagesIds = imagesIds;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater li = LayoutInflater.from(ctx);
        View view = li.inflate(R.layout.recycler_view_holder, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.image.setImageResource(imagesIds[i]);
        myViewHolder.title.setText(titles[i]);
        myViewHolder.detail.setText(details[i]);
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
            TextView title;
            TextView detail;
            ImageView image;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            detail = itemView.findViewById(R.id.detail);
            image = itemView.findViewById(R.id.image_id);
        }
    }
}
