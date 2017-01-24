package com.danielstone.materialaboutlibrary;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.danielstone.materialaboutlibrary.adapters.MaterialAboutListAdapter;
import com.danielstone.materialaboutlibrary.model.MaterialAboutList;
import com.danielstone.materialaboutlibrary.util.DefaultViewTypeManager;
import com.danielstone.materialaboutlibrary.util.ViewTypeManager;

public abstract class MaterialAboutFragment extends Fragment {

    private RecyclerView recyclerView;
    private MaterialAboutListAdapter adapter;
    MaterialAboutList list = new MaterialAboutList.Builder().build();

    protected abstract MaterialAboutList getMaterialAboutList(Context activityContext);

    public static final String TAG = "MaterialAboutFragment";

    public static MaterialAboutFragment newInstance(MaterialAboutFragment fragment) {
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.mal_material_about_fragment, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.mal_recyclerview);
        adapter = new MaterialAboutListAdapter(list, getViewTypeManager());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        recyclerView.setAlpha(0f);
        recyclerView.setTranslationY(20);

        ListTask task = new ListTask(getActivity());
        task.execute();

        return rootView;
    }

    private class ListTask extends AsyncTask<String, String, String> {

        Context fragmentContext;

        public ListTask(Context activityContext) {
            this.fragmentContext = activityContext;
        }

        @Override
        protected String doInBackground(String... params) {
            list = getMaterialAboutList(fragmentContext);
            return null;
        }

        @Override
        protected void onPostExecute(String s) {

            adapter.swapData(list);
            recyclerView.animate().alpha(1f).translationY(0f).setDuration(200).start();
            super.onPostExecute(s);
            fragmentContext = null;
        }
    }

    protected ViewTypeManager getViewTypeManager() {
        return new DefaultViewTypeManager();
    }

    protected void setMaterialAboutList(MaterialAboutList materialAboutList) {
        list = materialAboutList;
        adapter.swapData(materialAboutList);
    }

    protected MaterialAboutList getMaterialAboutList() {
        return list;
    }
}
