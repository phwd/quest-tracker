package com.oculus.android.exoplayer2.drm;

import com.oculus.android.exoplayer2.drm.ExoMediaDrm;
import java.util.UUID;

public interface MediaDrmCallback {
    byte[] executeKeyRequest(UUID uuid, ExoMediaDrm.KeyRequest keyRequest) throws Exception;

    byte[] executeProvisionRequest(UUID uuid, ExoMediaDrm.ProvisionRequest provisionRequest) throws Exception;
}
