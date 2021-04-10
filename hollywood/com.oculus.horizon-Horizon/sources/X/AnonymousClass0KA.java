package X;

import android.view.WindowInsets;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

@RequiresApi(21)
/* renamed from: X.0KA  reason: invalid class name */
public class AnonymousClass0KA extends AnonymousClass0sO {
    public AnonymousClass053 A00 = null;

    @Override // X.C001807q
    @NonNull
    public final AnonymousClass053 A00() {
        AnonymousClass053 r0 = this.A00;
        if (r0 == null) {
            WindowInsets windowInsets = this.A01;
            int stableInsetLeft = windowInsets.getStableInsetLeft();
            int stableInsetTop = windowInsets.getStableInsetTop();
            int stableInsetRight = windowInsets.getStableInsetRight();
            int stableInsetBottom = windowInsets.getStableInsetBottom();
            if (stableInsetLeft == 0 && stableInsetTop == 0 && stableInsetRight == 0 && stableInsetBottom == 0) {
                r0 = AnonymousClass053.A04;
            } else {
                r0 = new AnonymousClass053(stableInsetLeft, stableInsetTop, stableInsetRight, stableInsetBottom);
            }
            this.A00 = r0;
        }
        return r0;
    }

    @Override // X.C001807q
    @NonNull
    public final AnonymousClass07r A04() {
        WindowInsets consumeStableInsets = this.A01.consumeStableInsets();
        if (consumeStableInsets != null) {
            return new AnonymousClass07r(consumeStableInsets);
        }
        throw null;
    }

    @Override // X.C001807q
    @NonNull
    public final AnonymousClass07r A05() {
        WindowInsets consumeSystemWindowInsets = this.A01.consumeSystemWindowInsets();
        if (consumeSystemWindowInsets != null) {
            return new AnonymousClass07r(consumeSystemWindowInsets);
        }
        throw null;
    }

    @Override // X.C001807q
    public final boolean A07() {
        return this.A01.isConsumed();
    }

    public AnonymousClass0KA(@NonNull AnonymousClass07r r2, @NonNull WindowInsets windowInsets) {
        super(r2, windowInsets);
    }
}
