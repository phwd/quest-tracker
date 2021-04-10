package X;

import androidx.annotation.VisibleForTesting;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.quicklog.AnnotationType;
import java.util.Arrays;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0T7  reason: invalid class name */
public final class AnonymousClass0T7 {
    public int A00 = 0;
    public int[] A01 = new int[5];
    public String[] A02 = new String[10];
    public volatile boolean A03;

    public final void A00(AnonymousClass0T6 r6) {
        int i = 0;
        int i2 = 0;
        while (i < this.A00) {
            String[] strArr = this.A02;
            r6.A9w(strArr[i2], strArr[i2 + 1], this.A01[i]);
            i++;
            i2 += 2;
        }
    }

    @VisibleForTesting
    public final void A01(String str, @Nullable String str2, @AnnotationType int i) {
        if (!this.A03) {
            int i2 = this.A00;
            int[] iArr = this.A01;
            int length = iArr.length;
            if (i2 >= length) {
                int i3 = length + (length >> 1);
                this.A01 = Arrays.copyOf(iArr, i3);
                this.A02 = (String[]) Arrays.copyOf(this.A02, i3 << 1);
            }
            int i4 = this.A00;
            int i5 = i4 << 1;
            String[] strArr = this.A02;
            strArr[i5] = str;
            strArr[i5 + 1] = str2;
            this.A01[i4] = i;
            this.A00 = i4 + 1;
            return;
        }
        throw new IllegalStateException("Attempted to modify locked instance of PointData");
    }

    public final boolean equals(@Nullable Object obj) {
        if (obj instanceof AnonymousClass0T7) {
            AnonymousClass0T7 r7 = (AnonymousClass0T7) obj;
            int i = this.A00;
            if (i == r7.A00) {
                int i2 = i << 1;
                int i3 = 0;
                while (true) {
                    if (i3 < i2) {
                        String str = this.A02[i3];
                        String str2 = r7.A02[i3];
                        if (str == null) {
                            if (str2 != null) {
                                break;
                            }
                        } else if (!str.equals(str2)) {
                            return false;
                        }
                        i3++;
                    } else {
                        for (int i4 = 0; i4 < i; i4++) {
                            if (this.A01[i4] == r7.A01[i4]) {
                            }
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        int i = this.A00;
        int i2 = i << 1;
        int i3 = 31;
        for (int i4 = 0; i4 < i2; i4++) {
            String[] strArr = this.A02;
            if (strArr[i4] != null) {
                i3 = (i3 * 31) + strArr[i4].hashCode();
            }
        }
        for (int i5 = 0; i5 < i; i5++) {
            i3 = (i3 * 31) + this.A01[i5];
        }
        return i3;
    }

    public final String toString() {
        int i = this.A00;
        if (i == 1 && this.A01[0] == 1) {
            String[] strArr = this.A02;
            if ("__key".equals(strArr[0])) {
                return strArr[1];
            }
        }
        StringBuilder sb = new StringBuilder("{");
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            sb.append('\"');
            String[] strArr2 = this.A02;
            sb.append(strArr2[i2]);
            sb.append("\":\"");
            sb.append(strArr2[i2 + 1]);
            sb.append("\",");
            i2 += 2;
        }
        sb.replace(sb.length() - 1, sb.length(), "}");
        return sb.toString();
    }
}
