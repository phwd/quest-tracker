package java.lang;

import android.icu.text.Transliterator;
import java.util.Locale;
import libcore.icu.ICU;

/* access modifiers changed from: package-private */
public class CaseMapper {
    private static final ThreadLocal EL_UPPER = new ThreadLocal() {
        /* class java.lang.CaseMapper.AnonymousClass1 */

        /* access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        public Transliterator initialValue() {
            return Transliterator.getInstance("el-Upper");
        }
    };
    private static final char[] upperValues = "SS\u0000ʼN\u0000J̌\u0000Ϊ́Ϋ́ԵՒ\u0000H̱\u0000T̈\u0000W̊\u0000Y̊\u0000Aʾ\u0000Υ̓\u0000Υ̓̀Υ̓́Υ̓͂ἈΙ\u0000ἉΙ\u0000ἊΙ\u0000ἋΙ\u0000ἌΙ\u0000ἍΙ\u0000ἎΙ\u0000ἏΙ\u0000ἈΙ\u0000ἉΙ\u0000ἊΙ\u0000ἋΙ\u0000ἌΙ\u0000ἍΙ\u0000ἎΙ\u0000ἏΙ\u0000ἨΙ\u0000ἩΙ\u0000ἪΙ\u0000ἫΙ\u0000ἬΙ\u0000ἭΙ\u0000ἮΙ\u0000ἯΙ\u0000ἨΙ\u0000ἩΙ\u0000ἪΙ\u0000ἫΙ\u0000ἬΙ\u0000ἭΙ\u0000ἮΙ\u0000ἯΙ\u0000ὨΙ\u0000ὩΙ\u0000ὪΙ\u0000ὫΙ\u0000ὬΙ\u0000ὭΙ\u0000ὮΙ\u0000ὯΙ\u0000ὨΙ\u0000ὩΙ\u0000ὪΙ\u0000ὫΙ\u0000ὬΙ\u0000ὭΙ\u0000ὮΙ\u0000ὯΙ\u0000ᾺΙ\u0000ΑΙ\u0000ΆΙ\u0000Α͂\u0000Α͂ΙΑΙ\u0000ῊΙ\u0000ΗΙ\u0000ΉΙ\u0000Η͂\u0000Η͂ΙΗΙ\u0000Ϊ̀Ϊ́Ι͂\u0000Ϊ͂Ϋ̀Ϋ́Ρ̓\u0000Υ͂\u0000Ϋ͂ῺΙ\u0000ΩΙ\u0000ΏΙ\u0000Ω͂\u0000Ω͂ΙΩΙ\u0000FF\u0000FI\u0000FL\u0000FFIFFLST\u0000ST\u0000ՄՆ\u0000ՄԵ\u0000ՄԻ\u0000ՎՆ\u0000ՄԽ\u0000".toCharArray();
    private static final char[] upperValues2 = "\u000b\u0000\f\u0000\r\u0000\u000e\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u000f\u0010\u0011\u0012\u0013\u0014\u0015\u0016\u0017\u0018\u0019\u001a\u001b\u001c\u001d\u001e\u001f !\"#$%&'()*+,-./0123456789:;<=>\u0000\u0000?@A\u0000BC\u0000\u0000\u0000\u0000D\u0000\u0000\u0000\u0000\u0000EFG\u0000HI\u0000\u0000\u0000\u0000J\u0000\u0000\u0000\u0000\u0000KL\u0000\u0000MN\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000OPQ\u0000RS\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000TUV\u0000WX\u0000\u0000\u0000\u0000Y".toCharArray();

    public static String toLowerCase(Locale locale, String str) {
        char c;
        String language = locale.getLanguage();
        if (language.equals("tr") || language.equals("az") || language.equals("lt")) {
            return ICU.toLowerCase(str, locale);
        }
        int length = str.length();
        char[] cArr = null;
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (charAt == 304 || Character.isHighSurrogate(charAt)) {
                return ICU.toLowerCase(str, locale);
            }
            if (charAt != 931 || !isFinalSigma(str, i)) {
                c = Character.toLowerCase(charAt);
            } else {
                c = 962;
            }
            if (charAt != c) {
                if (cArr == null) {
                    cArr = new char[length];
                    str.getCharsNoCheck(0, length, cArr, 0);
                }
                cArr[i] = c;
            }
        }
        if (cArr == null) {
            return str;
        }
        new String(cArr);
        throw null;
    }

    private static boolean isFinalSigma(String str, int i) {
        if (i <= 0) {
            return false;
        }
        char charAt = str.charAt(i - 1);
        if (!Character.isLowerCase(charAt) && !Character.isUpperCase(charAt) && !Character.isTitleCase(charAt)) {
            return false;
        }
        int i2 = i + 1;
        if (i2 >= str.length()) {
            return true;
        }
        char charAt2 = str.charAt(i2);
        if (Character.isLowerCase(charAt2) || Character.isUpperCase(charAt2) || Character.isTitleCase(charAt2)) {
            return false;
        }
        return true;
    }

    private static int upperIndex(int i) {
        int i2;
        if (i < 223) {
            return -1;
        }
        if (i <= 1415) {
            if (i == 223) {
                return 0;
            }
            if (i == 329) {
                return 1;
            }
            if (i == 496) {
                return 2;
            }
            if (i == 912) {
                return 3;
            }
            if (i != 944) {
                return i != 1415 ? -1 : 5;
            }
            return 4;
        } else if (i < 7830) {
            return -1;
        } else {
            if (i <= 7834) {
                return (i + 6) - 7830;
            }
            if (i < 8016 || i > 8188) {
                int i3 = 64256;
                if (i < 64256) {
                    return -1;
                }
                if (i <= 64262) {
                    i2 = i + 90;
                } else {
                    i3 = 64275;
                    if (i < 64275 || i > 64279) {
                        return -1;
                    }
                    i2 = i + 97;
                }
                return i2 - i3;
            }
            char c = upperValues2[i - 8016];
            if (c == 0) {
                return -1;
            }
            return c;
        }
    }

    public static String toUpperCase(Locale locale, String str, int i) {
        String language = locale.getLanguage();
        if (language.equals("tr") || language.equals("az") || language.equals("lt")) {
            return ICU.toUpperCase(str, locale);
        }
        if (language.equals("el")) {
            return ((Transliterator) EL_UPPER.get()).transliterate(str);
        }
        char[] cArr = null;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            char charAt = str.charAt(i3);
            if (Character.isHighSurrogate(charAt)) {
                return ICU.toUpperCase(str, locale);
            }
            int upperIndex = upperIndex(charAt);
            int i4 = 2;
            if (upperIndex == -1) {
                if (cArr != null && i2 >= cArr.length) {
                    char[] cArr2 = new char[(cArr.length + (i / 6) + 2)];
                    System.arraycopy((Object) cArr, 0, (Object) cArr2, 0, cArr.length);
                    cArr = cArr2;
                }
                char upperCase = Character.toUpperCase(charAt);
                if (cArr != null) {
                    cArr[i2] = upperCase;
                    i2++;
                } else if (charAt != upperCase) {
                    cArr = new char[i];
                    str.getCharsNoCheck(0, i3, cArr, 0);
                    i2 = i3 + 1;
                    cArr[i3] = upperCase;
                }
            } else {
                int i5 = upperIndex * 3;
                char c = upperValues[i5 + 2];
                if (cArr == null) {
                    cArr = new char[((i / 6) + i + 2)];
                    str.getCharsNoCheck(0, i3, cArr, 0);
                    i2 = i3;
                } else {
                    if (c == 0) {
                        i4 = 1;
                    }
                    if (i4 + i2 >= cArr.length) {
                        char[] cArr3 = new char[(cArr.length + (i / 6) + 3)];
                        System.arraycopy((Object) cArr, 0, (Object) cArr3, 0, cArr.length);
                        cArr = cArr3;
                    }
                }
                char[] cArr4 = upperValues;
                int i6 = i2 + 1;
                cArr[i2] = cArr4[i5];
                char c2 = cArr4[i5 + 1];
                int i7 = i6 + 1;
                cArr[i6] = c2;
                if (c != 0) {
                    i2 = i7 + 1;
                    cArr[i7] = c;
                } else {
                    i2 = i7;
                }
            }
        }
        if (cArr == null) {
            return str;
        }
        if (cArr.length == i2 || cArr.length - i2 < 8) {
            new String(0, i2, cArr);
            throw null;
        }
        new String(cArr, 0, i2);
        throw null;
    }
}
