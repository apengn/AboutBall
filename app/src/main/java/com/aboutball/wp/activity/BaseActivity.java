package com.aboutball.wp.activity;

import android.os.Build;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import com.aboutball.wp.R;
import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;

public class BaseActivity extends AppCompatActivity implements Toolbar.OnMenuItemClickListener, View.OnClickListener {
    private Toolbar toolbar;
    private MaterialDialog materialDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        toolbar = getActionToolBar();
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            toolbar.setTitle(getToolbarTitleStringResId() != 0 ?
                    getResources().getString(getToolbarTitleStringResId())
                    : getToolbarTitle());
            toolbar.setNavigationIcon(getToolbarBackIcon());
            toolbar.setTitleTextColor(getResources().getColor(getToolbarTitleTextColor()));
            toolbar.setOnMenuItemClickListener(this);
            toolbar.setNavigationOnClickListener(this);
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
        return R.drawable.ic_arrow_back_black_24dp;
    }

    protected void onBack() {
        finish();
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
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.cat_topappbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onClick(View view) {
        onBack();
    }


    public final void showProgressDialog(int loadingContentResId) {
        showProgressDialog(getResources().getString(loadingContentResId));
    }

    public final void showProgressDialog() {
        showProgressDialog("");
    }

    public final void showProgressDialog(String loadingContent) {
        if (materialDialog == null) {
            materialDialog = new MaterialDialog.Builder(this)
                    .content("加载中...").contentGravity(GravityEnum.CENTER)
                    .progress(true, 0).canceledOnTouchOutside(false)
                    .progressIndeterminateStyle(true).build();
        }
        materialDialog.setContent(!TextUtils.isEmpty(loadingContent) ? loadingContent : "加载中...");
        materialDialog.show();
    }

    public final void closeProgressDialog() {
        if (materialDialog != null) {
            materialDialog.dismiss();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        closeProgressDialog();
        materialDialog = null;
    }
}
