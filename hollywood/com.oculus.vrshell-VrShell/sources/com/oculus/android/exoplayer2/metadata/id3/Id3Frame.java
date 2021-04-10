package com.oculus.android.exoplayer2.metadata.id3;

import com.oculus.android.exoplayer2.metadata.Metadata;
import com.oculus.android.exoplayer2.util.Assertions;

public abstract class Id3Frame implements Metadata.Entry {
    public final String id;

    public int describeContents() {
        return 0;
    }

    public Id3Frame(String str) {
        this.id = (String) Assertions.checkNotNull(str);
    }
}
