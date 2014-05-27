package com.marvinlabs.widget.constrained.demo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author Vincent Mimoun-Prat @ MarvinLabs
 * @date {17/06/13}
 */
public class AspectRatioDemoFragment extends Fragment {

    public static AspectRatioDemoFragment newInstance() {
        AspectRatioDemoFragment f = new AspectRatioDemoFragment();
        return f;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_aspectratio_demo, null, false);
        return root;
    }
}
