package X;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.annotation.Nullable;

/* renamed from: X.0h7  reason: invalid class name and case insensitive filesystem */
public class C04610h7 implements Serializable, Comparable<C04610h7> {
    public static final char[] A02 = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    public static final C04610h7 A03 = A05(new byte[0]);
    public static final long serialVersionUID = 1;
    public transient int A00;
    public transient String A01;
    public final byte[] data;

    public C04610h7 A0D() {
        int i = 0;
        while (true) {
            byte[] bArr = this.data;
            if (i >= bArr.length) {
                return this;
            }
            byte b = bArr[i];
            if (b < 65 || b > 90) {
                i++;
            } else {
                byte[] bArr2 = (byte[]) bArr.clone();
                bArr2[i] = (byte) (b + 32);
                for (int i2 = i + 1; i2 < bArr2.length; i2++) {
                    byte b2 = bArr2[i2];
                    if (b2 >= 65 && b2 <= 90) {
                        bArr2[i2] = (byte) (b2 + 32);
                    }
                }
                return new C04610h7(bArr2);
            }
        }
    }

    public boolean equals(Object obj) {
        C04610h7 r6;
        byte[] bArr;
        int length;
        return obj == this || ((obj instanceof C04610h7) && (r6 = (C04610h7) obj).A07() == (length = (bArr = this.data).length) && r6.A0H(0, bArr, 0, length));
    }

    public static int A01(char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        }
        char c2 = 'a';
        if (c < 'a' || c > 'f') {
            c2 = 'A';
            if (c < 'A' || c > 'F') {
                throw new IllegalArgumentException("Unexpected hex digit: " + c);
            }
        }
        return (c - c2) + 10;
    }

    @Nullable
    public static C04610h7 A02(String str) {
        int i;
        if (str != null) {
            int length = str.length();
            while (length > 0 && ((r1 = str.charAt(length - 1)) == '=' || r1 == '\n' || r1 == '\r' || r1 == ' ' || r1 == '\t')) {
                length--;
            }
            int i2 = (int) ((((long) length) * 6) / 8);
            byte[] bArr = new byte[i2];
            int i3 = 0;
            int i4 = 0;
            int i5 = 0;
            for (int i6 = 0; i6 < length; i6++) {
                char charAt = str.charAt(i6);
                if (charAt >= 'A' && charAt <= 'Z') {
                    i = charAt - 'A';
                } else if (charAt >= 'a' && charAt <= 'z') {
                    i = charAt - 'G';
                } else if (charAt >= '0' && charAt <= '9') {
                    i = charAt + 4;
                } else if (charAt == '+' || charAt == '-') {
                    i = 62;
                } else if (charAt == '/' || charAt == '_') {
                    i = 63;
                } else {
                    if (!(charAt == '\n' || charAt == '\r' || charAt == ' ' || charAt == '\t')) {
                        return null;
                    }
                }
                i4 = (i4 << 6) | ((byte) i);
                i3++;
                if (i3 % 4 == 0) {
                    int i7 = i5 + 1;
                    bArr[i5] = (byte) (i4 >> 16);
                    int i8 = i7 + 1;
                    bArr[i7] = (byte) (i4 >> 8);
                    i5 = i8 + 1;
                    bArr[i8] = (byte) i4;
                }
            }
            int i9 = i3 % 4;
            if (i9 == 1) {
                return null;
            }
            if (i9 == 2) {
                bArr[i5] = (byte) ((i4 << 12) >> 16);
                i5++;
            } else if (i9 == 3) {
                int i10 = i4 << 6;
                int i11 = i5 + 1;
                bArr[i5] = (byte) (i10 >> 16);
                i5 = i11 + 1;
                bArr[i11] = (byte) (i10 >> 8);
            }
            if (i5 != i2) {
                byte[] bArr2 = new byte[i5];
                System.arraycopy(bArr, 0, bArr2, 0, i5);
                bArr = bArr2;
            }
            return new C04610h7(bArr);
        }
        throw new IllegalArgumentException("base64 == null");
    }

    public static C04610h7 A03(String str) {
        String str2;
        if (str != null) {
            int length = str.length();
            if (length % 2 == 0) {
                int i = length >> 1;
                byte[] bArr = new byte[i];
                for (int i2 = 0; i2 < i; i2++) {
                    int i3 = i2 << 1;
                    bArr[i2] = (byte) ((A01(str.charAt(i3)) << 4) + A01(str.charAt(i3 + 1)));
                }
                return A05(bArr);
            }
            str2 = AnonymousClass006.A05("Unexpected hex string: ", str);
        } else {
            str2 = "hex == null";
        }
        throw new IllegalArgumentException(str2);
    }

    public static C04610h7 A04(String str) {
        if (str != null) {
            C04610h7 r0 = new C04610h7(str.getBytes(C04530gy.A00));
            r0.A01 = str;
            return r0;
        }
        throw new IllegalArgumentException("s == null");
    }

    public static C04610h7 A05(byte... bArr) {
        if (bArr != null) {
            return new C04610h7((byte[]) bArr.clone());
        }
        throw new IllegalArgumentException("data == null");
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeInt(this.data.length);
        objectOutputStream.write(this.data);
    }

    public byte A06(int i) {
        return this.data[i];
    }

    public int A07() {
        return this.data.length;
    }

    public String A08() {
        byte[] bArr = this.data;
        byte[] bArr2 = C04640hA.A00;
        int length = bArr.length;
        byte[] bArr3 = new byte[(((length + 2) / 3) << 2)];
        int i = length % 3;
        int i2 = length - i;
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4 += 3) {
            int i5 = i3 + 1;
            bArr3[i3] = bArr2[(bArr[i4] & 255) >> 2];
            int i6 = i5 + 1;
            int i7 = i4 + 1;
            bArr3[i5] = bArr2[((bArr[i4] & 3) << 4) | ((bArr[i7] & 255) >> 4)];
            int i8 = i6 + 1;
            int i9 = i4 + 2;
            bArr3[i6] = bArr2[((bArr[i7] & 15) << 2) | ((bArr[i9] & 255) >> 6)];
            i3 = i8 + 1;
            bArr3[i8] = bArr2[bArr[i9] & 63];
        }
        if (i == 1) {
            int i10 = i3 + 1;
            bArr3[i3] = bArr2[(bArr[i2] & 255) >> 2];
            int i11 = i10 + 1;
            bArr3[i10] = bArr2[(bArr[i2] & 3) << 4];
            bArr3[i11] = 61;
            bArr3[i11 + 1] = 61;
        } else if (i == 2) {
            int i12 = i3 + 1;
            bArr3[i3] = bArr2[(bArr[i2] & 255) >> 2];
            int i13 = i12 + 1;
            int i14 = (bArr[i2] & 3) << 4;
            int i15 = i2 + 1;
            bArr3[i12] = bArr2[((bArr[i15] & 255) >> 4) | i14];
            bArr3[i13] = bArr2[(bArr[i15] & 15) << 2];
            bArr3[i13 + 1] = 61;
        }
        try {
            return new String(bArr3, "US-ASCII");
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError(e);
        }
    }

    public String A09() {
        byte[] bArr = this.data;
        int length = bArr.length;
        char[] cArr = new char[(length << 1)];
        int i = 0;
        for (byte b : bArr) {
            int i2 = i + 1;
            char[] cArr2 = A02;
            cArr[i] = cArr2[(b >> 4) & 15];
            i = i2 + 1;
            cArr[i2] = cArr2[b & 15];
        }
        return new String(cArr);
    }

    public String A0A() {
        String str = this.A01;
        if (str != null) {
            return str;
        }
        String str2 = new String(this.data, C04530gy.A00);
        this.A01 = str2;
        return str2;
    }

    public C04610h7 A0B() {
        try {
            return A05(MessageDigest.getInstance("SHA-1").digest(this.data));
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError(e);
        }
    }

    public C04610h7 A0C() {
        try {
            return A05(MessageDigest.getInstance("SHA-256").digest(this.data));
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError(e);
        }
    }

    public C04610h7 A0E(int i, int i2) {
        String str;
        if (i >= 0) {
            byte[] bArr = this.data;
            int length = bArr.length;
            if (i2 <= length) {
                int i3 = i2 - i;
                if (i3 < 0) {
                    str = "endIndex < beginIndex";
                } else if (i == 0 && i2 == length) {
                    return this;
                } else {
                    byte[] bArr2 = new byte[i3];
                    System.arraycopy(bArr, i, bArr2, 0, i3);
                    return new C04610h7(bArr2);
                }
            } else {
                str = AnonymousClass006.A02("endIndex > length(", length, ")");
            }
        } else {
            str = "beginIndex < 0";
        }
        throw new IllegalArgumentException(str);
    }

    public void A0F(AnonymousClass0HR r4) {
        byte[] bArr = this.data;
        r4.A0J(bArr, 0, bArr.length);
    }

    public boolean A0G(int i, C04610h7 r3, int i2, int i3) {
        return r3.A0H(i2, this.data, i, i3);
    }

    public boolean A0H(int i, byte[] bArr, int i2, int i3) {
        if (i < 0) {
            return false;
        }
        byte[] bArr2 = this.data;
        if (i > bArr2.length - i3 || i2 < 0 || i2 > bArr.length - i3) {
            return false;
        }
        for (int i4 = 0; i4 < i3; i4++) {
            if (bArr2[i4 + i] != bArr[i4 + i2]) {
                return false;
            }
        }
        return true;
    }

    public byte[] A0I() {
        return (byte[]) this.data.clone();
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // java.lang.Comparable
    public final int compareTo(C04610h7 r9) {
        C04610h7 r92 = r9;
        int A07 = A07();
        int A072 = r92.A07();
        int min = Math.min(A07, A072);
        for (int i = 0; i < min; i++) {
            int A06 = A06(i) & 255;
            int A062 = r92.A06(i) & 255;
            if (A06 != A062) {
                if (A06 < A062) {
                    return -1;
                } else {
                    return 1;
                }
            }
        }
        if (A07 == A072) {
            return 0;
        }
        if (A07 < A072) {
            return -1;
        }
        return 1;
    }

    public int hashCode() {
        int i = this.A00;
        if (i != 0) {
            return i;
        }
        int hashCode = Arrays.hashCode(this.data);
        this.A00 = hashCode;
        return hashCode;
    }

    public String toString() {
        StringBuilder sb;
        String str;
        if (this.data.length == 0) {
            return "[size=0]";
        }
        String A0A = A0A();
        int length = A0A.length();
        int i = 0;
        int i2 = 0;
        while (true) {
            if (i < length) {
                if (i2 == 64) {
                    break;
                }
                int codePointAt = A0A.codePointAt(i);
                if (Character.isISOControl(codePointAt)) {
                    if (!(codePointAt == 10 || codePointAt == 13)) {
                        break;
                    }
                } else if (codePointAt == 65533) {
                    break;
                }
                i2++;
                i += Character.charCount(codePointAt);
            } else {
                i = length;
                break;
            }
        }
        i = -1;
        if (i == -1) {
            int length2 = this.data.length;
            if (length2 <= 64) {
                sb = new StringBuilder("[hex=");
                str = A09();
            } else {
                sb = new StringBuilder();
                sb.append("[size=");
                sb.append(length2);
                sb.append(" hex=");
                str = A0E(0, 64).A09();
                sb.append(str);
                sb.append("…]");
                return sb.toString();
            }
        } else {
            str = A0A.substring(0, i).replace("\\", "\\\\").replace("\n", "\\n").replace("\r", "\\r");
            if (i < length) {
                sb = new StringBuilder();
                sb.append("[size=");
                sb.append(this.data.length);
                sb.append(" text=");
                sb.append(str);
                sb.append("…]");
                return sb.toString();
            }
            sb = new StringBuilder("[text=");
        }
        sb.append(str);
        sb.append("]");
        return sb.toString();
    }

    public C04610h7(byte[] bArr) {
        this.data = bArr;
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException {
        int readInt = objectInputStream.readInt();
        if (readInt >= 0) {
            byte[] bArr = new byte[readInt];
            int i = 0;
            while (i < readInt) {
                int read = objectInputStream.read(bArr, i, readInt - i);
                if (read != -1) {
                    i += read;
                } else {
                    throw new EOFException();
                }
            }
            C04610h7 r2 = new C04610h7(bArr);
            try {
                Field declaredField = C04610h7.class.getDeclaredField("data");
                declaredField.setAccessible(true);
                declaredField.set(this, r2.data);
            } catch (IllegalAccessException | NoSuchFieldException unused) {
                throw new AssertionError();
            }
        } else {
            throw new IllegalArgumentException(AnonymousClass006.A01("byteCount < 0: ", readInt));
        }
    }
}
