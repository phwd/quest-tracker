package X;

import android.content.ComponentName;
import android.content.Intent;

/* renamed from: X.03V  reason: invalid class name */
public abstract class AnonymousClass03V {
    public int A00;
    public boolean A01;
    public final ComponentName A02;

    public void A00() {
    }

    public void A01() {
    }

    public void A02() {
    }

    public abstract void A04(Intent intent);

    public final void A03(int i) {
        if (!this.A01) {
            this.A01 = true;
            this.A00 = i;
            return;
        }
        int i2 = this.A00;
        if (i2 != i) {
            throw new IllegalArgumentException(AnonymousClass006.A03("Given job ID ", i, " is different than previous ", i2));
        }
    }

    public AnonymousClass03V(ComponentName componentName) {
        this.A02 = componentName;
    }
}
