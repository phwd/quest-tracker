package defpackage;

import android.graphics.Typeface;

/* renamed from: Mf1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0748Mf1 extends AbstractC5414wM0 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ AbstractC0931Pf1 f8493a;
    public final /* synthetic */ C0870Of1 b;

    public C0748Mf1(C0870Of1 of1, AbstractC0931Pf1 pf1) {
        this.b = of1;
        this.f8493a = pf1;
    }

    @Override // defpackage.AbstractC5414wM0
    public void c(int i) {
        this.b.k = true;
        this.f8493a.a(i);
    }

    @Override // defpackage.AbstractC5414wM0
    public void d(Typeface typeface) {
        C0870Of1 of1 = this.b;
        of1.l = Typeface.create(typeface, of1.c);
        C0870Of1 of12 = this.b;
        of12.k = true;
        this.f8493a.b(of12.l, false);
    }
}
