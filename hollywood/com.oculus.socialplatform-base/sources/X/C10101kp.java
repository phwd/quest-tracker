package X;

import com.facebook.common.time.RealtimeSinceBootClock;
import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@Nullsafe(Nullsafe.Mode.LOCAL)
@Immutable
/* renamed from: X.1kp  reason: invalid class name and case insensitive filesystem */
public final class C10101kp implements AnonymousClass0H3 {
    public final int A00;
    public final AnonymousClass0PI A01;
    public final AnonymousClass0PO A02;
    public final Object A03;
    public final String A04;

    @Override // X.AnonymousClass0H3
    public final boolean A68() {
        return false;
    }

    public final String toString() {
        return String.format(null, "%s_%s_%s_%s_%s_%s_%d", this.A04, null, this.A02, this.A01, null, null, Integer.valueOf(this.A00));
    }

    @Override // X.AnonymousClass0H3
    public final String A5D() {
        return this.A04;
    }

    public final boolean equals(@Nullable Object obj) {
        if (!(obj instanceof C10101kp)) {
            return false;
        }
        C10101kp r4 = (C10101kp) obj;
        if (this.A00 != r4.A00 || !this.A04.equals(r4.A04) || !C00730Ih.A01(this.A02, r4.A02) || !C00730Ih.A01(this.A01, r4.A01) || !C00730Ih.A01(null, null) || !C00730Ih.A01(null, null)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return this.A00;
    }

    public C10101kp(String str, @Nullable AnonymousClass0PO r7, AnonymousClass0PI r8, Object obj) {
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
            AnonymousClass0PI r2 = this.A01;
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
}
