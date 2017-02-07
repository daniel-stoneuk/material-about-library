package com.danielstone.materialaboutlibrary;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.danielstone.materialaboutlibrary.adapters.MaterialAboutListAdapter;
import com.danielstone.materialaboutlibrary.model.MaterialAboutList;
import com.danielstone.materialaboutlibrary.util.DefaultViewTypeManager;
import com.danielstone.materialaboutlibrary.util.ViewTypeManager;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public abstract class MaterialAboutFragment extends Fragment {

    private MaterialAboutList list = new MaterialAboutList.Builder().build();
    private RecyclerView recyclerView;
    private MaterialAboutListAdapter adapter;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({THEME_LIGHT, THEME_DARK})
    public @interface FragmentTheme {}
    public static final int THEME_LIGHT = 0;
    public static final int THEME_DARK = 1;

    public static MaterialAboutFragment newInstance(MaterialAboutFragment fragment) {
        return fragment;
    }

    protected abstract MaterialAboutList getMaterialAboutList(Context activityContext);

    @FragmentTheme
    protected int getTheme() {
        return THEME_LIGHT;
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int style = -1;
        switch (getTheme()) {
            case THEME_LIGHT:
                style = R.style.Theme_Mal_Light_DarkActionBar;
                break;
            case THEME_DARK:
                style = R.style.Theme_Mal_Dark_DarkActionBar;
                break;
        }
        // create ContextThemeWrapper from the original Activity Context with the custom theme
        final Context contextThemeWrapper = new android.view.ContextThemeWrapper(getActivity(), style);
        LayoutInflater localInflater = inflater.cloneInContext(contextThemeWrapper);
        View rootView = localInflater.inflate(R.layout.mal_material_about_fragment, container, false);

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

    protected ViewTypeManager getViewTypeManager() {
        return new DefaultViewTypeManager();
    }

    protected MaterialAboutList getMaterialAboutList() {
        return list;
    }

    protected void setMaterialAboutList(MaterialAboutList materialAboutList) {
        list = materialAboutList;
        adapter.swapData(materialAboutList);
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
            recyclerView.animate().alpha(1f).translationY(0f).setDuration(400).setInterpolator(new FastOutSlowInInterpolator()).start();
            super.onPostExecute(s);
            fragmentContext = null;
        }
    }
}
