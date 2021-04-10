package X;

import android.text.TextUtils;
import java.util.Locale;

/* renamed from: X.Tu  reason: case insensitive filesystem */
public abstract class AbstractC0148Tu implements AnonymousClass7C {
    public final AnonymousClass7D A00;

    @Override // X.AnonymousClass7C
    public final boolean A2D(CharSequence charSequence, int i, int i2) {
        if (charSequence == null || i < 0 || i2 < 0 || charSequence.length() - i2 < i) {
            throw new IllegalArgumentException();
        }
        AnonymousClass7D r0 = this.A00;
        if (r0 != null) {
            int A1A = r0.A1A(charSequence, i, i2);
            if (A1A == 0) {
                return true;
            }
            if (A1A == 1) {
                return false;
            }
        }
        if (!(this instanceof Ce)) {
            return ((Cf) this).A00;
        }
        if (TextUtils.getLayoutDirectionFromLocale(Locale.getDefault()) == 1) {
            return true;
        }
        return false;
    }

    public AbstractC0148Tu(AnonymousClass7D r1) {
        this.A00 = r1;
    }
}
