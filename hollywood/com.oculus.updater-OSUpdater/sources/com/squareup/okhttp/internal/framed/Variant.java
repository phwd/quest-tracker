package com.squareup.okhttp.internal.framed;

import okio.BufferedSink;
import okio.BufferedSource;

public interface Variant {
    FrameReader newReader(BufferedSource bufferedSource, boolean z);

    FrameWriter newWriter(BufferedSink bufferedSink, boolean z);
}
