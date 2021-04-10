package com.facebook.acra.util;

import com.facebook.infer.annotation.Nullsafe;
import java.io.InputStream;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class InputStreamField {
    private InputStream mInputStream;
    private long mLength;
    private boolean mSendAsAFile;
    private boolean mSendCompressed;

    public InputStreamField(InputStream is, boolean compress, boolean file, long length) {
        this.mInputStream = is;
        this.mSendCompressed = compress;
        this.mSendAsAFile = file;
        this.mLength = length;
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
