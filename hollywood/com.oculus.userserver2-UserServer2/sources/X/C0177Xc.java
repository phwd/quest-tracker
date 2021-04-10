package X;

import java.util.Date;
import java.util.regex.Pattern;

/* renamed from: X.Xc  reason: case insensitive filesystem */
public final class C0177Xc {
    public static final Pattern A09 = Pattern.compile("(\\d{1,2})[^\\d]*");
    public static final Pattern A0A = Pattern.compile("(?i)(jan|feb|mar|apr|may|jun|jul|aug|sep|oct|nov|dec).*");
    public static final Pattern A0B = Pattern.compile("(\\d{1,2}):(\\d{1,2}):(\\d{1,2})[^\\d]*");
    public static final Pattern A0C = Pattern.compile("(\\d{2,4})[^\\d]*");
    public final long A00;
    public final String A01;
    public final String A02;
    public final String A03;
    public final String A04;
    public final boolean A05;
    public final boolean A06;
    public final boolean A07;
    public final boolean A08;

    public static int A00(String str, int i, int i2, boolean z) {
        boolean z2;
        while (i < i2) {
            char charAt = str.charAt(i);
            if (charAt >= ' ' ? charAt >= 127 || ((charAt >= '0' && charAt <= '9') || ((charAt >= 'a' && charAt <= 'z') || ((charAt >= 'A' && charAt <= 'Z') || charAt == ':'))) : charAt != '\t') {
                z2 = true;
            } else {
                z2 = false;
            }
            if (z2 == (!z)) {
                return i;
            }
            i++;
        }
        return i2;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof C0177Xc)) {
            return false;
        }
        C0177Xc xc = (C0177Xc) obj;
        if (xc.A02.equals(this.A02) && xc.A04.equals(this.A04) && xc.A01.equals(this.A01) && xc.A03.equals(this.A03) && xc.A00 == this.A00 && xc.A08 == this.A08 && xc.A06 == this.A06 && xc.A07 == this.A07 && xc.A05 == this.A05) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        long j = this.A00;
        return ((((((((((((((((527 + this.A02.hashCode()) * 31) + this.A04.hashCode()) * 31) + this.A01.hashCode()) * 31) + this.A03.hashCode()) * 31) + ((int) (j ^ (j >>> 32)))) * 31) + (!this.A08 ? 1 : 0)) * 31) + (!this.A06 ? 1 : 0)) * 31) + (!this.A07 ? 1 : 0)) * 31) + (!this.A05 ? 1 : 0);
    }

    public final String toString() {
        String format;
        StringBuilder sb = new StringBuilder();
        sb.append(this.A02);
        sb.append('=');
        sb.append(this.A04);
        if (this.A07) {
            long j = this.A00;
            if (j == Long.MIN_VALUE) {
                format = "; max-age=0";
            } else {
                sb.append("; expires=");
                format = C0172Wx.A00.get().format(new Date(j));
            }
            sb.append(format);
        }
        if (!this.A05) {
            sb.append("; domain=");
            sb.append(this.A01);
        }
        sb.append("; path=");
        sb.append(this.A03);
        if (this.A08) {
            sb.append("; secure");
        }
        if (this.A06) {
            sb.append("; httponly");
        }
        return sb.toString();
    }

    public C0177Xc(String str, String str2, long j, String str3, String str4, boolean z, boolean z2, boolean z3, boolean z4) {
        this.A02 = str;
        this.A04 = str2;
        this.A00 = j;
        this.A01 = str3;
        this.A03 = str4;
        this.A08 = z;
        this.A06 = z2;
        this.A05 = z3;
        this.A07 = z4;
    }
}
