package com.example.splashactivity.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Music implements Parcelable {
    private String name;
    private String singer;
    private String img_url;
    private String sound_url;

    public Music(String name, String singer, String img_url, String sound_url) {
        this.name = name;
        this.singer = singer;
        this.img_url = img_url;
        this.sound_url = sound_url;
    }

    protected Music(Parcel in) {
        name = in.readString();
        singer = in.readString();
        img_url = in.readString();
        sound_url = in.readString();
    }

    public static final Creator<Music> CREATOR = new Creator<Music>() {
        @Override
        public Music createFromParcel(Parcel in) {
            return new Music(in);
        }

        @Override
        public Music[] newArray(int size) {
            return new Music[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getSound_url() {
        return sound_url;
    }

    public void setSound_url(String sound_url) {
        this.sound_url = sound_url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(singer);
        dest.writeString(img_url);
        dest.writeString(sound_url);
    }
}
