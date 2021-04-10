package X;

import android.os.Build;
import androidx.annotation.NonNull;

/* renamed from: X.07n  reason: invalid class name */
public final class AnonymousClass07n {
    public final AnonymousClass07o A00;

    public AnonymousClass07n() {
        if (Build.VERSION.SDK_INT >= 29) {
            this.A00 = new C05410vf();
        } else {
            this.A00 = new C05420vg();
        }
    }

    public AnonymousClass07n(@NonNull C003307q r3) {
        if (Build.VERSION.SDK_INT >= 29) {
            this.A00 = new C05410vf(r3);
        } else {
            this.A00 = new C05420vg(r3);
        }
    }
}
