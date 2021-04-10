package defpackage;

import J.N;
import java.util.UUID;
import org.chromium.media.MediaDrmBridge;

/* renamed from: Yd0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC1473Yd0 implements Runnable {
    public final /* synthetic */ C2725ge0 F;
    public final /* synthetic */ long G;
    public final /* synthetic */ C1534Zd0 H;

    public RunnableC1473Yd0(C1534Zd0 zd0, C2725ge0 ge0, long j) {
        this.H = zd0;
        this.F = ge0;
        this.G = j;
    }

    public void run() {
        this.F.b();
        MediaDrmBridge mediaDrmBridge = this.H.f9355a;
        C2725ge0 ge0 = this.F;
        long j = this.G;
        UUID uuid = MediaDrmBridge.f10978a;
        if (mediaDrmBridge.h()) {
            N.MFLUFEZc(mediaDrmBridge.g, mediaDrmBridge, ge0.b, j);
        }
    }
}
