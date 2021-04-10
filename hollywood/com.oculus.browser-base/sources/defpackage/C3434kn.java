package defpackage;

import com.google.android.gms.cast.ApplicationMetadata;

/* renamed from: kn  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3434kn extends AbstractC1252Um {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C3605ln f10303a;

    public C3434kn(C3605ln lnVar, AbstractC3263jn jnVar) {
        this.f10303a = lnVar;
    }

    @Override // defpackage.AbstractC1252Um
    public void c(ApplicationMetadata applicationMetadata) {
        C3605ln.m(this.f10303a);
    }

    @Override // defpackage.AbstractC1252Um
    public void d() {
        C3605ln.m(this.f10303a);
    }

    @Override // defpackage.AbstractC1252Um
    public void f() {
        C3605ln.m(this.f10303a);
        C0153Cl n = this.f10303a.n();
        if (!n.g.isEmpty()) {
            for (C0092Bl bl : n.g) {
                n.h(bl.f7755a, "v2_message", null, bl.b);
            }
            n.g.clear();
        }
    }
}
