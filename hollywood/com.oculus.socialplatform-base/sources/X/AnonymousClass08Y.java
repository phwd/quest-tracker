package X;

import android.graphics.Paint;
import android.os.Build;
import android.view.ActionMode;
import android.widget.TextView;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Px;
import androidx.annotation.RestrictTo;

/* renamed from: X.08Y  reason: invalid class name */
public final class AnonymousClass08Y {
    @NonNull
    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public static ActionMode.Callback A00(@NonNull TextView textView, @NonNull ActionMode.Callback callback) {
        int i = Build.VERSION.SDK_INT;
        if (i < 26 || i > 27 || (callback instanceof AnonymousClass08X)) {
            return callback;
        }
        return new AnonymousClass08X(callback, textView);
    }

    public static void A01(@NonNull TextView textView, @IntRange(from = 0) @Px int i) {
        int i2;
        if (i < 0) {
            throw new IllegalArgumentException();
        } else if (Build.VERSION.SDK_INT >= 28) {
            textView.setFirstBaselineToTopHeight(i);
        } else {
            Paint.FontMetricsInt fontMetricsInt = textView.getPaint().getFontMetricsInt();
            if (textView.getIncludeFontPadding()) {
                i2 = fontMetricsInt.top;
            } else {
                i2 = fontMetricsInt.ascent;
            }
            if (i > Math.abs(i2)) {
                textView.setPadding(textView.getPaddingLeft(), i + i2, textView.getPaddingRight(), textView.getPaddingBottom());
            }
        }
    }

    public static void A02(@NonNull TextView textView, @IntRange(from = 0) @Px int i) {
        int i2;
        if (i >= 0) {
            Paint.FontMetricsInt fontMetricsInt = textView.getPaint().getFontMetricsInt();
            if (textView.getIncludeFontPadding()) {
                i2 = fontMetricsInt.bottom;
            } else {
                i2 = fontMetricsInt.descent;
            }
            if (i > Math.abs(i2)) {
                textView.setPadding(textView.getPaddingLeft(), textView.getPaddingTop(), textView.getPaddingRight(), i - i2);
                return;
            }
            return;
        }
        throw new IllegalArgumentException();
    }

    public static void A03(@NonNull TextView textView, @IntRange(from = 0) @Px int i) {
        if (i >= 0) {
            int fontMetricsInt = textView.getPaint().getFontMetricsInt(null);
            if (i != fontMetricsInt) {
                textView.setLineSpacing((float) (i - fontMetricsInt), 1.0f);
                return;
            }
            return;
        }
        throw new IllegalArgumentException();
    }
}
