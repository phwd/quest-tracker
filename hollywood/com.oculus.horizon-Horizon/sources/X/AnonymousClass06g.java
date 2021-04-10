package X;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.Objects;

/* renamed from: X.06g  reason: invalid class name */
public final class AnonymousClass06g<F, S> {
    @Nullable
    public final F A00;
    @Nullable
    public final S A01;

    public final boolean equals(Object obj) {
        if (!(obj instanceof AnonymousClass06g)) {
            return false;
        }
        AnonymousClass06g r4 = (AnonymousClass06g) obj;
        if (!Objects.equals(r4.A00, this.A00) || !Objects.equals(r4.A01, this.A01)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        int hashCode;
        F f = this.A00;
        int i = 0;
        if (f == null) {
            hashCode = 0;
        } else {
            hashCode = f.hashCode();
        }
        S s = this.A01;
        if (s != null) {
            i = s.hashCode();
        }
        return hashCode ^ i;
    }

    @NonNull
    public final String toString() {
        return AnonymousClass006.A09("Pair{", String.valueOf(this.A00), " ", String.valueOf(this.A01), "}");
    }

    public AnonymousClass06g(@Nullable F f, @Nullable S s) {
        this.A00 = f;
        this.A01 = s;
    }
}
