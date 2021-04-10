package defpackage;

import java.nio.ByteBuffer;
import org.chromium.base.Callback;
import org.chromium.media.MediaDrmStorageBridge$PersistentInfo;

/* renamed from: fe0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2554fe0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Callback f9938a;
    public final /* synthetic */ C3067ie0 b;

    public C2554fe0(C3067ie0 ie0, Callback callback) {
        this.b = ie0;
        this.f9938a = callback;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        MediaDrmStorageBridge$PersistentInfo mediaDrmStorageBridge$PersistentInfo = (MediaDrmStorageBridge$PersistentInfo) obj;
        if (mediaDrmStorageBridge$PersistentInfo == null) {
            this.f9938a.onResult(null);
            return;
        }
        C2725ge0 ge0 = new C2725ge0(mediaDrmStorageBridge$PersistentInfo.emeId(), null, mediaDrmStorageBridge$PersistentInfo.keySetId(), null);
        String mimeType = mediaDrmStorageBridge$PersistentInfo.mimeType();
        int keyType = mediaDrmStorageBridge$PersistentInfo.keyType();
        if (!(keyType == 2 || keyType == 3)) {
            keyType = 2;
        }
        this.b.f10152a.put(ByteBuffer.wrap(mediaDrmStorageBridge$PersistentInfo.emeId()), new C2896he0(ge0, mimeType, keyType));
        this.f9938a.onResult(ge0);
    }
}
