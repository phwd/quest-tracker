package X;

import android.os.Build;
import androidx.annotation.NonNull;

/* renamed from: X.07o  reason: invalid class name and case insensitive filesystem */
public final class C001607o {
    public final C001707p A00;

    public C001607o() {
        if (Build.VERSION.SDK_INT >= 29) {
            this.A00 = new AnonymousClass0sP();
        } else {
            this.A00 = new AnonymousClass0sQ();
        }
    }

    public C001607o(@NonNull AnonymousClass07r r3) {
        if (Build.VERSION.SDK_INT >= 29) {
            this.A00 = new AnonymousClass0sP(r3);
        } else {
            this.A00 = new AnonymousClass0sQ(r3);
        }
    }
}
