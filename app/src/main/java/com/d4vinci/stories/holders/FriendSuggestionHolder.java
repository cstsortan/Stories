package com.d4vinci.stories.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.d4vinci.stories.R;
import com.d4vinci.stories.models.User;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

/**
 * Created by D4Vinci on 7/27/2017.
 */

public class FriendSuggestionHolder extends RecyclerView.ViewHolder {

    private ImageView ivPic;
    private TextView tvName;
    private TextView tvMutualFriends;
    private Button btAddFriend;

    public FriendSuggestionHolder(View itemView) {
        super(itemView);
        ivPic = (ImageView) itemView.findViewById(R.id.ivFriendSuggestionPic);
        tvName = (TextView) itemView.findViewById(R.id.tvFriendSuggestionName);
        tvMutualFriends = (TextView) itemView.findViewById(R.id.tvMutualFriends);
        btAddFriend = (Button) itemView.findViewById(R.id.btAddFriend);
    }

    public void updateUI(final User friend) {
        if(Objects.equals(friend.getUid(), FirebaseAuth.getInstance().getCurrentUser().getUid())) {
            itemView.setVisibility(View.GONE);
        } else {
            itemView.setVisibility(View.VISIBLE);
        }
        Glide.with(itemView.getContext().getApplicationContext())
                .load(friend.getPhoto())
                .into(ivPic);
        tvName.setText(friend.getName());
        btAddFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFriend(friend);
            }
        });
        if (friend.getFriends() != null) {
            tvMutualFriends.setText(friend.getFriends().length + " mutual friends");
        } else {
            tvMutualFriends.setText("");
        }
    }

    private void addFriend(User friend) {
        //TODO add friend logic

    }

}
