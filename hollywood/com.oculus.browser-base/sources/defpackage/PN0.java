package defpackage;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Executor;
import org.chromium.mojo.system.impl.WatcherImpl;

/* renamed from: PN0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PN0 implements LN0 {
    public final C2784gy F;
    public AbstractC3426kk0 G;
    public long H = 1;
    public Map I = new HashMap();

    /* renamed from: J  reason: collision with root package name */
    public final Executor f8687J;

    public PN0(AbstractC1552Zj0 zj0) {
        WatcherImpl a2 = AbstractC5802yh.a(zj0);
        C2784gy gyVar = new C2784gy(zj0, a2);
        this.F = gyVar;
        gyVar.I = new NN0(this, null);
        SA l = zj0.l();
        if (l != null) {
            this.f8687J = AbstractC3880nM.a(l);
        } else {
            this.f8687J = null;
        }
    }

    public void Y(AbstractC0543Ix ix) {
        this.F.f10037J = ix;
    }

    @Override // defpackage.AbstractC3255jk0
    public boolean b(C2740gj0 gj0) {
        return this.F.b(gj0);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable, defpackage.AbstractC3255jk0
    public void close() {
        this.F.close();
    }

    public void f0(AbstractC3426kk0 kk0) {
        this.G = kk0;
    }

    @Override // defpackage.AbstractC3426kk0
    public boolean g(C2740gj0 gj0, AbstractC3255jk0 jk0) {
        IS0 a2 = gj0.a();
        long j = this.H;
        long j2 = j + 1;
        this.H = j2;
        if (j == 0) {
            this.H = 1 + j2;
            j = j2;
        }
        if (!this.I.containsKey(Long.valueOf(j))) {
            C0942Pj0 pj0 = a2.d;
            ByteBuffer byteBuffer = a2.f10015a;
            Objects.requireNonNull(pj0);
            byteBuffer.putLong(24, j);
            pj0.f = j;
            if (!this.F.b(a2)) {
                return false;
            }
            this.I.put(Long.valueOf(j), jk0);
            return true;
        }
        throw new IllegalStateException("Unable to find a new request identifier.");
    }

    public void g0() {
        C2784gy gyVar = this.F;
        gyVar.H.a(gyVar.G, RA.c, gyVar.F);
    }

    @Override // defpackage.RW
    public PW x() {
        return this.F.x();
    }
}
