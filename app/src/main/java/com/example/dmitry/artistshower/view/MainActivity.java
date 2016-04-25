package com.example.dmitry.artistshower.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dmitry.artistshower.App;
import com.example.dmitry.artistshower.R;
import com.example.dmitry.artistshower.model.Artist;
import com.example.dmitry.artistshower.presenter.IMainActivityPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements IMainActivity {
    static final String tag = "MainActivity";
    //с использованием butterknife делаем bind элементов - это удобно и явно показывает, что нам нужно в этом activity
    @Bind(R.id.artists_recycler)
    RecyclerView mRecycler;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    //с помощью dagger 2 делаем inject презентера
    @Inject
    IMainActivityPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        App.getAppComponent(this).inject(this);
        mToolbar.setTitle("Исполнители");
        setSupportActionBar(mToolbar);
        initRecycler();
        mPresenter.onCreate(savedInstanceState, this);
        mPresenter.loadData();
    }

    @Override
    public void setArtists(List<Artist> artists) {
        ((ArtistAdapter) mRecycler.getAdapter()).setArtists(artists);
    }

    private void initRecycler() {
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecycler.setLayoutManager(mLayoutManager);
        mRecycler.setAdapter(new ArtistAdapter());
    }

    public class ArtistAdapter extends RecyclerView.Adapter<ArtistAdapter.ViewHolder> {

        private List<Artist> mArtists = new ArrayList<>();

        public void setArtists(List<Artist> artists) {
            mArtists = artists;
            Log.d(tag, "ArtistAdapter mArtists size = " + mArtists.size());
            notifyDataSetChanged();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_artist_main_activity, parent, false));
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.bind(mArtists.get(position));
        }

        @Override
        public int getItemCount() {
            return mArtists.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            @Bind(R.id.main_artist_small_cover)
            ImageView mArtistSmallCover;
            @Bind(R.id.main_artist_name)
            TextView mArtistName;
            @Bind(R.id.main_artist_genres)
            TextView mArtistGenres;
            @Bind(R.id.main_artist_statistics)
            TextView mArtistStatistics;

            private Artist holderArtist;

            ViewHolder(View item) {
                super(item);
                ButterKnife.bind(this, item);
                item.setOnClickListener(this);
            }

            public void bind(Artist artist) {
                holderArtist = artist;
                mArtistName.setText(holderArtist.getName());
                mArtistGenres.setText(holderArtist.getGenres().toString());
                //mArtistStatistics.setText(holderArtist.getAlbums());
            }

            @Override
            public void onClick(View v) {
                int index = getAdapterPosition();
                if (index != RecyclerView.NO_POSITION) {
                    mPresenter.onArtistSelected(mArtists.get(index));
                }
            }
        }
    }
}
