package X;

import android.graphics.Bitmap;
import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.concurrent.GuardedBy;

@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.1rU  reason: invalid class name */
public final class AnonymousClass1rU {
    @GuardedBy("this")
    public int A00;
    @GuardedBy("this")
    public long A01;
    public final int A02;
    public final int A03;
    public final AnonymousClass1ou<Bitmap> A04;

    public AnonymousClass1rU(int i) {
        boolean z = true;
        AnonymousClass0KU.A01(true);
        AnonymousClass0KU.A01(Boolean.valueOf(i <= 0 ? false : z));
        this.A02 = 384;
        this.A03 = i;
        this.A04 = new AnonymousClass1rO(this);
    }
}
