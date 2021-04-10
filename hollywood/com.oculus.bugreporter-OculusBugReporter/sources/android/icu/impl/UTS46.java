package android.icu.impl;

import android.icu.impl.Normalizer2Impl;
import android.icu.lang.UCharacter;
import android.icu.lang.UScript;
import android.icu.text.IDNA;
import android.icu.text.Normalizer2;
import java.util.EnumSet;

public final class UTS46 extends IDNA {
    private static final int EN_AN_MASK = (U_MASK(5) | U_MASK(2));
    private static final int ES_CS_ET_ON_BN_NSM_MASK = (((((U_MASK(3) | U_MASK(6)) | U_MASK(4)) | U_MASK(10)) | U_MASK(18)) | U_MASK(17));
    private static final int L_EN_ES_CS_ET_ON_BN_NSM_MASK;
    private static final int L_EN_MASK = (L_MASK | U_MASK(2));
    private static final int L_MASK = U_MASK(0);
    private static final int L_R_AL_MASK;
    private static final int R_AL_AN_EN_ES_CS_ET_ON_BN_NSM_MASK;
    private static final int R_AL_AN_MASK;
    private static final int R_AL_EN_AN_MASK = (R_AL_MASK | EN_AN_MASK);
    private static final int R_AL_MASK = (U_MASK(1) | U_MASK(13));
    private static int U_GC_M_MASK = ((U_MASK(6) | U_MASK(7)) | U_MASK(8));
    private static final byte[] asciiData = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, -1, -1, -1, -1, -1, -1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, -1, -1, -1, -1, -1, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, -1, -1, -1, -1};
    private static final EnumSet<IDNA.Error> severeErrors = EnumSet.of(IDNA.Error.LEADING_COMBINING_MARK, IDNA.Error.DISALLOWED, IDNA.Error.PUNYCODE, IDNA.Error.LABEL_HAS_DOT, IDNA.Error.INVALID_ACE_LABEL);
    private static final Normalizer2 uts46Norm2 = Normalizer2.getInstance(null, "uts46", Normalizer2.Mode.COMPOSE);
    final int options;

    public UTS46(int options2) {
        this.options = options2;
    }

    @Override // android.icu.text.IDNA
    public StringBuilder labelToASCII(CharSequence label, StringBuilder dest, IDNA.Info info) {
        return process(label, true, true, dest, info);
    }

    @Override // android.icu.text.IDNA
    public StringBuilder labelToUnicode(CharSequence label, StringBuilder dest, IDNA.Info info) {
        return process(label, true, false, dest, info);
    }

    @Override // android.icu.text.IDNA
    public StringBuilder nameToASCII(CharSequence name, StringBuilder dest, IDNA.Info info) {
        process(name, false, true, dest, info);
        if (dest.length() >= 254 && !info.getErrors().contains(IDNA.Error.DOMAIN_NAME_TOO_LONG) && isASCIIString(dest) && (dest.length() > 254 || dest.charAt(253) != '.')) {
            addError(info, IDNA.Error.DOMAIN_NAME_TOO_LONG);
        }
        return dest;
    }

    @Override // android.icu.text.IDNA
    public StringBuilder nameToUnicode(CharSequence name, StringBuilder dest, IDNA.Info info) {
        return process(name, false, false, dest, info);
    }

    static {
        int i = L_MASK;
        int i2 = R_AL_MASK;
        L_R_AL_MASK = i | i2;
        R_AL_AN_MASK = i2 | U_MASK(5);
        int i3 = L_EN_MASK;
        int i4 = ES_CS_ET_ON_BN_NSM_MASK;
        L_EN_ES_CS_ET_ON_BN_NSM_MASK = i3 | i4;
        R_AL_AN_EN_ES_CS_ET_ON_BN_NSM_MASK = R_AL_MASK | EN_AN_MASK | i4;
    }

    private static boolean isASCIIString(CharSequence dest) {
        int length = dest.length();
        for (int i = 0; i < length; i++) {
            if (dest.charAt(i) > 127) {
                return false;
            }
        }
        return true;
    }

    private StringBuilder process(CharSequence src, boolean isLabel, boolean toASCII, StringBuilder dest, IDNA.Info info) {
        int i;
        if (dest != src) {
            boolean disallowNonLDHDot = false;
            dest.delete(0, Integer.MAX_VALUE);
            resetInfo(info);
            int srcLength = src.length();
            if (srcLength == 0) {
                addError(info, IDNA.Error.EMPTY_LABEL);
                return dest;
            }
            if ((this.options & 2) != 0) {
                disallowNonLDHDot = true;
            }
            int i2 = 0;
            int labelStart = 0;
            while (i2 != srcLength) {
                char c = src.charAt(i2);
                if (c <= 127) {
                    byte b = asciiData[c];
                    if (b > 0) {
                        dest.append((char) (c + ' '));
                    } else if (b >= 0 || !disallowNonLDHDot) {
                        dest.append(c);
                        if (c == '-') {
                            if (i2 == labelStart + 3 && src.charAt(i2 - 1) == '-') {
                                i = i2 + 1;
                                promoteAndResetLabelErrors(info);
                                processUnicode(src, labelStart, i, isLabel, toASCII, dest, info);
                                if (isBiDi(info) && !hasCertainErrors(info, severeErrors) && (!isOkBiDi(info) || (labelStart > 0 && !isASCIIOkBiDi(dest, labelStart)))) {
                                    addError(info, IDNA.Error.BIDI);
                                }
                                return dest;
                            }
                            if (i2 == labelStart) {
                                addLabelError(info, IDNA.Error.LEADING_HYPHEN);
                            }
                            if (i2 + 1 == srcLength || src.charAt(i2 + 1) == '.') {
                                addLabelError(info, IDNA.Error.TRAILING_HYPHEN);
                            }
                        } else if (c != '.') {
                            continue;
                        } else if (isLabel) {
                            i = i2 + 1;
                            promoteAndResetLabelErrors(info);
                            processUnicode(src, labelStart, i, isLabel, toASCII, dest, info);
                            addError(info, IDNA.Error.BIDI);
                            return dest;
                        } else {
                            if (i2 == labelStart) {
                                addLabelError(info, IDNA.Error.EMPTY_LABEL);
                            }
                            if (toASCII && i2 - labelStart > 63) {
                                addLabelError(info, IDNA.Error.LABEL_TOO_LONG);
                            }
                            promoteAndResetLabelErrors(info);
                            labelStart = i2 + 1;
                        }
                    }
                    i2++;
                }
                i = i2;
                promoteAndResetLabelErrors(info);
                processUnicode(src, labelStart, i, isLabel, toASCII, dest, info);
                addError(info, IDNA.Error.BIDI);
                return dest;
            }
            if (toASCII) {
                if (i2 - labelStart > 63) {
                    addLabelError(info, IDNA.Error.LABEL_TOO_LONG);
                }
                if (!isLabel && i2 >= 254 && (i2 > 254 || labelStart < i2)) {
                    addError(info, IDNA.Error.DOMAIN_NAME_TOO_LONG);
                }
            }
            promoteAndResetLabelErrors(info);
            return dest;
        }
        throw new IllegalArgumentException();
    }

    private StringBuilder processUnicode(CharSequence src, int labelStart, int mappingStart, boolean isLabel, boolean toASCII, StringBuilder dest, IDNA.Info info) {
        if (mappingStart == 0) {
            uts46Norm2.normalize(src, dest);
        } else {
            uts46Norm2.normalizeSecondAndAppend(dest, src.subSequence(mappingStart, src.length()));
        }
        boolean doMapDevChars = false;
        if (toASCII) {
            if ((this.options & 16) == 0) {
                doMapDevChars = true;
            }
        } else if ((this.options & 32) == 0) {
            doMapDevChars = true;
        }
        int labelStart2 = labelStart;
        boolean doMapDevChars2 = doMapDevChars;
        int destLength = dest.length();
        int labelLimit = labelStart;
        while (labelLimit < destLength) {
            char c = dest.charAt(labelLimit);
            if (c != '.' || isLabel) {
                if (c >= 223) {
                    if (c <= 8205 && (c == 223 || c == 962 || c >= 8204)) {
                        setTransitionalDifferent(info);
                        if (doMapDevChars2) {
                            destLength = mapDevChars(dest, labelStart2, labelLimit);
                            doMapDevChars2 = false;
                        }
                    } else if (Character.isSurrogate(c)) {
                        if (Normalizer2Impl.UTF16Plus.isSurrogateLead(c)) {
                            if (labelLimit + 1 != destLength && Character.isLowSurrogate(dest.charAt(labelLimit + 1))) {
                            }
                        } else if (labelLimit != labelStart2 && Character.isHighSurrogate(dest.charAt(labelLimit - 1))) {
                        }
                        addLabelError(info, IDNA.Error.DISALLOWED);
                        dest.setCharAt(labelLimit, 65533);
                    }
                }
                labelLimit++;
            } else {
                int labelLength = labelLimit - labelStart2;
                int newLength = processLabel(dest, labelStart2, labelLength, toASCII, info);
                promoteAndResetLabelErrors(info);
                destLength += newLength - labelLength;
                int i = newLength + 1 + labelStart2;
                labelStart2 = i;
                labelLimit = i;
            }
        }
        if (labelStart2 == 0 || labelStart2 < labelLimit) {
            processLabel(dest, labelStart2, labelLimit - labelStart2, toASCII, info);
            promoteAndResetLabelErrors(info);
        }
        return dest;
    }

    private int mapDevChars(StringBuilder dest, int labelStart, int mappingStart) {
        int length = dest.length();
        boolean didMapDevChars = false;
        int i = mappingStart;
        while (i < length) {
            char c = dest.charAt(i);
            if (c == 223) {
                didMapDevChars = true;
                int i2 = i + 1;
                dest.setCharAt(i, 's');
                i = i2 + 1;
                dest.insert(i2, 's');
                length++;
            } else if (c == 962) {
                didMapDevChars = true;
                dest.setCharAt(i, 963);
                i++;
            } else if (c == 8204 || c == 8205) {
                didMapDevChars = true;
                dest.delete(i, i + 1);
                length--;
            } else {
                i++;
            }
        }
        if (!didMapDevChars) {
            return length;
        }
        dest.replace(labelStart, Integer.MAX_VALUE, uts46Norm2.normalize(dest.subSequence(labelStart, dest.length())));
        return dest.length();
    }

    private static boolean isNonASCIIDisallowedSTD3Valid(int c) {
        return c == 8800 || c == 8814 || c == 8815;
    }

    private static int replaceLabel(StringBuilder dest, int destLabelStart, int destLabelLength, CharSequence label, int labelLength) {
        if (label != dest) {
            dest.delete(destLabelStart, destLabelStart + destLabelLength).insert(destLabelStart, label);
        }
        return labelLength;
    }

    /* JADX INFO: Multiple debug info for r6v2 int: [D('c' char), D('i' int)] */
    /* JADX WARNING: Removed duplicated region for block: B:114:0x01dd  */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x0137  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int processLabel(java.lang.StringBuilder r21, int r22, int r23, boolean r24, android.icu.text.IDNA.Info r25) {
        /*
        // Method dump skipped, instructions count: 540
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.impl.UTS46.processLabel(java.lang.StringBuilder, int, int, boolean, android.icu.text.IDNA$Info):int");
    }

    private int markBadACELabel(StringBuilder dest, int labelStart, int labelLength, boolean toASCII, IDNA.Info info) {
        boolean disallowNonLDHDot = (this.options & 2) != 0;
        boolean isASCII = true;
        boolean onlyLDH = true;
        int i = labelStart + 4;
        int limit = labelStart + labelLength;
        do {
            char c = dest.charAt(i);
            if (c > 127) {
                onlyLDH = false;
                isASCII = false;
            } else if (c == '.') {
                addLabelError(info, IDNA.Error.LABEL_HAS_DOT);
                dest.setCharAt(i, 65533);
                onlyLDH = false;
                isASCII = false;
            } else if (asciiData[c] < 0) {
                onlyLDH = false;
                if (disallowNonLDHDot) {
                    dest.setCharAt(i, 65533);
                    isASCII = false;
                }
            }
            i++;
        } while (i < limit);
        if (onlyLDH) {
            dest.insert(labelStart + labelLength, (char) 65533);
            return labelLength + 1;
        } else if (!toASCII || !isASCII || labelLength <= 63) {
            return labelLength;
        } else {
            addLabelError(info, IDNA.Error.LABEL_TOO_LONG);
            return labelLength;
        }
    }

    private void checkLabelBiDi(CharSequence label, int labelStart, int labelLength, IDNA.Info info) {
        int dir;
        int c = Character.codePointAt(label, labelStart);
        int i = labelStart + Character.charCount(c);
        int firstMask = U_MASK(UBiDiProps.INSTANCE.getClass(c));
        if (((~L_R_AL_MASK) & firstMask) != 0) {
            setNotOkBiDi(info);
        }
        int labelLimit = labelStart + labelLength;
        while (true) {
            if (i < labelLimit) {
                int c2 = Character.codePointBefore(label, labelLimit);
                labelLimit -= Character.charCount(c2);
                int dir2 = UBiDiProps.INSTANCE.getClass(c2);
                if (dir2 != 17) {
                    dir = U_MASK(dir2);
                    break;
                }
            } else {
                dir = firstMask;
                break;
            }
        }
        if ((L_MASK & firstMask) == 0 ? ((~R_AL_EN_AN_MASK) & dir) != 0 : ((~L_EN_MASK) & dir) != 0) {
            setNotOkBiDi(info);
        }
        int mask = firstMask | dir;
        while (i < labelLimit) {
            int c3 = Character.codePointAt(label, i);
            i += Character.charCount(c3);
            mask |= U_MASK(UBiDiProps.INSTANCE.getClass(c3));
        }
        if ((L_MASK & firstMask) == 0) {
            if (((~R_AL_AN_EN_ES_CS_ET_ON_BN_NSM_MASK) & mask) != 0) {
                setNotOkBiDi(info);
            }
            int i2 = EN_AN_MASK;
            if ((mask & i2) == i2) {
                setNotOkBiDi(info);
            }
        } else if (((~L_EN_ES_CS_ET_ON_BN_NSM_MASK) & mask) != 0) {
            setNotOkBiDi(info);
        }
        if ((R_AL_AN_MASK & mask) != 0) {
            setBiDi(info);
        }
    }

    private static boolean isASCIIOkBiDi(CharSequence s, int length) {
        char c;
        int labelStart = 0;
        for (int i = 0; i < length; i++) {
            char c2 = s.charAt(i);
            if (c2 == '.') {
                if (i > labelStart && (('a' > (c = s.charAt(i - 1)) || c > 'z') && ('0' > c || c > '9'))) {
                    return false;
                }
                labelStart = i + 1;
            } else if (i == labelStart) {
                if ('a' > c2 || c2 > 'z') {
                    return false;
                }
            } else if (c2 <= ' ' && (c2 >= 28 || ('\t' <= c2 && c2 <= '\r'))) {
                return false;
            }
        }
        return true;
    }

    private boolean isLabelOkContextJ(CharSequence label, int labelStart, int labelLength) {
        int labelLimit = labelStart + labelLength;
        for (int i = labelStart; i < labelLimit; i++) {
            if (label.charAt(i) == 8204) {
                if (i == labelStart) {
                    return false;
                }
                int c = Character.codePointBefore(label, i);
                int j = i - Character.charCount(c);
                if (uts46Norm2.getCombiningClass(c) == 9) {
                    continue;
                } else {
                    while (true) {
                        int type = UBiDiProps.INSTANCE.getJoiningType(c);
                        if (type == 5) {
                            if (j == 0) {
                                return false;
                            }
                            c = Character.codePointBefore(label, j);
                            j -= Character.charCount(c);
                        } else if (type != 3 && type != 2) {
                            return false;
                        } else {
                            int j2 = i + 1;
                            while (j2 != labelLimit) {
                                int c2 = Character.codePointAt(label, j2);
                                j2 += Character.charCount(c2);
                                int type2 = UBiDiProps.INSTANCE.getJoiningType(c2);
                                if (type2 != 5) {
                                    if (!(type2 == 4 || type2 == 2)) {
                                        return false;
                                    }
                                }
                            }
                            return false;
                        }
                    }
                }
            } else if (label.charAt(i) != 8205) {
                continue;
            } else if (i == labelStart) {
                return false;
            } else {
                if (uts46Norm2.getCombiningClass(Character.codePointBefore(label, i)) != 9) {
                    return false;
                }
            }
        }
        return true;
    }

    private void checkLabelContextO(CharSequence label, int labelStart, int labelLength, IDNA.Info info) {
        int labelEnd = (labelStart + labelLength) - 1;
        int arabicDigits = 0;
        for (int i = labelStart; i <= labelEnd; i++) {
            int c = label.charAt(i);
            if (c >= 183) {
                if (c <= 1785) {
                    if (c == 183) {
                        if (labelStart >= i || label.charAt(i - 1) != 'l' || i >= labelEnd || label.charAt(i + 1) != 'l') {
                            addLabelError(info, IDNA.Error.CONTEXTO_PUNCTUATION);
                        }
                    } else if (c == 885) {
                        if (i >= labelEnd || 14 != UScript.getScript(Character.codePointAt(label, i + 1))) {
                            addLabelError(info, IDNA.Error.CONTEXTO_PUNCTUATION);
                        }
                    } else if (c == 1523 || c == 1524) {
                        if (labelStart >= i || 19 != UScript.getScript(Character.codePointBefore(label, i))) {
                            addLabelError(info, IDNA.Error.CONTEXTO_PUNCTUATION);
                        }
                    } else if (1632 <= c) {
                        if (c <= 1641) {
                            if (arabicDigits > 0) {
                                addLabelError(info, IDNA.Error.CONTEXTO_DIGITS);
                            }
                            arabicDigits = -1;
                        } else if (1776 <= c) {
                            if (arabicDigits < 0) {
                                addLabelError(info, IDNA.Error.CONTEXTO_DIGITS);
                            }
                            arabicDigits = 1;
                        }
                    }
                } else if (c == 12539) {
                    int j = labelStart;
                    while (true) {
                        if (j > labelEnd) {
                            addLabelError(info, IDNA.Error.CONTEXTO_PUNCTUATION);
                            break;
                        }
                        int c2 = Character.codePointAt(label, j);
                        int script = UScript.getScript(c2);
                        if (script == 20 || script == 22 || script == 17) {
                            break;
                        }
                        j += Character.charCount(c2);
                    }
                }
            }
        }
    }

    private static int U_MASK(int x) {
        return 1 << x;
    }

    private static int U_GET_GC_MASK(int c) {
        return 1 << UCharacter.getType(c);
    }
}
