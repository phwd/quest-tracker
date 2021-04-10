package defpackage;

import J.N;
import java.util.Objects;
import org.chromium.mojo.system.impl.CoreImpl;

/* renamed from: QW  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class QW implements PW {
    public int F;
    public CoreImpl G;

    public QW(CoreImpl coreImpl, int i) {
        this.G = coreImpl;
        this.F = i;
    }

    @Override // defpackage.PW
    public boolean a() {
        return this.F != 0;
    }

    @Override // defpackage.PW, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        int i = this.F;
        if (i != 0) {
            this.F = 0;
            CoreImpl coreImpl = this.G;
            Objects.requireNonNull(coreImpl);
            int MLQe1QEw = N.MLQe1QEw(coreImpl, i);
            if (MLQe1QEw != 0) {
                throw new C5475wl0(MLQe1QEw);
            }
        }
    }

    @Override // java.lang.Object
    public final void finalize() {
        if (a()) {
            AbstractC1220Ua0.f("HandleImpl", "Handle was not closed.", new Object[0]);
            CoreImpl coreImpl = this.G;
            int i = this.F;
            Objects.requireNonNull(coreImpl);
            N.MLQe1QEw(coreImpl, i);
        }
        super.finalize();
    }

    @Override // defpackage.PW
    public int h() {
        int i = this.F;
        this.F = 0;
        return i;
    }

    @Override // defpackage.PW
    public SA l() {
        return this.G;
    }

    @Override // defpackage.PW
    public Qp1 r() {
        return new Rp1(this);
    }

    public QW(QW qw) {
        this.G = qw.G;
        int i = qw.F;
        qw.F = 0;
        this.F = i;
    }
}
