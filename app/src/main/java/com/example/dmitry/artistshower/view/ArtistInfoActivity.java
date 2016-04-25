package com.example.dmitry.artistshower.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dmitry.artistshower.App;
import com.example.dmitry.artistshower.R;
import com.example.dmitry.artistshower.model.Artist;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by dmitry on 25.04.16.
 */
public class ArtistInfoActivity extends AppCompatActivity implements Toolbar.OnClickListener {
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

    //при создании нужно проинициализировать все отображаемые элементы
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.artist_info);
        ButterKnife.bind(this);
        App.getAppComponent(this).inject(this);
        Intent intent = getIntent();
        if (intent != null) {
            Artist artist = (Artist) intent.getSerializableExtra("Artist");
            manageToolBar(artist.getName());
            Picasso.with(getApplicationContext()).load(artist.getBigCover()).into(mBigCover);
            mGenres.setText(artist.getGenres().toString());
            mStatistics.setText(artist.getAlbums() + " \u2022 " + artist.getTracks());
            mBiographyText.setText(artist.getDescription());
        } else {
            Toast.makeText(getApplicationContext(), "приходящий в ArtistInfo intent пуст", Toast.LENGTH_LONG).show();
        }
    }

    //обработка Toolbar вынесена в отдельную функцию из-за своего объема
    private void manageToolBar(String artistName) {
        mToolBar.setTitle(artistName);
        mToolBar.setTitleTextColor(Color.WHITE);
        mToolBar.setNavigationIcon(R.drawable.ic_back);
        setSupportActionBar(mToolBar);
        mToolBar.setNavigationOnClickListener(this);
    }

    //также мы добавляем обработку кнопки "назад"
    @Override
    public void onClick(View v) {
        onBackPressed();
    }
}
