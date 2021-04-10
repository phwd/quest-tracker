package java.util;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.time.temporal.TemporalAccessor;
import java.util.Locale;
import libcore.icu.LocaleData;
import sun.misc.FormattedFloatingDecimal;

public final class Formatter implements Closeable, Flushable {
    private static double scaleUp;
    private Appendable a;
    private final Locale l;
    private IOException lastException;
    private final char zero;

    public enum BigDecimalLayoutForm {
        SCIENTIFIC,
        DECIMAL_FLOAT
    }

    /* access modifiers changed from: private */
    public static class DateTime {
        static boolean isValid(char c) {
            if (c == 'F' || c == 'h' || c == 'p' || c == 'H' || c == 'I' || c == 'Y' || c == 'Z' || c == 'r' || c == 's' || c == 'y' || c == 'z') {
                return true;
            }
            switch (c) {
                case 'A':
                case 'B':
                case 'C':
                case 'D':
                    return true;
                default:
                    switch (c) {
                        case 'L':
                        case 'M':
                        case 'N':
                            return true;
                        default:
                            switch (c) {
                                case 'Q':
                                case 'R':
                                case 'S':
                                case 'T':
                                    return true;
                                default:
                                    switch (c) {
                                        case 'a':
                                        case 'b':
                                        case 'c':
                                        case 'd':
                                        case 'e':
                                            return true;
                                        default:
                                            switch (c) {
                                                case 'j':
                                                case 'k':
                                                case 'l':
                                                case 'm':
                                                    return true;
                                                default:
                                                    return false;
                                            }
                                    }
                            }
                    }
            }
        }
    }

    /* access modifiers changed from: private */
    public interface FormatString {
        int index();

        void print(Object obj, Locale locale);

        String toString();
    }

    private static final Appendable nonNullAppendable(Appendable appendable) {
        return appendable == null ? new StringBuilder() : appendable;
    }

    private Formatter(Locale locale, Appendable appendable) {
        this.a = appendable;
        this.l = locale;
        this.zero = getZero(locale);
    }

    public Formatter() {
        this(Locale.getDefault(Locale.Category.FORMAT), new StringBuilder());
    }

    public Formatter(Locale locale) {
        this(locale, new StringBuilder());
    }

    public Formatter(Appendable appendable, Locale locale) {
        this(locale, nonNullAppendable(appendable));
    }

    private static char getZero(Locale locale) {
        if (locale == null || locale.equals(Locale.US)) {
            return '0';
        }
        return DecimalFormatSymbols.getInstance(locale).getZeroDigit();
    }

    public Locale locale() {
        ensureOpen();
        return this.l;
    }

    public Appendable out() {
        ensureOpen();
        return this.a;
    }

    public String toString() {
        ensureOpen();
        return this.a.toString();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        Appendable appendable = this.a;
        if (appendable != null) {
            try {
                if (appendable instanceof Closeable) {
                    ((Closeable) appendable).close();
                }
            } catch (IOException e) {
                this.lastException = e;
            } catch (Throwable th) {
                this.a = null;
                throw th;
            }
            this.a = null;
        }
    }

    private void ensureOpen() {
        if (this.a == null) {
            throw new FormatterClosedException();
        }
    }

    public Formatter format(String str, Object... objArr) {
        format(this.l, str, objArr);
        return this;
    }

    public Formatter format(Locale locale, String str, Object... objArr) {
        IOException e;
        ensureOpen();
        FormatString[] parse = parse(str);
        int i = -1;
        int i2 = -1;
        for (FormatString formatString : parse) {
            int index = formatString.index();
            Object obj = null;
            if (index == -2) {
                formatString.print(null, locale);
            } else if (index != -1) {
                if (index != 0) {
                    i = index - 1;
                    if (objArr != null) {
                        try {
                            if (i > objArr.length - 1) {
                                throw new MissingFormatArgumentException(formatString.toString());
                            }
                        } catch (IOException e2) {
                            e = e2;
                            this.lastException = e;
                        }
                    }
                    if (objArr != null) {
                        obj = objArr[i];
                    }
                    formatString.print(obj, locale);
                } else {
                    i = i2 + 1;
                    if (objArr != null) {
                        try {
                            if (i > objArr.length - 1) {
                                throw new MissingFormatArgumentException(formatString.toString());
                            }
                        } catch (IOException e3) {
                            e = e3;
                            i2 = i;
                            this.lastException = e;
                        }
                    }
                    if (objArr != null) {
                        obj = objArr[i];
                    }
                    formatString.print(obj, locale);
                    i2 = i;
                }
            } else if (i < 0 || (objArr != null && i > objArr.length - 1)) {
                throw new MissingFormatArgumentException(formatString.toString());
            } else {
                if (objArr != null) {
                    obj = objArr[i];
                }
                formatString.print(obj, locale);
            }
        }
        return this;
    }

    private FormatString[] parse(String str) {
        ArrayList arrayList = new ArrayList();
        int length = str.length();
        int i = 0;
        while (i < length) {
            int indexOf = str.indexOf(37, i);
            if (str.charAt(i) != '%') {
                if (indexOf == -1) {
                    indexOf = length;
                }
                arrayList.add(new FixedString(str.substring(i, indexOf)));
                i = indexOf;
            } else {
                FormatSpecifierParser formatSpecifierParser = new FormatSpecifierParser(str, i + 1);
                arrayList.add(formatSpecifierParser.getFormatSpecifier());
                i = formatSpecifierParser.getEndIdx();
            }
        }
        return (FormatString[]) arrayList.toArray(new FormatString[arrayList.size()]);
    }

    /* access modifiers changed from: private */
    public class FormatSpecifierParser {
        private String conv;
        private int cursor;
        private String flags;
        private final String format;
        private FormatSpecifier fs;
        private String index;
        private String precision;
        private String tT;
        private String width;

        public FormatSpecifierParser(String str, int i) {
            this.format = str;
            this.cursor = i;
            if (nextIsInt()) {
                String nextInt = nextInt();
                if (peek() == '$') {
                    this.index = nextInt;
                    advance();
                } else if (nextInt.charAt(0) == '0') {
                    back(nextInt.length());
                } else {
                    this.width = nextInt;
                }
            }
            this.flags = "";
            while (this.width == null && ",-(+# 0<".indexOf(peek()) >= 0) {
                this.flags += advance();
            }
            if (this.width == null && nextIsInt()) {
                this.width = nextInt();
            }
            if (peek() == '.') {
                advance();
                if (nextIsInt()) {
                    this.precision = nextInt();
                } else {
                    throw new IllegalFormatPrecisionException(peek());
                }
            }
            if (peek() == 't' || peek() == 'T') {
                this.tT = String.valueOf(advance());
            }
            this.conv = String.valueOf(advance());
            this.fs = new FormatSpecifier(this.index, this.flags, this.width, this.precision, this.tT, this.conv);
        }

        private String nextInt() {
            int i = this.cursor;
            while (nextIsInt()) {
                advance();
            }
            return this.format.substring(i, this.cursor);
        }

        private boolean nextIsInt() {
            return !isEnd() && Character.isDigit(peek());
        }

        private char peek() {
            if (!isEnd()) {
                return this.format.charAt(this.cursor);
            }
            throw new UnknownFormatConversionException("End of String");
        }

        private char advance() {
            if (!isEnd()) {
                String str = this.format;
                int i = this.cursor;
                this.cursor = i + 1;
                return str.charAt(i);
            }
            throw new UnknownFormatConversionException("End of String");
        }

        private void back(int i) {
            this.cursor -= i;
        }

        private boolean isEnd() {
            return this.cursor == this.format.length();
        }

        public FormatSpecifier getFormatSpecifier() {
            return this.fs;
        }

        public int getEndIdx() {
            return this.cursor;
        }
    }

    /* access modifiers changed from: private */
    public class FixedString implements FormatString {
        private String s;

        @Override // java.util.Formatter.FormatString
        public int index() {
            return -2;
        }

        FixedString(String str) {
            this.s = str;
        }

        @Override // java.util.Formatter.FormatString
        public void print(Object obj, Locale locale) {
            Formatter.this.a.append(this.s);
        }

        @Override // java.util.Formatter.FormatString
        public String toString() {
            return this.s;
        }
    }

    /* access modifiers changed from: private */
    public class FormatSpecifier implements FormatString {
        private char c;
        private boolean dt = false;
        private Flags f = Flags.NONE;
        private int index = -1;
        private int precision;
        private int width;

        private int index(String str) {
            if (str != null) {
                try {
                    this.index = Integer.parseInt(str);
                } catch (NumberFormatException unused) {
                }
            } else {
                this.index = 0;
            }
            return this.index;
        }

        @Override // java.util.Formatter.FormatString
        public int index() {
            return this.index;
        }

        private Flags flags(String str) {
            this.f = Flags.parse(str);
            if (this.f.contains(Flags.PREVIOUS)) {
                this.index = -1;
            }
            return this.f;
        }

        private int width(String str) {
            this.width = -1;
            if (str != null) {
                try {
                    this.width = Integer.parseInt(str);
                    if (this.width < 0) {
                        throw new IllegalFormatWidthException(this.width);
                    }
                } catch (NumberFormatException unused) {
                }
            }
            return this.width;
        }

        private int precision(String str) {
            this.precision = -1;
            if (str != null) {
                try {
                    this.precision = Integer.parseInt(str);
                    if (this.precision < 0) {
                        throw new IllegalFormatPrecisionException(this.precision);
                    }
                } catch (NumberFormatException unused) {
                }
            }
            return this.precision;
        }

        private char conversion(String str) {
            this.c = str.charAt(0);
            if (!this.dt) {
                if (Conversion.isValid(this.c)) {
                    if (Character.isUpperCase(this.c)) {
                        this.f.add(Flags.UPPERCASE);
                    }
                    this.c = Character.toLowerCase(this.c);
                    if (Conversion.isText(this.c)) {
                        this.index = -2;
                    }
                } else {
                    throw new UnknownFormatConversionException(String.valueOf(this.c));
                }
            }
            return this.c;
        }

        FormatSpecifier(String str, String str2, String str3, String str4, String str5, String str6) {
            index(str);
            flags(str2);
            width(str3);
            precision(str4);
            if (str5 != null) {
                this.dt = true;
                if (str5.equals("T")) {
                    this.f.add(Flags.UPPERCASE);
                }
            }
            conversion(str6);
            if (this.dt) {
                checkDateTime();
            } else if (Conversion.isGeneral(this.c)) {
                checkGeneral();
            } else if (Conversion.isCharacter(this.c)) {
                checkCharacter();
            } else if (Conversion.isInteger(this.c)) {
                checkInteger();
            } else if (Conversion.isFloat(this.c)) {
                checkFloat();
            } else if (Conversion.isText(this.c)) {
                checkText();
            } else {
                throw new UnknownFormatConversionException(String.valueOf(this.c));
            }
        }

        @Override // java.util.Formatter.FormatString
        public void print(Object obj, Locale locale) {
            if (this.dt) {
                printDateTime(obj, locale);
                return;
            }
            char c2 = this.c;
            if (c2 != '%') {
                if (c2 != 'C') {
                    if (c2 != 's') {
                        if (c2 != 'x') {
                            if (c2 == 'n') {
                                Formatter.this.a.append(System.lineSeparator());
                                return;
                            } else if (c2 != 'o') {
                                switch (c2) {
                                    case 'a':
                                    case 'e':
                                    case 'f':
                                    case 'g':
                                        printFloat(obj, locale);
                                        return;
                                    case 'b':
                                        printBoolean(obj);
                                        return;
                                    case 'c':
                                        break;
                                    case 'd':
                                        break;
                                    case 'h':
                                        printHashCode(obj);
                                        return;
                                    default:
                                        return;
                                }
                            }
                        }
                        printInteger(obj, locale);
                        return;
                    }
                    printString(obj, locale);
                    return;
                }
                printCharacter(obj);
                return;
            }
            Formatter.this.a.append('%');
        }

        private void printInteger(Object obj, Locale locale) {
            if (obj == null) {
                print("null");
            } else if (obj instanceof Byte) {
                print(((Byte) obj).byteValue(), locale);
            } else if (obj instanceof Short) {
                print(((Short) obj).shortValue(), locale);
            } else if (obj instanceof Integer) {
                print(((Integer) obj).intValue(), locale);
            } else if (obj instanceof Long) {
                print(((Long) obj).longValue(), locale);
            } else if (obj instanceof BigInteger) {
                print((BigInteger) obj, locale);
            } else {
                failConversion(this.c, obj);
                throw null;
            }
        }

        private void printFloat(Object obj, Locale locale) {
            if (obj == null) {
                print("null");
            } else if (obj instanceof Float) {
                print(((Float) obj).floatValue(), locale);
            } else if (obj instanceof Double) {
                print(((Double) obj).doubleValue(), locale);
            } else if (obj instanceof BigDecimal) {
                print((BigDecimal) obj, locale);
            } else {
                failConversion(this.c, obj);
                throw null;
            }
        }

        private void printDateTime(Object obj, Locale locale) {
            Calendar calendar;
            if (obj == null) {
                print("null");
                return;
            }
            if (obj instanceof Long) {
                calendar = Calendar.getInstance(locale == null ? Locale.US : locale);
                calendar.setTimeInMillis(((Long) obj).longValue());
            } else if (obj instanceof Date) {
                calendar = Calendar.getInstance(locale == null ? Locale.US : locale);
                calendar.setTime((Date) obj);
            } else if (obj instanceof Calendar) {
                calendar = (Calendar) ((Calendar) obj).clone();
                calendar.setLenient(true);
            } else if (obj instanceof TemporalAccessor) {
                print((TemporalAccessor) obj, this.c, locale);
                return;
            } else {
                failConversion(this.c, obj);
                throw null;
            }
            print(calendar, this.c, locale);
        }

        private void printCharacter(Object obj) {
            if (obj == null) {
                print("null");
            } else if (obj instanceof Character) {
                print(((Character) obj).toString());
            } else if (obj instanceof Byte) {
                byte byteValue = ((Byte) obj).byteValue();
                if (Character.isValidCodePoint(byteValue)) {
                    new String(Character.toChars(byteValue));
                    throw null;
                }
                throw new IllegalFormatCodePointException(byteValue);
            } else if (obj instanceof Short) {
                short shortValue = ((Short) obj).shortValue();
                if (Character.isValidCodePoint(shortValue)) {
                    new String(Character.toChars(shortValue));
                    throw null;
                }
                throw new IllegalFormatCodePointException(shortValue);
            } else if (obj instanceof Integer) {
                int intValue = ((Integer) obj).intValue();
                if (Character.isValidCodePoint(intValue)) {
                    new String(Character.toChars(intValue));
                    throw null;
                }
                throw new IllegalFormatCodePointException(intValue);
            } else {
                failConversion(this.c, obj);
                throw null;
            }
        }

        private void printString(Object obj, Locale locale) {
            if (obj instanceof Formattable) {
                Formatter formatter = Formatter.this;
                if (formatter.locale() != locale) {
                    formatter = new Formatter(formatter.out(), locale);
                }
                ((Formattable) obj).formatTo(formatter, this.f.valueOf(), this.width, this.precision);
            } else if (this.f.contains(Flags.ALTERNATE)) {
                failMismatch(Flags.ALTERNATE, 's');
                throw null;
            } else if (obj == null) {
                print("null");
            } else {
                print(obj.toString());
            }
        }

        private void printBoolean(Object obj) {
            String str;
            if (obj == null) {
                str = Boolean.toString(false);
            } else if (obj instanceof Boolean) {
                str = ((Boolean) obj).toString();
            } else {
                str = Boolean.toString(true);
            }
            print(str);
        }

        private void printHashCode(Object obj) {
            if (obj == null) {
                print("null");
            } else {
                Integer.toHexString(obj.hashCode());
                throw null;
            }
        }

        private void print(String str) {
            int i = this.precision;
            if (i != -1 && i < str.length()) {
                str = str.substring(0, this.precision);
            }
            if (this.f.contains(Flags.UPPERCASE)) {
                str = str.toUpperCase(Formatter.this.l != null ? Formatter.this.l : Locale.getDefault());
            }
            Formatter.this.a.append(justify(str));
        }

        private String justify(String str) {
            if (this.width == -1) {
                return str;
            }
            StringBuilder sb = new StringBuilder();
            boolean contains = this.f.contains(Flags.LEFT_JUSTIFY);
            int length = this.width - str.length();
            if (!contains) {
                for (int i = 0; i < length; i++) {
                    sb.append(' ');
                }
            }
            sb.append(str);
            if (contains) {
                for (int i2 = 0; i2 < length; i2++) {
                    sb.append(' ');
                }
            }
            return sb.toString();
        }

        @Override // java.util.Formatter.FormatString
        public String toString() {
            StringBuilder sb = new StringBuilder("%");
            Flags dup = this.f.dup();
            dup.remove(Flags.UPPERCASE);
            sb.append(dup.toString());
            int i = this.index;
            if (i > 0) {
                sb.append(i);
                sb.append('$');
            }
            int i2 = this.width;
            if (i2 != -1) {
                sb.append(i2);
            }
            if (this.precision != -1) {
                sb.append('.');
                sb.append(this.precision);
            }
            if (this.dt) {
                sb.append(this.f.contains(Flags.UPPERCASE) ? 'T' : 't');
            }
            sb.append(this.f.contains(Flags.UPPERCASE) ? Character.toUpperCase(this.c) : this.c);
            return sb.toString();
        }

        private void checkGeneral() {
            char c2 = this.c;
            if ((c2 == 'b' || c2 == 'h') && this.f.contains(Flags.ALTERNATE)) {
                failMismatch(Flags.ALTERNATE, this.c);
                throw null;
            } else if (this.width != -1 || !this.f.contains(Flags.LEFT_JUSTIFY)) {
                checkBadFlags(Flags.PLUS, Flags.LEADING_SPACE, Flags.ZERO_PAD, Flags.GROUP, Flags.PARENTHESES);
            } else {
                throw new MissingFormatWidthException(toString());
            }
        }

        private void checkDateTime() {
            int i = this.precision;
            if (i != -1) {
                throw new IllegalFormatPrecisionException(i);
            } else if (DateTime.isValid(this.c)) {
                checkBadFlags(Flags.ALTERNATE, Flags.PLUS, Flags.LEADING_SPACE, Flags.ZERO_PAD, Flags.GROUP, Flags.PARENTHESES);
                if (this.width == -1 && this.f.contains(Flags.LEFT_JUSTIFY)) {
                    throw new MissingFormatWidthException(toString());
                }
            } else {
                throw new UnknownFormatConversionException("t" + this.c);
            }
        }

        private void checkCharacter() {
            int i = this.precision;
            if (i == -1) {
                checkBadFlags(Flags.ALTERNATE, Flags.PLUS, Flags.LEADING_SPACE, Flags.ZERO_PAD, Flags.GROUP, Flags.PARENTHESES);
                if (this.width == -1 && this.f.contains(Flags.LEFT_JUSTIFY)) {
                    throw new MissingFormatWidthException(toString());
                }
                return;
            }
            throw new IllegalFormatPrecisionException(i);
        }

        private void checkInteger() {
            checkNumeric();
            int i = this.precision;
            if (i == -1) {
                char c2 = this.c;
                if (c2 == 'd') {
                    checkBadFlags(Flags.ALTERNATE);
                } else if (c2 == 'o') {
                    checkBadFlags(Flags.GROUP);
                } else {
                    checkBadFlags(Flags.GROUP);
                }
            } else {
                throw new IllegalFormatPrecisionException(i);
            }
        }

        private void checkBadFlags(Flags... flagsArr) {
            for (int i = 0; i < flagsArr.length; i++) {
                if (this.f.contains(flagsArr[i])) {
                    failMismatch(flagsArr[i], this.c);
                    throw null;
                }
            }
        }

        private void checkFloat() {
            checkNumeric();
            char c2 = this.c;
            if (c2 != 'f') {
                if (c2 == 'a') {
                    checkBadFlags(Flags.PARENTHESES, Flags.GROUP);
                } else if (c2 == 'e') {
                    checkBadFlags(Flags.GROUP);
                } else if (c2 == 'g') {
                    checkBadFlags(Flags.ALTERNATE);
                }
            }
        }

        private void checkNumeric() {
            int i = this.width;
            if (i == -1 || i >= 0) {
                int i2 = this.precision;
                if (i2 != -1 && i2 < 0) {
                    throw new IllegalFormatPrecisionException(i2);
                } else if (this.width == -1 && (this.f.contains(Flags.LEFT_JUSTIFY) || this.f.contains(Flags.ZERO_PAD))) {
                    throw new MissingFormatWidthException(toString());
                } else if ((this.f.contains(Flags.PLUS) && this.f.contains(Flags.LEADING_SPACE)) || (this.f.contains(Flags.LEFT_JUSTIFY) && this.f.contains(Flags.ZERO_PAD))) {
                    throw new IllegalFormatFlagsException(this.f.toString());
                }
            } else {
                throw new IllegalFormatWidthException(i);
            }
        }

        private void checkText() {
            int i = this.precision;
            if (i == -1) {
                char c2 = this.c;
                if (c2 != '%') {
                    if (c2 == 'n') {
                        int i2 = this.width;
                        if (i2 != -1) {
                            throw new IllegalFormatWidthException(i2);
                        } else if (this.f.valueOf() != Flags.NONE.valueOf()) {
                            throw new IllegalFormatFlagsException(this.f.toString());
                        }
                    }
                } else if (this.f.valueOf() != Flags.LEFT_JUSTIFY.valueOf() && this.f.valueOf() != Flags.NONE.valueOf()) {
                    throw new IllegalFormatFlagsException(this.f.toString());
                } else if (this.width == -1 && this.f.contains(Flags.LEFT_JUSTIFY)) {
                    throw new MissingFormatWidthException(toString());
                }
            } else {
                throw new IllegalFormatPrecisionException(i);
            }
        }

        private void print(byte b, Locale locale) {
            char c2;
            long j = (long) b;
            if (b < 0 && ((c2 = this.c) == 'o' || c2 == 'x')) {
                j += 256;
            }
            print(j, locale);
        }

        private void print(short s, Locale locale) {
            char c2;
            long j = (long) s;
            if (s < 0 && ((c2 = this.c) == 'o' || c2 == 'x')) {
                j += 65536;
            }
            print(j, locale);
        }

        private void print(int i, Locale locale) {
            char c2;
            long j = (long) i;
            if (i < 0 && ((c2 = this.c) == 'o' || c2 == 'x')) {
                j += 4294967296L;
            }
            print(j, locale);
        }

        private void print(long j, Locale locale) {
            char[] cArr;
            StringBuilder sb = new StringBuilder();
            char c2 = this.c;
            if (c2 == 'd') {
                int i = (j > 0 ? 1 : (j == 0 ? 0 : -1));
                boolean z = i < 0;
                if (i < 0) {
                    cArr = Long.toString(j, 10).substring(1).toCharArray();
                } else {
                    cArr = Long.toString(j, 10).toCharArray();
                }
                leadingSign(sb, z);
                Flags flags = this.f;
                localizedMagnitude(sb, cArr, flags, adjustWidth(this.width, flags, z), locale);
                trailingSign(sb, z);
            } else if (c2 == 'o') {
                checkBadFlags(Flags.PARENTHESES, Flags.LEADING_SPACE, Flags.PLUS);
                Long.toOctalString(j);
                throw null;
            } else if (c2 == 'x') {
                checkBadFlags(Flags.PARENTHESES, Flags.LEADING_SPACE, Flags.PLUS);
                Long.toHexString(j);
                throw null;
            }
            Formatter.this.a.append(justify(sb.toString()));
        }

        private StringBuilder leadingSign(StringBuilder sb, boolean z) {
            if (!z) {
                if (this.f.contains(Flags.PLUS)) {
                    sb.append('+');
                } else if (this.f.contains(Flags.LEADING_SPACE)) {
                    sb.append(' ');
                }
            } else if (this.f.contains(Flags.PARENTHESES)) {
                sb.append('(');
            } else {
                sb.append('-');
            }
            return sb;
        }

        private StringBuilder trailingSign(StringBuilder sb, boolean z) {
            if (z && this.f.contains(Flags.PARENTHESES)) {
                sb.append(')');
            }
            return sb;
        }

        private void print(BigInteger bigInteger, Locale locale) {
            StringBuilder sb = new StringBuilder();
            boolean z = false;
            boolean z2 = bigInteger.signum() == -1;
            BigInteger abs = bigInteger.abs();
            leadingSign(sb, z2);
            char c2 = this.c;
            if (c2 == 'd') {
                char[] charArray = abs.toString().toCharArray();
                Flags flags = this.f;
                localizedMagnitude(sb, charArray, flags, adjustWidth(this.width, flags, z2), locale);
            } else if (c2 == 'o') {
                String bigInteger2 = abs.toString(8);
                int length = bigInteger2.length() + sb.length();
                if (z2 && this.f.contains(Flags.PARENTHESES)) {
                    length++;
                }
                if (this.f.contains(Flags.ALTERNATE)) {
                    length++;
                    sb.append('0');
                }
                if (this.f.contains(Flags.ZERO_PAD)) {
                    for (int i = 0; i < this.width - length; i++) {
                        sb.append('0');
                    }
                }
                sb.append(bigInteger2);
            } else if (c2 == 'x') {
                String bigInteger3 = abs.toString(16);
                int length2 = bigInteger3.length() + sb.length();
                if (z2 && this.f.contains(Flags.PARENTHESES)) {
                    length2++;
                }
                if (this.f.contains(Flags.ALTERNATE)) {
                    length2 += 2;
                    sb.append(this.f.contains(Flags.UPPERCASE) ? "0X" : "0x");
                }
                if (this.f.contains(Flags.ZERO_PAD)) {
                    for (int i2 = 0; i2 < this.width - length2; i2++) {
                        sb.append('0');
                    }
                }
                if (this.f.contains(Flags.UPPERCASE)) {
                    bigInteger3 = bigInteger3.toUpperCase();
                }
                sb.append(bigInteger3);
            }
            if (bigInteger.signum() == -1) {
                z = true;
            }
            trailingSign(sb, z);
            Formatter.this.a.append(justify(sb.toString()));
        }

        private void print(float f2, Locale locale) {
            print((double) f2, locale);
        }

        private void print(double d, Locale locale) {
            StringBuilder sb = new StringBuilder();
            boolean z = Double.compare(d, 0.0d) == -1;
            if (!Double.isNaN(d)) {
                double abs = Math.abs(d);
                leadingSign(sb, z);
                if (!Double.isInfinite(abs)) {
                    print(sb, abs, locale, this.f, this.c, this.precision, z);
                } else {
                    sb.append(this.f.contains(Flags.UPPERCASE) ? "INFINITY" : "Infinity");
                }
                trailingSign(sb, z);
            } else {
                sb.append(this.f.contains(Flags.UPPERCASE) ? "NAN" : "NaN");
            }
            Formatter.this.a.append(justify(sb.toString()));
        }

        private void print(StringBuilder sb, double d, Locale locale, Flags flags, char c2, int i, boolean z) {
            char[] cArr;
            int i2;
            char[] cArr2;
            Locale locale2;
            String str;
            int i3 = i;
            char c3 = 'e';
            if (c2 == 'e') {
                if (i3 == -1) {
                    i3 = 6;
                }
                FormattedFloatingDecimal valueOf = FormattedFloatingDecimal.valueOf(d, i3, FormattedFloatingDecimal.Form.SCIENTIFIC);
                char[] addZeros = addZeros(valueOf.getMantissa(), i3);
                char[] addDot = (!flags.contains(Flags.ALTERNATE) || i3 != 0) ? addZeros : addDot(addZeros);
                if (d == 0.0d) {
                    cArr2 = new char[]{'+', '0', '0'};
                } else {
                    cArr2 = valueOf.getExponent();
                }
                int i4 = this.width;
                if (i4 != -1) {
                    i4 = adjustWidth((i4 - cArr2.length) - 1, flags, z);
                }
                localizedMagnitude(sb, addDot, flags, i4, locale);
                if (locale != null) {
                    locale2 = locale;
                } else {
                    locale2 = Locale.getDefault();
                }
                LocaleData localeData = LocaleData.get(locale2);
                if (flags.contains(Flags.UPPERCASE)) {
                    str = localeData.exponentSeparator.toUpperCase(locale2);
                } else {
                    str = localeData.exponentSeparator.toLowerCase(locale2);
                }
                sb.append(str);
                Flags dup = flags.dup();
                dup.remove(Flags.GROUP);
                sb.append(cArr2[0]);
                char[] cArr3 = new char[(cArr2.length - 1)];
                System.arraycopy((Object) cArr2, 1, (Object) cArr3, 0, cArr2.length - 1);
                sb.append((CharSequence) localizedMagnitude((StringBuilder) null, cArr3, dup, -1, locale));
            } else if (c2 == 'f') {
                if (i3 == -1) {
                    i3 = 6;
                }
                char[] addZeros2 = addZeros(FormattedFloatingDecimal.valueOf(d, i3, FormattedFloatingDecimal.Form.DECIMAL_FLOAT).getMantissa(), i3);
                if (flags.contains(Flags.ALTERNATE) && i3 == 0) {
                    addZeros2 = addDot(addZeros2);
                }
                int i5 = this.width;
                if (i5 != -1) {
                    i5 = adjustWidth(i5, flags, z);
                }
                localizedMagnitude(sb, addZeros2, flags, i5, locale);
            } else {
                char[] cArr4 = null;
                if (c2 == 'g') {
                    if (i3 == -1) {
                        i3 = 6;
                    } else if (i3 == 0) {
                        i3 = 1;
                    }
                    if (d == 0.0d) {
                        cArr = new char[]{'0'};
                        i2 = 0;
                    } else {
                        FormattedFloatingDecimal valueOf2 = FormattedFloatingDecimal.valueOf(d, i3, FormattedFloatingDecimal.Form.GENERAL);
                        cArr4 = valueOf2.getExponent();
                        cArr = valueOf2.getMantissa();
                        i2 = valueOf2.getExponentRounded();
                    }
                    int i6 = cArr4 != null ? i3 - 1 : i3 - (i2 + 1);
                    char[] addZeros3 = addZeros(cArr, i6);
                    if (flags.contains(Flags.ALTERNATE) && i6 == 0) {
                        addZeros3 = addDot(addZeros3);
                    }
                    int i7 = this.width;
                    if (i7 != -1) {
                        if (cArr4 != null) {
                            i7 = adjustWidth((i7 - cArr4.length) - 1, flags, z);
                        } else {
                            i7 = adjustWidth(i7, flags, z);
                        }
                    }
                    localizedMagnitude(sb, addZeros3, flags, i7, locale);
                    if (cArr4 != null) {
                        if (flags.contains(Flags.UPPERCASE)) {
                            c3 = 'E';
                        }
                        sb.append(c3);
                        Flags dup2 = flags.dup();
                        dup2.remove(Flags.GROUP);
                        sb.append(cArr4[0]);
                        char[] cArr5 = new char[(cArr4.length - 1)];
                        System.arraycopy((Object) cArr4, 1, (Object) cArr5, 0, cArr4.length - 1);
                        sb.append((CharSequence) localizedMagnitude((StringBuilder) null, cArr5, dup2, -1, locale));
                    }
                } else if (c2 == 'a') {
                    if (i3 == -1) {
                        i3 = 0;
                    } else if (i3 == 0) {
                        i3 = 1;
                    }
                    String hexDouble = hexDouble(d, i3);
                    boolean contains = flags.contains(Flags.UPPERCASE);
                    sb.append(contains ? "0X" : "0x");
                    if (flags.contains(Flags.ZERO_PAD)) {
                        for (int i8 = 0; i8 < (this.width - hexDouble.length()) - 2; i8++) {
                            sb.append('0');
                        }
                    }
                    char c4 = 'p';
                    int indexOf = hexDouble.indexOf(112);
                    char[] charArray = hexDouble.substring(0, indexOf).toCharArray();
                    if (!contains) {
                        if (i3 != 0) {
                            charArray = addZeros(charArray, i3);
                        }
                        sb.append(charArray);
                        if (contains) {
                            c4 = 'P';
                        }
                        sb.append(c4);
                        sb.append(hexDouble.substring(indexOf + 1));
                        return;
                    }
                    new String(charArray);
                    throw null;
                }
            }
        }

        /* JADX WARNING: Removed duplicated region for block: B:18:0x0031  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private char[] addZeros(char[] r5, int r6) {
            /*
                r4 = this;
                r4 = 0
                r0 = r4
            L_0x0002:
                int r1 = r5.length
                r2 = 46
                if (r0 >= r1) goto L_0x000f
                char r1 = r5[r0]
                if (r1 != r2) goto L_0x000c
                goto L_0x000f
            L_0x000c:
                int r0 = r0 + 1
                goto L_0x0002
            L_0x000f:
                int r1 = r5.length
                if (r0 != r1) goto L_0x0014
                r1 = 1
                goto L_0x0015
            L_0x0014:
                r1 = r4
            L_0x0015:
                int r3 = r5.length
                int r3 = r3 - r0
                r0 = r1 ^ 1
                int r3 = r3 - r0
                if (r3 != r6) goto L_0x001d
                return r5
            L_0x001d:
                int r0 = r5.length
                int r0 = r0 + r6
                int r0 = r0 - r3
                int r0 = r0 + r1
                char[] r6 = new char[r0]
                int r0 = r5.length
                java.lang.System.arraycopy(r5, r4, r6, r4, r0)
                int r4 = r5.length
                if (r1 == 0) goto L_0x002e
                int r5 = r5.length
                r6[r5] = r2
                goto L_0x0035
            L_0x002e:
                int r5 = r6.length
                if (r4 >= r5) goto L_0x0038
                r5 = 48
                r6[r4] = r5
            L_0x0035:
                int r4 = r4 + 1
                goto L_0x002e
            L_0x0038:
                return r6
            */
            throw new UnsupportedOperationException("Method not decompiled: java.util.Formatter.FormatSpecifier.addZeros(char[], int):char[]");
        }

        private String hexDouble(double d, int i) {
            double d2;
            if (!Double.isFinite(d) || d == 0.0d || i == 0 || i >= 13) {
                return Double.toHexString(d).substring(2);
            }
            boolean z = true;
            boolean z2 = Math.getExponent(d) == -1023;
            if (z2) {
                double unused = Formatter.scaleUp = Math.scalb(1.0d, 54);
                d2 = Formatter.scaleUp * d;
                Math.getExponent(d2);
            } else {
                d2 = d;
            }
            int i2 = 53 - ((i * 4) + 1);
            long doubleToLongBits = Double.doubleToLongBits(d2);
            long j = (Long.MAX_VALUE & doubleToLongBits) >> i2;
            long j2 = (~(-1 << i2)) & doubleToLongBits;
            boolean z3 = (j & 1) == 0;
            long j3 = 1 << (i2 - 1);
            boolean z4 = (j3 & j2) != 0;
            if (i2 <= 1 || ((~j3) & j2) == 0) {
                z = false;
            }
            if ((z3 && z4 && z) || (!z3 && z4)) {
                j++;
            }
            double longBitsToDouble = Double.longBitsToDouble((Long.MIN_VALUE & doubleToLongBits) | (j << i2));
            if (Double.isInfinite(longBitsToDouble)) {
                return "1.0p1024";
            }
            String substring = Double.toHexString(longBitsToDouble).substring(2);
            if (!z2) {
                return substring;
            }
            int indexOf = substring.indexOf(112);
            if (indexOf == -1) {
                return null;
            }
            return substring.substring(0, indexOf) + "p" + Integer.toString(Integer.parseInt(substring.substring(indexOf + 1)) - 54);
        }

        private void print(BigDecimal bigDecimal, Locale locale) {
            char c2 = this.c;
            if (c2 != 'a') {
                StringBuilder sb = new StringBuilder();
                boolean z = bigDecimal.signum() == -1;
                BigDecimal abs = bigDecimal.abs();
                leadingSign(sb, z);
                print(sb, abs, locale, this.f, this.c, this.precision, z);
                trailingSign(sb, z);
                Formatter.this.a.append(justify(sb.toString()));
                return;
            }
            failConversion(c2, bigDecimal);
            throw null;
        }

        private void print(StringBuilder sb, BigDecimal bigDecimal, Locale locale, Flags flags, char c2, int i, boolean z) {
            BigDecimal bigDecimal2;
            int i2;
            int i3;
            int i4 = i;
            char c3 = 'e';
            int i5 = 0;
            if (c2 == 'e') {
                if (i4 == -1) {
                    i4 = 6;
                }
                int scale = bigDecimal.scale();
                int precision2 = bigDecimal.precision();
                int i6 = precision2 - 1;
                if (i4 > i6) {
                    i2 = i4 - i6;
                    i3 = precision2;
                } else {
                    i3 = i4 + 1;
                    i2 = 0;
                }
                BigDecimal bigDecimal3 = new BigDecimal(bigDecimal.unscaledValue(), scale, new MathContext(i3));
                BigDecimalLayout bigDecimalLayout = new BigDecimalLayout(bigDecimal3.unscaledValue(), bigDecimal3.scale(), BigDecimalLayoutForm.SCIENTIFIC);
                char[] mantissa = bigDecimalLayout.mantissa();
                if ((precision2 == 1 || !bigDecimalLayout.hasDot()) && (i2 > 0 || flags.contains(Flags.ALTERNATE))) {
                    mantissa = addDot(mantissa);
                }
                char[] trailingZeros = trailingZeros(mantissa, i2);
                char[] exponent = bigDecimalLayout.exponent();
                int i7 = this.width;
                if (i7 != -1) {
                    i7 = adjustWidth((i7 - exponent.length) - 1, flags, z);
                }
                localizedMagnitude(sb, trailingZeros, flags, i7, locale);
                if (flags.contains(Flags.UPPERCASE)) {
                    c3 = 'E';
                }
                sb.append(c3);
                Flags dup = flags.dup();
                dup.remove(Flags.GROUP);
                char c4 = exponent[0];
                sb.append(exponent[0]);
                char[] cArr = new char[(exponent.length - 1)];
                System.arraycopy((Object) exponent, 1, (Object) cArr, 0, exponent.length - 1);
                sb.append((CharSequence) localizedMagnitude((StringBuilder) null, cArr, dup, -1, locale));
            } else if (c2 == 'f') {
                if (i4 == -1) {
                    i4 = 6;
                }
                int scale2 = bigDecimal.scale();
                if (scale2 > i4) {
                    int precision3 = bigDecimal.precision();
                    bigDecimal2 = precision3 <= scale2 ? bigDecimal.setScale(i4, RoundingMode.HALF_UP) : new BigDecimal(bigDecimal.unscaledValue(), scale2, new MathContext(precision3 - (scale2 - i4)));
                } else {
                    bigDecimal2 = bigDecimal;
                }
                BigDecimalLayout bigDecimalLayout2 = new BigDecimalLayout(bigDecimal2.unscaledValue(), bigDecimal2.scale(), BigDecimalLayoutForm.DECIMAL_FLOAT);
                char[] mantissa2 = bigDecimalLayout2.mantissa();
                if (bigDecimalLayout2.scale() < i4) {
                    i5 = i4 - bigDecimalLayout2.scale();
                }
                if (bigDecimalLayout2.scale() == 0 && (flags.contains(Flags.ALTERNATE) || i5 > 0)) {
                    mantissa2 = addDot(bigDecimalLayout2.mantissa());
                }
                localizedMagnitude(sb, trailingZeros(mantissa2, i5), flags, adjustWidth(this.width, flags, z), locale);
            } else if (c2 == 'g') {
                if (i4 == -1) {
                    i4 = 6;
                } else if (i4 == 0) {
                    i4 = 1;
                }
                BigDecimal valueOf = BigDecimal.valueOf(1, 4);
                BigDecimal valueOf2 = BigDecimal.valueOf(1, -i4);
                if (bigDecimal.equals(BigDecimal.ZERO) || (bigDecimal.compareTo(valueOf) != -1 && bigDecimal.compareTo(valueOf2) == -1)) {
                    print(sb, bigDecimal, locale, flags, 'f', (i4 - ((-bigDecimal.scale()) + (bigDecimal.unscaledValue().toString().length() - 1))) - 1, z);
                } else {
                    print(sb, bigDecimal, locale, flags, 'e', i4 - 1, z);
                }
            }
        }

        /* access modifiers changed from: private */
        public class BigDecimalLayout {
            private boolean dot = false;
            private StringBuilder exp;
            private StringBuilder mant;
            private int scale;

            public BigDecimalLayout(BigInteger bigInteger, int i, BigDecimalLayoutForm bigDecimalLayoutForm) {
                layout(bigInteger, i, bigDecimalLayoutForm);
            }

            public boolean hasDot() {
                return this.dot;
            }

            public int scale() {
                return this.scale;
            }

            public char[] mantissa() {
                return toCharArray(this.mant);
            }

            public char[] exponent() {
                return toCharArray(this.exp);
            }

            private char[] toCharArray(StringBuilder sb) {
                if (sb == null) {
                    return null;
                }
                char[] cArr = new char[sb.length()];
                sb.getChars(0, cArr.length, cArr, 0);
                return cArr;
            }

            private void layout(BigInteger bigInteger, int i, BigDecimalLayoutForm bigDecimalLayoutForm) {
                char[] charArray = bigInteger.toString().toCharArray();
                this.scale = i;
                this.mant = new StringBuilder(charArray.length + 14);
                if (i == 0) {
                    int length = charArray.length;
                    if (length > 1) {
                        this.mant.append(charArray[0]);
                        if (bigDecimalLayoutForm == BigDecimalLayoutForm.SCIENTIFIC) {
                            this.mant.append('.');
                            this.dot = true;
                            int i2 = length - 1;
                            this.mant.append(charArray, 1, i2);
                            this.exp = new StringBuilder("+");
                            if (length < 10) {
                                StringBuilder sb = this.exp;
                                sb.append("0");
                                sb.append(i2);
                                return;
                            }
                            this.exp.append(i2);
                            return;
                        }
                        this.mant.append(charArray, 1, length - 1);
                        return;
                    }
                    this.mant.append(charArray);
                    if (bigDecimalLayoutForm == BigDecimalLayoutForm.SCIENTIFIC) {
                        this.exp = new StringBuilder("+00");
                        return;
                    }
                    return;
                }
                long length2 = (-((long) i)) + ((long) (charArray.length - 1));
                if (bigDecimalLayoutForm == BigDecimalLayoutForm.DECIMAL_FLOAT) {
                    int length3 = i - charArray.length;
                    if (length3 >= 0) {
                        this.mant.append("0.");
                        this.dot = true;
                        while (length3 > 0) {
                            this.mant.append('0');
                            length3--;
                        }
                        this.mant.append(charArray);
                        return;
                    }
                    int i3 = -length3;
                    if (i3 < charArray.length) {
                        this.mant.append(charArray, 0, i3);
                        this.mant.append('.');
                        this.dot = true;
                        this.mant.append(charArray, i3, i);
                        return;
                    }
                    this.mant.append(charArray, 0, charArray.length);
                    for (int i4 = 0; i4 < (-i); i4++) {
                        this.mant.append('0');
                    }
                    this.scale = 0;
                    return;
                }
                this.mant.append(charArray[0]);
                if (charArray.length > 1) {
                    this.mant.append('.');
                    this.dot = true;
                    this.mant.append(charArray, 1, charArray.length - 1);
                }
                this.exp = new StringBuilder();
                int i5 = (length2 > 0 ? 1 : (length2 == 0 ? 0 : -1));
                if (i5 != 0) {
                    long abs = Math.abs(length2);
                    this.exp.append(i5 < 0 ? '-' : '+');
                    if (abs < 10) {
                        this.exp.append('0');
                    }
                    this.exp.append(abs);
                    return;
                }
                this.exp.append("+00");
            }
        }

        private int adjustWidth(int i, Flags flags, boolean z) {
            return (i == -1 || !z || !flags.contains(Flags.PARENTHESES)) ? i : i - 1;
        }

        private char[] addDot(char[] cArr) {
            char[] cArr2 = new char[(cArr.length + 1)];
            System.arraycopy((Object) cArr, 0, (Object) cArr2, 0, cArr.length);
            cArr2[cArr2.length - 1] = '.';
            return cArr2;
        }

        private char[] trailingZeros(char[] cArr, int i) {
            if (i <= 0) {
                return cArr;
            }
            char[] cArr2 = new char[(cArr.length + i)];
            System.arraycopy((Object) cArr, 0, (Object) cArr2, 0, cArr.length);
            for (int length = cArr.length; length < cArr2.length; length++) {
                cArr2[length] = '0';
            }
            return cArr2;
        }

        private void print(Calendar calendar, char c2, Locale locale) {
            StringBuilder sb = new StringBuilder();
            print(sb, calendar, c2, locale);
            String justify = justify(sb.toString());
            if (this.f.contains(Flags.UPPERCASE)) {
                justify = justify.toUpperCase();
            }
            Formatter.this.a.append(justify);
        }

        /* JADX WARNING: Removed duplicated region for block: B:47:0x0189  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private java.lang.Appendable print(java.lang.StringBuilder r17, java.util.Calendar r18, char r19, java.util.Locale r20) {
            /*
            // Method dump skipped, instructions count: 864
            */
            throw new UnsupportedOperationException("Method not decompiled: java.util.Formatter.FormatSpecifier.print(java.lang.StringBuilder, java.util.Calendar, char, java.util.Locale):java.lang.Appendable");
        }

        private void print(TemporalAccessor temporalAccessor, char c2, Locale locale) {
            StringBuilder sb = new StringBuilder();
            print(sb, temporalAccessor, c2, locale);
            String justify = justify(sb.toString());
            if (this.f.contains(Flags.UPPERCASE)) {
                justify = justify.toUpperCase();
            }
            Formatter.this.a.append(justify);
        }

        /* JADX WARNING: Removed duplicated region for block: B:51:0x01d0 A[Catch:{ DateTimeException -> 0x0397 }] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private java.lang.Appendable print(java.lang.StringBuilder r17, java.time.temporal.TemporalAccessor r18, char r19, java.util.Locale r20) {
            /*
            // Method dump skipped, instructions count: 990
            */
            throw new UnsupportedOperationException("Method not decompiled: java.util.Formatter.FormatSpecifier.print(java.lang.StringBuilder, java.time.temporal.TemporalAccessor, char, java.util.Locale):java.lang.Appendable");
        }

        private void failMismatch(Flags flags, char c2) {
            throw new FormatFlagsConversionMismatchException(flags.toString(), c2);
        }

        private void failConversion(char c2, Object obj) {
            throw new IllegalFormatConversionException(c2, obj.getClass());
        }

        private char getZero(Locale locale) {
            if (locale == null || locale.equals(Formatter.this.locale())) {
                return Formatter.this.zero;
            }
            return DecimalFormatSymbols.getInstance(locale).getZeroDigit();
        }

        private StringBuilder localizedMagnitude(StringBuilder sb, long j, Flags flags, int i, Locale locale) {
            return localizedMagnitude(sb, Long.toString(j, 10).toCharArray(), flags, i, locale);
        }

        private StringBuilder localizedMagnitude(StringBuilder sb, char[] cArr, Flags flags, int i, Locale locale) {
            char c2;
            char c3;
            int i2;
            if (sb == null) {
                sb = new StringBuilder();
            }
            int length = sb.length();
            char zero = getZero(locale);
            int length2 = cArr.length;
            int i3 = 0;
            while (true) {
                c2 = '.';
                if (i3 >= length2) {
                    i3 = length2;
                    break;
                } else if (cArr[i3] == '.') {
                    break;
                } else {
                    i3++;
                }
            }
            if (i3 >= length2) {
                c2 = 0;
            } else if (locale != null && !locale.equals(Locale.US)) {
                c2 = DecimalFormatSymbols.getInstance(locale).getDecimalSeparator();
            }
            if (!flags.contains(Flags.GROUP)) {
                c3 = 0;
                i2 = -1;
            } else if (locale == null || locale.equals(Locale.US)) {
                c3 = ',';
                i2 = 3;
            } else {
                char groupingSeparator = DecimalFormatSymbols.getInstance(locale).getGroupingSeparator();
                DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getIntegerInstance(locale);
                i2 = decimalFormat.getGroupingSize();
                c3 = (!decimalFormat.isGroupingUsed() || decimalFormat.getGroupingSize() == 0) ? 0 : groupingSeparator;
            }
            char c4 = c3;
            for (int i4 = 0; i4 < length2; i4++) {
                if (i4 == i3) {
                    sb.append(c2);
                    c4 = 0;
                } else {
                    sb.append((char) ((cArr[i4] - '0') + zero));
                    if (!(c4 == 0 || i4 == i3 - 1 || (i3 - i4) % i2 != 1)) {
                        sb.append(c4);
                    }
                }
            }
            int length3 = sb.length();
            if (i != -1 && flags.contains(Flags.ZERO_PAD)) {
                for (int i5 = 0; i5 < i - length3; i5++) {
                    sb.insert(length, zero);
                }
            }
            return sb;
        }
    }

    /* access modifiers changed from: private */
    public static class Flags {
        static final Flags ALTERNATE = new Flags(4);
        static final Flags GROUP = new Flags(64);
        static final Flags LEADING_SPACE = new Flags(16);
        static final Flags LEFT_JUSTIFY = new Flags(1);
        static final Flags NONE = new Flags(0);
        static final Flags PARENTHESES = new Flags(128);
        static final Flags PLUS = new Flags(8);
        static final Flags PREVIOUS = new Flags(256);
        static final Flags UPPERCASE = new Flags(2);
        static final Flags ZERO_PAD = new Flags(32);
        private int flags;

        private Flags(int i) {
            this.flags = i;
        }

        public int valueOf() {
            return this.flags;
        }

        public boolean contains(Flags flags2) {
            return (this.flags & flags2.valueOf()) == flags2.valueOf();
        }

        public Flags dup() {
            return new Flags(this.flags);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private Flags add(Flags flags2) {
            this.flags = flags2.valueOf() | this.flags;
            return this;
        }

        public Flags remove(Flags flags2) {
            this.flags = (~flags2.valueOf()) & this.flags;
            return this;
        }

        public static Flags parse(String str) {
            char[] charArray = str.toCharArray();
            Flags flags2 = new Flags(0);
            for (char c : charArray) {
                Flags parse = parse(c);
                if (!flags2.contains(parse)) {
                    flags2.add(parse);
                } else {
                    throw new DuplicateFormatFlagsException(parse.toString());
                }
            }
            return flags2;
        }

        private static Flags parse(char c) {
            if (c == ' ') {
                return LEADING_SPACE;
            }
            if (c == '#') {
                return ALTERNATE;
            }
            if (c == '(') {
                return PARENTHESES;
            }
            if (c == '0') {
                return ZERO_PAD;
            }
            if (c == '<') {
                return PREVIOUS;
            }
            switch (c) {
                case '+':
                    return PLUS;
                case ',':
                    return GROUP;
                case '-':
                    return LEFT_JUSTIFY;
                default:
                    throw new UnknownFormatFlagsException(String.valueOf(c));
            }
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            if (contains(LEFT_JUSTIFY)) {
                sb.append('-');
            }
            if (contains(UPPERCASE)) {
                sb.append('^');
            }
            if (contains(ALTERNATE)) {
                sb.append('#');
            }
            if (contains(PLUS)) {
                sb.append('+');
            }
            if (contains(LEADING_SPACE)) {
                sb.append(' ');
            }
            if (contains(ZERO_PAD)) {
                sb.append('0');
            }
            if (contains(GROUP)) {
                sb.append(',');
            }
            if (contains(PARENTHESES)) {
                sb.append('(');
            }
            if (contains(PREVIOUS)) {
                sb.append('<');
            }
            return sb.toString();
        }
    }

    /* access modifiers changed from: private */
    public static class Conversion {
        static boolean isCharacter(char c) {
            return c == 'C' || c == 'c';
        }

        static boolean isFloat(char c) {
            if (c == 'A' || c == 'E' || c == 'G' || c == 'a') {
                return true;
            }
            switch (c) {
                case 'e':
                case 'f':
                case 'g':
                    return true;
                default:
                    return false;
            }
        }

        static boolean isGeneral(char c) {
            return c == 'B' || c == 'H' || c == 'S' || c == 'b' || c == 'h' || c == 's';
        }

        static boolean isInteger(char c) {
            return c == 'X' || c == 'd' || c == 'o' || c == 'x';
        }

        static boolean isText(char c) {
            return c == '%' || c == 'n';
        }

        static boolean isValid(char c) {
            return isGeneral(c) || isInteger(c) || isFloat(c) || isText(c) || c == 't' || isCharacter(c);
        }
    }
}
