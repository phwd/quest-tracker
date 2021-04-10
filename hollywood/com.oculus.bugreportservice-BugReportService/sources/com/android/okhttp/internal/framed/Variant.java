package com.android.okhttp.internal.framed;

import com.android.okhttp.okio.BufferedSink;
import com.android.okhttp.okio.BufferedSource;

public interface Variant {
    FrameReader newReader(BufferedSource bufferedSource, boolean z);

    FrameWriter newWriter(BufferedSink bufferedSink, boolean z);
}
