package com.example.tugas9;

import android.os.Parcel;
import android.os.Parcelable;

public class MenuPhoto implements Parcelable {

    private String mUrl;
    private String mTitle;

    public MenuPhoto(String url, String title) {
        mUrl = url;
        mTitle = title;
    }

    protected MenuPhoto(Parcel in) {
        mUrl = in.readString();
        mTitle = in.readString();
    }

    public static final Creator<MenuPhoto> CREATOR = new Creator<MenuPhoto>() {
        @Override
        public MenuPhoto createFromParcel(Parcel in) {
            return new MenuPhoto(in);
        }

        @Override
        public MenuPhoto[] newArray(int size) {
            return new MenuPhoto[size];
        }
    };

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public static  MenuPhoto[] getMenuPhotos() {

        return new MenuPhoto[]{
                new MenuPhoto("https://www.acouplecooks.com/wp-content/uploads/2021/08/How-to-make-espresso-009.jpg", "Espresso"),
                new MenuPhoto("https://www.acouplecooks.com/wp-content/uploads/2020/10/How-to-make-an-Americano-004.jpg", "Americano"),
                new MenuPhoto("https://www.acouplecooks.com/wp-content/uploads/2020/10/how-to-make-cappuccino-005.jpg", "Cappucinno"),
                new MenuPhoto("https://www.acouplecooks.com/wp-content/uploads/2021/05/Latte-Art-067.jpg", "Latte"),
                new MenuPhoto("https://www.acouplecooks.com/wp-content/uploads/2020/10/How-to-Make-a-Mocha-002.jpg", "Mocha"),
                new MenuPhoto("https://www.acouplecooks.com/wp-content/uploads/2021/08/French-Press-Cold-Brew-011.jpg", "Cold Brew"),
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mUrl);
        parcel.writeString(mTitle);
    }
}