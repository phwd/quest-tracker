package X;

import androidx.annotation.VisibleForTesting;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
@VisibleForTesting
/* renamed from: X.1hw  reason: invalid class name */
public class AnonymousClass1hw {
    public int A00;
    public int A01;

    public final void A00(int i) {
        int i2;
        int i3 = this.A01;
        if (i3 < i || (i2 = this.A00) <= 0) {
            AnonymousClass0J5.A06("com.facebook.imagepipeline.memory.BasePool.Counter", "Unexpected decrement of %d. Current numBytes = %d, count = %d", Integer.valueOf(i), Integer.valueOf(i3), Integer.valueOf(this.A00));
            return;
        }
        this.A00 = i2 - 1;
        this.A01 = i3 - i;
    }
}
