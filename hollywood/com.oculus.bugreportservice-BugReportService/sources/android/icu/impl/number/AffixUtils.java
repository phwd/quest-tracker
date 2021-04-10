package android.icu.impl.number;

import android.icu.text.NumberFormat;
import android.icu.text.UnicodeSet;

public class AffixUtils {

    public interface SymbolProvider {
        CharSequence getSymbol(int i);
    }

    public interface TokenConsumer {
        void consumeToken(int i);
    }

    private static int getCodePoint(long j) {
        return (int) (j >>> 40);
    }

    private static int getOffset(long j) {
        return (int) (j & -1);
    }

    private static int getState(long j) {
        return (int) ((j >>> 36) & 15);
    }

    private static int getType(long j) {
        return (int) ((j >>> 32) & 15);
    }

    private static long makeTag(int i, int i2, int i3, int i4) {
        return ((-((long) i2)) << 32) | ((long) i) | 0 | (((long) i3) << 36) | (((long) i4) << 40);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001e, code lost:
        if (r4 == 39) goto L_0x0031;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int estimateLength(java.lang.CharSequence r9) {
        /*
            r0 = 0
            if (r9 != 0) goto L_0x0004
            return r0
        L_0x0004:
            r1 = r0
            r2 = r1
            r3 = r2
        L_0x0007:
            int r4 = r9.length()
            r5 = 2
            r6 = 1
            if (r1 >= r4) goto L_0x0041
            int r4 = java.lang.Character.codePointAt(r9, r1)
            r7 = 3
            r8 = 39
            if (r2 == 0) goto L_0x0035
            if (r2 == r6) goto L_0x002b
            if (r2 == r5) goto L_0x0027
            if (r2 != r7) goto L_0x0021
            if (r4 != r8) goto L_0x0039
            goto L_0x0031
        L_0x0021:
            java.lang.AssertionError r9 = new java.lang.AssertionError
            r9.<init>()
            throw r9
        L_0x0027:
            if (r4 != r8) goto L_0x0039
            r2 = r7
            goto L_0x003b
        L_0x002b:
            if (r4 != r8) goto L_0x0031
            int r3 = r3 + 1
            r2 = r0
            goto L_0x003b
        L_0x0031:
            int r3 = r3 + 1
            r2 = r5
            goto L_0x003b
        L_0x0035:
            if (r4 != r8) goto L_0x0039
            r2 = r6
            goto L_0x003b
        L_0x0039:
            int r3 = r3 + 1
        L_0x003b:
            int r4 = java.lang.Character.charCount(r4)
            int r1 = r1 + r4
            goto L_0x0007
        L_0x0041:
            if (r2 == r6) goto L_0x0046
            if (r2 == r5) goto L_0x0046
            return r3
        L_0x0046:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Unterminated quote: \""
            r1.append(r2)
            r1.append(r9)
            java.lang.String r9 = "\""
            r1.append(r9)
            java.lang.String r9 = r1.toString()
            r0.<init>(r9)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.impl.number.AffixUtils.estimateLength(java.lang.CharSequence):int");
    }

    public static int escape(CharSequence charSequence, StringBuilder sb) {
        if (charSequence == null) {
            return 0;
        }
        int length = sb.length();
        int i = 0;
        boolean z = false;
        while (i < charSequence.length()) {
            int codePointAt = Character.codePointAt(charSequence, i);
            if (codePointAt != 37) {
                if (codePointAt == 39) {
                    sb.append("''");
                } else if (!(codePointAt == 43 || codePointAt == 45 || codePointAt == 164 || codePointAt == 8240)) {
                    if (z) {
                        sb.append('\'');
                        sb.appendCodePoint(codePointAt);
                        z = false;
                    } else {
                        sb.appendCodePoint(codePointAt);
                    }
                }
                i += Character.charCount(codePointAt);
            }
            if (!z) {
                sb.append('\'');
                sb.appendCodePoint(codePointAt);
                z = true;
            } else {
                sb.appendCodePoint(codePointAt);
            }
            i += Character.charCount(codePointAt);
        }
        if (z) {
            sb.append('\'');
        }
        return sb.length() - length;
    }

    public static String escape(CharSequence charSequence) {
        if (charSequence == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        escape(charSequence, sb);
        return sb.toString();
    }

    public static final NumberFormat.Field getFieldForType(int i) {
        if (i == -15) {
            return NumberFormat.Field.CURRENCY;
        }
        switch (i) {
            case -9:
                return NumberFormat.Field.CURRENCY;
            case -8:
                return NumberFormat.Field.CURRENCY;
            case -7:
                return NumberFormat.Field.CURRENCY;
            case -6:
                return NumberFormat.Field.CURRENCY;
            case -5:
                return NumberFormat.Field.CURRENCY;
            case -4:
                return NumberFormat.Field.PERMILLE;
            case -3:
                return NumberFormat.Field.PERCENT;
            case -2:
                return NumberFormat.Field.SIGN;
            case -1:
                return NumberFormat.Field.SIGN;
            default:
                throw new AssertionError();
        }
    }

    public static int unescape(CharSequence charSequence, NumberStringBuilder numberStringBuilder, int i, SymbolProvider symbolProvider) {
        int insertCodePoint;
        int i2 = 0;
        long j = 0;
        while (hasNext(j, charSequence)) {
            j = nextToken(j, charSequence);
            int typeOrCp = getTypeOrCp(j);
            if (typeOrCp == -15) {
                insertCodePoint = numberStringBuilder.insertCodePoint(i + i2, 65533, NumberFormat.Field.CURRENCY);
            } else if (typeOrCp < 0) {
                insertCodePoint = numberStringBuilder.insert(i + i2, symbolProvider.getSymbol(typeOrCp), getFieldForType(typeOrCp));
            } else {
                insertCodePoint = numberStringBuilder.insertCodePoint(i + i2, typeOrCp, null);
            }
            i2 += insertCodePoint;
        }
        return i2;
    }

    public static int unescapedCount(CharSequence charSequence, boolean z, SymbolProvider symbolProvider) {
        int i;
        long j = 0;
        int i2 = 0;
        while (hasNext(j, charSequence)) {
            j = nextToken(j, charSequence);
            int typeOrCp = getTypeOrCp(j);
            if (typeOrCp == -15) {
                i2++;
            } else {
                if (typeOrCp < 0) {
                    CharSequence symbol = symbolProvider.getSymbol(typeOrCp);
                    i = z ? symbol.length() : Character.codePointCount(symbol, 0, symbol.length());
                } else {
                    i = z ? Character.charCount(typeOrCp) : 1;
                }
                i2 += i;
            }
        }
        return i2;
    }

    public static boolean containsType(CharSequence charSequence, int i) {
        if (!(charSequence == null || charSequence.length() == 0)) {
            long j = 0;
            while (hasNext(j, charSequence)) {
                j = nextToken(j, charSequence);
                if (getTypeOrCp(j) == i) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean hasCurrencySymbols(CharSequence charSequence) {
        if (!(charSequence == null || charSequence.length() == 0)) {
            long j = 0;
            while (hasNext(j, charSequence)) {
                j = nextToken(j, charSequence);
                int typeOrCp = getTypeOrCp(j);
                if (typeOrCp < 0 && getFieldForType(typeOrCp) == NumberFormat.Field.CURRENCY) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean containsOnlySymbolsAndIgnorables(CharSequence charSequence, UnicodeSet unicodeSet) {
        if (charSequence == null) {
            return true;
        }
        long j = 0;
        while (hasNext(j, charSequence)) {
            j = nextToken(j, charSequence);
            int typeOrCp = getTypeOrCp(j);
            if (typeOrCp >= 0 && !unicodeSet.contains(typeOrCp)) {
                return false;
            }
        }
        return true;
    }

    public static void iterateWithConsumer(CharSequence charSequence, TokenConsumer tokenConsumer) {
        long j = 0;
        while (hasNext(j, charSequence)) {
            j = nextToken(j, charSequence);
            tokenConsumer.consumeToken(getTypeOrCp(j));
        }
    }

    private static long nextToken(long j, CharSequence charSequence) {
        int offset = getOffset(j);
        int state = getState(j);
        while (offset < charSequence.length()) {
            int codePointAt = Character.codePointAt(charSequence, offset);
            int charCount = Character.charCount(codePointAt);
            switch (state) {
                case 0:
                    if (codePointAt != 37) {
                        if (codePointAt == 39) {
                            state = 1;
                            break;
                        } else if (codePointAt == 43) {
                            return makeTag(offset + charCount, -2, 0, 0);
                        } else {
                            if (codePointAt != 45) {
                                if (codePointAt == 164) {
                                    state = 4;
                                    break;
                                } else if (codePointAt != 8240) {
                                    return makeTag(offset + charCount, 0, 0, codePointAt);
                                } else {
                                    return makeTag(offset + charCount, -4, 0, 0);
                                }
                            } else {
                                return makeTag(offset + charCount, -1, 0, 0);
                            }
                        }
                    } else {
                        return makeTag(offset + charCount, -3, 0, 0);
                    }
                case 1:
                    if (codePointAt == 39) {
                        return makeTag(offset + charCount, 0, 0, codePointAt);
                    }
                    return makeTag(offset + charCount, 0, 2, codePointAt);
                case 2:
                    if (codePointAt == 39) {
                        state = 3;
                        break;
                    } else {
                        return makeTag(offset + charCount, 0, 2, codePointAt);
                    }
                case 3:
                    if (codePointAt == 39) {
                        return makeTag(offset + charCount, 0, 2, codePointAt);
                    }
                    state = 0;
                    continue;
                case 4:
                    if (codePointAt == 164) {
                        state = 5;
                        break;
                    } else {
                        return makeTag(offset, -5, 0, 0);
                    }
                case 5:
                    if (codePointAt == 164) {
                        state = 6;
                        break;
                    } else {
                        return makeTag(offset, -6, 0, 0);
                    }
                case 6:
                    if (codePointAt == 164) {
                        state = 7;
                        break;
                    } else {
                        return makeTag(offset, -7, 0, 0);
                    }
                case 7:
                    if (codePointAt == 164) {
                        state = 8;
                        break;
                    } else {
                        return makeTag(offset, -8, 0, 0);
                    }
                case 8:
                    if (codePointAt == 164) {
                        state = 9;
                        break;
                    } else {
                        return makeTag(offset, -9, 0, 0);
                    }
                case 9:
                    if (codePointAt != 164) {
                        return makeTag(offset, -15, 0, 0);
                    }
                    break;
                default:
                    throw new AssertionError();
            }
            offset += charCount;
        }
        switch (state) {
            case 0:
                return -1;
            case 1:
            case 2:
                throw new IllegalArgumentException("Unterminated quote in pattern affix: \"" + ((Object) charSequence) + "\"");
            case 3:
                return -1;
            case 4:
                return makeTag(offset, -5, 0, 0);
            case 5:
                return makeTag(offset, -6, 0, 0);
            case 6:
                return makeTag(offset, -7, 0, 0);
            case 7:
                return makeTag(offset, -8, 0, 0);
            case 8:
                return makeTag(offset, -9, 0, 0);
            case 9:
                return makeTag(offset, -15, 0, 0);
            default:
                throw new AssertionError();
        }
    }

    private static boolean hasNext(long j, CharSequence charSequence) {
        int state = getState(j);
        int offset = getOffset(j);
        if (state == 2 && offset == charSequence.length() - 1 && charSequence.charAt(offset) == '\'') {
            return false;
        }
        if (state != 0) {
            return true;
        }
        return offset < charSequence.length();
    }

    private static int getTypeOrCp(long j) {
        int type = getType(j);
        return type == 0 ? getCodePoint(j) : -type;
    }
}
