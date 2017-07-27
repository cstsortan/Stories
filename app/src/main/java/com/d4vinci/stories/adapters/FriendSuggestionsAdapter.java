package com.d4vinci.stories.adapters;

import com.d4vinci.stories.R;
import com.d4vinci.stories.holders.FriendSuggestionHolder;
import com.d4vinci.stories.models.User;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

/**
 * Created by D4Vinci on 7/27/2017.
 */

public class FriendSuggestionsAdapter extends FirebaseRecyclerAdapter<User, FriendSuggestionHolder> {

    public FriendSuggestionsAdapter(DatabaseReference friendSuggestionsReference) {
        super(User.class, R.layout.holder_friend_suggestion, FriendSuggestionHolder.class, friendSuggestionsReference);
    }

    @Override
    protected void populateViewHolder(FriendSuggestionHolder viewHolder, User friend, int position) {
        if(friend.getUid().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
            return;
        } else {
            viewHolder.updateUI(friend);
        }
    }
}
