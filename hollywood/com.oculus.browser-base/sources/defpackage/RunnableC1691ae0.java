package defpackage;

import android.media.MediaDrm;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.chromium.media.MediaDrmBridge;

/* renamed from: ae0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC1691ae0 implements Runnable {
    public final /* synthetic */ C2725ge0 F;
    public final /* synthetic */ boolean G;
    public final /* synthetic */ List H;
    public final /* synthetic */ boolean I;

    /* renamed from: J  reason: collision with root package name */
    public final /* synthetic */ C1871be0 f9440J;

    public RunnableC1691ae0(C1871be0 be0, C2725ge0 ge0, boolean z, List list, boolean z2) {
        this.f9440J = be0;
        this.F = ge0;
        this.G = z;
        this.H = list;
        this.I = z2;
    }

    public void run() {
        this.F.b();
        MediaDrmBridge mediaDrmBridge = this.f9440J.f9549a;
        C2725ge0 ge0 = this.F;
        List<MediaDrm.KeyStatus> list = this.H;
        ArrayList arrayList = new ArrayList();
        for (MediaDrm.KeyStatus keyStatus : list) {
            arrayList.add(new MediaDrmBridge.KeyStatus(keyStatus.getKeyId(), keyStatus.getStatusCode(), null));
        }
        Object[] array = arrayList.toArray();
        boolean z = this.G;
        boolean z2 = this.I;
        UUID uuid = MediaDrmBridge.f10978a;
        mediaDrmBridge.l(ge0, array, z, z2);
    }
}
