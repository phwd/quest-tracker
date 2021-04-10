package X;

import com.facebook.common.time.RealtimeSinceBootClock;
import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@Nullsafe(Nullsafe.Mode.LOCAL)
@Immutable
/* renamed from: X.1pC  reason: invalid class name */
public final class AnonymousClass1pC implements AnonymousClass1kC {
    public final int A00;
    public final AnonymousClass1r8 A01;
    public final AnonymousClass1pN A02;
    public final Object A03;
    public final String A04;

    @Override // X.AnonymousClass1kC
    public final boolean A55() {
        return false;
    }

    public final String toString() {
        return String.format(null, "%s_%s_%s_%s_%s_%s_%d", this.A04, null, this.A02, this.A01, null, null, Integer.valueOf(this.A00));
    }

    public final boolean equals(@Nullable Object obj) {
        if (!(obj instanceof AnonymousClass1pC)) {
            return false;
        }
        AnonymousClass1pC r4 = (AnonymousClass1pC) obj;
        if (this.A00 != r4.A00 || !this.A04.equals(r4.A04) || !AnonymousClass0KT.A01(this.A02, r4.A02) || !AnonymousClass0KT.A01(this.A01, r4.A01) || !AnonymousClass0KT.A01(null, null) || !AnonymousClass0KT.A01(null, null)) {
            return false;
        }
        return true;
    }

    public AnonymousClass1pC(String str, @Nullable AnonymousClass1pN r7, AnonymousClass1r8 r8, Object obj) {
        int hashCode;
        int hashCode2;
        int hashCode3;
        if (str != null) {
            this.A04 = str;
            this.A02 = r7;
            this.A01 = r8;
            Integer valueOf = Integer.valueOf(str.hashCode());
            Integer num = 0;
            Integer valueOf2 = Integer.valueOf(r7.hashCode());
            AnonymousClass1r8 r2 = this.A01;
            if (valueOf == null) {
                hashCode = 0;
            } else {
                hashCode = valueOf.hashCode();
            }
            int hashCode4 = num.hashCode();
            if (valueOf2 == null) {
                hashCode2 = 0;
            } else {
                hashCode2 = valueOf2.hashCode();
            }
            if (r2 == null) {
                hashCode3 = 0;
            } else {
                hashCode3 = r2.hashCode();
            }
            this.A00 = ((((((((((hashCode + 31) * 31) + hashCode4) * 31) + hashCode2) * 31) + hashCode3) * 31) + 0) * 31) + 0;
            this.A03 = obj;
            RealtimeSinceBootClock.A00.now();
            return;
        }
        throw null;
    }

    @Override // X.AnonymousClass1kC
    public final String A4c() {
        return this.A04;
    }

    public final int hashCode() {
        return this.A00;
    }
}
