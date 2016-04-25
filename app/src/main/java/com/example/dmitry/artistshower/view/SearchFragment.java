package com.example.dmitry.artistshower.view;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;

import com.example.dmitry.artistshower.App;
import com.example.dmitry.artistshower.R;
import com.example.dmitry.artistshower.presenter.IPresenter;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by dmitry on 25.04.16.
 */

//этот фрагмент позволяет искать среди исполнителей конкретного путем ввода его имени
public class SearchFragment extends DialogFragment {
    @Bind(R.id.search_name)
    EditText mEditTextName;
    @Bind(R.id.search_toolbar)
    Toolbar mSearchToolbar;
    @Inject
    IPresenter mPresenter;



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        App.getAppComponent(getActivity()).inject(this);
        View view = inflater.inflate(R.layout.search_fragment_layout, null);
        ButterKnife.bind(this, view);

        //меню состоит всего лишь из одного значка галочки
        mSearchToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.search_accept) {
                    mPresenter.searchArtist(mEditTextName.getText().toString());
                    dismiss();
                    return true;
                } else {
                    return false;
                }
            }
        });
        mSearchToolbar.inflateMenu(R.menu.search_fragment_menu);
        mSearchToolbar.setTitle("Поиск исполнителя");
        return view;
    }
}

