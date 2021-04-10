package X;

import android.annotation.SuppressLint;
import android.os.Build;
import android.text.PrecomputedText;
import android.text.TextDirectionHeuristic;
import android.text.TextPaint;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import java.util.Objects;

/* renamed from: X.09m  reason: invalid class name */
public final class AnonymousClass09m {
    public final PrecomputedText.Params A00;
    public final int A01;
    public final int A02;
    @Nullable
    public final TextDirectionHeuristic A03;
    @NonNull
    public final TextPaint A04;

    /* JADX WARNING: Code restructure failed: missing block: B:25:0x007a, code lost:
        if (r2.getTypeface() != null) goto L_0x007c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean equals(@androidx.annotation.Nullable java.lang.Object r7) {
        /*
        // Method dump skipped, instructions count: 148
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass09m.equals(java.lang.Object):boolean");
    }

    public final int hashCode() {
        TextPaint textPaint = this.A04;
        return Objects.hash(Float.valueOf(textPaint.getTextSize()), Float.valueOf(textPaint.getTextScaleX()), Float.valueOf(textPaint.getTextSkewX()), Float.valueOf(textPaint.getLetterSpacing()), Integer.valueOf(textPaint.getFlags()), textPaint.getTextLocales(), textPaint.getTypeface(), Boolean.valueOf(textPaint.isElegantTextHeight()), this.A03, Integer.valueOf(this.A01), Integer.valueOf(this.A02));
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("{");
        StringBuilder sb2 = new StringBuilder("textSize=");
        TextPaint textPaint = this.A04;
        sb2.append(textPaint.getTextSize());
        sb.append(sb2.toString());
        sb.append(", textScaleX=" + textPaint.getTextScaleX());
        sb.append(", textSkewX=" + textPaint.getTextSkewX());
        sb.append(", letterSpacing=" + textPaint.getLetterSpacing());
        sb.append(", elegantTextHeight=" + textPaint.isElegantTextHeight());
        sb.append(", textLocale=" + textPaint.getTextLocales());
        StringBuilder sb3 = new StringBuilder(", typeface=");
        sb3.append(textPaint.getTypeface());
        sb.append(sb3.toString());
        if (Build.VERSION.SDK_INT >= 26) {
            sb.append(AnonymousClass006.A05(", variationSettings=", textPaint.getFontVariationSettings()));
        }
        sb.append(", textDir=" + this.A03);
        sb.append(AnonymousClass006.A01(", breakStrategy=", this.A01));
        sb.append(AnonymousClass006.A01(", hyphenationFrequency=", this.A02));
        sb.append("}");
        return sb.toString();
    }

    @RequiresApi(28)
    public AnonymousClass09m(@NonNull PrecomputedText.Params params) {
        this.A04 = params.getTextPaint();
        this.A03 = params.getTextDirection();
        this.A01 = params.getBreakStrategy();
        this.A02 = params.getHyphenationFrequency();
        this.A00 = Build.VERSION.SDK_INT < 29 ? null : params;
    }

    @SuppressLint({"NewApi"})
    public AnonymousClass09m(@NonNull TextPaint textPaint, @NonNull TextDirectionHeuristic textDirectionHeuristic, int i, int i2) {
        PrecomputedText.Params params;
        if (Build.VERSION.SDK_INT >= 29) {
            params = new PrecomputedText.Params.Builder(textPaint).setBreakStrategy(i).setHyphenationFrequency(i2).setTextDirection(textDirectionHeuristic).build();
        } else {
            params = null;
        }
        this.A00 = params;
        this.A04 = textPaint;
        this.A03 = textDirectionHeuristic;
        this.A01 = i;
        this.A02 = i2;
    }
}
