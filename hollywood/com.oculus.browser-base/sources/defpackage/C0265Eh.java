package defpackage;

import android.graphics.Bitmap;

/* renamed from: Eh  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C0265Eh extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C3741mb0 f7973a;

    public C0265Eh(C3741mb0 mb0) {
        this.f7973a = mb0;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C3912nb0 nb0 = this.f7973a.f10434a;
        nb0.f = (Bitmap) obj;
        C3912nb0.a(nb0, 3);
    }
}
