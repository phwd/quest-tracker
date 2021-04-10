package defpackage;

import android.os.Build;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.material.datepicker.MaterialCalendarGridView;
import com.oculus.browser.R;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: El0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0276El0 extends XK0 {
    public final TextView Z;
    public final MaterialCalendarGridView a0;

    public C0276El0(LinearLayout linearLayout, boolean z) {
        super(linearLayout);
        TextView textView = (TextView) linearLayout.findViewById(R.id.month_title);
        this.Z = textView;
        AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
        Xt1 xt1 = new Xt1(R.id.tag_accessibility_heading, Boolean.class, 28);
        Boolean bool = Boolean.TRUE;
        if (Build.VERSION.SDK_INT >= 28) {
            xt1.d(textView, bool);
        } else if (xt1.e(xt1.c(textView), bool)) {
            C5349w d = AbstractC1920bu1.d(textView);
            AbstractC1920bu1.n(textView, d == null ? new C5349w() : d);
            textView.setTag(R.id.tag_accessibility_heading, bool);
            AbstractC1920bu1.h(textView, 0);
        }
        this.a0 = (MaterialCalendarGridView) linearLayout.findViewById(R.id.month_grid);
        if (!z) {
            textView.setVisibility(8);
        }
    }
}
