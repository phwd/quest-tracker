package com.oculus.os;

import android.media.AudioTrack;

public class AudioTrackHelper {

    public interface AudioTrackListener {
        void onTrackHasNewPCM(long j, long j2, int i, int i2, int i3, int i4, float f);
    }

    public static void registerAudioTrackListener(final AudioTrackListener listener) {
        AudioTrack.registerAudioTrackPCMListener(new AudioTrack.AudioTrackPCMListener() {
            /* class com.oculus.os.AudioTrackHelper.AnonymousClass1 */

            public void onTrackHasNewPCM(long trackID, long bufferMemoryAddress, int sizeBytes, int frequency, int numChannels, int bitsPerSample, float volume) {
                AudioTrackListener.this.onTrackHasNewPCM(trackID, bufferMemoryAddress, sizeBytes, frequency, numChannels, bitsPerSample, volume);
            }
        });
    }

    public static void unregisterAudioTrackListener() {
        AudioTrack.unregisterAudioTrackPCMListener();
    }
}
