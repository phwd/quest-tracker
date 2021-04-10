package java.util;

import android.icu.text.SymbolTable;
import android.support.v4.media.session.PlaybackStateCompat;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.Flushable;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.nio.charset.UnsupportedCharsetException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.time.temporal.TemporalAccessor;
import java.util.Locale;
import libcore.icu.LocaleData;
import sun.misc.FormattedFloatingDecimal;

public final class Formatter implements Closeable, Flushable {
    private static final int MAX_FD_CHARS = 30;
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
    public interface FormatString {
        int index();

        void print(Object obj, Locale locale) throws IOException;

        String toString();
    }

    private static Charset toCharset(String csn) throws UnsupportedEncodingException {
        Objects.requireNonNull(csn, "charsetName");
        try {
            return Charset.forName(csn);
        } catch (IllegalCharsetNameException | UnsupportedCharsetException e) {
            throw new UnsupportedEncodingException(csn);
        }
    }

    private static final Appendable nonNullAppendable(Appendable a2) {
        if (a2 == null) {
            return new StringBuilder();
        }
        return a2;
    }

    private Formatter(Locale l2, Appendable a2) {
        this.a = a2;
        this.l = l2;
        this.zero = getZero(l2);
    }

    private Formatter(Charset charset, Locale l2, File file) throws FileNotFoundException {
        this(l2, new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), charset)));
    }

    public Formatter() {
        this(Locale.getDefault(Locale.Category.FORMAT), new StringBuilder());
    }

    public Formatter(Appendable a2) {
        this(Locale.getDefault(Locale.Category.FORMAT), nonNullAppendable(a2));
    }

    public Formatter(Locale l2) {
        this(l2, new StringBuilder());
    }

    public Formatter(Appendable a2, Locale l2) {
        this(l2, nonNullAppendable(a2));
    }

    public Formatter(String fileName) throws FileNotFoundException {
        this(Locale.getDefault(Locale.Category.FORMAT), new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName))));
    }

    public Formatter(String fileName, String csn) throws FileNotFoundException, UnsupportedEncodingException {
        this(fileName, csn, Locale.getDefault(Locale.Category.FORMAT));
    }

    public Formatter(String fileName, String csn, Locale l2) throws FileNotFoundException, UnsupportedEncodingException {
        this(toCharset(csn), l2, new File(fileName));
    }

    public Formatter(File file) throws FileNotFoundException {
        this(Locale.getDefault(Locale.Category.FORMAT), new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file))));
    }

    public Formatter(File file, String csn) throws FileNotFoundException, UnsupportedEncodingException {
        this(file, csn, Locale.getDefault(Locale.Category.FORMAT));
    }

    public Formatter(File file, String csn, Locale l2) throws FileNotFoundException, UnsupportedEncodingException {
        this(toCharset(csn), l2, file);
    }

    public Formatter(PrintStream ps) {
        this(Locale.getDefault(Locale.Category.FORMAT), (Appendable) Objects.requireNonNull(ps));
    }

    public Formatter(OutputStream os) {
        this(Locale.getDefault(Locale.Category.FORMAT), new BufferedWriter(new OutputStreamWriter(os)));
    }

    public Formatter(OutputStream os, String csn) throws UnsupportedEncodingException {
        this(os, csn, Locale.getDefault(Locale.Category.FORMAT));
    }

    public Formatter(OutputStream os, String csn, Locale l2) throws UnsupportedEncodingException {
        this(l2, new BufferedWriter(new OutputStreamWriter(os, csn)));
    }

    private static char getZero(Locale l2) {
        if (l2 == null || l2.equals(Locale.US)) {
            return '0';
        }
        return DecimalFormatSymbols.getInstance(l2).getZeroDigit();
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

    @Override // java.io.Flushable
    public void flush() {
        ensureOpen();
        Appendable appendable = this.a;
        if (appendable instanceof Flushable) {
            try {
                ((Flushable) appendable).flush();
            } catch (IOException ioe) {
                this.lastException = ioe;
            }
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        Appendable appendable = this.a;
        if (appendable != null) {
            try {
                if (appendable instanceof Closeable) {
                    ((Closeable) appendable).close();
                }
            } catch (IOException ioe) {
                this.lastException = ioe;
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

    public IOException ioException() {
        return this.lastException;
    }

    public Formatter format(String format, Object... args) {
        return format(this.l, format, args);
    }

    public Formatter format(Locale l2, String format, Object... args) {
        ensureOpen();
        int last = -1;
        int lasto = -1;
        FormatString[] fsa = parse(format);
        for (FormatString fs : fsa) {
            int index = fs.index();
            Object obj = null;
            if (index == -2) {
                fs.print(null, l2);
            } else if (index != -1) {
                if (index != 0) {
                    last = index - 1;
                    if (args != null) {
                        try {
                            if (last > args.length - 1) {
                                throw new MissingFormatArgumentException(fs.toString());
                            }
                        } catch (IOException x) {
                            this.lastException = x;
                        }
                    }
                    if (args != null) {
                        obj = args[last];
                    }
                    fs.print(obj, l2);
                } else {
                    lasto++;
                    last = lasto;
                    if (args != null) {
                        if (lasto > args.length - 1) {
                            throw new MissingFormatArgumentException(fs.toString());
                        }
                    }
                    if (args != null) {
                        obj = args[lasto];
                    }
                    fs.print(obj, l2);
                }
            } else if (last < 0 || (args != null && last > args.length - 1)) {
                throw new MissingFormatArgumentException(fs.toString());
            } else {
                if (args != null) {
                    obj = args[last];
                }
                fs.print(obj, l2);
            }
        }
        return this;
    }

    private FormatString[] parse(String s) {
        ArrayList<FormatString> al = new ArrayList<>();
        int i = 0;
        int len = s.length();
        while (i < len) {
            int nextPercent = s.indexOf(37, i);
            if (s.charAt(i) != '%') {
                int plainTextEnd = nextPercent == -1 ? len : nextPercent;
                al.add(new FixedString(s.substring(i, plainTextEnd)));
                i = plainTextEnd;
            } else {
                FormatSpecifierParser fsp = new FormatSpecifierParser(s, i + 1);
                al.add(fsp.getFormatSpecifier());
                i = fsp.getEndIdx();
            }
        }
        return (FormatString[]) al.toArray(new FormatString[al.size()]);
    }

    /* access modifiers changed from: private */
    public class FormatSpecifierParser {
        private static final String FLAGS = ",-(+# 0<";
        private String conv;
        private int cursor;
        private String flags;
        private final String format;
        private FormatSpecifier fs;
        private String index;
        private String precision;
        private String tT;
        private String width;

        public FormatSpecifierParser(String format2, int startIdx) {
            this.format = format2;
            this.cursor = startIdx;
            if (nextIsInt()) {
                String nint = nextInt();
                if (peek() == '$') {
                    this.index = nint;
                    advance();
                } else if (nint.charAt(0) == '0') {
                    back(nint.length());
                } else {
                    this.width = nint;
                }
            }
            this.flags = "";
            while (this.width == null && FLAGS.indexOf(peek()) >= 0) {
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
            int strBegin = this.cursor;
            while (nextIsInt()) {
                advance();
            }
            return this.format.substring(strBegin, this.cursor);
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

        private void back(int len) {
            this.cursor -= len;
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

        FixedString(String s2) {
            this.s = s2;
        }

        @Override // java.util.Formatter.FormatString
        public int index() {
            return -2;
        }

        @Override // java.util.Formatter.FormatString
        public void print(Object arg, Locale l) throws IOException {
            Formatter.this.a.append(this.s);
        }

        @Override // java.util.Formatter.FormatString
        public String toString() {
            return this.s;
        }
    }

    /* access modifiers changed from: private */
    public class FormatSpecifier implements FormatString {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private char c;
        private boolean dt = false;
        private Flags f = Flags.NONE;
        private int index = -1;
        private int precision;
        private int width;

        private int index(String s) {
            if (s != null) {
                try {
                    this.index = Integer.parseInt(s);
                } catch (NumberFormatException e) {
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

        private Flags flags(String s) {
            this.f = Flags.parse(s);
            if (this.f.contains(Flags.PREVIOUS)) {
                this.index = -1;
            }
            return this.f;
        }

        /* access modifiers changed from: package-private */
        public Flags flags() {
            return this.f;
        }

        private int width(String s) {
            this.width = -1;
            if (s != null) {
                try {
                    this.width = Integer.parseInt(s);
                    if (this.width < 0) {
                        throw new IllegalFormatWidthException(this.width);
                    }
                } catch (NumberFormatException e) {
                }
            }
            return this.width;
        }

        /* access modifiers changed from: package-private */
        public int width() {
            return this.width;
        }

        private int precision(String s) {
            this.precision = -1;
            if (s != null) {
                try {
                    this.precision = Integer.parseInt(s);
                    if (this.precision < 0) {
                        throw new IllegalFormatPrecisionException(this.precision);
                    }
                } catch (NumberFormatException e) {
                }
            }
            return this.precision;
        }

        /* access modifiers changed from: package-private */
        public int precision() {
            return this.precision;
        }

        private char conversion(String s) {
            this.c = s.charAt(0);
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

        private char conversion() {
            return this.c;
        }

        FormatSpecifier(String indexStr, String flagsStr, String widthStr, String precisionStr, String tTStr, String convStr) {
            index(indexStr);
            flags(flagsStr);
            width(widthStr);
            precision(precisionStr);
            if (tTStr != null) {
                this.dt = true;
                if (tTStr.equals("T")) {
                    this.f.add(Flags.UPPERCASE);
                }
            }
            conversion(convStr);
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
        public void print(Object arg, Locale l) throws IOException {
            if (this.dt) {
                printDateTime(arg, l);
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
                                        printFloat(arg, l);
                                        return;
                                    case 'b':
                                        printBoolean(arg);
                                        return;
                                    case 'c':
                                        break;
                                    case 'd':
                                        break;
                                    case 'h':
                                        printHashCode(arg);
                                        return;
                                    default:
                                        return;
                                }
                            }
                        }
                        printInteger(arg, l);
                        return;
                    }
                    printString(arg, l);
                    return;
                }
                printCharacter(arg);
                return;
            }
            Formatter.this.a.append('%');
        }

        private void printInteger(Object arg, Locale l) throws IOException {
            if (arg == null) {
                print("null");
            } else if (arg instanceof Byte) {
                print(((Byte) arg).byteValue(), l);
            } else if (arg instanceof Short) {
                print(((Short) arg).shortValue(), l);
            } else if (arg instanceof Integer) {
                print(((Integer) arg).intValue(), l);
            } else if (arg instanceof Long) {
                print(((Long) arg).longValue(), l);
            } else if (arg instanceof BigInteger) {
                print((BigInteger) arg, l);
            } else {
                failConversion(this.c, arg);
            }
        }

        private void printFloat(Object arg, Locale l) throws IOException {
            if (arg == null) {
                print("null");
            } else if (arg instanceof Float) {
                print(((Float) arg).floatValue(), l);
            } else if (arg instanceof Double) {
                print(((Double) arg).doubleValue(), l);
            } else if (arg instanceof BigDecimal) {
                print((BigDecimal) arg, l);
            } else {
                failConversion(this.c, arg);
            }
        }

        private void printDateTime(Object arg, Locale l) throws IOException {
            if (arg == null) {
                print("null");
                return;
            }
            Calendar cal = null;
            if (arg instanceof Long) {
                cal = Calendar.getInstance(l == null ? Locale.US : l);
                cal.setTimeInMillis(((Long) arg).longValue());
            } else if (arg instanceof Date) {
                cal = Calendar.getInstance(l == null ? Locale.US : l);
                cal.setTime((Date) arg);
            } else if (arg instanceof Calendar) {
                cal = (Calendar) ((Calendar) arg).clone();
                cal.setLenient(true);
            } else if (arg instanceof TemporalAccessor) {
                print((TemporalAccessor) arg, this.c, l);
                return;
            } else {
                failConversion(this.c, arg);
            }
            print(cal, this.c, l);
        }

        private void printCharacter(Object arg) throws IOException {
            if (arg == null) {
                print("null");
                return;
            }
            String s = null;
            if (arg instanceof Character) {
                s = ((Character) arg).toString();
            } else if (arg instanceof Byte) {
                byte i = ((Byte) arg).byteValue();
                if (Character.isValidCodePoint(i)) {
                    s = new String(Character.toChars(i));
                } else {
                    throw new IllegalFormatCodePointException(i);
                }
            } else if (arg instanceof Short) {
                short i2 = ((Short) arg).shortValue();
                if (Character.isValidCodePoint(i2)) {
                    s = new String(Character.toChars(i2));
                } else {
                    throw new IllegalFormatCodePointException(i2);
                }
            } else if (arg instanceof Integer) {
                int i3 = ((Integer) arg).intValue();
                if (Character.isValidCodePoint(i3)) {
                    s = new String(Character.toChars(i3));
                } else {
                    throw new IllegalFormatCodePointException(i3);
                }
            } else {
                failConversion(this.c, arg);
            }
            print(s);
        }

        private void printString(Object arg, Locale l) throws IOException {
            if (arg instanceof Formattable) {
                Formatter fmt = Formatter.this;
                if (fmt.locale() != l) {
                    fmt = new Formatter(fmt.out(), l);
                }
                ((Formattable) arg).formatTo(fmt, this.f.valueOf(), this.width, this.precision);
                return;
            }
            if (this.f.contains(Flags.ALTERNATE)) {
                failMismatch(Flags.ALTERNATE, 's');
            }
            if (arg == null) {
                print("null");
            } else {
                print(arg.toString());
            }
        }

        private void printBoolean(Object arg) throws IOException {
            String s;
            if (arg == null) {
                s = Boolean.toString(false);
            } else if (arg instanceof Boolean) {
                s = ((Boolean) arg).toString();
            } else {
                s = Boolean.toString(true);
            }
            print(s);
        }

        private void printHashCode(Object arg) throws IOException {
            String s;
            if (arg == null) {
                s = "null";
            } else {
                s = Integer.toHexString(arg.hashCode());
            }
            print(s);
        }

        private void print(String s) throws IOException {
            int i = this.precision;
            if (i != -1 && i < s.length()) {
                s = s.substring(0, this.precision);
            }
            if (this.f.contains(Flags.UPPERCASE)) {
                s = s.toUpperCase(Formatter.this.l != null ? Formatter.this.l : Locale.getDefault());
            }
            Formatter.this.a.append(justify(s));
        }

        private String justify(String s) {
            if (this.width == -1) {
                return s;
            }
            StringBuilder sb = new StringBuilder();
            boolean pad = this.f.contains(Flags.LEFT_JUSTIFY);
            int sp = this.width - s.length();
            if (!pad) {
                for (int i = 0; i < sp; i++) {
                    sb.append(' ');
                }
            }
            sb.append(s);
            if (pad) {
                for (int i2 = 0; i2 < sp; i2++) {
                    sb.append(' ');
                }
            }
            return sb.toString();
        }

        @Override // java.util.Formatter.FormatString
        public String toString() {
            StringBuilder sb = new StringBuilder("%");
            sb.append(this.f.dup().remove(Flags.UPPERCASE).toString());
            int i = this.index;
            if (i > 0) {
                sb.append(i);
                sb.append(SymbolTable.SYMBOL_REF);
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
            }
            if (this.width != -1 || !this.f.contains(Flags.LEFT_JUSTIFY)) {
                checkBadFlags(Flags.PLUS, Flags.LEADING_SPACE, Flags.ZERO_PAD, Flags.GROUP, Flags.PARENTHESES);
                return;
            }
            throw new MissingFormatWidthException(toString());
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

        private void checkBadFlags(Flags... badFlags) {
            for (int i = 0; i < badFlags.length; i++) {
                if (this.f.contains(badFlags[i])) {
                    failMismatch(badFlags[i], this.c);
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

        private void print(byte value, Locale l) throws IOException {
            char c2;
            long v = (long) value;
            if (value < 0 && ((c2 = this.c) == 'o' || c2 == 'x')) {
                v += 256;
            }
            print(v, l);
        }

        private void print(short value, Locale l) throws IOException {
            char c2;
            long v = (long) value;
            if (value < 0 && ((c2 = this.c) == 'o' || c2 == 'x')) {
                v += PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH;
            }
            print(v, l);
        }

        private void print(int value, Locale l) throws IOException {
            char c2;
            long v = (long) value;
            if (value < 0 && ((c2 = this.c) == 'o' || c2 == 'x')) {
                v += 4294967296L;
            }
            print(v, l);
        }

        private void print(long value, Locale l) throws IOException {
            int len;
            int len2;
            char[] va;
            StringBuilder sb = new StringBuilder();
            char c2 = this.c;
            boolean neg = false;
            if (c2 == 'd') {
                if (value < 0) {
                    neg = true;
                }
                if (value < 0) {
                    va = Long.toString(value, 10).substring(1).toCharArray();
                } else {
                    va = Long.toString(value, 10).toCharArray();
                }
                leadingSign(sb, neg);
                Flags flags = this.f;
                localizedMagnitude(sb, va, flags, adjustWidth(this.width, flags, neg), l);
                trailingSign(sb, neg);
            } else if (c2 == 'o') {
                checkBadFlags(Flags.PARENTHESES, Flags.LEADING_SPACE, Flags.PLUS);
                String s = Long.toOctalString(value);
                if (this.f.contains(Flags.ALTERNATE)) {
                    len2 = s.length() + 1;
                } else {
                    len2 = s.length();
                }
                if (this.f.contains(Flags.ALTERNATE)) {
                    sb.append('0');
                }
                if (this.f.contains(Flags.ZERO_PAD)) {
                    for (int i = 0; i < this.width - len2; i++) {
                        sb.append('0');
                    }
                }
                sb.append(s);
            } else if (c2 == 'x') {
                checkBadFlags(Flags.PARENTHESES, Flags.LEADING_SPACE, Flags.PLUS);
                String s2 = Long.toHexString(value);
                if (this.f.contains(Flags.ALTERNATE)) {
                    len = s2.length() + 2;
                } else {
                    len = s2.length();
                }
                if (this.f.contains(Flags.ALTERNATE)) {
                    sb.append(this.f.contains(Flags.UPPERCASE) ? "0X" : "0x");
                }
                if (this.f.contains(Flags.ZERO_PAD)) {
                    for (int i2 = 0; i2 < this.width - len; i2++) {
                        sb.append('0');
                    }
                }
                if (this.f.contains(Flags.UPPERCASE)) {
                    s2 = s2.toUpperCase();
                }
                sb.append(s2);
            }
            Formatter.this.a.append(justify(sb.toString()));
        }

        private StringBuilder leadingSign(StringBuilder sb, boolean neg) {
            if (!neg) {
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

        private StringBuilder trailingSign(StringBuilder sb, boolean neg) {
            if (neg && this.f.contains(Flags.PARENTHESES)) {
                sb.append(')');
            }
            return sb;
        }

        private void print(BigInteger value, Locale l) throws IOException {
            StringBuilder sb = new StringBuilder();
            boolean z = false;
            boolean neg = value.signum() == -1;
            BigInteger v = value.abs();
            leadingSign(sb, neg);
            char c2 = this.c;
            if (c2 == 'd') {
                char[] va = v.toString().toCharArray();
                Flags flags = this.f;
                localizedMagnitude(sb, va, flags, adjustWidth(this.width, flags, neg), l);
            } else if (c2 == 'o') {
                String s = v.toString(8);
                int len = s.length() + sb.length();
                if (neg && this.f.contains(Flags.PARENTHESES)) {
                    len++;
                }
                if (this.f.contains(Flags.ALTERNATE)) {
                    len++;
                    sb.append('0');
                }
                if (this.f.contains(Flags.ZERO_PAD)) {
                    for (int i = 0; i < this.width - len; i++) {
                        sb.append('0');
                    }
                }
                sb.append(s);
            } else if (c2 == 'x') {
                String s2 = v.toString(16);
                int len2 = s2.length() + sb.length();
                if (neg && this.f.contains(Flags.PARENTHESES)) {
                    len2++;
                }
                if (this.f.contains(Flags.ALTERNATE)) {
                    len2 += 2;
                    sb.append(this.f.contains(Flags.UPPERCASE) ? "0X" : "0x");
                }
                if (this.f.contains(Flags.ZERO_PAD)) {
                    for (int i2 = 0; i2 < this.width - len2; i2++) {
                        sb.append('0');
                    }
                }
                if (this.f.contains(Flags.UPPERCASE)) {
                    s2 = s2.toUpperCase();
                }
                sb.append(s2);
            }
            if (value.signum() == -1) {
                z = true;
            }
            trailingSign(sb, z);
            Formatter.this.a.append(justify(sb.toString()));
        }

        private void print(float value, Locale l) throws IOException {
            print((double) value, l);
        }

        private void print(double value, Locale l) throws IOException {
            StringBuilder sb = new StringBuilder();
            boolean neg = Double.compare(value, 0.0d) == -1;
            if (!Double.isNaN(value)) {
                double v = Math.abs(value);
                leadingSign(sb, neg);
                if (!Double.isInfinite(v)) {
                    print(sb, v, l, this.f, this.c, this.precision, neg);
                } else {
                    sb.append(this.f.contains(Flags.UPPERCASE) ? "INFINITY" : "Infinity");
                }
                trailingSign(sb, neg);
            } else {
                sb.append(this.f.contains(Flags.UPPERCASE) ? "NAN" : "NaN");
            }
            Formatter.this.a.append(justify(sb.toString()));
        }

        /* JADX INFO: Multiple debug info for r0v8 int: [D('mant' char[]), D('newW' int)] */
        /* JADX INFO: Multiple debug info for r0v34 int: [D('mant' char[]), D('newW' int)] */
        private void print(StringBuilder sb, double value, Locale l, Flags f2, char c2, int precision2, boolean neg) throws IOException {
            int expRounded;
            char[] exp;
            char[] exp2;
            char[] mant;
            int newW;
            char[] mant2;
            int newW2;
            char[] mant3;
            int newW3;
            String str;
            int prec = 6;
            if (c2 == 'e') {
                if (precision2 != -1) {
                    prec = precision2;
                }
                FormattedFloatingDecimal fd = FormattedFloatingDecimal.valueOf(value, prec, FormattedFloatingDecimal.Form.SCIENTIFIC);
                char[] mant4 = addZeros(fd.getMantissa(), prec);
                if (!f2.contains(Flags.ALTERNATE) || prec != 0) {
                    mant3 = mant4;
                } else {
                    mant3 = addDot(mant4);
                }
                char[] exp3 = value == 0.0d ? new char[]{'+', '0', '0'} : fd.getExponent();
                int newW4 = this.width;
                int i = this.width;
                if (i != -1) {
                    newW3 = adjustWidth((i - exp3.length) - 1, f2, neg);
                } else {
                    newW3 = newW4;
                }
                localizedMagnitude(sb, mant3, f2, newW3, l);
                Locale separatorLocale = l != null ? l : Locale.getDefault();
                LocaleData localeData = LocaleData.get(separatorLocale);
                if (f2.contains(Flags.UPPERCASE)) {
                    str = localeData.exponentSeparator.toUpperCase(separatorLocale);
                } else {
                    str = localeData.exponentSeparator.toLowerCase(separatorLocale);
                }
                sb.append(str);
                Flags flags = f2.dup().remove(Flags.GROUP);
                sb.append(exp3[0]);
                char[] tmp = new char[(exp3.length - 1)];
                System.arraycopy((Object) exp3, 1, (Object) tmp, 0, exp3.length - 1);
                sb.append((CharSequence) localizedMagnitude((StringBuilder) null, tmp, flags, -1, l));
            } else if (c2 == 'f') {
                if (precision2 != -1) {
                    prec = precision2;
                }
                char[] mant5 = addZeros(FormattedFloatingDecimal.valueOf(value, prec, FormattedFloatingDecimal.Form.DECIMAL_FLOAT).getMantissa(), prec);
                if (!f2.contains(Flags.ALTERNATE) || prec != 0) {
                    mant2 = mant5;
                } else {
                    mant2 = addDot(mant5);
                }
                int newW5 = this.width;
                int i2 = this.width;
                if (i2 != -1) {
                    newW2 = adjustWidth(i2, f2, neg);
                } else {
                    newW2 = newW5;
                }
                localizedMagnitude(sb, mant2, f2, newW2, l);
            } else if (c2 == 'g') {
                int prec2 = precision2;
                if (precision2 == -1) {
                    prec2 = 6;
                } else if (precision2 == 0) {
                    prec2 = 1;
                }
                if (value == 0.0d) {
                    exp2 = new char[]{'0'};
                    exp = null;
                    expRounded = 0;
                } else {
                    FormattedFloatingDecimal fd2 = FormattedFloatingDecimal.valueOf(value, prec2, FormattedFloatingDecimal.Form.GENERAL);
                    char[] exp4 = fd2.getExponent();
                    char[] mant6 = fd2.getMantissa();
                    expRounded = fd2.getExponentRounded();
                    exp = exp4;
                    exp2 = mant6;
                }
                int prec3 = exp != null ? prec2 - 1 : prec2 - (expRounded + 1);
                char[] mant7 = addZeros(exp2, prec3);
                if (!f2.contains(Flags.ALTERNATE) || prec3 != 0) {
                    mant = mant7;
                } else {
                    mant = addDot(mant7);
                }
                int newW6 = this.width;
                int i3 = this.width;
                if (i3 == -1) {
                    newW = newW6;
                } else if (exp != null) {
                    newW = adjustWidth((i3 - exp.length) - 1, f2, neg);
                } else {
                    newW = adjustWidth(i3, f2, neg);
                }
                localizedMagnitude(sb, mant, f2, newW, l);
                if (exp != null) {
                    sb.append(f2.contains(Flags.UPPERCASE) ? 'E' : 'e');
                    Flags flags2 = f2.dup().remove(Flags.GROUP);
                    sb.append(exp[0]);
                    char[] tmp2 = new char[(exp.length - 1)];
                    System.arraycopy((Object) exp, 1, (Object) tmp2, 0, exp.length - 1);
                    sb.append((CharSequence) localizedMagnitude((StringBuilder) null, tmp2, flags2, -1, l));
                }
            } else if (c2 == 'a') {
                int prec4 = precision2;
                if (precision2 == -1) {
                    prec4 = 0;
                } else if (precision2 == 0) {
                    prec4 = 1;
                }
                String s = hexDouble(value, prec4);
                boolean upper = f2.contains(Flags.UPPERCASE);
                sb.append(upper ? "0X" : "0x");
                if (f2.contains(Flags.ZERO_PAD)) {
                    for (int i4 = 0; i4 < (this.width - s.length()) - 2; i4++) {
                        sb.append('0');
                    }
                }
                char c3 = 'p';
                int idx = s.indexOf(112);
                char[] va = s.substring(0, idx).toCharArray();
                if (upper) {
                    va = new String(va).toUpperCase(Locale.US).toCharArray();
                }
                sb.append(prec4 != 0 ? addZeros(va, prec4) : va);
                if (upper) {
                    c3 = 'P';
                }
                sb.append(c3);
                sb.append(s.substring(idx + 1));
            }
        }

        private char[] addZeros(char[] v, int prec) {
            int i = 0;
            while (i < v.length && v[i] != '.') {
                i++;
            }
            boolean needDot = false;
            if (i == v.length) {
                needDot = true;
            }
            int i2 = 1;
            int outPrec = (v.length - i) - (needDot ? 0 : 1);
            if (outPrec == prec) {
                return v;
            }
            int length = (v.length + prec) - outPrec;
            if (!needDot) {
                i2 = 0;
            }
            char[] tmp = new char[(length + i2)];
            System.arraycopy((Object) v, 0, (Object) tmp, 0, v.length);
            int start = v.length;
            if (needDot) {
                tmp[v.length] = '.';
                start++;
            }
            for (int j = start; j < tmp.length; j++) {
                tmp[j] = '0';
            }
            return tmp;
        }

        private String hexDouble(double d, int prec) {
            double d2;
            if (!Double.isFinite(d) || d == 0.0d || prec == 0 || prec >= 13) {
                return Double.toHexString(d).substring(2);
            }
            int exponent = Math.getExponent(d);
            boolean subnormal = exponent == -1023;
            if (subnormal) {
                double unused = Formatter.scaleUp = Math.scalb(1.0d, 54);
                d2 = Formatter.scaleUp * d;
                exponent = Math.getExponent(d2);
            } else {
                d2 = d;
            }
            int shiftDistance = 53 - ((prec * 4) + 1);
            long doppel = Double.doubleToLongBits(d2);
            long newSignif = (Long.MAX_VALUE & doppel) >> shiftDistance;
            long roundingBits = (~(-1 << shiftDistance)) & doppel;
            boolean leastZero = (newSignif & 1) == 0;
            boolean round = ((1 << (shiftDistance + -1)) & roundingBits) != 0;
            boolean sticky = shiftDistance > 1 && ((~(1 << (shiftDistance + -1))) & roundingBits) != 0;
            if ((leastZero && round && sticky) || (!leastZero && round)) {
                newSignif++;
            }
            double result = Double.longBitsToDouble((Long.MIN_VALUE & doppel) | (newSignif << shiftDistance));
            if (Double.isInfinite(result)) {
                return "1.0p1024";
            }
            String res = Double.toHexString(result).substring(2);
            if (!subnormal) {
                return res;
            }
            int idx = res.indexOf(112);
            if (idx == -1) {
                return null;
            }
            return res.substring(0, idx) + "p" + Integer.toString(Integer.parseInt(res.substring(idx + 1)) - 54);
        }

        private void print(BigDecimal value, Locale l) throws IOException {
            char c2 = this.c;
            if (c2 == 'a') {
                failConversion(c2, value);
            }
            StringBuilder sb = new StringBuilder();
            boolean neg = value.signum() == -1;
            BigDecimal v = value.abs();
            leadingSign(sb, neg);
            print(sb, v, l, this.f, this.c, this.precision, neg);
            trailingSign(sb, neg);
            Formatter.this.a.append(justify(sb.toString()));
        }

        private void print(StringBuilder sb, BigDecimal value, Locale l, Flags f2, char c2, int precision2, boolean neg) throws IOException {
            int prec;
            int compPrec;
            int newW;
            BigDecimal value2 = value;
            int prec2 = 6;
            if (c2 == 'e') {
                if (precision2 != -1) {
                    prec2 = precision2;
                }
                int scale = value.scale();
                int origPrec = value.precision();
                int nzeros = 0;
                if (prec2 > origPrec - 1) {
                    compPrec = origPrec;
                    nzeros = prec2 - (origPrec - 1);
                } else {
                    compPrec = prec2 + 1;
                }
                BigDecimal v = new BigDecimal(value.unscaledValue(), scale, new MathContext(compPrec));
                BigDecimalLayout bdl = new BigDecimalLayout(v.unscaledValue(), v.scale(), BigDecimalLayoutForm.SCIENTIFIC);
                char[] mant = bdl.mantissa();
                if ((origPrec == 1 || !bdl.hasDot()) && (nzeros > 0 || f2.contains(Flags.ALTERNATE))) {
                    mant = addDot(mant);
                }
                char[] mant2 = trailingZeros(mant, nzeros);
                char[] exp = bdl.exponent();
                int newW2 = this.width;
                int i = this.width;
                if (i != -1) {
                    newW = adjustWidth((i - exp.length) - 1, f2, neg);
                } else {
                    newW = newW2;
                }
                localizedMagnitude(sb, mant2, f2, newW, l);
                sb.append(f2.contains(Flags.UPPERCASE) ? 'E' : 'e');
                Flags flags = f2.dup().remove(Flags.GROUP);
                char c3 = exp[0];
                sb.append(exp[0]);
                char[] tmp = new char[(exp.length - 1)];
                System.arraycopy((Object) exp, 1, (Object) tmp, 0, exp.length - 1);
                sb.append((CharSequence) localizedMagnitude((StringBuilder) null, tmp, flags, -1, l));
                return;
            }
            int nzeros2 = 0;
            if (c2 == 'f') {
                if (precision2 != -1) {
                    prec2 = precision2;
                }
                int scale2 = value.scale();
                if (scale2 > prec2) {
                    int compPrec2 = value.precision();
                    if (compPrec2 <= scale2) {
                        value2 = value2.setScale(prec2, RoundingMode.HALF_UP);
                    } else {
                        value2 = new BigDecimal(value.unscaledValue(), scale2, new MathContext(compPrec2 - (scale2 - prec2)));
                    }
                }
                BigDecimalLayout bdl2 = new BigDecimalLayout(value2.unscaledValue(), value2.scale(), BigDecimalLayoutForm.DECIMAL_FLOAT);
                char[] mant3 = bdl2.mantissa();
                if (bdl2.scale() < prec2) {
                    nzeros2 = prec2 - bdl2.scale();
                }
                if (bdl2.scale() == 0 && (f2.contains(Flags.ALTERNATE) || nzeros2 > 0)) {
                    mant3 = addDot(bdl2.mantissa());
                }
                localizedMagnitude(sb, trailingZeros(mant3, nzeros2), f2, adjustWidth(this.width, f2, neg), l);
            } else if (c2 == 'g') {
                if (precision2 == -1) {
                    prec = 6;
                } else if (precision2 == 0) {
                    prec = 1;
                } else {
                    prec = precision2;
                }
                BigDecimal tenToTheNegFour = BigDecimal.valueOf(1, 4);
                BigDecimal tenToThePrec = BigDecimal.valueOf(1, -prec);
                if (!value2.equals(BigDecimal.ZERO)) {
                    if (value2.compareTo(tenToTheNegFour) == -1 || value2.compareTo(tenToThePrec) != -1) {
                        print(sb, value, l, f2, 'e', prec - 1, neg);
                        return;
                    }
                }
                print(sb, value, l, f2, 'f', (prec - ((-value.scale()) + (value.unscaledValue().toString().length() - 1))) - 1, neg);
            } else {
                if (c2 == 'a') {
                }
            }
        }

        /* access modifiers changed from: private */
        public class BigDecimalLayout {
            private boolean dot = false;
            private StringBuilder exp;
            private StringBuilder mant;
            private int scale;

            public BigDecimalLayout(BigInteger intVal, int scale2, BigDecimalLayoutForm form) {
                layout(intVal, scale2, form);
            }

            public boolean hasDot() {
                return this.dot;
            }

            public int scale() {
                return this.scale;
            }

            public char[] layoutChars() {
                StringBuilder sb = new StringBuilder(this.mant);
                if (this.exp != null) {
                    sb.append('E');
                    sb.append((CharSequence) this.exp);
                }
                return toCharArray(sb);
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
                char[] result = new char[sb.length()];
                sb.getChars(0, result.length, result, 0);
                return result;
            }

            private void layout(BigInteger intVal, int scale2, BigDecimalLayoutForm form) {
                char[] coeff = intVal.toString().toCharArray();
                this.scale = scale2;
                this.mant = new StringBuilder(coeff.length + 14);
                if (scale2 == 0) {
                    int len = coeff.length;
                    if (len > 1) {
                        this.mant.append(coeff[0]);
                        if (form == BigDecimalLayoutForm.SCIENTIFIC) {
                            this.mant.append('.');
                            this.dot = true;
                            this.mant.append(coeff, 1, len - 1);
                            this.exp = new StringBuilder("+");
                            if (len < 10) {
                                StringBuilder sb = this.exp;
                                sb.append(AndroidHardcodedSystemProperties.JAVA_VERSION);
                                sb.append(len - 1);
                                return;
                            }
                            this.exp.append(len - 1);
                            return;
                        }
                        this.mant.append(coeff, 1, len - 1);
                        return;
                    }
                    this.mant.append(coeff);
                    if (form == BigDecimalLayoutForm.SCIENTIFIC) {
                        this.exp = new StringBuilder("+00");
                        return;
                    }
                    return;
                }
                long adjusted = (-((long) scale2)) + ((long) (coeff.length - 1));
                if (form == BigDecimalLayoutForm.DECIMAL_FLOAT) {
                    int pad = scale2 - coeff.length;
                    if (pad >= 0) {
                        this.mant.append("0.");
                        this.dot = true;
                        while (pad > 0) {
                            this.mant.append('0');
                            pad--;
                        }
                        this.mant.append(coeff);
                    } else if ((-pad) < coeff.length) {
                        this.mant.append(coeff, 0, -pad);
                        this.mant.append('.');
                        this.dot = true;
                        this.mant.append(coeff, -pad, scale2);
                    } else {
                        this.mant.append(coeff, 0, coeff.length);
                        for (int i = 0; i < (-scale2); i++) {
                            this.mant.append('0');
                        }
                        this.scale = 0;
                    }
                } else {
                    this.mant.append(coeff[0]);
                    if (coeff.length > 1) {
                        this.mant.append('.');
                        this.dot = true;
                        this.mant.append(coeff, 1, coeff.length - 1);
                    }
                    this.exp = new StringBuilder();
                    if (adjusted != 0) {
                        long abs = Math.abs(adjusted);
                        this.exp.append(adjusted < 0 ? '-' : '+');
                        if (abs < 10) {
                            this.exp.append('0');
                        }
                        this.exp.append(abs);
                        return;
                    }
                    this.exp.append("+00");
                }
            }
        }

        private int adjustWidth(int width2, Flags f2, boolean neg) {
            if (width2 == -1 || !neg || !f2.contains(Flags.PARENTHESES)) {
                return width2;
            }
            return width2 - 1;
        }

        private char[] addDot(char[] mant) {
            char[] tmp = new char[(mant.length + 1)];
            System.arraycopy((Object) mant, 0, (Object) tmp, 0, mant.length);
            tmp[tmp.length - 1] = '.';
            return tmp;
        }

        private char[] trailingZeros(char[] mant, int nzeros) {
            char[] tmp = mant;
            if (nzeros > 0) {
                tmp = new char[(mant.length + nzeros)];
                System.arraycopy((Object) mant, 0, (Object) tmp, 0, mant.length);
                for (int i = mant.length; i < tmp.length; i++) {
                    tmp[i] = '0';
                }
            }
            return tmp;
        }

        private void print(Calendar t, char c2, Locale l) throws IOException {
            StringBuilder sb = new StringBuilder();
            print(sb, t, c2, l);
            String s = justify(sb.toString());
            if (this.f.contains(Flags.UPPERCASE)) {
                s = s.toUpperCase();
            }
            Formatter.this.a.append(s);
        }

        /* JADX WARNING: Removed duplicated region for block: B:48:0x0185  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private java.lang.Appendable print(java.lang.StringBuilder r18, java.util.Calendar r19, char r20, java.util.Locale r21) throws java.io.IOException {
            /*
            // Method dump skipped, instructions count: 880
            */
            throw new UnsupportedOperationException("Method not decompiled: java.util.Formatter.FormatSpecifier.print(java.lang.StringBuilder, java.util.Calendar, char, java.util.Locale):java.lang.Appendable");
        }

        private void print(TemporalAccessor t, char c2, Locale l) throws IOException {
            StringBuilder sb = new StringBuilder();
            print(sb, t, c2, l);
            String s = justify(sb.toString());
            if (this.f.contains(Flags.UPPERCASE)) {
                s = s.toUpperCase();
            }
            Formatter.this.a.append(s);
        }

        /* JADX WARNING: Removed duplicated region for block: B:51:0x01bd A[Catch:{ DateTimeException -> 0x01ea }] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private java.lang.Appendable print(java.lang.StringBuilder r17, java.time.temporal.TemporalAccessor r18, char r19, java.util.Locale r20) throws java.io.IOException {
            /*
            // Method dump skipped, instructions count: 976
            */
            throw new UnsupportedOperationException("Method not decompiled: java.util.Formatter.FormatSpecifier.print(java.lang.StringBuilder, java.time.temporal.TemporalAccessor, char, java.util.Locale):java.lang.Appendable");
        }

        private void failMismatch(Flags f2, char c2) {
            throw new FormatFlagsConversionMismatchException(f2.toString(), c2);
        }

        private void failConversion(char c2, Object arg) {
            throw new IllegalFormatConversionException(c2, arg.getClass());
        }

        private char getZero(Locale l) {
            if (l == null || l.equals(Formatter.this.locale())) {
                return Formatter.this.zero;
            }
            return DecimalFormatSymbols.getInstance(l).getZeroDigit();
        }

        private StringBuilder localizedMagnitude(StringBuilder sb, long value, Flags f2, int width2, Locale l) {
            return localizedMagnitude(sb, Long.toString(value, 10).toCharArray(), f2, width2, l);
        }

        private StringBuilder localizedMagnitude(StringBuilder sb, char[] value, Flags f2, int width2, Locale l) {
            StringBuilder sb2;
            char[] cArr = value;
            if (sb == null) {
                sb2 = new StringBuilder();
            } else {
                sb2 = sb;
            }
            int begin = sb2.length();
            char zero = getZero(l);
            char grpSep = 0;
            int grpSize = -1;
            char decSep = 0;
            int len = cArr.length;
            int dot = len;
            int j = 0;
            while (true) {
                if (j >= len) {
                    break;
                } else if (cArr[j] == '.') {
                    dot = j;
                    break;
                } else {
                    j++;
                }
            }
            if (dot < len) {
                if (l == null || l.equals(Locale.US)) {
                    decSep = '.';
                } else {
                    decSep = DecimalFormatSymbols.getInstance(l).getDecimalSeparator();
                }
            }
            if (f2.contains(Flags.GROUP)) {
                if (l == null || l.equals(Locale.US)) {
                    grpSep = ',';
                    grpSize = 3;
                } else {
                    grpSep = DecimalFormatSymbols.getInstance(l).getGroupingSeparator();
                    DecimalFormat df = (DecimalFormat) NumberFormat.getIntegerInstance(l);
                    grpSize = df.getGroupingSize();
                    if (!df.isGroupingUsed() || df.getGroupingSize() == 0) {
                        grpSep = 0;
                    }
                }
            }
            int j2 = 0;
            while (j2 < len) {
                if (j2 == dot) {
                    sb2.append(decSep);
                    grpSep = 0;
                } else {
                    sb2.append((char) ((cArr[j2] - '0') + zero));
                    if (!(grpSep == 0 || j2 == dot - 1 || (dot - j2) % grpSize != 1)) {
                        sb2.append(grpSep);
                    }
                }
                j2++;
                cArr = value;
            }
            int len2 = sb2.length();
            if (width2 != -1 && f2.contains(Flags.ZERO_PAD)) {
                for (int k = 0; k < width2 - len2; k++) {
                    sb2.insert(begin, zero);
                }
            }
            return sb2;
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

        private Flags(int f) {
            this.flags = f;
        }

        public int valueOf() {
            return this.flags;
        }

        public boolean contains(Flags f) {
            return (this.flags & f.valueOf()) == f.valueOf();
        }

        public Flags dup() {
            return new Flags(this.flags);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private Flags add(Flags f) {
            this.flags |= f.valueOf();
            return this;
        }

        public Flags remove(Flags f) {
            this.flags &= ~f.valueOf();
            return this;
        }

        public static Flags parse(String s) {
            char[] ca = s.toCharArray();
            Flags f = new Flags(0);
            for (char c : ca) {
                Flags v = parse(c);
                if (!f.contains(v)) {
                    f.add(v);
                } else {
                    throw new DuplicateFormatFlagsException(v.toString());
                }
            }
            return f;
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

        public static String toString(Flags f) {
            return f.toString();
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
        static final char BOOLEAN = 'b';
        static final char BOOLEAN_UPPER = 'B';
        static final char CHARACTER = 'c';
        static final char CHARACTER_UPPER = 'C';
        static final char DATE_TIME = 't';
        static final char DATE_TIME_UPPER = 'T';
        static final char DECIMAL_FLOAT = 'f';
        static final char DECIMAL_INTEGER = 'd';
        static final char GENERAL = 'g';
        static final char GENERAL_UPPER = 'G';
        static final char HASHCODE = 'h';
        static final char HASHCODE_UPPER = 'H';
        static final char HEXADECIMAL_FLOAT = 'a';
        static final char HEXADECIMAL_FLOAT_UPPER = 'A';
        static final char HEXADECIMAL_INTEGER = 'x';
        static final char HEXADECIMAL_INTEGER_UPPER = 'X';
        static final char LINE_SEPARATOR = 'n';
        static final char OCTAL_INTEGER = 'o';
        static final char PERCENT_SIGN = '%';
        static final char SCIENTIFIC = 'e';
        static final char SCIENTIFIC_UPPER = 'E';
        static final char STRING = 's';
        static final char STRING_UPPER = 'S';

        private Conversion() {
        }

        static boolean isValid(char c) {
            return isGeneral(c) || isInteger(c) || isFloat(c) || isText(c) || c == 't' || isCharacter(c);
        }

        static boolean isGeneral(char c) {
            if (c == 'B' || c == 'H' || c == 'S' || c == 'b' || c == 'h' || c == 's') {
                return true;
            }
            return false;
        }

        static boolean isCharacter(char c) {
            if (c == 'C' || c == 'c') {
                return true;
            }
            return false;
        }

        static boolean isInteger(char c) {
            if (c == 'X' || c == 'd' || c == 'o' || c == 'x') {
                return true;
            }
            return false;
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

        static boolean isText(char c) {
            if (c == '%' || c == 'n') {
                return true;
            }
            return false;
        }
    }

    /* access modifiers changed from: private */
    public static class DateTime {
        static final char AM_PM = 'p';
        static final char CENTURY = 'C';
        static final char DATE = 'D';
        static final char DATE_TIME = 'c';
        static final char DAY_OF_MONTH = 'e';
        static final char DAY_OF_MONTH_0 = 'd';
        static final char DAY_OF_YEAR = 'j';
        static final char HOUR = 'l';
        static final char HOUR_0 = 'I';
        static final char HOUR_OF_DAY = 'k';
        static final char HOUR_OF_DAY_0 = 'H';
        static final char ISO_STANDARD_DATE = 'F';
        static final char MILLISECOND = 'L';
        static final char MILLISECOND_SINCE_EPOCH = 'Q';
        static final char MINUTE = 'M';
        static final char MONTH = 'm';
        static final char NAME_OF_DAY = 'A';
        static final char NAME_OF_DAY_ABBREV = 'a';
        static final char NAME_OF_MONTH = 'B';
        static final char NAME_OF_MONTH_ABBREV = 'b';
        static final char NAME_OF_MONTH_ABBREV_X = 'h';
        static final char NANOSECOND = 'N';
        static final char SECOND = 'S';
        static final char SECONDS_SINCE_EPOCH = 's';
        static final char TIME = 'T';
        static final char TIME_12_HOUR = 'r';
        static final char TIME_24_HOUR = 'R';
        static final char YEAR_2 = 'y';
        static final char YEAR_4 = 'Y';
        static final char ZONE = 'Z';
        static final char ZONE_NUMERIC = 'z';

        private DateTime() {
        }

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
}
