package X;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.Writer;

/* renamed from: X.0GL  reason: invalid class name */
public final class AnonymousClass0GL implements Closeable, Flushable {
    public static final String[] A09;
    public static final String[] A0A;
    public int A00 = 0;
    public String A01;
    public String A02;
    public boolean A03;
    public boolean A04;
    public boolean A05;
    public int[] A06 = new int[32];
    public String A07;
    public final Writer A08;

    public final void A08() throws IOException {
        A02(1, 2, "]");
    }

    public final void A09() throws IOException {
        A02(3, 5, "}");
    }

    static {
        String[] strArr = new String[128];
        A0A = strArr;
        int i = 0;
        do {
            strArr[i] = String.format("\\u%04x", Integer.valueOf(i));
            i++;
        } while (i <= 31);
        strArr[34] = "\\\"";
        strArr[92] = "\\\\";
        strArr[9] = "\\t";
        strArr[8] = "\\b";
        strArr[10] = "\\n";
        strArr[13] = "\\r";
        strArr[12] = "\\f";
        String[] strArr2 = (String[]) strArr.clone();
        A09 = strArr2;
        strArr2[60] = "\\u003c";
        strArr2[62] = "\\u003e";
        strArr2[38] = "\\u0026";
        strArr2[61] = "\\u003d";
        strArr2[39] = "\\u0027";
    }

    private void A00() throws IOException {
        if (this.A01 != null) {
            Writer writer = this.A08;
            writer.write("\n");
            int i = this.A00;
            for (int i2 = 1; i2 < i; i2++) {
                writer.write(this.A01);
            }
        }
    }

    private void A01(int i) {
        int i2 = this.A00;
        int[] iArr = this.A06;
        if (i2 == iArr.length) {
            int[] iArr2 = new int[(i2 << 1)];
            System.arraycopy(iArr, 0, iArr2, 0, i2);
            this.A06 = iArr2;
            iArr = iArr2;
        }
        int i3 = this.A00;
        this.A00 = i3 + 1;
        iArr[i3] = i;
    }

    private void A02(int i, int i2, String str) throws IOException {
        String str2;
        int i3 = this.A00;
        if (i3 != 0) {
            int i4 = i3 - 1;
            int i5 = this.A06[i4];
            if (i5 == i2 || i5 == i) {
                String str3 = this.A07;
                if (str3 == null) {
                    this.A00 = i4;
                    if (i5 == i2) {
                        A00();
                    }
                    this.A08.write(str);
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

    public static void A03(AnonymousClass0GL r5) throws IOException {
        String str;
        int i = r5.A00;
        if (i != 0) {
            int[] iArr = r5.A06;
            int i2 = i - 1;
            int i3 = iArr[i2];
            if (i3 == 1) {
                iArr[i2] = 2;
            } else if (i3 == 2) {
                r5.A08.append(',');
            } else if (i3 != 4) {
                if (i3 != 6) {
                    if (i3 != 7) {
                        str = "Nesting problem.";
                    } else if (!r5.A04) {
                        str = "JSON must have only one top-level value.";
                    }
                }
                iArr[i2] = 7;
                return;
            } else {
                r5.A08.append((CharSequence) r5.A02);
                r5.A06[r5.A00 - 1] = 5;
                return;
            }
            r5.A00();
            return;
        }
        str = "JsonWriter is closed.";
        throw new IllegalStateException(str);
    }

    public static void A04(AnonymousClass0GL r3) throws IOException {
        String str;
        if (r3.A07 != null) {
            int i = r3.A00;
            if (i != 0) {
                int i2 = r3.A06[i - 1];
                if (i2 == 5) {
                    r3.A08.write(44);
                } else if (i2 != 3) {
                    str = "Nesting problem.";
                }
                r3.A00();
                r3.A06[r3.A00 - 1] = 4;
                r3.A05(r3.A07);
                r3.A07 = null;
                return;
            }
            str = "JsonWriter is closed.";
            throw new IllegalStateException(str);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x002c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void A05(java.lang.String r9) throws java.io.IOException {
        /*
            r8 = this;
            boolean r0 = r8.A03
            if (r0 == 0) goto L_0x003e
            java.lang.String[] r7 = X.AnonymousClass0GL.A09
        L_0x0006:
            java.io.Writer r6 = r8.A08
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
            java.lang.String[] r7 = X.AnonymousClass0GL.A0A
            goto L_0x0006
        L_0x0041:
            if (r2 >= r4) goto L_0x0047
            int r4 = r4 - r2
            r6.write(r9, r2, r4)
        L_0x0047:
            r6.write(r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0GL.A05(java.lang.String):void");
    }

    public final void A0A() throws IOException {
        if (this.A07 != null) {
            if (this.A05) {
                A04(this);
            } else {
                this.A07 = null;
                return;
            }
        }
        A03(this);
        this.A08.write("null");
    }

    public final void A0C(Number number) throws IOException {
        if (number == null) {
            A0A();
            return;
        }
        A04(this);
        String obj = number.toString();
        if (this.A04 || (!obj.equals("-Infinity") && !obj.equals("Infinity") && !obj.equals("NaN"))) {
            A03(this);
            this.A08.append((CharSequence) obj);
            return;
        }
        throw new IllegalArgumentException("Numeric values must be finite, but was " + number);
    }

    public final void A0D(String str) throws IOException {
        if (str == null) {
            throw new NullPointerException("name == null");
        } else if (this.A07 != null) {
            throw new IllegalStateException();
        } else if (this.A00 != 0) {
            this.A07 = str;
        } else {
            throw new IllegalStateException("JsonWriter is closed.");
        }
    }

    public final void A0E(String str) throws IOException {
        if (str == null) {
            A0A();
            return;
        }
        A04(this);
        A03(this);
        A05(str);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        this.A08.close();
        int i = this.A00;
        if (i > 1 || (i == 1 && this.A06[0] != 7)) {
            throw new IOException("Incomplete document");
        }
        this.A00 = 0;
    }

    @Override // java.io.Flushable
    public final void flush() throws IOException {
        if (this.A00 != 0) {
            this.A08.flush();
            return;
        }
        throw new IllegalStateException("JsonWriter is closed.");
    }

    public AnonymousClass0GL(Writer writer) {
        A01(6);
        this.A02 = ":";
        this.A05 = true;
        if (writer != null) {
            this.A08 = writer;
            return;
        }
        throw new NullPointerException("out == null");
    }

    public final void A06() throws IOException {
        A04(this);
        A03(this);
        A01(1);
        this.A08.write("[");
    }

    public final void A07() throws IOException {
        A04(this);
        A03(this);
        A01(3);
        this.A08.write("{");
    }

    public final void A0B(long j) throws IOException {
        A04(this);
        A03(this);
        this.A08.write(Long.toString(j));
    }
}
