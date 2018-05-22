package com.aboutball.wp.activity;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;

import com.aboutball.wp.R;
import com.aboutball.wp.fragment.DynamicFragment;
import com.aboutball.wp.fragment.HomeFragment;
import com.aboutball.wp.fragment.MessageFragment;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    private HomeFragment homeFragment;
    private DynamicFragment dynamicFragment;
    private MessageFragment messageFragment;
    private ArrayList<Fragment> fragments;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    showFragment(homeFragment);
                    return true;
                case R.id.navigation_dashboard:
                    showFragment(dynamicFragment);
                    return true;
                case R.id.navigation_notifications:
                    showFragment(messageFragment);
                    return true;
            }
            return false;
        }
    };
    private FragmentManager fragmentManager;

    private void showFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        if (homeFragment == fragment) {
//            fragmentTransaction.hide(dynamicFragment).hide(messageFragment);
//        } else if (dynamicFragment == fragment) {
//            fragmentTransaction.hide(homeFragment).hide(messageFragment);
//        } else if ((messageFragment == fragment)){
//            fragmentTransaction.hide(homeFragment).hide(dynamicFragment);
//        }
        fragmentTransaction.replace(R.id.container,fragment);
        fragmentTransaction.commitNowAllowingStateLoss();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragments = new ArrayList<>();
        fragmentManager = getSupportFragmentManager();
        homeFragment = new HomeFragment();
        dynamicFragment = new DynamicFragment();
        messageFragment = new MessageFragment();

        fragments.add(homeFragment);
        fragments.add(dynamicFragment);
        fragments.add(messageFragment);

//        fragmentManager.beginTransaction().add(R.id.container, homeFragment).
//                add(R.id.container, dynamicFragment).
//                add(R.id.container, messageFragment).commitNowAllowingStateLoss();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        int[][] states = new int[][]{
                new int[]{-android.R.attr.state_checked},
                new int[]{android.R.attr.state_checked}
        };

        int[] colors = new int[]{getResources().getColor(R.color.home_bottom_normal),
                getResources().getColor(R.color.home_bottom_checked)
        };
        ColorStateList csl = new ColorStateList(states, colors);
        navigation.setItemTextColor(csl);
        navigation.setItemIconTintList(csl);
        showFragment(homeFragment);
    }

}
