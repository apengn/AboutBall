package com.aboutball.wp.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.aboutball.wp.R;
import com.aboutball.wp.activity.BaseActivity;


public class BaseFragment extends Fragment implements Toolbar.OnMenuItemClickListener, View.OnClickListener {

    private Toolbar toolbar;
    protected BaseActivity baseActivity;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context != null && context instanceof BaseActivity) {
            this.baseActivity = (BaseActivity) context;
        }
    }

    protected final void setToolbar() {
        toolbar = getActionToolBar();
        if (toolbar != null && baseActivity != null) {
            baseActivity.setSupportActionBar(toolbar);
            toolbar.setTitle(getToolbarTitleStringResId() != 0 ?
                    getResources().getString(getToolbarTitleStringResId())
                    : getToolbarTitle());
            if (getToolbarBackIcon() != 0) {
                toolbar.setNavigationIcon(getToolbarBackIcon());
                toolbar.setNavigationOnClickListener(this);
            }
            toolbar.setTitleTextColor(getResources().getColor(getToolbarTitleTextColor()));
            toolbar.setOnMenuItemClickListener(this);

        } else {
            Log.e("BaseFragment", "toolbar is null");
        }
    }

    protected int getToolbarTitleStringResId() {
        return 0;
    }

    protected String getToolbarTitle() {
        return "";
    }

    protected int getToolbarTitleTextColor() {
        return R.color.white;
    }


    protected int getToolbarBackIcon() {
        //R.drawable.ic_arrow_back_black_24dp
        return R.drawable.ic_arrow_back_black_24dp;
    }

    protected void onBack() {

    }

    protected Toolbar getActionToolBar() {
        return null;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
//        if (item.getItemId() == R.id.search) {
//            Snackbar.make(toolbar, "search", Snackbar.LENGTH_LONG).show();
//        }
        return false;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
//        inflater.inflate(R.menu.cat_topappbar_menu, menu);
    }

    @Override
    public void onClick(View view) {
        onBack();
    }


    protected final void showProgressDialog(int loadingContentResId) {
        showProgressDialog(getResources().getString(loadingContentResId));
    }

    protected final void showProgressDialog(String loadingContent) {
        if (baseActivity != null) {
            baseActivity.showProgressDialog(loadingContent);
        }
    }

    protected final void showProgressDialog() {
        showProgressDialog("");
    }

    protected final void closeProgressDialog() {
        if (baseActivity != null) {
            baseActivity.closeProgressDialog();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        closeProgressDialog();
    }
}
