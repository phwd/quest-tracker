package com.oculus.android.exoplayer2.source;

import androidx.annotation.CallSuper;
import androidx.annotation.Nullable;
import com.oculus.android.exoplayer2.ExoPlayer;
import com.oculus.android.exoplayer2.Timeline;
import com.oculus.android.exoplayer2.source.MediaSource;
import com.oculus.android.exoplayer2.util.Assertions;
import java.io.IOException;
import java.util.HashMap;

public abstract class CompositeMediaSource<T> implements MediaSource {
    private final HashMap<T, MediaSource> childSources = new HashMap<>();
    private ExoPlayer player;

    /* access modifiers changed from: protected */
    public abstract void onChildSourceInfoRefreshed(@Nullable T t, MediaSource mediaSource, Timeline timeline, @Nullable Object obj);

    protected CompositeMediaSource() {
    }

    @Override // com.oculus.android.exoplayer2.source.MediaSource
    @CallSuper
    public void prepareSource(ExoPlayer exoPlayer, boolean z, MediaSource.Listener listener) {
        this.player = exoPlayer;
    }

    @Override // com.oculus.android.exoplayer2.source.MediaSource
    @CallSuper
    public void maybeThrowSourceInfoRefreshError() throws IOException {
        for (MediaSource mediaSource : this.childSources.values()) {
            mediaSource.maybeThrowSourceInfoRefreshError();
        }
    }

    @Override // com.oculus.android.exoplayer2.source.MediaSource
    @CallSuper
    public void releaseSource() {
        for (MediaSource mediaSource : this.childSources.values()) {
            mediaSource.releaseSource();
        }
        this.childSources.clear();
        this.player = null;
    }

    /* access modifiers changed from: protected */
    public void prepareChildSource(@Nullable final T t, final MediaSource mediaSource) {
        Assertions.checkArgument(!this.childSources.containsKey(t));
        this.childSources.put(t, mediaSource);
        mediaSource.prepareSource(this.player, false, new MediaSource.Listener() {
            /* class com.oculus.android.exoplayer2.source.CompositeMediaSource.AnonymousClass1 */

            /* JADX DEBUG: Multi-variable search result rejected for r3v1, resolved type: com.oculus.android.exoplayer2.source.CompositeMediaSource */
            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.oculus.android.exoplayer2.source.MediaSource.Listener
            public void onSourceInfoRefreshed(MediaSource mediaSource, Timeline timeline, @Nullable Object obj) {
                CompositeMediaSource.this.onChildSourceInfoRefreshed(t, mediaSource, timeline, obj);
            }
        });
    }

    /* access modifiers changed from: protected */
    public void releaseChildSource(@Nullable T t) {
        this.childSources.remove(t).releaseSource();
    }
}
