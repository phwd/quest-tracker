package defpackage;

import android.text.StaticLayout;
import android.widget.TextView;

/* renamed from: Q8  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class Q8 {
    public abstract void a(StaticLayout.Builder builder, TextView textView);

    public boolean b(TextView textView) {
        return ((Boolean) R8.e(textView, "getHorizontallyScrolling", Boolean.FALSE)).booleanValue();
    }
}
