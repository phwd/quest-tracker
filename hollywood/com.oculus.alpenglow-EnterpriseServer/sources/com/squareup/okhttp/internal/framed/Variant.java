package com.squareup.okhttp.internal.framed;

import X.AnonymousClass0Od;
import X.AnonymousClass0Oe;
import com.squareup.okhttp.Protocol;

public interface Variant {
    Protocol getProtocol();

    FrameReader newReader(AnonymousClass0Od v, boolean z);

    FrameWriter newWriter(AnonymousClass0Oe v, boolean z);
}
