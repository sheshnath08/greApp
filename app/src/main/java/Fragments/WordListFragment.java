package Fragments;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
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
import Listners.RecyclerItemClickListner;

/**
 * Created by Sheshnath on 10/9/2015.
 */
public class WordListFragment extends Fragment {
    public static WordListFragment newInstance() {
        return new WordListFragment();
    }

    private static final String[]MOVIES = new String[]{
            "The Woman in Black: Angel of Death",
            "20 Once Again",
            "Taken 3",
            "Tevar",
            "I",
            "Blackhat",
            "Spare Parts",
            "The Wedding Ringer",
            "Ex Machina",
            "Mortdecai",
            "Strange Magic",
            "The Boy Next Door",
            "The SpongeBob Movie: Sponge Out of Water",
            "Kingsman: The Secret Service",
            "Boonie Bears: Mystical Winter",
            "Project Almanac",
            "Running Man",
            "Wild Card",
            "It Follows",
            "C'est si bon",
            "Yennai Arindhaal",
            "Shaun the Sheep Movie",
            "Jupiter Ascending",
            "Old Fashioned",
            "Somewhere Only We Know",
            "Fifty Shades of Grey",
            "Dragon Blade",
            "Danny Collins",
            "Do You Believe?",
            "Jalaibee",
            "The Divergent Series: Insurgent",
            "The Gunman",
            "Get Hard",
            "Home"
    };

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
        for (String movie : MOVIES) {
            mModels.add(new WordModel(movie,movie));
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
        //TODO Add a Click listner here
    }

}
