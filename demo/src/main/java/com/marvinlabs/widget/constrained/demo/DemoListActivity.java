package com.marvinlabs.widget.constrained.demo;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

public class DemoListActivity extends FragmentActivity implements DemoListFragment.OnDemoSelectedListener {

    private static final String TAG_LIST_FRAGMENT = "ListFragment";
    private static final String TAG_DEMO_FRAGMENT = "DemoFragment";
    private DemoListFragment listFragment;
    private boolean isDualPane;
    private int currentDemo;

    /**
     * Creates a fragment given a fully qualified class name and some arguments
     */
    public static Fragment newFragmentInstance(String className, Bundle args) {
        try {
            Class<?> c = Class.forName(className);
            Fragment f = (Fragment) c.newInstance();
            f.setArguments(args);
            return f;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Cannot create fragment", e);
        } catch (InstantiationException e) {
            throw new RuntimeException("Cannot create fragment", e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Cannot create fragment", e);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demolist);

        listFragment = (DemoListFragment) getSupportFragmentManager().findFragmentByTag(TAG_LIST_FRAGMENT);
        if (listFragment == null) {
            listFragment = DemoListFragment.newInstance();
            getSupportFragmentManager().beginTransaction().replace(R.id.main_container, listFragment,
                    TAG_LIST_FRAGMENT).commitAllowingStateLoss();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        listFragment.setSelectable(isDualPane);
    }

    @Override
    public void onDemoSelected(String fragmentClass, Bundle args) {
        Intent i = new Intent(this, DemoActivity.class);
        i.putExtra(DemoActivity.EXTRA_FRAGMENT_CLASS, fragmentClass);
        i.putExtra(DemoActivity.EXTRA_FRAGMENT_ARGS, args);
        startActivity(i);
    }

}
