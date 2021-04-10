package X;

import android.graphics.Bitmap;
import com.facebook.imagepipeline.memory.PoolBackend;
import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.1iT  reason: invalid class name */
public final class AnonymousClass1iT implements AnonymousClass0VL {
    public int A00;
    public int A01 = 4194304;
    public final PoolBackend<Bitmap> A02 = new AnonymousClass1iL();
    public final AnonymousClass1i3 A03;

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AbstractC04990rf
    public final Bitmap get(int i) {
        I i2;
        synchronized (this) {
            int i3 = this.A00;
            if (i3 > 0) {
                while (i3 > 0) {
                    PoolBackend<Bitmap> poolBackend = this.A02;
                    AnonymousClass1iW<T> r4 = poolBackend.A00;
                    synchronized (r4) {
                        AnonymousClass1iX<T> r3 = r4.A01;
                        if (r3 == null) {
                            i2 = null;
                        } else {
                            i2 = r3.A03.pollLast();
                            if (r3.A03.isEmpty()) {
                                AnonymousClass1iW.A00(r4, r3);
                                r4.A02.remove(r3.A00);
                            }
                        }
                    }
                    if (i2 != null) {
                        synchronized (poolBackend) {
                            poolBackend.A01.remove(i2);
                        }
                    }
                    I i4 = i2;
                    if (i4 == null) {
                        break;
                    }
                    i3 = this.A00 - C01110Pz.A01(i4);
                    this.A00 = i3;
                }
            }
            Bitmap bitmap = (Bitmap) this.A02.A01(i);
            if (bitmap != null) {
                this.A00 -= C01110Pz.A01(bitmap);
                return bitmap;
            }
            return Bitmap.createBitmap(1, i, Bitmap.Config.ALPHA_8);
        }
    }

    public AnonymousClass1iT(AnonymousClass1i3 r3) {
        this.A03 = r3;
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // X.AbstractC04990rf, X.AbstractC00840Jw
    public final void A8y(Bitmap bitmap) {
        PoolBackend<Bitmap> poolBackend = this.A02;
        int A012 = C01110Pz.A01(bitmap);
        if (A012 <= this.A01) {
            poolBackend.A02(bitmap);
            synchronized (this) {
                this.A00 += A012;
            }
        }
    }
}
