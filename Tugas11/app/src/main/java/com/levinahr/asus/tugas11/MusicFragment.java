package com.levinahr.asus.tugas11;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MusicFragment extends Fragment implements View.OnClickListener {
    private Button Play, Pause, Stop, Next;
    private MediaPlayer mediaPlayer;
    private List<Integer> playlist = new ArrayList<>();
    private List<Integer> album = new ArrayList<>();
    private int count = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_music, container, false);

        //Inisialisasi Button
        Play = view.findViewById(R.id.play);
        Pause = view.findViewById(R.id.pause);
        Stop = view.findViewById(R.id.stop);
        Next = view.findViewById(R.id.next);

        //Menambahkan Listener pada Button
        Play.setOnClickListener(this);
        Pause.setOnClickListener(this);
        Stop.setOnClickListener(this);
        Next.setOnClickListener(this);

        createPlaylist();
        stateAwal();

        return view;
    }

    private void createPlaylist() {
        playlist.add(R.raw.line_without_a_hook);
        album.add(R.drawable.lwoah_album);
        playlist.add(R.raw.fix_you);
        album.add(R.drawable.fy_album);
        playlist.add(R.raw.way_back_home);
        album.add(R.drawable.wbh_album);
    }

    //Untuk menentukan kondisi saat aplikasi pertama kali berjalan
    private void stateAwal(){
        Play.setEnabled(true);
        Next.setEnabled(true);
        Pause.setEnabled(false);
        Stop.setEnabled(false);
    }

    //Method untuk memainkan musik
    private void playAudio(){
        //Menentukan resource audio yang akan dijalankan
        mediaPlayer = MediaPlayer.create(getContext(), playlist.get(count));

        //Kondisi Button setelah tombol play di klik
        Play.setEnabled(false);
        Pause.setEnabled(true);
        Stop.setEnabled(true);

        //Menjalankan Audio / Musik
        try {
            mediaPlayer.prepare();

        } catch (IllegalStateException ex) {
            ex.printStackTrace();
        } catch (IOException ex1) {
            ex1.printStackTrace();
        }

        mediaPlayer.start();

        //Setelah audio selesai dimainkan maka kondisi Button akan kembali seperti awal
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                stateAwal();
            }
        });
    }

    //Method untuk mengentikan musik
    @SuppressLint("SetTextI18n")
    private void pauseAudio(){
        //Jika audio sedang dimainkan, maka audio dapat di pause
        if(mediaPlayer.isPlaying()){
            if(mediaPlayer != null){
                mediaPlayer.pause();
                Pause.setText("Continue");
            }

        } else {
            //Jika audio sedang di pause, maka audio dapat dilanjutkan kembali
            if(mediaPlayer != null){
                mediaPlayer.start();
                Pause.setText("Pause");
            }
        }
    }

    //Method untuk mengakhiri musik
    private void stopAudio(){
        mediaPlayer.stop();
        try {
            //Menyetel audio ke status awal
            mediaPlayer.prepare();
            mediaPlayer.seekTo(0);

        } catch (Throwable t) {
            t.printStackTrace();
        }

        stateAwal();
    }

    private void nextAudio() {
        count++;
        if (count > 2) {
            count = 0;
        }

        ImageView imageView = getView().findViewById(R.id.gambar);
        imageView.setImageResource(album.get(count));

        if (mediaPlayer != null) {
            stopAudio();
        }

        stateAwal();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.play:
                playAudio();
                break;

            case R.id.pause:
                pauseAudio();
                break;

            case R.id.stop:
                stopAudio();
                break;

            case R.id.next:
                nextAudio();
                break;
        }
    }

    @Override
    public void onStop() {
        super.onStop();

        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }
}