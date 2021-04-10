package com.facebook.quicklog;

import androidx.annotation.VisibleForTesting;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.infer.annotation.PropagatesNullable;
import java.util.Arrays;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class PointData {
    private static final int INITIAL_SIZE = 5;
    static final int STRING_TYPE = 1;
    private static final String sLegacyKey = "__key";
    private int mIndex = 0;
    private String[] mKeyVals = new String[10];
    private volatile boolean mLocked;
    private int[] mTypes = new int[5];

    public interface Visitor {
        void visitPointData(String str, @Nullable String str2, @AnnotationType int i);
    }

    public static PointData singleStringOrNull(@PropagatesNullable String str) {
        if (str == null) {
            return null;
        }
        return new PointData().addData(sLegacyKey, str).lock();
    }

    public PointData addNullableData(String str, @Nullable String str2, @Nullable String str3) {
        if (str2 == null) {
            return str3 == null ? this : addData(str, str3);
        }
        return addData(str, str2);
    }

    public PointData addData(String str, @Nullable String str2) {
        addData(str, str2, 1);
        return this;
    }

    public PointData lock() {
        this.mLocked = true;
        return this;
    }

    @VisibleForTesting
    public void addData(String str, @Nullable String str2, @AnnotationType int i) {
        if (!this.mLocked) {
            ensureSize();
            int i2 = this.mIndex;
            int i3 = i2 * 2;
            String[] strArr = this.mKeyVals;
            strArr[i3] = str;
            strArr[i3 + 1] = str2;
            this.mTypes[i2] = i;
            this.mIndex = i2 + 1;
            return;
        }
        throw new IllegalStateException("Attempted to modify locked instance of PointData");
    }

    @Nullable
    public String getDataValue(String str) {
        int i = 0;
        while (true) {
            String[] strArr = this.mKeyVals;
            if (i >= strArr.length) {
                return null;
            }
            if (str.equals(strArr[i])) {
                return this.mKeyVals[i + 1];
            }
            i += 2;
        }
    }

    private void ensureSize() {
        int i = this.mIndex;
        int[] iArr = this.mTypes;
        if (i >= iArr.length) {
            int length = iArr.length + (iArr.length >> 1);
            this.mTypes = Arrays.copyOf(iArr, length);
            this.mKeyVals = (String[]) Arrays.copyOf(this.mKeyVals, length * 2);
        }
    }

    public void ensureLocked() {
        if (!this.mLocked) {
            throw new IllegalStateException("PointData should be locked before passing to the storage");
        }
    }

    public void acceptForAll(Visitor visitor) {
        int i = 0;
        int i2 = 0;
        while (i < this.mIndex) {
            String[] strArr = this.mKeyVals;
            visitor.visitPointData(strArr[i2], strArr[i2 + 1], this.mTypes[i]);
            i++;
            i2 += 2;
        }
    }

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
        if (this.mIndex == 1 && this.mTypes[0] == 1 && sLegacyKey.equals(this.mKeyVals[0])) {
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
