package com.facebook.common.util;

import android.annotation.TargetApi;
import android.os.Build;
import android.widget.TextView;
import androidx.core.text.TextDirectionHeuristicCompat;
import androidx.core.text.TextDirectionHeuristicsCompat;
import androidx.core.view.ViewCompat;
import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class TextRTLUtil {
    public static boolean isRtl(TextView textView, CharSequence charSequence, int i) {
        return getTextDirectionHeuristic(textView).isRtl(charSequence, 0, i);
    }

    private static TextDirectionHeuristicCompat getTextDirectionHeuristic(TextView textView) {
        if (Build.VERSION.SDK_INT >= 17) {
            return getTextDirectionHeuristicApi17(textView);
        }
        return getTextDirectionHeuristicBelowApi17(textView);
    }

    @TargetApi(17)
    private static TextDirectionHeuristicCompat getTextDirectionHeuristicApi17(TextView textView) {
        int textDirection = textView.getTextDirection();
        if (textDirection != 1) {
            if (textDirection == 2) {
                return TextDirectionHeuristicsCompat.ANYRTL_LTR;
            }
            if (textDirection == 3) {
                return TextDirectionHeuristicsCompat.LTR;
            }
            if (textDirection == 4) {
                return TextDirectionHeuristicsCompat.RTL;
            }
            if (textDirection != 5) {
                return TextDirectionHeuristicsCompat.FIRSTSTRONG_LTR;
            }
            return TextDirectionHeuristicsCompat.LOCALE;
        } else if (textView.getLayoutDirection() == 1) {
            return TextDirectionHeuristicsCompat.FIRSTSTRONG_RTL;
        } else {
            return TextDirectionHeuristicsCompat.FIRSTSTRONG_LTR;
        }
    }

    private static TextDirectionHeuristicCompat getTextDirectionHeuristicBelowApi17(TextView textView) {
        if (ViewCompat.getLayoutDirection(textView) == 1) {
            return TextDirectionHeuristicsCompat.FIRSTSTRONG_RTL;
        }
        return TextDirectionHeuristicsCompat.FIRSTSTRONG_LTR;
    }
}
