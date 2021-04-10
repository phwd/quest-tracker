package defpackage;

import java.util.Iterator;

/* renamed from: sO0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4738sO0 extends AbstractC5078uO0 implements Iterator {
    public C4568rO0 F;
    public boolean G = true;
    public final /* synthetic */ C5248vO0 H;

    public C4738sO0(C5248vO0 vo0) {
        this.H = vo0;
    }

    @Override // defpackage.AbstractC5078uO0
    public void a(C4568rO0 ro0) {
        C4568rO0 ro02 = this.F;
        if (ro0 == ro02) {
            C4568rO0 ro03 = ro02.I;
            this.F = ro03;
            this.G = ro03 == null;
        }
    }

    public boolean hasNext() {
        if (this.G) {
            return this.H.F != null;
        }
        C4568rO0 ro0 = this.F;
        return (ro0 == null || ro0.H == null) ? false : true;
    }

    @Override // java.util.Iterator
    public Object next() {
        if (this.G) {
            this.G = false;
            this.F = this.H.F;
        } else {
            C4568rO0 ro0 = this.F;
            this.F = ro0 != null ? ro0.H : null;
        }
        return this.F;
    }
}
