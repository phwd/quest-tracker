package com.facebook.quicklog;

import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class PointData {
    private int mIndex = 0;
    private String[] mKeyVals = new String[10];
    private int[] mTypes = new int[5];

    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof PointData)) {
            return false;
        }
        PointData pointData = (PointData) obj;
        int i = this.mIndex;
        if (i != pointData.mIndex) {
            return false;
        }
        int i2 = i * 2;
        for (int i3 = 0; i3 < i2; i3++) {
            String str = this.mKeyVals[i3];
            String str2 = pointData.mKeyVals[i3];
            if (str == null) {
                if (str2 != null) {
                    return false;
                }
            } else if (!str.equals(str2)) {
                return false;
            }
        }
        for (int i4 = 0; i4 < i; i4++) {
            if (this.mTypes[i4] != pointData.mTypes[i4]) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        int i = this.mIndex;
        int i2 = i * 2;
        int i3 = 31;
        for (int i4 = 0; i4 < i2; i4++) {
            String[] strArr = this.mKeyVals;
            if (strArr[i4] != null) {
                i3 = (i3 * 31) + strArr[i4].hashCode();
            }
        }
        for (int i5 = 0; i5 < i; i5++) {
            i3 = (i3 * 31) + this.mTypes[i5];
        }
        return i3;
    }

    public String toString() {
        if (this.mIndex == 1 && this.mTypes[0] == 1 && "__key".equals(this.mKeyVals[0])) {
            return this.mKeyVals[1];
        }
        StringBuilder sb = new StringBuilder();
        sb.append('{');
        int i = 0;
        for (int i2 = 0; i2 < this.mIndex; i2++) {
            sb.append('\"');
            sb.append(this.mKeyVals[i]);
            sb.append("\":\"");
            sb.append(this.mKeyVals[i + 1]);
            sb.append("\",");
            i += 2;
        }
        sb.replace(sb.length() - 1, sb.length(), "}");
        return sb.toString();
    }
}
