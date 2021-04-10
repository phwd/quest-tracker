package com.android.org.bouncycastle.asn1;

import java.io.InputStream;

/* access modifiers changed from: package-private */
public abstract class LimitedInputStream extends InputStream {
    protected final InputStream _in;
    private int _limit;

    LimitedInputStream(InputStream in, int limit) {
        this._in = in;
        this._limit = limit;
    }

    /* access modifiers changed from: package-private */
    public int getRemaining() {
        return this._limit;
    }

    /* access modifiers changed from: protected */
    public void setParentEofDetect(boolean on) {
        InputStream inputStream = this._in;
        if (inputStream instanceof IndefiniteLengthInputStream) {
            ((IndefiniteLengthInputStream) inputStream).setEofOn00(on);
        }
    }
}
