package defpackage;

import java.util.Objects;

/* renamed from: CD0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class CD0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final ED0 f7796a;

    public CD0(ED0 ed0) {
        this.f7796a = ed0;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        ED0 ed0 = this.f7796a;
        C2952hx hxVar = (C2952hx) obj;
        Objects.requireNonNull(ed0);
        ed0.h.post(new DD0(ed0, hxVar.f10112a != null, hxVar));
    }
}
