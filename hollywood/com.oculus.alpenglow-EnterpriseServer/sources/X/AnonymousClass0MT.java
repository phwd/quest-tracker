package X;

import android.text.TextUtils;
import java.util.Locale;

/* renamed from: X.0MT  reason: invalid class name */
public class AnonymousClass0MT extends AbstractC03800dH {
    public static final AnonymousClass0MT A00 = new AnonymousClass0MT();

    public AnonymousClass0MT() {
        super(null);
    }

    @Override // X.AbstractC03800dH
    public final boolean A00() {
        if (TextUtils.getLayoutDirectionFromLocale(Locale.getDefault()) != 1) {
            return false;
        }
        return true;
    }
}
