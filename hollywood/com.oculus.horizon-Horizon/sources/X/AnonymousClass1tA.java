package X;

import com.facebook.infer.annotation.Nullsafe;
import com.oculus.appmanager.info.ApkUpdateInfoContract;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.1tA  reason: invalid class name */
public final class AnonymousClass1tA {
    public static final AnonymousClass1tA A03 = new AnonymousClass1tA(Integer.MAX_VALUE, true, true);
    public int A00;
    public boolean A01;
    public boolean A02;

    public final boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AnonymousClass1tA)) {
            return false;
        }
        AnonymousClass1tA r4 = (AnonymousClass1tA) obj;
        return this.A00 == r4.A00 && this.A02 == r4.A02 && this.A01 == r4.A01;
    }

    public final int hashCode() {
        int i = this.A00;
        int i2 = 0;
        int i3 = 0;
        if (this.A02) {
            i3 = 4194304;
        }
        int i4 = i ^ i3;
        if (this.A01) {
            i2 = ApkUpdateInfoContract.UPDATE_TYPE_STORE;
        }
        return i4 ^ i2;
    }

    public AnonymousClass1tA(int i, boolean z, boolean z2) {
        this.A00 = i;
        this.A02 = z;
        this.A01 = z2;
    }
}
