package com.squareup.okhttp.internal.framed;

import X.AnonymousClass0Lw;
import java.io.IOException;
import java.util.List;

public interface PushObserver {
    public static final PushObserver CANCEL = new PushObserver() {
        /* class com.squareup.okhttp.internal.framed.PushObserver.AnonymousClass1 */

        @Override // com.squareup.okhttp.internal.framed.PushObserver
        public boolean onData(int i, AnonymousClass0Lw r4, int i2, boolean z) throws IOException {
            r4.A94((long) i2);
            return true;
        }

        @Override // com.squareup.okhttp.internal.framed.PushObserver
        public boolean onHeaders(int i, List<Header> list, boolean z) {
            return true;
        }

        @Override // com.squareup.okhttp.internal.framed.PushObserver
        public boolean onRequest(int i, List<Header> list) {
            return true;
        }

        @Override // com.squareup.okhttp.internal.framed.PushObserver
        public void onReset(int i, ErrorCode errorCode) {
        }
    };

    boolean onData(int i, AnonymousClass0Lw v, int i2, boolean z) throws IOException;

    boolean onHeaders(int i, List<Header> list, boolean z);

    boolean onRequest(int i, List<Header> list);

    void onReset(int i, ErrorCode errorCode);
}
