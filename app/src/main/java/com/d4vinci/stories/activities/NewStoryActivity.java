package com.d4vinci.stories.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.d4vinci.stories.R;
import com.d4vinci.stories.services.DataRefs;

import java.util.ArrayList;

public class NewStoryActivity extends AppCompatActivity {

    private static final int PERMISSION_READ_EXTERNAL = 123;
    private static final String TAG = "NewStoryActivity";

    private ArrayList<String> images = new ArrayList<>();
    private EditText etStoryText;

    private ImageView addPhoto;
    private ImageView ivStoryPhoto;
    private Uri selectedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_story);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etStoryText = (EditText) findViewById(R.id.etNewStoryText);

        ivStoryPhoto = (ImageView) findViewById(R.id.ivStoryPhoto);

        addPhoto = (ImageView) findViewById(R.id.ivAddPhotoToStory);
        addPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI), 1);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_new_story, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_new_story_post:
                Toast.makeText(this, "Posting new story...", Toast.LENGTH_SHORT).show();
                if (selectedImage!=null && !etStoryText.getText().toString().equals("")) {
                    DataRefs.getInstance().uploadStory(etStoryText.getText().toString(), selectedImage.toString());
                } else {
                    Toast.makeText(this, "Error with this story", Toast.LENGTH_SHORT).show();
                }
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if(resultCode == RESULT_OK) {
                    selectedImage = data.getData();
                    Glide.with(this)
                            .load(selectedImage)
                            .into(ivStoryPhoto);
                }
                break;
        }
    }
}
