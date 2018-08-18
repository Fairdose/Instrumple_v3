package com.example.st_peter_hq.instrumple;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class KeyboardActivity extends Home  {

    private MediaPlayer player;

    private AudioManager manager;

    private LinearLayout playerBar;

    private TextView nowPlaying;

    private ImageView sampleImage;

    private AudioManager.OnAudioFocusChangeListener changeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                player.pause();
                player.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                player.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                releaseMediaPlayer();
            }
        }
    };
    private MediaPlayer.OnCompletionListener completionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {

            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);

        manager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Instrument> instruments = new ArrayList<Instrument>();
        instruments.add(new Instrument(R.string.keyboard_1, R.drawable.keyboard, new Sample("Sample 61", R.raw.sound, R.drawable.keyboard), null, null));
        instruments.add(new Instrument(R.string.keyboard_2, R.drawable.keyboard, new Sample("Sample 29", R.raw.sound, R.drawable.keyboard), null, null));
        instruments.add(new Instrument(R.string.keyboard_3, R.drawable.keyboard, new Sample("Sample 13", R.raw.sound, R.drawable.keyboard), null, null));

        InstrumentAdapter adapter = new InstrumentAdapter(this, instruments);

        ListView listView = findViewById(R.id.list);

        playerBar = findViewById(R.id.player_bar);

        nowPlaying = findViewById(R.id.now_playing);

        sampleImage = findViewById(R.id.sample_image);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                releaseMediaPlayer();

                Instrument instrument = instruments.get(position);

                int result = manager.requestAudioFocus(changeListener,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    playerBar.setVisibility(View.VISIBLE);
                    nowPlaying.setText(instrument.getAudioResourceId().getSample());
                    sampleImage.setImageResource(instrument.getAudioResourceId().getSampleImage());

                    player = MediaPlayer.create(KeyboardActivity.this, instrument.getAudioResourceId().getSampleLocation());
                    player.start();
                    player.setOnCompletionListener(completionListener);
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer() {

        if (player != null) {
            playerBar.setVisibility(View.GONE);
            player.release();
            player = null;
            manager.abandonAudioFocus(changeListener);
        }
    }
}
