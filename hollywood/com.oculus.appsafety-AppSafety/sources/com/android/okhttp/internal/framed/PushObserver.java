package com.android.okhttp.internal.framed;

import com.android.okhttp.okio.BufferedSource;
import java.io.IOException;
import java.util.List;

public interface PushObserver {
    public static final PushObserver CANCEL = new PushObserver() {
        /* class com.android.okhttp.internal.framed.PushObserver.AnonymousClass1 */

        @Override // com.android.okhttp.internal.framed.PushObserver
        public boolean onRequest(int streamId, List<Header> list) {
            return true;
        }

        @Override // com.android.okhttp.internal.framed.PushObserver
        public boolean onHeaders(int streamId, List<Header> list, boolean last) {
            return true;
        }

        @Override // com.android.okhttp.internal.framed.PushObserver
        public boolean onData(int streamId, BufferedSource source, int byteCount, boolean last) throws IOException {
            source.skip((long) byteCount);
            return true;
        }

        @Override // com.android.okhttp.internal.framed.PushObserver
        public void onReset(int streamId, ErrorCode errorCode) {
        }
    };

    boolean onData(int i, BufferedSource bufferedSource, int i2, boolean z) throws IOException;

    boolean onHeaders(int i, List<Header> list, boolean z);

    boolean onRequest(int i, List<Header> list);

    void onReset(int i, ErrorCode errorCode);
}
