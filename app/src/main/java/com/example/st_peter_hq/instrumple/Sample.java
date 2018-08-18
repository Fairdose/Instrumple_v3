package com.example.st_peter_hq.instrumple;

import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;

import java.util.concurrent.TimeUnit;

public class Sample {

    private String instrumentSample;

    private int sampleLocation;

    private int sampleImage;

    public Sample(String s, int r, int i) {
        instrumentSample = s;
        sampleLocation = r;
        sampleImage = i;
    }

    public String getSample() {
        return instrumentSample;
    }

    public int getSampleLocation() {
        return sampleLocation;
    }

    public int getSampleImage() {
        return sampleImage;
    }

}
