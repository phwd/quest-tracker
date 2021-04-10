package X;

import com.facebook.infer.annotation.Nullsafe;
import com.oculus.appmanager.info.ApkUpdateInfoContract;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0mW  reason: invalid class name and case insensitive filesystem */
public final class C03410mW {
    public static final C03410mW A03 = new C03410mW(Integer.MAX_VALUE, true, true);
    public int A00;
    public boolean A01;
    public boolean A02;

    public final boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof C03410mW)) {
            return false;
        }
        C03410mW r4 = (C03410mW) obj;
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

    public C03410mW(int i, boolean z, boolean z2) {
        this.A00 = i;
        this.A02 = z;
        this.A01 = z2;
    }
}
