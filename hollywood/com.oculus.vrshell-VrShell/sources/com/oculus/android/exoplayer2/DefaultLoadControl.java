package com.oculus.android.exoplayer2;

import com.oculus.android.exoplayer2.source.TrackGroupArray;
import com.oculus.android.exoplayer2.trackselection.TrackSelectionArray;
import com.oculus.android.exoplayer2.upstream.Allocator;
import com.oculus.android.exoplayer2.upstream.DefaultAllocator;
import com.oculus.android.exoplayer2.util.PriorityTaskManager;
import com.oculus.android.exoplayer2.util.Util;

public class DefaultLoadControl implements LoadControl {
    public static final int DEFAULT_BUFFER_FOR_PLAYBACK_AFTER_REBUFFER_MS = 5000;
    public static final int DEFAULT_BUFFER_FOR_PLAYBACK_MS = 2500;
    public static final int DEFAULT_MAX_BUFFER_MS = 30000;
    public static final int DEFAULT_MIN_BUFFER_MS = 15000;
    public static final boolean DEFAULT_PRIORITIZE_TIME_OVER_SIZE_THRESHOLDS = true;
    public static final int DEFAULT_TARGET_BUFFER_BYTES = -1;
    private final DefaultAllocator allocator;
    private final long bufferForPlaybackAfterRebufferUs;
    private final long bufferForPlaybackUs;
    private boolean isBuffering;
    private final long maxBufferUs;
    private final long minBufferUs;
    private final boolean prioritizeTimeOverSizeThresholds;
    private final PriorityTaskManager priorityTaskManager;
    private final int targetBufferBytesOverwrite;
    private int targetBufferSize;

    @Override // com.oculus.android.exoplayer2.LoadControl
    public long getBackBufferDurationUs() {
        return 0;
    }

    @Override // com.oculus.android.exoplayer2.LoadControl
    public boolean retainBackBufferFromKeyframe() {
        return false;
    }

    public DefaultLoadControl() {
        this(new DefaultAllocator(true, 65536));
    }

    public DefaultLoadControl(DefaultAllocator defaultAllocator) {
        this(defaultAllocator, DEFAULT_MIN_BUFFER_MS, 30000, 2500, 5000, -1, true);
    }

    public DefaultLoadControl(DefaultAllocator defaultAllocator, int i, int i2, int i3, int i4, int i5, boolean z) {
        this(defaultAllocator, i, i2, i3, i4, i5, z, null);
    }

    public DefaultLoadControl(DefaultAllocator defaultAllocator, int i, int i2, int i3, int i4, int i5, boolean z, PriorityTaskManager priorityTaskManager2) {
        this.allocator = defaultAllocator;
        this.minBufferUs = ((long) i) * 1000;
        this.maxBufferUs = ((long) i2) * 1000;
        this.bufferForPlaybackUs = ((long) i3) * 1000;
        this.bufferForPlaybackAfterRebufferUs = ((long) i4) * 1000;
        this.targetBufferBytesOverwrite = i5;
        this.prioritizeTimeOverSizeThresholds = z;
        this.priorityTaskManager = priorityTaskManager2;
    }

    @Override // com.oculus.android.exoplayer2.LoadControl
    public void onPrepared() {
        reset(false);
    }

    @Override // com.oculus.android.exoplayer2.LoadControl
    public void onTracksSelected(Renderer[] rendererArr, TrackGroupArray trackGroupArray, TrackSelectionArray trackSelectionArray) {
        int i = this.targetBufferBytesOverwrite;
        if (i == -1) {
            i = calculateTargetBufferSize(rendererArr, trackSelectionArray);
        }
        this.targetBufferSize = i;
        this.allocator.setTargetBufferSize(this.targetBufferSize);
    }

    @Override // com.oculus.android.exoplayer2.LoadControl
    public void onStopped() {
        reset(true);
    }

    @Override // com.oculus.android.exoplayer2.LoadControl
    public void onReleased() {
        reset(true);
    }

    @Override // com.oculus.android.exoplayer2.LoadControl
    public Allocator getAllocator() {
        return this.allocator;
    }

    @Override // com.oculus.android.exoplayer2.LoadControl
    public boolean shouldContinueLoading(long j, float f) {
        boolean z;
        boolean z2 = true;
        boolean z3 = this.allocator.getTotalBytesAllocated() >= this.targetBufferSize;
        boolean z4 = this.isBuffering;
        if (this.prioritizeTimeOverSizeThresholds) {
            if (j >= this.minBufferUs && (j > this.maxBufferUs || !z4 || z3)) {
                z2 = false;
            }
            this.isBuffering = z2;
        } else {
            if (z3 || (j >= this.minBufferUs && (j > this.maxBufferUs || !z4))) {
                z2 = false;
            }
            this.isBuffering = z2;
        }
        PriorityTaskManager priorityTaskManager2 = this.priorityTaskManager;
        if (!(priorityTaskManager2 == null || (z = this.isBuffering) == z4)) {
            if (z) {
                priorityTaskManager2.add(0);
            } else {
                priorityTaskManager2.remove(0);
            }
        }
        return this.isBuffering;
    }

    @Override // com.oculus.android.exoplayer2.LoadControl
    public boolean shouldStartPlayback(long j, float f, boolean z) {
        long playoutDurationForMediaDuration = Util.getPlayoutDurationForMediaDuration(j, f);
        long j2 = z ? this.bufferForPlaybackAfterRebufferUs : this.bufferForPlaybackUs;
        return j2 <= 0 || playoutDurationForMediaDuration >= j2 || (!this.prioritizeTimeOverSizeThresholds && this.allocator.getTotalBytesAllocated() >= this.targetBufferSize);
    }

    /* access modifiers changed from: protected */
    public int calculateTargetBufferSize(Renderer[] rendererArr, TrackSelectionArray trackSelectionArray) {
        int i = 0;
        for (int i2 = 0; i2 < rendererArr.length; i2++) {
            if (trackSelectionArray.get(i2) != null) {
                i += Util.getDefaultBufferSize(rendererArr[i2].getTrackType());
            }
        }
        return i;
    }

    private void reset(boolean z) {
        this.targetBufferSize = 0;
        PriorityTaskManager priorityTaskManager2 = this.priorityTaskManager;
        if (priorityTaskManager2 != null && this.isBuffering) {
            priorityTaskManager2.remove(0);
        }
        this.isBuffering = false;
        if (z) {
            this.allocator.reset();
        }
    }
}
