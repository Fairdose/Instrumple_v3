package com.example.st_peter_hq.instrumple;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class PercussionActivity extends AppCompatActivity {

    private MediaPlayer player;

    private AudioManager manager;

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
        instruments.add(new Instrument(R.string.percussion_1, R.drawable.percussion, R.raw.sound, null, null));
        instruments.add(new Instrument(R.string.percussion_2, R.drawable.percussion, R.raw.sound, null, null));
        instruments.add(new Instrument(R.string.percussion_3, R.drawable.percussion, R.raw.sound, null, null));

        InstrumentAdapter adapter = new InstrumentAdapter(this, instruments);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                releaseMediaPlayer();

                Instrument instrument = instruments.get(position);

                int result = manager.requestAudioFocus(changeListener,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    player = MediaPlayer.create(PercussionActivity.this, instrument.getAudioResourceId());
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
            player.release();
            player = null;
            manager.abandonAudioFocus(changeListener);
        }
    }
}
