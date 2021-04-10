package X;

import android.os.LocaleList;
import java.util.Locale;

/* renamed from: X.dh  reason: case insensitive filesystem */
public final class C0641dh implements AnonymousClass1W {
    public final LocaleList A00;

    @Override // X.AnonymousClass1W
    public final Locale A2F(int i) {
        return this.A00.get(i);
    }

    public final boolean equals(Object obj) {
        return this.A00.equals(((AnonymousClass1W) obj).A2b());
    }

    public final int hashCode() {
        return this.A00.hashCode();
    }

    public final String toString() {
        return this.A00.toString();
    }

    public C0641dh(LocaleList localeList) {
        this.A00 = localeList;
    }

    @Override // X.AnonymousClass1W
    public final Object A2b() {
        return this.A00;
    }
}
