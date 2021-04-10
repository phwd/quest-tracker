package defpackage;

import J.N;
import android.media.MediaDrm;
import android.media.NotProvisionedException;
import java.util.Objects;
import java.util.UUID;
import org.chromium.media.MediaDrmBridge;

/* renamed from: Vd0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1290Vd0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ long f9096a;
    public final /* synthetic */ C2725ge0 b;
    public final /* synthetic */ C2896he0 c;
    public final /* synthetic */ MediaDrmBridge d;

    public C1290Vd0(MediaDrmBridge mediaDrmBridge, long j, C2725ge0 ge0, C2896he0 he0) {
        this.d = mediaDrmBridge;
        this.f9096a = j;
        this.b = ge0;
        this.c = he0;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        if (!((Boolean) obj).booleanValue()) {
            MediaDrmBridge mediaDrmBridge = this.d;
            long j = this.f9096a;
            UUID uuid = MediaDrmBridge.f10978a;
            mediaDrmBridge.j(j, "Fail to update persistent storage");
            return;
        }
        MediaDrmBridge mediaDrmBridge2 = this.d;
        C2725ge0 ge0 = this.b;
        String str = this.c.b;
        long j2 = this.f9096a;
        UUID uuid2 = MediaDrmBridge.f10978a;
        Objects.requireNonNull(mediaDrmBridge2);
        try {
            MediaDrm.KeyRequest e = mediaDrmBridge2.e(ge0, null, str, 3, null);
            if (e == null) {
                mediaDrmBridge2.j(j2, "Fail to generate key release request");
                return;
            }
            if (mediaDrmBridge2.h()) {
                N.MOzXytse(mediaDrmBridge2.g, mediaDrmBridge2, j2);
            }
            mediaDrmBridge2.m(ge0, e);
        } catch (NotProvisionedException unused) {
            AbstractC1220Ua0.a("media", "removeSession called on unprovisioned device", new Object[0]);
            mediaDrmBridge2.j(j2, "Unknown failure");
        }
    }
}
