package X;

import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.1tG  reason: invalid class name */
public final class AnonymousClass1tG implements AnonymousClass1kC {
    public final String A00;

    public final boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof AnonymousClass1tG) {
            return this.A00.equals(((AnonymousClass1tG) obj).A00);
        }
        return false;
    }

    public final int hashCode() {
        return this.A00.hashCode();
    }

    public AnonymousClass1tG(String str) {
        if (str != null) {
            this.A00 = str;
            return;
        }
        throw null;
    }

    @Override // X.AnonymousClass1kC
    public final String A4c() {
        return this.A00;
    }

    @Override // X.AnonymousClass1kC
    public final boolean A55() {
        return false;
    }

    public final String toString() {
        return this.A00;
    }
}
