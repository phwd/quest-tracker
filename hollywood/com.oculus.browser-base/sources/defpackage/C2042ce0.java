package defpackage;

import J.N;
import java.util.UUID;
import org.chromium.media.MediaDrmBridge;

/* renamed from: ce0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2042ce0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C2725ge0 f9620a;
    public final long b;
    public final boolean c;
    public final /* synthetic */ MediaDrmBridge d;

    public C2042ce0(MediaDrmBridge mediaDrmBridge, C2725ge0 ge0, long j, boolean z) {
        this.d = mediaDrmBridge;
        this.f9620a = ge0;
        this.b = j;
        this.c = z;
    }

    /* renamed from: b */
    public void onResult(Boolean bool) {
        if (!bool.booleanValue()) {
            MediaDrmBridge mediaDrmBridge = this.d;
            long j = this.b;
            UUID uuid = MediaDrmBridge.f10978a;
            mediaDrmBridge.j(j, "failed to update key after response accepted");
            return;
        }
        this.f9620a.b();
        MediaDrmBridge mediaDrmBridge2 = this.d;
        long j2 = this.b;
        UUID uuid2 = MediaDrmBridge.f10978a;
        if (mediaDrmBridge2.h()) {
            N.MOzXytse(mediaDrmBridge2.g, mediaDrmBridge2, j2);
        }
    }
}
