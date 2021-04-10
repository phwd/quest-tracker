package X;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* renamed from: X.1cP  reason: invalid class name and case insensitive filesystem */
public final class C07491cP<T> {
    public static final AnonymousClass1cQ<Object> A04 = new AnonymousClass1cR();
    public final AnonymousClass1cQ<T> A00;
    public final T A01;
    public final String A02;
    public volatile byte[] A03;

    @NonNull
    public static <T> C07491cP<T> A00(@NonNull String str, @NonNull T t) {
        return new C07491cP<>(str, t, A04);
    }

    public final boolean equals(Object obj) {
        if (obj instanceof C07491cP) {
            return this.A02.equals(((C07491cP) obj).A02);
        }
        return false;
    }

    public final int hashCode() {
        return this.A02.hashCode();
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Option{key='");
        sb.append(this.A02);
        sb.append('\'');
        sb.append('}');
        return sb.toString();
    }

    public C07491cP(@NonNull String str, @Nullable T t, @NonNull AnonymousClass1cQ<T> r5) {
        if (!TextUtils.isEmpty(str)) {
            this.A02 = str;
            this.A01 = t;
            AnonymousClass1S2.A00(r5);
            this.A00 = r5;
            return;
        }
        throw new IllegalArgumentException("Must not be null or empty");
    }
}
