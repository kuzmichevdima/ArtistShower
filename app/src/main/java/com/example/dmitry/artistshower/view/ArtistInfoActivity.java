package com.example.dmitry.artistshower.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dmitry.artistshower.App;
import com.example.dmitry.artistshower.R;
import com.example.dmitry.artistshower.model.Artist;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by dmitry on 25.04.16.
 */
public class ArtistInfoActivity extends AppCompatActivity implements IArtistInfoActivity, Toolbar.OnClickListener {
    @Bind(R.id.artist_info_toolbar)
    Toolbar mToolBar;
    @Bind(R.id.artist_info_big_cover)
    ImageView mBigCover;
    @Bind(R.id.artist_info_genres)
    TextView mGenres;
    @Bind(R.id.artist_info_statistics)
    TextView mStatistics;
    @Bind(R.id.artist_info_biography_text)
    TextView mBiographyText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.artist_info);
        ButterKnife.bind(this);
        App.getAppComponent(this).inject(this);
        Intent intent = getIntent();
        if (intent != null) {
            Artist artist = (Artist) intent.getSerializableExtra("Artist");
            //Log.d("mytag", "ArtistInfo constructor artist = " + artist);
            manageToolBar(artist.getName());
            Picasso.with(getApplicationContext()).load(artist.getBigCover()).into(mBigCover);
            mGenres.setText(artist.getGenres().toString());
            mStatistics.setText(artist.getAlbums() + " \u2022 " + artist.getTracks());
            mBiographyText.setText(artist.getDescription());
        } else Log.e("mytag", "intent is null");


    }

    private void manageToolBar(String artistName) {
        mToolBar.setTitle(artistName);
        mToolBar.setTitleTextColor(Color.WHITE);
        mToolBar.setNavigationIcon(R.drawable.ic_back);
        setSupportActionBar(mToolBar);
        mToolBar.setNavigationOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        onBackPressed();
    }
}
