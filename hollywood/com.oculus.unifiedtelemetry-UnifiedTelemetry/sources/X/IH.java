package X;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public final class IH extends DateFormat {
    public static final IH A05 = new IH();
    public static final DateFormat A06;
    public static final DateFormat A07;
    public static final DateFormat A08;
    public static final DateFormat A09;
    public static final TimeZone A0A = TimeZone.getTimeZone("GMT");
    public static final String[] A0B = {"yyyy-MM-dd'T'HH:mm:ss.SSSZ", "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", "EEE, dd MMM yyyy HH:mm:ss zzz", "yyyy-MM-dd"};
    public transient DateFormat A00;
    public transient DateFormat A01;
    public transient DateFormat A02;
    public transient DateFormat A03;
    public transient TimeZone A04;

    static {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.US);
        A09 = simpleDateFormat;
        TimeZone timeZone = A0A;
        simpleDateFormat.setTimeZone(timeZone);
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        A06 = simpleDateFormat2;
        simpleDateFormat2.setTimeZone(timeZone);
        SimpleDateFormat simpleDateFormat3 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        A07 = simpleDateFormat3;
        simpleDateFormat3.setTimeZone(timeZone);
        SimpleDateFormat simpleDateFormat4 = new SimpleDateFormat("yyyy-MM-dd");
        A08 = simpleDateFormat4;
        simpleDateFormat4.setTimeZone(timeZone);
    }

    private final DateFormat A00(DateFormat dateFormat) {
        TimeZone timeZone = this.A04;
        DateFormat dateFormat2 = (DateFormat) dateFormat.clone();
        if (timeZone != null) {
            dateFormat2.setTimeZone(timeZone);
        }
        return dateFormat2;
    }

    @Override // java.lang.Object
    public final Object clone() {
        return new IH();
    }

    @Override // java.text.DateFormat
    public final StringBuffer format(Date date, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        DateFormat dateFormat = this.A00;
        if (dateFormat == null) {
            dateFormat = A00(A06);
            this.A00 = dateFormat;
        }
        return dateFormat.format(date, stringBuffer, fieldPosition);
    }

    public final void setTimeZone(TimeZone timeZone) {
        if (timeZone != this.A04) {
            this.A03 = null;
            this.A00 = null;
            this.A01 = null;
            this.A02 = null;
            this.A04 = timeZone;
        }
    }

    public IH() {
    }

    public IH(TimeZone timeZone) {
        this.A04 = timeZone;
    }

    @Override // java.text.DateFormat
    public final Date parse(String str) throws ParseException {
        String trim = str.trim();
        ParsePosition parsePosition = new ParsePosition(0);
        Date parse = parse(trim, parsePosition);
        if (parse != null) {
            return parse;
        }
        StringBuilder sb = new StringBuilder();
        String[] strArr = A0B;
        for (String str2 : strArr) {
            if (sb.length() > 0) {
                sb.append("\", \"");
            } else {
                sb.append('\"');
            }
            sb.append(str2);
        }
        sb.append('\"');
        throw new ParseException(String.format("Can not parse date \"%s\": not compatible with any of standard forms (%s)", trim, sb.toString()), parsePosition.getErrorIndex());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:75:0x014a, code lost:
        if (r1 < 0) goto L_0x014c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.Date parse(java.lang.String r9, java.text.ParsePosition r10) {
        /*
        // Method dump skipped, instructions count: 345
        */
        throw new UnsupportedOperationException("Method not decompiled: X.IH.parse(java.lang.String, java.text.ParsePosition):java.util.Date");
    }
}
