package com.oculus.video.drm;

import android.text.TextUtils;
import android.util.Base64;
import com.oculus.android.exoplayer2.C;
import com.oculus.android.exoplayer2.drm.ExoMediaDrm;
import com.oculus.android.exoplayer2.drm.HttpMediaDrmCallback;
import com.oculus.android.exoplayer2.upstream.HttpDataSource;
import com.oculus.android.exoplayer2.util.Assertions;
import java.util.HashMap;
import java.util.UUID;
import org.json.JSONObject;

public class OculusHttpMediaDrmCallback extends HttpMediaDrmCallback {
    private DrmProxyResponseFormat drmResponseFormat;

    public OculusHttpMediaDrmCallback(String str, HttpDataSource.Factory factory, DrmProxyResponseFormat drmProxyResponseFormat) {
        super(str, false, factory);
        this.drmResponseFormat = drmProxyResponseFormat;
    }

    @Override // com.oculus.android.exoplayer2.drm.HttpMediaDrmCallback, com.oculus.android.exoplayer2.drm.MediaDrmCallback
    public byte[] executeKeyRequest(UUID uuid, ExoMediaDrm.KeyRequest keyRequest) throws Exception {
        String defaultUrl = keyRequest.getDefaultUrl();
        if (this.forceDefaultLicenseUrl || TextUtils.isEmpty(defaultUrl)) {
            defaultUrl = this.defaultLicenseUrl;
        }
        int i = AnonymousClass1.$SwitchMap$com$oculus$video$drm$DrmProxyResponseFormat[this.drmResponseFormat.ordinal()];
        if (i == 1) {
            Assertions.checkState(C.WIDEVINE_UUID.equals(uuid), "Unexpected uuid.");
            return Base64.decode(new JSONObject(getResponseString(defaultUrl, keyRequest)).getString("license"), 0);
        } else if (i != 2) {
            return super.executeKeyRequest(uuid, keyRequest);
        } else {
            Assertions.checkState(C.WIDEVINE_UUID.equals(uuid), "Unexpected uuid.");
            return Base64.decode(getResponseString(defaultUrl, keyRequest), 0);
        }
    }

    /* renamed from: com.oculus.video.drm.OculusHttpMediaDrmCallback$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$video$drm$DrmProxyResponseFormat = new int[DrmProxyResponseFormat.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|8) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        static {
            /*
                com.oculus.video.drm.DrmProxyResponseFormat[] r0 = com.oculus.video.drm.DrmProxyResponseFormat.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.oculus.video.drm.OculusHttpMediaDrmCallback.AnonymousClass1.$SwitchMap$com$oculus$video$drm$DrmProxyResponseFormat = r0
                int[] r0 = com.oculus.video.drm.OculusHttpMediaDrmCallback.AnonymousClass1.$SwitchMap$com$oculus$video$drm$DrmProxyResponseFormat     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.oculus.video.drm.DrmProxyResponseFormat r1 = com.oculus.video.drm.DrmProxyResponseFormat.JSON     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = com.oculus.video.drm.OculusHttpMediaDrmCallback.AnonymousClass1.$SwitchMap$com$oculus$video$drm$DrmProxyResponseFormat     // Catch:{ NoSuchFieldError -> 0x001f }
                com.oculus.video.drm.DrmProxyResponseFormat r1 = com.oculus.video.drm.DrmProxyResponseFormat.STRING     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = com.oculus.video.drm.OculusHttpMediaDrmCallback.AnonymousClass1.$SwitchMap$com$oculus$video$drm$DrmProxyResponseFormat     // Catch:{ NoSuchFieldError -> 0x002a }
                com.oculus.video.drm.DrmProxyResponseFormat r1 = com.oculus.video.drm.DrmProxyResponseFormat.BYTECODE     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.video.drm.OculusHttpMediaDrmCallback.AnonymousClass1.<clinit>():void");
        }
    }

    private String getResponseString(String str, ExoMediaDrm.KeyRequest keyRequest) throws Exception {
        HashMap hashMap = new HashMap();
        hashMap.put("Content-Type", "application/octet-stream");
        synchronized (this.keyRequestProperties) {
            hashMap.putAll(this.keyRequestProperties);
        }
        return new String(executePost(this.dataSourceFactory, str, Base64.encodeToString(keyRequest.getData(), 0).getBytes(), null), C.UTF8_NAME);
    }
}
