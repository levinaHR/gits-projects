package com.levinahr.asus.catdoption.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.levinahr.asus.catdoption.BuildConfig;
import com.levinahr.asus.catdoption.R;
import com.levinahr.asus.catdoption.activity.ui.cat.DetailCatActivity;
import com.levinahr.asus.catdoption.model.CatModel;

import java.util.List;

public class CatAdapter extends RecyclerView.Adapter<CatAdapter.CatViewHolder> {
    private Context context;
    private List<CatModel> catList;

    public CatAdapter(Context context, List<CatModel> catList) {
        this.context = context;
        this.catList = catList;
    }

    @NonNull
    @Override
    public CatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cat, parent, false);
        return new CatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CatViewHolder holder, int position) {
        CatModel cat = catList.get(position);

        holder.name.setText(cat.getName());
        holder.age.setText(cat.getAge());
        holder.breed.setText(cat.getBreed());

        if (cat.getGender() == 0) {
            holder.gender.setText("Male");

        } else {
            holder.gender.setText("Female");
        }

        if (cat.getStatus() == 0) {
            holder.status.setText("Adopted");

        } else {
            holder.status.setText("Looking for new home");
        }

        holder.cardView.setOnClickListener(view -> {
            Intent intent = new Intent(context, DetailCatActivity.class);
            String id = String.valueOf(catList.get(position).getId());

            intent.putExtra("id", id);
            intent.putExtra("name", catList.get(position).getName());
            intent.putExtra("age", catList.get(position).getAge());
            intent.putExtra("breed", catList.get(position).getBreed());
            intent.putExtra("description", catList.get(position).getDescription());

            if (catList.get(position).getGender() == 0) {
                intent.putExtra("gender", "Male");

            } else {
                intent.putExtra("gender", "Female");
            }

            if (catList.get(position).getStatus() == 0) {
                intent.putExtra("status", "Adopted");

            } else {
                intent.putExtra("status", "Looking for new home");
            }

            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return catList.size();
    }

    public class CatViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView age;
        private TextView gender;
        private TextView breed;
        private TextView status;
        private CardView cardView;

        public CatViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name_cat);
            age = itemView.findViewById(R.id.age_cat);
            gender = itemView.findViewById(R.id.gender_cat);
            breed = itemView.findViewById(R.id.breed_cat);
            status = itemView.findViewById(R.id.status_cat);
            cardView = itemView.findViewById(R.id.cv_cat);
        }
    }
}
