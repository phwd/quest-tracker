package com.oculus.audio_capture;

import android.util.Log;
import com.oculus.os.AudioTrackHelper;
import com.oculus.os.Version;

public class AudioCaptureHelper {
    private static final String TAG = AudioCaptureHelper.class.getSimpleName();
    private static boolean sIsRegistered = false;

    public static native void nativeOnTrackHasNewPCM(long j, long j2, int i, int i2, int i3, int i4, float f);

    public static synchronized void startAudioCapture() {
        synchronized (AudioCaptureHelper.class) {
            if (Version.CURRENT_SDK_VERSION < 50) {
                Log.e(TAG, "The OSSDK version must be at least 50");
                return;
            }
            if (!sIsRegistered) {
                Log.d(TAG, "register AudioTrack Listener");
                AudioTrackHelper.registerAudioTrackListener(new AudioTrackHelper.AudioTrackListener() {
                    /* class com.oculus.audio_capture.AudioCaptureHelper.AnonymousClass1 */

                    public void onTrackHasNewPCM(long trackID, long bufferMemoryAddress, int sizeBytes, int frequency, int numChannels, int bitsPerSample, float volume) {
                        AudioCaptureHelper.nativeOnTrackHasNewPCM(trackID, bufferMemoryAddress, sizeBytes, frequency, numChannels, bitsPerSample, volume);
                    }
                });
                sIsRegistered = true;
            }
        }
    }

    public static synchronized void stopAudioCapture() {
        synchronized (AudioCaptureHelper.class) {
            if (sIsRegistered) {
                Log.d(TAG, "unregister AudioTrack Listener");
                AudioTrackHelper.unregisterAudioTrackListener();
            }
            sIsRegistered = false;
        }
    }
}
