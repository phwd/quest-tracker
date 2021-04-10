package defpackage;

/* renamed from: Gx  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0421Gx {

    /* renamed from: a  reason: collision with root package name */
    public final KS f8124a;

    public C0421Gx(KS ks) {
        this.f8124a = ks;
    }

    public void a() {
        b("ConfirmSyncTimeoutDialog");
        b("ConfirmSyncProgressDialog");
        b("ConfirmImportSyncDataDialog");
        b("ConfirmManagedSyncDataDialog");
    }

    public final void b(String str) {
        OE oe = (OE) this.f8124a.J(str);
        if (oe != null) {
            oe.e1();
        }
    }

    public final void c(OE oe, String str) {
        C0317Fe fe = new C0317Fe(this.f8124a);
        fe.i(0, oe, str, 1);
        fe.f();
    }
}
