package com.facebook.acra.util;

import com.facebook.infer.annotation.Nullsafe;
import java.io.InputStream;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class InputStreamField {
    private InputStream mInputStream;
    private long mLength;
    private boolean mSendAsAFile;
    private boolean mSendCompressed;

    public InputStreamField(InputStream inputStream, boolean z, boolean z2, long j) {
        this.mInputStream = inputStream;
        this.mSendCompressed = z;
        this.mSendAsAFile = z2;
        this.mLength = j;
    }

    public InputStream getInputStream() {
        return this.mInputStream;
    }

    public boolean getSendCompressed() {
        return this.mSendCompressed;
    }

    public boolean getSendAsFile() {
        return this.mSendAsAFile;
    }

    public long getLength() {
        return this.mLength;
    }
}
