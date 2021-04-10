package X;

import android.text.TextUtils;
import java.util.Locale;

/* renamed from: X.Iw  reason: case insensitive filesystem */
public class C0105Iw extends AbstractC0291aF {
    public static final C0105Iw A00 = new C0105Iw();

    public C0105Iw() {
        super(null);
    }

    @Override // X.AbstractC0291aF
    public final boolean A00() {
        if (TextUtils.getLayoutDirectionFromLocale(Locale.getDefault()) != 1) {
            return false;
        }
        return true;
    }
}
