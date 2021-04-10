package com.oculus.video.audio;

import android.media.MediaFormat;
import com.oculus.android.exoplayer2.Format;
import java.nio.ByteBuffer;

public interface AudioSpatializer {

    public enum PlayState {
        UNKNOWN,
        PLAYING,
        PAUSED
    }

    void configure(MediaFormat mediaFormat, boolean z) throws InitializationException;

    void enableFocus(boolean z);

    int getAudioMix(ByteBuffer byteBuffer);

    int getAudioMixBufferSize();

    AudioSpatializerController getController();

    PlayState getPlayState();

    long getPlaybackHeadPosition();

    void handleEndOfStream();

    boolean isInitialized();

    void pause();

    void play();

    int processBuffer(ByteBuffer byteBuffer, long j, int i, int i2) throws WriteException, UnsupportedAudioChannelLayoutException;

    void release();

    void reset();

    void setAudioSpatializerController(AudioSpatializerController audioSpatializerController);

    void setFocusProperties(float f, float f2);

    void setFocusWidthDegrees(float f);

    void setOffFocusLeveldB(float f);

    void setVolume(float f);

    boolean supportsFormat(Format format);

    void updateListenerOrientation();

    public static final class InitializationException extends Exception {
        public InitializationException() {
            this("AudioSpatializer init failed");
        }

        public InitializationException(String str) {
            super(str);
        }
    }

    public static final class WriteException extends Exception {
        public WriteException() {
            super("AudioSpatializer write failed");
        }
    }

    public static final class UnsupportedAudioChannelLayoutException extends Exception {
        public UnsupportedAudioChannelLayoutException(AudioChannelLayout audioChannelLayout) {
            super("Unsupported AudioChannelLayout: " + audioChannelLayout.toString());
        }
    }
}
