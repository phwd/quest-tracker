package X;

import android.graphics.Insets;
import android.view.WindowInsets;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

@RequiresApi(api = 29)
/* renamed from: X.0vf  reason: invalid class name and case insensitive filesystem */
public class C05410vf extends AnonymousClass07o {
    public final WindowInsets.Builder A00;

    @Override // X.AnonymousClass07o
    @NonNull
    public final C003307q A00() {
        return C003307q.A01(this.A00.build());
    }

    @Override // X.AnonymousClass07o
    public final void A01(@NonNull AnonymousClass057 r6) {
        this.A00.setStableInsets(Insets.of(r6.A01, r6.A03, r6.A02, r6.A00));
    }

    @Override // X.AnonymousClass07o
    public final void A02(@NonNull AnonymousClass057 r6) {
        this.A00.setSystemWindowInsets(Insets.of(r6.A01, r6.A03, r6.A02, r6.A00));
    }

    public C05410vf() {
        this.A00 = new WindowInsets.Builder();
    }

    public C05410vf(@NonNull C003307q r3) {
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
