package X;

import android.view.WindowInsets;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

@RequiresApi(21)
/* renamed from: X.0MQ  reason: invalid class name */
public class AnonymousClass0MQ extends C03720d8 {
    public AnonymousClass08P A00 = null;

    @Override // X.AnonymousClass0B6
    @NonNull
    public final AnonymousClass08P A00() {
        AnonymousClass08P r0 = this.A00;
        if (r0 != null) {
            return r0;
        }
        WindowInsets windowInsets = this.A01;
        AnonymousClass08P A002 = AnonymousClass08P.A00(windowInsets.getStableInsetLeft(), windowInsets.getStableInsetTop(), windowInsets.getStableInsetRight(), windowInsets.getStableInsetBottom());
        this.A00 = A002;
        return A002;
    }

    @Override // X.AnonymousClass0B6
    @NonNull
    public final AnonymousClass0B7 A04() {
        return AnonymousClass0B7.A01(this.A01.consumeStableInsets());
    }

    @Override // X.AnonymousClass0B6
    @NonNull
    public final AnonymousClass0B7 A05() {
        return AnonymousClass0B7.A01(this.A01.consumeSystemWindowInsets());
    }

    @Override // X.AnonymousClass0B6
    public final boolean A07() {
        return this.A01.isConsumed();
    }

    public AnonymousClass0MQ(@NonNull AnonymousClass0B7 r2, @NonNull WindowInsets windowInsets) {
        super(r2, windowInsets);
    }
}
