package X;

import android.view.WindowInsets;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

@RequiresApi(20)
/* renamed from: X.0sO  reason: invalid class name */
public class AnonymousClass0sO extends C001807q {
    public AnonymousClass053 A00 = null;
    @NonNull
    public final WindowInsets A01;

    @Override // X.C001807q
    @NonNull
    public final AnonymousClass053 A01() {
        AnonymousClass053 r0 = this.A00;
        if (r0 == null) {
            WindowInsets windowInsets = this.A01;
            int systemWindowInsetLeft = windowInsets.getSystemWindowInsetLeft();
            int systemWindowInsetTop = windowInsets.getSystemWindowInsetTop();
            int systemWindowInsetRight = windowInsets.getSystemWindowInsetRight();
            int systemWindowInsetBottom = windowInsets.getSystemWindowInsetBottom();
            if (systemWindowInsetLeft == 0 && systemWindowInsetTop == 0 && systemWindowInsetRight == 0 && systemWindowInsetBottom == 0) {
                r0 = AnonymousClass053.A04;
            } else {
                r0 = new AnonymousClass053(systemWindowInsetLeft, systemWindowInsetTop, systemWindowInsetRight, systemWindowInsetBottom);
            }
            this.A00 = r0;
        }
        return r0;
    }

    @Override // X.C001807q
    public final boolean A06() {
        return this.A01.isRound();
    }

    public AnonymousClass0sO(@NonNull AnonymousClass07r r2, @NonNull WindowInsets windowInsets) {
        super(r2);
        this.A01 = windowInsets;
    }
}
