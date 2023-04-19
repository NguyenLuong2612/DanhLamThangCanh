package com.example.danhlamthangcanh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class ChiTietDLTC extends AppCompatActivity {
    TextView contentname, content1, content2;
    ImageView imgcontent1, imgcontent2;

    StorageReference img1, img2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_dltc);

        contentname = findViewById(R.id.contentname);
        content1 = findViewById(R.id.content1);
        content2 = findViewById(R.id.content2);
        imgcontent1 = findViewById(R.id.imgcontent1);
        imgcontent2 = findViewById(R.id.imgcontent2);

        Bundle b = getIntent().getExtras();
        contentname.setText( b.getString("contentname"));
        content1.setText(b.getString("content1"));
        String linkimg1 = b.getString("imgcontent1").toString();
        String linkimg2 = b.getString("imgcontent2").toString();
        String video = b.getString("video").toString();
        content2.setText(b.getString("content2"));

        // Lấy tham chiếu đến hình ảnh trong Firebase Storage
        RequestManager requestManager = Glide.with(this);
        requestManager.load(linkimg1)
                .into(imgcontent1);
        requestManager.load(linkimg2)
                .into(imgcontent2);

        YouTubePlayerView youTubePlayerView = findViewById(R.id.youtube_player_view);
        getLifecycle().addObserver(youTubePlayerView);

        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                String videoId = video;
                youTubePlayer.cueVideo(videoId, 0);
            }
        });
    }
}