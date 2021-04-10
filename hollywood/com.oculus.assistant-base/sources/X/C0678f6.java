package X;

import android.content.SharedPreferences;

/* renamed from: X.f6  reason: case insensitive filesystem */
public final class C0678f6 implements AnonymousClass7h {
    public final SharedPreferences A00;

    @Override // X.AnonymousClass7h
    public final int A2U(String str, int i) {
        return this.A00.getInt(str, i);
    }

    @Override // X.AnonymousClass7h
    public final String A2x(String str, String str2) {
        return this.A00.getString(str, str2);
    }

    public C0678f6(SharedPreferences sharedPreferences) {
        this.A00 = sharedPreferences;
    }
}
