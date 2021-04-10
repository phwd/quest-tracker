package com.oculus.horizon.service_media;

import com.oculus.horizon.platformplugin.PlatformPluginManager;
import com.oculus.time.Clock;

public class AudioCapture {
    public static final int AUDIO_BUFFER_LENGTH = 48000;
    public static final int INVALID_BYTES_LENGTH = -1;
    public static final int SAMPLE_RATE = 48000;
    public final byte[] mAudioBytesBuffer = new byte[96000];
    public final short[] mAudioShortsBuffer = new short[48000];
    public final Clock mClock = new Clock();
    public long mCurrentTimestampMs;
    public final Object mLock = new Object();
    public final long mNativeAudioRenderer;
    public final PlatformPluginManager mPlatformPluginManager;

    public AudioCapture(PlatformPluginManager platformPluginManager, long j) {
        this.mPlatformPluginManager = platformPluginManager;
        this.mNativeAudioRenderer = j;
    }
}
