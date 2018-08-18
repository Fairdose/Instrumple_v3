package com.example.st_peter_hq.instrumple;

import android.support.annotation.Nullable;

public class Instrument {
    
    //Define values
    
    private int iInstrumentName;

    private Sample iInstrumentId;

    private String iSummedHistory;

    private String iExternalLink;

    private int iInstrumentResId = NO_IMAGE_PROVIDED;

    private static final int NO_IMAGE_PROVIDED = -1;
    
    //Create Instrument object
    
    public Instrument(int instrumentName, int imageResourceId, Sample audioResourceId, @Nullable String summedHistory, @Nullable String externalLink) {
        iInstrumentName = instrumentName;
        iInstrumentId = audioResourceId;
        iInstrumentResId = imageResourceId;
        iSummedHistory = summedHistory;
        iExternalLink = externalLink;

    }
    
    //Define getter methods
    
    public int getInstrumentName() { return iInstrumentName; }
    
    public int getImageResourceId() { return iInstrumentResId; }

    public String getiSummedHistory() { return iSummedHistory; }

    public String getiExternalLink() { return iExternalLink; }

    public boolean hasImage() { return iInstrumentResId != NO_IMAGE_PROVIDED; }
    
    public Sample getAudioResourceId() { return iInstrumentId; }
}
