package com.aboutball.wp.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aboutball.wp.R;

public class DynamicFragment extends BaseFragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_dynamic, null);
            setToolbar();
        }
        return view;
    }

    @Override
    protected Toolbar getActionToolBar() {
        Toolbar toolbar=null;
        if (view != null) {
            toolbar = view.findViewById(R.id.toolbar);
        }
        return toolbar;
    }

    @Override
    protected String getToolbarTitle() {
        return "动态";
    }
}
