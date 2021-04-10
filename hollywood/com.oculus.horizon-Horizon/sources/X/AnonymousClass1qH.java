package X;

import android.graphics.Bitmap;
import com.facebook.imagepipeline.image.QualityInfo;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
/* renamed from: X.1qH  reason: invalid class name */
public final class AnonymousClass1qH extends AnonymousClass1t1 implements AnonymousClass0L4 {
    @GuardedBy("this")
    public AnonymousClass1qa<Bitmap> A00;
    public final int A01;
    public final int A02;
    public final AnonymousClass1tA A03;
    public volatile Bitmap A04;

    /* JADX WARN: Incorrect args count in method signature: (Landroid/graphics/Bitmap;LX/1ou<Landroid/graphics/Bitmap;>;Lcom/facebook/imagepipeline/image/QualityInfo;I)V */
    public AnonymousClass1qH(Bitmap bitmap, AnonymousClass1ou r4, AnonymousClass1tA r5) {
        this.A04 = bitmap;
        this.A00 = AnonymousClass1qa.A01(this.A04, r4);
        this.A03 = r5;
        this.A02 = 0;
        this.A01 = 0;
    }

    public AnonymousClass1qH(AnonymousClass1qa<Bitmap> r2, QualityInfo qualityInfo, int i, int i2) {
        AnonymousClass1qa<Bitmap> A032;
        synchronized (r2) {
            A032 = r2.A05() ? r2.clone() : null;
        }
        if (A032 != null) {
            this.A00 = A032;
            this.A04 = A032.A04();
            this.A03 = qualityInfo;
            this.A02 = i;
            this.A01 = i2;
            return;
        }
        throw null;
    }
}
