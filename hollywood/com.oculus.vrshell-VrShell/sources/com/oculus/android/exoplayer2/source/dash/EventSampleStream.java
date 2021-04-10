package com.oculus.android.exoplayer2.source.dash;

import com.oculus.android.exoplayer2.C;
import com.oculus.android.exoplayer2.Format;
import com.oculus.android.exoplayer2.FormatHolder;
import com.oculus.android.exoplayer2.decoder.DecoderInputBuffer;
import com.oculus.android.exoplayer2.metadata.emsg.EventMessageEncoder;
import com.oculus.android.exoplayer2.source.SampleStream;
import com.oculus.android.exoplayer2.source.dash.manifest.EventStream;
import com.oculus.android.exoplayer2.util.Util;
import java.io.IOException;

final class EventSampleStream implements SampleStream {
    private int currentIndex;
    private final EventMessageEncoder eventMessageEncoder = new EventMessageEncoder();
    private EventStream eventStream;
    private boolean eventStreamUpdatable;
    private long[] eventTimesUs;
    private boolean isFormatSentDownstream;
    private long pendingSeekPositionUs = C.TIME_UNSET;
    private final Format upstreamFormat;

    @Override // com.oculus.android.exoplayer2.source.SampleStream
    public boolean isReady() {
        return true;
    }

    @Override // com.oculus.android.exoplayer2.source.SampleStream
    public void maybeThrowError() throws IOException {
    }

    EventSampleStream(EventStream eventStream2, Format format, boolean z) {
        this.upstreamFormat = format;
        updateEventStream(eventStream2, z);
    }

    /* access modifiers changed from: package-private */
    public void updateEventStream(EventStream eventStream2, boolean z) {
        int i = this.currentIndex;
        long j = i == 0 ? -9223372036854775807L : this.eventTimesUs[i - 1];
        this.eventStreamUpdatable = z;
        this.eventStream = eventStream2;
        this.eventTimesUs = eventStream2.presentationTimesUs;
        long j2 = this.pendingSeekPositionUs;
        if (j2 != C.TIME_UNSET) {
            seekToUs(j2);
        } else if (j != C.TIME_UNSET) {
            this.currentIndex = Util.binarySearchCeil(this.eventTimesUs, j, false, false);
        }
    }

    /* access modifiers changed from: package-private */
    public String eventStreamId() {
        return this.eventStream.id();
    }

    @Override // com.oculus.android.exoplayer2.source.SampleStream
    public int readData(FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, boolean z) {
        if (z || !this.isFormatSentDownstream) {
            formatHolder.format = this.upstreamFormat;
            this.isFormatSentDownstream = true;
            return -5;
        }
        int i = this.currentIndex;
        if (i != this.eventTimesUs.length) {
            this.currentIndex = i + 1;
            byte[] encode = this.eventMessageEncoder.encode(this.eventStream.events[i], this.eventStream.timescale);
            if (encode == null) {
                return -3;
            }
            decoderInputBuffer.ensureSpaceForWrite(encode.length);
            decoderInputBuffer.setFlags(1);
            decoderInputBuffer.data.put(encode);
            decoderInputBuffer.timeUs = this.eventTimesUs[i];
            return -4;
        } else if (this.eventStreamUpdatable) {
            return -3;
        } else {
            decoderInputBuffer.setFlags(4);
            return -4;
        }
    }

    @Override // com.oculus.android.exoplayer2.source.SampleStream
    public int skipData(long j) {
        int max = Math.max(this.currentIndex, Util.binarySearchCeil(this.eventTimesUs, j, true, false));
        int i = max - this.currentIndex;
        this.currentIndex = max;
        return i;
    }

    public void seekToUs(long j) {
        boolean z = false;
        this.currentIndex = Util.binarySearchCeil(this.eventTimesUs, j, true, false);
        if (this.eventStreamUpdatable && this.currentIndex == this.eventTimesUs.length) {
            z = true;
        }
        if (!z) {
            j = C.TIME_UNSET;
        }
        this.pendingSeekPositionUs = j;
    }
}
