package defpackage;

/* renamed from: JY0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class JY0 implements AbstractC5483wo {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ LY0 f8296a;

    public JY0(LY0 ly0) {
        this.f8296a = ly0;
    }

    @Override // defpackage.AbstractC5483wo
    public void a(C5653xo xoVar) {
        AbstractC5483wo woVar = this.f8296a.d;
        if (woVar != null) {
            woVar.a(xoVar);
        }
        LY0 ly0 = this.f8296a;
        if (ly0.b != null) {
            ly0.b = null;
            ly0.c = false;
        }
    }

    @Override // defpackage.AbstractC5483wo
    public void b(C5653xo xoVar) {
        AbstractC1220Ua0.f("SpareChildConn", "Failed to warm up the spare sandbox service", new Object[0]);
        AbstractC5483wo woVar = this.f8296a.d;
        if (woVar != null) {
            woVar.b(xoVar);
        }
        LY0.a(this.f8296a);
    }

    @Override // defpackage.AbstractC5483wo
    public void c() {
        LY0 ly0 = this.f8296a;
        ly0.c = true;
        AbstractC5483wo woVar = ly0.d;
        if (woVar != null) {
            woVar.c();
            LY0.a(this.f8296a);
        }
    }
}
