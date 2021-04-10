package X;

import android.graphics.Insets;
import android.view.WindowInsets;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

@RequiresApi(api = 29)
/* renamed from: X.0d9  reason: invalid class name and case insensitive filesystem */
public class C03730d9 extends AnonymousClass0B5 {
    public final WindowInsets.Builder A00;

    @Override // X.AnonymousClass0B5
    @NonNull
    public final AnonymousClass0B7 A00() {
        return AnonymousClass0B7.A01(this.A00.build());
    }

    @Override // X.AnonymousClass0B5
    public final void A01(@NonNull AnonymousClass08P r6) {
        this.A00.setStableInsets(Insets.of(r6.A01, r6.A03, r6.A02, r6.A00));
    }

    @Override // X.AnonymousClass0B5
    public final void A02(@NonNull AnonymousClass08P r6) {
        this.A00.setSystemWindowInsets(Insets.of(r6.A01, r6.A03, r6.A02, r6.A00));
    }

    public C03730d9() {
        this.A00 = new WindowInsets.Builder();
    }

    public C03730d9(@NonNull AnonymousClass0B7 r3) {
        WindowInsets.Builder builder;
        WindowInsets A02 = r3.A02();
        if (A02 != null) {
            builder = new WindowInsets.Builder(A02);
        } else {
            builder = new WindowInsets.Builder();
        }
        this.A00 = builder;
    }
}
