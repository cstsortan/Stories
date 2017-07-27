package com.d4vinci.stories.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.d4vinci.stories.R;
import com.d4vinci.stories.adapters.FriendSuggestionsAdapter;
import com.d4vinci.stories.services.DataRefs;

/**
 * A simple {@link Fragment} subclass.
 */
public class FriendSuggestionsFragment extends Fragment {

    private RecyclerView rvFriendSuggestions;

    public FriendSuggestionsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_friend_suggestions, container, false);

        rvFriendSuggestions = (RecyclerView) view.findViewById(R.id.rvFriendSuggestions);
        rvFriendSuggestions.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rvFriendSuggestions.setAdapter(new FriendSuggestionsAdapter(DataRefs.getInstance().getUsersRef()));

        return view;
    }

}
