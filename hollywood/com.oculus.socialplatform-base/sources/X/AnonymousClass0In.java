package X;

import android.view.DisplayCutout;
import android.view.WindowInsets;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import java.util.Objects;

@RequiresApi(28)
/* renamed from: X.0In  reason: invalid class name */
public class AnonymousClass0In extends AnonymousClass0W9 {
    @Override // X.AnonymousClass07p
    @Nullable
    public final AnonymousClass071 A02() {
        DisplayCutout displayCutout = this.A01.getDisplayCutout();
        if (displayCutout == null) {
            return null;
        }
        return new AnonymousClass071(displayCutout);
    }

    @Override // X.AnonymousClass07p
    @NonNull
    public final C003307q A03() {
        return C003307q.A01(this.A01.consumeDisplayCutout());
    }

    @Override // X.AnonymousClass07p
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AnonymousClass0In)) {
            return false;
        }
        return Objects.equals(this.A01, ((C05400ve) obj).A01);
    }

    @Override // X.AnonymousClass07p
    public final int hashCode() {
        return this.A01.hashCode();
    }

    public AnonymousClass0In(@NonNull C003307q r1, @NonNull WindowInsets windowInsets) {
        super(r1, windowInsets);
    }
}
