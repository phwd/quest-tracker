package X;

import androidx.annotation.NonNull;

/* renamed from: X.1Jc  reason: invalid class name */
public final class AnonymousClass1Jc {
    @NonNull
    public final String A00;

    public final boolean equals(Object obj) {
        if (obj instanceof AnonymousClass1Jc) {
            return this.A00.equals(((AnonymousClass1Jc) obj).A00);
        }
        return false;
    }

    public final int hashCode() {
        return this.A00.hashCode();
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("StringHeaderFactory{value='");
        sb.append(this.A00);
        sb.append('\'');
        sb.append('}');
        return sb.toString();
    }

    public AnonymousClass1Jc(@NonNull String str) {
        this.A00 = str;
    }
}
