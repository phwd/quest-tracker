package com.oculus.video.audio;

import android.text.TextUtils;
import androidx.core.os.EnvironmentCompat;

public enum AudioChannelLayout {
    UNKNOWN(EnvironmentCompat.MEDIA_UNKNOWN, -1, EnvironmentCompat.MEDIA_UNKNOWN, -1),
    MONO("mono", 0, "1", 1),
    STEREO("stereo", 0, "2", 2),
    TBE_4_4_2("tbe_4.4.2", 1, "tbe_4.4.2", 10),
    TBE_4("tbe_4", 2, "tbe_4", 4),
    TBE_4_2("tbe_4.2", 3, "tbe_4.2", 6),
    TBE_6("tbe_6", 4, "tbe_6", 6),
    TBE_6_2("tbe_6.2", 5, "tbe_6.2", 8),
    TBE_8("tbe_8", 6, "tbe_8", 8),
    TBE_8_2("tbe_8.2", 7, "tbe_8.2", 10),
    S5_1("s5.1", 0, "s5.1", 6),
    S7_1("s7.1", 0, "s7.1", 8),
    AMBIX_4("ambiX_4", 2, "ambiX_4", 4),
    AMBIX_4_2("ambiX_4.2", 3, "ambiX_4.2", 6),
    AMBIX_9("ambiX_9", 8, "ambiX_9", 9),
    AMBIX_9_2("ambiX_9.2", 9, "ambiX_9.2", 11),
    AMBIX_16("ambiX_16", 10, "ambiX_16", 16),
    AMBIX_16_2("ambiX_16.2", 11, "ambiX_16.2", 18);
    
    public final String channelConfiguration;
    public final String key;
    public final int priority;
    public final int totalChannelCount;

    public static AudioChannelLayout getChannelLayoutForChannelCount(int i) {
        if (i == 1) {
            return MONO;
        }
        if (i == 2) {
            return STEREO;
        }
        if (i == 6) {
            return S5_1;
        }
        if (i == 11) {
            return AMBIX_9_2;
        }
        if (i == 16) {
            return AMBIX_16;
        }
        if (i == 18) {
            return AMBIX_16_2;
        }
        if (i == 8) {
            return S7_1;
        }
        if (i != 9) {
            return UNKNOWN;
        }
        return AMBIX_9;
    }

    private AudioChannelLayout(String str, int i, String str2, int i2) {
        this.key = str;
        this.priority = i;
        this.channelConfiguration = str2;
        this.totalChannelCount = i2;
    }

    public boolean isVirtualizationLayout() {
        int i = AnonymousClass1.$SwitchMap$com$oculus$video$audio$AudioChannelLayout[ordinal()];
        return i == 1 || i == 2 || i == 3 || i == 4;
    }

    public boolean isSpatializationLayout() {
        switch (this) {
            case TBE_4_4_2:
            case TBE_4:
            case TBE_4_2:
            case TBE_6:
            case TBE_6_2:
            case TBE_8:
            case TBE_8_2:
            case AMBIX_4:
            case AMBIX_4_2:
            case AMBIX_9:
            case AMBIX_9_2:
            case AMBIX_16:
            case AMBIX_16_2:
                return true;
            default:
                return false;
        }
    }

    public static AudioChannelLayout fromString(String str) {
        if (TextUtils.isEmpty(str)) {
            return UNKNOWN;
        }
        AudioChannelLayout[] values = values();
        for (AudioChannelLayout audioChannelLayout : values) {
            if (audioChannelLayout.key.equalsIgnoreCase(str)) {
                return audioChannelLayout;
            }
        }
        return UNKNOWN;
    }

    public static AudioChannelLayout fromChannelConfiguration(String str) {
        if (TextUtils.isEmpty(str)) {
            return UNKNOWN;
        }
        AudioChannelLayout[] values = values();
        for (AudioChannelLayout audioChannelLayout : values) {
            if (audioChannelLayout.channelConfiguration.equalsIgnoreCase(str)) {
                return audioChannelLayout;
            }
        }
        return UNKNOWN;
    }

    public String toString() {
        return this.key;
    }
}
