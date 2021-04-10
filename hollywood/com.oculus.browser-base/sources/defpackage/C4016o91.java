package defpackage;

/* renamed from: o91  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4016o91 implements D91 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ I91 f10535a;

    public C4016o91(I91 i91) {
        this.f10535a = i91;
    }

    @Override // defpackage.D91
    public void a(int i) {
        int x = this.f10535a.g.x(i);
        if (x != -1) {
            UH0 uh0 = ((C4765sb0) this.f10535a.g.get(x)).b;
            QH0 qh0 = AbstractC5106ub1.h;
            boolean h = uh0.h(qh0);
            if (h) {
                AbstractC3535lK0.a("TabMultiSelect.TabUnselected");
            } else {
                AbstractC3535lK0.a("TabMultiSelect.TabSelected");
            }
            ((C4765sb0) this.f10535a.g.get(x)).b.j(qh0, !h);
        }
    }
}
