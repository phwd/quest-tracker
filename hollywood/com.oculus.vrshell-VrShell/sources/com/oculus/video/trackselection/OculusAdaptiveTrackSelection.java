package com.oculus.video.trackselection;

import com.oculus.android.exoplayer2.C;
import com.oculus.android.exoplayer2.Format;
import com.oculus.android.exoplayer2.source.TrackGroup;
import com.oculus.android.exoplayer2.source.chunk.Chunk;
import com.oculus.android.exoplayer2.source.chunk.MediaChunk;
import com.oculus.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.oculus.android.exoplayer2.trackselection.TrackSelection;
import com.oculus.android.exoplayer2.upstream.BandwidthMeter;
import com.oculus.android.exoplayer2.util.Clock;
import com.oculus.android.exoplayer2.util.Util;
import java.util.List;

public class OculusAdaptiveTrackSelection extends AdaptiveTrackSelection {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int DEFAULT_MAX_DURATION_TO_RETAIN_FAST_ADAPTATION_MS = 3000;
    public static final int DEFAULT_MIN_DURATION_TO_RETAIN_FAST_ADAPTATION_MS = 1000;
    private static final String TAG = "OculusAdaptiveTrackSelection";
    private final Callback callback;
    private long lastQualityEvaluationMs = C.TIME_UNSET;
    private long lastQualitySwitchMs = C.TIME_UNSET;
    private int lastSelectedTrackOnOffsetUpdate = -1;
    private final long minTimeBetweenQualityReevaluationMs;
    private final long minTimeBetweenQualitySwitchMs;
    private int selectedIndexOffset = 0;
    private TrackSelectionOverrideState state;

    public interface Callback {
        long getTrackSelectionOverrideBitrate();

        boolean isTrackSelectionOverrideActive();
    }

    /* access modifiers changed from: private */
    public enum TrackSelectionOverrideState {
        NONE,
        ACTIVE,
        EXITING
    }

    public static final class Factory implements TrackSelection.Factory {
        protected final float bandwidthFraction;
        protected final BandwidthMeter bandwidthMeter;
        protected final float bufferedFractionToLiveEdgeForQualityIncrease;
        protected final Callback callback;
        protected final Clock clock;
        protected final int maxDurationForQualityDecreaseMs;
        protected final int maxInitialBitrate;
        protected final int minDurationForQualityIncreaseMs;
        protected final int minDurationToRetainAfterDiscardMs;
        protected final long minTimeBetweenBufferReevaluationMs;
        protected final long minTimeBetweenQualityReevaluationMs;
        protected final long minTimeBetweenQualitySwitchMs;

        public Factory(BandwidthMeter bandwidthMeter2, int i, int i2, int i3, int i4, float f, float f2, long j, long j2, long j3, Clock clock2, Callback callback2) {
            this.bandwidthMeter = bandwidthMeter2;
            this.maxInitialBitrate = i;
            this.minDurationForQualityIncreaseMs = i2;
            this.maxDurationForQualityDecreaseMs = i3;
            this.minDurationToRetainAfterDiscardMs = i4;
            this.bandwidthFraction = f;
            this.bufferedFractionToLiveEdgeForQualityIncrease = f2;
            this.minTimeBetweenBufferReevaluationMs = j;
            this.minTimeBetweenQualityReevaluationMs = j2;
            this.minTimeBetweenQualitySwitchMs = j3;
            this.clock = clock2;
            this.callback = callback2;
        }

        @Override // com.oculus.android.exoplayer2.trackselection.TrackSelection.Factory
        public OculusAdaptiveTrackSelection createTrackSelection(TrackGroup trackGroup, int... iArr) {
            return new OculusAdaptiveTrackSelection(trackGroup, iArr, this.bandwidthMeter, this.maxInitialBitrate, (long) this.minDurationForQualityIncreaseMs, (long) this.maxDurationForQualityDecreaseMs, (long) this.minDurationToRetainAfterDiscardMs, this.bandwidthFraction, this.bufferedFractionToLiveEdgeForQualityIncrease, this.minTimeBetweenBufferReevaluationMs, this.minTimeBetweenQualityReevaluationMs, this.minTimeBetweenQualitySwitchMs, this.clock, this.callback);
        }
    }

    public OculusAdaptiveTrackSelection(TrackGroup trackGroup, int[] iArr, BandwidthMeter bandwidthMeter, int i, long j, long j2, long j3, float f, float f2, long j4, long j5, long j6, Clock clock, Callback callback2) {
        super(trackGroup, iArr, bandwidthMeter, i, j, j2, j3, f, f2, j4, clock);
        this.minTimeBetweenQualityReevaluationMs = j5;
        this.minTimeBetweenQualitySwitchMs = j6;
        this.callback = callback2;
        this.state = TrackSelectionOverrideState.NONE;
    }

    @Override // com.oculus.android.exoplayer2.trackselection.BaseTrackSelection, com.oculus.android.exoplayer2.trackselection.AdaptiveTrackSelection, com.oculus.android.exoplayer2.trackselection.TrackSelection
    public void enable() {
        this.lastBufferEvaluationMs = C.TIME_UNSET;
        this.lastQualityEvaluationMs = C.TIME_UNSET;
        this.lastQualitySwitchMs = C.TIME_UNSET;
    }

    public void clearSelectedIndexOffset() {
        this.selectedIndexOffset = 0;
        this.lastSelectedTrackOnOffsetUpdate = -1;
    }

    public boolean updateSelectedIndexOffset(Chunk chunk) {
        if (!(chunk instanceof MediaChunk) || chunk.dataSpec.length == -1 || chunk.startTimeUs == C.TIME_UNSET || chunk.endTimeUs == C.TIME_UNSET || this.selectedIndex >= this.length - 1 || this.lastSelectedTrackOnOffsetUpdate == this.selectedIndex) {
            return false;
        }
        float f = (((float) chunk.dataSpec.length) * 8.0f) / (((float) (chunk.endTimeUs - chunk.startTimeUs)) * 1.0E-6f);
        long bitrateEstimate = this.bandwidthMeter.getBitrateEstimate();
        if (f <= ((float) (bitrateEstimate == -1 ? (long) this.maxInitialBitrate : (long) (((float) bitrateEstimate) * this.bandwidthFraction)))) {
            return false;
        }
        this.selectedIndexOffset++;
        this.lastSelectedTrackOnOffsetUpdate = this.selectedIndex;
        return true;
    }

    @Override // com.oculus.android.exoplayer2.trackselection.AdaptiveTrackSelection, com.oculus.android.exoplayer2.trackselection.TrackSelection
    public void updateSelectedTrack(long j, long j2, long j3) {
        long elapsedRealtime = this.clock.elapsedRealtime();
        if (isTrackSelectionOverrideActive()) {
            int i = this.selectedIndex;
            this.selectedIndex = getOverriddenSelectionIndex(elapsedRealtime);
            if (this.selectedIndex != i) {
                this.reason = 2;
                return;
            }
            return;
        }
        long j4 = this.lastQualityEvaluationMs;
        boolean z = false;
        boolean z2 = j4 != C.TIME_UNSET && elapsedRealtime - j4 < this.minTimeBetweenQualityReevaluationMs;
        long j5 = this.lastQualitySwitchMs;
        if (j5 != C.TIME_UNSET && elapsedRealtime - j5 < this.minTimeBetweenQualitySwitchMs) {
            z = true;
        }
        if (this.selectedIndexOffset != 0 || (!z2 && !z)) {
            this.lastQualityEvaluationMs = elapsedRealtime;
            int i2 = this.selectedIndex;
            this.selectedIndex = determineIdealSelectedIndex(elapsedRealtime);
            int i3 = this.selectedIndex;
            int i4 = this.selectedIndexOffset;
            while (true) {
                int i5 = i3 + i4;
                if (i5 < this.length) {
                    if (!isBlacklisted(i5, elapsedRealtime)) {
                        this.selectedIndex = i5;
                        break;
                    }
                    this.selectedIndexOffset++;
                    i3 = this.selectedIndex;
                    i4 = this.selectedIndexOffset;
                } else {
                    break;
                }
            }
            if (this.selectedIndex != i2) {
                if (!isBlacklisted(i2, elapsedRealtime)) {
                    Format format = getFormat(i2);
                    Format format2 = getFormat(this.selectedIndex);
                    if (format2.bitrate > format.bitrate && j2 < minDurationForQualityIncreaseUs(j3)) {
                        this.selectedIndex = i2;
                    } else if (format2.bitrate < format.bitrate && j2 >= this.maxDurationForQualityDecreaseUs) {
                        this.selectedIndex = i2;
                    }
                }
                if (this.selectedIndex != i2) {
                    this.lastQualitySwitchMs = elapsedRealtime;
                    this.reason = 3;
                }
            }
        }
    }

    @Override // com.oculus.android.exoplayer2.trackselection.BaseTrackSelection, com.oculus.android.exoplayer2.trackselection.AdaptiveTrackSelection, com.oculus.android.exoplayer2.trackselection.TrackSelection
    public int evaluateQueueSize(long j, List<? extends MediaChunk> list) {
        if (!isTrackSelectionOverrideActive()) {
            return super.evaluateQueueSize(j, list);
        }
        long elapsedRealtime = this.clock.elapsedRealtime();
        if (list.isEmpty()) {
            maybeUpdateTrackSelectionOverrideState(true);
            return 0;
        }
        int size = list.size();
        if (Util.getPlayoutDurationForMediaDuration(((MediaChunk) list.get(size - 1)).startTimeUs - j, this.playbackSpeed) < 1000) {
            maybeUpdateTrackSelectionOverrideState(true);
            return size;
        }
        Format format = getFormat(getOverriddenSelectionIndex(elapsedRealtime));
        int i = 0;
        while (i < size) {
            MediaChunk mediaChunk = (MediaChunk) list.get(i);
            Format format2 = mediaChunk.trackFormat;
            long playoutDurationForMediaDuration = Util.getPlayoutDurationForMediaDuration(mediaChunk.startTimeUs - j, this.playbackSpeed);
            if ((format2.bitrate != format.bitrate || this.state == TrackSelectionOverrideState.EXITING) && format2.height != -1 && format2.width != -1 && playoutDurationForMediaDuration >= 1000) {
                if (playoutDurationForMediaDuration > 3000) {
                    i = Math.max(0, i - 1);
                }
                maybeUpdateTrackSelectionOverrideState(true);
                return i;
            }
            i++;
        }
        maybeUpdateTrackSelectionOverrideState(true);
        return size;
    }

    private boolean isTrackSelectionOverrideActive() {
        maybeUpdateTrackSelectionOverrideState(false);
        if (this.state != TrackSelectionOverrideState.NONE) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public int getOverriddenSelectionIndex(long j) {
        long trackSelectionOverrideBitrate = this.callback.getTrackSelectionOverrideBitrate();
        int i = 0;
        for (int i2 = 0; i2 < this.length; i2++) {
            if (!isBlacklisted(i2, j)) {
                if (((long) Math.round(((float) getFormat(i2).bitrate) * this.playbackSpeed)) <= trackSelectionOverrideBitrate) {
                    return i2;
                }
                i = i2;
            }
        }
        return i;
    }

    private void maybeUpdateTrackSelectionOverrideState(boolean z) {
        if (this.callback.isTrackSelectionOverrideActive()) {
            this.state = TrackSelectionOverrideState.ACTIVE;
            return;
        }
        int i = AnonymousClass1.$SwitchMap$com$oculus$video$trackselection$OculusAdaptiveTrackSelection$TrackSelectionOverrideState[this.state.ordinal()];
        if (i == 1) {
            this.state = TrackSelectionOverrideState.EXITING;
        } else if (i == 2 && z) {
            this.state = TrackSelectionOverrideState.NONE;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.oculus.video.trackselection.OculusAdaptiveTrackSelection$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$video$trackselection$OculusAdaptiveTrackSelection$TrackSelectionOverrideState = new int[TrackSelectionOverrideState.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|8) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        static {
            /*
                com.oculus.video.trackselection.OculusAdaptiveTrackSelection$TrackSelectionOverrideState[] r0 = com.oculus.video.trackselection.OculusAdaptiveTrackSelection.TrackSelectionOverrideState.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.oculus.video.trackselection.OculusAdaptiveTrackSelection.AnonymousClass1.$SwitchMap$com$oculus$video$trackselection$OculusAdaptiveTrackSelection$TrackSelectionOverrideState = r0
                int[] r0 = com.oculus.video.trackselection.OculusAdaptiveTrackSelection.AnonymousClass1.$SwitchMap$com$oculus$video$trackselection$OculusAdaptiveTrackSelection$TrackSelectionOverrideState     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.oculus.video.trackselection.OculusAdaptiveTrackSelection$TrackSelectionOverrideState r1 = com.oculus.video.trackselection.OculusAdaptiveTrackSelection.TrackSelectionOverrideState.ACTIVE     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = com.oculus.video.trackselection.OculusAdaptiveTrackSelection.AnonymousClass1.$SwitchMap$com$oculus$video$trackselection$OculusAdaptiveTrackSelection$TrackSelectionOverrideState     // Catch:{ NoSuchFieldError -> 0x001f }
                com.oculus.video.trackselection.OculusAdaptiveTrackSelection$TrackSelectionOverrideState r1 = com.oculus.video.trackselection.OculusAdaptiveTrackSelection.TrackSelectionOverrideState.EXITING     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = com.oculus.video.trackselection.OculusAdaptiveTrackSelection.AnonymousClass1.$SwitchMap$com$oculus$video$trackselection$OculusAdaptiveTrackSelection$TrackSelectionOverrideState     // Catch:{ NoSuchFieldError -> 0x002a }
                com.oculus.video.trackselection.OculusAdaptiveTrackSelection$TrackSelectionOverrideState r1 = com.oculus.video.trackselection.OculusAdaptiveTrackSelection.TrackSelectionOverrideState.NONE     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.video.trackselection.OculusAdaptiveTrackSelection.AnonymousClass1.<clinit>():void");
        }
    }
}
