package X;

import android.view.DisplayCutout;
import android.view.WindowInsets;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import java.util.Objects;

@RequiresApi(28)
/* renamed from: X.0An  reason: invalid class name and case insensitive filesystem */
public class C00550An extends AnonymousClass0KA {
    @Override // X.C001807q
    @Nullable
    public final C001406t A02() {
        DisplayCutout displayCutout = this.A01.getDisplayCutout();
        if (displayCutout == null) {
            return null;
        }
        return new C001406t(displayCutout);
    }

    @Override // X.C001807q
    @NonNull
    public final AnonymousClass07r A03() {
        WindowInsets consumeDisplayCutout = this.A01.consumeDisplayCutout();
        if (consumeDisplayCutout != null) {
            return new AnonymousClass07r(consumeDisplayCutout);
        }
        throw null;
    }

    @Override // X.C001807q
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C00550An)) {
            return false;
        }
        return Objects.equals(this.A01, ((AnonymousClass0sO) obj).A01);
    }

    @Override // X.C001807q
    public final int hashCode() {
        return this.A01.hashCode();
    }

    public C00550An(@NonNull AnonymousClass07r r1, @NonNull WindowInsets windowInsets) {
        super(r1, windowInsets);
    }
}
