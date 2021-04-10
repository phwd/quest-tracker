package com.squareup.okhttp.internal.framed;

import X.KC;
import X.KJ;
import com.squareup.okhttp.Protocol;

public interface Variant {
    Protocol getProtocol();

    FrameReader newReader(KC kc, boolean z);

    FrameWriter newWriter(KJ kj, boolean z);
}
