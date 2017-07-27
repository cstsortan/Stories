package com.d4vinci.stories.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.d4vinci.stories.R;
import com.d4vinci.stories.activities.NewStoryActivity;
import com.d4vinci.stories.adapters.StoriesFeedAdapter;
import com.d4vinci.stories.models.User;
import com.d4vinci.stories.services.DataRefs;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class StoriesFragment extends Fragment {

    private RecyclerView rvStoriesFeed;
    private StoriesFeedAdapter storiesFeedAdapter;

    public StoriesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_stories, container, false);

        rvStoriesFeed = (RecyclerView) view.findViewById(R.id.rvStoriesFeed);
        rvStoriesFeed.setLayoutManager(new LinearLayoutManager(
                getContext(), LinearLayoutManager.VERTICAL, false
        ));
        storiesFeedAdapter = new StoriesFeedAdapter(DataRefs.getInstance().getStoriesRef());

        rvStoriesFeed.setAdapter(storiesFeedAdapter);
        rvStoriesFeed.setHasFixedSize(true);
        rvStoriesFeed.smoothScrollToPosition(0);

        final ImageView imageView = (ImageView) view.findViewById(R.id.ivNewStoryBtnPhoto);
        DataRefs.getInstance()
                .getUsersRef()
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Glide.with(getActivity())
                                .load(dataSnapshot.getValue(User.class).getPhoto())
                                .into(imageView);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

        LinearLayout layoutNewStoryBtn = (LinearLayout) view.findViewById(R.id.layoutNewStoryBtn);
        layoutNewStoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), NewStoryActivity.class));
            }
        });

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        storiesFeedAdapter.cleanup();
    }
}
