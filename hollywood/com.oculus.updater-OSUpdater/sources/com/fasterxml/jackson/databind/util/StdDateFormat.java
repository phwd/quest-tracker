package com.fasterxml.jackson.databind.util;

import com.fasterxml.jackson.core.io.NumberInput;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class StdDateFormat extends DateFormat {
    protected static final String[] ALL_FORMATS = {"yyyy-MM-dd'T'HH:mm:ss.SSSZ", "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", "EEE, dd MMM yyyy HH:mm:ss zzz", "yyyy-MM-dd"};
    protected static final DateFormat DATE_FORMAT_ISO8601 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
    protected static final DateFormat DATE_FORMAT_ISO8601_Z = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    protected static final DateFormat DATE_FORMAT_PLAIN = new SimpleDateFormat("yyyy-MM-dd");
    protected static final DateFormat DATE_FORMAT_RFC1123 = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.US);
    private static final TimeZone DEFAULT_TIMEZONE = TimeZone.getTimeZone("GMT");
    public static final StdDateFormat instance = new StdDateFormat();
    protected transient DateFormat _formatISO8601;
    protected transient DateFormat _formatISO8601_z;
    protected transient DateFormat _formatPlain;
    protected transient DateFormat _formatRFC1123;
    protected transient TimeZone _timezone;

    static {
        DATE_FORMAT_RFC1123.setTimeZone(DEFAULT_TIMEZONE);
        DATE_FORMAT_ISO8601.setTimeZone(DEFAULT_TIMEZONE);
        DATE_FORMAT_ISO8601_Z.setTimeZone(DEFAULT_TIMEZONE);
        DATE_FORMAT_PLAIN.setTimeZone(DEFAULT_TIMEZONE);
    }

    public StdDateFormat() {
    }

    public StdDateFormat(TimeZone timeZone) {
        this._timezone = timeZone;
    }

    public StdDateFormat withTimeZone(TimeZone timeZone) {
        if (timeZone == null) {
            timeZone = DEFAULT_TIMEZONE;
        }
        return new StdDateFormat(timeZone);
    }

    @Override // java.lang.Object
    public StdDateFormat clone() {
        return new StdDateFormat();
    }

    public void setTimeZone(TimeZone timeZone) {
        if (timeZone != this._timezone) {
            this._formatRFC1123 = null;
            this._formatISO8601 = null;
            this._formatISO8601_z = null;
            this._formatPlain = null;
            this._timezone = timeZone;
        }
    }

    @Override // java.text.DateFormat
    public Date parse(String str) throws ParseException {
        String trim = str.trim();
        ParsePosition parsePosition = new ParsePosition(0);
        Date parse = parse(trim, parsePosition);
        if (parse != null) {
            return parse;
        }
        StringBuilder sb = new StringBuilder();
        String[] strArr = ALL_FORMATS;
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

    public Date parse(String str, ParsePosition parsePosition) {
        char charAt;
        if (looksLikeISO8601(str)) {
            return parseAsISO8601(str, parsePosition);
        }
        int length = str.length();
        while (true) {
            length--;
            if (length < 0 || (((charAt = str.charAt(length)) < '0' || charAt > '9') && (length > 0 || charAt != '-'))) {
                if (length < 0 || !NumberInput.inLongRange(str, false)) {
                    return parseAsRFC1123(str, parsePosition);
                }
                return new Date(Long.parseLong(str));
            }
        }
        if (length < 0) {
        }
        return parseAsRFC1123(str, parsePosition);
    }

    @Override // java.text.DateFormat
    public StringBuffer format(Date date, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        if (this._formatISO8601 == null) {
            this._formatISO8601 = _cloneFormat(DATE_FORMAT_ISO8601);
        }
        return this._formatISO8601.format(date, stringBuffer, fieldPosition);
    }

    /* access modifiers changed from: protected */
    public boolean looksLikeISO8601(String str) {
        if (str.length() < 5 || !Character.isDigit(str.charAt(0)) || !Character.isDigit(str.charAt(3)) || str.charAt(4) != '-') {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public Date parseAsISO8601(String str, ParsePosition parsePosition) {
        DateFormat dateFormat;
        int length = str.length();
        int i = length - 1;
        char charAt = str.charAt(i);
        if (length <= 10 && Character.isDigit(charAt)) {
            dateFormat = this._formatPlain;
            if (dateFormat == null) {
                dateFormat = _cloneFormat(DATE_FORMAT_PLAIN);
                this._formatPlain = dateFormat;
            }
        } else if (charAt == 'Z') {
            DateFormat dateFormat2 = this._formatISO8601_z;
            if (dateFormat2 == null) {
                dateFormat2 = _cloneFormat(DATE_FORMAT_ISO8601_Z);
                this._formatISO8601_z = dateFormat2;
            }
            if (str.charAt(length - 4) == ':') {
                StringBuilder sb = new StringBuilder(str);
                sb.insert(i, ".000");
                str = sb.toString();
            }
            dateFormat = dateFormat2;
        } else if (hasTimeZone(str)) {
            int i2 = length - 3;
            char charAt2 = str.charAt(i2);
            if (charAt2 == ':') {
                StringBuilder sb2 = new StringBuilder(str);
                sb2.delete(i2, length - 2);
                str = sb2.toString();
            } else if (charAt2 == '+' || charAt2 == '-') {
                str = str + "00";
            }
            int length2 = str.length();
            if (Character.isDigit(str.charAt(length2 - 9))) {
                StringBuilder sb3 = new StringBuilder(str);
                sb3.insert(length2 - 5, ".000");
                str = sb3.toString();
            }
            dateFormat = this._formatISO8601;
            if (dateFormat == null) {
                dateFormat = _cloneFormat(DATE_FORMAT_ISO8601);
                this._formatISO8601 = dateFormat;
            }
        } else {
            StringBuilder sb4 = new StringBuilder(str);
            if ((length - str.lastIndexOf(84)) - 1 <= 8) {
                sb4.append(".000");
            }
            sb4.append('Z');
            str = sb4.toString();
            dateFormat = this._formatISO8601_z;
            if (dateFormat == null) {
                dateFormat = _cloneFormat(DATE_FORMAT_ISO8601_Z);
                this._formatISO8601_z = dateFormat;
            }
        }
        return dateFormat.parse(str, parsePosition);
    }

    /* access modifiers changed from: protected */
    public Date parseAsRFC1123(String str, ParsePosition parsePosition) {
        if (this._formatRFC1123 == null) {
            this._formatRFC1123 = _cloneFormat(DATE_FORMAT_RFC1123);
        }
        return this._formatRFC1123.parse(str, parsePosition);
    }

    private static final boolean hasTimeZone(String str) {
        char charAt;
        char charAt2;
        int length = str.length();
        if (length < 6) {
            return false;
        }
        char charAt3 = str.charAt(length - 6);
        if (charAt3 == '+' || charAt3 == '-' || (charAt = str.charAt(length - 5)) == '+' || charAt == '-' || (charAt2 = str.charAt(length - 3)) == '+' || charAt2 == '-') {
            return true;
        }
        return false;
    }

    private final DateFormat _cloneFormat(DateFormat dateFormat) {
        return _cloneFormat(dateFormat, this._timezone);
    }

    private static final DateFormat _cloneFormat(DateFormat dateFormat, TimeZone timeZone) {
        DateFormat dateFormat2 = (DateFormat) dateFormat.clone();
        if (timeZone != null) {
            dateFormat2.setTimeZone(timeZone);
        }
        return dateFormat2;
    }
}
