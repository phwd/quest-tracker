package defpackage;

import android.text.StaticLayout;
import android.widget.TextView;

/* renamed from: P8  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class P8 extends O8 {
    @Override // defpackage.Q8, defpackage.O8
    public void a(StaticLayout.Builder builder, TextView textView) {
        builder.setTextDirection(textView.getTextDirectionHeuristic());
    }

    @Override // defpackage.Q8
    public boolean b(TextView textView) {
        return textView.isHorizontallyScrollable();
    }
}
