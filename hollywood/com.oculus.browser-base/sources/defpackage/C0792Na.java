package defpackage;

/* renamed from: Na  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0792Na extends AbstractC2032cb {
    public final int i;
    public final /* synthetic */ AbstractC0914Pa j;

    public C0792Na(AbstractC0914Pa pa, int i2) {
        this.j = pa;
        this.i = i2;
    }

    @Override // defpackage.AbstractC2032cb
    public Object c() {
        return this.j.d(this.i);
    }

    @Override // defpackage.AbstractC2032cb
    public void k(Object obj) {
        AbstractC3197jM0 jm0 = (AbstractC3197jM0) obj;
        if (this.j.c.get(this.i) != null) {
            AbstractC0914Pa pa = this.j;
            int i2 = this.i;
            pa.b(i2, jm0);
            pa.c.remove(i2);
        }
    }
}
