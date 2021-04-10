package defpackage;

import android.media.MediaDrm;
import java.util.List;
import org.chromium.media.MediaDrmBridge;

/* renamed from: be0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1871be0 implements MediaDrm.OnKeyStatusChangeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ MediaDrmBridge f9549a;

    public C1871be0(MediaDrmBridge mediaDrmBridge, RunnableC1107Sd0 sd0) {
        this.f9549a = mediaDrmBridge;
    }

    @Override // android.media.MediaDrm.OnKeyStatusChangeListener
    public void onKeyStatusChange(MediaDrm mediaDrm, byte[] bArr, List list, boolean z) {
        C2725ge0 a2 = MediaDrmBridge.a(this.f9549a, bArr);
        MediaDrmBridge.b(this.f9549a, a2, new RunnableC1691ae0(this, a2, z, list, this.f9549a.k.b(a2).c == 3));
    }
}
