package com.oculus.android.exoplayer2.upstream;

import android.os.Handler;
import android.support.v4.media.session.PlaybackStateCompat;
import com.oculus.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.oculus.android.exoplayer2.upstream.BandwidthMeter;
import com.oculus.android.exoplayer2.util.Assertions;
import com.oculus.android.exoplayer2.util.Clock;
import com.oculus.android.exoplayer2.util.SlidingPercentile;

public final class DefaultBandwidthMeter implements BandwidthMeter, TransferListener<Object> {
    private static final int BYTES_TRANSFERRED_FOR_ESTIMATE = 524288;
    public static final int DEFAULT_MAX_WEIGHT = 2000;
    private static final int ELAPSED_MILLIS_FOR_ESTIMATE = 2000;
    private long bitrateEstimate;
    private final Clock clock;
    private final Handler eventHandler;
    private final BandwidthMeter.EventListener eventListener;
    private long sampleBytesTransferred;
    private long sampleStartTimeMs;
    private final SlidingPercentile slidingPercentile;
    private int streamCount;
    private long totalBytesTransferred;
    private long totalElapsedTimeMs;

    public DefaultBandwidthMeter() {
        this(null, null);
    }

    public DefaultBandwidthMeter(Handler handler, BandwidthMeter.EventListener eventListener2) {
        this(handler, eventListener2, 2000);
    }

    public DefaultBandwidthMeter(Handler handler, BandwidthMeter.EventListener eventListener2, int i) {
        this(handler, eventListener2, i, Clock.DEFAULT);
    }

    public DefaultBandwidthMeter(Handler handler, BandwidthMeter.EventListener eventListener2, int i, Clock clock2) {
        this.eventHandler = handler;
        this.eventListener = eventListener2;
        this.slidingPercentile = new SlidingPercentile(i);
        this.clock = clock2;
        this.bitrateEstimate = -1;
    }

    @Override // com.oculus.android.exoplayer2.upstream.BandwidthMeter
    public synchronized long getBitrateEstimate() {
        return this.bitrateEstimate;
    }

    @Override // com.oculus.android.exoplayer2.upstream.TransferListener
    public synchronized void onTransferStart(Object obj, DataSpec dataSpec) {
        if (this.streamCount == 0) {
            this.sampleStartTimeMs = this.clock.elapsedRealtime();
        }
        this.streamCount++;
    }

    @Override // com.oculus.android.exoplayer2.upstream.TransferListener
    public synchronized void onBytesTransferred(Object obj, int i) {
        this.sampleBytesTransferred += (long) i;
    }

    @Override // com.oculus.android.exoplayer2.upstream.TransferListener
    public synchronized void onTransferEnd(Object obj) {
        Assertions.checkState(this.streamCount > 0);
        long elapsedRealtime = this.clock.elapsedRealtime();
        int i = (int) (elapsedRealtime - this.sampleStartTimeMs);
        long j = (long) i;
        this.totalElapsedTimeMs += j;
        this.totalBytesTransferred += this.sampleBytesTransferred;
        if (i > 0) {
            this.slidingPercentile.addSample((int) Math.sqrt((double) this.sampleBytesTransferred), (float) ((this.sampleBytesTransferred * 8000) / j));
            if (this.totalElapsedTimeMs >= AdaptiveTrackSelection.DEFAULT_MIN_TIME_BETWEEN_BUFFER_REEVALUTATION_MS || this.totalBytesTransferred >= PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED) {
                float percentile = this.slidingPercentile.getPercentile(0.5f);
                this.bitrateEstimate = Float.isNaN(percentile) ? -1 : (long) percentile;
            }
        }
        notifyBandwidthSample(i, this.sampleBytesTransferred, this.bitrateEstimate);
        int i2 = this.streamCount - 1;
        this.streamCount = i2;
        if (i2 > 0) {
            this.sampleStartTimeMs = elapsedRealtime;
        }
        this.sampleBytesTransferred = 0;
    }

    private void notifyBandwidthSample(final int i, final long j, final long j2) {
        Handler handler = this.eventHandler;
        if (handler != null && this.eventListener != null) {
            handler.post(new Runnable() {
                /* class com.oculus.android.exoplayer2.upstream.DefaultBandwidthMeter.AnonymousClass1 */

                public void run() {
                    DefaultBandwidthMeter.this.eventListener.onBandwidthSample(i, j, j2);
                }
            });
        }
    }
}
