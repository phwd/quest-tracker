package defpackage;

import android.media.NotProvisionedException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;
import org.chromium.media.MediaDrmBridge;

/* renamed from: Td0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1168Td0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ long f8970a;
    public final /* synthetic */ MediaDrmBridge b;

    public C1168Td0(MediaDrmBridge mediaDrmBridge, long j) {
        this.b = mediaDrmBridge;
        this.f8970a = j;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C2725ge0 ge0 = (C2725ge0) obj;
        if (ge0 == null) {
            MediaDrmBridge mediaDrmBridge = this.b;
            long j = this.f8970a;
            UUID uuid = MediaDrmBridge.f10978a;
            Objects.requireNonNull(mediaDrmBridge);
            mediaDrmBridge.k(j, C2725ge0.a(new byte[0]));
            return;
        }
        MediaDrmBridge mediaDrmBridge2 = this.b;
        long j2 = this.f8970a;
        UUID uuid2 = MediaDrmBridge.f10978a;
        Objects.requireNonNull(mediaDrmBridge2);
        try {
            byte[] n = mediaDrmBridge2.n();
            if (n == null) {
                mediaDrmBridge2.j(j2, "Failed to open session to load license.");
                return;
            }
            C3067ie0 ie0 = mediaDrmBridge2.k;
            C2896he0 b2 = ie0.b(ge0);
            ge0.c = n;
            ie0.b.put(ByteBuffer.wrap(n), b2);
            if (mediaDrmBridge2.k.b(ge0).c == 3) {
                AbstractC1220Ua0.f("media", "Persistent license is waiting for release ack.", new Object[0]);
                mediaDrmBridge2.k(j2, ge0);
                ArrayList arrayList = new ArrayList();
                arrayList.add(new MediaDrmBridge.KeyStatus(MediaDrmBridge.b, 1, null));
                mediaDrmBridge2.l(ge0, arrayList.toArray(), false, true);
                return;
            }
            mediaDrmBridge2.p = new C2383ee0(ge0);
            mediaDrmBridge2.e.restoreKeys(ge0.c, ge0.d);
            mediaDrmBridge2.k(j2, ge0);
            mediaDrmBridge2.p.a();
            mediaDrmBridge2.p = null;
        } catch (NotProvisionedException unused) {
            AbstractC1220Ua0.f("media", "Persistent license load fail because origin isn't provisioned.", new Object[0]);
            mediaDrmBridge2.c(ge0);
            mediaDrmBridge2.k.a(ge0, new C1229Ud0(mediaDrmBridge2, j2));
        } catch (IllegalStateException unused2) {
            mediaDrmBridge2.c(ge0);
            mediaDrmBridge2.k.a(ge0, new C1229Ud0(mediaDrmBridge2, j2));
        }
    }
}
