package com.example.dmitry.artistshower.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.dmitry.artistshower.App;
import com.example.dmitry.artistshower.R;
import com.example.dmitry.artistshower.presenter.IMainActivityPresenter;
import com.example.dmitry.artistshower.presenter.MainActivityPresenter;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements IMainActivity {
    //с использованием butterknife делаем bind элементов - это удобно и явно показывает, что нам нужно в этом activity
    @Bind(R.id.artists_recycler)
    RecyclerView mRecycler;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    //с помощью dagger 2 делаем inject презентера
    @Inject
    IMainActivityPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        App.getAppComponent(this).inject(this);
        toolbar.setTitle("Исполнители");
        setSupportActionBar(toolbar);

    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mPresenter.onCreate(savedInstanceState, this);
        mPresenter.loadData();
    }
}
