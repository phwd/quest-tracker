package com.oculus.assistant.service.api.playback;

public interface PlaybackStatusListener {
    void onPlaybackStarted();

    void onPlaybackStopped();
}
