package X;

import android.os.Build;
import android.os.LocaleList;
import java.util.Locale;

/* renamed from: X.1V  reason: invalid class name */
public final class AnonymousClass1V {
    public static final AnonymousClass1V A01 = A01(new Locale[0]);
    public AnonymousClass1W A00;

    public static AnonymousClass1V A00(LocaleList localeList) {
        return new AnonymousClass1V(new C0641dh(localeList));
    }

    public static AnonymousClass1V A01(Locale... localeArr) {
        if (Build.VERSION.SDK_INT >= 24) {
            return A00(new LocaleList(localeArr));
        }
        return new AnonymousClass1V(new C0640dg(localeArr));
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof AnonymousClass1V) || !this.A00.equals(((AnonymousClass1V) obj).A00)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return this.A00.hashCode();
    }

    public final String toString() {
        return this.A00.toString();
    }

    public AnonymousClass1V(AnonymousClass1W r1) {
        this.A00 = r1;
    }
}
