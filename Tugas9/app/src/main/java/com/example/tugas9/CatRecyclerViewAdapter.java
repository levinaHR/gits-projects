package com.example.tugas9;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CatRecyclerViewAdapter extends RecyclerView.Adapter<CatRecyclerViewAdapter.CatViewHolder> {
    List<Cat> catList = new ArrayList<>();

    public CatRecyclerViewAdapter(List<Cat> catList) {
        this.catList = catList;
    }

    @NonNull
    @Override
    public CatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_cat_item, parent, false);

        return new CatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CatViewHolder holder, int position) {
        Cat c = catList.get(position);
        holder.title.setText(c.getTitle());
        holder.desc.setText(c.getDesc());
        holder.pic.setImageResource(c.getPic());
    }

    @Override
    public int getItemCount() {
        return catList.size();
    }

    public static class CatViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView desc;
        public ImageView pic;

        public CatViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.cat_title);
            desc = itemView.findViewById(R.id.cat_desc);
            pic = itemView.findViewById(R.id.cat_pic);
        }
    }
}
