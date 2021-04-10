package libcore.util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.TimeZone;
import libcore.io.BufferIterator;

public final class ZoneInfo extends TimeZone {
    private static final int[] LEAP = {0, 31, 60, 91, 121, 152, 182, 213, 244, 274, 305, 335};
    private static final int[] NORMAL = {0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334};
    static final long serialVersionUID = -4598738130123921552L;
    private int mDstSavings;
    private final int mEarliestRawOffset;
    private final byte[] mIsDsts;
    private final int[] mOffsets;
    private int mRawOffset;
    private final long[] mTransitions;
    private final byte[] mTypes;
    private final boolean mUseDst;

    public static class WallTime {
    }

    public static ZoneInfo readTimeZone(String str, BufferIterator bufferIterator, long j) {
        int readInt = bufferIterator.readInt();
        if (readInt == 1415211366) {
            bufferIterator.skip(28);
            int readInt2 = bufferIterator.readInt();
            if (readInt2 < 0 || readInt2 > 2000) {
                throw new IOException("Timezone id=" + str + " has an invalid number of transitions=" + readInt2);
            }
            int readInt3 = bufferIterator.readInt();
            if (readInt3 < 1) {
                throw new IOException("ZoneInfo requires at least one type to be provided for each timezone but could not find one for '" + str + "'");
            } else if (readInt3 <= 256) {
                bufferIterator.skip(4);
                int[] iArr = new int[readInt2];
                bufferIterator.readIntArray(iArr, 0, iArr.length);
                long[] jArr = new long[readInt2];
                for (int i = 0; i < readInt2; i++) {
                    jArr[i] = (long) iArr[i];
                    if (i > 0) {
                        int i2 = i - 1;
                        if (jArr[i] <= jArr[i2]) {
                            throw new IOException(str + " transition at " + i + " is not sorted correctly, is " + jArr[i] + ", previous is " + jArr[i2]);
                        }
                    }
                }
                byte[] bArr = new byte[readInt2];
                bufferIterator.readByteArray(bArr, 0, bArr.length);
                for (int i3 = 0; i3 < bArr.length; i3++) {
                    int i4 = bArr[i3] & 255;
                    if (i4 >= readInt3) {
                        throw new IOException(str + " type at " + i3 + " is not < " + readInt3 + ", is " + i4);
                    }
                }
                int[] iArr2 = new int[readInt3];
                byte[] bArr2 = new byte[readInt3];
                for (int i5 = 0; i5 < readInt3; i5++) {
                    iArr2[i5] = bufferIterator.readInt();
                    byte readByte = bufferIterator.readByte();
                    if (readByte == 0 || readByte == 1) {
                        bArr2[i5] = readByte;
                        bufferIterator.skip(1);
                    } else {
                        throw new IOException(str + " dst at " + i5 + " is not 0 or 1, is " + ((int) readByte));
                    }
                }
                return new ZoneInfo(str, jArr, bArr, iArr2, bArr2, j);
            } else {
                throw new IOException("Timezone with id " + str + " has too many types=" + readInt3);
            }
        } else {
            throw new IOException("Timezone id=" + str + " has an invalid header=" + readInt);
        }
    }

    private ZoneInfo(String str, long[] jArr, byte[] bArr, int[] iArr, byte[] bArr2, long j) {
        if (iArr.length != 0) {
            this.mTransitions = jArr;
            this.mTypes = bArr;
            this.mIsDsts = bArr2;
            setID(str);
            int length = this.mTransitions.length - 1;
            int i = -1;
            int i2 = -1;
            while (true) {
                if ((i == -1 || i2 == -1) && length >= 0) {
                    int i3 = this.mTypes[length] & 255;
                    if (i == -1 && this.mIsDsts[i3] == 0) {
                        i = length;
                    }
                    if (i2 == -1 && this.mIsDsts[i3] != 0) {
                        i2 = length;
                    }
                    length--;
                }
            }
            int i4 = 0;
            if (this.mTransitions.length == 0) {
                this.mRawOffset = iArr[0];
            } else if (i != -1) {
                this.mRawOffset = iArr[this.mTypes[i] & 255];
            } else {
                throw new IllegalStateException("ZoneInfo requires at least one non-DST transition to be provided for each timezone that has at least one transition but could not find one for '" + str + "'");
            }
            if (i2 != -1 && this.mTransitions[i2] < roundUpMillisToSeconds(j)) {
                i2 = -1;
            }
            if (i2 == -1) {
                this.mDstSavings = 0;
                this.mUseDst = false;
            } else {
                byte[] bArr3 = this.mTypes;
                this.mDstSavings = (iArr[bArr3[i2] & 255] - iArr[bArr3[i] & 255]) * 1000;
                this.mUseDst = true;
            }
            int i5 = 0;
            while (true) {
                byte[] bArr4 = this.mIsDsts;
                if (i5 >= bArr4.length) {
                    i5 = -1;
                    break;
                } else if (bArr4[i5] == 0) {
                    break;
                } else {
                    i5++;
                }
            }
            int i6 = i5 != -1 ? iArr[i5] : this.mRawOffset;
            this.mOffsets = iArr;
            while (true) {
                int[] iArr2 = this.mOffsets;
                if (i4 < iArr2.length) {
                    iArr2[i4] = iArr2[i4] - this.mRawOffset;
                    i4++;
                } else {
                    this.mRawOffset *= 1000;
                    this.mEarliestRawOffset = i6 * 1000;
                    return;
                }
            }
        } else {
            throw new IllegalArgumentException("ZoneInfo requires at least one offset to be provided for each timezone but could not find one for '" + str + "'");
        }
    }

    private void readObject(ObjectInputStream objectInputStream) {
        objectInputStream.defaultReadObject();
        throw null;
    }

    @Override // java.util.TimeZone
    public int getOffset(int i, int i2, int i3, int i4, int i5, int i6) {
        int i7 = i2 % 400;
        long j = (((long) (i2 / 400)) * 12622780800000L) + (((long) i7) * 31536000000L) + (((long) ((i7 + 3) / 4)) * 86400000);
        if (i7 > 0) {
            j -= ((long) ((i7 - 1) / 100)) * 86400000;
        }
        return getOffset(((((j + (((long) (i7 == 0 || (i7 % 4 == 0 && i7 % 100 != 0) ? LEAP : NORMAL)[i3]) * 86400000)) + (((long) (i4 - 1)) * 86400000)) + ((long) i6)) - ((long) this.mRawOffset)) - 62167219200000L);
    }

    public int findTransitionIndex(long j) {
        int binarySearch = Arrays.binarySearch(this.mTransitions, j);
        if (binarySearch >= 0) {
            return binarySearch;
        }
        int i = (~binarySearch) - 1;
        if (i < 0) {
            return -1;
        }
        return i;
    }

    /* access modifiers changed from: package-private */
    public int findOffsetIndexForTimeInSeconds(long j) {
        int findTransitionIndex = findTransitionIndex(j);
        if (findTransitionIndex < 0) {
            return -1;
        }
        return this.mTypes[findTransitionIndex] & 255;
    }

    /* access modifiers changed from: package-private */
    public int findOffsetIndexForTimeInMilliseconds(long j) {
        return findOffsetIndexForTimeInSeconds(roundDownMillisToSeconds(j));
    }

    static long roundDownMillisToSeconds(long j) {
        if (j < 0) {
            return (j - 999) / 1000;
        }
        return j / 1000;
    }

    static long roundUpMillisToSeconds(long j) {
        if (j > 0) {
            return (j + 999) / 1000;
        }
        return j / 1000;
    }

    public int getOffsetsByUtcTime(long j, int[] iArr) {
        int i;
        int i2;
        int i3;
        int i4;
        int findTransitionIndex = findTransitionIndex(roundDownMillisToSeconds(j));
        if (findTransitionIndex == -1) {
            i2 = this.mEarliestRawOffset;
            i3 = i2;
            i = 0;
        } else {
            int i5 = this.mTypes[findTransitionIndex] & 255;
            i3 = this.mRawOffset + (this.mOffsets[i5] * 1000);
            if (this.mIsDsts[i5] == 0) {
                i = 0;
                i2 = i3;
            } else {
                int i6 = findTransitionIndex - 1;
                while (true) {
                    if (i6 < 0) {
                        i4 = -1;
                        break;
                    }
                    int i7 = this.mTypes[i6] & 255;
                    if (this.mIsDsts[i7] == 0) {
                        i4 = this.mRawOffset + (this.mOffsets[i7] * 1000);
                        break;
                    }
                    i6--;
                }
                i2 = i4 == -1 ? this.mEarliestRawOffset : i4;
                i = i3 - i2;
            }
        }
        iArr[0] = i2;
        iArr[1] = i;
        return i3;
    }

    @Override // java.util.TimeZone
    public int getOffset(long j) {
        int findOffsetIndexForTimeInMilliseconds = findOffsetIndexForTimeInMilliseconds(j);
        if (findOffsetIndexForTimeInMilliseconds == -1) {
            return this.mEarliestRawOffset;
        }
        return this.mRawOffset + (this.mOffsets[findOffsetIndexForTimeInMilliseconds] * 1000);
    }

    @Override // java.util.TimeZone
    public boolean inDaylightTime(Date date) {
        int findOffsetIndexForTimeInMilliseconds = findOffsetIndexForTimeInMilliseconds(date.getTime());
        if (findOffsetIndexForTimeInMilliseconds == -1) {
            return false;
        }
        if (this.mIsDsts[findOffsetIndexForTimeInMilliseconds] == 1) {
            return true;
        }
        return false;
    }

    @Override // java.util.TimeZone
    public int getRawOffset() {
        return this.mRawOffset;
    }

    @Override // java.util.TimeZone
    public int getDSTSavings() {
        return this.mDstSavings;
    }

    @Override // java.util.TimeZone
    public boolean useDaylightTime() {
        return this.mUseDst;
    }

    public boolean hasSameRules(TimeZone timeZone) {
        if (!(timeZone instanceof ZoneInfo)) {
            return false;
        }
        ZoneInfo zoneInfo = (ZoneInfo) timeZone;
        boolean z = this.mUseDst;
        if (z != zoneInfo.mUseDst) {
            return false;
        }
        if (!z) {
            if (this.mRawOffset == zoneInfo.mRawOffset) {
                return true;
            }
            return false;
        } else if (this.mRawOffset != zoneInfo.mRawOffset || !Arrays.equals(this.mOffsets, zoneInfo.mOffsets) || !Arrays.equals(this.mIsDsts, zoneInfo.mIsDsts) || !Arrays.equals(this.mTypes, zoneInfo.mTypes) || !Arrays.equals(this.mTransitions, zoneInfo.mTransitions)) {
            return false;
        } else {
            return true;
        }
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ZoneInfo)) {
            return false;
        }
        ZoneInfo zoneInfo = (ZoneInfo) obj;
        if (!getID().equals(zoneInfo.getID()) || !hasSameRules(zoneInfo)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((((((((((((getID().hashCode() + 31) * 31) + Arrays.hashCode(this.mOffsets)) * 31) + Arrays.hashCode(this.mIsDsts)) * 31) + this.mRawOffset) * 31) + Arrays.hashCode(this.mTransitions)) * 31) + Arrays.hashCode(this.mTypes)) * 31) + (this.mUseDst ? 1231 : 1237);
    }

    public String toString() {
        return ZoneInfo.class.getName() + "[id=\"" + getID() + "\",mRawOffset=" + this.mRawOffset + ",mEarliestRawOffset=" + this.mEarliestRawOffset + ",mUseDst=" + this.mUseDst + ",mDstSavings=" + this.mDstSavings + ",transitions=" + this.mTransitions.length + "]";
    }

    @Override // java.util.TimeZone
    public Object clone() {
        return super.clone();
    }
}
