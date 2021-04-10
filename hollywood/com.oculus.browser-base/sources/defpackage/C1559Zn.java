package defpackage;

/* renamed from: Zn  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1559Zn implements AbstractC5483wo {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ AbstractC5483wo f9371a;
    public final /* synthetic */ AbstractC2412eo b;

    public C1559Zn(AbstractC2412eo eoVar, AbstractC5483wo woVar) {
        this.b = eoVar;
        this.f9371a = woVar;
    }

    @Override // defpackage.AbstractC5483wo
    public void a(C5653xo xoVar) {
        if (this.f9371a != null) {
            this.b.c.post(new RunnableC1437Xn(this, xoVar));
        }
        this.b.c.postDelayed(new RunnableC1498Yn(this, xoVar), 1);
    }

    @Override // defpackage.AbstractC5483wo
    public void b(C5653xo xoVar) {
        if (this.f9371a != null) {
            this.b.c.post(new RunnableC1376Wn(this, xoVar));
        }
        this.b.c.postDelayed(new RunnableC1498Yn(this, xoVar), 1);
    }

    @Override // defpackage.AbstractC5483wo
    public void c() {
        if (this.f9371a != null) {
            this.b.c.post(new RunnableC1315Vn(this));
        }
    }
}
