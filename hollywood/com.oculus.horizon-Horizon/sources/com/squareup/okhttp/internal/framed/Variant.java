package com.squareup.okhttp.internal.framed;

import X.AnonymousClass0Lw;
import X.AnonymousClass0Lx;
import com.squareup.okhttp.Protocol;

public interface Variant {
    Protocol getProtocol();

    FrameReader newReader(AnonymousClass0Lw v, boolean z);

    FrameWriter newWriter(AnonymousClass0Lx v, boolean z);
}
