package android.icu.text;

import android.icu.impl.ICUConfig;
import android.icu.impl.PatternProps;
import android.icu.impl.PatternTokenizer;
import android.icu.util.Freezable;
import android.icu.util.ICUCloneNotSupportedException;
import java.util.ArrayList;

public final class MessagePattern implements Cloneable, Freezable<MessagePattern> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int ARG_NAME_NOT_NUMBER = -1;
    public static final int ARG_NAME_NOT_VALID = -2;
    private static final int MAX_PREFIX_LENGTH = 24;
    public static final double NO_NUMERIC_VALUE = -1.23456789E8d;
    private static final ArgType[] argTypes = ArgType.values();
    private static final ApostropheMode defaultAposMode = ApostropheMode.valueOf(ICUConfig.get("android.icu.text.MessagePattern.ApostropheMode", "DOUBLE_OPTIONAL"));
    private ApostropheMode aposMode;
    private volatile boolean frozen;
    private boolean hasArgNames;
    private boolean hasArgNumbers;
    private String msg;
    private boolean needsAutoQuoting;
    private ArrayList<Double> numericValues;
    private ArrayList<Part> parts;

    public enum ApostropheMode {
        DOUBLE_OPTIONAL,
        DOUBLE_REQUIRED
    }

    public MessagePattern() {
        this.parts = new ArrayList<>();
        this.aposMode = defaultAposMode;
    }

    public MessagePattern(ApostropheMode mode) {
        this.parts = new ArrayList<>();
        this.aposMode = mode;
    }

    public MessagePattern(String pattern) {
        this.parts = new ArrayList<>();
        this.aposMode = defaultAposMode;
        parse(pattern);
    }

    public MessagePattern parse(String pattern) {
        preParse(pattern);
        parseMessage(0, 0, 0, ArgType.NONE);
        postParse();
        return this;
    }

    public MessagePattern parseChoiceStyle(String pattern) {
        preParse(pattern);
        parseChoiceStyle(0, 0);
        postParse();
        return this;
    }

    public MessagePattern parsePluralStyle(String pattern) {
        preParse(pattern);
        parsePluralOrSelectStyle(ArgType.PLURAL, 0, 0);
        postParse();
        return this;
    }

    public MessagePattern parseSelectStyle(String pattern) {
        preParse(pattern);
        parsePluralOrSelectStyle(ArgType.SELECT, 0, 0);
        postParse();
        return this;
    }

    public void clear() {
        if (!isFrozen()) {
            this.msg = null;
            this.hasArgNumbers = false;
            this.hasArgNames = false;
            this.needsAutoQuoting = false;
            this.parts.clear();
            ArrayList<Double> arrayList = this.numericValues;
            if (arrayList != null) {
                arrayList.clear();
                return;
            }
            return;
        }
        throw new UnsupportedOperationException("Attempt to clear() a frozen MessagePattern instance.");
    }

    public void clearPatternAndSetApostropheMode(ApostropheMode mode) {
        clear();
        this.aposMode = mode;
    }

    public boolean equals(Object other) {
        String str;
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        MessagePattern o = (MessagePattern) other;
        if (!this.aposMode.equals(o.aposMode) || ((str = this.msg) != null ? !str.equals(o.msg) : o.msg != null) || !this.parts.equals(o.parts)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int hashCode = this.aposMode.hashCode() * 37;
        String str = this.msg;
        return ((hashCode + (str != null ? str.hashCode() : 0)) * 37) + this.parts.hashCode();
    }

    public ApostropheMode getApostropheMode() {
        return this.aposMode;
    }

    /* access modifiers changed from: package-private */
    public boolean jdkAposMode() {
        return this.aposMode == ApostropheMode.DOUBLE_REQUIRED;
    }

    public String getPatternString() {
        return this.msg;
    }

    public boolean hasNamedArguments() {
        return this.hasArgNames;
    }

    public boolean hasNumberedArguments() {
        return this.hasArgNumbers;
    }

    public String toString() {
        return this.msg;
    }

    public static int validateArgumentName(String name) {
        if (!PatternProps.isIdentifier(name)) {
            return -2;
        }
        return parseArgNumber(name, 0, name.length());
    }

    public String autoQuoteApostropheDeep() {
        if (!this.needsAutoQuoting) {
            return this.msg;
        }
        StringBuilder modified = null;
        int i = countParts();
        while (i > 0) {
            i--;
            Part part = getPart(i);
            if (part.getType() == Part.Type.INSERT_CHAR) {
                if (modified == null) {
                    modified = new StringBuilder(this.msg.length() + 10).append(this.msg);
                }
                modified.insert(part.index, (char) part.value);
            }
        }
        if (modified == null) {
            return this.msg;
        }
        return modified.toString();
    }

    public int countParts() {
        return this.parts.size();
    }

    public Part getPart(int i) {
        return this.parts.get(i);
    }

    public Part.Type getPartType(int i) {
        return this.parts.get(i).type;
    }

    public int getPatternIndex(int partIndex) {
        return this.parts.get(partIndex).index;
    }

    public String getSubstring(Part part) {
        int index = part.index;
        return this.msg.substring(index, part.length + index);
    }

    public boolean partSubstringMatches(Part part, String s) {
        return part.length == s.length() && this.msg.regionMatches(part.index, s, 0, part.length);
    }

    public double getNumericValue(Part part) {
        Part.Type type = part.type;
        if (type == Part.Type.ARG_INT) {
            return (double) part.value;
        }
        if (type == Part.Type.ARG_DOUBLE) {
            return this.numericValues.get(part.value).doubleValue();
        }
        return -1.23456789E8d;
    }

    public double getPluralOffset(int pluralStart) {
        Part part = this.parts.get(pluralStart);
        if (part.type.hasNumericValue()) {
            return getNumericValue(part);
        }
        return 0.0d;
    }

    public int getLimitPartIndex(int start) {
        int limit = this.parts.get(start).limitPartIndex;
        if (limit < start) {
            return start;
        }
        return limit;
    }

    public static final class Part {
        private static final int MAX_LENGTH = 65535;
        private static final int MAX_VALUE = 32767;
        private final int index;
        private final char length;
        private int limitPartIndex;
        private final Type type;
        private short value;

        private Part(Type t, int i, int l, int v) {
            this.type = t;
            this.index = i;
            this.length = (char) l;
            this.value = (short) v;
        }

        public Type getType() {
            return this.type;
        }

        public int getIndex() {
            return this.index;
        }

        public int getLength() {
            return this.length;
        }

        public int getLimit() {
            return this.index + this.length;
        }

        public int getValue() {
            return this.value;
        }

        public ArgType getArgType() {
            Type type2 = getType();
            if (type2 == Type.ARG_START || type2 == Type.ARG_LIMIT) {
                return MessagePattern.argTypes[this.value];
            }
            return ArgType.NONE;
        }

        public enum Type {
            MSG_START,
            MSG_LIMIT,
            SKIP_SYNTAX,
            INSERT_CHAR,
            REPLACE_NUMBER,
            ARG_START,
            ARG_LIMIT,
            ARG_NUMBER,
            ARG_NAME,
            ARG_TYPE,
            ARG_STYLE,
            ARG_SELECTOR,
            ARG_INT,
            ARG_DOUBLE;

            public boolean hasNumericValue() {
                return this == ARG_INT || this == ARG_DOUBLE;
            }
        }

        public String toString() {
            String valueString = (this.type == Type.ARG_START || this.type == Type.ARG_LIMIT) ? getArgType().name() : Integer.toString(this.value);
            return this.type.name() + "(" + valueString + ")@" + this.index;
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (other == null || getClass() != other.getClass()) {
                return false;
            }
            Part o = (Part) other;
            if (this.type.equals(o.type) && this.index == o.index && this.length == o.length && this.value == o.value && this.limitPartIndex == o.limitPartIndex) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            return (((((this.type.hashCode() * 37) + this.index) * 37) + this.length) * 37) + this.value;
        }
    }

    public enum ArgType {
        NONE,
        SIMPLE,
        CHOICE,
        PLURAL,
        SELECT,
        SELECTORDINAL;

        public boolean hasPluralStyle() {
            return this == PLURAL || this == SELECTORDINAL;
        }
    }

    public Object clone() {
        if (isFrozen()) {
            return this;
        }
        return cloneAsThawed();
    }

    @Override // android.icu.util.Freezable
    public MessagePattern cloneAsThawed() {
        try {
            MessagePattern newMsg = (MessagePattern) super.clone();
            newMsg.parts = (ArrayList) this.parts.clone();
            ArrayList<Double> arrayList = this.numericValues;
            if (arrayList != null) {
                newMsg.numericValues = (ArrayList) arrayList.clone();
            }
            newMsg.frozen = false;
            return newMsg;
        } catch (CloneNotSupportedException e) {
            throw new ICUCloneNotSupportedException(e);
        }
    }

    @Override // android.icu.util.Freezable
    public MessagePattern freeze() {
        this.frozen = true;
        return this;
    }

    @Override // android.icu.util.Freezable
    public boolean isFrozen() {
        return this.frozen;
    }

    private void preParse(String pattern) {
        if (!isFrozen()) {
            this.msg = pattern;
            this.hasArgNumbers = false;
            this.hasArgNames = false;
            this.needsAutoQuoting = false;
            this.parts.clear();
            ArrayList<Double> arrayList = this.numericValues;
            if (arrayList != null) {
                arrayList.clear();
                return;
            }
            return;
        }
        throw new UnsupportedOperationException("Attempt to parse(" + prefix(pattern) + ") on frozen MessagePattern instance.");
    }

    private void postParse() {
    }

    private int parseMessage(int index, int msgStartLength, int nestingLevel, ArgType parentType) {
        int index2;
        if (nestingLevel <= 32767) {
            int msgStart = this.parts.size();
            addPart(Part.Type.MSG_START, index, msgStartLength, nestingLevel);
            int index3 = index + msgStartLength;
            while (index3 < this.msg.length()) {
                int index4 = index3 + 1;
                char c = this.msg.charAt(index3);
                if (c == '\'') {
                    if (index4 == this.msg.length()) {
                        addPart(Part.Type.INSERT_CHAR, index4, 0, 39);
                        this.needsAutoQuoting = true;
                    } else {
                        char c2 = this.msg.charAt(index4);
                        if (c2 == '\'') {
                            addPart(Part.Type.SKIP_SYNTAX, index4, 1, 0);
                            index3 = index4 + 1;
                        } else if (this.aposMode == ApostropheMode.DOUBLE_REQUIRED || c2 == '{' || c2 == '}' || ((parentType == ArgType.CHOICE && c2 == '|') || (parentType.hasPluralStyle() && c2 == '#'))) {
                            addPart(Part.Type.SKIP_SYNTAX, index4 - 1, 1, 0);
                            while (true) {
                                index2 = this.msg.indexOf(39, index4 + 1);
                                if (index2 < 0) {
                                    int index5 = this.msg.length();
                                    addPart(Part.Type.INSERT_CHAR, index5, 0, 39);
                                    this.needsAutoQuoting = true;
                                    index3 = index5;
                                    break;
                                } else if (index2 + 1 >= this.msg.length() || this.msg.charAt(index2 + 1) != '\'') {
                                    addPart(Part.Type.SKIP_SYNTAX, index2, 1, 0);
                                    index3 = index2 + 1;
                                } else {
                                    index4 = index2 + 1;
                                    addPart(Part.Type.SKIP_SYNTAX, index4, 1, 0);
                                }
                            }
                            addPart(Part.Type.SKIP_SYNTAX, index2, 1, 0);
                            index3 = index2 + 1;
                        } else {
                            addPart(Part.Type.INSERT_CHAR, index4, 0, 39);
                            this.needsAutoQuoting = true;
                        }
                    }
                } else if (parentType.hasPluralStyle() && c == '#') {
                    addPart(Part.Type.REPLACE_NUMBER, index4 - 1, 1, 0);
                } else if (c == '{') {
                    index3 = parseArg(index4 - 1, 1, nestingLevel);
                } else if ((nestingLevel > 0 && c == '}') || (parentType == ArgType.CHOICE && c == '|')) {
                    addLimitPart(msgStart, Part.Type.MSG_LIMIT, index4 - 1, (parentType == ArgType.CHOICE && c == '}') ? 0 : 1, nestingLevel);
                    if (parentType == ArgType.CHOICE) {
                        return index4 - 1;
                    }
                    return index4;
                }
                index3 = index4;
            }
            if (nestingLevel <= 0 || inTopLevelChoiceMessage(nestingLevel, parentType)) {
                addLimitPart(msgStart, Part.Type.MSG_LIMIT, index3, 0, nestingLevel);
                return index3;
            }
            throw new IllegalArgumentException("Unmatched '{' braces in message " + prefix());
        }
        throw new IndexOutOfBoundsException();
    }

    /* JADX INFO: Multiple debug info for r2v2 int: [D('index' int), D('nameIndex' int)] */
    private int parseArg(int index, int argStartLength, int nestingLevel) {
        ArgType argType;
        int index2;
        char c;
        int argStart = this.parts.size();
        ArgType argType2 = ArgType.NONE;
        addPart(Part.Type.ARG_START, index, argStartLength, argType2.ordinal());
        int nameIndex = skipWhiteSpace(index + argStartLength);
        if (nameIndex != this.msg.length()) {
            int index3 = skipIdentifier(nameIndex);
            int number = parseArgNumber(nameIndex, index3);
            if (number >= 0) {
                int length = index3 - nameIndex;
                if (length > 65535 || number > 32767) {
                    throw new IndexOutOfBoundsException("Argument number too large: " + prefix(nameIndex));
                }
                this.hasArgNumbers = true;
                addPart(Part.Type.ARG_NUMBER, nameIndex, length, number);
            } else if (number == -1) {
                int length2 = index3 - nameIndex;
                if (length2 <= 65535) {
                    this.hasArgNames = true;
                    addPart(Part.Type.ARG_NAME, nameIndex, length2, 0);
                } else {
                    throw new IndexOutOfBoundsException("Argument name too long: " + prefix(nameIndex));
                }
            } else {
                throw new IllegalArgumentException("Bad argument syntax: " + prefix(nameIndex));
            }
            int index4 = skipWhiteSpace(index3);
            if (index4 != this.msg.length()) {
                char c2 = this.msg.charAt(index4);
                if (c2 == '}') {
                    argType = argType2;
                    index2 = index4;
                } else if (c2 == ',') {
                    int typeIndex = skipWhiteSpace(index4 + 1);
                    int index5 = typeIndex;
                    while (index5 < this.msg.length() && isArgTypeChar(this.msg.charAt(index5))) {
                        index5++;
                    }
                    int length3 = index5 - typeIndex;
                    int index6 = skipWhiteSpace(index5);
                    if (index6 == this.msg.length()) {
                        throw new IllegalArgumentException("Unmatched '{' braces in message " + prefix());
                    } else if (length3 == 0 || !((c = this.msg.charAt(index6)) == ',' || c == '}')) {
                        throw new IllegalArgumentException("Bad argument syntax: " + prefix(nameIndex));
                    } else if (length3 <= 65535) {
                        ArgType argType3 = ArgType.SIMPLE;
                        if (length3 == 6) {
                            if (isChoice(typeIndex)) {
                                argType3 = ArgType.CHOICE;
                            } else if (isPlural(typeIndex)) {
                                argType3 = ArgType.PLURAL;
                            } else if (isSelect(typeIndex)) {
                                argType3 = ArgType.SELECT;
                            }
                        } else if (length3 == 13 && isSelect(typeIndex) && isOrdinal(typeIndex + 6)) {
                            argType3 = ArgType.SELECTORDINAL;
                        }
                        this.parts.get(argStart).value = (short) argType3.ordinal();
                        if (argType3 == ArgType.SIMPLE) {
                            addPart(Part.Type.ARG_TYPE, typeIndex, length3, 0);
                        }
                        if (c != '}') {
                            int index7 = index6 + 1;
                            if (argType3 == ArgType.SIMPLE) {
                                argType = argType3;
                                index2 = parseSimpleStyle(index7);
                            } else if (argType3 == ArgType.CHOICE) {
                                argType = argType3;
                                index2 = parseChoiceStyle(index7, nestingLevel);
                            } else {
                                argType = argType3;
                                index2 = parsePluralOrSelectStyle(argType3, index7, nestingLevel);
                            }
                        } else if (argType3 == ArgType.SIMPLE) {
                            argType = argType3;
                            index2 = index6;
                        } else {
                            throw new IllegalArgumentException("No style field for complex argument: " + prefix(nameIndex));
                        }
                    } else {
                        throw new IndexOutOfBoundsException("Argument type name too long: " + prefix(nameIndex));
                    }
                } else {
                    throw new IllegalArgumentException("Bad argument syntax: " + prefix(nameIndex));
                }
                addLimitPart(argStart, Part.Type.ARG_LIMIT, index2, 1, argType.ordinal());
                return index2 + 1;
            }
            throw new IllegalArgumentException("Unmatched '{' braces in message " + prefix());
        }
        throw new IllegalArgumentException("Unmatched '{' braces in message " + prefix());
    }

    private int parseSimpleStyle(int index) {
        int nestedBraces = 0;
        while (index < this.msg.length()) {
            int index2 = index + 1;
            char c = this.msg.charAt(index);
            if (c == '\'') {
                int index3 = this.msg.indexOf(39, index2);
                if (index3 >= 0) {
                    index = index3 + 1;
                } else {
                    throw new IllegalArgumentException("Quoted literal argument style text reaches to the end of the message: " + prefix(index));
                }
            } else if (c == '{') {
                nestedBraces++;
                index = index2;
            } else if (c != '}') {
                index = index2;
            } else if (nestedBraces > 0) {
                nestedBraces--;
                index = index2;
            } else {
                int index4 = index2 - 1;
                int length = index4 - index;
                if (length <= 65535) {
                    addPart(Part.Type.ARG_STYLE, index, length, 0);
                    return index4;
                }
                throw new IndexOutOfBoundsException("Argument style text too long: " + prefix(index));
            }
        }
        throw new IllegalArgumentException("Unmatched '{' braces in message " + prefix());
    }

    private int parseChoiceStyle(int index, int nestingLevel) {
        int index2 = skipWhiteSpace(index);
        if (index2 == this.msg.length() || this.msg.charAt(index2) == '}') {
            throw new IllegalArgumentException("Missing choice argument pattern in " + prefix());
        }
        while (true) {
            int index3 = skipDouble(index2);
            int length = index3 - index2;
            if (length == 0) {
                throw new IllegalArgumentException("Bad choice pattern syntax: " + prefix(index));
            } else if (length <= 65535) {
                parseDouble(index2, index3, true);
                int index4 = skipWhiteSpace(index3);
                if (index4 != this.msg.length()) {
                    char c = this.msg.charAt(index4);
                    if (c == '#' || c == '<' || c == 8804) {
                        addPart(Part.Type.ARG_SELECTOR, index4, 1, 0);
                        int index5 = parseMessage(index4 + 1, 0, nestingLevel + 1, ArgType.CHOICE);
                        if (index5 == this.msg.length()) {
                            return index5;
                        }
                        if (this.msg.charAt(index5) != '}') {
                            index2 = skipWhiteSpace(index5 + 1);
                        } else if (inMessageFormatPattern(nestingLevel)) {
                            return index5;
                        } else {
                            throw new IllegalArgumentException("Bad choice pattern syntax: " + prefix(index));
                        }
                    } else {
                        throw new IllegalArgumentException("Expected choice separator (#<≤) instead of '" + c + "' in choice pattern " + prefix(index));
                    }
                } else {
                    throw new IllegalArgumentException("Bad choice pattern syntax: " + prefix(index));
                }
            } else {
                throw new IndexOutOfBoundsException("Choice number too long: " + prefix(index2));
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:54:0x018f, code lost:
        throw new java.lang.IllegalArgumentException("No message fragment after " + r17.toString().toLowerCase(java.util.Locale.ENGLISH) + " selector: " + prefix(r3));
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int parsePluralOrSelectStyle(android.icu.text.MessagePattern.ArgType r17, int r18, int r19) {
        /*
        // Method dump skipped, instructions count: 567
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.text.MessagePattern.parsePluralOrSelectStyle(android.icu.text.MessagePattern$ArgType, int, int):int");
    }

    private static int parseArgNumber(CharSequence s, int start, int limit) {
        boolean badNumber;
        int number;
        if (start >= limit) {
            return -2;
        }
        int start2 = start + 1;
        char c = s.charAt(start);
        if (c == '0') {
            if (start2 == limit) {
                return 0;
            }
            number = 0;
            badNumber = true;
        } else if ('1' > c || c > '9') {
            return -1;
        } else {
            number = c - '0';
            badNumber = false;
        }
        while (start2 < limit) {
            int start3 = start2 + 1;
            char c2 = s.charAt(start2);
            if ('0' > c2 || c2 > '9') {
                return -1;
            }
            if (number >= 214748364) {
                badNumber = true;
            }
            number = (number * 10) + (c2 - '0');
            start2 = start3;
        }
        if (badNumber) {
            return -2;
        }
        return number;
    }

    private int parseArgNumber(int start, int limit) {
        return parseArgNumber(this.msg, start, limit);
    }

    private void parseDouble(int start, int limit, boolean allowInfinity) {
        int index;
        int value = 0;
        int isNegative = 0;
        int index2 = start + 1;
        char c = this.msg.charAt(start);
        if (c == '-') {
            isNegative = 1;
            if (index2 != limit) {
                index = index2 + 1;
                c = this.msg.charAt(index2);
            }
            throw new NumberFormatException("Bad syntax for numeric value: " + this.msg.substring(start, limit));
        } else if (c == '+') {
            if (index2 != limit) {
                index = index2 + 1;
                c = this.msg.charAt(index2);
            }
            throw new NumberFormatException("Bad syntax for numeric value: " + this.msg.substring(start, limit));
        } else {
            index = index2;
        }
        if (c == 8734) {
            if (allowInfinity && index == limit) {
                addArgDoublePart(isNegative != 0 ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY, start, limit - start);
                return;
            }
            throw new NumberFormatException("Bad syntax for numeric value: " + this.msg.substring(start, limit));
        }
        while ('0' <= c && c <= '9' && (value = (value * 10) + (c - '0')) <= isNegative + 32767) {
            if (index == limit) {
                addPart(Part.Type.ARG_INT, start, limit - start, isNegative != 0 ? -value : value);
                return;
            } else {
                c = this.msg.charAt(index);
                index++;
            }
        }
        addArgDoublePart(Double.parseDouble(this.msg.substring(start, limit)), start, limit - start);
    }

    static void appendReducedApostrophes(String s, int start, int limit, StringBuilder sb) {
        int doubleApos = -1;
        while (true) {
            int i = s.indexOf(39, start);
            if (i < 0 || i >= limit) {
                sb.append((CharSequence) s, start, limit);
            } else if (i == doubleApos) {
                sb.append(PatternTokenizer.SINGLE_QUOTE);
                start++;
                doubleApos = -1;
            } else {
                sb.append((CharSequence) s, start, i);
                int i2 = i + 1;
                start = i2;
                doubleApos = i2;
            }
        }
        sb.append((CharSequence) s, start, limit);
    }

    private int skipWhiteSpace(int index) {
        return PatternProps.skipWhiteSpace(this.msg, index);
    }

    private int skipIdentifier(int index) {
        return PatternProps.skipIdentifier(this.msg, index);
    }

    private int skipDouble(int index) {
        while (index < this.msg.length() && (((c = this.msg.charAt(index)) >= '0' || "+-.".indexOf(c) >= 0) && (c <= '9' || c == 'e' || c == 'E' || c == 8734))) {
            index++;
        }
        return index;
    }

    private static boolean isArgTypeChar(int c) {
        return (97 <= c && c <= 122) || (65 <= c && c <= 90);
    }

    private boolean isChoice(int index) {
        char c;
        int index2 = index + 1;
        char c2 = this.msg.charAt(index);
        if (c2 == 'c' || c2 == 'C') {
            int index3 = index2 + 1;
            char c3 = this.msg.charAt(index2);
            if (c3 == 'h' || c3 == 'H') {
                int index4 = index3 + 1;
                char c4 = this.msg.charAt(index3);
                if (c4 == 'o' || c4 == 'O') {
                    int index5 = index4 + 1;
                    char c5 = this.msg.charAt(index4);
                    if (c5 == 'i' || c5 == 'I') {
                        int index6 = index5 + 1;
                        char c6 = this.msg.charAt(index5);
                        if ((c6 == 'c' || c6 == 'C') && ((c = this.msg.charAt(index6)) == 'e' || c == 'E')) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean isPlural(int index) {
        char c;
        int index2 = index + 1;
        char c2 = this.msg.charAt(index);
        if (c2 == 'p' || c2 == 'P') {
            int index3 = index2 + 1;
            char c3 = this.msg.charAt(index2);
            if (c3 == 'l' || c3 == 'L') {
                int index4 = index3 + 1;
                char c4 = this.msg.charAt(index3);
                if (c4 == 'u' || c4 == 'U') {
                    int index5 = index4 + 1;
                    char c5 = this.msg.charAt(index4);
                    if (c5 == 'r' || c5 == 'R') {
                        int index6 = index5 + 1;
                        char c6 = this.msg.charAt(index5);
                        if ((c6 == 'a' || c6 == 'A') && ((c = this.msg.charAt(index6)) == 'l' || c == 'L')) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean isSelect(int index) {
        char c;
        int index2 = index + 1;
        char c2 = this.msg.charAt(index);
        if (c2 == 's' || c2 == 'S') {
            int index3 = index2 + 1;
            char c3 = this.msg.charAt(index2);
            if (c3 == 'e' || c3 == 'E') {
                int index4 = index3 + 1;
                char c4 = this.msg.charAt(index3);
                if (c4 == 'l' || c4 == 'L') {
                    int index5 = index4 + 1;
                    char c5 = this.msg.charAt(index4);
                    if (c5 == 'e' || c5 == 'E') {
                        int index6 = index5 + 1;
                        char c6 = this.msg.charAt(index5);
                        if ((c6 == 'c' || c6 == 'C') && ((c = this.msg.charAt(index6)) == 't' || c == 'T')) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean isOrdinal(int index) {
        char c;
        int index2 = index + 1;
        char c2 = this.msg.charAt(index);
        if (c2 == 'o' || c2 == 'O') {
            int index3 = index2 + 1;
            char c3 = this.msg.charAt(index2);
            if (c3 == 'r' || c3 == 'R') {
                int index4 = index3 + 1;
                char c4 = this.msg.charAt(index3);
                if (c4 == 'd' || c4 == 'D') {
                    int index5 = index4 + 1;
                    char c5 = this.msg.charAt(index4);
                    if (c5 == 'i' || c5 == 'I') {
                        int index6 = index5 + 1;
                        char c6 = this.msg.charAt(index5);
                        if (c6 == 'n' || c6 == 'N') {
                            int index7 = index6 + 1;
                            char c7 = this.msg.charAt(index6);
                            if ((c7 == 'a' || c7 == 'A') && ((c = this.msg.charAt(index7)) == 'l' || c == 'L')) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean inMessageFormatPattern(int nestingLevel) {
        return nestingLevel > 0 || this.parts.get(0).type == Part.Type.MSG_START;
    }

    private boolean inTopLevelChoiceMessage(int nestingLevel, ArgType parentType) {
        return nestingLevel == 1 && parentType == ArgType.CHOICE && this.parts.get(0).type != Part.Type.MSG_START;
    }

    private void addPart(Part.Type type, int index, int length, int value) {
        this.parts.add(new Part(type, index, length, value));
    }

    private void addLimitPart(int start, Part.Type type, int index, int length, int value) {
        this.parts.get(start).limitPartIndex = this.parts.size();
        addPart(type, index, length, value);
    }

    private void addArgDoublePart(double numericValue, int start, int length) {
        int numericIndex;
        ArrayList<Double> arrayList = this.numericValues;
        if (arrayList == null) {
            this.numericValues = new ArrayList<>();
            numericIndex = 0;
        } else {
            numericIndex = arrayList.size();
            if (numericIndex > 32767) {
                throw new IndexOutOfBoundsException("Too many numeric values");
            }
        }
        this.numericValues.add(Double.valueOf(numericValue));
        addPart(Part.Type.ARG_DOUBLE, start, length, numericIndex);
    }

    private static String prefix(String s, int start) {
        StringBuilder prefix = new StringBuilder(44);
        if (start == 0) {
            prefix.append("\"");
        } else {
            prefix.append("[at pattern index ");
            prefix.append(start);
            prefix.append("] \"");
        }
        if (s.length() - start <= 24) {
            prefix.append(start == 0 ? s : s.substring(start));
        } else {
            int limit = (start + 24) - 4;
            if (Character.isHighSurrogate(s.charAt(limit - 1))) {
                limit--;
            }
            prefix.append((CharSequence) s, start, limit);
            prefix.append(" ...");
        }
        prefix.append("\"");
        return prefix.toString();
    }

    private static String prefix(String s) {
        return prefix(s, 0);
    }

    private String prefix(int start) {
        return prefix(this.msg, start);
    }

    private String prefix() {
        return prefix(this.msg, 0);
    }
}
