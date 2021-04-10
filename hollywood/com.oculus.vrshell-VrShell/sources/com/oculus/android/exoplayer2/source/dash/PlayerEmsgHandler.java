package com.oculus.android.exoplayer2.source.dash;

import android.os.Handler;
import android.os.Message;
import androidx.annotation.Nullable;
import com.oculus.android.exoplayer2.C;
import com.oculus.android.exoplayer2.Format;
import com.oculus.android.exoplayer2.FormatHolder;
import com.oculus.android.exoplayer2.ParserException;
import com.oculus.android.exoplayer2.extractor.ExtractorInput;
import com.oculus.android.exoplayer2.extractor.TrackOutput;
import com.oculus.android.exoplayer2.metadata.MetadataInputBuffer;
import com.oculus.android.exoplayer2.metadata.emsg.EventMessage;
import com.oculus.android.exoplayer2.metadata.emsg.EventMessageDecoder;
import com.oculus.android.exoplayer2.source.SampleQueue;
import com.oculus.android.exoplayer2.source.chunk.Chunk;
import com.oculus.android.exoplayer2.source.dash.manifest.DashManifest;
import com.oculus.android.exoplayer2.upstream.Allocator;
import com.oculus.android.exoplayer2.util.ParsableByteArray;
import com.oculus.android.exoplayer2.util.Util;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public final class PlayerEmsgHandler implements Handler.Callback {
    private static final int EMSG_MANIFEST_EXPIRED = 2;
    private static final int EMSG_MEDIA_PRESENTATION_ENDED = 1;
    private final Allocator allocator;
    private final EventMessageDecoder decoder = new EventMessageDecoder();
    private boolean dynamicMediaPresentationEnded;
    private long expiredManifestPublishTimeUs;
    private final Handler handler = new Handler(this);
    private boolean isWaitingForManifestRefresh;
    private long lastLoadedChunkEndTimeBeforeRefreshUs = C.TIME_UNSET;
    private long lastLoadedChunkEndTimeUs = C.TIME_UNSET;
    private DashManifest manifest;
    private final TreeMap<Long, Long> manifestPublishTimeToExpiryTimeUs = new TreeMap<>();
    private final PlayerEmsgCallback playerEmsgCallback;
    private boolean released;

    public interface PlayerEmsgCallback {
        void onDashLiveMediaPresentationEndSignalEncountered();

        void onDashManifestPublishTimeExpired(long j);

        void onDashManifestRefreshRequested();
    }

    public PlayerEmsgHandler(DashManifest dashManifest, PlayerEmsgCallback playerEmsgCallback2, Allocator allocator2) {
        this.manifest = dashManifest;
        this.playerEmsgCallback = playerEmsgCallback2;
        this.allocator = allocator2;
    }

    public void updateManifest(DashManifest dashManifest) {
        this.isWaitingForManifestRefresh = false;
        this.expiredManifestPublishTimeUs = C.TIME_UNSET;
        this.manifest = dashManifest;
        removePreviouslyExpiredManifestPublishTimeValues();
    }

    /* access modifiers changed from: package-private */
    public boolean maybeRefreshManifestBeforeLoadingNextChunk(long j) {
        if (!this.manifest.dynamic) {
            return false;
        }
        boolean z = true;
        if (this.isWaitingForManifestRefresh) {
            return true;
        }
        if (!this.dynamicMediaPresentationEnded) {
            Map.Entry<Long, Long> ceilingExpiryEntryForPublishTime = ceilingExpiryEntryForPublishTime(this.manifest.publishTimeMs);
            if (ceilingExpiryEntryForPublishTime == null || ceilingExpiryEntryForPublishTime.getValue().longValue() >= j) {
                z = false;
            } else {
                this.expiredManifestPublishTimeUs = ceilingExpiryEntryForPublishTime.getKey().longValue();
                notifyManifestPublishTimeExpired();
            }
        }
        if (z) {
            maybeNotifyDashManifestRefreshNeeded();
        }
        return z;
    }

    /* access modifiers changed from: package-private */
    public boolean maybeRefreshManifestOnLoadingError(Chunk chunk) {
        if (!this.manifest.dynamic) {
            return false;
        }
        if (this.isWaitingForManifestRefresh) {
            return true;
        }
        long j = this.lastLoadedChunkEndTimeUs;
        if (!(j != C.TIME_UNSET && j < chunk.startTimeUs)) {
            return false;
        }
        maybeNotifyDashManifestRefreshNeeded();
        return true;
    }

    /* access modifiers changed from: package-private */
    public void onChunkLoadCompleted(Chunk chunk) {
        if (this.lastLoadedChunkEndTimeUs != C.TIME_UNSET || chunk.endTimeUs > this.lastLoadedChunkEndTimeUs) {
            this.lastLoadedChunkEndTimeUs = chunk.endTimeUs;
        }
    }

    public static boolean isPlayerEmsgEvent(String str, String str2) {
        return "urn:mpeg:dash:event:2012".equals(str) && ("1".equals(str2) || "2".equals(str2) || "3".equals(str2));
    }

    public PlayerTrackEmsgHandler newPlayerTrackEmsgHandler() {
        return new PlayerTrackEmsgHandler(new SampleQueue(this.allocator));
    }

    public void release() {
        this.released = true;
        this.handler.removeCallbacksAndMessages(null);
    }

    public boolean handleMessage(Message message) {
        if (this.released) {
            return true;
        }
        int i = message.what;
        if (i == 1) {
            handleMediaPresentationEndedMessageEncountered();
            return true;
        } else if (i != 2) {
            return false;
        } else {
            ManifestExpiryEventInfo manifestExpiryEventInfo = (ManifestExpiryEventInfo) message.obj;
            handleManifestExpiredMessage(manifestExpiryEventInfo.eventTimeUs, manifestExpiryEventInfo.manifestPublishTimeMsInEmsg);
            return true;
        }
    }

    private void handleManifestExpiredMessage(long j, long j2) {
        if (!this.manifestPublishTimeToExpiryTimeUs.containsKey(Long.valueOf(j2))) {
            this.manifestPublishTimeToExpiryTimeUs.put(Long.valueOf(j2), Long.valueOf(j));
        } else if (this.manifestPublishTimeToExpiryTimeUs.get(Long.valueOf(j2)).longValue() > j) {
            this.manifestPublishTimeToExpiryTimeUs.put(Long.valueOf(j2), Long.valueOf(j));
        }
    }

    private void handleMediaPresentationEndedMessageEncountered() {
        this.dynamicMediaPresentationEnded = true;
        notifySourceMediaPresentationEnded();
    }

    private Map.Entry<Long, Long> ceilingExpiryEntryForPublishTime(long j) {
        if (this.manifestPublishTimeToExpiryTimeUs.isEmpty()) {
            return null;
        }
        return this.manifestPublishTimeToExpiryTimeUs.ceilingEntry(Long.valueOf(j));
    }

    private void removePreviouslyExpiredManifestPublishTimeValues() {
        Iterator<Map.Entry<Long, Long>> it = this.manifestPublishTimeToExpiryTimeUs.entrySet().iterator();
        while (it.hasNext()) {
            if (it.next().getKey().longValue() < this.manifest.publishTimeMs) {
                it.remove();
            }
        }
    }

    private void notifyManifestPublishTimeExpired() {
        this.playerEmsgCallback.onDashManifestPublishTimeExpired(this.expiredManifestPublishTimeUs);
    }

    private void notifySourceMediaPresentationEnded() {
        this.playerEmsgCallback.onDashLiveMediaPresentationEndSignalEncountered();
    }

    private void maybeNotifyDashManifestRefreshNeeded() {
        long j = this.lastLoadedChunkEndTimeBeforeRefreshUs;
        if (j == C.TIME_UNSET || j != this.lastLoadedChunkEndTimeUs) {
            this.isWaitingForManifestRefresh = true;
            this.lastLoadedChunkEndTimeBeforeRefreshUs = this.lastLoadedChunkEndTimeUs;
            this.playerEmsgCallback.onDashManifestRefreshRequested();
        }
    }

    /* access modifiers changed from: private */
    public static long getManifestPublishTimeMsInEmsg(EventMessage eventMessage) {
        try {
            return Util.parseXsDateTime(new String(eventMessage.messageData));
        } catch (ParserException unused) {
            return C.TIME_UNSET;
        }
    }

    /* access modifiers changed from: private */
    public static boolean isMessageSignalingMediaPresentationEnded(EventMessage eventMessage) {
        return eventMessage.presentationTimeUs == 0 && eventMessage.durationMs == 0;
    }

    public final class PlayerTrackEmsgHandler implements TrackOutput {
        private final MetadataInputBuffer buffer = new MetadataInputBuffer();
        private final FormatHolder formatHolder = new FormatHolder();
        private final SampleQueue sampleQueue;

        PlayerTrackEmsgHandler(SampleQueue sampleQueue2) {
            this.sampleQueue = sampleQueue2;
        }

        @Override // com.oculus.android.exoplayer2.extractor.TrackOutput
        public void format(Format format) {
            this.sampleQueue.format(format);
        }

        @Override // com.oculus.android.exoplayer2.extractor.TrackOutput
        public int sampleData(ExtractorInput extractorInput, int i, boolean z) throws IOException, InterruptedException {
            return this.sampleQueue.sampleData(extractorInput, i, z);
        }

        @Override // com.oculus.android.exoplayer2.extractor.TrackOutput
        public void sampleData(ParsableByteArray parsableByteArray, int i) {
            this.sampleQueue.sampleData(parsableByteArray, i);
        }

        @Override // com.oculus.android.exoplayer2.extractor.TrackOutput
        public void sampleMetadata(long j, int i, int i2, int i3, TrackOutput.CryptoData cryptoData) {
            this.sampleQueue.sampleMetadata(j, i, i2, i3, cryptoData);
            parseAndDiscardSamples();
        }

        public boolean maybeRefreshManifestBeforeLoadingNextChunk(long j) {
            return PlayerEmsgHandler.this.maybeRefreshManifestBeforeLoadingNextChunk(j);
        }

        public void onChunkLoadCompleted(Chunk chunk) {
            PlayerEmsgHandler.this.onChunkLoadCompleted(chunk);
        }

        public boolean maybeRefreshManifestOnLoadingError(Chunk chunk) {
            return PlayerEmsgHandler.this.maybeRefreshManifestOnLoadingError(chunk);
        }

        public void release() {
            this.sampleQueue.reset();
        }

        private void parseAndDiscardSamples() {
            while (this.sampleQueue.hasNextSample()) {
                MetadataInputBuffer dequeueSample = dequeueSample();
                if (dequeueSample != null) {
                    long j = dequeueSample.timeUs;
                    EventMessage eventMessage = (EventMessage) PlayerEmsgHandler.this.decoder.decode(dequeueSample).get(0);
                    if (PlayerEmsgHandler.isPlayerEmsgEvent(eventMessage.schemeIdUri, eventMessage.value)) {
                        parsePlayerEmsgEvent(j, eventMessage);
                    }
                }
            }
            this.sampleQueue.discardToRead();
        }

        @Nullable
        private MetadataInputBuffer dequeueSample() {
            this.buffer.clear();
            if (this.sampleQueue.read(this.formatHolder, this.buffer, false, false, 0) != -4) {
                return null;
            }
            this.buffer.flip();
            return this.buffer;
        }

        private void parsePlayerEmsgEvent(long j, EventMessage eventMessage) {
            long manifestPublishTimeMsInEmsg = PlayerEmsgHandler.getManifestPublishTimeMsInEmsg(eventMessage);
            if (manifestPublishTimeMsInEmsg != C.TIME_UNSET) {
                if (PlayerEmsgHandler.isMessageSignalingMediaPresentationEnded(eventMessage)) {
                    onMediaPresentationEndedMessageEncountered();
                } else {
                    onManifestExpiredMessageEncountered(j, manifestPublishTimeMsInEmsg);
                }
            }
        }

        private void onMediaPresentationEndedMessageEncountered() {
            PlayerEmsgHandler.this.handler.sendMessage(PlayerEmsgHandler.this.handler.obtainMessage(1));
        }

        private void onManifestExpiredMessageEncountered(long j, long j2) {
            PlayerEmsgHandler.this.handler.sendMessage(PlayerEmsgHandler.this.handler.obtainMessage(2, new ManifestExpiryEventInfo(j, j2)));
        }
    }

    /* access modifiers changed from: private */
    public static final class ManifestExpiryEventInfo {
        public final long eventTimeUs;
        public final long manifestPublishTimeMsInEmsg;

        public ManifestExpiryEventInfo(long j, long j2) {
            this.eventTimeUs = j;
            this.manifestPublishTimeMsInEmsg = j2;
        }
    }
}
