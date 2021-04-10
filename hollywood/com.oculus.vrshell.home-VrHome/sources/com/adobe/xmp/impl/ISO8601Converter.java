package com.adobe.xmp.impl;

import com.adobe.xmp.XMPDateTime;
import com.adobe.xmp.XMPException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public final class ISO8601Converter {
    private ISO8601Converter() {
    }

    public static XMPDateTime parse(String iso8601String) throws XMPException {
        return parse(iso8601String, new XMPDateTimeImpl());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00c9, code lost:
        if (r1.hasNext() != false) goto L_0x00cb;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.adobe.xmp.XMPDateTime parse(java.lang.String r11, com.adobe.xmp.XMPDateTime r12) throws com.adobe.xmp.XMPException {
        /*
        // Method dump skipped, instructions count: 600
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adobe.xmp.impl.ISO8601Converter.parse(java.lang.String, com.adobe.xmp.XMPDateTime):com.adobe.xmp.XMPDateTime");
    }

    public static String render(XMPDateTime dateTime) {
        StringBuffer buffer = new StringBuffer();
        DecimalFormat df = new DecimalFormat("0000", new DecimalFormatSymbols(Locale.ENGLISH));
        buffer.append(df.format((long) dateTime.getYear()));
        if (dateTime.getMonth() == 0) {
            return buffer.toString();
        }
        df.applyPattern("'-'00");
        buffer.append(df.format((long) dateTime.getMonth()));
        if (dateTime.getDay() == 0) {
            return buffer.toString();
        }
        buffer.append(df.format((long) dateTime.getDay()));
        if (!(dateTime.getHour() == 0 && dateTime.getMinute() == 0 && dateTime.getSecond() == 0 && dateTime.getNanoSecond() == 0 && (dateTime.getTimeZone() == null || dateTime.getTimeZone().getRawOffset() == 0))) {
            buffer.append('T');
            df.applyPattern("00");
            buffer.append(df.format((long) dateTime.getHour()));
            buffer.append(':');
            buffer.append(df.format((long) dateTime.getMinute()));
            if (!(dateTime.getSecond() == 0 && dateTime.getNanoSecond() == 0)) {
                df.applyPattern(":00.#########");
                buffer.append(df.format(((double) dateTime.getSecond()) + (((double) dateTime.getNanoSecond()) / 1.0E9d)));
            }
            if (dateTime.getTimeZone() != null) {
                int offset = dateTime.getTimeZone().getOffset(dateTime.getCalendar().getTimeInMillis());
                if (offset == 0) {
                    buffer.append('Z');
                } else {
                    int tminutes = Math.abs((offset % 3600000) / 60000);
                    df.applyPattern("+00;-00");
                    buffer.append(df.format((long) (offset / 3600000)));
                    df.applyPattern(":00");
                    buffer.append(df.format((long) tminutes));
                }
            }
        }
        return buffer.toString();
    }
}
