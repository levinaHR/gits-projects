package com.example.tugas9;

public class Cat {
    String title;
    String desc;
    int pic;

    public Cat(String title, String desc, int pic) {
        this.title = title;
        this.desc = desc;
        this.pic = pic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }
}
