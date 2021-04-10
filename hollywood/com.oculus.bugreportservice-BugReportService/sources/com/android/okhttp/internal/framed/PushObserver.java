package com.android.okhttp.internal.framed;

import com.android.okhttp.okio.BufferedSource;
import java.util.List;

public interface PushObserver {
    public static final PushObserver CANCEL = new PushObserver() {
        /* class com.android.okhttp.internal.framed.PushObserver.AnonymousClass1 */

        @Override // com.android.okhttp.internal.framed.PushObserver
        public boolean onHeaders(int i, List list, boolean z) {
            return true;
        }

        @Override // com.android.okhttp.internal.framed.PushObserver
        public boolean onRequest(int i, List list) {
            return true;
        }

        @Override // com.android.okhttp.internal.framed.PushObserver
        public void onReset(int i, ErrorCode errorCode) {
        }

        @Override // com.android.okhttp.internal.framed.PushObserver
        public boolean onData(int i, BufferedSource bufferedSource, int i2, boolean z) {
            bufferedSource.skip((long) i2);
            return true;
        }
    };

    boolean onData(int i, BufferedSource bufferedSource, int i2, boolean z);

    boolean onHeaders(int i, List list, boolean z);

    boolean onRequest(int i, List list);

    void onReset(int i, ErrorCode errorCode);
}
