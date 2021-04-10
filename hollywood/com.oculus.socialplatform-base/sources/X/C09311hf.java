package X;

import android.graphics.Bitmap;
import androidx.annotation.VisibleForTesting;
import java.util.Queue;

@VisibleForTesting
/* renamed from: X.1hf  reason: invalid class name and case insensitive filesystem */
public final class C09311hf implements AbstractC09351hm {
    public int A00;
    public Bitmap.Config A01;
    public final C09281hc A02;

    @Override // X.AbstractC09351hm
    public final void A6b() {
        Queue<T> queue = this.A02.A00;
        if (queue.size() < 20) {
            queue.offer(this);
        }
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof C09311hf)) {
            return false;
        }
        C09311hf r4 = (C09311hf) obj;
        if (this.A00 != r4.A00 || !C08381eW.A07(this.A01, r4.A01)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        int i;
        int i2 = this.A00 * 31;
        Bitmap.Config config = this.A01;
        if (config != null) {
            i = config.hashCode();
        } else {
            i = 0;
        }
        return i2 + i;
    }

    public final String toString() {
        return AnonymousClass1hY.A00(this.A00, this.A01);
    }

    public C09311hf(C09281hc r1) {
        this.A02 = r1;
    }
}
