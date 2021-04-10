package com.facebook.common.iolite;

import com.facebook.infer.annotation.Nullsafe;
import java.io.IOException;
import java.io.OutputStream;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class ProgressOutputStream extends FbFilterOutputStream {
    private final Listener mListener;
    private long mOffset = 0;

    public interface Listener {
        void onProgress(long j, long j2);
    }

    public ProgressOutputStream(OutputStream outputStream, Listener listener) {
        super(outputStream);
        this.mListener = listener;
    }

    @Override // java.io.OutputStream, com.facebook.common.iolite.FbFilterOutputStream, java.io.FilterOutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.out.write(bArr, i, i2);
        addOffset((long) i2);
    }

    @Override // java.io.OutputStream, java.io.FilterOutputStream
    public void write(int i) throws IOException {
        this.out.write(i);
        addOffset(1);
    }

    private void addOffset(long j) {
        this.mOffset += j;
        this.mListener.onProgress(j, this.mOffset);
    }
}
