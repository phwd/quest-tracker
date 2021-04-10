package defpackage;

import org.chromium.chrome.browser.tab.Tab;

/* renamed from: EX  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class EX implements HX {
    public final /* synthetic */ Tab b;
    public final /* synthetic */ WT c;
    public final /* synthetic */ Runnable d;
    public final /* synthetic */ AbstractC3467ky e;
    public final /* synthetic */ String f;
    public final /* synthetic */ Q31 g;

    public EX(Tab tab, WT wt, Runnable runnable, AbstractC3467ky kyVar, String str, Q31 q31) {
        this.b = tab;
        this.c = wt;
        this.d = runnable;
        this.e = kyVar;
        this.f = str;
        this.g = q31;
    }

    @Override // defpackage.HX
    public C5455we1 a() {
        return new C5455we1(this.b, this.e, this.f);
    }

    @Override // defpackage.HX
    public C0682Ld1 b() {
        return new C0682Ld1(this.b, this.c, this.d);
    }
}
