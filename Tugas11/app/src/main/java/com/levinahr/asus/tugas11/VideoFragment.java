package com.levinahr.asus.tugas11;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import java.util.ArrayList;
import java.util.List;

public class VideoFragment extends Fragment implements View.OnClickListener {
    private VideoView videoView;
    private MediaController mediaController;
    private Button playVideo, nextVideo;
    private List<Integer> playlist = new ArrayList<>();
    private int count = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video, container, false);
        Context context = view.getContext();

        videoView = view.findViewById(R.id.video);
        playVideo = view.findViewById(R.id.play);
        nextVideo = view.findViewById(R.id.next);
        mediaController = new MediaController(context);

        createPlaylist();
        playVideo.setOnClickListener(this);
        nextVideo.setOnClickListener(this);

        return view;
    }

    private void createPlaylist() {
        playlist.add(R.raw.line_without_a_hook_mv);
        playlist.add(R.raw.fix_you_mv);
        playlist.add(R.raw.way_back_home_mv);
    }

    private void playVideo() {
        Uri uri = Uri.parse("android.resource://" + getContext().getPackageName() + "/" + playlist.get(count));
        videoView.setVideoURI(uri);

        //Memasang MediaController untuk menampilkan tombol play, pause, dsb
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);

        //Menjalankan Video
        videoView.start();
    }

    private void nextVideo() {
        count++;
        if (count > 2) {
            count = 0;
        }

        playVideo();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.play:
                playVideo();
                break;

            case R.id.next:
                nextVideo();
                break;
        }
    }
}