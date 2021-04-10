package com.oculus.android.exoplayer2.trackselection;

import com.oculus.android.exoplayer2.C;
import com.oculus.android.exoplayer2.Format;
import com.oculus.android.exoplayer2.source.TrackGroup;
import com.oculus.android.exoplayer2.source.chunk.MediaChunk;
import com.oculus.android.exoplayer2.trackselection.TrackSelection;
import com.oculus.android.exoplayer2.upstream.BandwidthMeter;
import com.oculus.android.exoplayer2.util.Clock;
import com.oculus.android.exoplayer2.util.Util;
import java.util.List;

public class AdaptiveTrackSelection extends BaseTrackSelection {
    public static final float DEFAULT_BANDWIDTH_FRACTION = 0.75f;
    public static final float DEFAULT_BUFFERED_FRACTION_TO_LIVE_EDGE_FOR_QUALITY_INCREASE = 0.75f;
    public static final int DEFAULT_MAX_DURATION_FOR_QUALITY_DECREASE_MS = 25000;
    public static final int DEFAULT_MAX_INITIAL_BITRATE = 800000;
    public static final int DEFAULT_MIN_DURATION_FOR_QUALITY_INCREASE_MS = 10000;
    public static final int DEFAULT_MIN_DURATION_TO_RETAIN_AFTER_DISCARD_MS = 25000;
    public static final long DEFAULT_MIN_TIME_BETWEEN_BUFFER_REEVALUTATION_MS = 2000;
    protected final float bandwidthFraction;
    protected final BandwidthMeter bandwidthMeter;
    private final float bufferedFractionToLiveEdgeForQualityIncrease;
    protected final Clock clock;
    protected long lastBufferEvaluationMs;
    protected final long maxDurationForQualityDecreaseUs;
    protected final int maxInitialBitrate;
    private final long minDurationForQualityIncreaseUs;
    private final long minDurationToRetainAfterDiscardUs;
    private final long minTimeBetweenBufferReevaluationMs;
    protected float playbackSpeed;
    protected int reason;
    protected int selectedIndex;

    @Override // com.oculus.android.exoplayer2.trackselection.TrackSelection
    public Object getSelectionData() {
        return null;
    }

    public static final class Factory implements TrackSelection.Factory {
        private final float bandwidthFraction;
        private final BandwidthMeter bandwidthMeter;
        private final float bufferedFractionToLiveEdgeForQualityIncrease;
        private final Clock clock;
        private final int maxDurationForQualityDecreaseMs;
        private final int maxInitialBitrate;
        private final int minDurationForQualityIncreaseMs;
        private final int minDurationToRetainAfterDiscardMs;
        private final long minTimeBetweenBufferReevaluationMs;

        public Factory(BandwidthMeter bandwidthMeter2) {
            this(bandwidthMeter2, AdaptiveTrackSelection.DEFAULT_MAX_INITIAL_BITRATE, 10000, 25000, 25000, 0.75f, 0.75f, AdaptiveTrackSelection.DEFAULT_MIN_TIME_BETWEEN_BUFFER_REEVALUTATION_MS, Clock.DEFAULT);
        }

        public Factory(BandwidthMeter bandwidthMeter2, int i, int i2, int i3, int i4, float f) {
            this(bandwidthMeter2, i, i2, i3, i4, f, 0.75f, AdaptiveTrackSelection.DEFAULT_MIN_TIME_BETWEEN_BUFFER_REEVALUTATION_MS, Clock.DEFAULT);
        }

        public Factory(BandwidthMeter bandwidthMeter2, int i, int i2, int i3, int i4, float f, float f2, long j, Clock clock2) {
            this.bandwidthMeter = bandwidthMeter2;
            this.maxInitialBitrate = i;
            this.minDurationForQualityIncreaseMs = i2;
            this.maxDurationForQualityDecreaseMs = i3;
            this.minDurationToRetainAfterDiscardMs = i4;
            this.bandwidthFraction = f;
            this.bufferedFractionToLiveEdgeForQualityIncrease = f2;
            this.minTimeBetweenBufferReevaluationMs = j;
            this.clock = clock2;
        }

        @Override // com.oculus.android.exoplayer2.trackselection.TrackSelection.Factory
        public AdaptiveTrackSelection createTrackSelection(TrackGroup trackGroup, int... iArr) {
            return new AdaptiveTrackSelection(trackGroup, iArr, this.bandwidthMeter, this.maxInitialBitrate, (long) this.minDurationForQualityIncreaseMs, (long) this.maxDurationForQualityDecreaseMs, (long) this.minDurationToRetainAfterDiscardMs, this.bandwidthFraction, this.bufferedFractionToLiveEdgeForQualityIncrease, this.minTimeBetweenBufferReevaluationMs, this.clock);
        }
    }

    public AdaptiveTrackSelection(TrackGroup trackGroup, int[] iArr, BandwidthMeter bandwidthMeter2) {
        this(trackGroup, iArr, bandwidthMeter2, DEFAULT_MAX_INITIAL_BITRATE, 10000, 25000, 25000, 0.75f, 0.75f, DEFAULT_MIN_TIME_BETWEEN_BUFFER_REEVALUTATION_MS, Clock.DEFAULT);
    }

    public AdaptiveTrackSelection(TrackGroup trackGroup, int[] iArr, BandwidthMeter bandwidthMeter2, int i, long j, long j2, long j3, float f, float f2, long j4, Clock clock2) {
        super(trackGroup, iArr);
        this.bandwidthMeter = bandwidthMeter2;
        this.maxInitialBitrate = i;
        this.minDurationForQualityIncreaseUs = j * 1000;
        this.maxDurationForQualityDecreaseUs = j2 * 1000;
        this.minDurationToRetainAfterDiscardUs = j3 * 1000;
        this.bandwidthFraction = f;
        this.bufferedFractionToLiveEdgeForQualityIncrease = f2;
        this.minTimeBetweenBufferReevaluationMs = j4;
        this.clock = clock2;
        this.playbackSpeed = 1.0f;
        this.selectedIndex = determineIdealSelectedIndex(Long.MIN_VALUE);
        this.reason = 1;
        this.lastBufferEvaluationMs = C.TIME_UNSET;
    }

    @Override // com.oculus.android.exoplayer2.trackselection.BaseTrackSelection, com.oculus.android.exoplayer2.trackselection.TrackSelection
    public void enable() {
        this.lastBufferEvaluationMs = C.TIME_UNSET;
    }

    @Override // com.oculus.android.exoplayer2.trackselection.BaseTrackSelection, com.oculus.android.exoplayer2.trackselection.TrackSelection
    public void onPlaybackSpeed(float f) {
        this.playbackSpeed = f;
    }

    @Override // com.oculus.android.exoplayer2.trackselection.TrackSelection
    public void updateSelectedTrack(long j, long j2, long j3) {
        long elapsedRealtime = this.clock.elapsedRealtime();
        int i = this.selectedIndex;
        this.selectedIndex = determineIdealSelectedIndex(elapsedRealtime);
        if (this.selectedIndex != i) {
            if (!isBlacklisted(i, elapsedRealtime)) {
                Format format = getFormat(i);
                Format format2 = getFormat(this.selectedIndex);
                if (format2.bitrate > format.bitrate && j2 < minDurationForQualityIncreaseUs(j3)) {
                    this.selectedIndex = i;
                } else if (format2.bitrate < format.bitrate && j2 >= this.maxDurationForQualityDecreaseUs) {
                    this.selectedIndex = i;
                }
            }
            if (this.selectedIndex != i) {
                this.reason = 3;
            }
        }
    }

    @Override // com.oculus.android.exoplayer2.trackselection.TrackSelection
    public int getSelectedIndex() {
        return this.selectedIndex;
    }

    @Override // com.oculus.android.exoplayer2.trackselection.TrackSelection
    public int getSelectionReason() {
        return this.reason;
    }

    @Override // com.oculus.android.exoplayer2.trackselection.BaseTrackSelection, com.oculus.android.exoplayer2.trackselection.TrackSelection
    public int evaluateQueueSize(long j, List<? extends MediaChunk> list) {
        long elapsedRealtime = this.clock.elapsedRealtime();
        long j2 = this.lastBufferEvaluationMs;
        if (j2 != C.TIME_UNSET && elapsedRealtime - j2 < this.minTimeBetweenBufferReevaluationMs) {
            return list.size();
        }
        this.lastBufferEvaluationMs = elapsedRealtime;
        if (list.isEmpty()) {
            return 0;
        }
        int size = list.size();
        if (Util.getPlayoutDurationForMediaDuration(((MediaChunk) list.get(size - 1)).startTimeUs - j, this.playbackSpeed) < this.minDurationToRetainAfterDiscardUs) {
            return size;
        }
        Format format = getFormat(determineIdealSelectedIndex(elapsedRealtime));
        for (int i = 0; i < size; i++) {
            MediaChunk mediaChunk = (MediaChunk) list.get(i);
            Format format2 = mediaChunk.trackFormat;
            if (Util.getPlayoutDurationForMediaDuration(mediaChunk.startTimeUs - j, this.playbackSpeed) >= this.minDurationToRetainAfterDiscardUs && format2.bitrate < format.bitrate && format2.height != -1 && format2.height < 720 && format2.width != -1 && format2.width < 1280 && format2.height < format.height) {
                return i;
            }
        }
        return size;
    }

    /* access modifiers changed from: protected */
    public int determineIdealSelectedIndex(long j) {
        long bitrateEstimate = this.bandwidthMeter.getBitrateEstimate();
        long j2 = bitrateEstimate == -1 ? (long) this.maxInitialBitrate : (long) (((float) bitrateEstimate) * this.bandwidthFraction);
        int i = 0;
        for (int i2 = 0; i2 < this.length; i2++) {
            if (j == Long.MIN_VALUE || !isBlacklisted(i2, j)) {
                if (((long) Math.round(((float) getFormat(i2).bitrate) * this.playbackSpeed)) <= j2) {
                    return i2;
                }
                i = i2;
            }
        }
        return i;
    }

    /* access modifiers changed from: protected */
    public long minDurationForQualityIncreaseUs(long j) {
        return (j > C.TIME_UNSET ? 1 : (j == C.TIME_UNSET ? 0 : -1)) != 0 && (j > this.minDurationForQualityIncreaseUs ? 1 : (j == this.minDurationForQualityIncreaseUs ? 0 : -1)) <= 0 ? (long) (((float) j) * this.bufferedFractionToLiveEdgeForQualityIncrease) : this.minDurationForQualityIncreaseUs;
    }
}
