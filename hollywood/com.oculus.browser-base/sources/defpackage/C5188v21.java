package defpackage;

import org.chromium.base.Callback;

/* renamed from: v21  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C5188v21 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final Callback f11456a;

    public C5188v21(Callback callback) {
        this.f11456a = callback;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        this.f11456a.onResult((C5868z21) obj);
    }
}
