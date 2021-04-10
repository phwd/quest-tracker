package java.util;

import com.oculus.bugreporter.Constants;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.CharBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.IllegalCharsetNameException;
import java.nio.charset.UnsupportedCharsetException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import sun.misc.LRUCache;

public final class Scanner implements Iterator<String>, Closeable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String BOOLEAN_PATTERN = "true|false";
    private static final int BUFFER_SIZE = 1024;
    private static Pattern FIND_ANY_PATTERN = Pattern.compile("(?s).*");
    private static final String LINE_PATTERN = ".*(\r\n|[\n\r  ])|.+$";
    private static final String LINE_SEPARATOR_PATTERN = "\r\n|[\n\r  ]";
    private static Pattern NON_ASCII_DIGIT = Pattern.compile("[\\p{javaDigit}&&[^0-9]]");
    private static Pattern WHITESPACE_PATTERN = Pattern.compile("\\p{javaWhitespace}+");
    private static volatile Pattern boolPattern;
    private static volatile Pattern linePattern;
    private static volatile Pattern separatorPattern;
    private int SIMPLE_GROUP_INDEX;
    private CharBuffer buf;
    private boolean closed;
    private Pattern decimalPattern;
    private String decimalSeparator;
    private int defaultRadix;
    private Pattern delimPattern;
    private String digits;
    private Pattern floatPattern;
    private String groupSeparator;
    private Pattern hasNextPattern;
    private int hasNextPosition;
    private String hasNextResult;
    private String infinityString;
    private Pattern integerPattern;
    private IOException lastException;
    private Locale locale;
    private boolean matchValid;
    private Matcher matcher;
    private String nanString;
    private boolean needInput;
    private String negativePrefix;
    private String negativeSuffix;
    private String non0Digit;
    private LRUCache<String, Pattern> patternCache;
    private int position;
    private String positivePrefix;
    private String positiveSuffix;
    private int radix;
    private int savedScannerPosition;
    private boolean skipped;
    private Readable source;
    private boolean sourceClosed;
    private Object typeCache;

    private static Pattern boolPattern() {
        Pattern bp = boolPattern;
        if (bp != null) {
            return bp;
        }
        Pattern bp2 = Pattern.compile(BOOLEAN_PATTERN, 2);
        boolPattern = bp2;
        return bp2;
    }

    private String buildIntegerPatternString() {
        String digit = "((?i)[" + this.digits.substring(0, this.radix) + "]|\\p{javaDigit})";
        String numeral = "((" + digit + "++)|" + ("(" + ("((?i)[" + this.digits.substring(1, this.radix) + "]|(" + this.non0Digit + "))") + digit + "?" + digit + "?(" + this.groupSeparator + digit + digit + digit + ")+)") + ")";
        return "(" + ("([-+]?(" + numeral + "))") + ")|(" + (this.positivePrefix + numeral + this.positiveSuffix) + ")|(" + (this.negativePrefix + numeral + this.negativeSuffix) + ")";
    }

    private Pattern integerPattern() {
        if (this.integerPattern == null) {
            this.integerPattern = this.patternCache.forName(buildIntegerPatternString());
        }
        return this.integerPattern;
    }

    private static Pattern separatorPattern() {
        Pattern sp = separatorPattern;
        if (sp != null) {
            return sp;
        }
        Pattern sp2 = Pattern.compile(LINE_SEPARATOR_PATTERN);
        separatorPattern = sp2;
        return sp2;
    }

    private static Pattern linePattern() {
        Pattern lp = linePattern;
        if (lp != null) {
            return lp;
        }
        Pattern lp2 = Pattern.compile(LINE_PATTERN);
        linePattern = lp2;
        return lp2;
    }

    private void buildFloatAndDecimalPattern() {
        String exponent = "([eE][+-]?" + "([0-9]|(\\p{javaDigit}))" + "+)?";
        String numeral = "((" + "([0-9]|(\\p{javaDigit}))" + "++)|" + ("(" + this.non0Digit + "([0-9]|(\\p{javaDigit}))" + "?" + "([0-9]|(\\p{javaDigit}))" + "?(" + this.groupSeparator + "([0-9]|(\\p{javaDigit}))" + "([0-9]|(\\p{javaDigit}))" + "([0-9]|(\\p{javaDigit}))" + ")+)") + ")";
        String decimalNumeral = "(" + numeral + "|" + numeral + this.decimalSeparator + "([0-9]|(\\p{javaDigit}))" + "*+|" + this.decimalSeparator + "([0-9]|(\\p{javaDigit}))" + "++)";
        String nonNumber = "(NaN|" + this.nanString + "|Infinity|" + this.infinityString + ")";
        String decimal = "(([-+]?" + decimalNumeral + exponent + ")|" + ("(" + this.positivePrefix + decimalNumeral + this.positiveSuffix + exponent + ")") + "|" + ("(" + this.negativePrefix + decimalNumeral + this.negativeSuffix + exponent + ")") + ")";
        this.floatPattern = Pattern.compile(decimal + "|" + "[-+]?0[xX][0-9a-fA-F]*\\.[0-9a-fA-F]+([pP][-+]?[0-9]+)?" + "|" + ("(([-+]?" + nonNumber + ")|" + ("(" + this.positivePrefix + nonNumber + this.positiveSuffix + ")") + "|" + ("(" + this.negativePrefix + nonNumber + this.negativeSuffix + ")") + ")"));
        this.decimalPattern = Pattern.compile(decimal);
    }

    private Pattern floatPattern() {
        if (this.floatPattern == null) {
            buildFloatAndDecimalPattern();
        }
        return this.floatPattern;
    }

    private Pattern decimalPattern() {
        if (this.decimalPattern == null) {
            buildFloatAndDecimalPattern();
        }
        return this.decimalPattern;
    }

    private Scanner(Readable source2, Pattern pattern) {
        this.sourceClosed = false;
        this.needInput = false;
        this.skipped = false;
        this.savedScannerPosition = -1;
        this.typeCache = null;
        this.matchValid = false;
        this.closed = false;
        this.radix = 10;
        this.defaultRadix = 10;
        this.locale = null;
        this.patternCache = new LRUCache<String, Pattern>(7) {
            /* class java.util.Scanner.AnonymousClass1 */

            /* access modifiers changed from: protected */
            public Pattern create(String s) {
                return Pattern.compile(s);
            }

            /* access modifiers changed from: protected */
            public boolean hasName(Pattern p, String s) {
                return p.pattern().equals(s);
            }
        };
        this.groupSeparator = "\\,";
        this.decimalSeparator = "\\.";
        this.nanString = "NaN";
        this.infinityString = "Infinity";
        this.positivePrefix = "";
        this.negativePrefix = "\\-";
        this.positiveSuffix = "";
        this.negativeSuffix = "";
        this.digits = "0123456789abcdefghijklmnopqrstuvwxyz";
        this.non0Digit = "[\\p{javaDigit}&&[^0]]";
        this.SIMPLE_GROUP_INDEX = 5;
        this.source = source2;
        this.delimPattern = pattern;
        this.buf = CharBuffer.allocate(1024);
        this.buf.limit(0);
        this.matcher = this.delimPattern.matcher(this.buf);
        this.matcher.useTransparentBounds(true);
        this.matcher.useAnchoringBounds(false);
        useLocale(Locale.getDefault(Locale.Category.FORMAT));
    }

    public Scanner(Readable source2) {
        this((Readable) Objects.requireNonNull(source2, Constants.EXTRA_SOURCE), WHITESPACE_PATTERN);
    }

    public Scanner(InputStream source2) {
        this(new InputStreamReader(source2), WHITESPACE_PATTERN);
    }

    public Scanner(InputStream source2, String charsetName) {
        this(makeReadable((InputStream) Objects.requireNonNull(source2, Constants.EXTRA_SOURCE), toCharset(charsetName)), WHITESPACE_PATTERN);
    }

    private static Charset toCharset(String csn) {
        Objects.requireNonNull(csn, "charsetName");
        try {
            return Charset.forName(csn);
        } catch (IllegalCharsetNameException | UnsupportedCharsetException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private static Readable makeReadable(InputStream source2, Charset charset) {
        return new InputStreamReader(source2, charset);
    }

    public Scanner(File source2) throws FileNotFoundException {
        this(new FileInputStream(source2).getChannel());
    }

    public Scanner(File source2, String charsetName) throws FileNotFoundException {
        this((File) Objects.requireNonNull(source2), toDecoder(charsetName));
    }

    private Scanner(File source2, CharsetDecoder dec) throws FileNotFoundException {
        this(makeReadable(new FileInputStream(source2).getChannel(), dec));
    }

    private static CharsetDecoder toDecoder(String charsetName) {
        if (charsetName != null) {
            try {
                return Charset.forName(charsetName).newDecoder();
            } catch (IllegalCharsetNameException | UnsupportedCharsetException e) {
                throw new IllegalArgumentException(charsetName);
            }
        } else {
            throw new IllegalArgumentException("charsetName == null");
        }
    }

    private static Readable makeReadable(ReadableByteChannel source2, CharsetDecoder dec) {
        return Channels.newReader(source2, dec, -1);
    }

    public Scanner(Path source2) throws IOException {
        this(Files.newInputStream(source2, new OpenOption[0]));
    }

    public Scanner(Path source2, String charsetName) throws IOException {
        this((Path) Objects.requireNonNull(source2), toCharset(charsetName));
    }

    private Scanner(Path source2, Charset charset) throws IOException {
        this(makeReadable(Files.newInputStream(source2, new OpenOption[0]), charset));
    }

    public Scanner(String source2) {
        this(new StringReader(source2), WHITESPACE_PATTERN);
    }

    public Scanner(ReadableByteChannel source2) {
        this(makeReadable((ReadableByteChannel) Objects.requireNonNull(source2, Constants.EXTRA_SOURCE)), WHITESPACE_PATTERN);
    }

    private static Readable makeReadable(ReadableByteChannel source2) {
        return makeReadable(source2, Charset.defaultCharset().newDecoder());
    }

    public Scanner(ReadableByteChannel source2, String charsetName) {
        this(makeReadable((ReadableByteChannel) Objects.requireNonNull(source2, Constants.EXTRA_SOURCE), toDecoder(charsetName)), WHITESPACE_PATTERN);
    }

    private void saveState() {
        this.savedScannerPosition = this.position;
    }

    private void revertState() {
        this.position = this.savedScannerPosition;
        this.savedScannerPosition = -1;
        this.skipped = false;
    }

    private boolean revertState(boolean b) {
        this.position = this.savedScannerPosition;
        this.savedScannerPosition = -1;
        this.skipped = false;
        return b;
    }

    private void cacheResult() {
        this.hasNextResult = this.matcher.group();
        this.hasNextPosition = this.matcher.end();
        this.hasNextPattern = this.matcher.pattern();
    }

    private void cacheResult(String result) {
        this.hasNextResult = result;
        this.hasNextPosition = this.matcher.end();
        this.hasNextPattern = this.matcher.pattern();
    }

    private void clearCaches() {
        this.hasNextPattern = null;
        this.typeCache = null;
    }

    private String getCachedResult() {
        this.position = this.hasNextPosition;
        this.hasNextPattern = null;
        this.typeCache = null;
        return this.hasNextResult;
    }

    private void useTypeCache() {
        if (!this.closed) {
            this.position = this.hasNextPosition;
            this.hasNextPattern = null;
            this.typeCache = null;
            return;
        }
        throw new IllegalStateException("Scanner closed");
    }

    private void readInput() {
        int n;
        if (this.buf.limit() == this.buf.capacity()) {
            makeSpace();
        }
        int p = this.buf.position();
        CharBuffer charBuffer = this.buf;
        charBuffer.position(charBuffer.limit());
        CharBuffer charBuffer2 = this.buf;
        charBuffer2.limit(charBuffer2.capacity());
        try {
            n = this.source.read(this.buf);
        } catch (IOException ioe) {
            this.lastException = ioe;
            n = -1;
        }
        if (n == -1) {
            this.sourceClosed = true;
            this.needInput = false;
        }
        if (n > 0) {
            this.needInput = false;
        }
        CharBuffer charBuffer3 = this.buf;
        charBuffer3.limit(charBuffer3.position());
        this.buf.position(p);
        this.matcher.reset(this.buf);
    }

    private boolean makeSpace() {
        clearCaches();
        int offset = this.savedScannerPosition;
        if (offset == -1) {
            offset = this.position;
        }
        this.buf.position(offset);
        if (offset > 0) {
            this.buf.compact();
            translateSavedIndexes(offset);
            this.position -= offset;
            this.buf.flip();
            return true;
        }
        CharBuffer newBuf = CharBuffer.allocate(this.buf.capacity() * 2);
        newBuf.put(this.buf);
        newBuf.flip();
        translateSavedIndexes(offset);
        this.position -= offset;
        this.buf = newBuf;
        this.matcher.reset(this.buf);
        return true;
    }

    private void translateSavedIndexes(int offset) {
        int i = this.savedScannerPosition;
        if (i != -1) {
            this.savedScannerPosition = i - offset;
        }
    }

    private void throwFor() {
        this.skipped = false;
        if (!this.sourceClosed || this.position != this.buf.limit()) {
            throw new InputMismatchException();
        }
        throw new NoSuchElementException();
    }

    private boolean hasTokenInBuffer() {
        this.matchValid = false;
        this.matcher.usePattern(this.delimPattern);
        this.matcher.region(this.position, this.buf.limit());
        if (this.matcher.lookingAt()) {
            this.position = this.matcher.end();
        }
        if (this.position == this.buf.limit()) {
            return false;
        }
        return true;
    }

    private String getCompleteTokenInBuffer(Pattern pattern) {
        this.matchValid = false;
        this.matcher.usePattern(this.delimPattern);
        if (!this.skipped) {
            this.matcher.region(this.position, this.buf.limit());
            if (this.matcher.lookingAt()) {
                if (!this.matcher.hitEnd() || this.sourceClosed) {
                    this.skipped = true;
                    this.position = this.matcher.end();
                } else {
                    this.needInput = true;
                    return null;
                }
            }
        }
        if (this.position != this.buf.limit()) {
            this.matcher.region(this.position, this.buf.limit());
            boolean foundNextDelim = this.matcher.find();
            if (foundNextDelim && this.matcher.end() == this.position) {
                foundNextDelim = this.matcher.find();
            }
            if (foundNextDelim) {
                if (!this.matcher.requireEnd() || this.sourceClosed) {
                    int tokenEnd = this.matcher.start();
                    if (pattern == null) {
                        pattern = FIND_ANY_PATTERN;
                    }
                    this.matcher.usePattern(pattern);
                    this.matcher.region(this.position, tokenEnd);
                    if (!this.matcher.matches()) {
                        return null;
                    }
                    String s = this.matcher.group();
                    this.position = this.matcher.end();
                    return s;
                }
                this.needInput = true;
                return null;
            } else if (this.sourceClosed) {
                if (pattern == null) {
                    pattern = FIND_ANY_PATTERN;
                }
                this.matcher.usePattern(pattern);
                this.matcher.region(this.position, this.buf.limit());
                if (!this.matcher.matches()) {
                    return null;
                }
                String s2 = this.matcher.group();
                this.position = this.matcher.end();
                return s2;
            } else {
                this.needInput = true;
                return null;
            }
        } else if (this.sourceClosed) {
            return null;
        } else {
            this.needInput = true;
            return null;
        }
    }

    private String findPatternInBuffer(Pattern pattern, int horizon) {
        this.matchValid = false;
        this.matcher.usePattern(pattern);
        int bufferLimit = this.buf.limit();
        int horizonLimit = -1;
        int searchLimit = bufferLimit;
        if (horizon > 0 && (horizonLimit = this.position + horizon) < bufferLimit) {
            searchLimit = horizonLimit;
        }
        this.matcher.region(this.position, searchLimit);
        if (this.matcher.find()) {
            if (this.matcher.hitEnd() && !this.sourceClosed) {
                if (searchLimit != horizonLimit) {
                    this.needInput = true;
                    return null;
                } else if (searchLimit == horizonLimit && this.matcher.requireEnd()) {
                    this.needInput = true;
                    return null;
                }
            }
            this.position = this.matcher.end();
            return this.matcher.group();
        } else if (this.sourceClosed) {
            return null;
        } else {
            if (horizon == 0 || searchLimit != horizonLimit) {
                this.needInput = true;
            }
            return null;
        }
    }

    private String matchPatternInBuffer(Pattern pattern) {
        this.matchValid = false;
        this.matcher.usePattern(pattern);
        this.matcher.region(this.position, this.buf.limit());
        if (this.matcher.lookingAt()) {
            if (!this.matcher.hitEnd() || this.sourceClosed) {
                this.position = this.matcher.end();
                return this.matcher.group();
            }
            this.needInput = true;
            return null;
        } else if (this.sourceClosed) {
            return null;
        } else {
            this.needInput = true;
            return null;
        }
    }

    private void ensureOpen() {
        if (this.closed) {
            throw new IllegalStateException("Scanner closed");
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (!this.closed) {
            Readable readable = this.source;
            if (readable instanceof Closeable) {
                try {
                    ((Closeable) readable).close();
                } catch (IOException ioe) {
                    this.lastException = ioe;
                }
            }
            this.sourceClosed = true;
            this.source = null;
            this.closed = true;
        }
    }

    public IOException ioException() {
        return this.lastException;
    }

    public Pattern delimiter() {
        return this.delimPattern;
    }

    public Scanner useDelimiter(Pattern pattern) {
        this.delimPattern = pattern;
        return this;
    }

    public Scanner useDelimiter(String pattern) {
        this.delimPattern = this.patternCache.forName(pattern);
        return this;
    }

    public Locale locale() {
        return this.locale;
    }

    public Scanner useLocale(Locale locale2) {
        if (locale2.equals(this.locale)) {
            return this;
        }
        this.locale = locale2;
        DecimalFormat df = (DecimalFormat) NumberFormat.getNumberInstance(locale2);
        DecimalFormatSymbols dfs = DecimalFormatSymbols.getInstance(locale2);
        this.groupSeparator = "\\" + dfs.getGroupingSeparator();
        this.decimalSeparator = "\\" + dfs.getDecimalSeparator();
        this.nanString = "\\Q" + dfs.getNaN() + "\\E";
        this.infinityString = "\\Q" + dfs.getInfinity() + "\\E";
        this.positivePrefix = df.getPositivePrefix();
        if (this.positivePrefix.length() > 0) {
            this.positivePrefix = "\\Q" + this.positivePrefix + "\\E";
        }
        this.negativePrefix = df.getNegativePrefix();
        if (this.negativePrefix.length() > 0) {
            this.negativePrefix = "\\Q" + this.negativePrefix + "\\E";
        }
        this.positiveSuffix = df.getPositiveSuffix();
        if (this.positiveSuffix.length() > 0) {
            this.positiveSuffix = "\\Q" + this.positiveSuffix + "\\E";
        }
        this.negativeSuffix = df.getNegativeSuffix();
        if (this.negativeSuffix.length() > 0) {
            this.negativeSuffix = "\\Q" + this.negativeSuffix + "\\E";
        }
        this.integerPattern = null;
        this.floatPattern = null;
        return this;
    }

    public int radix() {
        return this.defaultRadix;
    }

    public Scanner useRadix(int radix2) {
        if (radix2 < 2 || radix2 > 36) {
            throw new IllegalArgumentException("radix:" + radix2);
        } else if (this.defaultRadix == radix2) {
            return this;
        } else {
            this.defaultRadix = radix2;
            this.integerPattern = null;
            return this;
        }
    }

    private void setRadix(int radix2) {
        if (radix2 > 36) {
            throw new IllegalArgumentException("radix == " + radix2);
        } else if (this.radix != radix2) {
            this.integerPattern = null;
            this.radix = radix2;
        }
    }

    public MatchResult match() {
        if (this.matchValid) {
            return this.matcher.toMatchResult();
        }
        throw new IllegalStateException("No match result available");
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("java.util.Scanner");
        sb.append("[delimiters=" + ((Object) this.delimPattern) + "]");
        sb.append("[position=" + this.position + "]");
        sb.append("[match valid=" + this.matchValid + "]");
        sb.append("[need input=" + this.needInput + "]");
        sb.append("[source closed=" + this.sourceClosed + "]");
        sb.append("[skipped=" + this.skipped + "]");
        sb.append("[group separator=" + this.groupSeparator + "]");
        sb.append("[decimal separator=" + this.decimalSeparator + "]");
        sb.append("[positive prefix=" + this.positivePrefix + "]");
        sb.append("[negative prefix=" + this.negativePrefix + "]");
        sb.append("[positive suffix=" + this.positiveSuffix + "]");
        sb.append("[negative suffix=" + this.negativeSuffix + "]");
        sb.append("[NaN string=" + this.nanString + "]");
        sb.append("[infinity string=" + this.infinityString + "]");
        return sb.toString();
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        ensureOpen();
        saveState();
        while (!this.sourceClosed) {
            if (hasTokenInBuffer()) {
                return revertState(true);
            }
            readInput();
        }
        return revertState(hasTokenInBuffer());
    }

    @Override // java.util.Iterator
    public String next() {
        ensureOpen();
        clearCaches();
        while (true) {
            String token = getCompleteTokenInBuffer(null);
            if (token != null) {
                this.matchValid = true;
                this.skipped = false;
                return token;
            } else if (this.needInput) {
                readInput();
            } else {
                throwFor();
            }
        }
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException();
    }

    public boolean hasNext(String pattern) {
        return hasNext(this.patternCache.forName(pattern));
    }

    public String next(String pattern) {
        return next(this.patternCache.forName(pattern));
    }

    public boolean hasNext(Pattern pattern) {
        ensureOpen();
        if (pattern != null) {
            this.hasNextPattern = null;
            saveState();
            while (getCompleteTokenInBuffer(pattern) == null) {
                if (!this.needInput) {
                    return revertState(false);
                }
                readInput();
            }
            this.matchValid = true;
            cacheResult();
            return revertState(true);
        }
        throw new NullPointerException();
    }

    public String next(Pattern pattern) {
        ensureOpen();
        if (pattern == null) {
            throw new NullPointerException();
        } else if (this.hasNextPattern == pattern) {
            return getCachedResult();
        } else {
            clearCaches();
            while (true) {
                String token = getCompleteTokenInBuffer(pattern);
                if (token != null) {
                    this.matchValid = true;
                    this.skipped = false;
                    return token;
                } else if (this.needInput) {
                    readInput();
                } else {
                    throwFor();
                }
            }
        }
    }

    public boolean hasNextLine() {
        saveState();
        String result = findWithinHorizon(linePattern(), 0);
        if (result != null) {
            String lineSep = match().group(1);
            if (lineSep != null) {
                result = result.substring(0, result.length() - lineSep.length());
                cacheResult(result);
            } else {
                cacheResult();
            }
        }
        revertState();
        if (result != null) {
            return true;
        }
        return false;
    }

    public String nextLine() {
        if (this.hasNextPattern == linePattern()) {
            return getCachedResult();
        }
        clearCaches();
        String result = findWithinHorizon(linePattern, 0);
        if (result != null) {
            String lineSep = match().group(1);
            if (lineSep != null) {
                result = result.substring(0, result.length() - lineSep.length());
            }
            if (result != null) {
                return result;
            }
            throw new NoSuchElementException();
        }
        throw new NoSuchElementException("No line found");
    }

    public String findInLine(String pattern) {
        return findInLine(this.patternCache.forName(pattern));
    }

    public String findInLine(Pattern pattern) {
        int endPosition;
        ensureOpen();
        if (pattern != null) {
            clearCaches();
            saveState();
            while (true) {
                if (findPatternInBuffer(separatorPattern(), 0) == null) {
                    if (!this.needInput) {
                        endPosition = this.buf.limit();
                        break;
                    }
                    readInput();
                } else {
                    endPosition = this.matcher.start();
                    break;
                }
            }
            revertState();
            int horizonForLine = endPosition - this.position;
            if (horizonForLine == 0) {
                return null;
            }
            return findWithinHorizon(pattern, horizonForLine);
        }
        throw new NullPointerException();
    }

    public String findWithinHorizon(String pattern, int horizon) {
        return findWithinHorizon(this.patternCache.forName(pattern), horizon);
    }

    public String findWithinHorizon(Pattern pattern, int horizon) {
        ensureOpen();
        if (pattern == null) {
            throw new NullPointerException();
        } else if (horizon >= 0) {
            clearCaches();
            while (true) {
                String token = findPatternInBuffer(pattern, horizon);
                if (token != null) {
                    this.matchValid = true;
                    return token;
                } else if (!this.needInput) {
                    return null;
                } else {
                    readInput();
                }
            }
        } else {
            throw new IllegalArgumentException("horizon < 0");
        }
    }

    public Scanner skip(Pattern pattern) {
        ensureOpen();
        if (pattern != null) {
            clearCaches();
            while (matchPatternInBuffer(pattern) == null) {
                if (this.needInput) {
                    readInput();
                } else {
                    throw new NoSuchElementException();
                }
            }
            this.matchValid = true;
            this.position = this.matcher.end();
            return this;
        }
        throw new NullPointerException();
    }

    public Scanner skip(String pattern) {
        return skip(this.patternCache.forName(pattern));
    }

    public boolean hasNextBoolean() {
        return hasNext(boolPattern());
    }

    public boolean nextBoolean() {
        clearCaches();
        return Boolean.parseBoolean(next(boolPattern()));
    }

    public boolean hasNextByte() {
        return hasNextByte(this.defaultRadix);
    }

    public boolean hasNextByte(int radix2) {
        String s;
        setRadix(radix2);
        boolean result = hasNext(integerPattern());
        if (!result) {
            return result;
        }
        try {
            if (this.matcher.group(this.SIMPLE_GROUP_INDEX) == null) {
                s = processIntegerToken(this.hasNextResult);
            } else {
                s = this.hasNextResult;
            }
            this.typeCache = Byte.valueOf(Byte.parseByte(s, radix2));
            return result;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public byte nextByte() {
        return nextByte(this.defaultRadix);
    }

    public byte nextByte(int radix2) {
        Object obj = this.typeCache;
        if (obj == null || !(obj instanceof Byte) || this.radix != radix2) {
            setRadix(radix2);
            clearCaches();
            try {
                String s = next(integerPattern());
                if (this.matcher.group(this.SIMPLE_GROUP_INDEX) == null) {
                    s = processIntegerToken(s);
                }
                return Byte.parseByte(s, radix2);
            } catch (NumberFormatException nfe) {
                this.position = this.matcher.start();
                throw new InputMismatchException(nfe.getMessage());
            }
        } else {
            byte val = ((Byte) obj).byteValue();
            useTypeCache();
            return val;
        }
    }

    public boolean hasNextShort() {
        return hasNextShort(this.defaultRadix);
    }

    public boolean hasNextShort(int radix2) {
        String s;
        setRadix(radix2);
        boolean result = hasNext(integerPattern());
        if (!result) {
            return result;
        }
        try {
            if (this.matcher.group(this.SIMPLE_GROUP_INDEX) == null) {
                s = processIntegerToken(this.hasNextResult);
            } else {
                s = this.hasNextResult;
            }
            this.typeCache = Short.valueOf(Short.parseShort(s, radix2));
            return result;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public short nextShort() {
        return nextShort(this.defaultRadix);
    }

    public short nextShort(int radix2) {
        Object obj = this.typeCache;
        if (obj == null || !(obj instanceof Short) || this.radix != radix2) {
            setRadix(radix2);
            clearCaches();
            try {
                String s = next(integerPattern());
                if (this.matcher.group(this.SIMPLE_GROUP_INDEX) == null) {
                    s = processIntegerToken(s);
                }
                return Short.parseShort(s, radix2);
            } catch (NumberFormatException nfe) {
                this.position = this.matcher.start();
                throw new InputMismatchException(nfe.getMessage());
            }
        } else {
            short val = ((Short) obj).shortValue();
            useTypeCache();
            return val;
        }
    }

    public boolean hasNextInt() {
        return hasNextInt(this.defaultRadix);
    }

    public boolean hasNextInt(int radix2) {
        String s;
        setRadix(radix2);
        boolean result = hasNext(integerPattern());
        if (!result) {
            return result;
        }
        try {
            if (this.matcher.group(this.SIMPLE_GROUP_INDEX) == null) {
                s = processIntegerToken(this.hasNextResult);
            } else {
                s = this.hasNextResult;
            }
            this.typeCache = Integer.valueOf(Integer.parseInt(s, radix2));
            return result;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private String processIntegerToken(String token) {
        String result = token.replaceAll("" + this.groupSeparator, "");
        boolean isNegative = false;
        int preLen = this.negativePrefix.length();
        if (preLen > 0 && result.startsWith(this.negativePrefix)) {
            isNegative = true;
            result = result.substring(preLen);
        }
        int sufLen = this.negativeSuffix.length();
        if (sufLen > 0 && result.endsWith(this.negativeSuffix)) {
            isNegative = true;
            result = result.substring(result.length() - sufLen, result.length());
        }
        if (!isNegative) {
            return result;
        }
        return "-" + result;
    }

    public int nextInt() {
        return nextInt(this.defaultRadix);
    }

    public int nextInt(int radix2) {
        Object obj = this.typeCache;
        if (obj == null || !(obj instanceof Integer) || this.radix != radix2) {
            setRadix(radix2);
            clearCaches();
            try {
                String s = next(integerPattern());
                if (this.matcher.group(this.SIMPLE_GROUP_INDEX) == null) {
                    s = processIntegerToken(s);
                }
                return Integer.parseInt(s, radix2);
            } catch (NumberFormatException nfe) {
                this.position = this.matcher.start();
                throw new InputMismatchException(nfe.getMessage());
            }
        } else {
            int val = ((Integer) obj).intValue();
            useTypeCache();
            return val;
        }
    }

    public boolean hasNextLong() {
        return hasNextLong(this.defaultRadix);
    }

    public boolean hasNextLong(int radix2) {
        String s;
        setRadix(radix2);
        boolean result = hasNext(integerPattern());
        if (!result) {
            return result;
        }
        try {
            if (this.matcher.group(this.SIMPLE_GROUP_INDEX) == null) {
                s = processIntegerToken(this.hasNextResult);
            } else {
                s = this.hasNextResult;
            }
            this.typeCache = Long.valueOf(Long.parseLong(s, radix2));
            return result;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public long nextLong() {
        return nextLong(this.defaultRadix);
    }

    public long nextLong(int radix2) {
        Object obj = this.typeCache;
        if (obj == null || !(obj instanceof Long) || this.radix != radix2) {
            setRadix(radix2);
            clearCaches();
            try {
                String s = next(integerPattern());
                if (this.matcher.group(this.SIMPLE_GROUP_INDEX) == null) {
                    s = processIntegerToken(s);
                }
                return Long.parseLong(s, radix2);
            } catch (NumberFormatException nfe) {
                this.position = this.matcher.start();
                throw new InputMismatchException(nfe.getMessage());
            }
        } else {
            long val = ((Long) obj).longValue();
            useTypeCache();
            return val;
        }
    }

    private String processFloatToken(String token) {
        String result = token.replaceAll(this.groupSeparator, "");
        if (!this.decimalSeparator.equals("\\.")) {
            result = result.replaceAll(this.decimalSeparator, ".");
        }
        boolean isNegative = false;
        int preLen = this.negativePrefix.length();
        if (preLen > 0 && result.startsWith(this.negativePrefix)) {
            isNegative = true;
            result = result.substring(preLen);
        }
        int sufLen = this.negativeSuffix.length();
        if (sufLen > 0 && result.endsWith(this.negativeSuffix)) {
            isNegative = true;
            result = result.substring(result.length() - sufLen, result.length());
        }
        if (result.equals(this.nanString)) {
            result = "NaN";
        }
        if (result.equals(this.infinityString)) {
            result = "Infinity";
        }
        if (result.equals("∞")) {
            result = "Infinity";
        }
        if (isNegative) {
            result = "-" + result;
        }
        if (!NON_ASCII_DIGIT.matcher(result).find()) {
            return result;
        }
        StringBuilder inASCII = new StringBuilder();
        for (int i = 0; i < result.length(); i++) {
            char nextChar = result.charAt(i);
            if (Character.isDigit(nextChar)) {
                int d = Character.digit(nextChar, 10);
                if (d != -1) {
                    inASCII.append(d);
                } else {
                    inASCII.append(nextChar);
                }
            } else {
                inASCII.append(nextChar);
            }
        }
        return inASCII.toString();
    }

    public boolean hasNextFloat() {
        setRadix(10);
        boolean result = hasNext(floatPattern());
        if (!result) {
            return result;
        }
        try {
            this.typeCache = Float.valueOf(Float.parseFloat(processFloatToken(this.hasNextResult)));
            return result;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public float nextFloat() {
        Object obj = this.typeCache;
        if (obj == null || !(obj instanceof Float)) {
            setRadix(10);
            clearCaches();
            try {
                return Float.parseFloat(processFloatToken(next(floatPattern())));
            } catch (NumberFormatException nfe) {
                this.position = this.matcher.start();
                throw new InputMismatchException(nfe.getMessage());
            }
        } else {
            float val = ((Float) obj).floatValue();
            useTypeCache();
            return val;
        }
    }

    public boolean hasNextDouble() {
        setRadix(10);
        boolean result = hasNext(floatPattern());
        if (!result) {
            return result;
        }
        try {
            this.typeCache = Double.valueOf(Double.parseDouble(processFloatToken(this.hasNextResult)));
            return result;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public double nextDouble() {
        Object obj = this.typeCache;
        if (obj == null || !(obj instanceof Double)) {
            setRadix(10);
            clearCaches();
            try {
                return Double.parseDouble(processFloatToken(next(floatPattern())));
            } catch (NumberFormatException nfe) {
                this.position = this.matcher.start();
                throw new InputMismatchException(nfe.getMessage());
            }
        } else {
            double val = ((Double) obj).doubleValue();
            useTypeCache();
            return val;
        }
    }

    public boolean hasNextBigInteger() {
        return hasNextBigInteger(this.defaultRadix);
    }

    public boolean hasNextBigInteger(int radix2) {
        String s;
        setRadix(radix2);
        boolean result = hasNext(integerPattern());
        if (!result) {
            return result;
        }
        try {
            if (this.matcher.group(this.SIMPLE_GROUP_INDEX) == null) {
                s = processIntegerToken(this.hasNextResult);
            } else {
                s = this.hasNextResult;
            }
            this.typeCache = new BigInteger(s, radix2);
            return result;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public BigInteger nextBigInteger() {
        return nextBigInteger(this.defaultRadix);
    }

    public BigInteger nextBigInteger(int radix2) {
        Object obj = this.typeCache;
        if (obj == null || !(obj instanceof BigInteger) || this.radix != radix2) {
            setRadix(radix2);
            clearCaches();
            try {
                String s = next(integerPattern());
                if (this.matcher.group(this.SIMPLE_GROUP_INDEX) == null) {
                    s = processIntegerToken(s);
                }
                return new BigInteger(s, radix2);
            } catch (NumberFormatException nfe) {
                this.position = this.matcher.start();
                throw new InputMismatchException(nfe.getMessage());
            }
        } else {
            BigInteger val = (BigInteger) obj;
            useTypeCache();
            return val;
        }
    }

    public boolean hasNextBigDecimal() {
        setRadix(10);
        boolean result = hasNext(decimalPattern());
        if (!result) {
            return result;
        }
        try {
            this.typeCache = new BigDecimal(processFloatToken(this.hasNextResult));
            return result;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public BigDecimal nextBigDecimal() {
        Object obj = this.typeCache;
        if (obj == null || !(obj instanceof BigDecimal)) {
            setRadix(10);
            clearCaches();
            try {
                return new BigDecimal(processFloatToken(next(decimalPattern())));
            } catch (NumberFormatException nfe) {
                this.position = this.matcher.start();
                throw new InputMismatchException(nfe.getMessage());
            }
        } else {
            BigDecimal val = (BigDecimal) obj;
            useTypeCache();
            return val;
        }
    }

    public Scanner reset() {
        this.delimPattern = WHITESPACE_PATTERN;
        useLocale(Locale.getDefault(Locale.Category.FORMAT));
        useRadix(10);
        clearCaches();
        return this;
    }
}
