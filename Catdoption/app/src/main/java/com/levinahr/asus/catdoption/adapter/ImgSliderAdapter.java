package com.levinahr.asus.catdoption.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.levinahr.asus.catdoption.R;
import com.levinahr.asus.catdoption.model.SliderModel;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class ImgSliderAdapter extends SliderViewAdapter<ImgSliderAdapter.ImgSliderViewHolder> {
    private Context context;
    private List<SliderModel> mSliderModels = new ArrayList<>();

    public ImgSliderAdapter(Context context, List<SliderModel> mSliderModels) {
        this.context = context;
        this.mSliderModels = mSliderModels;
    }

    public void renewItems(List<SliderModel> sliderModels) {
        this.mSliderModels = sliderModels;
        notifyDataSetChanged();
    }

    public void deleteItem(int position) {
        this.mSliderModels.remove(position);
        notifyDataSetChanged();
    }

    public void addItem(SliderModel sliderItem) {
        this.mSliderModels.add(sliderItem);
        notifyDataSetChanged();
    }

    @Override
    public ImgSliderViewHolder onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image_slider, null);
        return new ImgSliderViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(ImgSliderViewHolder viewHolder, final int position) {

        SliderModel sliderItem = mSliderModels.get(position);

        viewHolder.textViewDescription.setText(sliderItem.getDescription());
        viewHolder.textViewDescription.setTextSize(16);
        viewHolder.textViewDescription.setTextColor(Color.WHITE);
        Glide.with(viewHolder.itemView)
                .load(sliderItem.getImageUrl())
                .fitCenter()
                .into(viewHolder.imageViewBackground);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "This is item in position " + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getCount() {
        //slider view count could be dynamic size
        return mSliderModels.size();
    }

    class ImgSliderViewHolder extends SliderViewAdapter.ViewHolder {
        View itemView;
        ImageView imageViewBackground;
//        ImageView imageGifContainer;
        TextView textViewDescription;

        public ImgSliderViewHolder(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.iv_auto_image_slider);
//            imageGifContainer = itemView.findViewById(R.id.iv_gif_container);
            textViewDescription = itemView.findViewById(R.id.tv_auto_image_slider);
            this.itemView = itemView;
        }
    }
}
