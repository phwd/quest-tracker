package X;

import android.net.Uri;
import android.text.TextUtils;
import java.util.Arrays;

public final class RR {
    public static final Uri A04 = new Uri.Builder().scheme("content").authority("com.google.android.gms.chimera").build();
    public final int A00;
    public final String A01;
    public final String A02;
    public final boolean A03;

    public final boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof RR) {
                RR rr = (RR) obj;
                if (!RY.A00(this.A01, rr.A01) || !RY.A00(this.A02, rr.A02) || this.A00 != rr.A00 || this.A03 != rr.A03) {
                    return false;
                }
            }
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.A01, this.A02, null, Integer.valueOf(this.A00), Boolean.valueOf(this.A03)});
    }

    public final String toString() {
        String str = this.A01;
        if (str != null) {
            return str;
        }
        RZ.A01(null);
        throw new NullPointerException("flattenToString");
    }

    public RR(String str, String str2, int i, boolean z) {
        if (!TextUtils.isEmpty(str)) {
            this.A01 = str;
            if (!TextUtils.isEmpty(str2)) {
                this.A02 = str2;
                this.A00 = i;
                this.A03 = z;
                return;
            }
            throw new IllegalArgumentException("Given String is empty or null");
        }
        throw new IllegalArgumentException("Given String is empty or null");
    }
}
