package com.squareup.okhttp.internal.framed;

import X.AnonymousClass0Lw;
import X.C07700vD;
import java.io.Closeable;
import java.io.IOException;
import java.util.List;

public interface FrameReader extends Closeable {

    public interface Handler {
        void ackSettings();

        void alternateService(int i, String str, C07700vD v, String str2, int i2, long j);

        void data(boolean z, int i, AnonymousClass0Lw v, int i2) throws IOException;

        void goAway(int i, ErrorCode errorCode, C07700vD v);

        void headers(boolean z, boolean z2, int i, int i2, List<Header> list, HeadersMode headersMode);

        void ping(boolean z, int i, int i2);

        void priority(int i, int i2, int i3, boolean z);

        void pushPromise(int i, int i2, List<Header> list) throws IOException;

        void rstStream(int i, ErrorCode errorCode);

        void settings(boolean z, Settings settings);

        void windowUpdate(int i, long j);
    }

    boolean nextFrame(Handler handler) throws IOException;

    void readConnectionPreface() throws IOException;
}
