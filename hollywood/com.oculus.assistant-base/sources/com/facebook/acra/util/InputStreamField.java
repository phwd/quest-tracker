package com.facebook.acra.util;

import java.io.InputStream;

public class InputStreamField {
    public InputStream mInputStream;
    public long mLength;
    public boolean mSendAsAFile;
    public boolean mSendCompressed;

    public InputStreamField(InputStream inputStream, boolean z, boolean z2, long j) {
        this.mInputStream = inputStream;
        this.mSendCompressed = z;
        this.mSendAsAFile = z2;
        this.mLength = j;
    }

    public InputStream getInputStream() {
        return this.mInputStream;
    }

    public long getLength() {
        return this.mLength;
    }

    public boolean getSendAsFile() {
        return this.mSendAsAFile;
    }

    public boolean getSendCompressed() {
        return this.mSendCompressed;
    }
}
