package X;

import android.content.Context;
import androidx.annotation.RequiresApi;

/* renamed from: X.0c8  reason: invalid class name and case insensitive filesystem */
public final class C03340c8 implements AnonymousClass0GV {
    public C01310Gc A00;
    public boolean A01;
    public final Context A02;
    public final AnonymousClass0GR A03;
    public final Object A04 = new Object();
    public final String A05;

    private C01310Gc A00() {
        C01310Gc r0;
        synchronized (this.A04) {
            if (this.A00 == null) {
                String str = this.A05;
                C01310Gc r1 = new C01310Gc(this.A02, str, new C03350c9[1], this.A03);
                this.A00 = r1;
                r1.setWriteAheadLoggingEnabled(this.A01);
            }
            r0 = this.A00;
        }
        return r0;
    }

    @Override // X.AnonymousClass0GV
    public final String A3K() {
        return this.A05;
    }

    @Override // X.AnonymousClass0GV
    @RequiresApi(api = 16)
    public final void A8H(boolean z) {
        synchronized (this.A04) {
            C01310Gc r0 = this.A00;
            if (r0 != null) {
                r0.setWriteAheadLoggingEnabled(z);
            }
            this.A01 = z;
        }
    }

    public C03340c8(Context context, String str, AnonymousClass0GR r4) {
        this.A02 = context;
        this.A05 = str;
        this.A03 = r4;
    }

    @Override // X.AnonymousClass0GV
    public final AnonymousClass0GQ A4y() {
        return A00().A01();
    }

    @Override // X.AnonymousClass0GV, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        A00().close();
    }
}
