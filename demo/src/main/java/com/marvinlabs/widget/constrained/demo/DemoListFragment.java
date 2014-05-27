package com.marvinlabs.widget.constrained.demo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * List all the available demos
 *
 * @author Vincent Mimoun-Prat @ MarvinLabs
 */
public class DemoListFragment extends Fragment implements AdapterView.OnItemClickListener {

    private SimpleAdapter adapter;
    private OnDemoSelectedListener demoSelectedListener;
    private List<Map<String, Object>> demos;
    private ListView listView;

    public static DemoListFragment newInstance() {
        DemoListFragment f = new DemoListFragment();
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_demolist, null, false);
        listView = (ListView) root.findViewById(android.R.id.list);
        return root;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        demos = new ArrayList<Map<String, Object>>();
        demos.add(newDemoEntry(getString(R.string.demo_aspectratio),
                "com.marvinlabs.widget.constrained.demo.AspectRatioDemoFragment",
                new Bundle()));
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        adapter = new SimpleAdapter(getActivity(), demos, android.R.layout.simple_list_item_1, new String[]{"label"},
                new int[]{android.R.id.text1});

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof OnDemoSelectedListener) {
            demoSelectedListener = (OnDemoSelectedListener) activity;
        }
    }

    @Override
    public void onDetach() {
        demoSelectedListener = null;
        super.onDetach();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        if (demoSelectedListener == null) return;

        Map<String, Object> item = (Map<String, Object>) adapter.getItem(position);
        demoSelectedListener.onDemoSelected((String) item.get("class"), (Bundle) item.get("args"));
    }

    /**
     * Sets choice mode for the list
     *
     * @param selectable whether list is to be selectable.
     */
    public void setSelectable(boolean selectable) {
        if (listView == null) return;
        listView.setChoiceMode(selectable ? ListView.CHOICE_MODE_SINGLE : ListView.CHOICE_MODE_NONE);
    }

    private Map<String, Object> newDemoEntry(String label, String className, Bundle args) {
        Map<String, Object> entry = new HashMap<String, Object>(3);
        entry.put("label", label);
        entry.put("class", className);
        entry.put("args", args);
        return entry;
    }

    public static interface OnDemoSelectedListener {
        public void onDemoSelected(String fragmentClass, Bundle args);
    }
}
