package X;

import android.text.TextUtils;
import java.util.Locale;

/* renamed from: X.0WC  reason: invalid class name */
public class AnonymousClass0WC extends AnonymousClass0vv {
    public static final AnonymousClass0WC A00 = new AnonymousClass0WC();

    public AnonymousClass0WC() {
        super(null);
    }

    @Override // X.AnonymousClass0vv
    public final boolean A00() {
        if (TextUtils.getLayoutDirectionFromLocale(Locale.getDefault()) != 1) {
            return false;
        }
        return true;
    }
}
