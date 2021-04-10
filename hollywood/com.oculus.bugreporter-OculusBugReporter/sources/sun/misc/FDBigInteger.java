package sun.misc;

import java.math.BigInteger;
import java.util.Arrays;

public class FDBigInteger {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static final long[] LONG_5_POW = {1, 5, 25, 125, 625, 3125, 15625, 78125, 390625, 1953125, 9765625, 48828125, 244140625, 1220703125, 6103515625L, 30517578125L, 152587890625L, 762939453125L, 3814697265625L, 19073486328125L, 95367431640625L, 476837158203125L, 2384185791015625L, 11920928955078125L, 59604644775390625L, 298023223876953125L, 1490116119384765625L};
    private static final long LONG_MASK = 4294967295L;
    private static final int MAX_FIVE_POW = 340;
    private static final FDBigInteger[] POW_5_CACHE = new FDBigInteger[MAX_FIVE_POW];
    static final int[] SMALL_5_POW = {1, 5, 25, 125, 625, 3125, 15625, 78125, 390625, 1953125, 9765625, 48828125, 244140625, 1220703125};
    public static final FDBigInteger ZERO = new FDBigInteger(new int[0], 0);
    private int[] data;
    private boolean isImmutable = false;
    private int nWords;
    private int offset;

    static {
        int i = 0;
        while (true) {
            int[] iArr = SMALL_5_POW;
            if (i >= iArr.length) {
                break;
            }
            FDBigInteger pow5 = new FDBigInteger(new int[]{iArr[i]}, 0);
            pow5.makeImmutable();
            POW_5_CACHE[i] = pow5;
            i++;
        }
        FDBigInteger prev = POW_5_CACHE[i - 1];
        while (i < MAX_FIVE_POW) {
            FDBigInteger[] fDBigIntegerArr = POW_5_CACHE;
            FDBigInteger mult = prev.mult(5);
            prev = mult;
            fDBigIntegerArr[i] = mult;
            prev.makeImmutable();
            i++;
        }
        ZERO.makeImmutable();
    }

    private FDBigInteger(int[] data2, int offset2) {
        this.data = data2;
        this.offset = offset2;
        this.nWords = data2.length;
        trimLeadingZeros();
    }

    public FDBigInteger(long lValue, char[] digits, int kDigits, int nDigits) {
        this.data = new int[Math.max((nDigits + 8) / 9, 2)];
        int[] iArr = this.data;
        iArr[0] = (int) lValue;
        iArr[1] = (int) (lValue >>> 32);
        this.offset = 0;
        this.nWords = 2;
        int i = kDigits;
        int limit = nDigits - 5;
        while (i < limit) {
            int ilim = i + 5;
            int v = digits[i] - '0';
            i++;
            while (i < ilim) {
                v = ((v * 10) + digits[i]) - 48;
                i++;
            }
            multAddMe(100000, v);
        }
        int factor = 1;
        int v2 = 0;
        while (i < nDigits) {
            v2 = ((v2 * 10) + digits[i]) - 48;
            factor *= 10;
            i++;
        }
        if (factor != 1) {
            multAddMe(factor, v2);
        }
        trimLeadingZeros();
    }

    public static FDBigInteger valueOfPow52(int p5, int p2) {
        if (p5 == 0) {
            return valueOfPow2(p2);
        }
        if (p2 == 0) {
            return big5pow(p5);
        }
        int[] iArr = SMALL_5_POW;
        if (p5 >= iArr.length) {
            return big5pow(p5).leftShift(p2);
        }
        int pow5 = iArr[p5];
        int wordcount = p2 >> 5;
        int bitcount = p2 & 31;
        if (bitcount == 0) {
            return new FDBigInteger(new int[]{pow5}, wordcount);
        }
        return new FDBigInteger(new int[]{pow5 << bitcount, pow5 >>> (32 - bitcount)}, wordcount);
    }

    public static FDBigInteger valueOfMulPow52(long value, int p5, int p2) {
        int[] r;
        int v0 = (int) value;
        int v1 = (int) (value >>> 32);
        int wordcount = p2 >> 5;
        int bitcount = p2 & 31;
        if (p5 != 0) {
            int[] iArr = SMALL_5_POW;
            if (p5 < iArr.length) {
                long pow5 = ((long) iArr[p5]) & LONG_MASK;
                long carry = (((long) v0) & LONG_MASK) * pow5;
                int v02 = (int) carry;
                long carry2 = ((((long) v1) & LONG_MASK) * pow5) + (carry >>> 32);
                int v12 = (int) carry2;
                int v2 = (int) (carry2 >>> 32);
                if (bitcount == 0) {
                    return new FDBigInteger(new int[]{v02, v12, v2}, wordcount);
                }
                return new FDBigInteger(new int[]{v02 << bitcount, (v12 << bitcount) | (v02 >>> (32 - bitcount)), (v2 << bitcount) | (v12 >>> (32 - bitcount)), v2 >>> (32 - bitcount)}, wordcount);
            }
            FDBigInteger pow52 = big5pow(p5);
            if (v1 == 0) {
                r = new int[(pow52.nWords + 1 + (p2 != 0 ? 1 : 0))];
                mult(pow52.data, pow52.nWords, v0, r);
            } else {
                r = new int[(pow52.nWords + 2 + (p2 != 0 ? 1 : 0))];
                mult(pow52.data, pow52.nWords, v0, v1, r);
            }
            return new FDBigInteger(r, pow52.offset).leftShift(p2);
        } else if (p2 == 0) {
            return new FDBigInteger(new int[]{v0, v1}, 0);
        } else if (bitcount == 0) {
            return new FDBigInteger(new int[]{v0, v1}, wordcount);
        } else {
            return new FDBigInteger(new int[]{v0 << bitcount, (v1 << bitcount) | (v0 >>> (32 - bitcount)), v1 >>> (32 - bitcount)}, wordcount);
        }
    }

    private static FDBigInteger valueOfPow2(int p2) {
        return new FDBigInteger(new int[]{1 << (p2 & 31)}, p2 >> 5);
    }

    private void trimLeadingZeros() {
        int i = this.nWords;
        if (i > 0) {
            int i2 = i - 1;
            if (this.data[i2] == 0) {
                while (i2 > 0 && this.data[i2 - 1] == 0) {
                    i2--;
                }
                this.nWords = i2;
                if (i2 == 0) {
                    this.offset = 0;
                }
            }
        }
    }

    public int getNormalizationBias() {
        int i = this.nWords;
        if (i != 0) {
            int zeros = Integer.numberOfLeadingZeros(this.data[i - 1]);
            return zeros < 4 ? zeros + 28 : zeros - 4;
        }
        throw new IllegalArgumentException("Zero value cannot be normalized");
    }

    private static void leftShift(int[] src, int idx, int[] result, int bitcount, int anticount, int prev) {
        while (idx > 0) {
            int v = prev << bitcount;
            prev = src[idx - 1];
            result[idx] = v | (prev >>> anticount);
            idx--;
        }
        result[0] = prev << bitcount;
    }

    public FDBigInteger leftShift(int shift) {
        int i;
        int[] result;
        int i2;
        int[] result2;
        if (shift == 0 || (i = this.nWords) == 0) {
            return this;
        }
        int wordcount = shift >> 5;
        int bitcount = shift & 31;
        if (!this.isImmutable) {
            if (bitcount != 0) {
                int anticount = 32 - bitcount;
                int[] iArr = this.data;
                if ((iArr[0] << bitcount) == 0) {
                    int idx = 0;
                    int prev = iArr[0];
                    while (true) {
                        i2 = this.nWords;
                        if (idx >= i2 - 1) {
                            break;
                        }
                        int v = prev >>> anticount;
                        int[] iArr2 = this.data;
                        prev = iArr2[idx + 1];
                        iArr2[idx] = v | (prev << bitcount);
                        idx++;
                    }
                    int v2 = prev >>> anticount;
                    this.data[idx] = v2;
                    if (v2 == 0) {
                        this.nWords = i2 - 1;
                    }
                    this.offset++;
                } else {
                    int idx2 = i - 1;
                    int prev2 = iArr[idx2];
                    int hi = prev2 >>> anticount;
                    int[] result3 = this.data;
                    int[] src = this.data;
                    if (hi != 0) {
                        if (i == iArr.length) {
                            int[] iArr3 = new int[(i + 1)];
                            result3 = iArr3;
                            this.data = iArr3;
                        }
                        int i3 = this.nWords;
                        this.nWords = i3 + 1;
                        result3[i3] = hi;
                        result = result3;
                    } else {
                        result = result3;
                    }
                    leftShift(src, idx2, result, bitcount, anticount, prev2);
                }
            }
            this.offset += wordcount;
            return this;
        } else if (bitcount == 0) {
            return new FDBigInteger(Arrays.copyOf(this.data, i), this.offset + wordcount);
        } else {
            int anticount2 = 32 - bitcount;
            int idx3 = i - 1;
            int prev3 = this.data[idx3];
            int hi2 = prev3 >>> anticount2;
            if (hi2 != 0) {
                int[] result4 = new int[(i + 1)];
                result4[i] = hi2;
                result2 = result4;
            } else {
                result2 = new int[i];
            }
            leftShift(this.data, idx3, result2, bitcount, anticount2, prev3);
            return new FDBigInteger(result2, this.offset + wordcount);
        }
    }

    private int size() {
        return this.nWords + this.offset;
    }

    public int quoRemIteration(FDBigInteger S) throws IllegalArgumentException {
        int thSize = size();
        int sSize = S.size();
        if (thSize < sSize) {
            int[] iArr = this.data;
            int p = multAndCarryBy10(iArr, this.nWords, iArr);
            if (p != 0) {
                int[] iArr2 = this.data;
                int i = this.nWords;
                this.nWords = i + 1;
                iArr2[i] = p;
                return 0;
            }
            trimLeadingZeros();
            return 0;
        } else if (thSize <= sSize) {
            long q = (((long) this.data[this.nWords - 1]) & LONG_MASK) / (((long) S.data[S.nWords - 1]) & LONG_MASK);
            if (multDiffMe(q, S) != 0) {
                long sum = 0;
                int tStart = S.offset - this.offset;
                int[] sd = S.data;
                int[] td = this.data;
                for (long j = 0; sum == j; j = 0) {
                    int sIndex = 0;
                    int tIndex = tStart;
                    long sum2 = sum;
                    while (tIndex < this.nWords) {
                        long sum3 = sum2 + (((long) td[tIndex]) & LONG_MASK) + (((long) sd[sIndex]) & LONG_MASK);
                        td[tIndex] = (int) sum3;
                        sum2 = sum3 >>> 32;
                        sIndex++;
                        tIndex++;
                        thSize = thSize;
                    }
                    q--;
                    sum = sum2;
                    thSize = thSize;
                }
            }
            int[] iArr3 = this.data;
            multAndCarryBy10(iArr3, this.nWords, iArr3);
            trimLeadingZeros();
            return (int) q;
        } else {
            throw new IllegalArgumentException("disparate values");
        }
    }

    public FDBigInteger multBy10() {
        int i = this.nWords;
        if (i == 0) {
            return this;
        }
        if (this.isImmutable) {
            int[] res = new int[(i + 1)];
            res[i] = multAndCarryBy10(this.data, i, res);
            return new FDBigInteger(res, this.offset);
        }
        int[] res2 = this.data;
        int p = multAndCarryBy10(res2, i, res2);
        if (p != 0) {
            int i2 = this.nWords;
            int[] iArr = this.data;
            if (i2 == iArr.length) {
                if (iArr[0] == 0) {
                    int i3 = i2 - 1;
                    this.nWords = i3;
                    System.arraycopy((Object) iArr, 1, (Object) iArr, 0, i3);
                    this.offset++;
                } else {
                    this.data = Arrays.copyOf(iArr, iArr.length + 1);
                }
            }
            int[] iArr2 = this.data;
            int i4 = this.nWords;
            this.nWords = i4 + 1;
            iArr2[i4] = p;
        } else {
            trimLeadingZeros();
        }
        return this;
    }

    public FDBigInteger multByPow52(int p5, int p2) {
        if (this.nWords == 0) {
            return this;
        }
        FDBigInteger res = this;
        if (p5 != 0) {
            int extraSize = p2 != 0 ? 1 : 0;
            int[] iArr = SMALL_5_POW;
            if (p5 < iArr.length) {
                int i = this.nWords;
                int[] r = new int[(i + 1 + extraSize)];
                mult(this.data, i, iArr[p5], r);
                res = new FDBigInteger(r, this.offset);
            } else {
                FDBigInteger pow5 = big5pow(p5);
                int[] r2 = new int[(this.nWords + pow5.size() + extraSize)];
                mult(this.data, this.nWords, pow5.data, pow5.nWords, r2);
                res = new FDBigInteger(r2, this.offset + pow5.offset);
            }
        }
        return res.leftShift(p2);
    }

    private static void mult(int[] s1, int s1Len, int[] s2, int s2Len, int[] dst) {
        for (int i = 0; i < s1Len; i++) {
            long v = ((long) s1[i]) & LONG_MASK;
            long p = 0;
            for (int j = 0; j < s2Len; j++) {
                long p2 = p + (((long) dst[i + j]) & LONG_MASK) + ((((long) s2[j]) & LONG_MASK) * v);
                dst[i + j] = (int) p2;
                p = p2 >>> 32;
            }
            dst[i + s2Len] = (int) p;
        }
    }

    public FDBigInteger leftInplaceSub(FDBigInteger subtrahend) {
        FDBigInteger minuend;
        if (this.isImmutable) {
            minuend = new FDBigInteger((int[]) this.data.clone(), this.offset);
        } else {
            minuend = this;
        }
        int offsetDiff = subtrahend.offset - minuend.offset;
        int[] sData = subtrahend.data;
        int[] mData = minuend.data;
        int subLen = subtrahend.nWords;
        int minLen = minuend.nWords;
        if (offsetDiff < 0) {
            int rLen = minLen - offsetDiff;
            if (rLen < mData.length) {
                System.arraycopy((Object) mData, 0, (Object) mData, -offsetDiff, minLen);
                Arrays.fill(mData, 0, -offsetDiff, 0);
            } else {
                int[] r = new int[rLen];
                System.arraycopy((Object) mData, 0, (Object) r, -offsetDiff, minLen);
                mData = r;
                minuend.data = r;
            }
            minuend.offset = subtrahend.offset;
            minLen = rLen;
            minuend.nWords = rLen;
            offsetDiff = 0;
        }
        long borrow = 0;
        int mIndex = offsetDiff;
        int sIndex = 0;
        while (sIndex < subLen && mIndex < minLen) {
            long diff = ((((long) mData[mIndex]) & LONG_MASK) - (((long) sData[sIndex]) & LONG_MASK)) + borrow;
            mData[mIndex] = (int) diff;
            borrow = diff >> 32;
            sIndex++;
            mIndex++;
            sData = sData;
            offsetDiff = offsetDiff;
        }
        while (borrow != 0 && mIndex < minLen) {
            long diff2 = (((long) mData[mIndex]) & LONG_MASK) + borrow;
            mData[mIndex] = (int) diff2;
            borrow = diff2 >> 32;
            mIndex++;
        }
        minuend.trimLeadingZeros();
        return minuend;
    }

    public FDBigInteger rightInplaceSub(FDBigInteger subtrahend) {
        FDBigInteger subtrahend2 = subtrahend;
        FDBigInteger minuend = this;
        if (subtrahend2.isImmutable) {
            subtrahend2 = new FDBigInteger((int[]) subtrahend2.data.clone(), subtrahend2.offset);
        }
        int offsetDiff = minuend.offset - subtrahend2.offset;
        int[] sData = subtrahend2.data;
        int[] mData = minuend.data;
        int subLen = subtrahend2.nWords;
        int minLen = minuend.nWords;
        if (offsetDiff < 0) {
            if (minLen < sData.length) {
                System.arraycopy((Object) sData, 0, (Object) sData, -offsetDiff, subLen);
                Arrays.fill(sData, 0, -offsetDiff, 0);
            } else {
                int[] r = new int[minLen];
                System.arraycopy((Object) sData, 0, (Object) r, -offsetDiff, subLen);
                sData = r;
                subtrahend2.data = r;
            }
            subtrahend2.offset = minuend.offset;
            subLen -= offsetDiff;
            offsetDiff = 0;
        } else {
            int rLen = minLen + offsetDiff;
            if (rLen >= sData.length) {
                int[] copyOf = Arrays.copyOf(sData, rLen);
                sData = copyOf;
                subtrahend2.data = copyOf;
            }
        }
        int sIndex = 0;
        long borrow = 0;
        while (sIndex < offsetDiff) {
            long diff = (0 - (((long) sData[sIndex]) & LONG_MASK)) + borrow;
            sData[sIndex] = (int) diff;
            borrow = diff >> 32;
            sIndex++;
            offsetDiff = offsetDiff;
            minuend = minuend;
        }
        int mIndex = 0;
        while (mIndex < minLen) {
            long diff2 = ((((long) mData[mIndex]) & LONG_MASK) - (((long) sData[sIndex]) & LONG_MASK)) + borrow;
            sData[sIndex] = (int) diff2;
            borrow = diff2 >> 32;
            sIndex++;
            mIndex++;
            mData = mData;
            subLen = subLen;
        }
        subtrahend2.nWords = sIndex;
        subtrahend2.trimLeadingZeros();
        return subtrahend2;
    }

    private static int checkZeroTail(int[] a, int from) {
        while (from > 0) {
            from--;
            if (a[from] != 0) {
                return 1;
            }
        }
        return 0;
    }

    public int cmp(FDBigInteger other) {
        int aSize = this.nWords + this.offset;
        int bSize = other.nWords + other.offset;
        if (aSize > bSize) {
            return 1;
        }
        if (aSize < bSize) {
            return -1;
        }
        int aLen = this.nWords;
        int bLen = other.nWords;
        while (aLen > 0 && bLen > 0) {
            aLen--;
            int a = this.data[aLen];
            bLen--;
            int b = other.data[bLen];
            if (a != b) {
                if ((((long) a) & LONG_MASK) < (LONG_MASK & ((long) b))) {
                    return -1;
                }
                return 1;
            }
        }
        if (aLen > 0) {
            return checkZeroTail(this.data, aLen);
        }
        if (bLen > 0) {
            return -checkZeroTail(other.data, bLen);
        }
        return 0;
    }

    public int cmpPow52(int p5, int p2) {
        if (p5 != 0) {
            return cmp(big5pow(p5).leftShift(p2));
        }
        int wordcount = p2 >> 5;
        int bitcount = p2 & 31;
        int i = this.nWords;
        int size = this.offset + i;
        if (size > wordcount + 1) {
            return 1;
        }
        if (size < wordcount + 1) {
            return -1;
        }
        int[] iArr = this.data;
        int a = iArr[i - 1];
        int b = 1 << bitcount;
        if (a == b) {
            return checkZeroTail(iArr, i - 1);
        }
        if ((((long) a) & LONG_MASK) < (((long) b) & LONG_MASK)) {
            return -1;
        }
        return 1;
    }

    public int addAndCmp(FDBigInteger x, FDBigInteger y) {
        int sSize;
        int bSize;
        FDBigInteger small;
        FDBigInteger big;
        int xSize = x.size();
        int ySize = y.size();
        if (xSize >= ySize) {
            big = x;
            small = y;
            bSize = xSize;
            sSize = ySize;
        } else {
            big = y;
            small = x;
            bSize = ySize;
            sSize = xSize;
        }
        int thSize = size();
        if (bSize == 0) {
            if (thSize == 0) {
                return 0;
            }
            return 1;
        } else if (sSize == 0) {
            return cmp(big);
        } else {
            if (bSize > thSize) {
                return -1;
            }
            if (bSize + 1 < thSize) {
                return 1;
            }
            long top = ((long) big.data[big.nWords - 1]) & LONG_MASK;
            if (sSize == bSize) {
                top += ((long) small.data[small.nWords - 1]) & LONG_MASK;
            }
            if ((top >>> 32) == 0) {
                if (((top + 1) >>> 32) == 0) {
                    if (bSize < thSize) {
                        return 1;
                    }
                    long v = LONG_MASK & ((long) this.data[this.nWords - 1]);
                    if (v < top) {
                        return -1;
                    }
                    if (v > top + 1) {
                        return 1;
                    }
                }
            } else if (bSize + 1 > thSize) {
                return -1;
            } else {
                long top2 = top >>> 32;
                long v2 = LONG_MASK & ((long) this.data[this.nWords - 1]);
                if (v2 < top2) {
                    return -1;
                }
                if (v2 > top2 + 1) {
                    return 1;
                }
            }
            return cmp(big.add(small));
        }
    }

    public void makeImmutable() {
        this.isImmutable = true;
    }

    private FDBigInteger mult(int i) {
        int i2 = this.nWords;
        if (i2 == 0) {
            return this;
        }
        int[] r = new int[(i2 + 1)];
        mult(this.data, i2, i, r);
        return new FDBigInteger(r, this.offset);
    }

    private FDBigInteger mult(FDBigInteger other) {
        if (this.nWords == 0) {
            return this;
        }
        if (size() == 1) {
            return other.mult(this.data[0]);
        }
        if (other.nWords == 0) {
            return other;
        }
        if (other.size() == 1) {
            return mult(other.data[0]);
        }
        int i = this.nWords;
        int i2 = other.nWords;
        int[] r = new int[(i + i2)];
        mult(this.data, i, other.data, i2, r);
        return new FDBigInteger(r, this.offset + other.offset);
    }

    private FDBigInteger add(FDBigInteger other) {
        int smallLen;
        FDBigInteger small;
        int bigLen;
        FDBigInteger big;
        int oSize;
        int tSize;
        long j;
        int tSize2 = size();
        int oSize2 = other.size();
        if (tSize2 >= oSize2) {
            big = this;
            bigLen = tSize2;
            small = other;
            smallLen = oSize2;
        } else {
            big = other;
            bigLen = oSize2;
            small = this;
            smallLen = tSize2;
        }
        int[] r = new int[(bigLen + 1)];
        int i = 0;
        long carry = 0;
        while (i < smallLen) {
            int i2 = big.offset;
            long j2 = i < i2 ? 0 : ((long) big.data[i - i2]) & LONG_MASK;
            int i3 = small.offset;
            if (i < i3) {
                tSize = tSize2;
                oSize = oSize2;
                j = 0;
            } else {
                int i4 = small.data[i - i3];
                tSize = tSize2;
                oSize = oSize2;
                j = ((long) i4) & LONG_MASK;
            }
            long carry2 = carry + j2 + j;
            r[i] = (int) carry2;
            carry = carry2 >> 32;
            i++;
            tSize2 = tSize;
            oSize2 = oSize;
        }
        while (i < bigLen) {
            int i5 = big.offset;
            long carry3 = carry + (i < i5 ? 0 : ((long) big.data[i - i5]) & LONG_MASK);
            r[i] = (int) carry3;
            carry = carry3 >> 32;
            i++;
        }
        r[bigLen] = (int) carry;
        return new FDBigInteger(r, 0);
    }

    private void multAddMe(int iv, int addend) {
        int i;
        long v = ((long) iv) & LONG_MASK;
        int[] iArr = this.data;
        long p = ((((long) iArr[0]) & LONG_MASK) * v) + (((long) addend) & LONG_MASK);
        iArr[0] = (int) p;
        long p2 = p >>> 32;
        int i2 = 1;
        while (true) {
            i = this.nWords;
            if (i2 >= i) {
                break;
            }
            int[] iArr2 = this.data;
            long p3 = p2 + ((((long) iArr2[i2]) & LONG_MASK) * v);
            iArr2[i2] = (int) p3;
            p2 = p3 >>> 32;
            i2++;
        }
        if (p2 != 0) {
            int[] iArr3 = this.data;
            this.nWords = i + 1;
            iArr3[i] = (int) p2;
        }
    }

    private long multDiffMe(long q, FDBigInteger S) {
        long diff = 0;
        if (q != 0) {
            int deltaSize = S.offset - this.offset;
            if (deltaSize >= 0) {
                int[] sd = S.data;
                int[] td = this.data;
                int sIndex = 0;
                int tIndex = deltaSize;
                while (sIndex < S.nWords) {
                    long diff2 = diff + ((((long) td[tIndex]) & LONG_MASK) - ((((long) sd[sIndex]) & LONG_MASK) * q));
                    td[tIndex] = (int) diff2;
                    diff = diff2 >> 32;
                    sIndex++;
                    tIndex++;
                }
            } else {
                int deltaSize2 = -deltaSize;
                int[] rd = new int[(this.nWords + deltaSize2)];
                int sIndex2 = 0;
                int rIndex = 0;
                int[] sd2 = S.data;
                while (rIndex < deltaSize2 && sIndex2 < S.nWords) {
                    long diff3 = diff - ((((long) sd2[sIndex2]) & LONG_MASK) * q);
                    rd[rIndex] = (int) diff3;
                    diff = diff3 >> 32;
                    sIndex2++;
                    rIndex++;
                }
                int tIndex2 = 0;
                int[] td2 = this.data;
                while (sIndex2 < S.nWords) {
                    long diff4 = diff + ((((long) td2[tIndex2]) & LONG_MASK) - ((((long) sd2[sIndex2]) & LONG_MASK) * q));
                    rd[rIndex] = (int) diff4;
                    diff = diff4 >> 32;
                    sIndex2++;
                    tIndex2++;
                    rIndex++;
                    deltaSize2 = deltaSize2;
                }
                this.nWords += deltaSize2;
                this.offset -= deltaSize2;
                this.data = rd;
            }
        }
        return diff;
    }

    private static int multAndCarryBy10(int[] src, int srcLen, int[] dst) {
        long carry = 0;
        for (int i = 0; i < srcLen; i++) {
            long product = ((((long) src[i]) & LONG_MASK) * 10) + carry;
            dst[i] = (int) product;
            carry = product >>> 32;
        }
        return (int) carry;
    }

    private static void mult(int[] src, int srcLen, int value, int[] dst) {
        long val = ((long) value) & LONG_MASK;
        long carry = 0;
        for (int i = 0; i < srcLen; i++) {
            long product = ((((long) src[i]) & LONG_MASK) * val) + carry;
            dst[i] = (int) product;
            carry = product >>> 32;
        }
        dst[srcLen] = (int) carry;
    }

    private static void mult(int[] src, int srcLen, int v0, int v1, int[] dst) {
        long v = ((long) v0) & LONG_MASK;
        long carry = 0;
        for (int j = 0; j < srcLen; j++) {
            long product = ((((long) src[j]) & LONG_MASK) * v) + carry;
            dst[j] = (int) product;
            carry = product >>> 32;
        }
        dst[srcLen] = (int) carry;
        long v2 = ((long) v1) & LONG_MASK;
        long carry2 = 0;
        for (int j2 = 0; j2 < srcLen; j2++) {
            long product2 = (((long) dst[j2 + 1]) & LONG_MASK) + ((((long) src[j2]) & LONG_MASK) * v2) + carry2;
            dst[j2 + 1] = (int) product2;
            carry2 = product2 >>> 32;
        }
        dst[srcLen + 1] = (int) carry2;
    }

    private static FDBigInteger big5pow(int p) {
        if (p < MAX_FIVE_POW) {
            return POW_5_CACHE[p];
        }
        return big5powRec(p);
    }

    private static FDBigInteger big5powRec(int p) {
        if (p < MAX_FIVE_POW) {
            return POW_5_CACHE[p];
        }
        int q = p >> 1;
        int r = p - q;
        FDBigInteger bigq = big5powRec(q);
        int[] iArr = SMALL_5_POW;
        if (r < iArr.length) {
            return bigq.mult(iArr[r]);
        }
        return bigq.mult(big5powRec(r));
    }

    public String toHexString() {
        int i = this.nWords;
        if (i == 0) {
            return AndroidHardcodedSystemProperties.JAVA_VERSION;
        }
        StringBuilder sb = new StringBuilder((i + this.offset) * 8);
        for (int i2 = this.nWords - 1; i2 >= 0; i2--) {
            String subStr = Integer.toHexString(this.data[i2]);
            for (int j = subStr.length(); j < 8; j++) {
                sb.append('0');
            }
            sb.append(subStr);
        }
        for (int i3 = this.offset; i3 > 0; i3--) {
            sb.append("00000000");
        }
        return sb.toString();
    }

    public BigInteger toBigInteger() {
        byte[] magnitude = new byte[((this.nWords * 4) + 1)];
        for (int i = 0; i < this.nWords; i++) {
            int w = this.data[i];
            magnitude[(magnitude.length - (i * 4)) - 1] = (byte) w;
            magnitude[(magnitude.length - (i * 4)) - 2] = (byte) (w >> 8);
            magnitude[(magnitude.length - (i * 4)) - 3] = (byte) (w >> 16);
            magnitude[(magnitude.length - (i * 4)) - 4] = (byte) (w >> 24);
        }
        return new BigInteger(magnitude).shiftLeft(this.offset * 32);
    }

    public String toString() {
        return toBigInteger().toString();
    }
}
