package com.oculus.android.exoplayer2.metadata.scte35;

import com.oculus.android.exoplayer2.metadata.Metadata;

public abstract class SpliceCommand implements Metadata.Entry {
    public int describeContents() {
        return 0;
    }
}
