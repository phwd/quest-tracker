package android.icu.text;

import android.icu.impl.IllegalIcuArgumentException;
import android.icu.impl.Utility;
import android.icu.lang.UCharacter;
import android.icu.text.Normalizer;
import android.icu.text.RuleBasedTransliterator;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* access modifiers changed from: package-private */
public class TransliteratorParser {
    private static UnicodeSet ILLEGAL_FUNC = new UnicodeSet("[\\^\\(\\.\\*\\+\\?\\{\\}\\|\\@]");
    private static UnicodeSet ILLEGAL_SEG = new UnicodeSet("[\\{\\}\\|\\@]");
    private static UnicodeSet ILLEGAL_TOP = new UnicodeSet("[\\)]");
    public UnicodeSet compoundFilter;
    private RuleBasedTransliterator.Data curData;
    public List dataVector;
    private int direction;
    private int dotStandIn = -1;
    public List idBlockVector;
    private ParseData parseData;
    private List segmentObjects;
    private StringBuffer segmentStandins;
    private String undefinedVariableName;
    private char variableLimit;
    private Map variableNames;
    private char variableNext;
    private List variablesVector;

    /* access modifiers changed from: private */
    public class ParseData implements SymbolTable {
        private ParseData() {
        }

        @Override // android.icu.text.SymbolTable
        public char[] lookup(String str) {
            return (char[]) TransliteratorParser.this.variableNames.get(str);
        }

        @Override // android.icu.text.SymbolTable
        public UnicodeMatcher lookupMatcher(int i) {
            int i2 = i - TransliteratorParser.this.curData.variablesBase;
            if (i2 < 0 || i2 >= TransliteratorParser.this.variablesVector.size()) {
                return null;
            }
            return (UnicodeMatcher) TransliteratorParser.this.variablesVector.get(i2);
        }

        @Override // android.icu.text.SymbolTable
        public String parseReference(String str, ParsePosition parsePosition, int i) {
            int index = parsePosition.getIndex();
            int i2 = index;
            while (i2 < i) {
                char charAt = str.charAt(i2);
                if ((i2 == index && !UCharacter.isUnicodeIdentifierStart(charAt)) || !UCharacter.isUnicodeIdentifierPart(charAt)) {
                    break;
                }
                i2++;
            }
            if (i2 == index) {
                return null;
            }
            parsePosition.setIndex(i2);
            return str.substring(index, i2);
        }

        public boolean isMatcher(int i) {
            int i2 = i - TransliteratorParser.this.curData.variablesBase;
            if (i2 < 0 || i2 >= TransliteratorParser.this.variablesVector.size()) {
                return true;
            }
            return TransliteratorParser.this.variablesVector.get(i2) instanceof UnicodeMatcher;
        }

        public boolean isReplacer(int i) {
            int i2 = i - TransliteratorParser.this.curData.variablesBase;
            if (i2 < 0 || i2 >= TransliteratorParser.this.variablesVector.size()) {
                return true;
            }
            return TransliteratorParser.this.variablesVector.get(i2) instanceof UnicodeReplacer;
        }
    }

    /* access modifiers changed from: private */
    public static abstract class RuleBody {
        /* access modifiers changed from: package-private */
        public abstract String handleNextLine();

        /* access modifiers changed from: package-private */
        public abstract void reset();

        private RuleBody() {
        }

        /* access modifiers changed from: package-private */
        public String nextLine() {
            String handleNextLine;
            String handleNextLine2 = handleNextLine();
            if (handleNextLine2 == null || handleNextLine2.length() <= 0 || handleNextLine2.charAt(handleNextLine2.length() - 1) != '\\') {
                return handleNextLine2;
            }
            StringBuilder sb = new StringBuilder(handleNextLine2);
            do {
                sb.deleteCharAt(sb.length() - 1);
                handleNextLine = handleNextLine();
                if (handleNextLine != null) {
                    sb.append(handleNextLine);
                    if (handleNextLine.length() <= 0) {
                        break;
                    }
                } else {
                    break;
                }
            } while (handleNextLine.charAt(handleNextLine.length() - 1) == '\\');
            return sb.toString();
        }
    }

    /* access modifiers changed from: private */
    public static class RuleArray extends RuleBody {
        String[] array;
        int i = 0;

        public RuleArray(String[] strArr) {
            super();
            this.array = strArr;
        }

        @Override // android.icu.text.TransliteratorParser.RuleBody
        public String handleNextLine() {
            int i2 = this.i;
            String[] strArr = this.array;
            if (i2 >= strArr.length) {
                return null;
            }
            this.i = i2 + 1;
            return strArr[i2];
        }

        @Override // android.icu.text.TransliteratorParser.RuleBody
        public void reset() {
            this.i = 0;
        }
    }

    /* access modifiers changed from: private */
    public static class RuleHalf {
        public boolean anchorEnd;
        public boolean anchorStart;
        public int ante;
        public int cursor;
        public int cursorOffset;
        private int cursorOffsetPos;
        private int nextSegmentNumber;
        public int post;
        public String text;

        private RuleHalf() {
            this.cursor = -1;
            this.ante = -1;
            this.post = -1;
            this.cursorOffset = 0;
            this.cursorOffsetPos = 0;
            this.anchorStart = false;
            this.anchorEnd = false;
            this.nextSegmentNumber = 1;
        }

        public int parse(String str, int i, int i2, TransliteratorParser transliteratorParser) {
            StringBuffer stringBuffer = new StringBuffer();
            int parseSection = parseSection(str, i, i2, transliteratorParser, stringBuffer, TransliteratorParser.ILLEGAL_TOP, false);
            this.text = stringBuffer.toString();
            if (this.cursorOffset <= 0 || this.cursor == this.cursorOffsetPos) {
                return parseSection;
            }
            TransliteratorParser.syntaxError("Misplaced |", str, i);
            throw null;
        }

        /* JADX WARNING: Removed duplicated region for block: B:134:0x0286  */
        /* JADX WARNING: Removed duplicated region for block: B:138:0x0295  */
        /* JADX WARNING: Removed duplicated region for block: B:141:0x029f  */
        /* JADX WARNING: Removed duplicated region for block: B:142:0x02a6  */
        /* JADX WARNING: Removed duplicated region for block: B:150:0x02d4  */
        /* JADX WARNING: Removed duplicated region for block: B:153:0x02dc  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private int parseSection(java.lang.String r36, int r37, int r38, android.icu.text.TransliteratorParser r39, java.lang.StringBuffer r40, android.icu.text.UnicodeSet r41, boolean r42) {
            /*
            // Method dump skipped, instructions count: 1224
            */
            throw new UnsupportedOperationException("Method not decompiled: android.icu.text.TransliteratorParser.RuleHalf.parseSection(java.lang.String, int, int, android.icu.text.TransliteratorParser, java.lang.StringBuffer, android.icu.text.UnicodeSet, boolean):int");
        }

        /* access modifiers changed from: package-private */
        public void removeContext() {
            String str = this.text;
            int i = this.ante;
            if (i < 0) {
                i = 0;
            }
            int i2 = this.post;
            if (i2 < 0) {
                i2 = this.text.length();
            }
            this.text = str.substring(i, i2);
            this.post = -1;
            this.ante = -1;
            this.anchorEnd = false;
            this.anchorStart = false;
        }

        public boolean isValidOutput(TransliteratorParser transliteratorParser) {
            int i = 0;
            while (i < this.text.length()) {
                int charAt = UTF16.charAt(this.text, i);
                i += UTF16.getCharCount(charAt);
                if (!transliteratorParser.parseData.isReplacer(charAt)) {
                    return false;
                }
            }
            return true;
        }

        public boolean isValidInput(TransliteratorParser transliteratorParser) {
            int i = 0;
            while (i < this.text.length()) {
                int charAt = UTF16.charAt(this.text, i);
                i += UTF16.getCharCount(charAt);
                if (!transliteratorParser.parseData.isMatcher(charAt)) {
                    return false;
                }
            }
            return true;
        }
    }

    public void parse(String str, int i) {
        parseRules(new RuleArray(new String[]{str}), i);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x0132, code lost:
        throw null;
     */
    /* JADX WARNING: Removed duplicated region for block: B:103:0x01c5  */
    /* JADX WARNING: Removed duplicated region for block: B:104:0x01cb  */
    /* JADX WARNING: Removed duplicated region for block: B:108:0x01d9 A[LOOP:3: B:106:0x01d1->B:108:0x01d9, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:113:0x020a A[Catch:{ IllegalArgumentException -> 0x0254 }] */
    /* JADX WARNING: Removed duplicated region for block: B:125:0x0228 A[Catch:{ IllegalArgumentException -> 0x0254 }, LOOP:4: B:123:0x0220->B:125:0x0228, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:135:0x0261  */
    /* JADX WARNING: Removed duplicated region for block: B:144:0x0290 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:145:0x0291  */
    /* JADX WARNING: Removed duplicated region for block: B:149:0x018c A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void parseRules(android.icu.text.TransliteratorParser.RuleBody r18, int r19) {
        /*
        // Method dump skipped, instructions count: 678
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.text.TransliteratorParser.parseRules(android.icu.text.TransliteratorParser$RuleBody, int):void");
    }

    private int parseRule(String str, int i, int i2) {
        this.segmentStandins = new StringBuffer();
        this.segmentObjects = new ArrayList();
        UnicodeMatcher[] unicodeMatcherArr = null;
        RuleHalf ruleHalf = new RuleHalf();
        RuleHalf ruleHalf2 = new RuleHalf();
        this.undefinedVariableName = null;
        int parse = ruleHalf.parse(str, i, i2, this);
        if (parse != i2) {
            parse--;
            char charAt = str.charAt(parse);
            if ("=><←→↔".indexOf(charAt) >= 0) {
                int i3 = parse + 1;
                if (charAt == '<' && i3 < i2 && str.charAt(i3) == '>') {
                    i3++;
                    charAt = '~';
                }
                if (charAt == 8592) {
                    charAt = '<';
                } else if (charAt == 8594) {
                    charAt = '>';
                } else if (charAt == 8596) {
                    charAt = '~';
                }
                int parse2 = ruleHalf2.parse(str, i3, i2, this);
                if (parse2 < i2) {
                    int i4 = parse2 - 1;
                    if (str.charAt(i4) == ';') {
                        parse2 = i4 + 1;
                    } else {
                        syntaxError("Unquoted operator", str, i);
                        throw null;
                    }
                }
                if (charAt == '=') {
                    if (this.undefinedVariableName == null) {
                        syntaxError("Missing '$' or duplicate definition", str, i);
                        throw null;
                    } else if (ruleHalf.text.length() != 1 || ruleHalf.text.charAt(0) != this.variableLimit) {
                        syntaxError("Malformed LHS", str, i);
                        throw null;
                    } else if (ruleHalf.anchorStart || ruleHalf.anchorEnd || ruleHalf2.anchorStart || ruleHalf2.anchorEnd) {
                        syntaxError("Malformed variable def", str, i);
                        throw null;
                    } else {
                        int length = ruleHalf2.text.length();
                        char[] cArr = new char[length];
                        ruleHalf2.text.getChars(0, length, cArr, 0);
                        this.variableNames.put(this.undefinedVariableName, cArr);
                        this.variableLimit = (char) (this.variableLimit + 1);
                        return parse2;
                    }
                } else if (this.undefinedVariableName != null) {
                    syntaxError("Undefined variable $" + this.undefinedVariableName, str, i);
                    throw null;
                } else if (this.segmentStandins.length() <= this.segmentObjects.size()) {
                    for (int i5 = 0; i5 < this.segmentStandins.length(); i5++) {
                        if (this.segmentStandins.charAt(i5) == 0) {
                            syntaxError("Internal error", str, i);
                            throw null;
                        }
                    }
                    for (int i6 = 0; i6 < this.segmentObjects.size(); i6++) {
                        if (this.segmentObjects.get(i6) == null) {
                            syntaxError("Internal error", str, i);
                            throw null;
                        }
                    }
                    if (charAt != '~') {
                        if ((this.direction == 0) != (charAt == '>')) {
                            return parse2;
                        }
                    }
                    if (this.direction != 1) {
                        ruleHalf2 = ruleHalf;
                        ruleHalf = ruleHalf2;
                    }
                    if (charAt == '~') {
                        ruleHalf.removeContext();
                        ruleHalf2.cursor = -1;
                        ruleHalf2.cursorOffset = 0;
                    }
                    if (ruleHalf2.ante < 0) {
                        ruleHalf2.ante = 0;
                    }
                    if (ruleHalf2.post < 0) {
                        ruleHalf2.post = ruleHalf2.text.length();
                    }
                    if (ruleHalf.ante >= 0 || ruleHalf.post >= 0 || ruleHalf2.cursor >= 0 || ((ruleHalf.cursorOffset != 0 && ruleHalf.cursor < 0) || ruleHalf.anchorStart || ruleHalf.anchorEnd || !ruleHalf2.isValidInput(this) || !ruleHalf.isValidOutput(this) || ruleHalf2.ante > ruleHalf2.post)) {
                        syntaxError("Malformed rule", str, i);
                        throw null;
                    }
                    if (this.segmentObjects.size() > 0) {
                        unicodeMatcherArr = new UnicodeMatcher[this.segmentObjects.size()];
                        this.segmentObjects.toArray(unicodeMatcherArr);
                    }
                    RuleBasedTransliterator.Data data = this.curData;
                    data.ruleSet.addRule(new TransliterationRule(ruleHalf2.text, ruleHalf2.ante, ruleHalf2.post, ruleHalf.text, ruleHalf.cursor, ruleHalf.cursorOffset, unicodeMatcherArr, ruleHalf2.anchorStart, ruleHalf2.anchorEnd, data));
                    return parse2;
                } else {
                    syntaxError("Undefined segment reference", str, i);
                    throw null;
                }
            }
        }
        syntaxError("No operator pos=" + parse, str, i);
        throw null;
    }

    private void setVariableRange(int i, int i2) {
        if (i > i2 || i < 0 || i2 > 65535) {
            throw new IllegalIcuArgumentException("Invalid variable range " + i + ", " + i2);
        }
        char c = (char) i;
        this.curData.variablesBase = c;
        if (this.dataVector.size() == 0) {
            this.variableNext = c;
            this.variableLimit = (char) (i2 + 1);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void checkVariableRange(int i, String str, int i2) {
        if (i >= this.curData.variablesBase && i < this.variableLimit) {
            syntaxError("Variable range character in rule", str, i2);
            throw null;
        }
    }

    private void pragmaMaximumBackup(int i) {
        throw new IllegalIcuArgumentException("use maximum backup pragma not implemented yet");
    }

    private void pragmaNormalizeRules(Normalizer.Mode mode) {
        throw new IllegalIcuArgumentException("use normalize rules pragma not implemented yet");
    }

    static boolean resemblesPragma(String str, int i, int i2) {
        return Utility.parsePattern(str, i, i2, "use ", null) >= 0;
    }

    private int parsePragma(String str, int i, int i2) {
        int[] iArr = new int[2];
        int i3 = i + 4;
        int parsePattern = Utility.parsePattern(str, i3, i2, "~variable range # #~;", iArr);
        if (parsePattern >= 0) {
            setVariableRange(iArr[0], iArr[1]);
            return parsePattern;
        } else if (Utility.parsePattern(str, i3, i2, "~maximum backup #~;", iArr) >= 0) {
            pragmaMaximumBackup(iArr[0]);
            throw null;
        } else if (Utility.parsePattern(str, i3, i2, "~nfd rules~;", null) >= 0) {
            pragmaNormalizeRules(Normalizer.NFD);
            throw null;
        } else if (Utility.parsePattern(str, i3, i2, "~nfc rules~;", null) < 0) {
            return -1;
        } else {
            pragmaNormalizeRules(Normalizer.NFC);
            throw null;
        }
    }

    static final void syntaxError(String str, String str2, int i) {
        int ruleEnd = ruleEnd(str2, i, str2.length());
        throw new IllegalIcuArgumentException(str + " in \"" + Utility.escape(str2.substring(i, ruleEnd)) + '\"');
    }

    static final int ruleEnd(String str, int i, int i2) {
        int quotedIndexOf = Utility.quotedIndexOf(str, i, i2, ";");
        return quotedIndexOf < 0 ? i2 : quotedIndexOf;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private final char parseSet(String str, ParsePosition parsePosition) {
        UnicodeSet unicodeSet = new UnicodeSet(str, parsePosition, this.parseData);
        if (this.variableNext < this.variableLimit) {
            unicodeSet.compact();
            return generateStandInFor(unicodeSet);
        }
        throw new RuntimeException("Private use variables exhausted");
    }

    /* access modifiers changed from: package-private */
    public char generateStandInFor(Object obj) {
        for (int i = 0; i < this.variablesVector.size(); i++) {
            if (this.variablesVector.get(i) == obj) {
                return (char) (this.curData.variablesBase + i);
            }
        }
        if (this.variableNext < this.variableLimit) {
            this.variablesVector.add(obj);
            char c = this.variableNext;
            this.variableNext = (char) (c + 1);
            return c;
        }
        throw new RuntimeException("Variable range exhausted");
    }

    public char getSegmentStandin(int i) {
        if (this.segmentStandins.length() < i) {
            this.segmentStandins.setLength(i);
        }
        int i2 = i - 1;
        char charAt = this.segmentStandins.charAt(i2);
        if (charAt == 0) {
            charAt = this.variableNext;
            if (charAt < this.variableLimit) {
                this.variableNext = (char) (charAt + 1);
                this.variablesVector.add(null);
                this.segmentStandins.setCharAt(i2, charAt);
            } else {
                throw new RuntimeException("Variable range exhausted");
            }
        }
        return charAt;
    }

    public void setSegmentObject(int i, StringMatcher stringMatcher) {
        while (this.segmentObjects.size() < i) {
            this.segmentObjects.add(null);
        }
        int segmentStandin = getSegmentStandin(i) - this.curData.variablesBase;
        int i2 = i - 1;
        if (this.segmentObjects.get(i2) == null && this.variablesVector.get(segmentStandin) == null) {
            this.segmentObjects.set(i2, stringMatcher);
            this.variablesVector.set(segmentStandin, stringMatcher);
            return;
        }
        throw new RuntimeException();
    }

    /* access modifiers changed from: package-private */
    public char getDotStandIn() {
        if (this.dotStandIn == -1) {
            this.dotStandIn = generateStandInFor(new UnicodeSet("[^[:Zp:][:Zl:]\\r\\n$]"));
        }
        return (char) this.dotStandIn;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void appendVariableDef(String str, StringBuffer stringBuffer) {
        char[] cArr = (char[]) this.variableNames.get(str);
        if (cArr != null) {
            stringBuffer.append(cArr);
        } else if (this.undefinedVariableName == null) {
            this.undefinedVariableName = str;
            char c = this.variableNext;
            char c2 = this.variableLimit;
            if (c < c2) {
                char c3 = (char) (c2 - 1);
                this.variableLimit = c3;
                stringBuffer.append(c3);
                return;
            }
            throw new RuntimeException("Private use variables exhausted");
        } else {
            throw new IllegalIcuArgumentException("Undefined variable $" + str);
        }
    }
}
