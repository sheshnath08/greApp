package Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sheshnath.grevocabularybuilder.Activities.WordCardActivity;
import com.sheshnath.grevocabularybuilder.R;

import java.util.ArrayList;
import java.util.List;

import Adapters.WordListAdapter;
import Adapters.WordModel;
import DataSource.Words;
import Listners.RecyclerItemClickListner;

/**
 * Created by Sheshnath on 10/9/2015.
 */
public class WordListFragment extends Fragment {
    public static WordListFragment newInstance() {
        return new WordListFragment();
    }



    private RecyclerView mRecyclerView;
    private WordListAdapter mAdapter;
    private List<WordModel> mModels;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.wordlistfragment, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.wordListView);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mModels = new ArrayList<>();

        //adding data to model list
        for (String word : Words.WORDLIST) {
            mModels.add(new WordModel(word,word, word, word));
        }

        mAdapter = new WordListAdapter(getActivity(), mModels);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListner(getContext(), new RecyclerItemClickListner.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        // TODO Handle item click
                        Intent intent = new Intent(getContext(), WordCardActivity.class);
                        getActivity().startActivity(intent);
                        //getFragmentManager().beginTransaction().replace(R.id.container, WordCardFragment.newInstance()).addToBackStack("word").commit();
                    }
                })
        );

    }

}
