package X;

import android.graphics.Bitmap;
import com.facebook.imagepipeline.image.QualityInfo;
import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.05g  reason: invalid class name and case insensitive filesystem */
public final class C002305g extends AnonymousClass0I0 {
    @GuardedBy("this")
    public AbstractC00820Ju<Bitmap> A00;
    public final int A01;
    public final int A02;
    public final C03410mW A03;
    public volatile Bitmap A04;

    @Override // X.AnonymousClass0VM
    public final synchronized boolean A03() {
        boolean z;
        z = false;
        if (this.A00 == null) {
            z = true;
        }
        return z;
    }

    @Override // java.io.Closeable, X.AnonymousClass0VM, java.lang.AutoCloseable
    public final void close() {
        AbstractC00820Ju<Bitmap> r1;
        synchronized (this) {
            r1 = this.A00;
            this.A00 = null;
            this.A04 = null;
        }
        if (r1 != null) {
            r1.close();
        }
    }

    @Override // X.AnonymousClass0VM
    public final int A00() {
        return C01110Pz.A01(this.A04);
    }

    @Override // X.AnonymousClass0mX
    public final int A44() {
        int i;
        if (this.A02 % 180 != 0 || (i = this.A01) == 5 || i == 7) {
            Bitmap bitmap = this.A04;
            if (bitmap != null) {
                return bitmap.getWidth();
            }
            return 0;
        }
        Bitmap bitmap2 = this.A04;
        if (bitmap2 != null) {
            return bitmap2.getHeight();
        }
        return 0;
    }

    @Override // X.AnonymousClass0mX
    public final int A5L() {
        int i;
        if (this.A02 % 180 != 0 || (i = this.A01) == 5 || i == 7) {
            Bitmap bitmap = this.A04;
            if (bitmap != null) {
                return bitmap.getHeight();
            }
            return 0;
        }
        Bitmap bitmap2 = this.A04;
        if (bitmap2 != null) {
            return bitmap2.getWidth();
        }
        return 0;
    }

    @Override // X.AnonymousClass0VM
    public final C03410mW A01() {
        return this.A03;
    }

    @Override // X.AnonymousClass0I0
    public final Bitmap A04() {
        return this.A04;
    }

    /* JADX WARN: Incorrect args count in method signature: (Landroid/graphics/Bitmap;LX/0Jw<Landroid/graphics/Bitmap;>;Lcom/facebook/imagepipeline/image/QualityInfo;I)V */
    public C002305g(Bitmap bitmap, AbstractC00840Jw r4, C03410mW r5) {
        this.A04 = bitmap;
        this.A00 = AbstractC00820Ju.A01(this.A04, r4);
        this.A03 = r5;
        this.A02 = 0;
        this.A01 = 0;
    }

    public C002305g(AbstractC00820Ju<Bitmap> r2, QualityInfo qualityInfo, int i, int i2) {
        AbstractC00820Ju<Bitmap> A05;
        synchronized (r2) {
            A05 = r2.A07() ? r2.clone() : null;
        }
        if (A05 != null) {
            this.A00 = A05;
            this.A04 = A05.A06();
            this.A03 = qualityInfo;
            this.A02 = i;
            this.A01 = i2;
            return;
        }
        throw null;
    }
}
