package com.oculus.android.exoplayer2.trackselection;

import com.oculus.android.exoplayer2.ExoPlaybackException;
import com.oculus.android.exoplayer2.RendererCapabilities;
import com.oculus.android.exoplayer2.source.TrackGroupArray;

public abstract class TrackSelector {
    private InvalidationListener listener;

    public interface InvalidationListener {
        void onTrackSelectionsInvalidated();
    }

    public abstract void onSelectionActivated(Object obj);

    public abstract TrackSelectorResult selectTracks(RendererCapabilities[] rendererCapabilitiesArr, TrackGroupArray trackGroupArray) throws ExoPlaybackException;

    public final void init(InvalidationListener invalidationListener) {
        this.listener = invalidationListener;
    }

    /* access modifiers changed from: protected */
    public final void invalidate() {
        InvalidationListener invalidationListener = this.listener;
        if (invalidationListener != null) {
            invalidationListener.onTrackSelectionsInvalidated();
        }
    }
}
