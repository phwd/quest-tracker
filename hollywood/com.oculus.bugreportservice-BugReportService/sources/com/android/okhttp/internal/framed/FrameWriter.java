package com.android.okhttp.internal.framed;

import com.android.okhttp.okio.Buffer;
import java.io.Closeable;
import java.util.List;

public interface FrameWriter extends Closeable {
    void ackSettings(Settings settings);

    void connectionPreface();

    void data(boolean z, int i, Buffer buffer, int i2);

    void flush();

    void goAway(int i, ErrorCode errorCode, byte[] bArr);

    int maxDataLength();

    void ping(boolean z, int i, int i2);

    void pushPromise(int i, int i2, List list);

    void rstStream(int i, ErrorCode errorCode);

    void settings(Settings settings);

    void synStream(boolean z, boolean z2, int i, int i2, List list);

    void windowUpdate(int i, long j);
}
