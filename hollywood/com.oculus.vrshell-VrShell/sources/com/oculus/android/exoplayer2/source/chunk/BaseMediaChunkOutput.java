package com.oculus.android.exoplayer2.source.chunk;

import android.util.Log;
import com.oculus.android.exoplayer2.extractor.DummyTrackOutput;
import com.oculus.android.exoplayer2.extractor.TrackOutput;
import com.oculus.android.exoplayer2.source.SampleQueue;
import com.oculus.android.exoplayer2.source.chunk.ChunkExtractorWrapper;

final class BaseMediaChunkOutput implements ChunkExtractorWrapper.TrackOutputProvider {
    private static final String TAG = "BaseMediaChunkOutput";
    private final SampleQueue[] sampleQueues;
    private final int[] trackTypes;

    public BaseMediaChunkOutput(int[] iArr, SampleQueue[] sampleQueueArr) {
        this.trackTypes = iArr;
        this.sampleQueues = sampleQueueArr;
    }

    @Override // com.oculus.android.exoplayer2.source.chunk.ChunkExtractorWrapper.TrackOutputProvider
    public TrackOutput track(int i, int i2) {
        int i3 = 0;
        while (true) {
            int[] iArr = this.trackTypes;
            if (i3 >= iArr.length) {
                Log.e(TAG, "Unmatched track of type: " + i2);
                return new DummyTrackOutput();
            } else if (i2 == iArr[i3]) {
                return this.sampleQueues[i3];
            } else {
                i3++;
            }
        }
    }

    public int[] getWriteIndices() {
        int[] iArr = new int[this.sampleQueues.length];
        int i = 0;
        while (true) {
            SampleQueue[] sampleQueueArr = this.sampleQueues;
            if (i >= sampleQueueArr.length) {
                return iArr;
            }
            if (sampleQueueArr[i] != null) {
                iArr[i] = sampleQueueArr[i].getWriteIndex();
            }
            i++;
        }
    }

    public void setSampleOffsetUs(long j) {
        SampleQueue[] sampleQueueArr = this.sampleQueues;
        for (SampleQueue sampleQueue : sampleQueueArr) {
            if (sampleQueue != null) {
                sampleQueue.setSampleOffsetUs(j);
            }
        }
    }
}
