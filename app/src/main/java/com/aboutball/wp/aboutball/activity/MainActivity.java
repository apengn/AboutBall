package com.aboutball.wp.aboutball.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.aboutball.wp.aboutball.R;
import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;

public class MainActivity extends BaseActivity implements Toolbar.OnMenuItemClickListener, View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected Toolbar getActionToolBar() {
        return findViewById(R.id.toolbar);
    }

    @Override
    protected String getToolbarTitle() {
        return "Main";
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cat_topappbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        if (item.getItemId() == R.id.search) {
            Snackbar.make(findViewById(R.id.container), "search", Snackbar.LENGTH_LONG).show();
        } else if (item.getItemId() == R.id.favorite) {
            showProgressDialog("favorite");

        } else if (item.getItemId() == R.id.feedback) {

        }
        return false;
    }

    @Override
    protected void onBack() {
//        super.onBack();
        Snackbar.make(findViewById(R.id.container), "search", Snackbar.LENGTH_LONG).show();
        showProgressDialog("");
    }
}
