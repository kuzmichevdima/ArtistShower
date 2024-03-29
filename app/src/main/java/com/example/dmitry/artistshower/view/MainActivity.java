package com.example.dmitry.artistshower.view;

import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dmitry.artistshower.App;
import com.example.dmitry.artistshower.R;
import com.example.dmitry.artistshower.model.Artist;
import com.example.dmitry.artistshower.presenter.IPresenter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

//В этом проекте используется принцип MVP, а также Dependency Injection (Butterknife и Dagger 2)
//Помимо минимальных требований, есть еще поиск артиста по имени (вызывается через нажатие на символ лупы в правом верхнем углу основного экрана
public class MainActivity extends AppCompatActivity implements IMainActivity {
    //с использованием butterknife делаем bind элементов - это удобно и явно показывает, что нам нужно в этом activity
    @Bind(R.id.artists_recycler)
    RecyclerView mRecycler;
    @Bind(R.id.main_toolbar)
    Toolbar mToolbar;
    //с помощью dagger 2 делаем inject презентера
    @Inject
    IPresenter mPresenter;

    @Override
    public Context getContext() {
        return getApplicationContext();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        App.getAppComponent(this).inject(this);
        mToolbar.setTitle(R.string.main_activity_title);
        setSupportActionBar(mToolbar);
        initRecycler();
        //вызываем метод презентера, загружающий данные
        mPresenter.onCreate(savedInstanceState, this);
        mPresenter.loadData();
    }

    //это метод, который будет вызываться презентером (и его мы объявили в интерфейсе)
    @Override
    public void setArtists(List<Artist> artists) {
        ((ArtistAdapter) mRecycler.getAdapter()).setArtists(artists);
    }

    //инициализация Recycler вынесена в отдельный метод для лучшей читабельности
    private void initRecycler() {
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecycler.setLayoutManager(mLayoutManager);
        mRecycler.setAdapter(new ArtistAdapter());
    }

    //в меню у нас один только пункт - поиск, обозначенный символом лупы
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.start_search) {
            FragmentTransaction ft = (getFragmentManager().beginTransaction());
            SearchFragment dialog = new SearchFragment();
            dialog.show(ft, "Search Fragment");
        }
        return super.onOptionsItemSelected(item);
    }

    public class ArtistAdapter extends RecyclerView.Adapter<ArtistAdapter.ViewHolder> {

        private List<Artist> mArtists = new ArrayList<>();

        public void setArtists(List<Artist> artists) {
            mArtists = artists;
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
            return mArtists == null ? 0 : mArtists.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            //здесь мы делаем bind всех элементов одной карточки
            @Bind(R.id.main_artist_small_cover)
            ImageView mArtistSmallCover;
            @Bind(R.id.main_artist_name)
            TextView mArtistName;
            @Bind(R.id.main_artist_genres)
            TextView mArtistGenres;
            @Bind(R.id.main_artist_albums)
            TextView mArtistAlbums;
            @Bind(R.id.main_artist_tracks)
            TextView mArtistTracks;

            private Artist mHolderArtist;

            ViewHolder(View item) {
                super(item);
                ButterKnife.bind(this, item);
                item.setOnClickListener(this);
            }

            public void bind(Artist artist) {
                mHolderArtist = artist;
                mArtistName.setText(mHolderArtist.getName());
                mArtistGenres.setText(mHolderArtist.getGenres());
                mArtistAlbums.setText(mHolderArtist.getAlbums());
                mArtistTracks.setText(mHolderArtist.getTracks());
                Picasso.with(getApplicationContext()).load(mHolderArtist.getSmallCover()).into(mArtistSmallCover);
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
