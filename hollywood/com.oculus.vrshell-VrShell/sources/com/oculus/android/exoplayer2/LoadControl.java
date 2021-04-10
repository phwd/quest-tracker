package com.oculus.android.exoplayer2;

import com.oculus.android.exoplayer2.source.TrackGroupArray;
import com.oculus.android.exoplayer2.trackselection.TrackSelectionArray;
import com.oculus.android.exoplayer2.upstream.Allocator;

public interface LoadControl {
    Allocator getAllocator();

    long getBackBufferDurationUs();

    void onPrepared();

    void onReleased();

    void onStopped();

    void onTracksSelected(Renderer[] rendererArr, TrackGroupArray trackGroupArray, TrackSelectionArray trackSelectionArray);

    boolean retainBackBufferFromKeyframe();

    boolean shouldContinueLoading(long j, float f);

    boolean shouldStartPlayback(long j, float f, boolean z);
}
