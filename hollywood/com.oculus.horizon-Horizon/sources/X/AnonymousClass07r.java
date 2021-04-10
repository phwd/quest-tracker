package X;

import android.os.Build;
import android.view.WindowInsets;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import java.util.Objects;

/* renamed from: X.07r  reason: invalid class name */
public final class AnonymousClass07r {
    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public static final AnonymousClass07r A01 = new C001607o().A00.A00().A00.A03().A00.A04().A00.A05();
    public final C001807q A00;

    @Nullable
    @RequiresApi(20)
    public final WindowInsets A00() {
        C001807q r1 = this.A00;
        if (r1 instanceof AnonymousClass0sO) {
            return ((AnonymousClass0sO) r1).A01;
        }
        return null;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AnonymousClass07r)) {
            return false;
        }
        return Objects.equals(this.A00, ((AnonymousClass07r) obj).A00);
    }

    public final int hashCode() {
        C001807q r0 = this.A00;
        if (r0 == null) {
            return 0;
        }
        return r0.hashCode();
    }

    @RequiresApi(20)
    public AnonymousClass07r(@NonNull WindowInsets windowInsets) {
        C001807q r0;
        int i = Build.VERSION.SDK_INT;
        if (i >= 29) {
            r0 = new AnonymousClass04U(this, windowInsets);
        } else if (i >= 28) {
            r0 = new C00550An(this, windowInsets);
        } else {
            r0 = new AnonymousClass0KA(this, windowInsets);
        }
        this.A00 = r0;
    }

    public AnonymousClass07r() {
        this.A00 = new C001807q(this);
    }
}
