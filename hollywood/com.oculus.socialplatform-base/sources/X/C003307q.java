package X;

import android.os.Build;
import android.view.WindowInsets;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import java.util.Objects;

/* renamed from: X.07q  reason: invalid class name and case insensitive filesystem */
public final class C003307q {
    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public static final C003307q A01 = new AnonymousClass07n().A00.A00().A00.A03().A00.A04().A00.A05();
    public final AnonymousClass07p A00;

    public static AnonymousClass057 A00(AnonymousClass057 r5, int i, int i2, int i3, int i4) {
        int max = Math.max(0, r5.A01 - i);
        int max2 = Math.max(0, r5.A03 - i2);
        int max3 = Math.max(0, r5.A02 - i3);
        int max4 = Math.max(0, r5.A00 - i4);
        if (max == i && max2 == i2 && max3 == i3 && max4 == i4) {
            return r5;
        }
        return AnonymousClass057.A00(max, max2, max3, max4);
    }

    @NonNull
    @RequiresApi(20)
    public static C003307q A01(@NonNull WindowInsets windowInsets) {
        if (windowInsets != null) {
            return new C003307q(windowInsets);
        }
        throw null;
    }

    @Nullable
    @RequiresApi(20)
    public final WindowInsets A02() {
        AnonymousClass07p r1 = this.A00;
        if (r1 instanceof C05400ve) {
            return ((C05400ve) r1).A01;
        }
        return null;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C003307q)) {
            return false;
        }
        return Objects.equals(this.A00, ((C003307q) obj).A00);
    }

    public final int hashCode() {
        AnonymousClass07p r0 = this.A00;
        if (r0 == null) {
            return 0;
        }
        return r0.hashCode();
    }

    @RequiresApi(20)
    public C003307q(@NonNull WindowInsets windowInsets) {
        AnonymousClass07p r0;
        int i = Build.VERSION.SDK_INT;
        if (i >= 29) {
            r0 = new C002405l(this, windowInsets);
        } else if (i >= 28) {
            r0 = new AnonymousClass0In(this, windowInsets);
        } else {
            r0 = new AnonymousClass0W9(this, windowInsets);
        }
        this.A00 = r0;
    }

    public C003307q() {
        this.A00 = new AnonymousClass07p(this);
    }
}
