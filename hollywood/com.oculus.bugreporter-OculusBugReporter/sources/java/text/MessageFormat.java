package java.text;

import android.icu.impl.PatternTokenizer;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.text.Format;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import sun.security.x509.InvalidityDateExtension;

public class MessageFormat extends Format {
    private static final int[] DATE_TIME_MODIFIERS = {2, 3, 2, 1, 0};
    private static final String[] DATE_TIME_MODIFIER_KEYWORDS = {"", "short", "medium", "long", "full"};
    private static final int INITIAL_FORMATS = 10;
    private static final int MODIFIER_CURRENCY = 1;
    private static final int MODIFIER_DEFAULT = 0;
    private static final int MODIFIER_FULL = 4;
    private static final int MODIFIER_INTEGER = 3;
    private static final int MODIFIER_LONG = 3;
    private static final int MODIFIER_MEDIUM = 2;
    private static final int MODIFIER_PERCENT = 2;
    private static final int MODIFIER_SHORT = 1;
    private static final String[] NUMBER_MODIFIER_KEYWORDS = {"", "currency", "percent", "integer"};
    private static final int SEG_INDEX = 1;
    private static final int SEG_MODIFIER = 3;
    private static final int SEG_RAW = 0;
    private static final int SEG_TYPE = 2;
    private static final int TYPE_CHOICE = 4;
    private static final int TYPE_DATE = 2;
    private static final String[] TYPE_KEYWORDS = {"", "number", InvalidityDateExtension.DATE, "time", "choice"};
    private static final int TYPE_NULL = 0;
    private static final int TYPE_NUMBER = 1;
    private static final int TYPE_TIME = 3;
    private static final long serialVersionUID = 6479157306784022952L;
    private int[] argumentNumbers;
    private Format[] formats;
    private Locale locale;
    private int maxOffset;
    private int[] offsets;
    private String pattern;

    public MessageFormat(String pattern2) {
        this.pattern = "";
        this.formats = new Format[10];
        this.offsets = new int[10];
        this.argumentNumbers = new int[10];
        this.maxOffset = -1;
        this.locale = Locale.getDefault(Locale.Category.FORMAT);
        applyPattern(pattern2);
    }

    public MessageFormat(String pattern2, Locale locale2) {
        this.pattern = "";
        this.formats = new Format[10];
        this.offsets = new int[10];
        this.argumentNumbers = new int[10];
        this.maxOffset = -1;
        this.locale = locale2;
        applyPattern(pattern2);
    }

    public void setLocale(Locale locale2) {
        this.locale = locale2;
    }

    public Locale getLocale() {
        return this.locale;
    }

    public void applyPattern(String pattern2) {
        StringBuilder[] segments = new StringBuilder[4];
        segments[0] = new StringBuilder();
        int part = 0;
        int formatNumber = 0;
        boolean inQuote = false;
        int braceStack = 0;
        this.maxOffset = -1;
        int i = 0;
        while (i < pattern2.length()) {
            char ch = pattern2.charAt(i);
            if (part == 0) {
                if (ch == '\'') {
                    if (i + 1 >= pattern2.length() || pattern2.charAt(i + 1) != '\'') {
                        inQuote = !inQuote;
                    } else {
                        segments[part].append(ch);
                        i++;
                    }
                } else if (ch != '{' || inQuote) {
                    segments[part].append(ch);
                } else {
                    part = 1;
                    if (segments[1] == null) {
                        segments[1] = new StringBuilder();
                    }
                }
            } else if (inQuote) {
                segments[part].append(ch);
                if (ch == '\'') {
                    inQuote = false;
                }
            } else if (ch != ' ') {
                if (ch == '\'') {
                    inQuote = true;
                } else if (ch != ',') {
                    if (ch == '{') {
                        braceStack++;
                        segments[part].append(ch);
                    } else if (ch == '}') {
                        if (braceStack == 0) {
                            part = 0;
                            makeFormat(i, formatNumber, segments);
                            formatNumber++;
                            segments[1] = null;
                            segments[2] = null;
                            segments[3] = null;
                        } else {
                            braceStack--;
                            segments[part].append(ch);
                        }
                    }
                } else if (part < 3) {
                    part++;
                    if (segments[part] == null) {
                        segments[part] = new StringBuilder();
                    }
                } else {
                    segments[part].append(ch);
                }
                segments[part].append(ch);
            } else if (part != 2 || segments[2].length() > 0) {
                segments[part].append(ch);
            }
            i++;
        }
        if (braceStack != 0 || part == 0) {
            this.pattern = segments[0].toString();
        } else {
            this.maxOffset = -1;
            throw new IllegalArgumentException("Unmatched braces in the pattern.");
        }
    }

    public String toPattern() {
        int lastOffset = 0;
        StringBuilder result = new StringBuilder();
        for (int i = 0; i <= this.maxOffset; i++) {
            copyAndFixQuotes(this.pattern, lastOffset, this.offsets[i], result);
            lastOffset = this.offsets[i];
            result.append('{');
            result.append(this.argumentNumbers[i]);
            Format fmt = this.formats[i];
            if (fmt != null) {
                if (fmt instanceof NumberFormat) {
                    if (fmt.equals(NumberFormat.getInstance(this.locale))) {
                        result.append(",number");
                    } else if (fmt.equals(NumberFormat.getCurrencyInstance(this.locale))) {
                        result.append(",number,currency");
                    } else if (fmt.equals(NumberFormat.getPercentInstance(this.locale))) {
                        result.append(",number,percent");
                    } else if (fmt.equals(NumberFormat.getIntegerInstance(this.locale))) {
                        result.append(",number,integer");
                    } else if (fmt instanceof DecimalFormat) {
                        result.append(",number,");
                        result.append(((DecimalFormat) fmt).toPattern());
                    } else if (fmt instanceof ChoiceFormat) {
                        result.append(",choice,");
                        result.append(((ChoiceFormat) fmt).toPattern());
                    }
                } else if (fmt instanceof DateFormat) {
                    int index = 0;
                    while (true) {
                        int[] iArr = DATE_TIME_MODIFIERS;
                        if (index >= iArr.length) {
                            break;
                        } else if (fmt.equals(DateFormat.getDateInstance(iArr[index], this.locale))) {
                            result.append(",date");
                            break;
                        } else if (fmt.equals(DateFormat.getTimeInstance(DATE_TIME_MODIFIERS[index], this.locale))) {
                            result.append(",time");
                            break;
                        } else {
                            index++;
                        }
                    }
                    if (index >= DATE_TIME_MODIFIERS.length) {
                        if (fmt instanceof SimpleDateFormat) {
                            result.append(",date,");
                            result.append(((SimpleDateFormat) fmt).toPattern());
                        }
                    } else if (index != 0) {
                        result.append(',');
                        result.append(DATE_TIME_MODIFIER_KEYWORDS[index]);
                    }
                }
            }
            result.append('}');
        }
        String str = this.pattern;
        copyAndFixQuotes(str, lastOffset, str.length(), result);
        return result.toString();
    }

    public void setFormatsByArgumentIndex(Format[] newFormats) {
        for (int i = 0; i <= this.maxOffset; i++) {
            int j = this.argumentNumbers[i];
            if (j < newFormats.length) {
                this.formats[i] = newFormats[j];
            }
        }
    }

    public void setFormats(Format[] newFormats) {
        int runsToCopy = newFormats.length;
        int i = this.maxOffset;
        if (runsToCopy > i + 1) {
            runsToCopy = i + 1;
        }
        for (int i2 = 0; i2 < runsToCopy; i2++) {
            this.formats[i2] = newFormats[i2];
        }
    }

    public void setFormatByArgumentIndex(int argumentIndex, Format newFormat) {
        for (int j = 0; j <= this.maxOffset; j++) {
            if (this.argumentNumbers[j] == argumentIndex) {
                this.formats[j] = newFormat;
            }
        }
    }

    public void setFormat(int formatElementIndex, Format newFormat) {
        if (formatElementIndex <= this.maxOffset) {
            this.formats[formatElementIndex] = newFormat;
            return;
        }
        throw new ArrayIndexOutOfBoundsException("maxOffset=" + this.maxOffset + "; formatElementIndex=" + formatElementIndex);
    }

    public Format[] getFormatsByArgumentIndex() {
        int maximumArgumentNumber = -1;
        for (int i = 0; i <= this.maxOffset; i++) {
            int[] iArr = this.argumentNumbers;
            if (iArr[i] > maximumArgumentNumber) {
                maximumArgumentNumber = iArr[i];
            }
        }
        Format[] resultArray = new Format[(maximumArgumentNumber + 1)];
        for (int i2 = 0; i2 <= this.maxOffset; i2++) {
            resultArray[this.argumentNumbers[i2]] = this.formats[i2];
        }
        return resultArray;
    }

    public Format[] getFormats() {
        int i = this.maxOffset;
        Format[] resultArray = new Format[(i + 1)];
        System.arraycopy(this.formats, 0, resultArray, 0, i + 1);
        return resultArray;
    }

    public final StringBuffer format(Object[] arguments, StringBuffer result, FieldPosition pos) {
        return subformat(arguments, result, pos, null);
    }

    public static String format(String pattern2, Object... arguments) {
        return new MessageFormat(pattern2).format(arguments);
    }

    @Override // java.text.Format
    public final StringBuffer format(Object arguments, StringBuffer result, FieldPosition pos) {
        return subformat((Object[]) arguments, result, pos, null);
    }

    @Override // java.text.Format
    public AttributedCharacterIterator formatToCharacterIterator(Object arguments) {
        StringBuffer result = new StringBuffer();
        ArrayList<AttributedCharacterIterator> iterators = new ArrayList<>();
        if (arguments != null) {
            subformat((Object[]) arguments, result, null, iterators);
            if (iterators.size() == 0) {
                return createAttributedCharacterIterator("");
            }
            return createAttributedCharacterIterator((AttributedCharacterIterator[]) iterators.toArray(new AttributedCharacterIterator[iterators.size()]));
        }
        throw new NullPointerException("formatToCharacterIterator must be passed non-null object");
    }

    public Object[] parse(String source, ParsePosition pos) {
        int next;
        if (source == null) {
            return new Object[0];
        }
        int maximumArgumentNumber = -1;
        for (int i = 0; i <= this.maxOffset; i++) {
            int[] iArr = this.argumentNumbers;
            if (iArr[i] > maximumArgumentNumber) {
                maximumArgumentNumber = iArr[i];
            }
        }
        Object[] resultArray = new Object[(maximumArgumentNumber + 1)];
        int patternOffset = 0;
        int sourceOffset = pos.index;
        ParsePosition tempStatus = new ParsePosition(0);
        int i2 = 0;
        while (i2 <= this.maxOffset) {
            int len = this.offsets[i2] - patternOffset;
            if (len == 0 || this.pattern.regionMatches(patternOffset, source, sourceOffset, len)) {
                int sourceOffset2 = sourceOffset + len;
                patternOffset += len;
                Format[] formatArr = this.formats;
                if (formatArr[i2] == null) {
                    int tempLength = i2 != this.maxOffset ? this.offsets[i2 + 1] : this.pattern.length();
                    if (patternOffset >= tempLength) {
                        next = source.length();
                    } else {
                        next = source.indexOf(this.pattern.substring(patternOffset, tempLength), sourceOffset2);
                    }
                    if (next < 0) {
                        pos.errorIndex = sourceOffset2;
                        return null;
                    }
                    String strValue = source.substring(sourceOffset2, next);
                    if (!strValue.equals("{" + this.argumentNumbers[i2] + "}")) {
                        resultArray[this.argumentNumbers[i2]] = source.substring(sourceOffset2, next);
                    }
                    sourceOffset = next;
                } else {
                    tempStatus.index = sourceOffset2;
                    resultArray[this.argumentNumbers[i2]] = formatArr[i2].parseObject(source, tempStatus);
                    if (tempStatus.index == sourceOffset2) {
                        pos.errorIndex = sourceOffset2;
                        return null;
                    }
                    sourceOffset = tempStatus.index;
                }
                i2++;
            } else {
                pos.errorIndex = sourceOffset;
                return null;
            }
        }
        int len2 = this.pattern.length() - patternOffset;
        if (len2 == 0 || this.pattern.regionMatches(patternOffset, source, sourceOffset, len2)) {
            pos.index = sourceOffset + len2;
            return resultArray;
        }
        pos.errorIndex = sourceOffset;
        return null;
    }

    public Object[] parse(String source) throws ParseException {
        ParsePosition pos = new ParsePosition(0);
        Object[] result = parse(source, pos);
        if (pos.index != 0) {
            return result;
        }
        throw new ParseException("MessageFormat parse error!", pos.errorIndex);
    }

    @Override // java.text.Format
    public Object parseObject(String source, ParsePosition pos) {
        return parse(source, pos);
    }

    @Override // java.text.Format
    public Object clone() {
        MessageFormat other = (MessageFormat) super.clone();
        other.formats = (Format[]) this.formats.clone();
        int i = 0;
        while (true) {
            Format[] formatArr = this.formats;
            if (i < formatArr.length) {
                if (formatArr[i] != null) {
                    other.formats[i] = (Format) formatArr[i].clone();
                }
                i++;
            } else {
                other.offsets = (int[]) this.offsets.clone();
                other.argumentNumbers = (int[]) this.argumentNumbers.clone();
                return other;
            }
        }
    }

    public boolean equals(Object obj) {
        Locale locale2;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        MessageFormat other = (MessageFormat) obj;
        if (this.maxOffset != other.maxOffset || !this.pattern.equals(other.pattern) || ((((locale2 = this.locale) == null || !locale2.equals(other.locale)) && (this.locale != null || other.locale != null)) || !Arrays.equals(this.offsets, other.offsets) || !Arrays.equals(this.argumentNumbers, other.argumentNumbers) || !Arrays.equals(this.formats, other.formats))) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.pattern.hashCode();
    }

    public static class Field extends Format.Field {
        public static final Field ARGUMENT = new Field("message argument field");
        private static final long serialVersionUID = 7899943957617360810L;

        protected Field(String name) {
            super(name);
        }

        /* access modifiers changed from: protected */
        @Override // java.text.AttributedCharacterIterator.Attribute
        public Object readResolve() throws InvalidObjectException {
            if (getClass() == Field.class) {
                return ARGUMENT;
            }
            throw new InvalidObjectException("subclass didn't correctly implement readResolve");
        }
    }

    private StringBuffer subformat(Object[] arguments, StringBuffer result, FieldPosition fp, List<AttributedCharacterIterator> characterIterators) {
        Object obj;
        int lastOffset = 0;
        int last = result.length();
        for (int i = 0; i <= this.maxOffset; i++) {
            result.append(this.pattern.substring(lastOffset, this.offsets[i]));
            lastOffset = this.offsets[i];
            int argumentNumber = this.argumentNumbers[i];
            if (arguments == null || argumentNumber >= arguments.length) {
                result.append('{');
                result.append(argumentNumber);
                result.append('}');
            } else {
                Object obj2 = arguments[argumentNumber];
                String arg = null;
                Format subFormatter = null;
                if (obj2 == null) {
                    arg = "null";
                    obj = obj2;
                } else {
                    Format[] formatArr = this.formats;
                    if (formatArr[i] != null) {
                        subFormatter = formatArr[i];
                        obj = obj2;
                        if (subFormatter instanceof ChoiceFormat) {
                            arg = formatArr[i].format(obj2);
                            obj = obj2;
                            if (arg.indexOf(123) >= 0) {
                                subFormatter = new MessageFormat(arg, this.locale);
                                obj = arguments;
                                arg = null;
                            }
                        }
                    } else if (obj2 instanceof Number) {
                        subFormatter = NumberFormat.getInstance(this.locale);
                        obj = obj2;
                    } else if (obj2 instanceof Date) {
                        subFormatter = DateFormat.getDateTimeInstance(3, 3, this.locale);
                        obj = obj2;
                    } else if (obj2 instanceof String) {
                        arg = (String) obj2;
                        obj = obj2;
                    } else {
                        arg = obj2.toString();
                        obj = obj2;
                        if (arg == null) {
                            arg = "null";
                            obj = obj2;
                        }
                    }
                }
                if (characterIterators != null) {
                    if (last != result.length()) {
                        characterIterators.add(createAttributedCharacterIterator(result.substring(last)));
                        last = result.length();
                    }
                    if (subFormatter != null) {
                        AttributedCharacterIterator subIterator = subFormatter.formatToCharacterIterator(obj);
                        append(result, subIterator);
                        if (last != result.length()) {
                            characterIterators.add(createAttributedCharacterIterator(subIterator, Field.ARGUMENT, Integer.valueOf(argumentNumber)));
                            last = result.length();
                        }
                        arg = null;
                    }
                    if (arg != null && arg.length() > 0) {
                        result.append(arg);
                        characterIterators.add(createAttributedCharacterIterator(arg, Field.ARGUMENT, Integer.valueOf(argumentNumber)));
                        last = result.length();
                    }
                } else {
                    if (subFormatter != null) {
                        arg = subFormatter.format(obj);
                    }
                    int last2 = result.length();
                    result.append(arg);
                    if (i == 0 && fp != null && Field.ARGUMENT.equals(fp.getFieldAttribute())) {
                        fp.setBeginIndex(last2);
                        fp.setEndIndex(result.length());
                    }
                    last = result.length();
                }
            }
        }
        String str = this.pattern;
        result.append(str.substring(lastOffset, str.length()));
        if (!(characterIterators == null || last == result.length())) {
            characterIterators.add(createAttributedCharacterIterator(result.substring(last)));
        }
        return result;
    }

    private void append(StringBuffer result, CharacterIterator iterator) {
        if (iterator.first() != 65535) {
            result.append(iterator.first());
            while (true) {
                char aChar = iterator.next();
                if (aChar != 65535) {
                    result.append(aChar);
                } else {
                    return;
                }
            }
        }
    }

    private void makeFormat(int position, int offsetNumber, StringBuilder[] textSegments) {
        int type;
        String[] segments = new String[textSegments.length];
        for (int i = 0; i < textSegments.length; i++) {
            StringBuilder oneseg = textSegments[i];
            segments[i] = oneseg != null ? oneseg.toString() : "";
        }
        try {
            int argumentNumber = Integer.parseInt(segments[1]);
            if (argumentNumber >= 0) {
                Format[] formatArr = this.formats;
                if (offsetNumber >= formatArr.length) {
                    int newLength = formatArr.length * 2;
                    Format[] newFormats = new Format[newLength];
                    int[] newOffsets = new int[newLength];
                    int[] newArgumentNumbers = new int[newLength];
                    System.arraycopy(formatArr, 0, newFormats, 0, this.maxOffset + 1);
                    System.arraycopy((Object) this.offsets, 0, (Object) newOffsets, 0, this.maxOffset + 1);
                    System.arraycopy((Object) this.argumentNumbers, 0, (Object) newArgumentNumbers, 0, this.maxOffset + 1);
                    this.formats = newFormats;
                    this.offsets = newOffsets;
                    this.argumentNumbers = newArgumentNumbers;
                }
                int oldMaxOffset = this.maxOffset;
                this.maxOffset = offsetNumber;
                this.offsets[offsetNumber] = segments[0].length();
                this.argumentNumbers[offsetNumber] = argumentNumber;
                Format newFormat = null;
                if (!(segments[2].length() == 0 || (type = findKeyword(segments[2], TYPE_KEYWORDS)) == 0)) {
                    if (type == 1) {
                        int findKeyword = findKeyword(segments[3], NUMBER_MODIFIER_KEYWORDS);
                        if (findKeyword == 0) {
                            newFormat = NumberFormat.getInstance(this.locale);
                        } else if (findKeyword == 1) {
                            newFormat = NumberFormat.getCurrencyInstance(this.locale);
                        } else if (findKeyword == 2) {
                            newFormat = NumberFormat.getPercentInstance(this.locale);
                        } else if (findKeyword != 3) {
                            try {
                                newFormat = new DecimalFormat(segments[3], DecimalFormatSymbols.getInstance(this.locale));
                            } catch (IllegalArgumentException e) {
                                this.maxOffset = oldMaxOffset;
                                throw e;
                            }
                        } else {
                            newFormat = NumberFormat.getIntegerInstance(this.locale);
                        }
                    } else if (type == 2 || type == 3) {
                        int mod = findKeyword(segments[3], DATE_TIME_MODIFIER_KEYWORDS);
                        if (mod < 0 || mod >= DATE_TIME_MODIFIER_KEYWORDS.length) {
                            try {
                                newFormat = new SimpleDateFormat(segments[3], this.locale);
                            } catch (IllegalArgumentException e2) {
                                this.maxOffset = oldMaxOffset;
                                throw e2;
                            }
                        } else {
                            newFormat = type == 2 ? DateFormat.getDateInstance(DATE_TIME_MODIFIERS[mod], this.locale) : DateFormat.getTimeInstance(DATE_TIME_MODIFIERS[mod], this.locale);
                        }
                    } else if (type == 4) {
                        try {
                            newFormat = new ChoiceFormat(segments[3]);
                        } catch (Exception e3) {
                            this.maxOffset = oldMaxOffset;
                            throw new IllegalArgumentException("Choice Pattern incorrect: " + segments[3], e3);
                        }
                    } else {
                        this.maxOffset = oldMaxOffset;
                        throw new IllegalArgumentException("unknown format type: " + segments[2]);
                    }
                }
                this.formats[offsetNumber] = newFormat;
                return;
            }
            throw new IllegalArgumentException("negative argument number: " + argumentNumber);
        } catch (NumberFormatException e4) {
            throw new IllegalArgumentException("can't parse argument number: " + segments[1], e4);
        }
    }

    private static final int findKeyword(String s, String[] list) {
        for (int i = 0; i < list.length; i++) {
            if (s.equals(list[i])) {
                return i;
            }
        }
        String ls = s.trim().toLowerCase(Locale.ROOT);
        if (ls == s) {
            return -1;
        }
        for (int i2 = 0; i2 < list.length; i2++) {
            if (ls.equals(list[i2])) {
                return i2;
            }
        }
        return -1;
    }

    private static final void copyAndFixQuotes(String source, int start, int end, StringBuilder target) {
        boolean quoted = false;
        for (int i = start; i < end; i++) {
            char ch = source.charAt(i);
            if (ch == '{') {
                if (!quoted) {
                    target.append(PatternTokenizer.SINGLE_QUOTE);
                    quoted = true;
                }
                target.append(ch);
            } else if (ch == '\'') {
                target.append("''");
            } else {
                if (quoted) {
                    target.append(PatternTokenizer.SINGLE_QUOTE);
                    quoted = false;
                }
                target.append(ch);
            }
        }
        if (quoted) {
            target.append(PatternTokenizer.SINGLE_QUOTE);
        }
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        int i = this.maxOffset;
        boolean isValid = i >= -1 && this.formats.length > i && this.offsets.length > i && this.argumentNumbers.length > i;
        if (isValid) {
            int lastOffset = this.pattern.length() + 1;
            int i2 = this.maxOffset;
            while (true) {
                if (i2 < 0) {
                    break;
                }
                int[] iArr = this.offsets;
                if (iArr[i2] < 0 || iArr[i2] > lastOffset) {
                    isValid = false;
                } else {
                    lastOffset = iArr[i2];
                    i2--;
                }
            }
            isValid = false;
        }
        if (!isValid) {
            throw new InvalidObjectException("Could not reconstruct MessageFormat from corrupt stream.");
        }
    }
}
