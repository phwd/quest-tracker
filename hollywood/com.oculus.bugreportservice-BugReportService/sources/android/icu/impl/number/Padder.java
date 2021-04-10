package android.icu.impl.number;

import android.icu.text.NumberFormat;

public class Padder {
    public static final Padder NONE = new Padder(null, -1, null);
    String paddingString;
    PadPosition position;
    int targetWidth;

    public enum PadPosition {
        BEFORE_PREFIX,
        AFTER_PREFIX,
        BEFORE_SUFFIX,
        AFTER_SUFFIX
    }

    public Padder(String str, int i, PadPosition padPosition) {
        this.paddingString = str == null ? " " : str;
        this.targetWidth = i;
        this.position = padPosition == null ? PadPosition.BEFORE_PREFIX : padPosition;
    }

    public static Padder forProperties(DecimalFormatProperties decimalFormatProperties) {
        return new Padder(decimalFormatProperties.getPadString(), decimalFormatProperties.getFormatWidth(), decimalFormatProperties.getPadPosition());
    }

    public boolean isValid() {
        return this.targetWidth > 0;
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x004f  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0057  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int padAndApply(android.icu.impl.number.Modifier r5, android.icu.impl.number.Modifier r6, android.icu.impl.number.NumberStringBuilder r7, int r8, int r9) {
        /*
            r4 = this;
            int r0 = r5.getCodePointCount()
            int r1 = r6.getCodePointCount()
            int r0 = r0 + r1
            int r1 = r4.targetWidth
            int r1 = r1 - r0
            int r0 = r7.codePointCount()
            int r1 = r1 - r0
            r0 = 0
            if (r1 > 0) goto L_0x0020
            int r4 = r5.apply(r7, r8, r9)
            int r4 = r4 + r0
            int r9 = r9 + r4
            int r5 = r6.apply(r7, r8, r9)
            int r4 = r4 + r5
            return r4
        L_0x0020:
            android.icu.impl.number.Padder$PadPosition r2 = r4.position
            android.icu.impl.number.Padder$PadPosition r3 = android.icu.impl.number.Padder.PadPosition.AFTER_PREFIX
            if (r2 != r3) goto L_0x002e
            java.lang.String r2 = r4.paddingString
            int r2 = addPaddingHelper(r2, r1, r7, r8)
        L_0x002c:
            int r0 = r0 + r2
            goto L_0x003b
        L_0x002e:
            android.icu.impl.number.Padder$PadPosition r3 = android.icu.impl.number.Padder.PadPosition.BEFORE_SUFFIX
            if (r2 != r3) goto L_0x003b
            java.lang.String r2 = r4.paddingString
            int r3 = r9 + 0
            int r2 = addPaddingHelper(r2, r1, r7, r3)
            goto L_0x002c
        L_0x003b:
            int r2 = r9 + r0
            int r5 = r5.apply(r7, r8, r2)
            int r0 = r0 + r5
            int r5 = r9 + r0
            int r5 = r6.apply(r7, r8, r5)
            int r0 = r0 + r5
            android.icu.impl.number.Padder$PadPosition r5 = r4.position
            android.icu.impl.number.Padder$PadPosition r6 = android.icu.impl.number.Padder.PadPosition.BEFORE_PREFIX
            if (r5 != r6) goto L_0x0057
            java.lang.String r4 = r4.paddingString
            int r4 = addPaddingHelper(r4, r1, r7, r8)
        L_0x0055:
            int r0 = r0 + r4
            goto L_0x0063
        L_0x0057:
            android.icu.impl.number.Padder$PadPosition r6 = android.icu.impl.number.Padder.PadPosition.AFTER_SUFFIX
            if (r5 != r6) goto L_0x0063
            java.lang.String r4 = r4.paddingString
            int r9 = r9 + r0
            int r4 = addPaddingHelper(r4, r1, r7, r9)
            goto L_0x0055
        L_0x0063:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.impl.number.Padder.padAndApply(android.icu.impl.number.Modifier, android.icu.impl.number.Modifier, android.icu.impl.number.NumberStringBuilder, int, int):int");
    }

    private static int addPaddingHelper(String str, int i, NumberStringBuilder numberStringBuilder, int i2) {
        for (int i3 = 0; i3 < i; i3++) {
            numberStringBuilder.insert(i2, str, (NumberFormat.Field) null);
        }
        return str.length() * i;
    }
}
