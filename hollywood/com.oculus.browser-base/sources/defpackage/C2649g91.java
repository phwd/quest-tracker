package defpackage;

import android.graphics.drawable.Drawable;

/* renamed from: g91  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C2649g91 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final I91 f9980a;
    public final C4384qI0 b;

    public C2649g91(I91 i91, C4384qI0 qi0) {
        this.f9980a = i91;
        this.b = qi0;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        I91 i91 = this.f9980a;
        Drawable drawable = (Drawable) obj;
        int x = i91.g.x(this.b.c());
        if (x != -1 && drawable != null) {
            ((C4765sb0) i91.g.get(x)).b.m(AbstractC5106ub1.d, drawable);
        }
    }
}
