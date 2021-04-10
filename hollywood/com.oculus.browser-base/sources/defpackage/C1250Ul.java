package defpackage;

/* renamed from: Ul  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C1250Ul {

    /* renamed from: a  reason: collision with root package name */
    public Object f9047a;
    public C1433Xl b;
    public C2685gM0 c = new C2685gM0();
    public boolean d;

    public boolean a(Object obj) {
        boolean z = true;
        this.d = true;
        C1433Xl xl = this.b;
        if (xl == null || !xl.G.i(obj)) {
            z = false;
        }
        if (z) {
            this.f9047a = null;
            this.b = null;
            this.c = null;
        }
        return z;
    }

    public void finalize() {
        C2685gM0 gm0;
        C1433Xl xl = this.b;
        if (xl != null && !xl.isDone()) {
            StringBuilder i = AbstractC2531fV.i("The completer object was garbage collected - this future would otherwise never complete. The tag was: ");
            i.append(this.f9047a);
            xl.G.j(new C1311Vl(i.toString()));
        }
        if (!this.d && (gm0 = this.c) != null) {
            gm0.i(null);
        }
    }
}
