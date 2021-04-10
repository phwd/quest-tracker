package com.facebook.acra.util;

import com.facebook.infer.annotation.Nullsafe;
import java.io.InputStream;

@Nullsafe(Nullsafe.Mode.LOCAL)
public final class InputStreamField {
    public InputStream mInputStream;
    long mLength;
    boolean mSendAsAFile;
    boolean mSendCompressed = true;

    public InputStreamField(InputStream inputStream, boolean z, boolean z2, long j) {
        this.mInputStream = inputStream;
        this.mSendAsAFile = z2;
        this.mLength = j;
    }
}
