package defpackage;

import android.graphics.Typeface;

/* renamed from: kg1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3415kg1 extends AbstractC0931Pf1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C3757mg1 f10294a;

    public C3415kg1(C3757mg1 mg1) {
        this.f10294a = mg1;
    }

    @Override // defpackage.AbstractC0931Pf1
    public void a(int i) {
        C3757mg1 mg1 = this.f10294a;
        mg1.d = true;
        AbstractC3586lg1 lg1 = (AbstractC3586lg1) mg1.e.get();
        if (lg1 != null) {
            C3098ip ipVar = (C3098ip) lg1;
            ipVar.C();
            ipVar.invalidateSelf();
        }
    }

    @Override // defpackage.AbstractC0931Pf1
    public void b(Typeface typeface, boolean z) {
        if (!z) {
            C3757mg1 mg1 = this.f10294a;
            mg1.d = true;
            AbstractC3586lg1 lg1 = (AbstractC3586lg1) mg1.e.get();
            if (lg1 != null) {
                C3098ip ipVar = (C3098ip) lg1;
                ipVar.C();
                ipVar.invalidateSelf();
            }
        }
    }
}
