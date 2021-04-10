package X;

import android.os.Build;
import android.view.WindowInsets;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import com.squareup.okhttp.internal.http.HttpEngine;
import java.util.Objects;

/* renamed from: X.0B7  reason: invalid class name */
public final class AnonymousClass0B7 {
    @RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
    public static final AnonymousClass0B7 A01 = new AnonymousClass0B4().A00.A00().A00.A03().A00.A04().A00.A05();
    public final AnonymousClass0B6 A00;

    public static AnonymousClass08P A00(AnonymousClass08P r5, int i, int i2, int i3, int i4) {
        int max = Math.max(0, r5.A01 - i);
        int max2 = Math.max(0, r5.A03 - i2);
        int max3 = Math.max(0, r5.A02 - i3);
        int max4 = Math.max(0, r5.A00 - i4);
        if (max == i && max2 == i2 && max3 == i3 && max4 == i4) {
            return r5;
        }
        return AnonymousClass08P.A00(max, max2, max3, max4);
    }

    @NonNull
    @RequiresApi(HttpEngine.MAX_FOLLOW_UPS)
    public static AnonymousClass0B7 A01(@NonNull WindowInsets windowInsets) {
        if (windowInsets != null) {
            return new AnonymousClass0B7(windowInsets);
        }
        throw null;
    }

    @Nullable
    @RequiresApi(HttpEngine.MAX_FOLLOW_UPS)
    public final WindowInsets A02() {
        AnonymousClass0B6 r1 = this.A00;
        if (r1 instanceof C03720d8) {
            return ((C03720d8) r1).A01;
        }
        return null;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AnonymousClass0B7)) {
            return false;
        }
        return Objects.equals(this.A00, ((AnonymousClass0B7) obj).A00);
    }

    public final int hashCode() {
        AnonymousClass0B6 r0 = this.A00;
        if (r0 == null) {
            return 0;
        }
        return r0.hashCode();
    }

    @RequiresApi(HttpEngine.MAX_FOLLOW_UPS)
    public AnonymousClass0B7(@NonNull WindowInsets windowInsets) {
        AnonymousClass0B6 r0;
        int i = Build.VERSION.SDK_INT;
        if (i >= 29) {
            r0 = new AnonymousClass08u(this, windowInsets);
        } else if (i >= 28) {
            r0 = new C01430Gx(this, windowInsets);
        } else {
            r0 = new AnonymousClass0MQ(this, windowInsets);
        }
        this.A00 = r0;
    }

    public AnonymousClass0B7() {
        this.A00 = new AnonymousClass0B6(this);
    }
}
