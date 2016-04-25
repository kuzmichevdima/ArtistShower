package com.example.dmitry.artistshower.model;

import java.util.List;

/**
 * Created by dmitry on 25.04.16.
 */
public class Artist {
    private int mId;
    private String mName;
    private List<String> mGenres;
    private int mTracks;
    private int mAlbums;
    private String mLink;
    private String mDescription;
    private String mSmallCover;
    private String mBigCover;

    public Artist(int mId, String mName, List<String> mGenres, int mTracks, int mAlbums, String mLink, String mDescription, String mSmallCover, String mBigCover) {
        this.mId = mId;
        this.mName = mName;
        this.mGenres = mGenres;
        this.mTracks = mTracks;
        this.mAlbums = mAlbums;
        this.mLink = mLink;
        this.mDescription = mDescription;
        this.mSmallCover = mSmallCover;
        this.mBigCover = mBigCover;
    }

    @Override
    public String toString() {
        return "(id) = " + mId + "(name) = " + mName + " (genres)" + mGenres + "(tracks) = " + mTracks + " (link) = " + mLink;
    }

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public List<String> getGenres() {
        return mGenres;
    }

    //Нужно научиться получать правильную форму слова
    private static int wordFormByNumber(int number) {
        if (number % 10 == 1 && number % 100 != 11) {
            return 1;
        } else if (number % 10 >= 2 && number % 10 <= 4 && (number % 100 < 10 || number % 100 >= 20)) {
            return 2;
        } else {
            return 3;
        }
    }

    public String getTracks() {
        int wordForm = wordFormByNumber(mTracks);
        String additional = (wordForm == 1 ? "песня" : wordForm == 2 ? "песни" : "песен");
        return mTracks + " " + additional;
    }

    public String getAlbums() {
        int wordForm = wordFormByNumber(mAlbums);
        String additional = (wordForm == 1 ? "альбом" : wordForm == 2 ? "альбома" : "альбомов");
        return mAlbums + " " + additional;
    }

    public String getmLink() {
        return mLink;
    }

    public String getmDescription() {
        return mDescription;
    }

    public String getSmallCover() {
        return mSmallCover;
    }

    public String getmBigCover() {
        return mBigCover;
    }
}
