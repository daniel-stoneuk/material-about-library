package com.danielstone.materialaboutlibrary;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import com.danielstone.materialaboutlibrary.adapters.MaterialAboutListAdapter;
import com.danielstone.materialaboutlibrary.model.MaterialAboutList;
import com.danielstone.materialaboutlibrary.util.DefaultViewTypeManager;
import com.danielstone.materialaboutlibrary.util.ViewTypeManager;

import java.lang.ref.WeakReference;

public abstract class MaterialAboutFragment extends Fragment {

    private MaterialAboutList list = new MaterialAboutList.Builder().build();
    private RecyclerView recyclerView;
    private MaterialAboutListAdapter adapter;

    public static MaterialAboutFragment newInstance(MaterialAboutFragment fragment) {
        return fragment;
    }

    protected abstract MaterialAboutList getMaterialAboutList(Context activityContext);


    protected boolean shouldAnimate() {
        return true;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.mal_material_about_content, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.mal_recyclerview);
        adapter = new MaterialAboutListAdapter(getViewTypeManager());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        RecyclerView.ItemAnimator animator = recyclerView.getItemAnimator();
        if (animator instanceof SimpleItemAnimator) {
            ((SimpleItemAnimator) animator).setSupportsChangeAnimations(false);
        }

        recyclerView.setAlpha(0f);
        recyclerView.setTranslationY(20);

        new ListTask(this).execute();

        return rootView;
    }

    @NonNull
    protected ViewTypeManager getViewTypeManager() {
        return new DefaultViewTypeManager();
    }

    protected MaterialAboutList getList() {
        return list;
    }

    protected void setMaterialAboutList(MaterialAboutList materialAboutList) {
        list = materialAboutList;
        adapter.setData(list.getCards());
    }

    private void displayList(){
        if (shouldAnimate()) {
            recyclerView.animate()
                    .alpha(1f)
                    .translationY(0f)
                    .setDuration(600)
                    .setInterpolator(new FastOutSlowInInterpolator())
                    .start();
        } else {
            recyclerView.setAlpha(1f);
            recyclerView.setTranslationY(0f);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    protected void refreshMaterialAboutList() {
        setMaterialAboutList(list);
    }

    private static class ListTask extends AsyncTask<String, String, MaterialAboutList> {

        private final WeakReference<MaterialAboutFragment> fragmentContextReference;

        ListTask(MaterialAboutFragment materialAboutFragment) {
            this.fragmentContextReference = new WeakReference<>(materialAboutFragment);
        }

        @Override
        protected MaterialAboutList doInBackground(String... params) {
            MaterialAboutFragment fragment = fragmentContextReference.get();
            return isCancelled() || fragment == null ? null : fragment.getMaterialAboutList(fragment.getActivity());
        }

        @Override
        protected void onPostExecute(MaterialAboutList materialAboutList) {
            MaterialAboutFragment fragment = fragmentContextReference.get();
            if (fragment != null) {
                fragment.setMaterialAboutList(materialAboutList);
                fragment.displayList();
            }
            fragmentContextReference.clear();
        }
    }
}
