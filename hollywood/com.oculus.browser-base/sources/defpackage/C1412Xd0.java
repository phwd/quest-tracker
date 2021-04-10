package defpackage;

import android.media.MediaDrm;
import android.media.NotProvisionedException;
import org.chromium.media.MediaDrmBridge;

/* renamed from: Xd0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1412Xd0 implements MediaDrm.OnEventListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ MediaDrmBridge f9221a;

    public C1412Xd0(MediaDrmBridge mediaDrmBridge, RunnableC1107Sd0 sd0) {
        this.f9221a = mediaDrmBridge;
    }

    public void onEvent(MediaDrm mediaDrm, byte[] bArr, int i, int i2, byte[] bArr2) {
        if (bArr == null) {
            AbstractC1220Ua0.a("media", "EventListener: No session for event %d.", Integer.valueOf(i));
            return;
        }
        C2725ge0 a2 = MediaDrmBridge.a(this.f9221a, bArr);
        if (a2 == null) {
            AbstractC1220Ua0.a("media", "EventListener: Invalid session %s", C2725ge0.c(bArr));
            return;
        }
        C2896he0 b = this.f9221a.k.b(a2);
        if (i == 2) {
            try {
                MediaDrm.KeyRequest e = this.f9221a.e(a2, bArr2, b.b, b.c, null);
                if (e != null) {
                    this.f9221a.m(a2, e);
                } else {
                    AbstractC1220Ua0.a("media", "EventListener: getKeyRequest failed.", new Object[0]);
                }
            } catch (NotProvisionedException e2) {
                AbstractC1220Ua0.a("media", "Device not provisioned", e2);
            }
        } else if (i != 3 && i != 4) {
            AbstractC1220Ua0.a("media", AbstractC2531fV.w("Invalid DRM event ", i), new Object[0]);
        }
    }
}
