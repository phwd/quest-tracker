package com.oculus.video;

import com.oculus.android.exoplayer2.LoadControl;
import com.oculus.android.exoplayer2.Renderer;
import com.oculus.android.exoplayer2.source.TrackGroupArray;
import com.oculus.android.exoplayer2.trackselection.TrackSelectionArray;
import com.oculus.android.exoplayer2.upstream.Allocator;
import com.oculus.android.exoplayer2.upstream.DefaultAllocator;
import com.oculus.android.exoplayer2.util.PriorityTaskManager;
import com.oculus.android.exoplayer2.util.Util;

public final class OculusLoadControl implements LoadControl {
    private static final int ABOVE_HIGH_WATERMARK = 0;
    private static final int BELOW_LOW_WATERMARK = 2;
    private static final int BETWEEN_WATERMARKS = 1;
    public static final int BUFFER_FOR_PLAYBACK_AFTER_REBUFFER_MS = 5000;
    public static final int BUFFER_FOR_PLAYBACK_MS = 2500;
    public static final int MAX_BUFFER_MS = 60000;
    public static final int MIN_BUFFER_MS = 30000;
    private final DefaultAllocator allocator;
    private final long bufferForPlaybackAfterRebufferUs;
    private final long bufferForPlaybackUs;
    private boolean isBuffering;
    private final long maxBufferUs;
    private final long minBufferUs;
    private final PriorityTaskManager priorityTaskManager;
    private int targetBufferSize;

    @Override // com.oculus.android.exoplayer2.LoadControl
    public long getBackBufferDurationUs() {
        return 0;
    }

    @Override // com.oculus.android.exoplayer2.LoadControl
    public boolean retainBackBufferFromKeyframe() {
        return false;
    }

    public OculusLoadControl() {
        this(new DefaultAllocator(true, 65536));
    }

    public OculusLoadControl(DefaultAllocator defaultAllocator) {
        this(defaultAllocator, 30000, MAX_BUFFER_MS, 2500, 5000);
    }

    public OculusLoadControl(DefaultAllocator defaultAllocator, int i, int i2, long j, long j2) {
        this(defaultAllocator, i, i2, j, j2, null);
    }

    public OculusLoadControl(DefaultAllocator defaultAllocator, int i, int i2, long j, long j2, PriorityTaskManager priorityTaskManager2) {
        this.allocator = defaultAllocator;
        this.minBufferUs = ((long) i) * 1000;
        this.maxBufferUs = ((long) i2) * 1000;
        this.bufferForPlaybackUs = j * 1000;
        this.bufferForPlaybackAfterRebufferUs = j2 * 1000;
        this.priorityTaskManager = priorityTaskManager2;
    }

    @Override // com.oculus.android.exoplayer2.LoadControl
    public void onPrepared() {
        reset(false);
    }

    @Override // com.oculus.android.exoplayer2.LoadControl
    public void onTracksSelected(Renderer[] rendererArr, TrackGroupArray trackGroupArray, TrackSelectionArray trackSelectionArray) {
        this.targetBufferSize = 0;
        for (int i = 0; i < rendererArr.length; i++) {
            if (trackSelectionArray.get(i) != null) {
                int trackType = rendererArr[i].getTrackType();
                int defaultBufferSize = Util.getDefaultBufferSize(trackType);
                if (trackType == 1 || trackType == 2) {
                    defaultBufferSize *= 6;
                }
                this.targetBufferSize += defaultBufferSize;
            }
        }
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
        int bufferTimeState = getBufferTimeState(j);
        boolean z2 = true;
        boolean z3 = this.allocator.getTotalBytesAllocated() >= this.targetBufferSize;
        boolean z4 = this.isBuffering;
        if (z3 || (bufferTimeState != 2 && (bufferTimeState != 1 || !z4))) {
            z2 = false;
        }
        this.isBuffering = z2;
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
        long j2 = z ? this.bufferForPlaybackAfterRebufferUs : this.bufferForPlaybackUs;
        return j2 <= 0 || j >= j2;
    }

    private int getBufferTimeState(long j) {
        if (j > this.maxBufferUs) {
            return 0;
        }
        return j < this.minBufferUs ? 2 : 1;
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
