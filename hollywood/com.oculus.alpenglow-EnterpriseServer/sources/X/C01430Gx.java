package X;

import android.view.DisplayCutout;
import android.view.WindowInsets;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import java.util.Objects;

@RequiresApi(28)
/* renamed from: X.0Gx  reason: invalid class name and case insensitive filesystem */
public class C01430Gx extends AnonymousClass0MQ {
    @Override // X.AnonymousClass0B6
    @Nullable
    public final AnonymousClass0AH A02() {
        DisplayCutout displayCutout = this.A01.getDisplayCutout();
        if (displayCutout == null) {
            return null;
        }
        return new AnonymousClass0AH(displayCutout);
    }

    @Override // X.AnonymousClass0B6
    @NonNull
    public final AnonymousClass0B7 A03() {
        return AnonymousClass0B7.A01(this.A01.consumeDisplayCutout());
    }

    @Override // X.AnonymousClass0B6
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C01430Gx)) {
            return false;
        }
        return Objects.equals(this.A01, ((C03720d8) obj).A01);
    }

    @Override // X.AnonymousClass0B6
    public final int hashCode() {
        return this.A01.hashCode();
    }

    public C01430Gx(@NonNull AnonymousClass0B7 r1, @NonNull WindowInsets windowInsets) {
        super(r1, windowInsets);
    }
}
