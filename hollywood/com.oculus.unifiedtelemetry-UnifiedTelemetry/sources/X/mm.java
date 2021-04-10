package X;

import com.facebook.acra.LogCatCollector;
import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.Writer;

public class mm implements Closeable, Flushable {
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

    public mm A09() throws IOException {
        A05(1, 2, "]");
        return this;
    }

    public mm A0A() throws IOException {
        A05(3, 5, "}");
        return this;
    }

    static {
        String[] strArr = new String[128];
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

    private void A02() throws IOException {
        String str;
        int i = this.A00;
        if (i != 0) {
            int[] iArr = this.A05;
            int i2 = i - 1;
            int i3 = iArr[i2];
            int i4 = 2;
            if (i3 != 1) {
                if (i3 == 2) {
                    this.A07.append(',');
                    return;
                } else if (i3 != 4) {
                    i4 = 7;
                    if (i3 != 6) {
                        if (i3 != 7) {
                            str = "Nesting problem.";
                        } else if (!this.A03) {
                            str = "JSON must have only one top-level value.";
                        }
                    }
                } else {
                    this.A07.append((CharSequence) this.A01);
                    this.A05[this.A00 - 1] = 5;
                    return;
                }
            }
            iArr[i2] = i4;
            return;
        }
        str = "JsonWriter is closed.";
        throw new IllegalStateException(str);
    }

    private void A03() throws IOException {
        String str;
        if (this.A06 != null) {
            int i = this.A00;
            if (i != 0) {
                int i2 = this.A05[i - 1];
                if (i2 == 5) {
                    this.A07.write(44);
                } else if (i2 != 3) {
                    str = "Nesting problem.";
                }
                this.A05[this.A00 - 1] = 4;
                A06(this.A06);
                this.A06 = null;
                return;
            }
            str = "JsonWriter is closed.";
            throw new IllegalStateException(str);
        }
    }

    private void A04(int i) {
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

    private void A05(int i, int i2, String str) throws IOException {
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
                str2 = AnonymousClass06.A04("Dangling name: ", str3);
            } else {
                str2 = "Nesting problem.";
            }
        } else {
            str2 = "JsonWriter is closed.";
        }
        throw new IllegalStateException(str2);
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x002c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void A06(java.lang.String r9) throws java.io.IOException {
        /*
            r8 = this;
            boolean r0 = r8.A02
            if (r0 == 0) goto L_0x003e
            java.lang.String[] r7 = X.mm.A08
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
            java.lang.String[] r7 = X.mm.A09
            goto L_0x0006
        L_0x0041:
            if (r2 >= r4) goto L_0x0047
            int r4 = r4 - r2
            r6.write(r9, r2, r4)
        L_0x0047:
            r6.write(r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: X.mm.A06(java.lang.String):void");
    }

    public mm A0B() throws IOException {
        if (this.A06 != null) {
            if (this.A04) {
                A03();
            } else {
                this.A06 = null;
                return this;
            }
        }
        A02();
        this.A07.write("null");
        return this;
    }

    public mm A0D(Boolean bool) throws IOException {
        String str;
        if (bool == null) {
            A0B();
            return this;
        }
        A03();
        A02();
        Writer writer = this.A07;
        if (bool.booleanValue()) {
            str = "true";
        } else {
            str = "false";
        }
        writer.write(str);
        return this;
    }

    public mm A0E(Number number) throws IOException {
        if (number == null) {
            A0B();
            return this;
        }
        A03();
        String obj = number.toString();
        if (this.A03 || (!obj.equals("-Infinity") && !obj.equals("Infinity") && !obj.equals("NaN"))) {
            A02();
            this.A07.append((CharSequence) obj);
            return this;
        }
        StringBuilder sb = new StringBuilder("Numeric values must be finite, but was ");
        sb.append(number);
        throw new IllegalArgumentException(sb.toString());
    }

    public mm A0F(String str) throws IOException {
        if (str == null) {
            throw new NullPointerException("name == null");
        } else if (this.A06 != null) {
            throw new IllegalStateException();
        } else if (this.A00 != 0) {
            this.A06 = str;
            return this;
        } else {
            throw new IllegalStateException("JsonWriter is closed.");
        }
    }

    public mm A0G(String str) throws IOException {
        if (str == null) {
            A0B();
            return this;
        }
        A03();
        A02();
        A06(str);
        return this;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.A07.close();
        int i = this.A00;
        if (i > 1 || (i == 1 && this.A05[0] != 7)) {
            throw new IOException("Incomplete document");
        }
        this.A00 = 0;
    }

    @Override // java.io.Flushable
    public void flush() throws IOException {
        if (this.A00 != 0) {
            this.A07.flush();
            return;
        }
        throw new IllegalStateException("JsonWriter is closed.");
    }

    public mm(Writer writer) {
        A04(6);
        this.A01 = ":";
        this.A04 = true;
        if (writer != null) {
            this.A07 = writer;
            return;
        }
        throw new NullPointerException("out == null");
    }

    public mm A07() throws IOException {
        A03();
        A02();
        A04(1);
        this.A07.write("[");
        return this;
    }

    public mm A08() throws IOException {
        A03();
        A02();
        A04(3);
        this.A07.write("{");
        return this;
    }

    public mm A0C(long j) throws IOException {
        A03();
        A02();
        this.A07.write(Long.toString(j));
        return this;
    }

    public mm A0H(boolean z) throws IOException {
        String str;
        A03();
        A02();
        Writer writer = this.A07;
        if (z) {
            str = "true";
        } else {
            str = "false";
        }
        writer.write(str);
        return this;
    }
}
