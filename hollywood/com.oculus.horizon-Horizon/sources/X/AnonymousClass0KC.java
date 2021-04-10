package X;

import android.text.TextUtils;
import java.util.Locale;

/* renamed from: X.0KC  reason: invalid class name */
public class AnonymousClass0KC extends AnonymousClass0sV {
    public static final AnonymousClass0KC A00 = new AnonymousClass0KC();

    public AnonymousClass0KC() {
        super(null);
    }

    @Override // X.AnonymousClass0sV
    public final boolean A00() {
        if (TextUtils.getLayoutDirectionFromLocale(Locale.getDefault()) != 1) {
            return false;
        }
        return true;
    }
}
