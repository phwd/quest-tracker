package X;

import android.view.Surface;
import com.facebook.annotations.OkToExtend;
import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;

@OkToExtend
@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.1xM  reason: invalid class name */
public class AnonymousClass1xM extends AnonymousClass1xK implements AbstractC11051xT {
    public AnonymousClass1xM(@Nullable Surface surface) {
        if (surface != null) {
            this.A00 = surface;
            return;
        }
        throw new IllegalArgumentException("surface cannot be null");
    }

    @Override // X.AnonymousClass1xK, X.AbstractC11051xT
    public final boolean A1e() {
        Surface surface;
        if (!super.A1e() || (surface = this.A00) == null || !surface.isValid()) {
            return false;
        }
        return true;
    }
}
