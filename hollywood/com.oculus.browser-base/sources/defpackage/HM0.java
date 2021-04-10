package defpackage;

import java.util.Objects;

/* renamed from: HM0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class HM0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final SM0 f8154a;
    public final W70 b;

    public HM0(SM0 sm0, W70 w70) {
        this.f8154a = sm0;
        this.b = w70;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        SM0 sm0 = this.f8154a;
        W70 w70 = this.b;
        C5859z.a(obj);
        Objects.requireNonNull(sm0);
        Objects.requireNonNull(w70);
    }
}
