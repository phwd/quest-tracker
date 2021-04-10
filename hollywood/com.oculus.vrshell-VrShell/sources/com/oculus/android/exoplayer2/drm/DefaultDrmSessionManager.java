package com.oculus.android.exoplayer2.drm;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import com.oculus.android.exoplayer2.C;
import com.oculus.android.exoplayer2.drm.DefaultDrmSession;
import com.oculus.android.exoplayer2.drm.DrmInitData;
import com.oculus.android.exoplayer2.drm.DrmSession;
import com.oculus.android.exoplayer2.drm.ExoMediaCrypto;
import com.oculus.android.exoplayer2.drm.ExoMediaDrm;
import com.oculus.android.exoplayer2.extractor.mp4.PsshAtomUtil;
import com.oculus.android.exoplayer2.util.Assertions;
import com.oculus.android.exoplayer2.util.MimeTypes;
import com.oculus.android.exoplayer2.util.Util;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@TargetApi(18)
public class DefaultDrmSessionManager<T extends ExoMediaCrypto> implements DrmSessionManager<T>, DefaultDrmSession.ProvisioningManager<T> {
    private static final String CENC_SCHEME_MIME_TYPE = "cenc";
    public static final int INITIAL_DRM_REQUEST_RETRY_COUNT = 3;
    public static final int MODE_DOWNLOAD = 2;
    public static final int MODE_PLAYBACK = 0;
    public static final int MODE_QUERY = 1;
    public static final int MODE_RELEASE = 3;
    public static final String PLAYREADY_CUSTOM_DATA_KEY = "PRCustomData";
    private static final String TAG = "DefaultDrmSessionMgr";
    private final MediaDrmCallback callback;
    private final Handler eventHandler;
    private final EventListener eventListener;
    private final int initialDrmRequestRetryCount;
    private final ExoMediaDrm<T> mediaDrm;
    volatile DefaultDrmSessionManager<T>.MediaDrmHandler mediaDrmHandler;
    private int mode;
    private final boolean multiSession;
    private byte[] offlineLicenseKeySetId;
    private final HashMap<String, String> optionalKeyRequestParameters;
    private Looper playbackLooper;
    private final List<DefaultDrmSession<T>> provisioningSessions;
    private final List<DefaultDrmSession<T>> sessions;
    private final UUID uuid;

    public interface EventListener {
        void onDrmKeysLoaded();

        void onDrmKeysRemoved();

        void onDrmKeysRestored();

        void onDrmSessionManagerError(Exception exc);
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Mode {
    }

    public static final class MissingSchemeDataException extends Exception {
        private MissingSchemeDataException(UUID uuid) {
            super("Media does not support uuid: " + uuid);
        }
    }

    public static DefaultDrmSessionManager<FrameworkMediaCrypto> newWidevineInstance(MediaDrmCallback mediaDrmCallback, HashMap<String, String> hashMap, Handler handler, EventListener eventListener2) throws UnsupportedDrmException {
        return newFrameworkInstance(C.WIDEVINE_UUID, mediaDrmCallback, hashMap, handler, eventListener2);
    }

    public static DefaultDrmSessionManager<FrameworkMediaCrypto> newPlayReadyInstance(MediaDrmCallback mediaDrmCallback, String str, Handler handler, EventListener eventListener2) throws UnsupportedDrmException {
        HashMap hashMap;
        if (!TextUtils.isEmpty(str)) {
            hashMap = new HashMap();
            hashMap.put(PLAYREADY_CUSTOM_DATA_KEY, str);
        } else {
            hashMap = null;
        }
        return newFrameworkInstance(C.PLAYREADY_UUID, mediaDrmCallback, hashMap, handler, eventListener2);
    }

    public static DefaultDrmSessionManager<FrameworkMediaCrypto> newFrameworkInstance(UUID uuid2, MediaDrmCallback mediaDrmCallback, HashMap<String, String> hashMap, Handler handler, EventListener eventListener2) throws UnsupportedDrmException {
        return new DefaultDrmSessionManager<>(uuid2, FrameworkMediaDrm.newInstance(uuid2), mediaDrmCallback, hashMap, handler, eventListener2, false, 3);
    }

    public DefaultDrmSessionManager(UUID uuid2, ExoMediaDrm<T> exoMediaDrm, MediaDrmCallback mediaDrmCallback, HashMap<String, String> hashMap, Handler handler, EventListener eventListener2) {
        this(uuid2, exoMediaDrm, mediaDrmCallback, hashMap, handler, eventListener2, false, 3);
    }

    public DefaultDrmSessionManager(UUID uuid2, ExoMediaDrm<T> exoMediaDrm, MediaDrmCallback mediaDrmCallback, HashMap<String, String> hashMap, Handler handler, EventListener eventListener2, boolean z) {
        this(uuid2, exoMediaDrm, mediaDrmCallback, hashMap, handler, eventListener2, z, 3);
    }

    public DefaultDrmSessionManager(UUID uuid2, ExoMediaDrm<T> exoMediaDrm, MediaDrmCallback mediaDrmCallback, HashMap<String, String> hashMap, Handler handler, EventListener eventListener2, boolean z, int i) {
        Assertions.checkNotNull(uuid2);
        Assertions.checkNotNull(exoMediaDrm);
        Assertions.checkArgument(!C.COMMON_PSSH_UUID.equals(uuid2), "Use C.CLEARKEY_UUID instead");
        this.uuid = uuid2;
        this.mediaDrm = exoMediaDrm;
        this.callback = mediaDrmCallback;
        this.optionalKeyRequestParameters = hashMap;
        this.eventHandler = handler;
        this.eventListener = eventListener2;
        this.multiSession = z;
        this.initialDrmRequestRetryCount = i;
        this.mode = 0;
        this.sessions = new ArrayList();
        this.provisioningSessions = new ArrayList();
        if (z) {
            exoMediaDrm.setPropertyString("sessionSharing", "enable");
        }
        exoMediaDrm.setOnEventListener(new MediaDrmEventListener());
    }

    public final String getPropertyString(String str) {
        return this.mediaDrm.getPropertyString(str);
    }

    public final void setPropertyString(String str, String str2) {
        this.mediaDrm.setPropertyString(str, str2);
    }

    public final byte[] getPropertyByteArray(String str) {
        return this.mediaDrm.getPropertyByteArray(str);
    }

    public final void setPropertyByteArray(String str, byte[] bArr) {
        this.mediaDrm.setPropertyByteArray(str, bArr);
    }

    public void setMode(int i, byte[] bArr) {
        Assertions.checkState(this.sessions.isEmpty());
        if (i == 1 || i == 3) {
            Assertions.checkNotNull(bArr);
        }
        this.mode = i;
        this.offlineLicenseKeySetId = bArr;
    }

    @Override // com.oculus.android.exoplayer2.drm.DrmSessionManager
    public boolean canAcquireSession(@NonNull DrmInitData drmInitData) {
        if (this.offlineLicenseKeySetId != null) {
            return true;
        }
        if (getSchemeData(drmInitData, this.uuid, true) == null) {
            if (drmInitData.schemeDataCount != 1 || !drmInitData.get(0).matches(C.COMMON_PSSH_UUID)) {
                return false;
            }
            Log.w(TAG, "DrmInitData only contains common PSSH SchemeData. Assuming support for: " + this.uuid);
        }
        String str = drmInitData.schemeType;
        if (str == null || "cenc".equals(str)) {
            return true;
        }
        if ((C.CENC_TYPE_cbc1.equals(str) || C.CENC_TYPE_cbcs.equals(str) || C.CENC_TYPE_cens.equals(str)) && Util.SDK_INT < 24) {
            return false;
        }
        return true;
    }

    @Override // com.oculus.android.exoplayer2.drm.DrmSessionManager
    public DrmSession<T> acquireSession(Looper looper, DrmInitData drmInitData) {
        String str;
        byte[] bArr;
        DefaultDrmSession<T> defaultDrmSession;
        Looper looper2 = this.playbackLooper;
        Assertions.checkState(looper2 == null || looper2 == looper);
        if (this.sessions.isEmpty()) {
            this.playbackLooper = looper;
            if (this.mediaDrmHandler == null) {
                this.mediaDrmHandler = new MediaDrmHandler(looper);
            }
        }
        DefaultDrmSession<T> defaultDrmSession2 = null;
        if (this.offlineLicenseKeySetId == null) {
            DrmInitData.SchemeData schemeData = getSchemeData(drmInitData, this.uuid, false);
            if (schemeData == null) {
                final MissingSchemeDataException missingSchemeDataException = new MissingSchemeDataException(this.uuid);
                Handler handler = this.eventHandler;
                if (!(handler == null || this.eventListener == null)) {
                    handler.post(new Runnable() {
                        /* class com.oculus.android.exoplayer2.drm.DefaultDrmSessionManager.AnonymousClass1 */

                        public void run() {
                            DefaultDrmSessionManager.this.eventListener.onDrmSessionManagerError(missingSchemeDataException);
                        }
                    });
                }
                return new ErrorStateDrmSession(new DrmSession.DrmSessionException(missingSchemeDataException));
            }
            byte[] schemeInitData = getSchemeInitData(schemeData, this.uuid);
            str = getSchemeMimeType(schemeData, this.uuid);
            bArr = schemeInitData;
        } else {
            bArr = null;
            str = null;
        }
        if (this.multiSession) {
            Iterator<DefaultDrmSession<T>> it = this.sessions.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                DefaultDrmSession<T> next = it.next();
                if (next.hasInitData(bArr)) {
                    defaultDrmSession2 = next;
                    break;
                }
            }
        } else if (!this.sessions.isEmpty()) {
            defaultDrmSession2 = this.sessions.get(0);
        }
        if (defaultDrmSession2 == null) {
            defaultDrmSession = new DefaultDrmSession<>(this.uuid, this.mediaDrm, this, bArr, str, this.mode, this.offlineLicenseKeySetId, this.optionalKeyRequestParameters, this.callback, looper, this.eventHandler, this.eventListener, this.initialDrmRequestRetryCount);
            this.sessions.add(defaultDrmSession);
        } else {
            defaultDrmSession = defaultDrmSession2;
        }
        defaultDrmSession.acquire();
        return defaultDrmSession;
    }

    @Override // com.oculus.android.exoplayer2.drm.DrmSessionManager
    public void releaseSession(DrmSession<T> drmSession) {
        if (!(drmSession instanceof ErrorStateDrmSession)) {
            DefaultDrmSession<T> defaultDrmSession = (DefaultDrmSession) drmSession;
            if (defaultDrmSession.release()) {
                this.sessions.remove(defaultDrmSession);
                if (this.provisioningSessions.size() > 1 && this.provisioningSessions.get(0) == defaultDrmSession) {
                    this.provisioningSessions.get(1).provision();
                }
                this.provisioningSessions.remove(defaultDrmSession);
            }
        }
    }

    @Override // com.oculus.android.exoplayer2.drm.DefaultDrmSession.ProvisioningManager
    public void provisionRequired(DefaultDrmSession<T> defaultDrmSession) {
        this.provisioningSessions.add(defaultDrmSession);
        if (this.provisioningSessions.size() == 1) {
            defaultDrmSession.provision();
        }
    }

    @Override // com.oculus.android.exoplayer2.drm.DefaultDrmSession.ProvisioningManager
    public void onProvisionCompleted() {
        for (DefaultDrmSession<T> defaultDrmSession : this.provisioningSessions) {
            defaultDrmSession.onProvisionCompleted();
        }
        this.provisioningSessions.clear();
    }

    @Override // com.oculus.android.exoplayer2.drm.DefaultDrmSession.ProvisioningManager
    public void onProvisionError(Exception exc) {
        for (DefaultDrmSession<T> defaultDrmSession : this.provisioningSessions) {
            defaultDrmSession.onProvisionError(exc);
        }
        this.provisioningSessions.clear();
    }

    private static DrmInitData.SchemeData getSchemeData(DrmInitData drmInitData, UUID uuid2, boolean z) {
        ArrayList arrayList = new ArrayList(drmInitData.schemeDataCount);
        int i = 0;
        while (true) {
            boolean z2 = true;
            if (i >= drmInitData.schemeDataCount) {
                break;
            }
            DrmInitData.SchemeData schemeData = drmInitData.get(i);
            if (!schemeData.matches(uuid2) && (!C.CLEARKEY_UUID.equals(uuid2) || !schemeData.matches(C.COMMON_PSSH_UUID))) {
                z2 = false;
            }
            if (z2 && (schemeData.data != null || z)) {
                arrayList.add(schemeData);
            }
            i++;
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        if (C.WIDEVINE_UUID.equals(uuid2)) {
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                DrmInitData.SchemeData schemeData2 = (DrmInitData.SchemeData) arrayList.get(i2);
                int parseVersion = schemeData2.hasData() ? PsshAtomUtil.parseVersion(schemeData2.data) : -1;
                if (Util.SDK_INT < 23 && parseVersion == 0) {
                    return schemeData2;
                }
                if (Util.SDK_INT >= 23 && parseVersion == 1) {
                    return schemeData2;
                }
            }
        }
        return (DrmInitData.SchemeData) arrayList.get(0);
    }

    private static byte[] getSchemeInitData(DrmInitData.SchemeData schemeData, UUID uuid2) {
        byte[] parseSchemeSpecificData;
        byte[] bArr = schemeData.data;
        return (Util.SDK_INT >= 21 || (parseSchemeSpecificData = PsshAtomUtil.parseSchemeSpecificData(bArr, uuid2)) == null) ? bArr : parseSchemeSpecificData;
    }

    private static String getSchemeMimeType(DrmInitData.SchemeData schemeData, UUID uuid2) {
        String str = schemeData.mimeType;
        if (Util.SDK_INT >= 26 || !C.CLEARKEY_UUID.equals(uuid2)) {
            return str;
        }
        return (MimeTypes.VIDEO_MP4.equals(str) || MimeTypes.AUDIO_MP4.equals(str)) ? "cenc" : str;
    }

    @SuppressLint({"HandlerLeak"})
    private class MediaDrmHandler extends Handler {
        public MediaDrmHandler(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            byte[] bArr = (byte[]) message.obj;
            for (DefaultDrmSession defaultDrmSession : DefaultDrmSessionManager.this.sessions) {
                if (defaultDrmSession.hasSessionId(bArr)) {
                    defaultDrmSession.onMediaDrmEvent(message.what);
                    return;
                }
            }
        }
    }

    private class MediaDrmEventListener implements ExoMediaDrm.OnEventListener<T> {
        private MediaDrmEventListener() {
        }

        @Override // com.oculus.android.exoplayer2.drm.ExoMediaDrm.OnEventListener
        public void onEvent(ExoMediaDrm<? extends T> exoMediaDrm, byte[] bArr, int i, int i2, byte[] bArr2) {
            if (DefaultDrmSessionManager.this.mode == 0) {
                DefaultDrmSessionManager.this.mediaDrmHandler.obtainMessage(i, bArr).sendToTarget();
            }
        }
    }
}
