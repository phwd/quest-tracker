package com.oculus.android.exoplayer2.drm;

import android.annotation.TargetApi;
import android.os.Looper;
import com.oculus.android.exoplayer2.drm.ExoMediaCrypto;

@TargetApi(16)
public interface DrmSessionManager<T extends ExoMediaCrypto> {
    DrmSession<T> acquireSession(Looper looper, DrmInitData drmInitData);

    boolean canAcquireSession(DrmInitData drmInitData);

    void releaseSession(DrmSession<T> drmSession);
}
