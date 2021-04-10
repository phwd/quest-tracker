package X;

import android.view.WindowInsets;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

@RequiresApi(21)
/* renamed from: X.0W9  reason: invalid class name */
public class AnonymousClass0W9 extends C05400ve {
    public AnonymousClass057 A00 = null;

    @Override // X.AnonymousClass07p
    @NonNull
    public final AnonymousClass057 A00() {
        AnonymousClass057 r0 = this.A00;
        if (r0 != null) {
            return r0;
        }
        WindowInsets windowInsets = this.A01;
        AnonymousClass057 A002 = AnonymousClass057.A00(windowInsets.getStableInsetLeft(), windowInsets.getStableInsetTop(), windowInsets.getStableInsetRight(), windowInsets.getStableInsetBottom());
        this.A00 = A002;
        return A002;
    }

    @Override // X.AnonymousClass07p
    @NonNull
    public final C003307q A04() {
        return C003307q.A01(this.A01.consumeStableInsets());
    }

    @Override // X.AnonymousClass07p
    @NonNull
    public final C003307q A05() {
        return C003307q.A01(this.A01.consumeSystemWindowInsets());
    }

    @Override // X.AnonymousClass07p
    public final boolean A07() {
        return this.A01.isConsumed();
    }

    public AnonymousClass0W9(@NonNull C003307q r2, @NonNull WindowInsets windowInsets) {
        super(r2, windowInsets);
    }
}
