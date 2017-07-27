package com.d4vinci.stories.adapters;

import com.d4vinci.stories.R;
import com.d4vinci.stories.holders.StoryHolder;
import com.d4vinci.stories.models.Story;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.Query;

/**
 * Created by D4Vinci on 7/17/2017.
 */

public class StoriesFeedAdapter extends FirebaseRecyclerAdapter<Story, StoryHolder> {

    public StoriesFeedAdapter(Query query) {
        super(Story.class, R.layout.story_feed_holder, StoryHolder.class, query);
    }

    @Override
    protected void populateViewHolder(StoryHolder storyHolder, Story story, int position) {
        storyHolder.updateUI(story);
    }
}
