package defpackage;

import android.net.Uri;

/* renamed from: jr  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C3275jr extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C5492wr f10241a;

    public C3275jr(C5492wr wrVar) {
        this.f10241a = wrVar;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        LT0.h(this.f10241a.j(), LT0.e(), (Uri) obj);
    }
}
