package com.oculus.android.exoplayer2.drm;

import com.oculus.android.exoplayer2.drm.DrmSession;
import com.oculus.android.exoplayer2.drm.ExoMediaCrypto;
import com.oculus.android.exoplayer2.util.Assertions;
import java.util.Map;

final class ErrorStateDrmSession<T extends ExoMediaCrypto> implements DrmSession<T> {
    private final DrmSession.DrmSessionException error;

    @Override // com.oculus.android.exoplayer2.drm.DrmSession
    public T getMediaCrypto() {
        return null;
    }

    @Override // com.oculus.android.exoplayer2.drm.DrmSession
    public byte[] getOfflineLicenseKeySetId() {
        return null;
    }

    @Override // com.oculus.android.exoplayer2.drm.DrmSession
    public int getState() {
        return 1;
    }

    @Override // com.oculus.android.exoplayer2.drm.DrmSession
    public Map<String, String> queryKeyStatus() {
        return null;
    }

    public ErrorStateDrmSession(DrmSession.DrmSessionException drmSessionException) {
        this.error = (DrmSession.DrmSessionException) Assertions.checkNotNull(drmSessionException);
    }

    @Override // com.oculus.android.exoplayer2.drm.DrmSession
    public DrmSession.DrmSessionException getError() {
        return this.error;
    }
}
