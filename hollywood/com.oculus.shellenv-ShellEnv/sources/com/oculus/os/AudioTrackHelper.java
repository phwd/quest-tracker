package com.oculus.os;

public class AudioTrackHelper {

    public interface AudioTrackListener {
        void onTrackHasNewPCM(long j, long j2, int i, int i2, int i3, int i4, float f);
    }

    public AudioTrackHelper() {
        throw new RuntimeException("Stub!");
    }

    public static void registerAudioTrackListener(AudioTrackListener audioTrackListener) {
        throw new RuntimeException("Stub!");
    }

    public static void unregisterAudioTrackListener() {
        throw new RuntimeException("Stub!");
    }
}
