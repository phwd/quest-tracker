package X;

import com.facebook.FacebookSdk;
import com.facebook.acra.LogCatCollector;
import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.Writer;

/* renamed from: X.0zU  reason: invalid class name and case insensitive filesystem */
public final class C09130zU implements Closeable, Flushable {
    public static final String[] A08;
    public static final String[] A09;
    public int A00 = 0;
    public String A01;
    public boolean A02;
    public boolean A03;
    public boolean A04;
    public int[] A05 = new int[32];
    public String A06;
    public final Writer A07;

    public final void A07() throws IOException {
        A01(1, 2, "]");
    }

    public final void A08() throws IOException {
        A01(3, 5, "}");
    }

    static {
        String[] strArr = new String[FacebookSdk.DEFAULT_MAXIMUM_POOL_SIZE];
        A09 = strArr;
        int i = 0;
        do {
            strArr[i] = String.format("\\u%04x", Integer.valueOf(i));
            i++;
        } while (i <= 31);
        strArr[34] = "\\\"";
        strArr[92] = "\\\\";
        strArr[9] = "\\t";
        strArr[8] = "\\b";
        strArr[10] = LogCatCollector.COMPRESS_NEWLINE;
        strArr[13] = "\\r";
        strArr[12] = "\\f";
        String[] strArr2 = (String[]) strArr.clone();
        A08 = strArr2;
        strArr2[60] = "\\u003c";
        strArr2[62] = "\\u003e";
        strArr2[38] = "\\u0026";
        strArr2[61] = "\\u003d";
        strArr2[39] = "\\u0027";
    }

    private void A00(int i) {
        int i2 = this.A00;
        int[] iArr = this.A05;
        if (i2 == iArr.length) {
            int[] iArr2 = new int[(i2 << 1)];
            System.arraycopy(iArr, 0, iArr2, 0, i2);
            this.A05 = iArr2;
            iArr = iArr2;
        }
        int i3 = this.A00;
        this.A00 = i3 + 1;
        iArr[i3] = i;
    }

    private void A01(int i, int i2, String str) throws IOException {
        String str2;
        int i3 = this.A00;
        if (i3 != 0) {
            int i4 = i3 - 1;
            int i5 = this.A05[i4];
            if (i5 == i2 || i5 == i) {
                String str3 = this.A06;
                if (str3 == null) {
                    this.A00 = i4;
                    this.A07.write(str);
                    return;
                }
                str2 = AnonymousClass006.A05("Dangling name: ", str3);
            } else {
                str2 = "Nesting problem.";
            }
        } else {
            str2 = "JsonWriter is closed.";
        }
        throw new IllegalStateException(str2);
    }

    public static void A02(C09130zU r5) throws IOException {
        String str;
        int i = r5.A00;
        if (i != 0) {
            int[] iArr = r5.A05;
            int i2 = i - 1;
            int i3 = iArr[i2];
            int i4 = 2;
            if (i3 != 1) {
                if (i3 == 2) {
                    r5.A07.append(',');
                    return;
                } else if (i3 != 4) {
                    i4 = 7;
                    if (i3 != 6) {
                        if (i3 != 7) {
                            str = "Nesting problem.";
                        } else if (!r5.A03) {
                            str = "JSON must have only one top-level value.";
                        }
                    }
                } else {
                    r5.A07.append((CharSequence) r5.A01);
                    r5.A05[r5.A00 - 1] = 5;
                    return;
                }
            }
            iArr[i2] = i4;
            return;
        }
        str = "JsonWriter is closed.";
        throw new IllegalStateException(str);
    }

    public static void A03(C09130zU r3) throws IOException {
        String str;
        if (r3.A06 != null) {
            int i = r3.A00;
            if (i != 0) {
                int i2 = r3.A05[i - 1];
                if (i2 == 5) {
                    r3.A07.write(44);
                } else if (i2 != 3) {
                    str = "Nesting problem.";
                }
                r3.A05[r3.A00 - 1] = 4;
                r3.A04(r3.A06);
                r3.A06 = null;
                return;
            }
            str = "JsonWriter is closed.";
            throw new IllegalStateException(str);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x002c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void A04(java.lang.String r9) throws java.io.IOException {
        /*
            r8 = this;
            boolean r0 = r8.A02
            if (r0 == 0) goto L_0x003e
            java.lang.String[] r7 = X.C09130zU.A08
        L_0x0006:
            java.io.Writer r6 = r8.A07
            java.lang.String r5 = "\""
            r6.write(r5)
            int r4 = r9.length()
            r3 = 0
            r2 = 0
        L_0x0013:
            if (r3 >= r4) goto L_0x0041
            char r1 = r9.charAt(r3)
            r0 = 128(0x80, float:1.794E-43)
            if (r1 >= r0) goto L_0x0024
            r1 = r7[r1]
            if (r1 != 0) goto L_0x002a
        L_0x0021:
            int r3 = r3 + 1
            goto L_0x0013
        L_0x0024:
            r0 = 8232(0x2028, float:1.1535E-41)
            if (r1 != r0) goto L_0x0037
            java.lang.String r1 = "\\u2028"
        L_0x002a:
            if (r2 >= r3) goto L_0x0031
            int r0 = r3 - r2
            r6.write(r9, r2, r0)
        L_0x0031:
            r6.write(r1)
            int r2 = r3 + 1
            goto L_0x0021
        L_0x0037:
            r0 = 8233(0x2029, float:1.1537E-41)
            if (r1 != r0) goto L_0x0021
            java.lang.String r1 = "\\u2029"
            goto L_0x002a
        L_0x003e:
            java.lang.String[] r7 = X.C09130zU.A09
            goto L_0x0006
        L_0x0041:
            if (r2 >= r4) goto L_0x0047
            int r4 = r4 - r2
            r6.write(r9, r2, r4)
        L_0x0047:
            r6.write(r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C09130zU.A04(java.lang.String):void");
    }

    public final void A09() throws IOException {
        if (this.A06 != null) {
            if (this.A04) {
                A03(this);
            } else {
                this.A06 = null;
                return;
            }
        }
        A02(this);
        this.A07.write("null");
    }

    public final void A0B(Number number) throws IOException {
        if (number == null) {
            A09();
            return;
        }
        A03(this);
        String obj = number.toString();
        if (this.A03 || (!obj.equals("-Infinity") && !obj.equals("Infinity") && !obj.equals("NaN"))) {
            A02(this);
            this.A07.append((CharSequence) obj);
            return;
        }
        StringBuilder sb = new StringBuilder("Numeric values must be finite, but was ");
        sb.append(number);
        throw new IllegalArgumentException(sb.toString());
    }

    public final void A0C(String str) throws IOException {
        if (str == null) {
            throw new NullPointerException("name == null");
        } else if (this.A06 != null) {
            throw new IllegalStateException();
        } else if (this.A00 != 0) {
            this.A06 = str;
        } else {
            throw new IllegalStateException("JsonWriter is closed.");
        }
    }

    public final void A0D(String str) throws IOException {
        if (str == null) {
            A09();
            return;
        }
        A03(this);
        A02(this);
        A04(str);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        this.A07.close();
        int i = this.A00;
        if (i > 1 || (i == 1 && this.A05[0] != 7)) {
            throw new IOException("Incomplete document");
        }
        this.A00 = 0;
    }

    @Override // java.io.Flushable
    public final void flush() throws IOException {
        if (this.A00 != 0) {
            this.A07.flush();
            return;
        }
        throw new IllegalStateException("JsonWriter is closed.");
    }

    public C09130zU(Writer writer) {
        A00(6);
        this.A01 = ":";
        this.A04 = true;
        if (writer != null) {
            this.A07 = writer;
            return;
        }
        throw new NullPointerException("out == null");
    }

    public final void A05() throws IOException {
        A03(this);
        A02(this);
        A00(1);
        this.A07.write("[");
    }

    public final void A06() throws IOException {
        A03(this);
        A02(this);
        A00(3);
        this.A07.write("{");
    }

    public final void A0A(long j) throws IOException {
        A03(this);
        A02(this);
        this.A07.write(Long.toString(j));
    }
}
