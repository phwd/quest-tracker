package X;

import android.graphics.Bitmap;
import com.facebook.imagepipeline.memory.PoolBackend;
import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.1sK  reason: invalid class name */
public final class AnonymousClass1sK implements AnonymousClass0Ox {
    public int A00;
    public int A01 = 4194304;
    public final PoolBackend<Bitmap> A02 = new AnonymousClass1sJ();
    public final AbstractC10691uo A03;

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AnonymousClass1sV
    public final Bitmap get(int i) {
        Throwable th;
        I i2;
        synchronized (this) {
            int i3 = this.A00;
            if (i3 > 0) {
                while (i3 > 0) {
                    PoolBackend<Bitmap> poolBackend = this.A02;
                    AnonymousClass1sM<T> r4 = poolBackend.A00;
                    synchronized (r4) {
                        try {
                            AnonymousClass1sN<T> r3 = r4.A01;
                            if (r3 == null) {
                                i2 = null;
                            } else {
                                i2 = r3.A03.pollLast();
                                if (r3.A03.isEmpty()) {
                                    AnonymousClass1sM.A00(r4, r3);
                                    r4.A02.remove(r3.A00);
                                }
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            throw th;
                        }
                    }
                    if (i2 != null) {
                        synchronized (poolBackend) {
                            try {
                                poolBackend.A01.remove(i2);
                            } catch (Throwable th3) {
                                th = th3;
                                throw th;
                            }
                        }
                    }
                    I i4 = i2;
                    if (i4 == null) {
                        break;
                    }
                    i3 = this.A00 - poolBackend.A01(i4);
                    this.A00 = i3;
                }
            }
            PoolBackend<Bitmap> poolBackend2 = this.A02;
            Bitmap bitmap = (Bitmap) poolBackend2.A02(i);
            if (bitmap != null) {
                this.A00 -= poolBackend2.A01(bitmap);
                return bitmap;
            }
            return Bitmap.createBitmap(1, i, Bitmap.Config.ALPHA_8);
        }
    }

    public AnonymousClass1sK(AbstractC10691uo r3) {
        this.A03 = r3;
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // X.AnonymousClass1sV, X.AnonymousClass1ou
    public final void A86(Bitmap bitmap) {
        PoolBackend<Bitmap> poolBackend = this.A02;
        int A012 = poolBackend.A01(bitmap);
        if (A012 <= this.A01) {
            poolBackend.A03(bitmap);
            synchronized (this) {
                this.A00 += A012;
            }
        }
    }
}
