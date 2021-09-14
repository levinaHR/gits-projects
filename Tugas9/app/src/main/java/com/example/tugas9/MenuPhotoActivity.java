package com.example.tugas9;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.palette.graphics.Palette;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

public class MenuPhotoActivity extends AppCompatActivity {

    public static final String EXTRA_MENU_PHOTO = "MenuPhotoActivity.MENU_PHOTO";
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_detail);

        mImageView = (ImageView) findViewById(R.id.menuImage);
        MenuPhoto menuPhoto = getIntent().getParcelableExtra(EXTRA_MENU_PHOTO);

        Glide.with(this)
                .asBitmap()
                .load(menuPhoto.getUrl())
                .error(R.drawable.logo)
                .listener(new RequestListener<Bitmap>() {

                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                        onPalette(Palette.from(resource).generate());
                        mImageView.setImageBitmap(resource);

                        return false;
                    }

                    public void onPalette(Palette palette) {
                      if (null != palette) {
                          ViewGroup parent = (ViewGroup) mImageView.getParent().getParent();
                          parent.setBackgroundColor(palette.getDarkVibrantColor(Color.GRAY));
                      }
                    }
                })
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(mImageView);
    }
}
