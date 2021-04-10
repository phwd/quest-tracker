package X;

import android.graphics.Insets;
import android.view.WindowInsets;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

@RequiresApi(api = 29)
/* renamed from: X.0sP  reason: invalid class name */
public class AnonymousClass0sP extends C001707p {
    public final WindowInsets.Builder A00;

    @Override // X.C001707p
    @NonNull
    public final AnonymousClass07r A00() {
        WindowInsets build = this.A00.build();
        if (build != null) {
            return new AnonymousClass07r(build);
        }
        throw null;
    }

    @Override // X.C001707p
    public final void A01(@NonNull AnonymousClass053 r6) {
        this.A00.setSystemWindowInsets(Insets.of(r6.A01, r6.A03, r6.A02, r6.A00));
    }

    public AnonymousClass0sP() {
        this.A00 = new WindowInsets.Builder();
    }

    public AnonymousClass0sP(@NonNull AnonymousClass07r r3) {
        WindowInsets.Builder builder;
        WindowInsets A002 = r3.A00();
        if (A002 != null) {
            builder = new WindowInsets.Builder(A002);
        } else {
            builder = new WindowInsets.Builder();
        }
        this.A00 = builder;
    }
}
