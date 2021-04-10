package com.facebook.quicklog.identifiers;

public class AlohaArMicdrop {
    public static final int DOWNLOAD_MIDI_PERFORMANCE = 54001672;
    public static final int DOWNLOAD_SONG_LIST = 54001668;
    public static final int DOWNLOAD_SONG_PERFORMANCE = 54001671;
    public static final int DOWNLOAD_SONG_PREVIEW = 54001669;
    public static final int INITIATE_MICDROP = 54001666;
    public static final int INITIATE_UNITY = 54001665;
    public static final int LOAD_MAIN_SCENE = 54001667;
    public static final short MODULE_ID = 824;
    public static final int WAIT_FOR_OTHER_PARTY = 54001670;

    public static String getMarkerName(int i) {
        switch (i) {
            case 1:
                return "ALOHA_AR_MICDROP_INITIATE_UNITY";
            case 2:
                return "ALOHA_AR_MICDROP_INITIATE_MICDROP";
            case 3:
                return "ALOHA_AR_MICDROP_LOAD_MAIN_SCENE";
            case 4:
                return "ALOHA_AR_MICDROP_DOWNLOAD_SONG_LIST";
            case 5:
                return "ALOHA_AR_MICDROP_DOWNLOAD_SONG_PREVIEW";
            case 6:
                return "ALOHA_AR_MICDROP_WAIT_FOR_OTHER_PARTY";
            case 7:
                return "ALOHA_AR_MICDROP_DOWNLOAD_SONG_PERFORMANCE";
            case 8:
                return "ALOHA_AR_MICDROP_DOWNLOAD_MIDI_PERFORMANCE";
            default:
                return "UNDEFINED_QPL_EVENT";
        }
    }
}
