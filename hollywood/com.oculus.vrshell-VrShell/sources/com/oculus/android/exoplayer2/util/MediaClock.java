package com.oculus.android.exoplayer2.util;

import com.oculus.android.exoplayer2.PlaybackParameters;

public interface MediaClock {
    PlaybackParameters getPlaybackParameters();

    long getPositionUs();

    PlaybackParameters setPlaybackParameters(PlaybackParameters playbackParameters);
}
