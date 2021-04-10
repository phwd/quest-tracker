package defpackage;

import android.media.MediaDrm;
import org.chromium.media.MediaDrmBridge;

/* renamed from: Zd0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1534Zd0 implements MediaDrm.OnExpirationUpdateListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ MediaDrmBridge f9355a;

    public C1534Zd0(MediaDrmBridge mediaDrmBridge, RunnableC1107Sd0 sd0) {
        this.f9355a = mediaDrmBridge;
    }

    public void onExpirationUpdate(MediaDrm mediaDrm, byte[] bArr, long j) {
        C2725ge0 a2 = MediaDrmBridge.a(this.f9355a, bArr);
        MediaDrmBridge.b(this.f9355a, a2, new RunnableC1473Yd0(this, a2, j));
    }
}
