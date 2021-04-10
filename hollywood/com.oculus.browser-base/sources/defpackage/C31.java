package defpackage;

import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.TextAppearanceSpan;
import android.text.style.UpdateAppearance;

/* renamed from: C31  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C31 extends SpannableString {
    public C31(CharSequence charSequence) {
        super(charSequence);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof C31)) {
            return false;
        }
        C31 c31 = (C31) obj;
        if (!TextUtils.equals(this, c31)) {
            return false;
        }
        if (TextUtils.isEmpty(this)) {
            return true;
        }
        UpdateAppearance[] updateAppearanceArr = (UpdateAppearance[]) getSpans(0, length(), UpdateAppearance.class);
        UpdateAppearance[] updateAppearanceArr2 = (UpdateAppearance[]) c31.getSpans(0, c31.length(), UpdateAppearance.class);
        if (updateAppearanceArr.length != updateAppearanceArr2.length) {
            return false;
        }
        for (int i = 0; i < updateAppearanceArr.length; i++) {
            UpdateAppearance updateAppearance = updateAppearanceArr[i];
            UpdateAppearance updateAppearance2 = updateAppearanceArr2[i];
            if (getSpanStart(updateAppearance) == c31.getSpanStart(updateAppearance2) && getSpanEnd(updateAppearance) == c31.getSpanEnd(updateAppearance2) && getSpanFlags(updateAppearance) == c31.getSpanFlags(updateAppearance2) && updateAppearance.getClass() == updateAppearance2.getClass()) {
                if (updateAppearance instanceof ForegroundColorSpan) {
                    if (((ForegroundColorSpan) updateAppearance).getForegroundColor() != ((ForegroundColorSpan) updateAppearance2).getForegroundColor()) {
                        return false;
                    }
                } else if (updateAppearance instanceof BackgroundColorSpan) {
                    if (((BackgroundColorSpan) updateAppearance).getBackgroundColor() != ((BackgroundColorSpan) updateAppearance2).getBackgroundColor()) {
                        return false;
                    }
                } else if (updateAppearance instanceof StyleSpan) {
                    if (((StyleSpan) updateAppearance).getStyle() != ((StyleSpan) updateAppearance2).getStyle()) {
                        return false;
                    }
                } else if (updateAppearance instanceof TextAppearanceSpan) {
                    TextAppearanceSpan textAppearanceSpan = (TextAppearanceSpan) updateAppearance;
                    TextAppearanceSpan textAppearanceSpan2 = (TextAppearanceSpan) updateAppearance2;
                    if (TextUtils.equals(textAppearanceSpan.getFamily(), textAppearanceSpan2.getFamily()) && textAppearanceSpan.getLinkTextColor().equals(textAppearanceSpan2.getLinkTextColor()) && textAppearanceSpan.getTextColor().equals(textAppearanceSpan2.getTextColor()) && textAppearanceSpan.getTextSize() == textAppearanceSpan2.getTextSize() && textAppearanceSpan.getTextStyle() == textAppearanceSpan2.getTextStyle()) {
                    }
                }
            }
            return false;
        }
        return true;
    }
}
