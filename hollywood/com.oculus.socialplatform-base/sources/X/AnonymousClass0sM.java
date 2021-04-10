package X;

import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.0sM  reason: invalid class name */
public final class AnonymousClass0sM implements AnonymousClass0H3 {
    public final String A00;

    public final boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof AnonymousClass0sM) {
            return this.A00.equals(((AnonymousClass0sM) obj).A00);
        }
        return false;
    }

    public final int hashCode() {
        return this.A00.hashCode();
    }

    public AnonymousClass0sM(String str) {
        if (str != null) {
            this.A00 = str;
            return;
        }
        throw null;
    }

    @Override // X.AnonymousClass0H3
    public final String A5D() {
        return this.A00;
    }

    @Override // X.AnonymousClass0H3
    public final boolean A68() {
        return false;
    }

    public final String toString() {
        return this.A00;
    }
}
