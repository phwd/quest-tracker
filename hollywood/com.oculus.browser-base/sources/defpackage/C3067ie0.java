package defpackage;

import J.N;
import java.nio.ByteBuffer;
import java.util.HashMap;
import org.chromium.base.Callback;
import org.chromium.media.MediaDrmStorageBridge$PersistentInfo;

/* renamed from: ie0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3067ie0 {

    /* renamed from: a  reason: collision with root package name */
    public HashMap f10152a = new HashMap();
    public HashMap b = new HashMap();
    public C3237je0 c;

    public C3067ie0(C3237je0 je0) {
        this.c = je0;
    }

    public void a(C2725ge0 ge0, Callback callback) {
        ge0.d = null;
        C3237je0 je0 = this.c;
        byte[] bArr = ge0.b;
        if (je0.a()) {
            N.MYa_y6Dg(je0.f10222a, je0, bArr, callback);
        } else {
            callback.onResult(Boolean.TRUE);
        }
    }

    public C2896he0 b(C2725ge0 ge0) {
        return (C2896he0) this.f10152a.get(ByteBuffer.wrap(ge0.b));
    }

    public C2725ge0 c(byte[] bArr) {
        C2896he0 he0 = (C2896he0) this.f10152a.get(ByteBuffer.wrap(bArr));
        if (he0 == null) {
            return null;
        }
        return he0.f10088a;
    }

    public void d(C2725ge0 ge0, byte[] bArr, Callback callback) {
        ge0.d = bArr;
        C3237je0 je0 = this.c;
        C2896he0 b2 = b(ge0);
        C2725ge0 ge02 = b2.f10088a;
        MediaDrmStorageBridge$PersistentInfo mediaDrmStorageBridge$PersistentInfo = new MediaDrmStorageBridge$PersistentInfo(ge02.b, ge02.d, b2.b, b2.c);
        if (je0.a()) {
            N.MeALR1v2(je0.f10222a, je0, mediaDrmStorageBridge$PersistentInfo, callback);
            return;
        }
        ((C2042ce0) callback).onResult(Boolean.FALSE);
    }
}
