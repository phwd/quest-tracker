package org.chromium.media;

import J.N;
import android.media.DeniedByServerException;
import android.media.MediaCrypto;
import android.media.MediaCryptoException;
import android.media.MediaDrm;
import android.media.MediaDrmException;
import android.media.NotProvisionedException;
import android.os.Handler;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Objects;
import java.util.UUID;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class MediaDrmBridge {

    /* renamed from: a  reason: collision with root package name */
    public static final UUID f10978a = UUID.fromString("edef8ba9-79d6-4ace-a3c8-27dcd51d21ed");
    public static final byte[] b = {0};
    public static final byte[] c = AbstractC3153j7.b("unprovision");
    public static final C2213de0 d = new C2213de0();
    public MediaDrm e;
    public MediaCrypto f;
    public long g;
    public UUID h;
    public final boolean i;
    public C2725ge0 j;
    public C3067ie0 k;
    public C3237je0 l;
    public boolean m = false;
    public String n;
    public boolean o;
    public C2383ee0 p;

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public class KeyStatus {

        /* renamed from: a  reason: collision with root package name */
        public final byte[] f10979a;
        public final int b;

        public KeyStatus(byte[] bArr, int i, RunnableC1107Sd0 sd0) {
            this.f10979a = bArr;
            this.b = i;
        }

        public final byte[] getKeyId() {
            return this.f10979a;
        }

        public final int getStatusCode() {
            return this.b;
        }
    }

    public MediaDrmBridge(UUID uuid, boolean z, long j2, long j3) {
        this.h = uuid;
        this.e = new MediaDrm(uuid);
        this.i = z;
        this.g = j2;
        C3237je0 je0 = new C3237je0(j3);
        this.l = je0;
        this.k = new C3067ie0(je0);
        this.e.setOnEventListener(new C1412Xd0(this, null));
        this.e.setOnExpirationUpdateListener(new C1534Zd0(this, null), (Handler) null);
        this.e.setOnKeyStatusChangeListener(new C1871be0(this, null), (Handler) null);
        if (i()) {
            this.e.setPropertyString("privacyMode", "enable");
            this.e.setPropertyString("sessionSharing", "enable");
        }
    }

    public static C2725ge0 a(MediaDrmBridge mediaDrmBridge, byte[] bArr) {
        C2725ge0 ge0;
        if (mediaDrmBridge.j == null) {
            AbstractC1220Ua0.a("media", "Session doesn't exist because media crypto session is not created.", new Object[0]);
            return null;
        }
        C2896he0 he0 = (C2896he0) mediaDrmBridge.k.b.get(ByteBuffer.wrap(bArr));
        if (he0 == null) {
            ge0 = null;
        } else {
            ge0 = he0.f10088a;
        }
        if (ge0 == null) {
            return null;
        }
        return ge0;
    }

    public static void b(MediaDrmBridge mediaDrmBridge, C2725ge0 ge0, Runnable runnable) {
        C2383ee0 ee0 = mediaDrmBridge.p;
        if (ee0 == null || !Arrays.equals(ee0.f9868a.b, ge0.b)) {
            runnable.run();
        } else {
            mediaDrmBridge.p.b.add(runnable);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0068  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00a8  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static org.chromium.media.MediaDrmBridge create(byte[] r16, java.lang.String r17, java.lang.String r18, boolean r19, long r20, long r22) {
        /*
        // Method dump skipped, instructions count: 212
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.media.MediaDrmBridge.create(byte[], java.lang.String, java.lang.String, boolean, long, long):org.chromium.media.MediaDrmBridge");
    }

    public static UUID g(byte[] bArr) {
        if (bArr.length != 16) {
            return null;
        }
        long j2 = 0;
        long j3 = 0;
        for (int i2 = 0; i2 < 8; i2++) {
            j3 = (j3 << 8) | ((long) (bArr[i2] & 255));
        }
        for (int i3 = 8; i3 < 16; i3++) {
            j2 = (j2 << 8) | ((long) (bArr[i3] & 255));
        }
        return new UUID(j3, j2);
    }

    public static int getFirstApiLevel() {
        try {
            return ((Integer) Class.forName("android.os.SystemProperties").getMethod("getInt", String.class, Integer.TYPE).invoke(null, "ro.product.first_api_level", 0)).intValue();
        } catch (Exception e2) {
            AbstractC1220Ua0.a("media", "Exception while getting system property %s. Using default.", "ro.product.first_api_level", e2);
            return 0;
        }
    }

    public static boolean isCryptoSchemeSupported(byte[] bArr, String str) {
        UUID g2 = g(bArr);
        if (str.isEmpty()) {
            return MediaDrm.isCryptoSchemeSupported(g2);
        }
        return MediaDrm.isCryptoSchemeSupported(g2, str);
    }

    public final void c(C2725ge0 ge0) {
        try {
            this.e.closeSession(ge0.c);
        } catch (Exception e2) {
            AbstractC1220Ua0.a("media", "closeSession failed: ", e2);
        }
    }

    public final void closeSession(byte[] bArr, long j2) {
        if (this.e == null) {
            j(j2, "closeSession() called when MediaDrm is null.");
            return;
        }
        C2725ge0 f2 = f(bArr);
        if (f2 == null) {
            StringBuilder i2 = AbstractC2531fV.i("Invalid sessionId in closeSession(): ");
            i2.append(C2725ge0.c(bArr));
            j(j2, i2.toString());
            return;
        }
        try {
            this.e.removeKeys(f2.c);
        } catch (Exception e2) {
            AbstractC1220Ua0.a("media", "removeKeys failed: ", e2);
        }
        c(f2);
        C3067ie0 ie0 = this.k;
        ie0.b(f2);
        ie0.f10152a.remove(ByteBuffer.wrap(f2.b));
        byte[] bArr2 = f2.c;
        if (bArr2 != null) {
            ie0.b.remove(ByteBuffer.wrap(bArr2));
        }
        if (h()) {
            N.MOzXytse(this.g, this, j2);
        }
        if (h()) {
            N.MulYy5b7(this.g, this, f2.b);
        }
        f2.b();
    }

    /* JADX WARNING: Removed duplicated region for block: B:38:0x00c8  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void createSessionFromNative(byte[] r16, java.lang.String r17, int r18, java.lang.String[] r19, long r20) {
        /*
        // Method dump skipped, instructions count: 209
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.media.MediaDrmBridge.createSessionFromNative(byte[], java.lang.String, int, java.lang.String[], long):void");
    }

    public final boolean d() {
        try {
            byte[] n2 = n();
            if (n2 == null) {
                AbstractC1220Ua0.a("media", "Cannot create MediaCrypto Session.", new Object[0]);
                return false;
            }
            C2725ge0 a2 = C2725ge0.a(n2);
            this.j = a2;
            a2.b();
            try {
                if (MediaCrypto.isCryptoSchemeSupported(this.h)) {
                    MediaCrypto mediaCrypto = new MediaCrypto(this.h, this.j.c);
                    this.f = mediaCrypto;
                    if (h()) {
                        N.MV9yuwVC(this.g, this, mediaCrypto);
                    }
                    return true;
                }
                AbstractC1220Ua0.a("media", "Cannot create MediaCrypto for unsupported scheme.", new Object[0]);
                p();
                return false;
            } catch (MediaCryptoException e2) {
                AbstractC1220Ua0.a("media", "Cannot create MediaCrypto", e2);
            }
        } catch (NotProvisionedException unused) {
            if (!d.f9796a) {
                return q();
            }
            C2213de0 de0 = d;
            de0.b.add(new RunnableC1107Sd0(this));
            return true;
        }
    }

    public final void destroy() {
        this.g = 0;
        if (this.e != null) {
            p();
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v1, resolved type: android.media.MediaDrm */
    /* JADX WARN: Multi-variable type inference failed */
    public final MediaDrm.KeyRequest e(C2725ge0 ge0, byte[] bArr, String str, int i2, HashMap hashMap) {
        byte[] bArr2;
        if (hashMap == null) {
            hashMap = new HashMap();
        }
        if (i2 == 3) {
            try {
                bArr2 = ge0.d;
            } catch (MediaDrm.MediaDrmStateException e2) {
                AbstractC1220Ua0.a("media", "MediaDrmStateException fired during getKeyRequest().", e2);
                return null;
            }
        } else {
            bArr2 = ge0.c;
        }
        return this.e.getKeyRequest(bArr2, bArr, str, i2, hashMap);
    }

    public final C2725ge0 f(byte[] bArr) {
        if (this.j == null) {
            AbstractC1220Ua0.a("media", "Session doesn't exist because media crypto session is not created.", new Object[0]);
            return null;
        }
        C2725ge0 c2 = this.k.c(bArr);
        if (c2 == null) {
            return null;
        }
        return c2;
    }

    public final String getSecurityLevel() {
        if (this.e == null || !i()) {
            AbstractC1220Ua0.a("media", "getSecurityLevel(): MediaDrm is null or security level is not supported.", new Object[0]);
            return "";
        }
        try {
            return this.e.getPropertyString("securityLevel");
        } catch (IllegalStateException e2) {
            AbstractC1220Ua0.a("media", "Failed to get current security level", e2);
            return "";
        } catch (Exception e3) {
            AbstractC1220Ua0.a("media", "Failed to get current security level", e3);
            return "";
        }
    }

    public final boolean h() {
        return this.g != 0;
    }

    public final boolean i() {
        return this.h.equals(f10978a);
    }

    public final void j(long j2, String str) {
        AbstractC1220Ua0.a("media", "onPromiseRejected: %s", str);
        if (h()) {
            N.M2P7BQ98(this.g, this, j2, str);
        }
    }

    public final void k(long j2, C2725ge0 ge0) {
        if (h()) {
            N.MtWWjNjU(this.g, this, j2, ge0.b);
        }
    }

    public final void l(C2725ge0 ge0, Object[] objArr, boolean z, boolean z2) {
        if (h()) {
            N.Mk8V79M2(this.g, this, ge0.b, objArr, z, z2);
        }
    }

    public final void loadSession(byte[] bArr, long j2) {
        C3067ie0 ie0 = this.k;
        C1168Td0 td0 = new C1168Td0(this, j2);
        C3237je0 je0 = ie0.c;
        C2554fe0 fe0 = new C2554fe0(ie0, td0);
        if (je0.a()) {
            N.Mmi_qOX8(je0.f10222a, je0, bArr, fe0);
        } else {
            fe0.onResult(null);
        }
    }

    public final void m(C2725ge0 ge0, MediaDrm.KeyRequest keyRequest) {
        if (h()) {
            N.Mf7HZHqV(this.g, this, ge0.b, keyRequest.getRequestType(), keyRequest.getData());
        }
    }

    public final byte[] n() {
        try {
            return (byte[]) this.e.openSession().clone();
        } catch (RuntimeException e2) {
            AbstractC1220Ua0.a("media", "Cannot open a new session", e2);
            p();
            return null;
        } catch (NotProvisionedException e3) {
            throw e3;
        } catch (MediaDrmException e4) {
            AbstractC1220Ua0.a("media", "Cannot open a new session", e4);
            p();
            return null;
        }
    }

    public boolean o(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            AbstractC1220Ua0.a("media", "Invalid provision response.", new Object[0]);
            return false;
        }
        try {
            this.e.provideProvisionResponse(bArr);
            return true;
        } catch (DeniedByServerException e2) {
            AbstractC1220Ua0.a("media", "failed to provide provision response", e2);
            return false;
        } catch (IllegalStateException e3) {
            AbstractC1220Ua0.a("media", "failed to provide provision response", e3);
            return false;
        }
    }

    public final void p() {
        C3067ie0 ie0 = this.k;
        Objects.requireNonNull(ie0);
        ArrayList arrayList = new ArrayList();
        for (C2896he0 he0 : ie0.f10152a.values()) {
            arrayList.add(he0.f10088a);
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            C2725ge0 ge0 = (C2725ge0) it.next();
            try {
                this.e.removeKeys(ge0.c);
            } catch (Exception e2) {
                AbstractC1220Ua0.a("media", "removeKeys failed: ", e2);
            }
            c(ge0);
            if (h()) {
                N.MulYy5b7(this.g, this, ge0.b);
            }
        }
        this.k = new C3067ie0(this.l);
        C2725ge0 ge02 = this.j;
        if (ge02 != null) {
            c(ge02);
            this.j = null;
        }
        MediaDrm mediaDrm = this.e;
        if (mediaDrm != null) {
            mediaDrm.release();
            this.e = null;
        }
        MediaCrypto mediaCrypto = this.f;
        if (mediaCrypto != null) {
            mediaCrypto.release();
            this.f = null;
        } else if (h()) {
            N.MV9yuwVC(this.g, this, null);
        }
    }

    public final void processProvisionResponse(boolean z, byte[] bArr) {
        this.m = false;
        boolean o2 = (this.e == null || !z) ? false : o(bArr);
        if (!this.i) {
            N.MAaklmRW(this.g, this, o2);
            if (!o2) {
                p();
            }
        } else if (!o2) {
            p();
        } else if (!this.o) {
            d();
        } else {
            C3237je0 je0 = this.l;
            C1351Wd0 wd0 = new C1351Wd0(this);
            if (je0.a()) {
                N.ME6vNmlv(je0.f10222a, je0, wd0);
            } else {
                wd0.onResult(Boolean.TRUE);
            }
        }
        if (this.i) {
            C2213de0 de0 = d;
            de0.f9796a = false;
            while (!de0.b.isEmpty()) {
                de0.b.remove();
                ((Runnable) de0.b.element()).run();
                if (de0.f9796a) {
                    return;
                }
            }
        }
    }

    public final void provision() {
        if (!this.o) {
            AbstractC1220Ua0.a("media", "Calling provision() without an origin.", new Object[0]);
            N.MAaklmRW(this.g, this, false);
            return;
        }
        try {
            byte[] n2 = n();
            if (n2 != null) {
                c(C2725ge0.a(n2));
            }
            N.MAaklmRW(this.g, this, true);
        } catch (NotProvisionedException unused) {
            if (!q()) {
                N.MAaklmRW(this.g, this, false);
            }
        }
    }

    public final boolean q() {
        this.m = true;
        if (!h()) {
            return false;
        }
        if (this.i) {
            d.f9796a = true;
        }
        try {
            MediaDrm.ProvisionRequest provisionRequest = this.e.getProvisionRequest();
            Object[] objArr = new Object[1];
            objArr[0] = this.o ? this.n : "<none>";
            AbstractC1220Ua0.d("media", "Provisioning origin ID %s", objArr);
            N.MmhSkOYV(this.g, this, provisionRequest.getDefaultUrl(), provisionRequest.getData());
            return true;
        } catch (IllegalStateException e2) {
            AbstractC1220Ua0.a("media", "Failed to get provisioning request", e2);
            return false;
        }
    }

    public final void removeSession(byte[] bArr, long j2) {
        C2725ge0 f2 = f(bArr);
        if (f2 == null) {
            j(j2, "Session doesn't exist");
            return;
        }
        C2896he0 b2 = this.k.b(f2);
        if (b2.c == 1) {
            j(j2, "Removing temporary session isn't implemented");
            return;
        }
        C3067ie0 ie0 = this.k;
        C1290Vd0 vd0 = new C1290Vd0(this, j2, f2, b2);
        C2896he0 b3 = ie0.b(f2);
        b3.c = 3;
        C3237je0 je0 = ie0.c;
        C2725ge0 ge0 = b3.f10088a;
        MediaDrmStorageBridge$PersistentInfo mediaDrmStorageBridge$PersistentInfo = new MediaDrmStorageBridge$PersistentInfo(ge0.b, ge0.d, b3.b, 3);
        if (je0.a()) {
            N.MeALR1v2(je0.f10222a, je0, mediaDrmStorageBridge$PersistentInfo, vd0);
        } else {
            vd0.onResult(Boolean.FALSE);
        }
    }

    public final boolean setServerCertificate(byte[] bArr) {
        if (!i()) {
            return true;
        }
        try {
            this.e.setPropertyByteArray("serviceCertificate", bArr);
            return true;
        } catch (IllegalArgumentException e2) {
            AbstractC1220Ua0.a("media", "Failed to set server certificate", e2);
            return false;
        } catch (IllegalStateException e3) {
            AbstractC1220Ua0.a("media", "Failed to set server certificate", e3);
            return false;
        }
    }

    public final void unprovision() {
        if (this.e != null && this.o) {
            o(c);
        }
    }

    public final void updateSession(byte[] bArr, byte[] bArr2, long j2) {
        if (this.e == null) {
            j(j2, "updateSession() called when MediaDrm is null.");
            return;
        }
        C2725ge0 f2 = f(bArr);
        if (f2 == null) {
            StringBuilder i2 = AbstractC2531fV.i("Invalid session in updateSession: ");
            i2.append(C2725ge0.c(bArr));
            j(j2, i2.toString());
            return;
        }
        try {
            C2896he0 b2 = this.k.b(f2);
            boolean z = b2.c == 3;
            byte[] bArr3 = null;
            if (z) {
                this.e.provideKeyResponse(f2.d, bArr2);
            } else {
                bArr3 = this.e.provideKeyResponse(f2.c, bArr2);
            }
            C2042ce0 ce0 = new C2042ce0(this, f2, j2, z);
            if (z) {
                this.k.a(f2, ce0);
            } else if (b2.c != 2 || bArr3 == null || bArr3.length <= 0) {
                ce0.onResult(Boolean.TRUE);
            } else {
                this.k.d(f2, bArr3, ce0);
            }
        } catch (NotProvisionedException e2) {
            AbstractC1220Ua0.a("media", "failed to provide key response", e2);
            j(j2, "Update session failed.");
            p();
        } catch (DeniedByServerException e3) {
            AbstractC1220Ua0.a("media", "failed to provide key response", e3);
            j(j2, "Update session failed.");
            p();
        } catch (IllegalStateException e4) {
            AbstractC1220Ua0.a("media", "failed to provide key response", e4);
            j(j2, "Update session failed.");
            p();
        }
    }
}
