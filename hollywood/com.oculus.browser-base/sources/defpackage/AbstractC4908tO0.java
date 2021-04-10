package defpackage;

import java.util.Iterator;

/* renamed from: tO0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC4908tO0 extends AbstractC5078uO0 implements Iterator {
    public C4568rO0 F;
    public C4568rO0 G;

    public AbstractC4908tO0(C4568rO0 ro0, C4568rO0 ro02) {
        this.F = ro02;
        this.G = ro0;
    }

    @Override // defpackage.AbstractC5078uO0
    public void a(C4568rO0 ro0) {
        C4568rO0 ro02 = null;
        if (this.F == ro0 && ro0 == this.G) {
            this.G = null;
            this.F = null;
        }
        C4568rO0 ro03 = this.F;
        if (ro03 == ro0) {
            this.F = b(ro03);
        }
        C4568rO0 ro04 = this.G;
        if (ro04 == ro0) {
            C4568rO0 ro05 = this.F;
            if (!(ro04 == ro05 || ro05 == null)) {
                ro02 = c(ro04);
            }
            this.G = ro02;
        }
    }

    public abstract C4568rO0 b(C4568rO0 ro0);

    public abstract C4568rO0 c(C4568rO0 ro0);

    public boolean hasNext() {
        return this.G != null;
    }

    @Override // java.util.Iterator
    public Object next() {
        C4568rO0 ro0 = this.G;
        C4568rO0 ro02 = this.F;
        this.G = (ro0 == ro02 || ro02 == null) ? null : c(ro0);
        return ro0;
    }
}
