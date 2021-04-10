package X;

import android.os.Build;
import androidx.annotation.NonNull;

/* renamed from: X.0B4  reason: invalid class name */
public final class AnonymousClass0B4 {
    public final AnonymousClass0B5 A00;

    public AnonymousClass0B4() {
        if (Build.VERSION.SDK_INT >= 29) {
            this.A00 = new C03730d9();
        } else {
            this.A00 = new C03740dA();
        }
    }

    public AnonymousClass0B4(@NonNull AnonymousClass0B7 r3) {
        if (Build.VERSION.SDK_INT >= 29) {
            this.A00 = new C03730d9(r3);
        } else {
            this.A00 = new C03740dA(r3);
        }
    }
}
