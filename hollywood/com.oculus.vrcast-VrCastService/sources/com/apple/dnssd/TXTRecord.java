package com.apple.dnssd;

import java.io.UnsupportedEncodingException;

public class TXTRecord {
    protected static final byte kAttrSep = 61;
    protected byte[] fBytes;

    public TXTRecord() {
        this.fBytes = new byte[0];
    }

    public TXTRecord(byte[] bArr) {
        this.fBytes = (byte[]) bArr.clone();
    }

    public void set(String str, String str2) {
        set(str, str2 != null ? str2.getBytes() : null);
    }

    public void set(String str, byte[] bArr) {
        int length = bArr != null ? bArr.length : 0;
        try {
            byte[] bytes = str.getBytes("US-ASCII");
            for (byte b : bytes) {
                if (b == 61) {
                    throw new IllegalArgumentException();
                }
            }
            if (bytes.length + length < 255) {
                int remove = remove(str);
                if (remove == -1) {
                    remove = size();
                }
                insert(bytes, bArr, remove);
                return;
            }
            throw new ArrayIndexOutOfBoundsException();
        } catch (UnsupportedEncodingException unused) {
            throw new IllegalArgumentException();
        }
    }

    /* access modifiers changed from: protected */
    public void insert(byte[] bArr, byte[] bArr2, int i) {
        byte[] bArr3 = this.fBytes;
        int length = bArr2 != null ? bArr2.length : 0;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            byte[] bArr4 = this.fBytes;
            if (i2 >= bArr4.length) {
                break;
            }
            i2 += (bArr4[i2] + 1) & 255;
        }
        int length2 = bArr.length + length + (bArr2 != null ? 1 : 0);
        int length3 = bArr3.length + length2 + 1;
        this.fBytes = new byte[length3];
        System.arraycopy(bArr3, 0, this.fBytes, 0, i2);
        int length4 = bArr3.length - i2;
        System.arraycopy(bArr3, i2, this.fBytes, length3 - length4, length4);
        byte[] bArr5 = this.fBytes;
        bArr5[i2] = (byte) length2;
        int i4 = i2 + 1;
        System.arraycopy(bArr, 0, bArr5, i4, bArr.length);
        if (bArr2 != null) {
            byte[] bArr6 = this.fBytes;
            bArr6[i4 + bArr.length] = kAttrSep;
            System.arraycopy(bArr2, 0, bArr6, i2 + bArr.length + 2, length);
        }
    }

    public int remove(String str) {
        int i = 0;
        int i2 = 0;
        while (true) {
            byte[] bArr = this.fBytes;
            if (i >= bArr.length) {
                return -1;
            }
            byte b = bArr[i];
            if (str.length() > b || !((str.length() == b || this.fBytes[str.length() + i + 1] == 61) && str.compareToIgnoreCase(new String(this.fBytes, i + 1, str.length())) == 0)) {
                i += (b + 1) & 255;
                i2++;
            } else {
                byte[] bArr2 = this.fBytes;
                this.fBytes = new byte[((bArr2.length - b) - 1)];
                System.arraycopy(bArr2, 0, this.fBytes, 0, i);
                System.arraycopy(bArr2, i + b + 1, this.fBytes, i, ((bArr2.length - i) - b) - 1);
                return i2;
            }
        }
    }

    public int size() {
        int i = 0;
        int i2 = 0;
        while (true) {
            byte[] bArr = this.fBytes;
            if (i >= bArr.length) {
                return i2;
            }
            i += (bArr[i] + 1) & 255;
            i2++;
        }
    }

    public boolean contains(String str) {
        int i = 0;
        while (true) {
            String key = getKey(i);
            if (key == null) {
                return false;
            }
            if (str.compareToIgnoreCase(key) == 0) {
                return true;
            }
            i++;
        }
    }

    public String getKey(int i) {
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < i; i4++) {
            byte[] bArr = this.fBytes;
            if (i3 >= bArr.length) {
                break;
            }
            i3 += bArr[i3] + 1;
        }
        byte[] bArr2 = this.fBytes;
        if (i3 >= bArr2.length) {
            return null;
        }
        byte b = bArr2[i3];
        while (i2 < b && this.fBytes[i3 + i2 + 1] != 61) {
            i2++;
        }
        return new String(this.fBytes, i3 + 1, i2);
    }

    public byte[] getValue(int i) {
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            byte[] bArr = this.fBytes;
            if (i2 >= bArr.length) {
                break;
            }
            i2 += bArr[i2] + 1;
        }
        byte[] bArr2 = this.fBytes;
        if (i2 < bArr2.length) {
            byte b = bArr2[i2];
            for (int i4 = 0; i4 < b; i4++) {
                byte[] bArr3 = this.fBytes;
                int i5 = i2 + i4;
                if (bArr3[i5 + 1] == 61) {
                    int i6 = (b - i4) - 1;
                    byte[] bArr4 = new byte[i6];
                    System.arraycopy(bArr3, i5 + 2, bArr4, 0, i6);
                    return bArr4;
                }
            }
        }
        return null;
    }

    public String getValueAsString(int i) {
        byte[] value = getValue(i);
        if (value != null) {
            return new String(value);
        }
        return null;
    }

    public byte[] getValue(String str) {
        int i = 0;
        while (true) {
            String key = getKey(i);
            if (key == null) {
                return null;
            }
            if (str.compareToIgnoreCase(key) == 0) {
                return getValue(i);
            }
            i++;
        }
    }

    public String getValueAsString(String str) {
        byte[] value = getValue(str);
        if (value != null) {
            return new String(value);
        }
        return null;
    }

    public byte[] getRawBytes() {
        return (byte[]) this.fBytes.clone();
    }

    public String toString() {
        String str;
        String str2 = null;
        int i = 0;
        while (true) {
            String key = getKey(i);
            if (key == null) {
                break;
            }
            String str3 = String.valueOf(i) + "={" + key;
            String valueAsString = getValueAsString(i);
            if (valueAsString != null) {
                str = str3 + "=" + valueAsString + "}";
            } else {
                str = str3 + "}";
            }
            if (str2 == null) {
                str2 = str;
            } else {
                str2 = str2 + ", " + str;
            }
            i++;
        }
        return str2 != null ? str2 : "";
    }
}
