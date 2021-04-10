package com.adobe.xmp.impl;

import com.adobe.xmp.XMPDateTime;
import com.adobe.xmp.XMPException;
import com.facebook.acra.NativeCrashDumpReporterUtil;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public final class ISO8601Converter {
    public static String render(XMPDateTime xMPDateTime) {
        StringBuffer stringBuffer = new StringBuffer();
        DecimalFormat decimalFormat = new DecimalFormat("0000", new DecimalFormatSymbols(Locale.ENGLISH));
        stringBuffer.append(decimalFormat.format((long) xMPDateTime.getYear()));
        if (xMPDateTime.getMonth() != 0) {
            decimalFormat.applyPattern("'-'00");
            stringBuffer.append(decimalFormat.format((long) xMPDateTime.getMonth()));
            if (xMPDateTime.getDay() != 0) {
                stringBuffer.append(decimalFormat.format((long) xMPDateTime.getDay()));
                if (!(xMPDateTime.getHour() == 0 && xMPDateTime.getMinute() == 0 && xMPDateTime.getSecond() == 0 && xMPDateTime.getNanoSecond() == 0 && (xMPDateTime.getTimeZone() == null || xMPDateTime.getTimeZone().getRawOffset() == 0))) {
                    stringBuffer.append('T');
                    decimalFormat.applyPattern("00");
                    stringBuffer.append(decimalFormat.format((long) xMPDateTime.getHour()));
                    stringBuffer.append(':');
                    stringBuffer.append(decimalFormat.format((long) xMPDateTime.getMinute()));
                    if (!(xMPDateTime.getSecond() == 0 && xMPDateTime.getNanoSecond() == 0)) {
                        decimalFormat.applyPattern(":00.#########");
                        stringBuffer.append(decimalFormat.format(((double) xMPDateTime.getSecond()) + (((double) xMPDateTime.getNanoSecond()) / 1.0E9d)));
                    }
                    if (xMPDateTime.getTimeZone() != null) {
                        int offset = xMPDateTime.getTimeZone().getOffset(xMPDateTime.getCalendar().getTimeInMillis());
                        if (offset == 0) {
                            stringBuffer.append('Z');
                        } else {
                            int i = offset / NativeCrashDumpReporterUtil.MIN_TIME_ELAPSED_SINCE_LAST_FATMINIDUMP_MS;
                            int abs = Math.abs((offset % NativeCrashDumpReporterUtil.MIN_TIME_ELAPSED_SINCE_LAST_FATMINIDUMP_MS) / NativeCrashDumpReporterUtil.MAX_TIME_DIFF_BETWEEN_COREDUMP_AND_MINIDUMP_MS);
                            decimalFormat.applyPattern("+00;-00");
                            stringBuffer.append(decimalFormat.format((long) i));
                            decimalFormat.applyPattern(":00");
                            stringBuffer.append(decimalFormat.format((long) abs));
                        }
                    }
                }
            }
        }
        return stringBuffer.toString();
    }

    public static XMPDateTime parse(String str) throws XMPException {
        XMPDateTimeImpl xMPDateTimeImpl = new XMPDateTimeImpl();
        parse(str, xMPDateTimeImpl);
        return xMPDateTimeImpl;
    }

    /* JADX WARNING: Removed duplicated region for block: B:103:0x01c5  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.adobe.xmp.XMPDateTime parse(java.lang.String r12, com.adobe.xmp.XMPDateTime r13) throws com.adobe.xmp.XMPException {
        /*
        // Method dump skipped, instructions count: 532
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adobe.xmp.impl.ISO8601Converter.parse(java.lang.String, com.adobe.xmp.XMPDateTime):com.adobe.xmp.XMPDateTime");
    }
}
