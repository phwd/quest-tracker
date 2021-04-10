package android.icu.impl.number;

import android.icu.impl.number.Padder;

public class PatternStringUtils {
    /* JADX WARNING: Code restructure failed: missing block: B:104:0x0259, code lost:
        if (r4.length() == 0) goto L_0x027c;
     */
    /* JADX WARNING: Removed duplicated region for block: B:108:0x0265  */
    /* JADX WARNING: Removed duplicated region for block: B:111:0x0272  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0139 A[LOOP:2: B:31:0x0130->B:33:0x0139, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0141 A[LOOP:3: B:34:0x013e->B:36:0x0141, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0154  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x015c  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0162  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0165  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x01b1  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x01cc  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x01db A[LOOP:6: B:78:0x01db->B:80:0x01e3, LOOP_START, PHI: r1 
      PHI: (r1v15 int) = (r1v11 int), (r1v18 int) binds: [B:77:0x01d9, B:80:0x01e3] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x0239  */
    /* JADX WARNING: Removed duplicated region for block: B:99:0x0245  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String propertiesToPatternString(android.icu.impl.number.DecimalFormatProperties r28) {
        /*
        // Method dump skipped, instructions count: 641
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.impl.number.PatternStringUtils.propertiesToPatternString(android.icu.impl.number.DecimalFormatProperties):java.lang.String");
    }

    /* renamed from: android.icu.impl.number.PatternStringUtils$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$android$icu$impl$number$Padder$PadPosition = new int[Padder.PadPosition.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(10:0|1|2|3|4|5|6|7|8|10) */
        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        static {
            /*
                android.icu.impl.number.Padder$PadPosition[] r0 = android.icu.impl.number.Padder.PadPosition.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                android.icu.impl.number.PatternStringUtils.AnonymousClass1.$SwitchMap$android$icu$impl$number$Padder$PadPosition = r0
                int[] r0 = android.icu.impl.number.PatternStringUtils.AnonymousClass1.$SwitchMap$android$icu$impl$number$Padder$PadPosition     // Catch:{ NoSuchFieldError -> 0x0014 }
                android.icu.impl.number.Padder$PadPosition r1 = android.icu.impl.number.Padder.PadPosition.BEFORE_PREFIX     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = android.icu.impl.number.PatternStringUtils.AnonymousClass1.$SwitchMap$android$icu$impl$number$Padder$PadPosition     // Catch:{ NoSuchFieldError -> 0x001f }
                android.icu.impl.number.Padder$PadPosition r1 = android.icu.impl.number.Padder.PadPosition.AFTER_PREFIX     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = android.icu.impl.number.PatternStringUtils.AnonymousClass1.$SwitchMap$android$icu$impl$number$Padder$PadPosition     // Catch:{ NoSuchFieldError -> 0x002a }
                android.icu.impl.number.Padder$PadPosition r1 = android.icu.impl.number.Padder.PadPosition.BEFORE_SUFFIX     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = android.icu.impl.number.PatternStringUtils.AnonymousClass1.$SwitchMap$android$icu$impl$number$Padder$PadPosition     // Catch:{ NoSuchFieldError -> 0x0035 }
                android.icu.impl.number.Padder$PadPosition r1 = android.icu.impl.number.Padder.PadPosition.AFTER_SUFFIX     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: android.icu.impl.number.PatternStringUtils.AnonymousClass1.<clinit>():void");
        }
    }

    private static int escapePaddingString(CharSequence charSequence, StringBuilder sb, int i) {
        if (charSequence == null || charSequence.length() == 0) {
            charSequence = " ";
        }
        int length = sb.length();
        int i2 = 1;
        if (charSequence.length() != 1) {
            sb.insert(i, '\'');
            for (int i3 = 0; i3 < charSequence.length(); i3++) {
                char charAt = charSequence.charAt(i3);
                if (charAt == '\'') {
                    sb.insert(i + i2, "''");
                    i2 += 2;
                } else {
                    sb.insert(i + i2, charAt);
                    i2++;
                }
            }
            sb.insert(i + i2, '\'');
        } else if (charSequence.equals("'")) {
            sb.insert(i, "''");
        } else {
            sb.insert(i, charSequence);
        }
        return sb.length() - length;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:34:0x004d, code lost:
        if (r9 != android.icu.number.NumberFormatter.SignDisplay.NEVER) goto L_0x0053;
     */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x005d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void patternInfoToStringBuilder(android.icu.impl.number.AffixPatternProvider r6, boolean r7, int r8, android.icu.number.NumberFormatter.SignDisplay r9, android.icu.impl.StandardPlural r10, boolean r11, java.lang.StringBuilder r12) {
        /*
        // Method dump skipped, instructions count: 135
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.impl.number.PatternStringUtils.patternInfoToStringBuilder(android.icu.impl.number.AffixPatternProvider, boolean, int, android.icu.number.NumberFormatter$SignDisplay, android.icu.impl.StandardPlural, boolean, java.lang.StringBuilder):void");
    }
}
