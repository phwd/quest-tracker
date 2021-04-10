package defpackage;

/* renamed from: Qp0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C1015Qp0 implements Hw1 {

    /* renamed from: a  reason: collision with root package name */
    public final C5232vH0 f8788a;
    public final String b;

    public C1015Qp0(C5232vH0 vh0, String str) {
        this.f8788a = vh0;
        this.b = str;
    }

    @Override // defpackage.Hw1
    public void a(boolean z, String str) {
        C5232vH0 vh0 = this.f8788a;
        String str2 = this.b;
        if (!z) {
            str2 = "";
        }
        vh0.b(str2);
    }
}
