package X;

import com.facebook.acra.LogCatCollector;
import com.facebook.acra.util.UrlEncodingWriter;
import java.io.EOFException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/* renamed from: X.cm  reason: case insensitive filesystem */
public class C0603cm implements Comparable, Serializable {
    public static final char[] A02 = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    public static final C0603cm A03 = A03(new byte[0]);
    public static final long serialVersionUID = 1;
    public transient int A00;
    public transient String A01;
    public final byte[] data;

    public boolean equals(Object obj) {
        C0603cm cmVar;
        byte[] bArr;
        int length;
        return obj == this || ((obj instanceof C0603cm) && (cmVar = (C0603cm) obj).A05() == (length = (bArr = this.data).length) && cmVar.A0F(0, bArr, 0, length));
    }

    public static int A00(char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        }
        char c2 = 'a';
        if (c < 'a' || c > 'f') {
            c2 = 'A';
            if (c < 'A' || c > 'F') {
                StringBuilder sb = new StringBuilder("Unexpected hex digit: ");
                sb.append(c);
                throw new IllegalArgumentException(sb.toString());
            }
        }
        return (c - c2) + 10;
    }

    public static C0603cm A01(String str) {
        if (str != null) {
            int length = str.length();
            if (length % 2 == 0) {
                int i = length >> 1;
                byte[] bArr = new byte[i];
                for (int i2 = 0; i2 < i; i2++) {
                    int i3 = i2 << 1;
                    bArr[i2] = (byte) ((A00(str.charAt(i3)) << 4) + A00(str.charAt(i3 + 1)));
                }
                return A03(bArr);
            }
            throw new IllegalArgumentException(AnonymousClass08.A04("Unexpected hex string: ", str));
        }
        throw new IllegalArgumentException("hex == null");
    }

    public static C0603cm A02(String str) {
        if (str != null) {
            C0603cm cmVar = new C0603cm(str.getBytes(C0611cu.A00));
            cmVar.A01 = str;
            return cmVar;
        }
        throw new IllegalArgumentException("s == null");
    }

    public static C0603cm A03(byte... bArr) {
        if (bArr != null) {
            return new C0603cm((byte[]) bArr.clone());
        }
        throw new IllegalArgumentException("data == null");
    }

    private final C0603cm A0C(int i, int i2) {
        if (this instanceof C1108sv) {
            return new C0603cm(A0G()).A0C(i, i2);
        }
        if (i >= 0) {
            byte[] bArr = this.data;
            int length = bArr.length;
            if (i2 <= length) {
                int i3 = i2 - i;
                if (i3 < 0) {
                    throw new IllegalArgumentException("endIndex < beginIndex");
                } else if (i == 0 && i2 == length) {
                    return this;
                } else {
                    byte[] bArr2 = new byte[i3];
                    System.arraycopy(bArr, i, bArr2, 0, i3);
                    return new C0603cm(bArr2);
                }
            } else {
                throw new IllegalArgumentException(AnonymousClass08.A01("endIndex > length(", length, ")"));
            }
        } else {
            throw new IllegalArgumentException("beginIndex < 0");
        }
    }

    private final boolean A0F(int i, byte[] bArr, int i2, int i3) {
        int i4;
        if (this instanceof C1108sv) {
            C1108sv svVar = (C1108sv) this;
            if (i < 0 || i > svVar.A05() - i3 || i2 < 0 || i2 > bArr.length - i3) {
                return false;
            }
            int A002 = C1108sv.A00(svVar, i);
            while (i3 > 0) {
                if (A002 == 0) {
                    i4 = 0;
                } else {
                    i4 = svVar.A00[A002 - 1];
                }
                int[] iArr = svVar.A00;
                int min = Math.min(i3, ((iArr[A002] - i4) + i4) - i);
                byte[][] bArr2 = svVar.A01;
                int i5 = (i - i4) + iArr[bArr2.length + A002];
                byte[] bArr3 = bArr2[A002];
                for (int i6 = 0; i6 < min; i6++) {
                    if (bArr3[i6 + i5] != bArr[i6 + i2]) {
                        return false;
                    }
                }
                i += min;
                i2 += min;
                i3 -= min;
                A002++;
            }
            return true;
        } else if (i < 0) {
            return false;
        } else {
            byte[] bArr4 = this.data;
            if (i > bArr4.length - i3 || i2 < 0 || i2 > bArr.length - i3) {
                return false;
            }
            for (int i7 = 0; i7 < i3; i7++) {
                if (bArr4[i7 + i] != bArr[i7 + i2]) {
                    return false;
                }
            }
            return true;
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) {
        objectOutputStream.writeInt(this.data.length);
        objectOutputStream.write(this.data);
    }

    public final byte A04(int i) {
        int i2;
        byte[] bArr;
        if (!(this instanceof C1108sv)) {
            bArr = this.data;
        } else {
            C1108sv svVar = (C1108sv) this;
            int[] iArr = svVar.A00;
            byte[][] bArr2 = svVar.A01;
            int length = bArr2.length;
            C0611cu.A00((long) iArr[length - 1], (long) i, 1);
            int A002 = C1108sv.A00(svVar, i);
            if (A002 == 0) {
                i2 = 0;
            } else {
                i2 = iArr[A002 - 1];
            }
            int i3 = iArr[length + A002];
            bArr = bArr2[A002];
            i = (i - i2) + i3;
        }
        return bArr[i];
    }

    public final int A05() {
        if (!(this instanceof C1108sv)) {
            return this.data.length;
        }
        C1108sv svVar = (C1108sv) this;
        return svVar.A00[svVar.A01.length - 1];
    }

    public final String A06() {
        if (this instanceof C1108sv) {
            return new C0603cm(A0G()).A06();
        }
        byte[] bArr = this.data;
        byte[] bArr2 = C0601ck.A00;
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
            bArr3[i8] = bArr2[bArr[i9] & UrlEncodingWriter.UTF16_REPLACEMENT_BYTE];
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

    public final String A07() {
        if (this instanceof C1108sv) {
            return new C0603cm(A0G()).A07();
        }
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

    public final String A08() {
        if (this instanceof C1108sv) {
            return new C0603cm(A0G()).A08();
        }
        String str = this.A01;
        if (str != null) {
            return str;
        }
        String str2 = new String(this.data, C0611cu.A00);
        this.A01 = str2;
        return str2;
    }

    public final C0603cm A09() {
        if (this instanceof C1108sv) {
            return new C0603cm(A0G()).A09();
        }
        try {
            return A03(MessageDigest.getInstance("SHA-1").digest(this.data));
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError(e);
        }
    }

    public final C0603cm A0A() {
        if (this instanceof C1108sv) {
            return new C0603cm(A0G()).A0A();
        }
        try {
            return A03(MessageDigest.getInstance("SHA-256").digest(this.data));
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError(e);
        }
    }

    public final C0603cm A0B() {
        if (this instanceof C1108sv) {
            return new C0603cm(A0G()).A0B();
        }
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
                return new C0603cm(bArr2);
            }
        }
    }

    public final void A0D(AnonymousClass33 r11) {
        if (!(this instanceof C1108sv)) {
            byte[] bArr = this.data;
            r11.A0F(bArr, 0, bArr.length);
            return;
        }
        C1108sv svVar = (C1108sv) this;
        byte[][] bArr2 = svVar.A01;
        int length = bArr2.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int[] iArr = svVar.A00;
            int i3 = iArr[length + i];
            int i4 = iArr[i];
            C0606cp cpVar = new C0606cp(bArr2[i], i3, (i3 + i4) - i2);
            C0606cp cpVar2 = r11.A01;
            if (cpVar2 == null) {
                cpVar.A03 = cpVar;
                cpVar.A02 = cpVar;
                r11.A01 = cpVar;
            } else {
                cpVar2.A03.A02(cpVar);
            }
            i++;
            i2 = i4;
        }
        r11.A00 += (long) i2;
    }

    public final boolean A0E(int i, C0603cm cmVar, int i2, int i3) {
        int i4;
        if (!(this instanceof C1108sv)) {
            return cmVar.A0F(i2, this.data, i, i3);
        }
        C1108sv svVar = (C1108sv) this;
        if (i < 0 || i > svVar.A05() - i3) {
            return false;
        }
        int A002 = C1108sv.A00(svVar, i);
        while (i3 > 0) {
            if (A002 == 0) {
                i4 = 0;
            } else {
                i4 = svVar.A00[A002 - 1];
            }
            int[] iArr = svVar.A00;
            int min = Math.min(i3, ((iArr[A002] - i4) + i4) - i);
            byte[][] bArr = svVar.A01;
            if (!cmVar.A0F(i2, bArr[A002], (i - i4) + iArr[bArr.length + A002], min)) {
                return false;
            }
            i += min;
            i2 += min;
            i3 -= min;
            A002++;
        }
        return true;
    }

    public final byte[] A0G() {
        if (!(this instanceof C1108sv)) {
            return (byte[]) this.data.clone();
        }
        C1108sv svVar = (C1108sv) this;
        int[] iArr = svVar.A00;
        byte[][] bArr = svVar.A01;
        int length = bArr.length;
        byte[] bArr2 = new byte[iArr[length - 1]];
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int i3 = iArr[length + i];
            int i4 = iArr[i];
            System.arraycopy(bArr[i], i3, bArr2, i2, i4 - i2);
            i++;
            i2 = i4;
        }
        return bArr2;
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        C0603cm cmVar = (C0603cm) obj;
        int A05 = A05();
        int A052 = cmVar.A05();
        int min = Math.min(A05, A052);
        for (int i = 0; i < min; i++) {
            int A04 = A04(i) & 255;
            int A042 = cmVar.A04(i) & 255;
            if (A04 != A042) {
                if (A04 < A042) {
                    return -1;
                } else {
                    return 1;
                }
            }
        }
        if (A05 == A052) {
            return 0;
        }
        if (A05 < A052) {
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
        String A08 = A08();
        int length = A08.length();
        int i = 0;
        int i2 = 0;
        while (true) {
            if (i < length) {
                if (i2 == 64) {
                    break;
                }
                int codePointAt = A08.codePointAt(i);
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
                str = A07();
            } else {
                sb = new StringBuilder();
                sb.append("[size=");
                sb.append(length2);
                sb.append(" hex=");
                str = A0C(0, 64).A07();
                sb.append(str);
                sb.append("…]");
                return sb.toString();
            }
        } else {
            str = A08.substring(0, i).replace("\\", "\\\\").replace("\n", LogCatCollector.COMPRESS_NEWLINE).replace("\r", "\\r");
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

    public C0603cm(byte[] bArr) {
        this.data = bArr;
    }

    private void readObject(ObjectInputStream objectInputStream) {
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
            C0603cm cmVar = new C0603cm(bArr);
            try {
                Field declaredField = C0603cm.class.getDeclaredField("data");
                declaredField.setAccessible(true);
                declaredField.set(this, cmVar.data);
            } catch (NoSuchFieldException unused) {
                throw new AssertionError();
            } catch (IllegalAccessException unused2) {
                throw new AssertionError();
            }
        } else {
            throw new IllegalArgumentException(AnonymousClass08.A00("byteCount < 0: ", readInt));
        }
    }
}
