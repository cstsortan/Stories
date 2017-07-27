package com.d4vinci.stories.holders;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.d4vinci.stories.R;
import com.d4vinci.stories.models.Story;
import com.d4vinci.stories.models.User;
import com.d4vinci.stories.services.DataRefs;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by D4Vinci on 7/17/2017.
 */

public class StoryHolder extends RecyclerView.ViewHolder {

    private static final String TAG = "StoryHolder";
    private ImageView ivPhoto;
    private ImageView ivAuthorPic;
    private TextView tvAuthorName;
    private TextView tvText;
    private ImageView ivLikeBtn;
    private TextView tvLikes;

    private boolean isLiked;

    public StoryHolder(final View itemView) {
        super(itemView);
        ivPhoto = (ImageView) itemView.findViewById(R.id.ivStoryFeedPhoto);
        ivAuthorPic = (ImageView) itemView.findViewById(R.id.ivStoryFeedAuthorPic);
        tvAuthorName = (TextView) itemView.findViewById(R.id.tvStoryFeedAuthor);
        tvText = (TextView) itemView.findViewById(R.id.tvStoryFeedText);
        tvLikes = (TextView) itemView.findViewById(R.id.tvStoryFeedLikesNumber);
        ivLikeBtn = (ImageView) itemView.findViewById(R.id.ivStoryFeedLikeBtn);
        ivLikeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isLiked) {
                    Log.d(TAG, "onClick: It was deselected");
                    ivLikeBtn.setImageResource(R.drawable.heart_deselected_icon);
                    isLiked = false;
                } else {
                    ivLikeBtn.setImageResource(R.drawable.heart_selected_icon);
                    Log.d(TAG, "onClick: it was selected");
                    isLiked = true;
                }
            }
        });
    }

    public void updateUI(Story story) {
        tvAuthorName.setText("");
        ivAuthorPic.setImageDrawable(null);
        tvText.setText(story.getText());
        if (story.getLikes() != null) {
            tvLikes.setText(story.getLikes().length);
        }
        if (story.getPhoto() != null) {
            Glide.with(itemView.getContext())
                    .load(story.getPhoto())
                    .into(ivPhoto);
        }
        isLiked = false;
        DataRefs.getInstance().getUsersRef().child(story.getAuthor()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                tvAuthorName.setText(user.getName());
                Glide.with(itemView.getContext().getApplicationContext())
                        .load(user.getPhoto())
                        .diskCacheStrategy(DiskCacheStrategy.RESULT)
                        .into(ivAuthorPic);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
