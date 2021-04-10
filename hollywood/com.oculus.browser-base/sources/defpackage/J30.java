package defpackage;

/* renamed from: J30  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class J30 implements AbstractC1617aA0 {
    public AbstractC1797bA0 F;

    @Override // defpackage.AbstractC1617aA0
    public void G(int i) {
    }

    @Override // defpackage.AbstractC1617aA0
    public void U() {
        AbstractC1797bA0 ba0 = this.F;
        if (ba0 != null) {
            ((C4018oA0) ba0).j0(1);
        }
    }

    @Override // defpackage.AbstractC0543Ix
    public void Y(C5475wl0 wl0) {
    }

    @Override // defpackage.AbstractC1617aA0
    public void a0(BB0 bb0) {
    }

    @Override // java.io.Closeable, defpackage.AbstractC3313k30, java.lang.AutoCloseable
    public void close() {
    }

    @Override // defpackage.AbstractC1617aA0
    public void n(AbstractC1797bA0 ba0, C1401Wz0[] wz0Arr, C2788gz0 gz0, C1523Yz0 yz0, boolean z) {
        this.F = ba0;
    }

    @Override // defpackage.AbstractC1617aA0
    public void o() {
        AbstractC1797bA0 ba0 = this.F;
        if (ba0 != null) {
            ((C4018oA0) ba0).g0(1);
        }
    }

    @Override // defpackage.AbstractC1617aA0
    public void p() {
    }

    @Override // defpackage.AbstractC1617aA0
    public void q(C2788gz0 gz0) {
    }

    @Override // defpackage.AbstractC1617aA0
    public void y() {
    }

    @Override // defpackage.AbstractC1617aA0
    public void z(boolean z, boolean z2) {
        AbstractC1797bA0 ba0 = this.F;
        if (ba0 != null) {
            ((C4018oA0) ba0).i0(1, "Web Payments API is disabled.");
            ((AbstractC2459f30) this.F).close();
        }
    }
}
