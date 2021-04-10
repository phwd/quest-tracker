package defpackage;

import org.chromium.base.Callback;

/* renamed from: NZ  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class NZ extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final Callback f8554a;

    public NZ(Callback callback) {
        this.f8554a = callback;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        Callback callback = this.f8554a;
        byte[] bArr = (byte[]) obj;
        if (bArr == null || bArr.length == 0) {
            callback.onResult(null);
        } else {
            callback.onResult(new C2901hg(bArr));
        }
    }
}
