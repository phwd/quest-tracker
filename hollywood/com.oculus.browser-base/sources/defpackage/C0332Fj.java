package defpackage;

import org.chromium.chrome.browser.tab.Tab;

/* renamed from: Fj  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0332Fj extends TT {

    /* renamed from: a  reason: collision with root package name */
    public int f8037a;
    public final /* synthetic */ C5638xj b;
    public final /* synthetic */ C0515Ij c;

    public C0332Fj(C0515Ij ij, C5638xj xjVar) {
        this.c = ij;
        this.b = xjVar;
    }

    @Override // defpackage.TT
    public void a(Tab tab) {
        if (this.c.U.H == tab) {
            C5638xj xjVar = this.b;
            xjVar.N.c(this.f8037a);
        }
    }
}
