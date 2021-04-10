package X;

import android.graphics.Bitmap;
import androidx.annotation.VisibleForTesting;

@VisibleForTesting
/* renamed from: X.1hc  reason: invalid class name and case insensitive filesystem */
public class C09281hc extends AbstractC09291hd<C09311hf> {
    public final C09311hf A00(int i, Bitmap.Config config) {
        C09311hf poll = this.A00.poll();
        if (poll == null) {
            poll = new C09311hf(this);
        }
        C09311hf r0 = (C09311hf) poll;
        r0.A00 = i;
        r0.A01 = config;
        return r0;
    }
}
