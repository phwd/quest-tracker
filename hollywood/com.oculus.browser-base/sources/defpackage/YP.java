package defpackage;

import android.content.Context;
import android.text.SpannableString;
import android.text.format.Formatter;
import android.text.style.TtsSpan;

/* renamed from: YP  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class YP {
    public static CharSequence a(Context context, long j) {
        String formatFileSize = Formatter.formatFileSize(context, j);
        if (j / 1024 >= 1) {
            return formatFileSize;
        }
        TtsSpan build = new TtsSpan.MeasureBuilder().setNumber(j).setUnit("byte").build();
        SpannableString spannableString = new SpannableString(formatFileSize);
        spannableString.setSpan(build, 0, spannableString.length(), 0);
        return spannableString;
    }
}
