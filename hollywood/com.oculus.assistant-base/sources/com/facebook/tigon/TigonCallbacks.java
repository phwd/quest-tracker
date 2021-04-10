package com.facebook.tigon;

import X.AbstractC0234Ls;
import X.C0233Lr;
import com.facebook.tigon.iface.TigonRequest;
import java.nio.ByteBuffer;

public interface TigonCallbacks {
    void onBody(ByteBuffer byteBuffer);

    void onEOM(AbstractC0234Ls ls);

    void onError(TigonError tigonError, AbstractC0234Ls ls);

    void onResponse(C0233Lr lr);

    void onStarted(TigonRequest tigonRequest);

    void onUploadProgress(long j, long j2);

    void onWillRetry(TigonError tigonError, AbstractC0234Ls ls);
}
