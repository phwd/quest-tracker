package android.icu.text;

import android.icu.impl.ICUConfig;
import android.icu.impl.PatternProps;
import android.icu.util.Freezable;
import android.icu.util.ICUCloneNotSupportedException;
import java.util.ArrayList;

public final class MessagePattern implements Cloneable, Freezable {
    private static final ArgType[] argTypes = ArgType.values();
    private static final ApostropheMode defaultAposMode = ApostropheMode.valueOf(ICUConfig.get("android.icu.text.MessagePattern.ApostropheMode", "DOUBLE_OPTIONAL"));
    private ApostropheMode aposMode = defaultAposMode;
    private volatile boolean frozen;
    private boolean hasArgNames;
    private boolean hasArgNumbers;
    private String msg;
    private boolean needsAutoQuoting;
    private ArrayList numericValues;
    private ArrayList parts = new ArrayList();

    public enum ApostropheMode {
        DOUBLE_OPTIONAL,
        DOUBLE_REQUIRED
    }

    private static boolean isArgTypeChar(int i) {
        return (97 <= i && i <= 122) || (65 <= i && i <= 90);
    }

    private void postParse() {
    }

    public MessagePattern parsePluralStyle(String str) {
        preParse(str);
        parsePluralOrSelectStyle(ArgType.PLURAL, 0, 0);
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
            ArrayList arrayList = this.numericValues;
            if (arrayList != null) {
                arrayList.clear();
                return;
            }
            return;
        }
        throw new UnsupportedOperationException("Attempt to clear() a frozen MessagePattern instance.");
    }

    public boolean equals(Object obj) {
        String str;
        if (this == obj) {
            return true;
        }
        if (obj == null || MessagePattern.class != obj.getClass()) {
            return false;
        }
        MessagePattern messagePattern = (MessagePattern) obj;
        return this.aposMode.equals(messagePattern.aposMode) && ((str = this.msg) != null ? str.equals(messagePattern.msg) : messagePattern.msg == null) && this.parts.equals(messagePattern.parts);
    }

    public int hashCode() {
        int hashCode = this.aposMode.hashCode() * 37;
        String str = this.msg;
        return ((hashCode + (str != null ? str.hashCode() : 0)) * 37) + this.parts.hashCode();
    }

    /* access modifiers changed from: package-private */
    public boolean jdkAposMode() {
        return this.aposMode == ApostropheMode.DOUBLE_REQUIRED;
    }

    public String toString() {
        return this.msg;
    }

    public int countParts() {
        return this.parts.size();
    }

    public Part getPart(int i) {
        return (Part) this.parts.get(i);
    }

    public Part.Type getPartType(int i) {
        return ((Part) this.parts.get(i)).type;
    }

    public boolean partSubstringMatches(Part part, String str) {
        return part.length == str.length() && this.msg.regionMatches(part.index, str, 0, part.length);
    }

    public double getNumericValue(Part part) {
        Part.Type type = part.type;
        if (type == Part.Type.ARG_INT) {
            return (double) part.value;
        }
        if (type == Part.Type.ARG_DOUBLE) {
            return ((Double) this.numericValues.get(part.value)).doubleValue();
        }
        return -1.23456789E8d;
    }

    public double getPluralOffset(int i) {
        Part part = (Part) this.parts.get(i);
        if (part.type.hasNumericValue()) {
            return getNumericValue(part);
        }
        return 0.0d;
    }

    public int getLimitPartIndex(int i) {
        int i2 = ((Part) this.parts.get(i)).limitPartIndex;
        return i2 < i ? i : i2;
    }

    public static final class Part {
        private final int index;
        private final char length;
        private int limitPartIndex;
        private final Type type;
        private short value;

        private Part(Type type2, int i, int i2, int i3) {
            this.type = type2;
            this.index = i;
            this.length = (char) i2;
            this.value = (short) i3;
        }

        public Type getType() {
            return this.type;
        }

        public int getIndex() {
            return this.index;
        }

        public int getLimit() {
            return this.index + this.length;
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
            Type type2 = this.type;
            String name = (type2 == Type.ARG_START || type2 == Type.ARG_LIMIT) ? getArgType().name() : Integer.toString(this.value);
            return this.type.name() + "(" + name + ")@" + this.index;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || Part.class != obj.getClass()) {
                return false;
            }
            Part part = (Part) obj;
            return this.type.equals(part.type) && this.index == part.index && this.length == part.length && this.value == part.value && this.limitPartIndex == part.limitPartIndex;
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

    public MessagePattern cloneAsThawed() {
        try {
            MessagePattern messagePattern = (MessagePattern) super.clone();
            messagePattern.parts = (ArrayList) this.parts.clone();
            ArrayList arrayList = this.numericValues;
            if (arrayList != null) {
                messagePattern.numericValues = (ArrayList) arrayList.clone();
            }
            messagePattern.frozen = false;
            return messagePattern;
        } catch (CloneNotSupportedException e) {
            throw new ICUCloneNotSupportedException(e);
        }
    }

    public boolean isFrozen() {
        return this.frozen;
    }

    private void preParse(String str) {
        if (!isFrozen()) {
            this.msg = str;
            this.hasArgNumbers = false;
            this.hasArgNames = false;
            this.needsAutoQuoting = false;
            this.parts.clear();
            ArrayList arrayList = this.numericValues;
            if (arrayList != null) {
                arrayList.clear();
                return;
            }
            return;
        }
        throw new UnsupportedOperationException("Attempt to parse(" + prefix(str) + ") on frozen MessagePattern instance.");
    }

    private int parseMessage(int i, int i2, int i3, ArgType argType) {
        int indexOf;
        if (i3 <= 32767) {
            int size = this.parts.size();
            addPart(Part.Type.MSG_START, i, i2, i3);
            int i4 = i + i2;
            while (i4 < this.msg.length()) {
                int i5 = i4 + 1;
                char charAt = this.msg.charAt(i4);
                if (charAt == '\'') {
                    if (i5 == this.msg.length()) {
                        addPart(Part.Type.INSERT_CHAR, i5, 0, 39);
                        this.needsAutoQuoting = true;
                    } else {
                        char charAt2 = this.msg.charAt(i5);
                        if (charAt2 == '\'') {
                            addPart(Part.Type.SKIP_SYNTAX, i5, 1, 0);
                            i4 = i5 + 1;
                        } else if (this.aposMode == ApostropheMode.DOUBLE_REQUIRED || charAt2 == '{' || charAt2 == '}' || ((argType == ArgType.CHOICE && charAt2 == '|') || (argType.hasPluralStyle() && charAt2 == '#'))) {
                            addPart(Part.Type.SKIP_SYNTAX, i5 - 1, 1, 0);
                            while (true) {
                                indexOf = this.msg.indexOf(39, i5 + 1);
                                if (indexOf < 0) {
                                    i4 = this.msg.length();
                                    addPart(Part.Type.INSERT_CHAR, i4, 0, 39);
                                    this.needsAutoQuoting = true;
                                    break;
                                }
                                i5 = indexOf + 1;
                                if (i5 >= this.msg.length() || this.msg.charAt(i5) != '\'') {
                                    addPart(Part.Type.SKIP_SYNTAX, indexOf, 1, 0);
                                } else {
                                    addPart(Part.Type.SKIP_SYNTAX, i5, 1, 0);
                                }
                            }
                            addPart(Part.Type.SKIP_SYNTAX, indexOf, 1, 0);
                        } else {
                            addPart(Part.Type.INSERT_CHAR, i5, 0, 39);
                            this.needsAutoQuoting = true;
                        }
                    }
                } else if (argType.hasPluralStyle() && charAt == '#') {
                    addPart(Part.Type.REPLACE_NUMBER, i5 - 1, 1, 0);
                } else if (charAt == '{') {
                    i4 = parseArg(i5 - 1, 1, i3);
                } else if ((i3 > 0 && charAt == '}') || (argType == ArgType.CHOICE && charAt == '|')) {
                    int i6 = (argType == ArgType.CHOICE && charAt == '}') ? 0 : 1;
                    int i7 = i5 - 1;
                    addLimitPart(size, Part.Type.MSG_LIMIT, i7, i6, i3);
                    return argType == ArgType.CHOICE ? i7 : i5;
                }
                i4 = i5;
            }
            if (i3 <= 0 || inTopLevelChoiceMessage(i3, argType)) {
                addLimitPart(size, Part.Type.MSG_LIMIT, i4, 0, i3);
                return i4;
            }
            throw new IllegalArgumentException("Unmatched '{' braces in message " + prefix());
        }
        throw new IndexOutOfBoundsException();
    }

    private int parseArg(int i, int i2, int i3) {
        int i4;
        char charAt;
        int size = this.parts.size();
        ArgType argType = ArgType.NONE;
        addPart(Part.Type.ARG_START, i, i2, argType.ordinal());
        int skipWhiteSpace = skipWhiteSpace(i + i2);
        if (skipWhiteSpace != this.msg.length()) {
            int skipIdentifier = skipIdentifier(skipWhiteSpace);
            int parseArgNumber = parseArgNumber(skipWhiteSpace, skipIdentifier);
            if (parseArgNumber >= 0) {
                int i5 = skipIdentifier - skipWhiteSpace;
                if (i5 > 65535 || parseArgNumber > 32767) {
                    throw new IndexOutOfBoundsException("Argument number too large: " + prefix(skipWhiteSpace));
                }
                this.hasArgNumbers = true;
                addPart(Part.Type.ARG_NUMBER, skipWhiteSpace, i5, parseArgNumber);
            } else if (parseArgNumber == -1) {
                int i6 = skipIdentifier - skipWhiteSpace;
                if (i6 <= 65535) {
                    this.hasArgNames = true;
                    addPart(Part.Type.ARG_NAME, skipWhiteSpace, i6, 0);
                } else {
                    throw new IndexOutOfBoundsException("Argument name too long: " + prefix(skipWhiteSpace));
                }
            } else {
                throw new IllegalArgumentException("Bad argument syntax: " + prefix(skipWhiteSpace));
            }
            int skipWhiteSpace2 = skipWhiteSpace(skipIdentifier);
            if (skipWhiteSpace2 != this.msg.length()) {
                char charAt2 = this.msg.charAt(skipWhiteSpace2);
                if (charAt2 == '}') {
                    i4 = skipWhiteSpace2;
                } else if (charAt2 == ',') {
                    int skipWhiteSpace3 = skipWhiteSpace(skipWhiteSpace2 + 1);
                    int i7 = skipWhiteSpace3;
                    while (i7 < this.msg.length() && isArgTypeChar(this.msg.charAt(i7))) {
                        i7++;
                    }
                    int i8 = i7 - skipWhiteSpace3;
                    int skipWhiteSpace4 = skipWhiteSpace(i7);
                    if (skipWhiteSpace4 == this.msg.length()) {
                        throw new IllegalArgumentException("Unmatched '{' braces in message " + prefix());
                    } else if (i8 == 0 || !((charAt = this.msg.charAt(skipWhiteSpace4)) == ',' || charAt == '}')) {
                        throw new IllegalArgumentException("Bad argument syntax: " + prefix(skipWhiteSpace));
                    } else if (i8 <= 65535) {
                        argType = ArgType.SIMPLE;
                        if (i8 == 6) {
                            if (isChoice(skipWhiteSpace3)) {
                                argType = ArgType.CHOICE;
                            } else if (isPlural(skipWhiteSpace3)) {
                                argType = ArgType.PLURAL;
                            } else if (isSelect(skipWhiteSpace3)) {
                                argType = ArgType.SELECT;
                            }
                        } else if (i8 == 13 && isSelect(skipWhiteSpace3) && isOrdinal(skipWhiteSpace3 + 6)) {
                            argType = ArgType.SELECTORDINAL;
                        }
                        ((Part) this.parts.get(size)).value = (short) argType.ordinal();
                        if (argType == ArgType.SIMPLE) {
                            addPart(Part.Type.ARG_TYPE, skipWhiteSpace3, i8, 0);
                        }
                        if (charAt != '}') {
                            int i9 = skipWhiteSpace4 + 1;
                            if (argType == ArgType.SIMPLE) {
                                i4 = parseSimpleStyle(i9);
                            } else if (argType == ArgType.CHOICE) {
                                i4 = parseChoiceStyle(i9, i3);
                            } else {
                                i4 = parsePluralOrSelectStyle(argType, i9, i3);
                            }
                        } else if (argType == ArgType.SIMPLE) {
                            i4 = skipWhiteSpace4;
                        } else {
                            throw new IllegalArgumentException("No style field for complex argument: " + prefix(skipWhiteSpace));
                        }
                    } else {
                        throw new IndexOutOfBoundsException("Argument type name too long: " + prefix(skipWhiteSpace));
                    }
                } else {
                    throw new IllegalArgumentException("Bad argument syntax: " + prefix(skipWhiteSpace));
                }
                addLimitPart(size, Part.Type.ARG_LIMIT, i4, 1, argType.ordinal());
                return i4 + 1;
            }
            throw new IllegalArgumentException("Unmatched '{' braces in message " + prefix());
        }
        throw new IllegalArgumentException("Unmatched '{' braces in message " + prefix());
    }

    private int parseSimpleStyle(int i) {
        int i2 = i;
        int i3 = 0;
        while (i2 < this.msg.length()) {
            int i4 = i2 + 1;
            char charAt = this.msg.charAt(i2);
            if (charAt == '\'') {
                int indexOf = this.msg.indexOf(39, i4);
                if (indexOf >= 0) {
                    i2 = indexOf + 1;
                } else {
                    throw new IllegalArgumentException("Quoted literal argument style text reaches to the end of the message: " + prefix(i));
                }
            } else {
                if (charAt == '{') {
                    i3++;
                } else if (charAt == '}') {
                    if (i3 > 0) {
                        i3--;
                    } else {
                        int i5 = i4 - 1;
                        int i6 = i5 - i;
                        if (i6 <= 65535) {
                            addPart(Part.Type.ARG_STYLE, i, i6, 0);
                            return i5;
                        }
                        throw new IndexOutOfBoundsException("Argument style text too long: " + prefix(i));
                    }
                }
                i2 = i4;
            }
        }
        throw new IllegalArgumentException("Unmatched '{' braces in message " + prefix());
    }

    private int parseChoiceStyle(int i, int i2) {
        int skipWhiteSpace = skipWhiteSpace(i);
        if (skipWhiteSpace == this.msg.length() || this.msg.charAt(skipWhiteSpace) == '}') {
            throw new IllegalArgumentException("Missing choice argument pattern in " + prefix());
        }
        while (true) {
            int skipDouble = skipDouble(skipWhiteSpace);
            int i3 = skipDouble - skipWhiteSpace;
            if (i3 == 0) {
                throw new IllegalArgumentException("Bad choice pattern syntax: " + prefix(i));
            } else if (i3 <= 65535) {
                parseDouble(skipWhiteSpace, skipDouble, true);
                int skipWhiteSpace2 = skipWhiteSpace(skipDouble);
                if (skipWhiteSpace2 != this.msg.length()) {
                    char charAt = this.msg.charAt(skipWhiteSpace2);
                    if (charAt == '#' || charAt == '<' || charAt == 8804) {
                        addPart(Part.Type.ARG_SELECTOR, skipWhiteSpace2, 1, 0);
                        int parseMessage = parseMessage(skipWhiteSpace2 + 1, 0, i2 + 1, ArgType.CHOICE);
                        if (parseMessage == this.msg.length()) {
                            return parseMessage;
                        }
                        if (this.msg.charAt(parseMessage) != '}') {
                            skipWhiteSpace = skipWhiteSpace(parseMessage + 1);
                        } else if (inMessageFormatPattern(i2)) {
                            return parseMessage;
                        } else {
                            throw new IllegalArgumentException("Bad choice pattern syntax: " + prefix(i));
                        }
                    } else {
                        throw new IllegalArgumentException("Expected choice separator (#<≤) instead of '" + charAt + "' in choice pattern " + prefix(i));
                    }
                } else {
                    throw new IllegalArgumentException("Bad choice pattern syntax: " + prefix(i));
                }
            } else {
                throw new IndexOutOfBoundsException("Choice number too long: " + prefix(skipWhiteSpace));
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:60:0x01c4, code lost:
        if (r5 == inMessageFormatPattern(r15)) goto L_0x01f6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x01c6, code lost:
        if (r3 == false) goto L_0x01c9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x01c8, code lost:
        return r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x01f5, code lost:
        throw new java.lang.IllegalArgumentException("Missing 'other' keyword in " + r13.toString().toLowerCase(java.util.Locale.ENGLISH) + " pattern in " + prefix());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x021e, code lost:
        throw new java.lang.IllegalArgumentException("Bad " + r13.toString().toLowerCase(java.util.Locale.ENGLISH) + " pattern syntax: " + prefix(r14));
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int parsePluralOrSelectStyle(android.icu.text.MessagePattern.ArgType r13, int r14, int r15) {
        /*
        // Method dump skipped, instructions count: 543
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.text.MessagePattern.parsePluralOrSelectStyle(android.icu.text.MessagePattern$ArgType, int, int):int");
    }

    private static int parseArgNumber(CharSequence charSequence, int i, int i2) {
        int i3;
        if (i >= i2) {
            return -2;
        }
        int i4 = i + 1;
        char charAt = charSequence.charAt(i);
        boolean z = false;
        if (charAt == '0') {
            if (i4 == i2) {
                return 0;
            }
            i3 = 0;
            z = true;
        } else if ('1' > charAt || charAt > '9') {
            return -1;
        } else {
            i3 = charAt - '0';
        }
        while (i4 < i2) {
            int i5 = i4 + 1;
            char charAt2 = charSequence.charAt(i4);
            if ('0' > charAt2 || charAt2 > '9') {
                return -1;
            }
            if (i3 >= 214748364) {
                z = true;
            }
            i3 = (i3 * 10) + (charAt2 - '0');
            i4 = i5;
        }
        if (z) {
            return -2;
        }
        return i3;
    }

    private int parseArgNumber(int i, int i2) {
        return parseArgNumber(this.msg, i, i2);
    }

    private void parseDouble(int i, int i2, boolean z) {
        int i3;
        int i4;
        int i5 = i + 1;
        char charAt = this.msg.charAt(i);
        int i6 = 0;
        if (charAt == '-') {
            if (i5 != i2) {
                i3 = i5 + 1;
                charAt = this.msg.charAt(i5);
                i4 = 1;
            }
            throw new NumberFormatException("Bad syntax for numeric value: " + this.msg.substring(i, i2));
        }
        if (charAt == '+') {
            if (i5 != i2) {
                i3 = i5 + 1;
                charAt = this.msg.charAt(i5);
            }
            throw new NumberFormatException("Bad syntax for numeric value: " + this.msg.substring(i, i2));
        }
        i3 = i5;
        i4 = 0;
        if (charAt == 8734) {
            if (z && i3 == i2) {
                addArgDoublePart(i4 != 0 ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY, i, i2 - i);
                return;
            }
            throw new NumberFormatException("Bad syntax for numeric value: " + this.msg.substring(i, i2));
        }
        while ('0' <= charAt && charAt <= '9' && (i6 = (i6 * 10) + (charAt - '0')) <= i4 + 32767) {
            if (i3 == i2) {
                Part.Type type = Part.Type.ARG_INT;
                int i7 = i2 - i;
                if (i4 != 0) {
                    i6 = -i6;
                }
                addPart(type, i, i7, i6);
                return;
            }
            char charAt2 = this.msg.charAt(i3);
            i3++;
            charAt = charAt2;
        }
        addArgDoublePart(Double.parseDouble(this.msg.substring(i, i2)), i, i2 - i);
    }

    static void appendReducedApostrophes(String str, int i, int i2, StringBuilder sb) {
        loop0:
        while (true) {
            int i3 = -1;
            while (true) {
                int indexOf = str.indexOf(39, i);
                if (indexOf < 0 || indexOf >= i2) {
                    sb.append((CharSequence) str, i, i2);
                } else if (indexOf == i3) {
                    break;
                } else {
                    sb.append((CharSequence) str, i, indexOf);
                    i = indexOf + 1;
                    i3 = i;
                }
            }
            sb.append('\'');
            i++;
        }
        sb.append((CharSequence) str, i, i2);
    }

    private int skipWhiteSpace(int i) {
        return PatternProps.skipWhiteSpace(this.msg, i);
    }

    private int skipIdentifier(int i) {
        return PatternProps.skipIdentifier(this.msg, i);
    }

    private int skipDouble(int i) {
        while (i < this.msg.length() && (((r0 = this.msg.charAt(i)) >= '0' || "+-.".indexOf(r0) >= 0) && (r0 <= '9' || r0 == 'e' || r0 == 'E' || r0 == 8734))) {
            i++;
        }
        return i;
    }

    private boolean isChoice(int i) {
        char charAt;
        int i2 = i + 1;
        char charAt2 = this.msg.charAt(i);
        if (charAt2 == 'c' || charAt2 == 'C') {
            int i3 = i2 + 1;
            char charAt3 = this.msg.charAt(i2);
            if (charAt3 == 'h' || charAt3 == 'H') {
                int i4 = i3 + 1;
                char charAt4 = this.msg.charAt(i3);
                if (charAt4 == 'o' || charAt4 == 'O') {
                    int i5 = i4 + 1;
                    char charAt5 = this.msg.charAt(i4);
                    if (charAt5 == 'i' || charAt5 == 'I') {
                        int i6 = i5 + 1;
                        char charAt6 = this.msg.charAt(i5);
                        if ((charAt6 == 'c' || charAt6 == 'C') && ((charAt = this.msg.charAt(i6)) == 'e' || charAt == 'E')) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean isPlural(int i) {
        char charAt;
        int i2 = i + 1;
        char charAt2 = this.msg.charAt(i);
        if (charAt2 == 'p' || charAt2 == 'P') {
            int i3 = i2 + 1;
            char charAt3 = this.msg.charAt(i2);
            if (charAt3 == 'l' || charAt3 == 'L') {
                int i4 = i3 + 1;
                char charAt4 = this.msg.charAt(i3);
                if (charAt4 == 'u' || charAt4 == 'U') {
                    int i5 = i4 + 1;
                    char charAt5 = this.msg.charAt(i4);
                    if (charAt5 == 'r' || charAt5 == 'R') {
                        int i6 = i5 + 1;
                        char charAt6 = this.msg.charAt(i5);
                        if ((charAt6 == 'a' || charAt6 == 'A') && ((charAt = this.msg.charAt(i6)) == 'l' || charAt == 'L')) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean isSelect(int i) {
        char charAt;
        int i2 = i + 1;
        char charAt2 = this.msg.charAt(i);
        if (charAt2 == 's' || charAt2 == 'S') {
            int i3 = i2 + 1;
            char charAt3 = this.msg.charAt(i2);
            if (charAt3 == 'e' || charAt3 == 'E') {
                int i4 = i3 + 1;
                char charAt4 = this.msg.charAt(i3);
                if (charAt4 == 'l' || charAt4 == 'L') {
                    int i5 = i4 + 1;
                    char charAt5 = this.msg.charAt(i4);
                    if (charAt5 == 'e' || charAt5 == 'E') {
                        int i6 = i5 + 1;
                        char charAt6 = this.msg.charAt(i5);
                        if ((charAt6 == 'c' || charAt6 == 'C') && ((charAt = this.msg.charAt(i6)) == 't' || charAt == 'T')) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean isOrdinal(int i) {
        char charAt;
        int i2 = i + 1;
        char charAt2 = this.msg.charAt(i);
        if (charAt2 == 'o' || charAt2 == 'O') {
            int i3 = i2 + 1;
            char charAt3 = this.msg.charAt(i2);
            if (charAt3 == 'r' || charAt3 == 'R') {
                int i4 = i3 + 1;
                char charAt4 = this.msg.charAt(i3);
                if (charAt4 == 'd' || charAt4 == 'D') {
                    int i5 = i4 + 1;
                    char charAt5 = this.msg.charAt(i4);
                    if (charAt5 == 'i' || charAt5 == 'I') {
                        int i6 = i5 + 1;
                        char charAt6 = this.msg.charAt(i5);
                        if (charAt6 == 'n' || charAt6 == 'N') {
                            int i7 = i6 + 1;
                            char charAt7 = this.msg.charAt(i6);
                            if ((charAt7 == 'a' || charAt7 == 'A') && ((charAt = this.msg.charAt(i7)) == 'l' || charAt == 'L')) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean inMessageFormatPattern(int i) {
        return i > 0 || ((Part) this.parts.get(0)).type == Part.Type.MSG_START;
    }

    private boolean inTopLevelChoiceMessage(int i, ArgType argType) {
        return i == 1 && argType == ArgType.CHOICE && ((Part) this.parts.get(0)).type != Part.Type.MSG_START;
    }

    private void addPart(Part.Type type, int i, int i2, int i3) {
        this.parts.add(new Part(type, i, i2, i3));
    }

    private void addLimitPart(int i, Part.Type type, int i2, int i3, int i4) {
        ((Part) this.parts.get(i)).limitPartIndex = this.parts.size();
        addPart(type, i2, i3, i4);
    }

    private void addArgDoublePart(double d, int i, int i2) {
        int i3;
        ArrayList arrayList = this.numericValues;
        if (arrayList == null) {
            this.numericValues = new ArrayList();
            i3 = 0;
        } else {
            i3 = arrayList.size();
            if (i3 > 32767) {
                throw new IndexOutOfBoundsException("Too many numeric values");
            }
        }
        this.numericValues.add(Double.valueOf(d));
        addPart(Part.Type.ARG_DOUBLE, i, i2, i3);
    }

    private static String prefix(String str, int i) {
        StringBuilder sb = new StringBuilder(44);
        if (i == 0) {
            sb.append("\"");
        } else {
            sb.append("[at pattern index ");
            sb.append(i);
            sb.append("] \"");
        }
        if (str.length() - i <= 24) {
            if (i != 0) {
                str = str.substring(i);
            }
            sb.append(str);
        } else {
            int i2 = (i + 24) - 4;
            if (Character.isHighSurrogate(str.charAt(i2 - 1))) {
                i2--;
            }
            sb.append((CharSequence) str, i, i2);
            sb.append(" ...");
        }
        sb.append("\"");
        return sb.toString();
    }

    private static String prefix(String str) {
        return prefix(str, 0);
    }

    private String prefix(int i) {
        return prefix(this.msg, i);
    }

    private String prefix() {
        return prefix(this.msg, 0);
    }
}
