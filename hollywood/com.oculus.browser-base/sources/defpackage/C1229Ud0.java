package defpackage;

import java.util.Objects;
import java.util.UUID;
import org.chromium.media.MediaDrmBridge;

/* renamed from: Ud0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1229Ud0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ long f9036a;
    public final /* synthetic */ MediaDrmBridge b;

    public C1229Ud0(MediaDrmBridge mediaDrmBridge, long j) {
        this.b = mediaDrmBridge;
        this.f9036a = j;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        if (!((Boolean) obj).booleanValue()) {
            AbstractC1220Ua0.f("media", "Failed to clear persistent storage for non-exist license", new Object[0]);
        }
        MediaDrmBridge mediaDrmBridge = this.b;
        long j = this.f9036a;
        UUID uuid = MediaDrmBridge.f10978a;
        Objects.requireNonNull(mediaDrmBridge);
        mediaDrmBridge.k(j, C2725ge0.a(new byte[0]));
    }
}
