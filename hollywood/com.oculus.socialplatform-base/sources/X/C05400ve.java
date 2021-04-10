package X;

import android.view.WindowInsets;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

@RequiresApi(20)
/* renamed from: X.0ve  reason: invalid class name and case insensitive filesystem */
public class C05400ve extends AnonymousClass07p {
    public AnonymousClass057 A00 = null;
    @NonNull
    public final WindowInsets A01;

    @Override // X.AnonymousClass07p
    @NonNull
    public final AnonymousClass057 A01() {
        AnonymousClass057 r0 = this.A00;
        if (r0 != null) {
            return r0;
        }
        WindowInsets windowInsets = this.A01;
        AnonymousClass057 A002 = AnonymousClass057.A00(windowInsets.getSystemWindowInsetLeft(), windowInsets.getSystemWindowInsetTop(), windowInsets.getSystemWindowInsetRight(), windowInsets.getSystemWindowInsetBottom());
        this.A00 = A002;
        return A002;
    }

    @Override // X.AnonymousClass07p
    @NonNull
    public C003307q A06(int i, int i2, int i3, int i4) {
        AnonymousClass07n r1 = new AnonymousClass07n(C003307q.A01(this.A01));
        AnonymousClass057 A002 = C003307q.A00(A01(), i, i2, i3, i4);
        AnonymousClass07o r12 = r1.A00;
        r12.A02(A002);
        r12.A01(C003307q.A00(A00(), i, i2, i3, i4));
        return r12.A00();
    }

    @Override // X.AnonymousClass07p
    public final boolean A08() {
        return this.A01.isRound();
    }

    public C05400ve(@NonNull C003307q r2, @NonNull WindowInsets windowInsets) {
        super(r2);
        this.A01 = windowInsets;
    }
}
