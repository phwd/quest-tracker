package com.squareup.okhttp.internal.framed;

import X.Dp;
import X.Du;
import com.squareup.okhttp.Protocol;

public interface Variant {
    Protocol getProtocol();

    FrameReader newReader(Dp dp, boolean z);

    FrameWriter newWriter(Du du, boolean z);
}
