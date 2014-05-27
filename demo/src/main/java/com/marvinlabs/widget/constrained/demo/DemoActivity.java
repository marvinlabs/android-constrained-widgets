package com.marvinlabs.widget.constrained.demo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

public class DemoActivity extends FragmentActivity {

    public static final String EXTRA_FRAGMENT_CLASS = "FragmentClass";
    public static final String EXTRA_FRAGMENT_ARGS = "FragmentArgs";
    private static final String TAG_DEMO_FRAGMENT = "DemoFragment";
    private Fragment demoFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        // If should be in two-pane mode, finish to return to main activity
        if (getResources().getBoolean(R.bool.has_two_panes)) {
            finish();
            return;
        }

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        demoFragment = getSupportFragmentManager().findFragmentByTag(TAG_DEMO_FRAGMENT);
        if (demoFragment != null) ft.remove(demoFragment);

        demoFragment = DemoListActivity.newFragmentInstance(getIntent().getStringExtra(EXTRA_FRAGMENT_CLASS),
                getIntent().getBundleExtra(EXTRA_FRAGMENT_ARGS));
        ft.replace(R.id.main_container, demoFragment, TAG_DEMO_FRAGMENT).commit();
    }
}
