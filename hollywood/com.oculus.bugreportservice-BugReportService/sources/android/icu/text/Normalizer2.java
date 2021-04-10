package android.icu.text;

import android.icu.impl.Norm2AllModes;
import android.icu.text.Normalizer;

public abstract class Normalizer2 {
    public abstract StringBuilder append(StringBuilder sb, CharSequence charSequence);

    public int getCombiningClass(int i) {
        return 0;
    }

    public abstract boolean hasBoundaryAfter(int i);

    public abstract boolean hasBoundaryBefore(int i);

    public abstract boolean isInert(int i);

    public abstract boolean isNormalized(CharSequence charSequence);

    public abstract StringBuilder normalize(CharSequence charSequence, StringBuilder sb);

    public abstract StringBuilder normalizeSecondAndAppend(StringBuilder sb, CharSequence charSequence);

    public abstract Normalizer.QuickCheckResult quickCheck(CharSequence charSequence);

    public abstract int spanQuickCheckYes(CharSequence charSequence);

    public static Normalizer2 getNFCInstance() {
        return Norm2AllModes.getNFCInstance().comp;
    }

    public static Normalizer2 getNFDInstance() {
        return Norm2AllModes.getNFCInstance().decomp;
    }

    public static Normalizer2 getNFKCInstance() {
        return Norm2AllModes.getNFKCInstance().comp;
    }

    public static Normalizer2 getNFKDInstance() {
        return Norm2AllModes.getNFKCInstance().decomp;
    }

    public String normalize(CharSequence charSequence) {
        if (charSequence instanceof String) {
            int spanQuickCheckYes = spanQuickCheckYes(charSequence);
            if (spanQuickCheckYes == charSequence.length()) {
                return (String) charSequence;
            }
            if (spanQuickCheckYes != 0) {
                StringBuilder sb = new StringBuilder(charSequence.length());
                sb.append(charSequence, 0, spanQuickCheckYes);
                return normalizeSecondAndAppend(sb, charSequence.subSequence(spanQuickCheckYes, charSequence.length())).toString();
            }
        }
        return normalize(charSequence, new StringBuilder(charSequence.length())).toString();
    }

    protected Normalizer2() {
    }
}
