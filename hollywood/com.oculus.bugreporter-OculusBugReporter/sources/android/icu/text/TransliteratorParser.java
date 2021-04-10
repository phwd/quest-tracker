package android.icu.text;

import android.icu.impl.IllegalIcuArgumentException;
import android.icu.impl.PatternProps;
import android.icu.impl.Utility;
import android.icu.lang.UCharacter;
import android.icu.text.Normalizer;
import android.icu.text.RuleBasedTransliterator;
import android.icu.text.TransliteratorIDParser;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* access modifiers changed from: package-private */
public class TransliteratorParser {
    private static final char ALT_FORWARD_RULE_OP = 8594;
    private static final char ALT_FUNCTION = 8710;
    private static final char ALT_FWDREV_RULE_OP = 8596;
    private static final char ALT_REVERSE_RULE_OP = 8592;
    private static final char ANCHOR_START = '^';
    private static final char CONTEXT_ANTE = '{';
    private static final char CONTEXT_POST = '}';
    private static final char CURSOR_OFFSET = '@';
    private static final char CURSOR_POS = '|';
    private static final char DOT = '.';
    private static final String DOT_SET = "[^[:Zp:][:Zl:]\\r\\n$]";
    private static final char END_OF_RULE = ';';
    private static final char ESCAPE = '\\';
    private static final char FORWARD_RULE_OP = '>';
    private static final char FUNCTION = '&';
    private static final char FWDREV_RULE_OP = '~';
    private static final String HALF_ENDERS = "=><←→↔;";
    private static final String ID_TOKEN = "::";
    private static final int ID_TOKEN_LEN = 2;
    private static UnicodeSet ILLEGAL_FUNC = new UnicodeSet("[\\^\\(\\.\\*\\+\\?\\{\\}\\|\\@]");
    private static UnicodeSet ILLEGAL_SEG = new UnicodeSet("[\\{\\}\\|\\@]");
    private static UnicodeSet ILLEGAL_TOP = new UnicodeSet("[\\)]");
    private static final char KLEENE_STAR = '*';
    private static final char ONE_OR_MORE = '+';
    private static final String OPERATORS = "=><←→↔";
    private static final char QUOTE = '\'';
    private static final char REVERSE_RULE_OP = '<';
    private static final char RULE_COMMENT_CHAR = '#';
    private static final char SEGMENT_CLOSE = ')';
    private static final char SEGMENT_OPEN = '(';
    private static final char VARIABLE_DEF_OP = '=';
    private static final char ZERO_OR_ONE = '?';
    public UnicodeSet compoundFilter;
    private RuleBasedTransliterator.Data curData;
    public List<RuleBasedTransliterator.Data> dataVector;
    private int direction;
    private int dotStandIn = -1;
    public List<String> idBlockVector;
    private ParseData parseData;
    private List<StringMatcher> segmentObjects;
    private StringBuffer segmentStandins;
    private String undefinedVariableName;
    private char variableLimit;
    private Map<String, char[]> variableNames;
    private char variableNext;
    private List<Object> variablesVector;

    /* access modifiers changed from: private */
    public class ParseData implements SymbolTable {
        private ParseData() {
        }

        @Override // android.icu.text.SymbolTable
        public char[] lookup(String name) {
            return (char[]) TransliteratorParser.this.variableNames.get(name);
        }

        @Override // android.icu.text.SymbolTable
        public UnicodeMatcher lookupMatcher(int ch) {
            int i = ch - TransliteratorParser.this.curData.variablesBase;
            if (i < 0 || i >= TransliteratorParser.this.variablesVector.size()) {
                return null;
            }
            return (UnicodeMatcher) TransliteratorParser.this.variablesVector.get(i);
        }

        @Override // android.icu.text.SymbolTable
        public String parseReference(String text, ParsePosition pos, int limit) {
            int start = pos.getIndex();
            int i = start;
            while (i < limit) {
                char c = text.charAt(i);
                if ((i == start && !UCharacter.isUnicodeIdentifierStart(c)) || !UCharacter.isUnicodeIdentifierPart(c)) {
                    break;
                }
                i++;
            }
            if (i == start) {
                return null;
            }
            pos.setIndex(i);
            return text.substring(start, i);
        }

        public boolean isMatcher(int ch) {
            int i = ch - TransliteratorParser.this.curData.variablesBase;
            if (i < 0 || i >= TransliteratorParser.this.variablesVector.size()) {
                return true;
            }
            return TransliteratorParser.this.variablesVector.get(i) instanceof UnicodeMatcher;
        }

        public boolean isReplacer(int ch) {
            int i = ch - TransliteratorParser.this.curData.variablesBase;
            if (i < 0 || i >= TransliteratorParser.this.variablesVector.size()) {
                return true;
            }
            return TransliteratorParser.this.variablesVector.get(i) instanceof UnicodeReplacer;
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
            String s;
            String s2 = handleNextLine();
            if (s2 == null || s2.length() <= 0 || s2.charAt(s2.length() - 1) != '\\') {
                return s2;
            }
            StringBuilder b = new StringBuilder(s2);
            do {
                b.deleteCharAt(b.length() - 1);
                s = handleNextLine();
                if (s != null) {
                    b.append(s);
                    if (s.length() <= 0) {
                        break;
                    }
                } else {
                    break;
                }
            } while (s.charAt(s.length() - 1) == '\\');
            return b.toString();
        }
    }

    /* access modifiers changed from: private */
    public static class RuleArray extends RuleBody {
        String[] array;
        int i = 0;

        public RuleArray(String[] array2) {
            super();
            this.array = array2;
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

        public int parse(String rule, int pos, int limit, TransliteratorParser parser) {
            StringBuffer buf = new StringBuffer();
            int pos2 = parseSection(rule, pos, limit, parser, buf, TransliteratorParser.ILLEGAL_TOP, false);
            this.text = buf.toString();
            if (this.cursorOffset > 0 && this.cursor != this.cursorOffsetPos) {
                TransliteratorParser.syntaxError("Misplaced |", rule, pos);
            }
            return pos2;
        }

        private int parseSection(String rule, int pos, int limit, TransliteratorParser parser, StringBuffer buf, UnicodeSet illegal, boolean isSegment) {
            int quoteLimit;
            int varLimit;
            int bufStart;
            boolean z;
            int[] iref;
            int[] iref2;
            int pos2;
            boolean z2;
            int pos3;
            ParsePosition pp;
            int[] iref3;
            int quoteLimit2;
            int varLimit2;
            int pos4;
            char c;
            int varLimit3;
            int qlimit;
            int qstart;
            ParsePosition pp2;
            int[] iref4 = new int[1];
            int bufStart2 = buf.length();
            ParsePosition pp3 = null;
            int quoteStart = -1;
            int quoteLimit3 = -1;
            int varStart = -1;
            int varLimit4 = -1;
            int escaped = pos;
            while (escaped < limit) {
                int pos5 = escaped + 1;
                char c2 = rule.charAt(escaped);
                if (PatternProps.isWhiteSpace(c2)) {
                    escaped = pos5;
                } else {
                    if (TransliteratorParser.HALF_ENDERS.indexOf(c2) < 0) {
                        if (this.anchorEnd) {
                            TransliteratorParser.syntaxError("Malformed variable reference", rule, pos);
                        }
                        if (UnicodeSet.resemblesPattern(rule, pos5 - 1)) {
                            if (pp3 == null) {
                                pp2 = new ParsePosition(0);
                            } else {
                                pp2 = pp3;
                            }
                            pp2.setIndex(pos5 - 1);
                            buf.append(parser.parseSet(rule, pp2));
                            pp3 = pp2;
                            escaped = pp2.getIndex();
                        } else if (c2 == '\\') {
                            if (pos5 == limit) {
                                TransliteratorParser.syntaxError("Trailing backslash", rule, pos);
                            }
                            iref4[0] = pos5;
                            int escaped2 = Utility.unescapeAt(rule, iref4);
                            int pos6 = iref4[0];
                            if (escaped2 == -1) {
                                TransliteratorParser.syntaxError("Malformed escape", rule, pos);
                            }
                            parser.checkVariableRange(escaped2, rule, pos);
                            UTF16.append(buf, escaped2);
                            escaped = pos6;
                        } else if (c2 == '\'') {
                            int iq = rule.indexOf(39, pos5);
                            if (iq == pos5) {
                                buf.append(c2);
                                escaped = pos5 + 1;
                            } else {
                                quoteStart = buf.length();
                                while (true) {
                                    if (iq < 0) {
                                        TransliteratorParser.syntaxError("Unterminated quote", rule, pos);
                                    }
                                    buf.append(rule.substring(pos5, iq));
                                    pos5 = iq + 1;
                                    if (pos5 >= limit || rule.charAt(pos5) != '\'') {
                                        quoteLimit3 = buf.length();
                                    } else {
                                        iq = rule.indexOf(39, pos5 + 1);
                                    }
                                }
                                quoteLimit3 = buf.length();
                                for (int iq2 = quoteStart; iq2 < quoteLimit3; iq2++) {
                                    parser.checkVariableRange(buf.charAt(iq2), rule, pos);
                                }
                                escaped = pos5;
                            }
                        } else {
                            parser.checkVariableRange(c2, rule, pos);
                            if (illegal.contains(c2)) {
                                TransliteratorParser.syntaxError("Illegal character '" + c2 + '\'', rule, pos);
                            }
                            if (c2 != '$') {
                                if (c2 != '&') {
                                    if (c2 == '.') {
                                        bufStart = bufStart2;
                                        buf.append(parser.getDotStandIn());
                                        pos2 = pos5;
                                        quoteLimit = quoteLimit3;
                                        varLimit = varLimit4;
                                        iref2 = iref4;
                                        z2 = true;
                                    } else if (c2 == '^') {
                                        bufStart = bufStart2;
                                        if (buf.length() != 0 || this.anchorStart) {
                                            TransliteratorParser.syntaxError("Misplaced anchor start", rule, pos);
                                            pos2 = pos5;
                                            quoteLimit = quoteLimit3;
                                            varLimit = varLimit4;
                                            iref2 = iref4;
                                            z2 = true;
                                        } else {
                                            z2 = true;
                                            this.anchorStart = true;
                                            pos2 = pos5;
                                            quoteLimit = quoteLimit3;
                                            varLimit = varLimit4;
                                            iref2 = iref4;
                                        }
                                    } else if (c2 != 8710) {
                                        if (c2 == '?') {
                                            pos4 = pos5;
                                            varLimit2 = varLimit4;
                                            quoteLimit2 = quoteLimit3;
                                            bufStart = bufStart2;
                                            iref3 = iref4;
                                            c = c2;
                                        } else if (c2 != '@') {
                                            switch (c2) {
                                                case '(':
                                                    int bufSegStart = buf.length();
                                                    int segmentNumber = this.nextSegmentNumber;
                                                    this.nextSegmentNumber = segmentNumber + 1;
                                                    bufStart = bufStart2;
                                                    int pos7 = parseSection(rule, pos5, limit, parser, buf, TransliteratorParser.ILLEGAL_SEG, true);
                                                    parser.setSegmentObject(segmentNumber, new StringMatcher(buf.substring(bufSegStart), segmentNumber, parser.curData));
                                                    buf.setLength(bufSegStart);
                                                    buf.append(parser.getSegmentStandin(segmentNumber));
                                                    escaped = pos7;
                                                    varLimit = varLimit4;
                                                    quoteLimit = quoteLimit3;
                                                    iref = iref4;
                                                    z = true;
                                                    break;
                                                case ')':
                                                    break;
                                                case '*':
                                                case '+':
                                                    pos4 = pos5;
                                                    varLimit2 = varLimit4;
                                                    quoteLimit2 = quoteLimit3;
                                                    bufStart = bufStart2;
                                                    iref3 = iref4;
                                                    c = c2;
                                                    break;
                                                default:
                                                    switch (c2) {
                                                        case '{':
                                                            if (this.ante >= 0) {
                                                                TransliteratorParser.syntaxError("Multiple ante contexts", rule, pos);
                                                            }
                                                            this.ante = buf.length();
                                                            pos2 = pos5;
                                                            varLimit = varLimit4;
                                                            quoteLimit = quoteLimit3;
                                                            bufStart = bufStart2;
                                                            iref2 = iref4;
                                                            z2 = true;
                                                            break;
                                                        case '|':
                                                            if (this.cursor >= 0) {
                                                                TransliteratorParser.syntaxError("Multiple cursors", rule, pos);
                                                            }
                                                            this.cursor = buf.length();
                                                            pos2 = pos5;
                                                            varLimit = varLimit4;
                                                            quoteLimit = quoteLimit3;
                                                            bufStart = bufStart2;
                                                            iref2 = iref4;
                                                            z2 = true;
                                                            break;
                                                        case '}':
                                                            if (this.post >= 0) {
                                                                TransliteratorParser.syntaxError("Multiple post contexts", rule, pos);
                                                            }
                                                            this.post = buf.length();
                                                            pos2 = pos5;
                                                            varLimit = varLimit4;
                                                            quoteLimit = quoteLimit3;
                                                            bufStart = bufStart2;
                                                            iref2 = iref4;
                                                            z2 = true;
                                                            break;
                                                        default:
                                                            if (c2 >= '!' && c2 <= '~' && ((c2 < '0' || c2 > '9') && ((c2 < 'A' || c2 > 'Z') && (c2 < 'a' || c2 > 'z')))) {
                                                                TransliteratorParser.syntaxError("Unquoted " + c2, rule, pos);
                                                            }
                                                            buf.append(c2);
                                                            pos2 = pos5;
                                                            varLimit = varLimit4;
                                                            quoteLimit = quoteLimit3;
                                                            bufStart = bufStart2;
                                                            iref2 = iref4;
                                                            z2 = true;
                                                            break;
                                                    }
                                            }
                                            iref4 = iref;
                                            bufStart2 = bufStart;
                                            varLimit4 = varLimit;
                                            quoteLimit3 = quoteLimit;
                                        } else {
                                            bufStart = bufStart2;
                                            int i = this.cursorOffset;
                                            if (i < 0) {
                                                if (buf.length() > 0) {
                                                    TransliteratorParser.syntaxError("Misplaced " + c2, rule, pos);
                                                }
                                                this.cursorOffset--;
                                                pos2 = pos5;
                                                varLimit = varLimit4;
                                                quoteLimit = quoteLimit3;
                                                iref2 = iref4;
                                                z2 = true;
                                            } else if (i > 0) {
                                                if (buf.length() != this.cursorOffsetPos || this.cursor >= 0) {
                                                    TransliteratorParser.syntaxError("Misplaced " + c2, rule, pos);
                                                }
                                                this.cursorOffset++;
                                                pos2 = pos5;
                                                varLimit = varLimit4;
                                                quoteLimit = quoteLimit3;
                                                iref2 = iref4;
                                                z2 = true;
                                            } else if (this.cursor == 0 && buf.length() == 0) {
                                                this.cursorOffset = -1;
                                                pos2 = pos5;
                                                varLimit = varLimit4;
                                                quoteLimit = quoteLimit3;
                                                iref2 = iref4;
                                                z2 = true;
                                            } else if (this.cursor < 0) {
                                                this.cursorOffsetPos = buf.length();
                                                z2 = true;
                                                this.cursorOffset = 1;
                                                pos2 = pos5;
                                                varLimit = varLimit4;
                                                quoteLimit = quoteLimit3;
                                                iref2 = iref4;
                                            } else {
                                                TransliteratorParser.syntaxError("Misplaced " + c2, rule, pos);
                                                pos2 = pos5;
                                                varLimit = varLimit4;
                                                quoteLimit = quoteLimit3;
                                                iref2 = iref4;
                                                z2 = true;
                                            }
                                        }
                                        if (!isSegment || buf.length() != bufStart) {
                                            if (buf.length() == quoteLimit2) {
                                                qstart = quoteStart;
                                                qlimit = quoteLimit2;
                                                varLimit3 = varLimit2;
                                            } else {
                                                varLimit3 = varLimit2;
                                                if (buf.length() == varLimit3) {
                                                    qstart = varStart;
                                                    qlimit = varLimit3;
                                                } else {
                                                    qstart = buf.length() - 1;
                                                    qlimit = qstart + 1;
                                                }
                                            }
                                            try {
                                                UnicodeMatcher m = new StringMatcher(buf.toString(), qstart, qlimit, 0, parser.curData);
                                                int min = 0;
                                                int max = Integer.MAX_VALUE;
                                                if (c == '+') {
                                                    min = 1;
                                                } else if (c == '?') {
                                                    min = 0;
                                                    max = 1;
                                                }
                                                UnicodeMatcher m2 = new Quantifier(m, min, max);
                                                buf.setLength(qstart);
                                                buf.append(parser.generateStandInFor(m2));
                                                varLimit = varLimit3;
                                                quoteLimit = quoteLimit2;
                                                pos2 = pos4;
                                                iref2 = iref3;
                                                z2 = true;
                                            } catch (RuntimeException e) {
                                                throw new IllegalIcuArgumentException("Failure in rule: " + (pos4 < 50 ? rule.substring(0, pos4) : "..." + rule.substring(pos4 - 50, pos4)) + "$$$" + (limit - pos4 <= 50 ? rule.substring(pos4, limit) : rule.substring(pos4, pos4 + 50) + "...")).initCause((Throwable) e);
                                            }
                                        } else {
                                            TransliteratorParser.syntaxError("Misplaced quantifier", rule, pos);
                                            pos2 = pos4;
                                            varLimit = varLimit2;
                                            quoteLimit = quoteLimit2;
                                            iref2 = iref3;
                                            z2 = true;
                                        }
                                    }
                                }
                                bufStart = bufStart2;
                                iref4[0] = pos5;
                                TransliteratorIDParser.SingleID single = TransliteratorIDParser.parseFilterID(rule, iref4);
                                if (single == null || !Utility.parseChar(rule, iref4, TransliteratorParser.SEGMENT_OPEN)) {
                                    TransliteratorParser.syntaxError("Invalid function", rule, pos);
                                }
                                Transliterator t = single.getInstance();
                                if (t == null) {
                                    TransliteratorParser.syntaxError("Invalid function ID", rule, pos);
                                }
                                int bufSegStart2 = buf.length();
                                varLimit = varLimit4;
                                quoteLimit = quoteLimit3;
                                int pos8 = parseSection(rule, iref4[0], limit, parser, buf, TransliteratorParser.ILLEGAL_FUNC, true);
                                FunctionReplacer r = new FunctionReplacer(t, new StringReplacer(buf.substring(bufSegStart2), parser.curData));
                                buf.setLength(bufSegStart2);
                                buf.append(parser.generateStandInFor(r));
                                escaped = pos8;
                                iref = iref4;
                                z = true;
                                iref4 = iref;
                                bufStart2 = bufStart;
                                varLimit4 = varLimit;
                                quoteLimit3 = quoteLimit;
                            } else {
                                varLimit = varLimit4;
                                quoteLimit = quoteLimit3;
                                bufStart = bufStart2;
                                iref2 = iref4;
                                pos2 = pos5;
                                if (pos2 == limit) {
                                    z2 = true;
                                    this.anchorEnd = true;
                                } else {
                                    int r2 = UCharacter.digit(rule.charAt(pos2), 10);
                                    if (r2 < 1 || r2 > 9) {
                                        iref = iref2;
                                        if (pp3 == null) {
                                            pp = new ParsePosition(0);
                                        } else {
                                            pp = pp3;
                                        }
                                        pp.setIndex(pos2);
                                        String name = parser.parseData.parseReference(rule, pp, limit);
                                        if (name == null) {
                                            z = true;
                                            this.anchorEnd = true;
                                            pp3 = pp;
                                            escaped = pos2;
                                            iref4 = iref;
                                            bufStart2 = bufStart;
                                            varLimit4 = varLimit;
                                            quoteLimit3 = quoteLimit;
                                        } else {
                                            z = true;
                                            pos3 = pp.getIndex();
                                            varStart = buf.length();
                                            parser.appendVariableDef(name, buf);
                                            varLimit = buf.length();
                                        }
                                    } else {
                                        iref = iref2;
                                        iref[0] = pos2;
                                        int r3 = Utility.parseNumber(rule, iref, 10);
                                        if (r3 < 0) {
                                            TransliteratorParser.syntaxError("Undefined segment reference", rule, pos);
                                        }
                                        pos3 = iref[0];
                                        buf.append(parser.getSegmentStandin(r3));
                                        pp = pp3;
                                        z = true;
                                    }
                                    pp3 = pp;
                                    escaped = pos3;
                                    iref4 = iref;
                                    bufStart2 = bufStart;
                                    varLimit4 = varLimit;
                                    quoteLimit3 = quoteLimit;
                                }
                            }
                            z = z2;
                            escaped = pos2;
                            iref = iref2;
                            iref4 = iref;
                            bufStart2 = bufStart;
                            varLimit4 = varLimit;
                            quoteLimit3 = quoteLimit;
                        }
                    } else if (isSegment) {
                        TransliteratorParser.syntaxError("Unclosed segment", rule, pos);
                    }
                    return pos5;
                }
            }
            return escaped;
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

        public boolean isValidOutput(TransliteratorParser parser) {
            int i = 0;
            while (i < this.text.length()) {
                int c = UTF16.charAt(this.text, i);
                i += UTF16.getCharCount(c);
                if (!parser.parseData.isReplacer(c)) {
                    return false;
                }
            }
            return true;
        }

        public boolean isValidInput(TransliteratorParser parser) {
            int i = 0;
            while (i < this.text.length()) {
                int c = UTF16.charAt(this.text, i);
                i += UTF16.getCharCount(c);
                if (!parser.parseData.isMatcher(c)) {
                    return false;
                }
            }
            return true;
        }
    }

    public void parse(String rules, int dir) {
        parseRules(new RuleArray(new String[]{rules}), dir);
    }

    /* access modifiers changed from: package-private */
    public void parseRules(RuleBody ruleArray, int dir) {
        boolean parsingIDs;
        RuntimeException previous;
        int i;
        RuleBasedTransliterator.Data data;
        int i2;
        int ppp;
        int i3;
        boolean parsingIDs2 = true;
        int ruleCount = 0;
        this.dataVector = new ArrayList();
        this.idBlockVector = new ArrayList();
        this.curData = null;
        this.direction = dir;
        this.compoundFilter = null;
        this.variablesVector = new ArrayList();
        this.variableNames = new HashMap();
        this.parseData = new ParseData();
        List<RuntimeException> errors = new ArrayList<>();
        int errorCount = 0;
        ruleArray.reset();
        StringBuilder idBlockResult = new StringBuilder();
        this.compoundFilter = null;
        int compoundFilterOffset = -1;
        loop0:
        while (true) {
            String rule = ruleArray.nextLine();
            int i4 = 0;
            int i5 = 1;
            if (rule == null) {
                parsingIDs = parsingIDs2;
                break;
            }
            int pos = 0;
            int limit = rule.length();
            int errorCount2 = errorCount;
            parsingIDs = parsingIDs2;
            while (pos < limit) {
                int pos2 = pos + 1;
                char c = rule.charAt(pos);
                if (!PatternProps.isWhiteSpace(c)) {
                    if (c == '#') {
                        int pos3 = rule.indexOf("\n", pos2) + 1;
                        if (pos3 == 0) {
                            break;
                        }
                        pos = pos3;
                    } else if (c != ';') {
                        ruleCount++;
                        int pos4 = pos2 - 1;
                        if (pos4 + 2 + i5 <= limit) {
                            try {
                                if (rule.regionMatches(pos4, ID_TOKEN, i4, 2)) {
                                    int pos5 = pos4 + 2;
                                    char c2 = rule.charAt(pos5);
                                    while (PatternProps.isWhiteSpace(c2) && pos5 < limit) {
                                        pos5++;
                                        c2 = rule.charAt(pos5);
                                    }
                                    int[] p = new int[i5];
                                    p[i4] = pos5;
                                    if (!parsingIDs) {
                                        if (this.curData != null) {
                                            if (this.direction == 0) {
                                                this.dataVector.add(this.curData);
                                            } else {
                                                this.dataVector.add(i4, this.curData);
                                            }
                                            this.curData = null;
                                        }
                                        parsingIDs = true;
                                    }
                                    TransliteratorIDParser.SingleID id = TransliteratorIDParser.parseSingleID(rule, p, this.direction);
                                    if (p[i4] == pos5 || !Utility.parseChar(rule, p, END_OF_RULE)) {
                                        int[] withParens = {-1};
                                        UnicodeSet f = TransliteratorIDParser.parseGlobalFilter(rule, p, this.direction, withParens, null);
                                        if (f == null || !Utility.parseChar(rule, p, END_OF_RULE)) {
                                            syntaxError("Invalid ::ID", rule, pos5);
                                        } else if ((this.direction == 0) == (withParens[0] == 0)) {
                                            if (this.compoundFilter != null) {
                                                syntaxError("Multiple global filters", rule, pos5);
                                            }
                                            this.compoundFilter = f;
                                            compoundFilterOffset = ruleCount;
                                        }
                                    } else if (this.direction == 0) {
                                        idBlockResult.append(id.canonID);
                                        idBlockResult.append(END_OF_RULE);
                                    } else {
                                        idBlockResult.insert(0, id.canonID + END_OF_RULE);
                                    }
                                    ppp = p[0];
                                    pos = ppp;
                                    i2 = 0;
                                    i5 = 1;
                                    i4 = i2;
                                }
                            } catch (IllegalArgumentException e) {
                                if (errorCount2 == 30) {
                                    IllegalIcuArgumentException icuEx = new IllegalIcuArgumentException("\nMore than 30 errors; further messages squelched");
                                    icuEx.initCause((Throwable) e);
                                    errors.add(icuEx);
                                    break loop0;
                                }
                                i2 = 0;
                                e.fillInStackTrace();
                                errors.add(e);
                                errorCount2++;
                                i5 = 1;
                                pos = ruleEnd(rule, pos4, limit) + 1;
                            }
                        }
                        if (parsingIDs) {
                            if (this.direction == 0) {
                                this.idBlockVector.add(idBlockResult.toString());
                                i3 = 0;
                            } else {
                                i3 = 0;
                                this.idBlockVector.add(0, idBlockResult.toString());
                            }
                            idBlockResult.delete(i3, idBlockResult.length());
                            parsingIDs = false;
                            this.curData = new RuleBasedTransliterator.Data();
                            setVariableRange(61440, 63743);
                        }
                        if (resemblesPragma(rule, pos4, limit)) {
                            ppp = parsePragma(rule, pos4, limit);
                            if (ppp < 0) {
                                syntaxError("Unrecognized pragma", rule, pos4);
                            }
                        } else {
                            ppp = parseRule(rule, pos4, limit);
                        }
                        pos = ppp;
                        i2 = 0;
                        i5 = 1;
                        i4 = i2;
                    }
                }
                pos = pos2;
            }
            parsingIDs2 = parsingIDs;
            errorCount = errorCount2;
        }
        if (!parsingIDs || idBlockResult.length() <= 0) {
            if (!parsingIDs && (data = this.curData) != null) {
                if (this.direction == 0) {
                    this.dataVector.add(data);
                } else {
                    this.dataVector.add(0, data);
                }
            }
        } else if (this.direction == 0) {
            this.idBlockVector.add(idBlockResult.toString());
        } else {
            this.idBlockVector.add(0, idBlockResult.toString());
        }
        for (int i6 = 0; i6 < this.dataVector.size(); i6++) {
            RuleBasedTransliterator.Data data2 = this.dataVector.get(i6);
            data2.variables = new Object[this.variablesVector.size()];
            this.variablesVector.toArray(data2.variables);
            data2.variableNames = new HashMap();
            data2.variableNames.putAll(this.variableNames);
        }
        this.variablesVector = null;
        try {
            if (this.compoundFilter != null) {
                if (this.direction == 0) {
                    i = 1;
                    if (compoundFilterOffset == 1) {
                    }
                    throw new IllegalIcuArgumentException("Compound filters misplaced");
                }
                i = 1;
                if (this.direction == i) {
                    if (compoundFilterOffset == ruleCount) {
                    }
                    throw new IllegalIcuArgumentException("Compound filters misplaced");
                }
            }
            for (int i7 = 0; i7 < this.dataVector.size(); i7++) {
                this.dataVector.get(i7).ruleSet.freeze();
            }
            if (this.idBlockVector.size() == 1 && this.idBlockVector.get(0).length() == 0) {
                this.idBlockVector.remove(0);
            }
        } catch (IllegalArgumentException e2) {
            e2.fillInStackTrace();
            errors.add(e2);
        }
        if (errors.size() != 0) {
            for (int i8 = errors.size() - 1; i8 > 0; i8--) {
                RuntimeException previous2 = errors.get(i8 - 1);
                while (true) {
                    previous = (RuntimeException) previous2;
                    if (previous.getCause() == null) {
                        break;
                    }
                    previous2 = previous.getCause();
                }
                previous.initCause(errors.get(i8));
            }
            throw errors.get(0);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0039, code lost:
        if (android.icu.text.TransliteratorParser.OPERATORS.indexOf(r8) < 0) goto L_0x003b;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int parseRule(java.lang.String r23, int r24, int r25) {
        /*
        // Method dump skipped, instructions count: 501
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.text.TransliteratorParser.parseRule(java.lang.String, int, int):int");
    }

    private void setVariableRange(int start, int end) {
        if (start > end || start < 0 || end > 65535) {
            throw new IllegalIcuArgumentException("Invalid variable range " + start + ", " + end);
        }
        this.curData.variablesBase = (char) start;
        if (this.dataVector.size() == 0) {
            this.variableNext = (char) start;
            this.variableLimit = (char) (end + 1);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void checkVariableRange(int ch, String rule, int start) {
        if (ch >= this.curData.variablesBase && ch < this.variableLimit) {
            syntaxError("Variable range character in rule", rule, start);
        }
    }

    private void pragmaMaximumBackup(int backup) {
        throw new IllegalIcuArgumentException("use maximum backup pragma not implemented yet");
    }

    private void pragmaNormalizeRules(Normalizer.Mode mode) {
        throw new IllegalIcuArgumentException("use normalize rules pragma not implemented yet");
    }

    static boolean resemblesPragma(String rule, int pos, int limit) {
        return Utility.parsePattern(rule, pos, limit, "use ", null) >= 0;
    }

    private int parsePragma(String rule, int pos, int limit) {
        int[] array = new int[2];
        int pos2 = pos + 4;
        int p = Utility.parsePattern(rule, pos2, limit, "~variable range # #~;", array);
        if (p >= 0) {
            setVariableRange(array[0], array[1]);
            return p;
        }
        int p2 = Utility.parsePattern(rule, pos2, limit, "~maximum backup #~;", array);
        if (p2 >= 0) {
            pragmaMaximumBackup(array[0]);
            return p2;
        }
        int p3 = Utility.parsePattern(rule, pos2, limit, "~nfd rules~;", null);
        if (p3 >= 0) {
            pragmaNormalizeRules(Normalizer.NFD);
            return p3;
        }
        int p4 = Utility.parsePattern(rule, pos2, limit, "~nfc rules~;", null);
        if (p4 < 0) {
            return -1;
        }
        pragmaNormalizeRules(Normalizer.NFC);
        return p4;
    }

    static final void syntaxError(String msg, String rule, int start) {
        int end = ruleEnd(rule, start, rule.length());
        throw new IllegalIcuArgumentException(msg + " in \"" + Utility.escape(rule.substring(start, end)) + '\"');
    }

    static final int ruleEnd(String rule, int start, int limit) {
        int end = Utility.quotedIndexOf(rule, start, limit, ";");
        return end < 0 ? limit : end;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private final char parseSet(String rule, ParsePosition pos) {
        UnicodeSet set = new UnicodeSet(rule, pos, this.parseData);
        if (this.variableNext < this.variableLimit) {
            set.compact();
            return generateStandInFor(set);
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

    public char getSegmentStandin(int seg) {
        if (this.segmentStandins.length() < seg) {
            this.segmentStandins.setLength(seg);
        }
        char c = this.segmentStandins.charAt(seg - 1);
        if (c != 0) {
            return c;
        }
        char c2 = this.variableNext;
        if (c2 < this.variableLimit) {
            this.variableNext = (char) (c2 + 1);
            this.variablesVector.add(null);
            this.segmentStandins.setCharAt(seg - 1, c2);
            return c2;
        }
        throw new RuntimeException("Variable range exhausted");
    }

    public void setSegmentObject(int seg, StringMatcher obj) {
        while (this.segmentObjects.size() < seg) {
            this.segmentObjects.add(null);
        }
        int index = getSegmentStandin(seg) - this.curData.variablesBase;
        if (this.segmentObjects.get(seg - 1) == null && this.variablesVector.get(index) == null) {
            this.segmentObjects.set(seg - 1, obj);
            this.variablesVector.set(index, obj);
            return;
        }
        throw new RuntimeException();
    }

    /* access modifiers changed from: package-private */
    public char getDotStandIn() {
        if (this.dotStandIn == -1) {
            this.dotStandIn = generateStandInFor(new UnicodeSet(DOT_SET));
        }
        return (char) this.dotStandIn;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void appendVariableDef(String name, StringBuffer buf) {
        char[] ch = this.variableNames.get(name);
        if (ch != null) {
            buf.append(ch);
        } else if (this.undefinedVariableName == null) {
            this.undefinedVariableName = name;
            char c = this.variableNext;
            char c2 = this.variableLimit;
            if (c < c2) {
                char c3 = (char) (c2 - 1);
                this.variableLimit = c3;
                buf.append(c3);
                return;
            }
            throw new RuntimeException("Private use variables exhausted");
        } else {
            throw new IllegalIcuArgumentException("Undefined variable $" + name);
        }
    }
}
