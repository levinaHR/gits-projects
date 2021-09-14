package com.example.tugas9;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class ImageGalleryAdapter extends RecyclerView.Adapter<ImageGalleryAdapter.MenuViewHolder>  {
    private MenuPhoto[] mMenuPhotos;
    private Context mContext;

    public ImageGalleryAdapter(Context context, MenuPhoto[] menuPhotos) {
        mContext = context;
        mMenuPhotos = menuPhotos;
    }

    @Override
    public MenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View photoView = inflater.inflate(R.layout.fragment_menu_item, parent, false);
        MenuViewHolder viewHolder = new MenuViewHolder(photoView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MenuViewHolder holder, int position) {
        MenuPhoto menuPhoto = mMenuPhotos[position];
        ImageView imageView = holder.mPhotoImageView;

        Glide.with(mContext)
                .load(menuPhoto.getUrl())
                .placeholder(R.drawable.logo)
                .into(imageView);
    }

    @Override
    public int getItemCount() {
        return (mMenuPhotos.length);
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView mPhotoImageView;

        public MenuViewHolder(View itemView) {

            super(itemView);
            mPhotoImageView = (ImageView) itemView.findViewById(R.id.iv_photo);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            int position = getAdapterPosition();
            if(position != RecyclerView.NO_POSITION) {
                MenuPhoto spacePhoto = mMenuPhotos[position];
                Intent intent = new Intent(mContext, MenuPhotoActivity.class);
                intent.putExtra(MenuPhotoActivity.EXTRA_MENU_PHOTO, spacePhoto);
                mContext.startActivity(intent);
            }
        }
    }
}
