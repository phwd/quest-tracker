package X;

import android.os.SystemClock;
import android.util.Log;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* renamed from: X.1f4  reason: invalid class name and case insensitive filesystem */
public final class C08661f4 implements AnonymousClass1fL, AbstractC08981fq {
    public int A00;
    public C08681f6 A01;
    public C06501aM A02;
    public Object A03;
    public final AnonymousClass1fL A04;
    public final AnonymousClass1ez<?> A05;
    public volatile C07091bb<?> A06;

    @Override // X.AnonymousClass1fL
    public final void A6v(AbstractC06491aL r3, Exception exc, AbstractC07051bX<?> r5, AnonymousClass1fM r6) {
        this.A04.A6v(r3, exc, r5, this.A06.A01.A3i());
    }

    @Override // X.AnonymousClass1fL
    public final void A6w(AbstractC06491aL r8, Object obj, AbstractC07051bX<?> r10, AnonymousClass1fM r11, AbstractC06491aL r12) {
        this.A04.A6w(r8, obj, r10, this.A06.A01.A3i(), r8);
    }

    @Override // X.AnonymousClass1fL
    public final void A9L() {
        throw new UnsupportedOperationException();
    }

    /* JADX INFO: finally extract failed */
    @Override // X.AbstractC08981fq
    public final boolean AAU() {
        AnonymousClass1ez<?> r4;
        Object obj = this.A03;
        if (obj != null) {
            this.A03 = null;
            SystemClock.elapsedRealtimeNanos();
            try {
                AnonymousClass1ez<?> r3 = this.A05;
                C07891dd r7 = r3.A02.A02.A04;
                Class<?> cls = obj.getClass();
                synchronized (r7) {
                    Iterator<C07751cx<?>> it = r7.A00.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        C07751cx<?> next = it.next();
                        if (next.A01.isAssignableFrom(cls)) {
                            AbstractC06701at<T> r1 = next.A00;
                            if (r1 != null) {
                                AnonymousClass1dZ r5 = new AnonymousClass1dZ(r1, obj, r3.A05);
                                this.A02 = new C06501aM(this.A06.A00, r3.A04);
                                r3.A07.A00().A8n(this.A02, r5);
                                if (Log.isLoggable("SourceGenerator", 2)) {
                                    SystemClock.elapsedRealtimeNanos();
                                }
                                this.A06.A01.A26();
                                this.A01 = new C08681f6(Collections.singletonList(this.A06.A00), r3, this);
                            }
                        }
                    }
                }
                throw new C07451cE(cls);
            } catch (Throwable th) {
                this.A06.A01.A26();
                throw th;
            }
        }
        C08681f6 r0 = this.A01;
        if (r0 != null && r0.AAU()) {
            return true;
        }
        this.A01 = null;
        this.A06 = null;
        while (true) {
            int i = this.A00;
            r4 = this.A05;
            if (i >= r4.A03().size()) {
                return false;
            }
            List<C07091bb<?>> A032 = r4.A03();
            int i2 = this.A00;
            this.A00 = i2 + 1;
            this.A06 = A032.get(i2);
            if (this.A06 == null || (!r4.A06.A00(this.A06.A01.A3i()) && r4.A01(this.A06.A01.A3h()) == null)) {
            }
        }
        this.A06.A01.A6H(r4.A03, new C08741fD(this, this.A06));
        return true;
    }

    @Override // X.AbstractC08981fq
    public final void cancel() {
        C07091bb<?> r0 = this.A06;
        if (r0 != null) {
            r0.A01.cancel();
        }
    }

    public C08661f4(AnonymousClass1ez<?> r1, AnonymousClass1fL r2) {
        this.A05 = r1;
        this.A04 = r2;
    }
}
