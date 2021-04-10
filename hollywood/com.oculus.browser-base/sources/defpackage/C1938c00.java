package defpackage;

import android.graphics.Bitmap;
import android.os.Looper;
import org.chromium.base.Callback;

/* renamed from: c00  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1938c00 extends MZ {
    public MZ b;
    public C0143Ch c;
    public int d;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C1938c00(MZ mz, PF pf, int i) {
        super(mz);
        Runtime runtime = Runtime.getRuntime();
        C0143Ch ch = new C0143Ch(pf, Math.min((int) Math.max(1.0f, ((float) (runtime.maxMemory() - (runtime.totalMemory() - runtime.freeMemory()))) * 0.125f), i));
        this.c = ch;
        this.b = mz;
        mz.e();
        if (this.b.e() == 0) {
            this.d = 2;
        } else if (this.b.e() == 1) {
            this.d = 3;
        } else {
            this.d = 2;
        }
    }

    @Override // defpackage.MZ
    public void a() {
        this.c.b().f(-1);
        Looper.myQueue().addIdleHandler(new C5972zh());
    }

    @Override // defpackage.MZ
    public void b() {
        MZ mz = this.b;
        if (mz != null) {
            mz.b();
            this.b = null;
        }
        C0143Ch ch = this.c;
        if (ch != null) {
            PF pf = ch.e;
            OF of = ch.d;
            if (pf.f8679a.contains(of)) {
                of.f8611a = null;
                pf.f8679a.remove(of);
            }
            ch.d = null;
            this.c = null;
        }
    }

    @Override // defpackage.MZ
    public void c(LZ lz, Callback callback) {
        this.b.c(lz, callback);
    }

    @Override // defpackage.MZ
    public void d(LZ lz, Callback callback) {
        Bitmap bitmap;
        String str = lz.f8424a;
        int i = lz.c;
        int i2 = lz.d;
        if (this.c == null) {
            bitmap = null;
        } else {
            bitmap = this.c.a(h(str, i, i2));
        }
        if (bitmap == null) {
            this.b.d(lz, new C1767b00(this, lz, callback));
            return;
        }
        f(lz.b, 8);
        callback.onResult(bitmap);
    }

    @Override // defpackage.MZ
    public int e() {
        return this.d;
    }

    public String h(String str, int i, int i2) {
        return str + "/" + i + "/" + i2;
    }
}
