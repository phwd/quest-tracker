package defpackage;

/* renamed from: ht0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC2941ht0 extends AbstractC2925ho {
    public boolean H;

    @Override // defpackage.AbstractC1649aL0
    public void b(Object obj, int i, Object obj2) {
        C5859z.a(obj);
        AbstractC2755go0 go0 = (AbstractC2755go0) obj2;
        if (go0 == null) {
            q(i);
            t(null);
            return;
        }
        go0.onResult(null);
    }

    @Override // defpackage.AbstractC1649aL0
    public int getItemViewType(int i) {
        q(i);
        return s();
    }

    public abstract int s();

    public abstract void t(PX0 px0);

    public void u(boolean z) {
        if (this.H != z) {
            this.H = z;
            if (z) {
                o(0, 1);
            } else {
                p(0, 1);
            }
        }
    }
}
