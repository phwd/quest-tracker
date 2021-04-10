package android.icu.text;

public final class CollationKey implements Comparable<CollationKey> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int MERGE_SEPERATOR_ = 2;
    private int m_hashCode_;
    private byte[] m_key_;
    private int m_length_;
    private String m_source_;

    public static final class BoundMode {
        @Deprecated
        public static final int COUNT = 3;
        public static final int LOWER = 0;
        public static final int UPPER = 1;
        public static final int UPPER_LONG = 2;

        private BoundMode() {
        }
    }

    public CollationKey(String source, byte[] key) {
        this(source, key, -1);
    }

    private CollationKey(String source, byte[] key, int length) {
        this.m_source_ = source;
        this.m_key_ = key;
        this.m_hashCode_ = 0;
        this.m_length_ = length;
    }

    public CollationKey(String source, RawCollationKey key) {
        this.m_source_ = source;
        this.m_length_ = key.size - 1;
        this.m_key_ = key.releaseBytes();
        this.m_hashCode_ = 0;
    }

    public String getSourceString() {
        return this.m_source_;
    }

    public byte[] toByteArray() {
        int length = getLength() + 1;
        byte[] result = new byte[length];
        System.arraycopy(this.m_key_, 0, result, 0, length);
        return result;
    }

    public int compareTo(CollationKey target) {
        int i = 0;
        while (true) {
            int l = this.m_key_[i] & 255;
            int r = target.m_key_[i] & 255;
            if (l < r) {
                return -1;
            }
            if (l > r) {
                return 1;
            }
            if (l == 0) {
                return 0;
            }
            i++;
        }
    }

    public boolean equals(Object target) {
        if (!(target instanceof CollationKey)) {
            return false;
        }
        return equals((CollationKey) target);
    }

    public boolean equals(CollationKey target) {
        if (this == target) {
            return true;
        }
        if (target == null) {
            return false;
        }
        int i = 0;
        while (true) {
            byte[] bArr = this.m_key_;
            if (bArr[i] != target.m_key_[i]) {
                return false;
            }
            if (bArr[i] == 0) {
                return true;
            }
            i++;
        }
    }

    public int hashCode() {
        if (this.m_hashCode_ == 0) {
            byte[] bArr = this.m_key_;
            if (bArr == null) {
                this.m_hashCode_ = 1;
            } else {
                StringBuilder key = new StringBuilder(bArr.length >> 1);
                int i = 0;
                while (true) {
                    byte[] bArr2 = this.m_key_;
                    if (bArr2[i] == 0 || bArr2[i + 1] == 0) {
                        byte[] bArr3 = this.m_key_;
                    } else {
                        key.append((char) ((bArr2[i + 1] & 255) | (bArr2[i] << 8)));
                        i += 2;
                    }
                }
                byte[] bArr32 = this.m_key_;
                if (bArr32[i] != 0) {
                    key.append((char) (bArr32[i] << 8));
                }
                this.m_hashCode_ = key.toString().hashCode();
            }
        }
        return this.m_hashCode_;
    }

    public CollationKey getBound(int boundType, int noOfLevels) {
        int offset;
        int offset2 = 0;
        int keystrength = 0;
        if (noOfLevels > 0) {
            while (true) {
                byte[] bArr = this.m_key_;
                if (offset2 >= bArr.length || bArr[offset2] == 0) {
                    break;
                }
                offset = offset2 + 1;
                if (bArr[offset2] == 1) {
                    keystrength++;
                    noOfLevels--;
                    if (noOfLevels == 0 || offset == bArr.length || bArr[offset] == 0) {
                        offset2 = offset - 1;
                    } else {
                        offset2 = offset;
                    }
                } else {
                    offset2 = offset;
                }
            }
            offset2 = offset - 1;
        }
        if (noOfLevels <= 0) {
            byte[] resultkey = new byte[(offset2 + boundType + 1)];
            System.arraycopy(this.m_key_, 0, resultkey, 0, offset2);
            if (boundType != 0) {
                if (boundType == 1) {
                    resultkey[offset2] = 2;
                    offset2++;
                } else if (boundType == 2) {
                    int offset3 = offset2 + 1;
                    resultkey[offset2] = -1;
                    offset2 = offset3 + 1;
                    resultkey[offset3] = -1;
                } else {
                    throw new IllegalArgumentException("Illegal boundType argument");
                }
            }
            resultkey[offset2] = 0;
            return new CollationKey(null, resultkey, offset2);
        }
        throw new IllegalArgumentException("Source collation key has only " + keystrength + " strength level. Call getBound() again  with noOfLevels < " + keystrength);
    }

    /* JADX INFO: Multiple debug info for r2v5 int: [D('remainingLength' int), D('rindex' int)] */
    public CollationKey merge(CollationKey source) {
        int rindex;
        byte[] bArr;
        if (source == null || source.getLength() == 0) {
            throw new IllegalArgumentException("CollationKey argument can not be null or of 0 length");
        }
        byte[] result = new byte[(getLength() + source.getLength() + 2)];
        int rindex2 = 0;
        int index = 0;
        int sourceindex = 0;
        while (true) {
            byte[] bArr2 = this.m_key_;
            if (bArr2[index] < 0 || bArr2[index] >= 2) {
                result[rindex2] = this.m_key_[index];
                rindex2++;
                index++;
            } else {
                rindex = rindex2 + 1;
                result[rindex2] = 2;
                while (true) {
                    bArr = source.m_key_;
                    if (bArr[sourceindex] >= 0 && bArr[sourceindex] < 2) {
                        break;
                    }
                    result[rindex] = source.m_key_[sourceindex];
                    rindex++;
                    sourceindex++;
                }
                if (this.m_key_[index] == 1 && bArr[sourceindex] == 1) {
                    index++;
                    sourceindex++;
                    rindex2 = rindex + 1;
                    result[rindex] = 1;
                } else {
                    int remainingLength = this.m_length_ - index;
                }
            }
        }
        int remainingLength2 = this.m_length_ - index;
        if (remainingLength2 > 0) {
            System.arraycopy(this.m_key_, index, result, rindex, remainingLength2);
            rindex += remainingLength2;
        } else {
            int remainingLength3 = source.m_length_ - sourceindex;
            if (remainingLength3 > 0) {
                System.arraycopy(source.m_key_, sourceindex, result, rindex, remainingLength3);
                rindex += remainingLength3;
            }
        }
        result[rindex] = 0;
        return new CollationKey(null, result, rindex);
    }

    private int getLength() {
        int i = this.m_length_;
        if (i >= 0) {
            return i;
        }
        int length = this.m_key_.length;
        int index = 0;
        while (true) {
            if (index >= length) {
                break;
            } else if (this.m_key_[index] == 0) {
                length = index;
                break;
            } else {
                index++;
            }
        }
        this.m_length_ = length;
        return this.m_length_;
    }
}
