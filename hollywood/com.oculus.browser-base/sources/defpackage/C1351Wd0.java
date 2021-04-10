package defpackage;

import java.util.UUID;
import org.chromium.media.MediaDrmBridge;

/* renamed from: Wd0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1351Wd0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ MediaDrmBridge f9159a;

    public C1351Wd0(MediaDrmBridge mediaDrmBridge) {
        this.f9159a = mediaDrmBridge;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        if (!((Boolean) obj).booleanValue()) {
            AbstractC1220Ua0.a("media", "Failed to initialize storage for origin", new Object[0]);
            MediaDrmBridge mediaDrmBridge = this.f9159a;
            UUID uuid = MediaDrmBridge.f10978a;
            mediaDrmBridge.p();
            return;
        }
        MediaDrmBridge mediaDrmBridge2 = this.f9159a;
        UUID uuid2 = MediaDrmBridge.f10978a;
        mediaDrmBridge2.d();
    }
}
