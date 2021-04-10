package defpackage;

import J.N;
import org.chromium.base.Callback;

/* renamed from: Mn0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0767Mn0 extends MZ {
    public C0767Mn0(PZ pz) {
        super(pz);
    }

    @Override // defpackage.MZ
    public void a() {
    }

    @Override // defpackage.MZ
    public void b() {
    }

    @Override // defpackage.MZ
    public void c(LZ lz, Callback callback) {
        N.Mq$OMPDL(this.f8483a.f8698a, 0, lz.f8424a, lz.b, lz.e, new NZ(callback));
    }

    @Override // defpackage.MZ
    public void d(LZ lz, Callback callback) {
        long currentTimeMillis = System.currentTimeMillis();
        N.MVa6KqKn(this.f8483a.f8698a, 0, lz.f8424a, lz.b, lz.e, new OZ(new C0707Ln0(this, callback, lz, currentTimeMillis), lz));
    }

    @Override // defpackage.MZ
    public int e() {
        return 0;
    }
}
