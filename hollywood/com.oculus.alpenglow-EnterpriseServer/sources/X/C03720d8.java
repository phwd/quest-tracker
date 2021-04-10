package X;

import android.view.WindowInsets;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import com.squareup.okhttp.internal.http.HttpEngine;

@RequiresApi(HttpEngine.MAX_FOLLOW_UPS)
/* renamed from: X.0d8  reason: invalid class name and case insensitive filesystem */
public class C03720d8 extends AnonymousClass0B6 {
    public AnonymousClass08P A00 = null;
    @NonNull
    public final WindowInsets A01;

    @Override // X.AnonymousClass0B6
    @NonNull
    public final AnonymousClass08P A01() {
        AnonymousClass08P r0 = this.A00;
        if (r0 != null) {
            return r0;
        }
        WindowInsets windowInsets = this.A01;
        AnonymousClass08P A002 = AnonymousClass08P.A00(windowInsets.getSystemWindowInsetLeft(), windowInsets.getSystemWindowInsetTop(), windowInsets.getSystemWindowInsetRight(), windowInsets.getSystemWindowInsetBottom());
        this.A00 = A002;
        return A002;
    }

    @Override // X.AnonymousClass0B6
    @NonNull
    public AnonymousClass0B7 A06(int i, int i2, int i3, int i4) {
        AnonymousClass0B4 r1 = new AnonymousClass0B4(AnonymousClass0B7.A01(this.A01));
        AnonymousClass08P A002 = AnonymousClass0B7.A00(A01(), i, i2, i3, i4);
        AnonymousClass0B5 r12 = r1.A00;
        r12.A02(A002);
        r12.A01(AnonymousClass0B7.A00(A00(), i, i2, i3, i4));
        return r12.A00();
    }

    @Override // X.AnonymousClass0B6
    public final boolean A08() {
        return this.A01.isRound();
    }

    public C03720d8(@NonNull AnonymousClass0B7 r2, @NonNull WindowInsets windowInsets) {
        super(r2);
        this.A01 = windowInsets;
    }
}
