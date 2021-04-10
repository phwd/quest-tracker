package android.icu.text;

/* access modifiers changed from: package-private */
public class RBBIRuleParseTable {
    static final short doCheckVarDef = 1;
    static final short doDotAny = 2;
    static final short doEndAssign = 3;
    static final short doEndOfRule = 4;
    static final short doEndVariableName = 5;
    static final short doExit = 6;
    static final short doExprCatOperator = 7;
    static final short doExprFinished = 8;
    static final short doExprOrOperator = 9;
    static final short doExprRParen = 10;
    static final short doExprStart = 11;
    static final short doLParen = 12;
    static final short doNOP = 13;
    static final short doNoChain = 14;
    static final short doOptionEnd = 15;
    static final short doOptionStart = 16;
    static final short doReverseDir = 17;
    static final short doRuleChar = 18;
    static final short doRuleError = 19;
    static final short doRuleErrorAssignExpr = 20;
    static final short doScanUnicodeSet = 21;
    static final short doSlash = 22;
    static final short doStartAssign = 23;
    static final short doStartTagValue = 24;
    static final short doStartVariableName = 25;
    static final short doTagDigit = 26;
    static final short doTagExpectedError = 27;
    static final short doTagValue = 28;
    static final short doUnaryOpPlus = 29;
    static final short doUnaryOpQuestion = 30;
    static final short doUnaryOpStar = 31;
    static final short doVariableNameExpectedErr = 32;
    static RBBIRuleTableElement[] gRuleParseStateTable = {new RBBIRuleTableElement(13, 0, 0, 0, true, null), new RBBIRuleTableElement(11, 254, 29, 9, false, "start"), new RBBIRuleTableElement(13, 132, 1, 0, true, null), new RBBIRuleTableElement(14, 94, 12, 9, true, null), new RBBIRuleTableElement(11, 36, 88, 98, false, null), new RBBIRuleTableElement(13, 33, 19, 0, true, null), new RBBIRuleTableElement(13, 59, 1, 0, true, null), new RBBIRuleTableElement(13, 252, 0, 0, false, null), new RBBIRuleTableElement(11, 255, 29, 9, false, null), new RBBIRuleTableElement(4, 59, 1, 0, true, "break-rule-end"), new RBBIRuleTableElement(13, 132, 9, 0, true, null), new RBBIRuleTableElement(doRuleError, 255, 103, 0, false, null), new RBBIRuleTableElement(11, 254, 29, 0, false, "start-after-caret"), new RBBIRuleTableElement(13, 132, 12, 0, true, null), new RBBIRuleTableElement(doRuleError, 94, 103, 0, false, null), new RBBIRuleTableElement(11, 36, 88, 37, false, null), new RBBIRuleTableElement(doRuleError, 59, 103, 0, false, null), new RBBIRuleTableElement(doRuleError, 252, 103, 0, false, null), new RBBIRuleTableElement(11, 255, 29, 0, false, null), new RBBIRuleTableElement(13, 33, 21, 0, true, "rev-option"), new RBBIRuleTableElement(17, 255, 28, 9, false, null), new RBBIRuleTableElement(16, 130, 23, 0, true, "option-scan1"), new RBBIRuleTableElement(doRuleError, 255, 103, 0, false, null), new RBBIRuleTableElement(13, 129, 23, 0, true, "option-scan2"), new RBBIRuleTableElement(15, 255, 25, 0, false, null), new RBBIRuleTableElement(13, 59, 1, 0, true, "option-scan3"), new RBBIRuleTableElement(13, 132, 25, 0, true, null), new RBBIRuleTableElement(doRuleError, 255, 103, 0, false, null), new RBBIRuleTableElement(11, 255, 29, 9, false, "reverse-rule"), new RBBIRuleTableElement(doRuleChar, 254, 38, 0, true, "term"), new RBBIRuleTableElement(13, 132, 29, 0, true, null), new RBBIRuleTableElement(doRuleChar, 131, 38, 0, true, null), new RBBIRuleTableElement(13, 91, 94, 38, false, null), new RBBIRuleTableElement(12, 40, 29, 38, true, null), new RBBIRuleTableElement(13, 36, 88, 37, false, null), new RBBIRuleTableElement(2, 46, 38, 0, true, null), new RBBIRuleTableElement(doRuleError, 255, 103, 0, false, null), new RBBIRuleTableElement(1, 255, 38, 0, false, "term-var-ref"), new RBBIRuleTableElement(13, 132, 38, 0, true, "expr-mod"), new RBBIRuleTableElement(doUnaryOpStar, 42, 43, 0, true, null), new RBBIRuleTableElement(doUnaryOpPlus, 43, 43, 0, true, null), new RBBIRuleTableElement(doUnaryOpQuestion, 63, 43, 0, true, null), new RBBIRuleTableElement(13, 255, 43, 0, false, null), new RBBIRuleTableElement(7, 254, 29, 0, false, "expr-cont"), new RBBIRuleTableElement(13, 132, 43, 0, true, null), new RBBIRuleTableElement(7, 131, 29, 0, false, null), new RBBIRuleTableElement(7, 91, 29, 0, false, null), new RBBIRuleTableElement(7, 40, 29, 0, false, null), new RBBIRuleTableElement(7, 36, 29, 0, false, null), new RBBIRuleTableElement(7, 46, 29, 0, false, null), new RBBIRuleTableElement(7, 47, 55, 0, false, null), new RBBIRuleTableElement(7, 123, 67, 0, true, null), new RBBIRuleTableElement(9, 124, 29, 0, true, null), new RBBIRuleTableElement(10, 41, 255, 0, true, null), new RBBIRuleTableElement(8, 255, 255, 0, false, null), new RBBIRuleTableElement(doSlash, 47, 57, 0, true, "look-ahead"), new RBBIRuleTableElement(13, 255, 103, 0, false, null), new RBBIRuleTableElement(7, 254, 29, 0, false, "expr-cont-no-slash"), new RBBIRuleTableElement(13, 132, 43, 0, true, null), new RBBIRuleTableElement(7, 131, 29, 0, false, null), new RBBIRuleTableElement(7, 91, 29, 0, false, null), new RBBIRuleTableElement(7, 40, 29, 0, false, null), new RBBIRuleTableElement(7, 36, 29, 0, false, null), new RBBIRuleTableElement(7, 46, 29, 0, false, null), new RBBIRuleTableElement(9, 124, 29, 0, true, null), new RBBIRuleTableElement(10, 41, 255, 0, true, null), new RBBIRuleTableElement(8, 255, 255, 0, false, null), new RBBIRuleTableElement(13, 132, 67, 0, true, "tag-open"), new RBBIRuleTableElement(doStartTagValue, 128, 70, 0, false, null), new RBBIRuleTableElement(doTagExpectedError, 255, 103, 0, false, null), new RBBIRuleTableElement(13, 132, 74, 0, true, "tag-value"), new RBBIRuleTableElement(13, 125, 74, 0, false, null), new RBBIRuleTableElement(doTagDigit, 128, 70, 0, true, null), new RBBIRuleTableElement(doTagExpectedError, 255, 103, 0, false, null), new RBBIRuleTableElement(13, 132, 74, 0, true, "tag-close"), new RBBIRuleTableElement(doTagValue, 125, 77, 0, true, null), new RBBIRuleTableElement(doTagExpectedError, 255, 103, 0, false, null), new RBBIRuleTableElement(7, 254, 29, 0, false, "expr-cont-no-tag"), new RBBIRuleTableElement(13, 132, 77, 0, true, null), new RBBIRuleTableElement(7, 131, 29, 0, false, null), new RBBIRuleTableElement(7, 91, 29, 0, false, null), new RBBIRuleTableElement(7, 40, 29, 0, false, null), new RBBIRuleTableElement(7, 36, 29, 0, false, null), new RBBIRuleTableElement(7, 46, 29, 0, false, null), new RBBIRuleTableElement(7, 47, 55, 0, false, null), new RBBIRuleTableElement(9, 124, 29, 0, true, null), new RBBIRuleTableElement(10, 41, 255, 0, true, null), new RBBIRuleTableElement(8, 255, 255, 0, false, null), new RBBIRuleTableElement(doStartVariableName, 36, 90, 0, true, "scan-var-name"), new RBBIRuleTableElement(13, 255, 103, 0, false, null), new RBBIRuleTableElement(13, 130, 92, 0, true, "scan-var-start"), new RBBIRuleTableElement(32, 255, 103, 0, false, null), new RBBIRuleTableElement(13, 129, 92, 0, true, "scan-var-body"), new RBBIRuleTableElement(5, 255, 255, 0, false, null), new RBBIRuleTableElement(doScanUnicodeSet, 91, 255, 0, true, "scan-unicode-set"), new RBBIRuleTableElement(doScanUnicodeSet, 112, 255, 0, true, null), new RBBIRuleTableElement(doScanUnicodeSet, 80, 255, 0, true, null), new RBBIRuleTableElement(13, 255, 103, 0, false, null), new RBBIRuleTableElement(13, 132, 98, 0, true, "assign-or-rule"), new RBBIRuleTableElement(doStartAssign, 61, 29, 101, true, null), new RBBIRuleTableElement(13, 255, 37, 9, false, null), new RBBIRuleTableElement(3, 59, 1, 0, true, "assign-end"), new RBBIRuleTableElement(doRuleErrorAssignExpr, 255, 103, 0, false, null), new RBBIRuleTableElement(6, 255, 103, 0, true, "errorDeath")};
    static final short kRuleSet_default = 255;
    static final short kRuleSet_digit_char = 128;
    static final short kRuleSet_eof = 252;
    static final short kRuleSet_escaped = 254;
    static final short kRuleSet_name_char = 129;
    static final short kRuleSet_name_start_char = 130;
    static final short kRuleSet_rule_char = 131;
    static final short kRuleSet_white_space = 132;

    RBBIRuleParseTable() {
    }

    /* access modifiers changed from: package-private */
    public static class RBBIRuleTableElement {
        short fAction;
        short fCharClass;
        boolean fNextChar;
        short fNextState;
        short fPushState;
        String fStateName;

        RBBIRuleTableElement(short a, int cc, int ns, int ps, boolean nc, String sn) {
            this.fAction = a;
            this.fCharClass = (short) cc;
            this.fNextState = (short) ns;
            this.fPushState = (short) ps;
            this.fNextChar = nc;
            this.fStateName = sn;
        }
    }
}
